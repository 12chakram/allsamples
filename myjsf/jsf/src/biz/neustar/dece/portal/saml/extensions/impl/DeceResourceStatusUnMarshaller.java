package biz.neustar.dece.portal.saml.extensions.impl;

import org.opensaml.common.impl.AbstractSAMLObjectUnmarshaller;
import org.opensaml.xml.io.UnmarshallingException;

public class DeceResourceStatusUnMarshaller extends
		AbstractSAMLObjectUnmarshaller
{
	public DeceResourceStatusUnMarshaller()
	{
		super();
	}

	protected void processChildElement(
			org.opensaml.xml.XMLObject parentSAMLObject,
			org.opensaml.xml.XMLObject childSAMLObject)
			throws UnmarshallingException
	{
		super.processChildElement(parentSAMLObject, childSAMLObject);
	}
}