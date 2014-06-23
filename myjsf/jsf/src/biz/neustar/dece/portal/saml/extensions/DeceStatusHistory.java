package biz.neustar.dece.portal.saml.extensions;

import java.util.List;

import javax.xml.namespace.QName;

import org.opensaml.common.SAMLObject;

public interface DeceStatusHistory extends SAMLObject
{
	/** Element local name. */
	public static final String DEFAULT_ELEMENT_LOCAL_NAME = "History";

	/** Default element name. */
	public static final QName DEFAULT_ELEMENT_NAME = new QName(
			DecePolicyConstants.DECE_NS, DEFAULT_ELEMENT_LOCAL_NAME,
			DecePolicyConstants.DECE_PREFIX);

	/** Local name of the XSI type. */
	public static final String TYPE_LOCAL_NAME = "StatusHistory-type";

	/** QName of the XSI type. */
	public static final QName TYPE_NAME = new QName(
			DecePolicyConstants.DECE_NS, TYPE_LOCAL_NAME,
			DecePolicyConstants.DECE_PREFIX);

	public List<DecePriorStatusType> getPriors();

	void addPriors(DecePriorStatusType decePriorStatusType);
}
