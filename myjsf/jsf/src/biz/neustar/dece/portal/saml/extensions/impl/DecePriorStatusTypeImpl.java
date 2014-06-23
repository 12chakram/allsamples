package biz.neustar.dece.portal.saml.extensions.impl;

import java.util.List;

import org.joda.time.DateTime;
import org.opensaml.common.impl.AbstractSAMLObject;
import org.opensaml.xml.XMLObject;

import biz.neustar.dece.portal.saml.extensions.DecePriorStatusType;

public class DecePriorStatusTypeImpl extends AbstractSAMLObject implements
		DecePriorStatusType
{
	protected DecePriorStatusTypeImpl(String namespaceURI,
			String elementLocalName, String namespacePrefix)
	{
		super(namespaceURI, elementLocalName, namespacePrefix);
	}

	protected String value;
	protected String modifiedBy;
	protected String description;
	protected DateTime modificationDate;

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
	public void setDescription(String description)
	{
		this.description = description;
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
