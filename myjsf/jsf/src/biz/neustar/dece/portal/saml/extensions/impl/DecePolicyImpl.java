package biz.neustar.dece.portal.saml.extensions.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.opensaml.common.impl.AbstractSAMLObject;
import org.opensaml.xml.XMLObject;
import org.opensaml.xml.schema.XSAny;
import org.opensaml.xml.util.DatatypeHelper;
import org.opensaml.xml.util.LazyList;

import biz.neustar.dece.portal.saml.extensions.DecePolicy;
import biz.neustar.dece.portal.saml.extensions.DeceResourceStatus;
import biz.neustar.dece.portal.saml.extensions.utils.Util;

public class DecePolicyImpl extends AbstractSAMLObject implements DecePolicy
{
	private XSAny policyAuthority = null;
	private XSAny policyClass = null;
	private List<XSAny> requestingEntity;
	private List<XSAny> resource;
	private String policyId;

	@Override
	public void setPolicyId(String policyId)
	{
		this.policyId = policyId;
	}

	// private final IndexedXMLObjectChildrenList<DecePolicy> decePolicyList;
	DeceResourceStatus resourceStatus;
	private Util util;

	protected DecePolicyImpl(String namespaceURI, String elementLocalName,
			String namespacePrefix)
	{
		super(namespaceURI, elementLocalName, namespacePrefix);
		this.requestingEntity = new LazyList<XSAny>();
		this.resource = new LazyList<XSAny>();
		util = new Util();
		// this.resourceStatus=new DeceResourceStatusImpl();
	}

	@Override
	public XSAny getPolicyAuthority()
	{
		return this.policyAuthority;
	}

	@Override
	public XSAny getPolicyClass()
	{
		return this.policyClass;
	}

	@Override
	public List<XSAny> getRequestingEntitities()
	{
		return this.requestingEntity;
	}

	@Override
	public List<XSAny> getResources()
	{
		return this.resource;
	}

	@Override
	public DeceResourceStatus getResourceStatus()
	{
		return this.resourceStatus;
	}

	@Override
	public void setResourceStatus(DeceResourceStatus deceResourceStatus)
	{
		this.resourceStatus = prepareForAssignment(this.resourceStatus,
				deceResourceStatus);
	}

	@Override
	public void setPolicyAuthority(String policyAuthority)
	{
		XSAny xSAny = util
				.buildXSAnyObject(DecePolicy.POLICY_AUTHORITY_ATTRIB_NAME);
		xSAny.setTextContent(policyAuthority);
		this.policyAuthority = prepareForAssignment(this.policyAuthority, xSAny);
	}

	@Override
	public void setPolicyClass(String policyClass)
	{
		XSAny xSAny = util
				.buildXSAnyObject(DecePolicy.POLICY_CLASS_ATTRIB_NAME);
		xSAny.setTextContent(policyClass);
		this.policyClass = prepareForAssignment(this.policyClass, xSAny);
	}

	// public List<XSAny> buildResources
	@Override
	public List<XMLObject> getOrderedChildren()
	{
		ArrayList<XMLObject> children = new ArrayList<XMLObject>();
		if (this.getPolicyClass() != null)
		{
			children.add(this.getPolicyClass());
		}
		if (this.getResources().size() > 0)
		{
			children.addAll(this.getResources());
		}
		if (this.getRequestingEntitities().size() > 0)
		{
			children.addAll(this.getRequestingEntitities());
		}
		if (this.getPolicyAuthority() != null)
		{
			children.add(this.getPolicyAuthority());
		}
		if (this.getResourceStatus() != null)
		{
			children.add(this.getResourceStatus());
		}
		// children.add(this);
		return Collections.unmodifiableList(children);
		// return children;
		// return null;
	}

	@Override
	public void addRequestingEntity(String requestingEntity)
	{
		requestingEntity = DatatypeHelper
				.safeTrimOrNullString(requestingEntity);
		if (requestingEntity != null
				&& !this.requestingEntity.contains(requestingEntity))
		{
			releaseThisandParentDOM();
			XSAny xSAny = util
					.buildXSAnyObject(DecePolicy.REQUESTING_ENTITY_ATTRIB_NAME);
			xSAny.setTextContent(requestingEntity);
			this.requestingEntity.add(xSAny);
		}
	}

	@Override
	public void addResource(String resource)
	{
		resource = DatatypeHelper.safeTrimOrNullString(resource);
		if (resource != null && !this.resource.contains(resource))
		{
			releaseThisandParentDOM();
			XSAny xSAny = util
					.buildXSAnyObject(DecePolicy.RESOURCE_ATTRIB_NAME);
			xSAny.setTextContent(resource);
			this.resource.add(xSAny);
		}
	}

	@Override
	public boolean isRequestingEntity(String requestingEntity)
	{
		return this.requestingEntity.contains(requestingEntity);
	}

	@Override
	public boolean isResource(String resource)
	{
		return this.resource.contains(resource);
	}

	@Override
	public void removeAllRequestingEntitities()
	{
		for (XSAny protocol : this.requestingEntity)
		{
			removeRequestingEntity(protocol);
		}
	}

	@Override
	public void removeAllResources()
	{
		for (XSAny protocol : this.resource)
		{
			removeResource(protocol);
		}
	}

	@Override
	public void removeRequestingEntitities(Collection<XSAny> resources)
	{
		for (XSAny protocol : resources)
		{
			removeRequestingEntity(protocol);
		}
	}

	@Override
	public void removeRequestingEntity(XSAny protocol)
	{
		// protocol = DatatypeHelper.safeTrimOrNullString(protocol);
		if (protocol != null && this.requestingEntity.contains(protocol))
		{
			releaseThisandParentDOM();
			this.requestingEntity.remove(protocol);
		}
	}

	@Override
	public void removeResource(XSAny resource)
	{
		// resource = DatatypeHelper.safeTrimOrNullString(resource);
		if (this.resource != null && this.resource.contains(resource))
		{
			releaseThisandParentDOM();
			// XSAny xSAny= util.buildXSAnyObject(DecePolicy.);
			this.resource.remove(resource);
		}
	}

	@Override
	public void removeResources(Collection<XSAny> resources)
	{
		for (XSAny protocol : resources)
		{
			removeResource(protocol);
		}
	}

	@Override
	public String getPolicyId()
	{
		// TODO Auto-generated method stub
		return this.policyId;
	}
}
