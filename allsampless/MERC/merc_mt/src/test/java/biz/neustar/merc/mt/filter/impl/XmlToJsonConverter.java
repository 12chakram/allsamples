package biz.neustar.merc.mt.filter.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

public class XmlToJsonConverter {

	public static void main(String[] args) throws Exception
	{
		xmltojson();
		jsontoxml();
	}


	private static void jsontoxml() throws IOException {
		String inputFilename = "json/purchase-titles2.json";
		XmlToJsonConverter obj = new XmlToJsonConverter();
		String json = obj.convertStreamToString(obj.getClass().getClassLoader().getResourceAsStream(inputFilename));
		String xml = new JsonXmlConverter().convertToXml(json);
		System.out.println(xml);
	}


	private static void xmltojson() throws IOException {
		String inputFilename = "xml/stream1.xml";
		XmlToJsonConverter obj = new XmlToJsonConverter();
		String xml = obj.convertStreamToString(obj.getClass().getClassLoader().getResourceAsStream(inputFilename));
		JsonXmlConverter jx = new JsonXmlConverter();
		jx.setStripNamespaces(true);
		String json = jx.convertToJson(xml);
		System.out.println(json);
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
