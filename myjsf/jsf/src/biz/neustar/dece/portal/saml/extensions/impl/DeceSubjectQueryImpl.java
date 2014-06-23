package biz.neustar.dece.portal.saml.extensions.impl;

import org.opensaml.common.xml.SAMLConstants;
import org.opensaml.saml2.core.SubjectQuery;
import org.opensaml.saml2.core.impl.SubjectQueryImpl;

public class DeceSubjectQueryImpl extends SubjectQueryImpl
{
	protected DeceSubjectQueryImpl(String namespaceURI,
			String elementLocalName, String namespacePrefix)
	{
		super(namespaceURI, elementLocalName, namespacePrefix);
	}

	public DeceSubjectQueryImpl()
	{
		this(SAMLConstants.SAML20P_NS, SubjectQuery.DEFAULT_ELEMENT_LOCAL_NAME,
				SAMLConstants.SAML20P_PREFIX);
	}
}
