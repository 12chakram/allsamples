package biz.neustar.dece.portal.saml.extensions.impl;

import org.opensaml.common.impl.AbstractSAMLObjectBuilder;

import biz.neustar.dece.portal.saml.extensions.DeceCurrentStatus;
import biz.neustar.dece.portal.saml.extensions.DecePolicy;
import biz.neustar.dece.portal.saml.extensions.DecePolicyConstants;

public class DeceCurrentStatusBuilder extends
		AbstractSAMLObjectBuilder<DeceCurrentStatus>
{
	@Override
	public DeceCurrentStatus buildObject()
	{
		return buildObject(DecePolicyConstants.DECE_NS,
				DeceCurrentStatus.DEFAULT_ELEMENT_LOCAL_NAME,
				DecePolicyConstants.DECE_PREFIX);
	}

	@Override
	public DeceCurrentStatus buildObject(String namespaceURI, String localName,
			String namespacePrefix)
	{
		// TODO Auto-generated method stub
		return new DeceCurrentStatusImpl(namespaceURI, localName,
				namespacePrefix);
	}
}
