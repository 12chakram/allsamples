package biz.neustar.dece.portal.saml.extensions.impl;

import org.opensaml.common.impl.AbstractSAMLObjectBuilder;

import biz.neustar.dece.portal.saml.extensions.DecePolicyConstants;
import biz.neustar.dece.portal.saml.extensions.DecePolicyList;

public class DecePolicyListBuilder extends
		AbstractSAMLObjectBuilder<DecePolicyList>
{
	public DecePolicyListBuilder()
	{

	}

	@Override
	public DecePolicyList buildObject()
	{
		return buildObject(DecePolicyConstants.DECE_NS,
				DecePolicyList.DEFAULT_ELEMENT_LOCAL_NAME,
				DecePolicyConstants.DECE_PREFIX);
	}

	@Override
	public DecePolicyList buildObject(String namespaceURI, String localName,
			String namespacePrefix)
	{
		return new DecePolicyListImpl(namespaceURI, localName, namespacePrefix);
	}
}
