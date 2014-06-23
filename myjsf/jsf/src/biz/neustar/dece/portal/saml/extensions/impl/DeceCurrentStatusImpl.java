package biz.neustar.dece.portal.saml.extensions.impl;

import java.util.List;

import org.joda.time.DateTime;
import org.opensaml.common.impl.AbstractSAMLObject;
import org.opensaml.xml.XMLObject;

import biz.neustar.dece.portal.saml.extensions.DeceCurrentStatus;

public class DeceCurrentStatusImpl extends AbstractSAMLObject implements
		DeceCurrentStatus
{
	protected String value;

	protected String description;

	protected DateTime modificationDate;

	protected String modifiedBy;

	protected DateTime creationDate;

	protected String createdBy;

	protected DateTime deletionDate;

	protected String deletedBy;

	protected DeceCurrentStatusImpl(String namespaceURI,
			String elementLocalName, String namespacePrefix)
	{
		super(namespaceURI, elementLocalName, namespacePrefix);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getCreatedBy()
	{
		return this.createdBy;
	}

	@Override
	public DateTime getCreationDate()
	{
		return this.creationDate;
	}

	@Override
	public String getDeletedBy()
	{
		return this.deletedBy;
	}

	@Override
	public DateTime getDeletionDate()
	{
		return this.deletionDate;
	}

	@Override
	public String getDescription()
	{
		return this.description;
	}

	@Override
	public DateTime getModificationDate()
	{
		return this.modificationDate;
	}

	@Override
	public String getModifiedBy()
	{
		return this.modifiedBy;
	}

	@Override
	public String getValue()
	{
		return this.value;
	}

	@Override
	public void setCreatedBy(String createdBy)
	{
		this.createdBy = createdBy;
	}

	@Override
	public void setCreationDate(DateTime creationDate)
	{
		this.creationDate = creationDate;
	}

	@Override
	public void setDeletedBy(String deletedBy)
	{
		this.deletedBy = deletedBy;
	}

	@Override
	public void setDeletionDate(DateTime deletionDat)
	{
		this.deletionDate = deletionDat;
	}

	@Override
	public void setDescription(String value)
	{
		this.value = value;
	}

	@Override
	public void setModificationDate(DateTime modificationDate)
	{
		this.modificationDate = modificationDate;
	}

	@Override
	public void setModifiedBy(String modifiedBy)
	{
		this.modifiedBy = modifiedBy;
	}

	@Override
	public void setValue(String value)
	{
		this.value = value;
	}

	@Override
	public List<XMLObject> getOrderedChildren()
	{
		return null;
	}
}
