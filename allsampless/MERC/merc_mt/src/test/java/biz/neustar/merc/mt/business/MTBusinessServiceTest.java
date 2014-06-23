/**
 * 
 */
package biz.neustar.merc.mt.business;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.annotation.Resource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.ContextConfiguration;

import biz.neustar.merc.mt.exceptions.MTException;

import biz.neustar.merc.mt.service.MTBusinessService;
import biz.neustar.merc.mt.vo.MTRequest;
import biz.neustar.merc.mt.vo.MercResponse;

/**
 * @author pkuninti
 * 
 */

@ContextConfiguration(locations = { "classpath:src/main/resources/mt-business-application-context.xml" })
public class MTBusinessServiceTest extends TestCase {

	@Resource
	private ApplicationContext	applicationContext = new FileSystemXmlApplicationContext(new String[] { "src/main/resources/mt-business-application-context.xml"});
	
	@Resource
	private 	MTBusinessService mtBusinessService;
	
	@Before
	public void setUp() throws Exception
	{
		mtBusinessService = (MTBusinessService) this.applicationContext.getBean("mtBusinessService");
	}
	
	/*@Test
	public void testMTBusinessService() throws MTException 
	{
		MTRequest request = new MTRequest();
		request.setMethod(HttpMethod.POST.toString());
		request.setUrlPath("/User/PurchaseTitles");
		request.setUserId("urn:nsr:merc:userid:org:nsr:merc:46b11ada-4411-4eef-8df3-c96ba7ab8d22");
		request.setAccountId("urn:nsr:merc:accountid:org:nsr:merc:42fbf43a-cc97-4fd2-8b2f-6071e754953c");
		request.addHeader("RoleName","urn:nsr:merc:role:storefront");
		try {
			request.setPayload(convertStreamToString(this.getClass().getClassLoader().getResourceAsStream("xml/purchase-titles.xml")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//request.setPayload("<?xml version=\"1.0\" encoding=\"UTF-8\"?><merc-basic:Entitlement ALID=\"urn:nsr:merc:accountid:alid123\" ContentID=\"urn:nsr:merc::md:contentid:test\" ThirdPartyID=\"urn:nsr:merc:thirdpartyid:123\" xmlns:md=\"http://www.movielabs.com/schema/md/v1.07/md\" xmlns:merc-basic=\"http://www.neustar.biz/schema/2012/01/merc-basic\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.neustar.biz/schema/2012/01/merc-basic merc-basic.xsd \"> <merc-basic:EntitlementBasic>    <merc-basic:EntitlementProfiles/>  </merc-basic:EntitlementBasic></merc-basic:Entitlement>");
		//request.setPayload("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><catalog> <cd> <title>Empire Burlesque</title> <artist>Bob Dylan</artist> <country>USA</country>   <company>Columbia</company>    <price>10.90</price>    <year>1985</year>  </cd><cd> <title>Praveen Kuninti</title> <artist>Neustar</artist> <country>USA</country>   <company>Columbia</company>    <price>10.90</price>    <year>1985</year>  </cd></catalog> ");
		MercResponse response = mtBusinessService.processRequest(request);
		System.out.println("\n\n\n\ntestMTBusinessService() MercResponse="+response+"\n\n\n\n");
	}*/
	
	/*@Test
	public void testAccountCreate() throws MTException, Exception 
	{
		
		MTRequest request = new MTRequest();
		request.setMethod(HttpMethod.POST.toString());
		request.setUrlPath("/Users");
		request.setUserId("urn:nsr:merc:userid:org:nsr:merc:46b11ada-4411-4eef-8df3-c96ba7ab8d22");
		request.setAccountId("urn:nsr:merc:accountid:org:nsr:merc:42fbf43a-cc97-4fd2-8b2f-6071e754953c");
		request.addHeader("RoleName","urn:nsr:merc:role:storefront");
		try {
			String user = null;
			//user= createXML();
			request.setPayload(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//request.setPayload("<?xml version=\"1.0\" encoding=\"UTF-8\"?><merc-basic:Entitlement ALID=\"urn:nsr:merc:accountid:alid123\" ContentID=\"urn:nsr:merc::md:contentid:test\" ThirdPartyID=\"urn:nsr:merc:thirdpartyid:123\" xmlns:md=\"http://www.movielabs.com/schema/md/v1.07/md\" xmlns:merc-basic=\"http://www.neustar.biz/schema/2012/01/merc-basic\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.neustar.biz/schema/2012/01/merc-basic merc-basic.xsd \"> <merc-basic:EntitlementBasic>    <merc-basic:EntitlementProfiles/>  </merc-basic:EntitlementBasic></merc-basic:Entitlement>");
		//request.setPayload("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><catalog> <cd> <title>Empire Burlesque</title> <artist>Bob Dylan</artist> <country>USA</country>   <company>Columbia</company>    <price>10.90</price>    <year>1985</year>  </cd><cd> <title>Praveen Kuninti</title> <artist>Neustar</artist> <country>USA</country>   <company>Columbia</company>    <price>10.90</price>    <year>1985</year>  </cd></catalog> ");
		MercResponse response = mtBusinessService.processRequest(request);
		System.out.println("\n\n\n\ntestMTBusinessService() MercResponse="+response+"\n\n\n\n");
		
	}*/
	
	@Test
	public void testgetTitleOverview() throws MTException, Exception 
	{
		
		MTRequest request = new MTRequest();
		request.setMethod(HttpMethod.GET.toString());
		request.setPathVariableValue("JUnitPathVariable123");
		request.setUrlPath("/Title/{id}/Overview");
		/*request.setUserId("urn:nsr:merc:userid:org:nsr:merc:46b11ada-4411-4eef-8df3-c96ba7ab8d22");
		request.setAccountId("urn:nsr:merc:accountid:org:nsr:merc:42fbf43a-cc97-4fd2-8b2f-6071e754953c");
		request.addHeader("RoleName","urn:nsr:merc:role:storefront");*/
		MercResponse response = mtBusinessService.processRequest(request, "/Title/{PathVariable}/Overview_GET");
		System.out.println("\n\n\n\ntestMTBusinessService() MercResponse="+response+"\n\n\n\n");
		
	}
	
	/*@Test
	public void testStreamCreate() throws MTException 
	{
		MTRequest request = new MTRequest();
		request.setMethod(HttpMethod.POST.toString());
		request.setUrlPath("/User/Streams");
		request.setUserId("urn:nsr:merc:userid:org:nsr:merc:46b11ada-4411-4eef-8df3-c96ba7ab8d22");
		request.setAccountId("urn:nsr:merc:accountid:org:nsr:merc:42fbf43a-cc97-4fd2-8b2f-6071e754953c");
		request.addHeader("RoleName","urn:nsr:merc:role:storefront");
		try {
			request.setPayload(convertStreamToString(this.getClass().getClassLoader().getResourceAsStream("xml/Stream.xml")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//request.setPayload("<?xml version=\"1.0\" encoding=\"UTF-8\"?><merc-basic:Entitlement ALID=\"urn:nsr:merc:accountid:alid123\" ContentID=\"urn:nsr:merc::md:contentid:test\" ThirdPartyID=\"urn:nsr:merc:thirdpartyid:123\" xmlns:md=\"http://www.movielabs.com/schema/md/v1.07/md\" xmlns:merc-basic=\"http://www.neustar.biz/schema/2012/01/merc-basic\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.neustar.biz/schema/2012/01/merc-basic merc-basic.xsd \"> <merc-basic:EntitlementBasic>    <merc-basic:EntitlementProfiles/>  </merc-basic:EntitlementBasic></merc-basic:Entitlement>");
		//request.setPayload("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><catalog> <cd> <title>Empire Burlesque</title> <artist>Bob Dylan</artist> <country>USA</country>   <company>Columbia</company>    <price>10.90</price>    <year>1985</year>  </cd><cd> <title>Praveen Kuninti</title> <artist>Neustar</artist> <country>USA</country>   <company>Columbia</company>    <price>10.90</price>    <year>1985</year>  </cd></catalog> ");
		MercResponse response = mtBusinessService.processRequest(request);
	//	assertTrue(response.getStatusCode()==201);
		System.out.println("\n\n\n\ntestMTBusinessService() MercResponse="+response+"\n\n\n\n");
	}
	
	
	@Test
	public void testUserGet() throws MTException 
	{
		MTRequest request = new MTRequest();
		request.setMethod(HttpMethod.GET.toString());
		request.setUrlPath("/User");
		request.setUserId("urn:nsr:merc:userid:org:nsr:merc:46b11ada-4411-4eef-8df3-c96ba7ab8d22");
		request.setAccountId("urn:nsr:merc:accountid:org:nsr:merc:42fbf43a-cc97-4fd2-8b2f-6071e754953c");
		request.addHeader("RoleName","urn:nsr:merc:role:storefront");
		MercResponse response = mtBusinessService.processRequest(request);
		System.out.println("\n\n\n\ntestMTBusinessService() MercResponse="+response+"\n\n\n\n");
	}*/
	
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

	/*private String createXML_usingJiBX() throws Exception {
		
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 		
		System.out.println("Entered create XML");
		UserDataType usr = new UserDataType();
		MDPersonNameType name = new MDPersonNameType();
		Country ctry = new Country();
		UserContactInfoType contact = new UserContactInfoType();
		ConfirmedCommunicationEndpointType endPointType = new ConfirmedCommunicationEndpointType();
		DateOfBirthType dob = new DateOfBirthType();
		UserCredentialsType credentials = new UserCredentialsType();
		
		
		name.setGivenName("Krithika");
		name.setSurName("Balamurugan");
		ctry.setCountry("US");
		endPointType.setConfirmationEndpoint("primaryEmail");
		endPointType.setValue("abc.efgh@email.com");
		//dob.setDate((Date)formatter.parse("1990-12-07"));
		dob.setMeetsAgeOfMajority(false);
		credentials.setPassword("Password1");
		credentials.setUsername("Myusername6");
		
		
		contact.setPrimaryEmail(endPointType);
		
		usr.setName(name);
		usr.setCountry(ctry);
		usr.setContactInfo(contact);
		usr.setDateOfBirth(dob);
		usr.setCredentials(credentials);
		System.out.println("Obbjects created");
		try
		{
		IBindingFactory bfact = BindingDirectory.getFactory(biz.neustar.merc.mt.jUnit.jibx.UserDataType.class);
		System.out.println("Jibx binding factory created");
		IMarshallingContext mctx = bfact.createMarshallingContext();
		ByteArrayOutputStream out = null;
		mctx.setOutput(out, null);
		System.out.println("Jibx output stream set");
        mctx.marshalDocument(usr);
        out.toString("UTF-8");
        System.out.println("JiBx Marshelled: "+out.toString("UTF-8"));
		}
		catch (Exception e){}
        
		return null;
	}*/
	

	/*
	
	private String createXML() throws Exception {
		
		
		JAXBContext jc = null;
		Unmarshaller um = null;
	
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 	
		GregorianCalendar gCal = new GregorianCalendar(1990,Calendar.JULY,20);
		XMLGregorianCalendar xmlgc = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCal);
		xmlgc.setTime(DatatypeConstants.FIELD_UNDEFINED, DatatypeConstants.FIELD_UNDEFINED, DatatypeConstants.FIELD_UNDEFINED);
		xmlgc.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
		UserDataType usr = new UserDataType();
		PersonNameType name = new PersonNameType();
		UserContactInfoType contact = new UserContactInfoType();
		ConfirmedCommunicationEndpointType endPointType = new ConfirmedCommunicationEndpointType();
		DateOfBirthType dob = new DateOfBirthType();
		UserCredentialsType credentials = new UserCredentialsType();
		
		
		name.setGivenName("Krithika");
		name.setSurName("Balamurugan");
		endPointType.setConfirmationEndpoint("primaryEmail");
		endPointType.setValue("abc.efgh@email.com");
		//dob.setDate((Date)formatter.parse("1990-12-07"));
		dob.setValue(xmlgc);
		dob.setMeetsAgeOfMajority(false);
		credentials.setPassword("Password1");
		credentials.setUsername("Myusername6");			
		contact.setPrimaryEmail(endPointType);			
		usr.setName(name);
		usr.setCountry("US");
		usr.setContactInfo(contact);
		usr.setDateOfBirth(dob);
		usr.setCredentials(credentials);			
		
		ObjectFactory obj = new ObjectFactory();
		JAXBElement<UserDataType> user = obj.createUser(usr);
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		jc = JAXBContext.newInstance("biz.neustar.merc.mt.jUnit.jaxb");
		Marshaller m = jc.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		m.marshal(user, out);
		System.out.println("JAXB Marshalled: "+out.toString("UTF-8"));		
		return out.toString("UTF-8");
		
	}
	
	*/
	
	/**
	 * @return the mtBusinessService
	 */
	public MTBusinessService getMtBusinessService() {
		return mtBusinessService;
	}

	/**
	 * @param mtBusinessService the mtBusinessService to set
	 */
	public void setMtBusinessService(MTBusinessService mtBusinessService) {
		this.mtBusinessService = mtBusinessService;
	}
	
	

}
