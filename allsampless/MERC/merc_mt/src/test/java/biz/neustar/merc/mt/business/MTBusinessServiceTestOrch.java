package biz.neustar.merc.mt.business;

import junit.framework.TestResult;

public class MTBusinessServiceTestOrch {
	
	public static void executeTest()
	{
		MTBusinessServiceSuite mtSuite = new MTBusinessServiceSuite();
		TestResult tr = new TestResult();
		mtSuite.run(tr);
	}

}
