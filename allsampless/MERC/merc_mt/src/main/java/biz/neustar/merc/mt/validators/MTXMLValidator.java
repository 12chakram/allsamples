/**
 * 
 */
package biz.neustar.merc.mt.validators;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import biz.neustar.merc.mt.exceptions.MTException;
import biz.neustar.merc.mt.exceptions.MTException.MTResponseCode;

/**
 * @author pkuninti
 *
 */
public class MTXMLValidator {

	private static final Logger logger = Logger.getLogger(MTXMLValidator.class);
	private Schema schema;
	
	public MTXMLValidator(ArrayList<String> xsdList){
		SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		StreamSource schemaSource[] = new StreamSource[xsdList.size()];

		for( int i = 0; i < schemaSource.length; i++ )
		{
			if(logger.isDebugEnabled()){
				logger.debug("Loading XSD - "+xsdList.get(i));
			}
			schemaSource[i] = new StreamSource(this.getClass().getClassLoader().getResourceAsStream(xsdList.get(i)));
		}
		try {
			this.schema = factory.newSchema(schemaSource);
		} catch (SAXException e) {
			logger.error("Schema compilation failed");
			e.printStackTrace();
		}
	}
	
	public void validateXML(InputStream xmlInputStream) throws MTException 
	{
		if(xmlInputStream == null || this.schema == null) { 
			 throw new MTException(MTResponseCode.INTERNAL_SERVER_ERROR);
		}
		try {
			Validator validator = this.schema.newValidator();
		    validator.validate(new StreamSource(xmlInputStream));
		    if(logger.isDebugEnabled()) {
		    	logger.debug("validateXML() - returning true");
		    }
		} catch (Exception e) {
			if(logger.isDebugEnabled()) {
		    	logger.debug("validateXML() - Exception - "+e.getMessage());
		    }
			throw new MTException(MTResponseCode.BAD_REQUEST, e.getMessage());
		}
		 
	}
	/**
	 * @param schema the schema to set
	 */
	public void setSchema(Schema schema) {
		this.schema = schema;
	}
}
