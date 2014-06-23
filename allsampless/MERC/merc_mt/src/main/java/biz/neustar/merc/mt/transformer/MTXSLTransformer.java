/**
 * 
 */
package biz.neustar.merc.mt.transformer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import biz.neustar.merc.mt.exceptions.MTException;
import biz.neustar.merc.mt.exceptions.MTException.MTResponseCode;

/**
 * @author pkuninti
 *
 */
@Component
public class MTXSLTransformer {

	private static final Logger logger = Logger.getLogger(MTXSLTransformer.class);
	
	public String transform(String inputXML, String xsltFileName) throws MTException
	{
		if(logger.isDebugEnabled()) {
			logger.debug("transform()  xsltFileName="+xsltFileName+" \ninputXML="+inputXML);
		}
		TransformerFactory factory = TransformerFactory.newInstance();
		StreamResult xmlOutputStream = null;
		try {
			StreamSource xslStreamSource = new StreamSource(this.getClass().getClassLoader().getResourceAsStream(xsltFileName));
			if(xslStreamSource != null){
				Transformer transformer = factory.newTransformer(xslStreamSource);
				StreamSource inputStreamSource = new StreamSource(new ByteArrayInputStream(inputXML.getBytes("UTF-8")));
				xmlOutputStream = new StreamResult(new ByteArrayOutputStream());
				transformer.transform(inputStreamSource, xmlOutputStream);
			}
		} catch (Exception e) {
			logger.warn("transform() - XSLTransformation Exception - "+e.getMessage());
			e.printStackTrace();
			throw new MTException(MTResponseCode.BAD_REQUEST);
		}
		return (xmlOutputStream != null && xmlOutputStream.getOutputStream() != null) ? new String(((ByteArrayOutputStream)xmlOutputStream.getOutputStream()).toByteArray()): inputXML;
	}	
}
