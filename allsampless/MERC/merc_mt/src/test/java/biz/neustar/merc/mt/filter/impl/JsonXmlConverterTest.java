package biz.neustar.merc.mt.filter.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({JsonXmlConverterTest.class})
class JsonXmlTestSuite {
	
}

/**
 * Test cases for testing new JsonXmlConverter()
 *
 */
public class JsonXmlConverterTest extends TestCase {

	// StAXON 1.0 places this string as beginning, after the JSON to XML conversion
	private static final String XMLVER_STR = "<?xml version=\"1.0\" ?>";
	
	@Before
	public void setUp(){}
	
	@After
	public void tearDown(){}
	
	@Test
	public void testValidJsonToXml() throws Exception
	{
		// \s A whitespace character: [ \t\n\x0B\f\r]
		
		String json = convertStreamToString(this.getClass().getClassLoader().getResourceAsStream("jsonxmlconv/jsoninput.dat"));
		String expXml = convertStreamToString(this.getClass().getClassLoader().getResourceAsStream("jsonxmlconv/xmloutput.dat")).replaceAll("\\s+", "");
		
		String convXml = new JsonXmlConverter().convertToXml(json).replaceAll("\\s+", "");
		if (!expXml.equalsIgnoreCase(convXml))
			fail("Conversion error. Content mis-match!");
	}

	@Test
	public void testValidXmlToJson() throws Exception
	{
		String xml = convertStreamToString(this.getClass().getClassLoader().getResourceAsStream("jsonxmlconv/xmlinput.dat"));
		String expJson = convertStreamToString(this.getClass().getClassLoader().getResourceAsStream("jsonxmlconv/jsonoutput.dat")).replaceAll("\\s+", "");
		
		String convJson = new JsonXmlConverter().convertToJson(xml).replaceAll("\\s+", "");
		if (!expJson.equalsIgnoreCase(convJson))
			fail("Conversion error. Content mis-match!");
	}
	
	@Test
	public void testEmptyJsonToXml() throws Exception
	{
		String convXml = new JsonXmlConverter().convertToXml("");
		if (!XMLVER_STR.equals(convXml))
			fail("Empty string! Json->Xml");
	}
	
	@Test
	public void testEmptyXmlToJson() throws Exception
	{
		String convJson = new JsonXmlConverter().convertToJson("");
		if (!"".equals(convJson))
			fail("Empty string! Xml->Json");
	}
	
	@Test
	public void testNullJsonToXml() throws Exception
	{
		String convXml = new JsonXmlConverter().convertToXml(null);
		assertNull("Null string! Json->Xml", convXml);
	}
	
	@Test
	public void testNullXmlToJson() throws Exception
	{
		String convJson = new JsonXmlConverter().convertToJson(null);
		assertNull("Null string! Xml->Json", convJson);		
	}
	
	@Test(expected=JsonXmlConversionException.class)
	public void testInvalidJsonToXml() throws Exception
	{
		String invalidJson = "{\"test\":\"xyz\",\"abcd\"}";
		try 
		{
			new JsonXmlConverter().convertToXml(invalidJson);
		} 
		catch (Exception e)
		{
			if (!(e instanceof JsonXmlConversionException))
				fail("Unexpected exception caught!");
			if (e.getMessage() == null ||
					e.getMessage().equals(""))
				fail("Null or Empty exception message found!");
		}
	}
	
	@Test(expected=JsonXmlConversionException.class)
	public void testInvalidXmlToJson() throws Exception
	{
		String invalidXml = "<root>data";
		try 
		{
			new JsonXmlConverter().convertToJson(invalidXml);
		} 
		catch (Exception e)
		{
			if (!(e instanceof JsonXmlConversionException))
				fail("Unexpected exception caught!");
			if (e.getMessage() == null ||
					e.getMessage().equals(""))
				fail("Null or Empty exception message found!");
		}
	}
	
	/**
	 * Read a file to string
	 * @param InputStream object
	 * @return
	 */
	private String convertStreamToString(InputStream is) throws IOException {
		if (is != null) {
			Writer writer = new StringWriter();

			char[] buffer = new char[1024];
			try {
				Reader reader = new BufferedReader(new InputStreamReader(is,
						"UTF-8"));
				int n;
				while ((n = reader.read(buffer)) != -1) {
					writer.write(buffer, 0, n);
				}
			} finally {
				is.close();
			}
			return writer.toString();
		} else {
			return "";
		}
	}	
	
	
}
