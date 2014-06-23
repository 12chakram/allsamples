package biz.neustar.dece.portal.saml.extensions.impl;

import org.opensaml.common.impl.AbstractSAMLObjectBuilder;

import biz.neustar.dece.portal.saml.extensions.DecePolicy;
import biz.neustar.dece.portal.saml.extensions.DecePolicyConstants;

public class DecePolicyBuilder extends AbstractSAMLObjectBuilder<DecePolicy>
{
	public DecePolicyBuilder()
	{

	}

	@Override
	public DecePolicy buildObject()
	{
		return buildObject(DecePolicyConstants.DECE_NS,
				DecePolicy.DEFAULT_ELEMENT_LOCAL_NAME,
				DecePolicyConstants.DECE_PREFIX);
	}

	@Override
	public DecePolicy buildObject(String namespaceURI, String localName,
			String namespacePrefix)
	{
		return new DecePolicyImpl(namespaceURI, localName, namespacePrefix);
	}
}
