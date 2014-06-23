package biz.neustar.dece.portal.saml.extensions.impl;

import org.opensaml.common.impl.AbstractSAMLObjectUnmarshaller;
import org.opensaml.xml.io.UnmarshallingException;
import org.opensaml.xml.schema.XSAny;

import biz.neustar.dece.portal.saml.extensions.DecePolicy;

public class DecePolicyUnMarshaller extends AbstractSAMLObjectUnmarshaller
{
	public DecePolicyUnMarshaller()
	{
		super();
	}
	 
	protected void processChildElement(
			org.opensaml.xml.XMLObject parentSAMLObject,
			org.opensaml.xml.XMLObject childSAMLObject)
			throws UnmarshallingException
	{
		// System.out.println("Inside DecePolicyUnMarshaller,parent class name="+parentSAMLObject.getClass().getCanonicalName());
		// System.out.println("Inside DecePolicyUnMarshaller,child class name="+childSAMLObject.getClass().getCanonicalName());

		if ((childSAMLObject != null) && (childSAMLObject instanceof XSAny))
		{
			// parentSAMLObject.
			String s = ((XSAny) childSAMLObject).getElementQName()
					.getLocalPart();
			String content = ((XSAny) childSAMLObject).getTextContent();

			if (s.equalsIgnoreCase(DecePolicy.POLICY_AUTHORITY_ATTRIB_NAME))
			{
				((DecePolicy) parentSAMLObject).setPolicyAuthority(content);
			}
			if (s.equalsIgnoreCase(DecePolicy.POLICY_CLASS_ATTRIB_NAME))
			{
				((DecePolicy) parentSAMLObject).setPolicyClass(content);
			}
			if (s.equalsIgnoreCase(DecePolicy.REQUESTING_ENTITY_ATTRIB_NAME))
			{
				((DecePolicy) parentSAMLObject).addRequestingEntity(content);// setRequestingEntity(content);
			}
			if (s.equalsIgnoreCase(DecePolicy.RESOURCE_ATTRIB_NAME))
			{
				((DecePolicy) parentSAMLObject).addResource(content);
			}
		}
	}
}
