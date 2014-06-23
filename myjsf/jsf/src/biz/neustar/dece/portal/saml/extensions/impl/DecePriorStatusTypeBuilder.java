package biz.neustar.dece.portal.saml.extensions.impl;

import org.opensaml.common.impl.AbstractSAMLObjectBuilder;

import biz.neustar.dece.portal.saml.extensions.DecePolicyConstants;
import biz.neustar.dece.portal.saml.extensions.DecePriorStatusType;

public class DecePriorStatusTypeBuilder extends
		AbstractSAMLObjectBuilder<DecePriorStatusType>
{
	@Override
	public DecePriorStatusType buildObject()
	{
		return buildObject(DecePolicyConstants.DECE_NS,
				DecePriorStatusType.DEFAULT_ELEMENT_LOCAL_NAME,
				DecePolicyConstants.DECE_PREFIX);
	}

	@Override
	public DecePriorStatusType buildObject(String namespaceURI,
			String localName, String namespacePrefix)
	{
		return new DecePriorStatusTypeImpl(namespaceURI, localName,
				namespacePrefix);
	}
}
