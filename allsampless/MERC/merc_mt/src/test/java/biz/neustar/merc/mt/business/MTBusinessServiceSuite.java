package biz.neustar.merc.mt.business;

import junit.framework.TestSuite;

public class MTBusinessServiceSuite extends TestSuite{

	public MTBusinessServiceSuite(){
		this.addTest(new MTBusinessServiceXmlUnitTestCase("TestCaseName","a","b","c",true)
				);
		
	}
	
}
