package biz.neustar.dece.portal.saml.extensions.impl;

import org.opensaml.common.impl.AbstractSAMLObjectBuilder;
import org.opensaml.common.xml.SAMLConstants;
import org.opensaml.saml2.core.SubjectQuery;

public class DeceSubjectQueryBuilder extends
		AbstractSAMLObjectBuilder<SubjectQuery>
{
	@Override
	public SubjectQuery buildObject()
	{
		return buildObject(SAMLConstants.SAML20P_NS,
				SubjectQuery.DEFAULT_ELEMENT_LOCAL_NAME,
				SAMLConstants.SAML20P_PREFIX);
	}

	@Override
	public SubjectQuery buildObject(String namespaceURI, String localName,
			String namespacePrefix)
	{
		return new DeceSubjectQueryImpl(namespaceURI, localName,
				namespacePrefix);
	}
}
