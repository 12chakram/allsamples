package biz.neustar.dece.portal.saml.extensions.impl;

import org.opensaml.common.impl.AbstractSAMLObjectUnmarshaller;
import org.opensaml.xml.XMLObject;
import org.opensaml.xml.io.UnmarshallingException;
import org.opensaml.xml.schema.XSAny;
import org.opensaml.xml.util.DatatypeHelper;
import org.w3c.dom.Attr;

import biz.neustar.dece.portal.saml.extensions.DecePolicy;
import biz.neustar.dece.portal.saml.extensions.DecePolicyList;

public class DecePolicyListUnMarshaller extends AbstractSAMLObjectUnmarshaller
{
	protected void processAttribute(XMLObject samlObject, Attr attribute)
			throws UnmarshallingException
	{
		System.out.println("Inside DecePolicyListUnMarshaller");
		DecePolicyList decePolicyList = (DecePolicyList) samlObject;

		if (attribute.getLocalName().equals(
				DecePolicyList.POLICY_LIST_ID_ATTRIB_NAME)
				&& !DatatypeHelper.isEmpty(attribute.getValue()))
		{
			decePolicyList.setPolicyListId(attribute.getValue());
		}
		else
		{
			super.processAttribute(samlObject, attribute);
		}
	}

	@Override
	protected void processElementContent(XMLObject samlObject,
			String elementContent)
	{
		System.out.println("Inside DecePolicyListUnMarshaller");
		DecePolicyList decePolicyList = (DecePolicyList) samlObject;
	}

	protected void processChildElement(
			org.opensaml.xml.XMLObject parentSAMLObject,
			org.opensaml.xml.XMLObject childSAMLObject)
	{
		// System.out.println("Inside DeceResourceStatusUnMarshaller,parent class name="+parentSAMLObject.getClass().getCanonicalName());
		// System.out.println("Inside DeceResourceStatusUnMarshaller,child class name="+childSAMLObject.getClass().getCanonicalName());

		if ((childSAMLObject != null) && (childSAMLObject instanceof XSAny))
		{
			// parentSAMLObject.
			String s = ((XSAny) childSAMLObject).getElementQName()
					.getLocalPart();
			String content = ((XSAny) childSAMLObject).getTextContent();
			if (s.equalsIgnoreCase(DecePolicyList.POLICY_LIST_ID_ATTRIB_NAME))
			{
				((DecePolicyList) parentSAMLObject).setPolicyListId(content); // setCurrent(content);

			}
		}

		if (parentSAMLObject != null)
		{
			DecePolicyList decePolicyList = (DecePolicyList) parentSAMLObject;
			if (childSAMLObject != null)
			{
				DecePolicy decePolicy = (DecePolicy) childSAMLObject;
				decePolicyList.getDecePolicyList().add(decePolicy);
			}
		}
		try
		{
			super.processChildElement(parentSAMLObject, childSAMLObject);
		}
		catch (UnmarshallingException e)
		{
			e.printStackTrace();
		}
	}

	public DecePolicyListUnMarshaller()
	{
		super();
	}
}
