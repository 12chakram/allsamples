package biz.neustar.dece.portal.saml.extensions.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.opensaml.common.impl.AbstractSAMLObject;
import org.opensaml.xml.XMLObject;

import biz.neustar.dece.portal.saml.extensions.DeceCurrentStatus;
import biz.neustar.dece.portal.saml.extensions.DeceResourceStatus;
import biz.neustar.dece.portal.saml.extensions.DeceStatusHistory;

public class DeceResourceStatusImpl extends AbstractSAMLObject implements
		DeceResourceStatus
{
	protected DeceResourceStatusImpl(String namespaceURI,
			String elementLocalName, String namespacePrefix)
	{
		super(namespaceURI, elementLocalName, namespacePrefix);
		// this.history=new
		// IndexedXMLObjectChildrenList<DeceStatusHistory>(this);
	}

	// private final IndexedXMLObjectChildrenList<DecePolicy> decePolicyList;
	private DeceCurrentStatus current;
	private DeceStatusHistory history;

	@Override
	public DeceCurrentStatus getCurrent()
	{
		// TODO Auto-generated method stub
		return this.current;
	}

	@Override
	public DeceStatusHistory getHistory()
	{
		// TODO Auto-generated method stub
		return this.history;
	}

	@Override
	public void setCurrent(DeceCurrentStatus current)
	{
		this.current = prepareForAssignment(this.current, current);
	}

	@Override
	public void setHistory(DeceStatusHistory history)
	{
		this.history = prepareForAssignment(this.history, history);
	}

	@Override
	public List<XMLObject> getOrderedChildren()
	{
		ArrayList<XMLObject> children = new ArrayList<XMLObject>();
		children.add(this.current);
		children.add(this.history);
		// System.out.println("GetOrderedChildren::size="+children.size());
		return Collections.unmodifiableList(children);
	}
}
