package biz.neustar.dece.portal.saml.extensions.impl;

import org.opensaml.common.impl.AbstractSAMLObjectBuilder;

import biz.neustar.dece.portal.saml.extensions.DecePolicyConstants;
import biz.neustar.dece.portal.saml.extensions.DeceResourceStatus;

public class DeceResourceStatusBuilder extends
		AbstractSAMLObjectBuilder<DeceResourceStatus>
{
	public DeceResourceStatusBuilder()
	{

	}

	@Override
	public DeceResourceStatus buildObject()
	{
		return buildObject(DecePolicyConstants.DECE_NS,
				DeceResourceStatus.DEFAULT_ELEMENT_LOCAL_NAME,
				DecePolicyConstants.DECE_PREFIX);
	}

	@Override
	public DeceResourceStatus buildObject(String namespaceURI,
			String localName, String namespacePrefix)
	{
		return new DeceResourceStatusImpl(namespaceURI, localName,
				namespacePrefix);
	}
}
