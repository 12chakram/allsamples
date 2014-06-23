package biz.neustar.dece.portal.saml.extensions.impl;

import org.opensaml.common.impl.AbstractSAMLObjectMarshaller;
import org.opensaml.xml.XMLObject;
import org.opensaml.xml.io.MarshallingException;
import org.w3c.dom.Element;

import biz.neustar.dece.portal.saml.extensions.DeceResourceStatus;

public class DeceResourceStatusMarshaller extends AbstractSAMLObjectMarshaller
{
	protected void marshallElementContent(XMLObject samlObject,
			Element domElement) throws MarshallingException
	{
		DeceResourceStatus deceResourceStatus = (DeceResourceStatus) samlObject;
		System.out.println("Inside DeceResourceStatusMarshaller");
	}
}
