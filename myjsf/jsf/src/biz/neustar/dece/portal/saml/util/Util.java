package biz.neustar.dece.portal.saml.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.opensaml.common.SAMLObjectBuilder;
import org.opensaml.saml2.common.Extensions;
import org.opensaml.saml2.common.impl.ExtensionsBuilder;
import org.opensaml.saml2.core.Audience;
import org.opensaml.saml2.core.AudienceRestriction;
import org.opensaml.saml2.core.Conditions;
import org.opensaml.saml2.core.impl.ConditionsBuilder;
import org.opensaml.xml.XMLObjectBuilderFactory;

import biz.neustar.dece.portal.saml.auth.NameSpace;
import biz.neustar.dece.portal.saml.auth.SAMLRequestParams;
import biz.neustar.dece.portal.saml.extensions.DecePolicy;
import biz.neustar.dece.portal.saml.extensions.DecePolicyList;
import biz.neustar.dece.portal.saml.extensions.DeceResourceStatus;
import biz.neustar.dece.portal.saml.extensions.impl.DecePolicyBuilder;
import biz.neustar.dece.portal.saml.extensions.impl.DecePolicyListBuilder;
import biz.neustar.dece.portal.saml.extensions.impl.DeceResourceStatusBuilder;

public class Util
{
	private Set convertFromListToSet(List<String> list)
	{
		return new HashSet(list);
	}

	public static Extensions buildSamlExtensions(
			SAMLRequestParams samlRequestParams)
	{
		DecePolicyList decePolicyList = new DecePolicyListBuilder()
				.buildObject();
		String policy = samlRequestParams.getPolicies();
		if (policy != null)
		{
			DecePolicy decePolicy = null;
			// User user = new
			// DeceDAO().getUser(samlRequestParams.getUserName(),
			// samlRequestParams.getIssuer());
			/*for (String policy : policies)
			{*/
				decePolicy = new DecePolicyBuilder().buildObject();

				DeceResourceStatus deceResourceStatus = (new DeceResourceStatusBuilder())
						.buildObject();
				decePolicy.setResourceStatus(deceResourceStatus);
				//decePolicy.setPolicyClass(policy);
				/*
				 * if ( policy.contains("ManageAccountConsent") ) {
				 * decePolicy.addResource(DeceNameSpace.ACCOUNT_ID +
				 * user.getNodeAccountId()); } else {
				 * decePolicy.addResource(DeceNameSpace.USER_ID +
				 * user.getNodeUserId()); }
				 */
				decePolicy.addResource(NameSpace.USER_ID + "10");
				decePolicy.setPolicyAuthority("urn:dece:role:coordinator");
				if (policy.startsWith("org:"))
				{
					System.out.println("addRequestingEntity="
							+ NameSpace.ORG_ID + "04");
					System.out.println("policyClass="
							+ policy.substring(policy.indexOf("org:") + 4));
					decePolicy.setPolicyClass(policy.substring(policy
							.indexOf("org:") + 4));
					decePolicy.addRequestingEntity(NameSpace.ORG_ID + "04");
				}
				else
				{
					decePolicy.setPolicyClass(policy);
					decePolicy.addRequestingEntity(samlRequestParams
							.getIssuer());
				}
				decePolicyList.getDecePolicyList().add(decePolicy);
			//}
		}

/*		// Add Child Policies
		String[] childPolicies = samlRequestParams.getChildPolicies();
		if (childPolicies != null && childPolicies.length > 0)
		{
			DecePolicy decePolicy = null;
			// User childUser = new
			// DeceDAO().getUser(samlRequestParams.getChildUserName(),
			// samlRequestParams.getIssuer());
			for (String policy : childPolicies)
			{
				decePolicy = new DecePolicyBuilder().buildObject();

				DeceResourceStatus deceResourceStatus = (new DeceResourceStatusBuilder())
						.buildObject();
				decePolicy.setResourceStatus(deceResourceStatus);
				// decePolicy.setPolicyClass(policy);
				
				 * if ( policy.contains("ManageAccountConsent") ) {
				 * decePolicy.addResource(DeceNameSpace.ACCOUNT_ID +
				 * childUser.getNodeAccountId()); } else {
				 * decePolicy.addResource(DeceNameSpace.USER_ID +
				 * childUser.getNodeUserId()); }
				 
				decePolicy.addResource(NameSpace.USER_ID + "test1234");
				decePolicy.setPolicyAuthority("urn:dece:role:coordinator");
				decePolicy.setPolicyClass(policy);
				decePolicy.addRequestingEntity(samlRequestParams.getIssuer());

				decePolicyList.getDecePolicyList().add(decePolicy);
			}
		}*/
		
		Extensions extensions = null;
		if (decePolicyList != null
				&& decePolicyList.getDecePolicyList() != null
				&& decePolicyList.getDecePolicyList().size() > 0)
		{
			extensions = new ExtensionsBuilder().buildObject();
			extensions.getUnknownXMLObjects().add(decePolicyList);
		}
		return extensions;
	}

	public static Conditions buildSamlConditions(String[] affilliatedMembers)
	{
		XMLObjectBuilderFactory builderFactory = org.opensaml.xml.Configuration
				.getBuilderFactory();
		ConditionsBuilder conditionsBuilder = (ConditionsBuilder) builderFactory
				.getBuilder(Conditions.DEFAULT_ELEMENT_NAME);
		Conditions conditions = conditionsBuilder.buildObject();

		SAMLObjectBuilder<AudienceRestriction> audienceRestrictionBuilder = (SAMLObjectBuilder<AudienceRestriction>) builderFactory
				.getBuilder(AudienceRestriction.DEFAULT_ELEMENT_NAME);
		SAMLObjectBuilder<Audience> audienceBuilder = (SAMLObjectBuilder<Audience>) builderFactory
				.getBuilder(Audience.DEFAULT_ELEMENT_NAME);
		AudienceRestriction audienceRestriction = audienceRestrictionBuilder
				.buildObject();
		Audience audience = null;
		if (affilliatedMembers != null)
		{
			for (String affilliatedMember : affilliatedMembers)
			{
				audience = audienceBuilder.buildObject();
				audience.setAudienceURI(affilliatedMember);
				audienceRestriction.getAudiences().add(audience);
			}
		}
		conditions.getAudienceRestrictions().add(audienceRestriction);
		return conditions;
	}

	public static String convertStreamToString(InputStream is, boolean good)
			throws IOException
	{
		/*
		 * To convert the InputStream to String we use the
		 * BufferedReader.readLine() method. We iterate until the BufferedReader
		 * return null which means there's no more data to read. Each line will
		 * appended to a StringBuilder and returned as String.
		 */
		if (is != null)
		{
			StringBuilder sb = new StringBuilder();

			String line;

			// try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "UTF-8"));
			while ((line = reader.readLine()) != null)
			{
				sb.append(line).append("\n");
			}
			// }
			// is.reset();
			return sb.toString();
		}
		else
		{
			return "";
		}
	}
}
