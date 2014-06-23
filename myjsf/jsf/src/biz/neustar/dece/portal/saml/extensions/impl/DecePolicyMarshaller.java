package biz.neustar.dece.portal.saml.extensions.impl;

import org.opensaml.common.impl.AbstractSAMLObjectMarshaller;
import org.opensaml.xml.XMLObject;
import org.opensaml.xml.io.MarshallingException;
import org.w3c.dom.Element;

import biz.neustar.dece.portal.saml.extensions.DecePolicy;
import biz.neustar.dece.portal.saml.extensions.DecePolicyList;

public class DecePolicyMarshaller extends AbstractSAMLObjectMarshaller
{
	protected void marshallAttributes(XMLObject samlObject, Element domElement)
			throws MarshallingException
	{
		System.out.println("DecePolicy:: atribute");
		DecePolicy decePolicy = (DecePolicy) samlObject;
		if (decePolicy.getPolicyId() != null)
		{
			domElement.setAttributeNS(null,
					DecePolicyList.POLICY_LIST_ID_ATTRIB_NAME,
					decePolicy.getPolicyId());
		}
	}
}
