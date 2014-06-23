package biz.neustar.dece.portal.saml.extensions.impl;

import javax.xml.namespace.QName;

import org.apache.log4j.Logger;
import org.opensaml.common.impl.AbstractSAMLObjectMarshaller;
import org.opensaml.xml.XMLObject;
import org.opensaml.xml.io.MarshallingException;
import org.opensaml.xml.util.XMLHelper;
import org.w3c.dom.Element;

import biz.neustar.dece.portal.saml.extensions.DeceCurrentStatus;
import biz.neustar.dece.portal.saml.extensions.DecePolicyConstants;
import biz.neustar.dece.portal.saml.extensions.DecePriorStatusType;
import biz.neustar.dece.portal.saml.extensions.utils.Util;

public class DeceCurrentStatusMarshaller extends AbstractSAMLObjectMarshaller
{
	Util util;
	Logger log = Logger.getLogger(this.getClass());

	public DeceCurrentStatusMarshaller()
	{
		System.out.println("DeceCurrentStatusMarshaller is being called");
		util = new Util();
	}

	protected void marshallAttributes(XMLObject xmlObject, Element domElement)
	{
		log.debug("DeceCurrentStatusMarshaller::marshallAttribute");
		DeceCurrentStatus deceCurrentStatus = (DeceCurrentStatus) xmlObject;
		/*
		 * if(!isDone){ //util.buildHierarchy(domElement, qname); isDone=true; }
		 */
		if (deceCurrentStatus != null)
		{
			if (deceCurrentStatus.getCreationDate() != null)
			{
				domElement.setAttributeNS(null,
						DeceCurrentStatus.CREATION_DATE_ATTRIB_NAME,
						deceCurrentStatus.getCreationDate().toString());
			}
			if (deceCurrentStatus.getCreatedBy() != null)
			{
				domElement.setAttributeNS(null,
						DeceCurrentStatus.CREATEDBY_ATTRIB_NAME,
						deceCurrentStatus.getCreatedBy());
			}
			if (deceCurrentStatus.getDeletionDate() != null)
			{
				domElement.setAttributeNS(null,
						DeceCurrentStatus.DELETION_DATE_ATTRIB_NAME,
						deceCurrentStatus.getDeletionDate().toString());
			}
			if (deceCurrentStatus.getDeletedBy() != null)
			{
				domElement.setAttributeNS(null,
						DeceCurrentStatus.DELETED_BY_ATTRIB_NAME,
						deceCurrentStatus.getDeletedBy());
			}
			if (deceCurrentStatus.getModificationDate() != null)
			{
				domElement.setAttributeNS(null,
						DeceCurrentStatus.MODIFICATION_DATE_ENTITY_ATTRIB_NAME,
						deceCurrentStatus.getModificationDate().toString());
			}
			if (deceCurrentStatus.getModifiedBy() != null)
			{
				domElement.setAttributeNS(null,
						DeceCurrentStatus.MODIFIED_BY_ATTRIB_NAME,
						deceCurrentStatus.getModifiedBy());
			}
		}
	}

	/** {@inheritDoc} */
	protected void marshallElementContent(XMLObject samlObject,
			Element domElement) throws MarshallingException
	{
		log.debug("DeceCurrentStatusMarshaller::marshallElementContent");
		DeceCurrentStatus deceCurrentStatus = (DeceCurrentStatus) samlObject;
		if (deceCurrentStatus.getValue() != null)
		{
			QName qname = XMLHelper.constructQName(DecePolicyConstants.DECE_NS,
					DeceCurrentStatus.VALUE_ATTRIB_NAME, "dece");
			util.buildHierarchy(domElement, deceCurrentStatus.getValue(), qname);
		}
		if (deceCurrentStatus.getDescription() != null)
		{
			QName qname = XMLHelper.constructQName(DecePolicyConstants.DECE_NS,
					DeceCurrentStatus.DESCRIPTION_ATTRIB_NAME, "dece");
			util.buildHierarchy(domElement, deceCurrentStatus.getDescription(),
					qname);
		}
	}
}
