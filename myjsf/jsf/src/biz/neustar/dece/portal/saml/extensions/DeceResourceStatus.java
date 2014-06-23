/**
 * 
 */
package biz.neustar.dece.portal.saml.extensions;

import javax.xml.namespace.QName;

import org.opensaml.common.SAMLObject;
import org.opensaml.common.xml.SAMLConstants;

/**
 * @author sjha
 * 
 */
public interface DeceResourceStatus extends SAMLObject
{
	public static final String DEFAULT_ELEMENT_LOCAL_NAME = "ResourceStatus";

	/** Default element name. */
	public static final QName DEFAULT_ELEMENT_NAME = new QName(
			DecePolicyConstants.DECE_NS, DEFAULT_ELEMENT_LOCAL_NAME,
			DecePolicyConstants.DECE_PREFIX);

	/** Local name of the XSI type. */
	public static final String TYPE_LOCAL_NAME = "ElementStatus-type";

	/** QName of the XSI type. */
	public static final QName TYPE_NAME = new QName(SAMLConstants.SAML20_NS,
			TYPE_LOCAL_NAME, SAMLConstants.SAML20_PREFIX);

	/** Current attribute name. */
	public static final String CURRENT_ATTRIB_NAME = "Current";
	/** Resource attribute name. */
	public static final String HISTORY_ATTRIB_NAME = "History";

	public DeceCurrentStatus getCurrent();

	public void setCurrent(DeceCurrentStatus current);

	public DeceStatusHistory getHistory();

	public void setHistory(DeceStatusHistory history);
}
