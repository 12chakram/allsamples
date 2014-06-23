package biz.neustar.dece.portal.saml.auth;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.opensaml.saml1.core.AttributeValue;
import org.opensaml.saml2.common.Extensions;
import org.opensaml.saml2.core.Assertion;
import org.opensaml.saml2.core.AssertionURIRef;
import org.opensaml.saml2.core.Attribute;
import org.opensaml.saml2.core.AttributeStatement;
import org.opensaml.saml2.core.Conditions;
import org.opensaml.saml2.core.Response;
import org.opensaml.xml.XMLObject;
import org.opensaml.xml.schema.impl.XSStringImpl;

public class SAMLTokenValidator
{
	static Logger logger = Logger.getLogger(SAMLTokenValidator.class);

	public void validateURL(String s) throws MalformedURLException
	{
		URL url = new URL(s);
		String query = url.getQuery();
		System.out.println("Query String=" + query);
	}

	public String getURNVal(String s) throws MalformedURLException,
			URISyntaxException, Exception
	{
		try
		{
			URI uri = new URI(s);
			if (!s.startsWith("urn"))
			{
				logger.error("String suppled is not in urn format");
				throw new MalformedURLException();
			}
			if ((uri.isOpaque() && (uri.isAbsolute())))
			{
				String[] fragments = s.split(":");
				return fragments[fragments.length - 1];
			}
			return null;
		}
		catch (Exception e)
		{
			throw new Exception(e);
		}
	}

	public boolean validateTime(Assertion assertion)
	{
		boolean validateTime = false;
		Conditions condition = assertion.getConditions();
		DateTime before = condition.getNotBefore();
		DateTime after = condition.getNotOnOrAfter();
		DateTime current = new DateTime();
		validateTime = (current.isAfter(before)) && (current.isBefore(after));
		logger.debug("Assertion status after time evaluation passed(true/false)="
				+ validateTime);
		return validateTime;
	}

	public String getAssertionUriRef(Assertion assertion)
			throws MalformedURLException
	{
		List<AssertionURIRef> assertionList = assertion.getAdvice()
				.getAssertionURIReferences();
		Iterator<AssertionURIRef> iter = assertionList.iterator();
		while (iter.hasNext())
		{
			AssertionURIRef assertionIdRef = iter.next();
			if (assertionIdRef != null)
			{
				String assertionURL = assertionIdRef.getAssertionURI();
				return this.getAssertionId(assertionURL);
			}
		}
		return null;
	}

	public String getAssertionIdentifier(Assertion assertion)
	{
		return assertion.getID();
	}

	public String getNameID(Assertion assertion) throws MalformedURLException,
			URISyntaxException, Exception
	{
		try
		{
			String nameid = assertion.getSubject().getNameID().getValue(); 
			logger.info("NameID derived from assertion=" + nameid);
			return nameid;
			/*nameid = this.getURNVal(nameid);
			System.out
					.println("NameID derived from assertion after extracting from URN format= "
							+ nameid);
			return nameid;*/
		}
		catch (Exception e)
		{
			throw new Exception(e);
		}
	}
	
	public String getAccountID(Assertion assertion)
			throws MalformedURLException, URISyntaxException, Exception
	{
		try
		{			
			List<AttributeStatement> lAttrStmt = assertion
					.getAttributeStatements();
			Iterator<AttributeStatement> iter = lAttrStmt.iterator();
			while (iter.hasNext())
			{
				AttributeStatement astmt = iter.next();

				List<Attribute> listAttributes = astmt.getAttributes();
				Iterator<Attribute> attriuteIterator = listAttributes
						.iterator();

				while (attriuteIterator.hasNext())
				{
					Attribute attribute = attriuteIterator.next();
					String attrName = attribute.getName();
					System.out.println("Attribute Name=" + attrName);
					if (attrName.equalsIgnoreCase("accountID"))
					{
						List<XMLObject> listXML = attribute
								.getAttributeValues();
						Iterator<XMLObject> xmlObjectIter = listXML.iterator();
						while (xmlObjectIter.hasNext())
						{
							XMLObject xmlobject = xmlObjectIter.next();
							String strAttributeValue = (xmlobject).getDOM().getTextContent(); 							
							System.out.println("AccountID derived="
									+ strAttributeValue);
							strAttributeValue = this
									.getURNVal(strAttributeValue);
							logger.info("AccountID after extracting from URN="
											+ strAttributeValue);
							return strAttributeValue;
							/*
							 * System.out.println("XML Object name=" +
							 * xmlobject.);
							 */
						}
					}
				}
			}
			return null;
		}
		catch (Exception e)
		{
			throw new Exception(e);
		}
	}

	private String getAssertionId(String urlString)
			throws MalformedURLException
	{
		URL url = new URL(urlString);
		String path = url.getPath();
		String[] entities = path.split("/");
		return entities[entities.length - 1];
	}

	public String getHostString(String urlString) throws MalformedURLException,
			URISyntaxException
	{
		URI uri = new URI(urlString);
		String retString = urlString;
		if (!uri.isOpaque())
		{
			URL url = new URL(urlString);
			retString = url.getHost();
		}
		return retString;
	}

	public String getConetextValue(String urlS, String precond, int val)
			throws MalformedURLException
	{
		URL url = new URL(urlS);
		String urlPath = url.getPath();
		logger.debug("Path=" + urlPath);
		String[] vals = urlPath.split("/");
		logger.debug("Value=" + vals[val] + " index=" + val);
		if (vals[val - 1].equalsIgnoreCase(precond))
		{
			return vals[val];
		}
		return null;
	}

	public boolean doesDoubleQuoteProcessingRequired(String s, int position)
	{
		if (s.trim().charAt(position) == '\"')
		{
			return true;
		}
		return false;
	}

	public boolean isProperlyDoubleQuoted(String s)
	{
		boolean doubleQuoteExists = (this.doesDoubleQuoteProcessingRequired(s,
				0) != this.doesDoubleQuoteProcessingRequired(s, s.length() - 1));
		return doubleQuoteExists;
	}

	public String removeDoubleQuotes(String s)
	{
		boolean doubleQuoteExists = (this.doesDoubleQuoteProcessingRequired(s,
				0) == this.doesDoubleQuoteProcessingRequired(s, s.length() - 1));
		if ((doubleQuoteExists) && this.doesDoubleQuoteProcessingRequired(s, 0))
		{
			return s.substring(1, (s.length() - 1));
		}
		return s;
	}

	public boolean isAnyURI(String s)
	{
		String sv = s.trim();

		if ((sv.length() == 0))
		{
			return true;
		}
		try
		{
			new URI(sv);
			return true;
		}
		catch (URISyntaxException e)
		{
			return false;
		}
	}
	
	public String getToken(Response resp) throws MalformedURLException,
			URISyntaxException, Exception
	{
		try
		{
			Extensions extn = resp.getExtensions();
			if (extn != null)
			{
				List lAttr = extn.getUnknownXMLObjects();
				Attribute attr = (Attribute) lAttr.get(0);
				List lAttrValues = attr.getAttributeValues();
				XSStringImpl value1 = (XSStringImpl) lAttrValues.get(0);
				System.out.println(((XSStringImpl) lAttrValues.get(0))
						.getValue());
				String token = this.getURNVal(((XSStringImpl) lAttrValues
						.get(0)).getValue());
				return token;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new Exception(e);
		}

		return null;
	}
}
