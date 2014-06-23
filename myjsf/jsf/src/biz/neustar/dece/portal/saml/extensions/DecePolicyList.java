package biz.neustar.dece.portal.saml.extensions;

import java.util.List;

import javax.xml.namespace.QName;

import org.opensaml.common.SAMLObject;

public interface DecePolicyList extends SAMLObject
{
	/** Element local name. */
	public static final String DEFAULT_ELEMENT_LOCAL_NAME = "PolicyList";

	/** Default element name. */
	public static final QName DEFAULT_ELEMENT_NAME = new QName(
			DecePolicyConstants.DECE_NS, DEFAULT_ELEMENT_LOCAL_NAME,
			DecePolicyConstants.DECE_PREFIX);

	/** Local name of the XSI type. */
	public static final String TYPE_LOCAL_NAME = "PoliciesAbstract-type";

	/** QName of the XSI type. */
	public static final QName TYPE_NAME = new QName(
			DecePolicyConstants.DECE_NS, TYPE_LOCAL_NAME,
			DecePolicyConstants.DECE_PREFIX);

	/** PolicyListIDattribute name. */
	public static final String POLICY_LIST_ID_ATTRIB_NAME = "PolicyListID";
	/** Policy attribute name. */
	public static final String _ATTRIB_NAME = "Policy";

	/** RequestingEntity attribute name. */

	public String getPolicyListId();

	public void setPolicyListId(String PolicyListId);

	public List<DecePolicy> getDecePolicyList();
	
}
