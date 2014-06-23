package biz.neustar.dece.portal.saml.extensions.impl;

import org.opensaml.common.impl.AbstractSAMLObjectMarshaller;
import org.opensaml.xml.XMLObject;
import org.opensaml.xml.io.MarshallingException;
import org.w3c.dom.Element;

import biz.neustar.dece.portal.saml.extensions.DecePolicyList;


public class DecePolicyListMarshaller extends AbstractSAMLObjectMarshaller
{
	protected void marshallAttributes(XMLObject samlObject, Element domElement)
			throws MarshallingException
	{
		System.out.println("DecePolicyListMarshaller:: atribute");
		DecePolicyList decePolicyList = (DecePolicyList) samlObject;
		if (decePolicyList.getPolicyListId() != null)
		{
			domElement.setAttributeNS(null,
					DecePolicyList.POLICY_LIST_ID_ATTRIB_NAME,
					decePolicyList.getPolicyListId());
		}
	}
}
