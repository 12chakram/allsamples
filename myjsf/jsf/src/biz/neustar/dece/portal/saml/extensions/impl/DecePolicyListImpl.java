package biz.neustar.dece.portal.saml.extensions.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.opensaml.common.impl.AbstractSAMLObject;
import org.opensaml.xml.XMLObject;
import org.opensaml.xml.util.IndexedXMLObjectChildrenList;

import biz.neustar.dece.portal.saml.extensions.DecePolicy;
import biz.neustar.dece.portal.saml.extensions.DecePolicyList;

public class DecePolicyListImpl extends AbstractSAMLObject implements
		DecePolicyList
{
	private final IndexedXMLObjectChildrenList<DecePolicy> decePolicyList;

	private String policyListId;

	protected DecePolicyListImpl(String namespaceURI, String elementLocalName,
			String namespacePrefix)
	{
		super(namespaceURI, elementLocalName, namespacePrefix);
		decePolicyList = new IndexedXMLObjectChildrenList<DecePolicy>(this);
	}

	@Override
	public List<DecePolicy> getDecePolicyList()
	{
		return this.decePolicyList.subList(0, decePolicyList.size());
	}

	@Override
	public String getPolicyListId()
	{
		return this.policyListId;
	}

	@Override
	public void setPolicyListId(String PolicyListId)
	{
		// this.policyListId=PolicyListId;
		this.policyListId = prepareForAssignment(this.policyListId,
				PolicyListId);
	}

	@Override
	public List<XMLObject> getOrderedChildren()
	{
		ArrayList<XMLObject> children = new ArrayList<XMLObject>();
		children.addAll(decePolicyList);
		// System.out.println("GetOrderedChildren::size="+children.size());
		return Collections.unmodifiableList(children);
	}
}
