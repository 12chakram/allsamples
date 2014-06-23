package biz.neustar.merc.mt.business;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.Resource;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import biz.neustar.merc.mt.exceptions.MTException;
import biz.neustar.merc.mt.service.MTBusinessService;
import biz.neustar.merc.mt.vo.MTRequest;

@RunWith(Parameterized.class)
@ContextConfiguration(locations = { "classpath:src/main/resources/mt-business-application-context.xml" })
public class MTBusinessServiceTestCase extends TestCase {
	
	private static final Logger logger = Logger.getLogger(MTBusinessServiceTestCase.class);
	
	private String reqMethod;
    private String reqPath;
    private String payLoadPattern;
    private boolean isAuthReq;
    private MTRequest mtRequest;
    
    @Resource
	private ApplicationContext	applicationContext = new FileSystemXmlApplicationContext(new String[] { "src/main/resources/mt-business-application-context.xml"});
	
	@Resource
	private MTBusinessService mtBusinessService;
	
    
    public MTBusinessServiceTestCase(String testCaseName,String reqMethod, String reqPath,
                            String payLoadPattern, boolean isAuthReq) {
                super(testCaseName);
                this.reqMethod = reqMethod;
                this.reqPath = reqPath;
                this.payLoadPattern = payLoadPattern;
                this.isAuthReq = isAuthReq;
    }
    
    /*public MTBusinessServiceTestCase(String testCaseName) {
    	super(testCaseName);
    }*/
    
    @Parameters
    public static Collection<Object[]> getConfig(){
    	Collection<Object[]> params = new ArrayList<Object[]>();
    	
    	Object[] arr1 = new Object[]{"testCaseName1","POST","/Users","CreateAcc",false};
    	Object[] arr2 = new Object[]{"testCaseName2","GET","/User","CreateAcc",true};
    	params.add(arr1);
    	params.add(arr2);
    	return params;
    	
    }
    
   /* @Override
    public void runTest()
    {
                testMe();
    }*/
    

    
    @Before
    public void prepareTestCase()
    {
    	mtBusinessService = (MTBusinessService) this.applicationContext.getBean("mtBusinessService");
    	mtRequest = new MTRequest();
    	mtRequest.setPayload("payloadString");
    	mtRequest.setMethod(this.reqMethod);
    	mtRequest.setUrlPath(reqPath);
    	logger.info("From@Before  - testCaseName: "+this.getName()+"  reqMethod: "+this.reqMethod+"  reqPath: "+this.reqPath+"  payLoadPattern: "+this.payLoadPattern+"  isAuthReq: "+this.isAuthReq);
    	
    }
    
    @Test
    public void testMe()
    {
    	if(null==mtBusinessService)
    		logger.info("Business Service Obj not instantiated");
    	try {
			mtBusinessService.processRequest(mtRequest);
		} catch (MTException e) {
			// TODO Auto-generated catch block
			logger.info(e.getErrorDescription());
			//e.printStackTrace();
		}
    	logger.info("testCaseName: "+this.getName()+"  reqMethod: "+this.reqMethod+"  reqPath: "+this.reqPath+"  payLoadPattern: "+this.payLoadPattern+"  isAuthReq: "+this.isAuthReq);
    			
                assertTrue("executeTestRun",true);
    }


}
