/**
 * 
 */
package biz.neustar.dece.portal.saml.extensions;

import javax.xml.namespace.QName;

import org.joda.time.DateTime;
import org.opensaml.common.SAMLObject;

/**
 * @author sjha
 * 
 */
public interface DecePriorStatusType extends SAMLObject
{
	/** Element local name. */
	public static final String DEFAULT_ELEMENT_LOCAL_NAME = "Prior";

	/** Default element name. */
	public static final QName DEFAULT_ELEMENT_NAME = new QName(
			DecePolicyConstants.DECE_NS, DEFAULT_ELEMENT_LOCAL_NAME,
			DecePolicyConstants.DECE_PREFIX);

	/** Local name of the XSI type. */
	public static final String TYPE_LOCAL_NAME = "PriorStatus-type";

	/** QName of the XSI type. */
	public static final QName TYPE_NAME = new QName(
			DecePolicyConstants.DECE_NS, TYPE_LOCAL_NAME,
			DecePolicyConstants.DECE_PREFIX);

	/** Value attribute name. */
	public static final String VALUE_ATTRIB_NAME = "Value";
	/** Description attribute name. */
	public static final String DESCRIPTION_ATTRIB_NAME = "Description";
	/** ModificationDate attribute name. */
	public static final String MODIFICATION_DATE_ENTITY_ATTRIB_NAME = "ModificationDate";
	/** ModifiedBy attribute name. */
	public static final String MODIFIED_BY_ATTRIB_NAME = "ModifiedBy";

	public String getValue();

	public void setValue(String value);

	public String getDescription();

	public void setDescription(String value);

	public DateTime getModificationDate();

	public void setModificationDate(DateTime modificationDate);

	public String getModifiedBy();

	public void setModifiedBy(String modifiedBy);
}
