package biz.neustar.dece.portal.saml.extensions;

import java.util.Collection;
import java.util.List;

import javax.xml.namespace.QName;

import org.opensaml.common.SAMLObject;
import org.opensaml.xml.schema.XSAny;

public interface DecePolicy extends SAMLObject
{
	/** Element local name. */
	public static final String DEFAULT_ELEMENT_LOCAL_NAME = "Policy";

	/** Default element name. */
	public static final QName DEFAULT_ELEMENT_NAME = new QName(
			DecePolicyConstants.DECE_NS, DEFAULT_ELEMENT_LOCAL_NAME,
			DecePolicyConstants.DECE_PREFIX);

	/** Local name of the XSI type. */
	public static final String TYPE_LOCAL_NAME = "Policy-type";

	/** QName of the XSI type. */
	public static final QName TYPE_NAME = new QName(
			DecePolicyConstants.DECE_NS, TYPE_LOCAL_NAME,
			DecePolicyConstants.DECE_PREFIX);
	public static final String POLICY_ID_ATTRIB_NAME = "PolicyID";
	/** PolicyClass attribute name. */
	public static final String POLICY_CLASS_ATTRIB_NAME = "PolicyClass";
	/** Resource attribute name. */
	public static final String RESOURCE_ATTRIB_NAME = "Resource";
	/** RequestingEntity attribute name. */
	public static final String REQUESTING_ENTITY_ATTRIB_NAME = "RequestingEntity";
	/** PolicyAuthority attribute name. */
	public static final String POLICY_AUTHORITY_ATTRIB_NAME = "PolicyAuthority";

	/** ResourceStatus attribute name. */
	// public static final String RESOURCE_STATUS_ATTRIB_NAME =
	// "ResourceStatus";

	public XSAny getPolicyClass();

	public void setPolicyClass(String policyClass);

	public List<XSAny> getResources();

	public boolean isResource(String resource);

	public void addResource(String resource);

	public void removeResource(XSAny resource);

	public void removeResources(Collection<XSAny> resources);

	public void removeAllResources();

	// public void setResource(String resource);

	public List<XSAny> getRequestingEntitities();

	public boolean isRequestingEntity(String requestingEntity);

	public void addRequestingEntity(String requestingEntity);

	public void removeRequestingEntity(XSAny requestingEntity);

	public void removeRequestingEntitities(Collection<XSAny> requestingEntities);

	public void removeAllRequestingEntitities();

	public XSAny getPolicyAuthority();

	public void setPolicyAuthority(String policyAuthority);

	public DeceResourceStatus getResourceStatus();

	public String getPolicyId();

	public void setResourceStatus(DeceResourceStatus deceResourceStatus);

	void setPolicyId(String policyId);
}
