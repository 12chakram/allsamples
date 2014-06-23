package biz.neustar.merc.mt.filter.impl;

import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stax.StAXResult;
import javax.xml.transform.stax.StAXSource;

import org.apache.log4j.Logger;
import org.codehaus.jettison.badgerfish.BadgerFishXMLStreamReader;
import org.codehaus.jettison.badgerfish.BadgerFishXMLStreamWriter;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.codehaus.jettison.util.StringIndenter;

/**
 * JSON <-> XML converter
 * 		
 *		- Uses Jettison BadgerFish
 *		- Due to unreliable initiation of XML Factory objects, we are using explicit FQN class creations
 *
 *		- NOTE: Remove stax-api-1.0.1.jar from the WAR file (if, present)
 */
public class JsonXmlConverter {

	private static final Logger logger =
	        Logger.getLogger(JsonXmlConverter.class);
	
	// At beginning, after the JSON to XML conversion
	private static final String XMLVER_STR = "<?xml version=\"1.0\" ?>";
	
	// JSON namespace string
	private final String XMLNS_JSON_STR = "\"@xmlns\":{"
											+ "\"md\":\"http:\\/\\/www.movielabs.com\\/schema\\/md\\/v1.07\\/md\","
											+ "\"merc-common\":\"http:\\/\\/www.neustar.biz\\/schema\\/2012\\/01\\/merc-common\","
											+ "\"merc-basic\":\"http:\\/\\/www.neustar.biz\\/schema\\/2012\\/01\\/merc-basic\","
											+ "\"xsi\":\"http:\\/\\/www.w3.org\\/2001\\/XMLSchema-instance\""
											+ "},"
											+ "\"@xsi:schemaLocation\":\"http:\\/\\/www.neustar.biz\\/schema\\/2012\\/01\\/merc-basic merc-basic.xsd \",";

	// Class FQNs
	private final String CL_XML_INPUT_FACT = "com.sun.xml.internal.stream.XMLInputFactoryImpl";
	private final String CL_XML_OUTPUT_FACT = "com.sun.xml.internal.stream.XMLOutputFactoryImpl";
	private final String CL_TRANSFORMER_FACT = "com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl";
	
	// class XML Input Factory
	private final XMLInputFactory cXmlInputFactoryImpl;
	
	// class XML Output Factory
	private final XMLOutputFactory cXmlOutputFactoryImpl;
	
	// class Transformer
	private final Transformer tr;
	
	// Bean Property: whether to strip off namespaces in final json
	private boolean stripNamespaces = true;
	
	/**
	 * Constructor
	 */
	public JsonXmlConverter() 
	{
		try
		{
			// initialize final members in constructor //
			
			// class loader
			////////////////////////////////////////////////////////////////////////////
			// Note: As of JBOSS AS 7, the "BOOTSTRAP" Class loader needs to be used to
			//			load the following classes.
			//       ie., null passed to Class.forName(...) will do. It will call the 
			//			'bootstrap class loader'. Which loaded rt.jar.
			////////////////////////////////////////////////////////////////////////////
			// ClassLoader cl = Class.forName("biz.neustar.merc.mt.filter.impl.JsonXmlConverter").getClassLoader();
			
			// load explicitly using the fully qualified class name
			// cXmlOutputFactoryImpl = cl.loadClass(CL_XML_OUTPUT_FACT);
			cXmlOutputFactoryImpl = (XMLOutputFactory)Class.forName(CL_XML_OUTPUT_FACT, true, null).newInstance();
		
			// load explicitly using the fully qualified class name
			// cXmlInputFactoryImpl = cl.loadClass(CL_XML_INPUT_FACT);
			cXmlInputFactoryImpl = ( XMLInputFactory)Class.forName(CL_XML_INPUT_FACT, true, null).newInstance();
			
			// load explicitly using the fully qualified class name
			tr = ((TransformerFactory)Class.forName(CL_TRANSFORMER_FACT, true, null).newInstance()).newTransformer();
		}
		catch (Exception ex)
		{
			throw new JsonXmlConversionException("Unable to create required classes using Class Loader and Initialize : " + ex.getMessage(), ex);
		}
	}

	/**
	 * Instantiate from Spring Context
	 * 		- classes & objects created by Spring
	 * @param inpFact
	 * @param outFact
	 * @param tran
	 */
	public JsonXmlConverter(XMLInputFactory inpFact, XMLOutputFactory outFact, Transformer tran)
	{
		cXmlInputFactoryImpl = inpFact;
		cXmlOutputFactoryImpl = outFact;
		tr = tran;
	}
	
	/**
	 * Convert JSON to XML
	 * 
	 * @param json string
	 * @return xml string
	 * @throws JsonXmlConversionException
	 */
	public String convertToXml(String json)
	{
		logger.debug("Entering convertToXml(String json)" + json);
		
		// if null
		if (json == null)
			return null;
		
		// if empty string
		if ("".equals(json))
			return XMLVER_STR;
		
		String retXml = null;
		
		try 
		{
			// StringBuffer var = new StringBuffer(json.replaceAll("\\s+", ""));
			
			// add Json Namespaces
			String var = addJsonNsString(json);
			
			// Create reader (JSON)
			XMLStreamReader reader = getJsonStreamReader(var);

			// writer
			StringWriter xmlWr = new StringWriter();
			
			// Create writer (XML)
			XMLStreamWriter writer = getXmlStreamWriter(xmlWr);

			// transform
			transform(reader, writer);
			
			// set return XML
			retXml = xmlWr.toString();
		} 
		catch (Exception e) 
		{
			// throw exception
			throw new JsonXmlConversionException("Unable to convert JSON input to XML. "+e.getMessage()+"\n"+json, e);
		}
		
		logger.debug("Exiting convertToXml(String json)" + retXml);
		return retXml;
	}

	
	/**
	 * Convert XML to JSON
	 * 
	 * @param xml string
	 * @return json string
	 * @throws JsonXmlConversionException
	 */	
	public String convertToJson(String xml)
	{
		logger.debug("Entering convertToJson(String xml)" + xml);

		// if null
		if (xml == null)
			return null;
		
		// if empty string
		if ("".equals(xml))
			return "";
		
		String retJson = null;
		
		try 
		{
			// strip whitespaces
			String var = xml.replaceAll(">\\s+<", "><");
			
			// Create reader (XML)
			XMLStreamReader reader = getXmlStreamReader(var);
		
			// writer
			StringWriter jsonWr = new StringWriter();
			
			// Create writer (JSON)
			XMLStreamWriter writer = getJsonStreamWriter(jsonWr);
			
			// transform
			transform(reader, writer);
			
			// strip NS strings from JSON
			String jsonNsLess = null;
			if (isStripNamespaces())
				jsonNsLess = stripJsonNsString(jsonWr.toString());
			else
				jsonNsLess = jsonWr.toString();
			
			// json indenter: remove when in production
			StringIndenter si = new StringIndenter(jsonNsLess);
			
			// return json
			retJson = si.result();
		} 
		catch (Exception e) 
		{
			// throw exception
			throw new JsonXmlConversionException("Unable to convert XML input to JSON.  "+e.getMessage()+"\n"+xml, e);
		}
		
		logger.debug("Exiting convertToJson(String xml)" + retJson);
		
		return retJson;	
	}

	/**
	 * Get XML Stream Writer
	 * 
	 * @param String Writer
	 * @return XML Stream Writer
	 * @throws ClassNotFoundException
	 * @throws XMLStreamException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	private XMLStreamWriter getXmlStreamWriter(StringWriter xmlWr)
			throws ClassNotFoundException, XMLStreamException,
			InstantiationException, IllegalAccessException 
	{
		// Create Writer (XML)
		//////////////////////////////////////////////////////////////////////////////////////////
		//    - error: the following line is returning unexpected/incompatible XMLStreamWriter
		// XMLStreamWriter writer = XMLOutputFactory.newInstance().createXMLStreamWriter(xmlRet);
		//////////////////////////////////////////////////////////////////////////////////////////
		
		// create writer
		XMLStreamWriter writer = getXmlOutputFactoryImpl().createXMLStreamWriter(xmlWr);
		
		//MercMtXmlEventWriter writer = new MercMtXmlEventWriter(writerr);
		
		return writer;
	}
	
	/**
	 * Get XML Stream Reader
	 * 
	 * @param Input XML string
	 * @return XML Stream Reader
	 * @throws ClassNotFoundException
	 * @throws XMLStreamException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	private XMLStreamReader getXmlStreamReader(String var)
			throws ClassNotFoundException, XMLStreamException,
			InstantiationException, IllegalAccessException 
	{
		// Create Reader (XML)
		////////////////////////////////////////////////////////////////////////////////////////////////////////
		//    - error: the following line is returning unexpected/incompatible XMLStreamReader
		// XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(new StringReader(var));
		////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		// create
		XMLStreamReader reader = getXmlInputFactoryImpl().createXMLStreamReader(new StringReader(var));
		
		// MercMtXmlEventReader reader = new MercMtXmlEventReader(readerr);			
		
		return reader;
	}

	
	/**
	 * Get required XMLStreamReader
	 * 		- BadgerFish implementation
	 * @param json string
	 * @return XMLStreamReader
	 * @throws
	 */
	private XMLStreamReader getJsonStreamReader(String json) 
			throws XMLStreamException, JSONException 
	{
		return new BadgerFishXMLStreamReader(new JSONObject(json));
	}
	
	/**
	 * Get required XMLStreamWriter
	 * 		- BadgerFish implementation
	 * @param writer where json o/p is written
	 * @return XMLStreamWriter
	 */
	private XMLStreamWriter getJsonStreamWriter(Writer writer)
	{
		return new BadgerFishXMLStreamWriter(writer);
	}

	/**
	 * Transform from Source to Result
	 * 
	 * @param reader
	 * @param writer
	 * @throws ClassNotFoundException
	 * @throws TransformerConfigurationException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws TransformerException
	 */
	private void transform(XMLStreamReader reader, XMLStreamWriter writer)
			throws ClassNotFoundException, TransformerConfigurationException,
			InstantiationException, IllegalAccessException,
			TransformerException 
	{
		// StAX source & result
		Source source = new StAXSource(reader);
		Result result = new StAXResult(writer);
		
		// Copy source to result via "identity transform".
		//////////////////////////////////////////////////////////////////////////////////////////
		//	  - error: the following line is returning unexpected/incompatible TransformerFactory
		// TransformerFactory.newInstance().newTransformer().transform(source, result);
		//////////////////////////////////////////////////////////////////////////////////////////
		
		getTransformer().transform(source, result);
	}
	
	/**
	 * Add XMLNS_JSON_STR string to JSON
	 * 
	 * @param json
	 * @return modified json
	 */
	private String addJsonNsString(String json)
	{
		// search if already there?
		if (json.indexOf("@xmlns") > 0)
		{
			logger.warn("Found @xmlns in json. No namespace strings will be added. " + json);
			return json;
		}

		// string buffer
		StringBuffer sbJson = new StringBuffer(json);
		
		// find 2nd '{'
		int begin = sbJson.indexOf("{");
		begin = sbJson.indexOf("{", begin+1);
		
		// verify
	    if (begin <= 0)
	    {
	      logger.fatal("Unable to find 2nd '{'. After which namespace strings have to be added. " + json);
	      return json;
	    }
		
		// add in the json
		sbJson.insert(begin+1, XMLNS_JSON_STR);
		
		return sbJson.toString();
	}
	
	/**
	 * Strip XMLNS Strings from JSON
	 * 
	 * @param json
	 * @return modified json
	 */
	private String stripJsonNsString(String json)
	{
		try 
		{
			// form JSONObject
			JSONObject rObj = new JSONObject(json.toString());
			
			// fetch root key
			JSONObject rootKeyObj = (JSONObject)rObj.get((String)rObj.keys().next());
			
			// remove xmlns key/value
			rootKeyObj.remove("@xmlns");
			
			// remove xsi:schemaLocation
			rootKeyObj.remove("@xsi:schemaLocation");
			
			// remove xsi:type
			// rootKeyObj.remove("@xsi:type");
			
			// return json string
			json = rObj.toString();
		}
		catch (JSONException ex)
		{
			logger.fatal("Unable to remove xmlns strings from JSON. " + ex.getMessage());
		}
		
		return json;
	}

	public boolean isStripNamespaces() {
		return stripNamespaces;
	}

	public void setStripNamespaces(boolean stripNamespaces) {
		this.stripNamespaces = stripNamespaces;
	}

	public XMLInputFactory getXmlInputFactoryImpl() {
		return cXmlInputFactoryImpl;
	}

	public XMLOutputFactory getXmlOutputFactoryImpl() {
		return cXmlOutputFactoryImpl;
	}

	public Transformer getTransformer() {
		return tr;
	}
}
