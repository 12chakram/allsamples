package biz.neustar.dece.portal.saml.extensions.utils;

import java.util.Iterator;
import java.util.List;

import javax.xml.namespace.QName;

import org.opensaml.Configuration;
import org.opensaml.xml.XMLObjectBuilder;
import org.opensaml.xml.XMLObjectBuilderFactory;
import org.opensaml.xml.schema.XSAny;
import org.opensaml.xml.schema.XSString;
import org.opensaml.xml.util.XMLHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import biz.neustar.dece.portal.saml.extensions.DecePolicy;
import biz.neustar.dece.portal.saml.extensions.DecePolicyConstants;
import biz.neustar.dece.portal.saml.extensions.DeceResourceStatus;

public class Util
{
	public Document getDocument(Element elmt)
	{
		return elmt.getOwnerDocument();

	}

	public void buildHierarchy(Element domElement, String data, QName qname)
	{
		Element elmt = XMLHelper.constructElement(this.getDocument(domElement),
				qname);
		XMLHelper.appendTextContent(elmt, data);
		XMLHelper.appendChildElement(domElement, elmt);
	}

	public void buildHierarchy(Element domElement, QName qname)
	{
		Element elmt = XMLHelper.constructElement(this.getDocument(domElement),
				qname);
		// XMLHelper.appendTextContent(elmt, data);
		XMLHelper.appendChildElement(domElement, elmt);
	}

	public void buildHierarchy(Element domElement, List<String> datas,
			QName qname)
	{
		for (String data : datas)
		{
			Element elmt = XMLHelper.constructElement(
					this.getDocument(domElement), qname);
			XMLHelper.appendTextContent(elmt, data);
			XMLHelper.appendChildElement(domElement, elmt);
		}
	}

	public void printDecePolicyListAttributes(List<XSAny> xsAnyList)
	{
		Iterator<XSAny> iter = xsAnyList.iterator();
		while (iter.hasNext())
		{
			System.out.println("Value=" + iter.next().getTextContent());
		}
	}

	public void printDecePolicy(DecePolicy decePolicy)
	{
		if (decePolicy != null)
		{
			System.out.println(DecePolicy.POLICY_CLASS_ATTRIB_NAME + "="
					+ decePolicy.getPolicyClass().getTextContent());
			System.out.println(DecePolicy.POLICY_AUTHORITY_ATTRIB_NAME + "="
					+ decePolicy.getPolicyAuthority().getTextContent());
			System.out.println(DecePolicy.REQUESTING_ENTITY_ATTRIB_NAME + "=");
			this.printDecePolicyListAttributes(decePolicy
					.getRequestingEntitities());
			System.out.println(DecePolicy.RESOURCE_ATTRIB_NAME + "=");
			this.printDecePolicyListAttributes(decePolicy.getResources());
			if (decePolicy.getResourceStatus() != null)
			{
				System.out.println(DeceResourceStatus.CURRENT_ATTRIB_NAME + "="
						+ decePolicy.getResourceStatus().getCurrent());
				System.out.println(DeceResourceStatus.HISTORY_ATTRIB_NAME + "="
						+ decePolicy.getResourceStatus().getHistory());
			}
		}
		else
		{
			System.out.println("DecePolicy was null");
		}
	}

	public XSAny buildXSAnyObject(String namespaceURI, String localName,
			String namespacePrefix)
	{
		XMLObjectBuilderFactory bf = Configuration.getBuilderFactory();

		XMLObjectBuilder<XSAny> xsAnyBuilder = bf.getBuilder(XSAny.TYPE_NAME);

		XSAny entity = xsAnyBuilder.buildObject(namespaceURI, localName,
				namespacePrefix);
		return entity;
	}

	public XSAny buildXSAnyObject(String localName)
	{
		XMLObjectBuilderFactory bf = Configuration.getBuilderFactory();

		XMLObjectBuilder<XSAny> xsAnyBuilder = bf.getBuilder(XSAny.TYPE_NAME);

		XSAny entity = xsAnyBuilder.buildObject(DecePolicyConstants.DECE_NS,
				localName, DecePolicyConstants.DECE_PREFIX);
		return entity;
	}

	public XSString buildXSStringObject(String namespaceURI, String localName,
			String namespacePrefix)
	{
		XMLObjectBuilderFactory bf = Configuration.getBuilderFactory();

		XMLObjectBuilder<XSString> xsAnyBuilder = bf
				.getBuilder(XSString.TYPE_NAME);

		XSString entity = xsAnyBuilder.buildObject(namespaceURI, localName,
				namespacePrefix);
		return entity;
	}

	// return buildObject(DecePolicyConstants.DECE_NS,
	// DecePolicy.DEFAULT_ELEMENT_LOCAL_NAME,
	// DecePolicyConstants.DECE_PREFIX);
	public XSString buildXSStringObject(String localName)
	{
		XMLObjectBuilderFactory bf = Configuration.getBuilderFactory();

		XMLObjectBuilder<XSString> xsAnyBuilder = bf
				.getBuilder(XSString.TYPE_NAME);

		XSString entity = xsAnyBuilder.buildObject(DecePolicyConstants.DECE_NS,
				localName, DecePolicyConstants.DECE_PREFIX);
		return entity;
	}
}
