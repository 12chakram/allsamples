package biz.neustar.dece.portal.saml.extensions;

import java.util.List;

import javax.xml.namespace.QName;

import org.opensaml.common.SAMLObject;
import org.joda.time.DateTime;

public interface DeceCurrentStatus extends SAMLObject
{
	/** Element local name. */
	public static final String DEFAULT_ELEMENT_LOCAL_NAME = "Current";

	/** Default element name. */
	public static final QName DEFAULT_ELEMENT_NAME = new QName(
			DecePolicyConstants.DECE_NS, DEFAULT_ELEMENT_LOCAL_NAME,
			DecePolicyConstants.DECE_PREFIX);

	/** Local name of the XSI type. */
	public static final String TYPE_LOCAL_NAME = "ElementStatus-type";

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

	/** CreationDate attribute name. */
	public static final String CREATION_DATE_ATTRIB_NAME = "CreationDate";
	/** DeletionDate attribute name. */
	public static final String DELETION_DATE_ATTRIB_NAME = "DeletionDate";
	/** DeletedBy attribute name. */
	public static final String DELETED_BY_ATTRIB_NAME = "DeletedBy";
	/** CreatedBy attribute name. */
	public static final String CREATEDBY_ATTRIB_NAME = "CreatedBy";

	public String getValue();

	public void setValue(String value);

	public String getDescription();

	public void setDescription(String value);

	public DateTime getModificationDate();

	public void setModificationDate(DateTime modificationDate);

	public String getModifiedBy();

	public void setModifiedBy(String modifiedBy);

	public DateTime getCreationDate();

	public void setCreationDate(DateTime creationDate);

	public DateTime getDeletionDate();

	public void setDeletionDate(DateTime deletionDat);

	public String getDeletedBy();

	public void setDeletedBy(String deletedBy);

	public String getCreatedBy();

	public void setCreatedBy(String createdBy);
}
