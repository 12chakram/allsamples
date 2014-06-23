/**
 * 
 */
package biz.neustar.merc.mt.validators;

import java.io.InputStream;

import javax.annotation.Resource;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author pkuninti
 *
 */
@ContextConfiguration(locations = { "classpath:src/main/resources/mt-business-application-context.xml" })
public class MTXMLValidatorTest extends TestCase {

	private static final Logger logger = Logger.getLogger(MTXMLValidator.class);
	
	@Resource
	private ApplicationContext	applicationContext = new FileSystemXmlApplicationContext(new String[] { "src/main/resources/mt-business-application-context.xml"});
	
	@Resource
	private 	MTXMLValidator mtXMLValidator;
	
	@Before
	public void setUp() throws Exception
	{
		mtXMLValidator = (MTXMLValidator) this.applicationContext.getBean("mtXMLValidator");
	}
	@After
	public void tearDown(){}
	
	@Test
	public void testValidateXML() throws Exception
	{
		logger.info("testValidateXML() - 3");
		try{
			String xmlFileName = "xml/entitlement-create.xml";
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(xmlFileName);
			mtXMLValidator.validateXML(inputStream);
		}catch (Exception e) {
			logger.info("Error");
			e.printStackTrace();
		}
	}
	/**
	 * @return the mtXMLValidator
	 */
	public MTXMLValidator getMtXMLValidator() {
		return mtXMLValidator;
	}
	/**
	 * @param mtXMLValidator the mtXMLValidator to set
	 */
	public void setMtXMLValidator(MTXMLValidator mtXMLValidator) {
		this.mtXMLValidator = mtXMLValidator;
	}
}
