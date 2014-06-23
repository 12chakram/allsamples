package biz.neustar.merc.mt.business;

import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.XMLTestCase;
import org.junit.Test;

public class MTBusinessServiceXmlUnitTestCase extends XMLTestCase{

	private String reqMethod;
	private String reqPath;
	private String payLoadPattern;
	private boolean isAuthReq;
	
	public void init()
	{
		System.setProperty("javax.xml.parsers.DocumentBuilderFactory",
				"com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl");
		System.setProperty("javax.xml.parsers.SAXParserFactory",
				"com.sun.org.apache.xerces.internal.jaxp.SAXParserFactoryImpl");
		System.setProperty("javax.xml.transform.TransformerFactory",
				"com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl");
	}
	
	public MTBusinessServiceXmlUnitTestCase() {
		super("name");
		init();
	}
	public MTBusinessServiceXmlUnitTestCase(String testCaseName,String reqMethod, String reqPath,
			String payLoadPattern, boolean isAuthReq) {
		super(testCaseName);
		init();
		this.reqMethod = reqMethod;
		this.reqPath = reqPath;
		this.payLoadPattern = payLoadPattern;
		this.isAuthReq = isAuthReq;
	}
	
	public MTBusinessServiceXmlUnitTestCase(String testCaseName) {
		super(testCaseName);
		init();
	}
    
	@Test
	public void testMe()
	{
		assertTrue("FAILED!", true);
	}
	
	@Test
	public void testXMLIdentical()throws Exception {
		String myControlXML =
							"<struct><int>3</int><boolean>false</boolean></struct>";
		String myTestXML =
							"<struct><boolean>false</boolean><int>3</int></struct>";
		
		Diff myDiff = new Diff(myControlXML, myTestXML);
		assertTrue("XML similar " + myDiff.toString(),
		myDiff.similar());
		assertTrue("XML identical " + myDiff.toString(),
		myDiff.identical());
	}
	
}
