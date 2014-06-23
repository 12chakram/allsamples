package biz.neustar.dece.portal.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import biz.neustar.dece.ui.dvo.RatingsDVO;
import biz.neustar.dece.ui.service.UIUtilService;

public class InitServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(InitServlet.class);
	
	/**
	 * 	@param servletConfig
	 *  init method will be called on Build Startup.
	 *  Configuring staticContentBaseUrl variable to Application Scope
	 *  Uses isLinuxBuild, decesspConfigLocation, decessp.decestatic.url content-param values from web.xml

	 */
    public void init(ServletConfig servletConfig) throws ServletException {
		try {
//Load configProperties
			Properties configProperties = new Properties();
			String configLocation = null;
			try{//For Linux externalized file
				configLocation = servletConfig.getServletContext().getInitParameter("decesspConfigLocation");
				logger.info("decesspConfigLocation context param value : " + configLocation);
				configProperties.load(new FileInputStream(configLocation));
			} catch (FileNotFoundException fne) { //For Windows file within Application
				logger.error("No Conf.properties found at externalized location in linux, hence trying to load conf file from WEB-INF path");
				configLocation = servletConfig.getServletContext().getRealPath("/").concat("WEB-INF/config.properties").toString();
				configProperties.load(new FileInputStream(configLocation));				
			}
//set proxy for http urls    
            if(configProperties.getProperty("proxyHost")!=null && !configProperties.getProperty("proxyHost").isEmpty()) 
            {
            	servletConfig.getServletContext().setAttribute("http.proxyHost", configProperties.getProperty("proxyHost"));
            	if(configProperties.getProperty("proxyPort")!=null && !configProperties.getProperty("proxyPort").isEmpty()){
            		servletConfig.getServletContext().setAttribute("http.proxyPort", configProperties.getProperty("proxyPort"));
            		servletConfig.getServletContext().setAttribute("http.urlconnect.timeoutinmillis", configProperties.getProperty("urlConnectionTimeout"));
            	}
            }
//staticContentBaseUrl
			String staticContentBaseUrl =  configProperties.getProperty("decessp.decestatic.url");
			if(staticContentBaseUrl != null && !"".equals(staticContentBaseUrl)){
				servletConfig.getServletContext().setAttribute("staticContentBaseUrl", staticContentBaseUrl);
				logger.info("staticContentBaseUrl Configured value : " + staticContentBaseUrl);
			}else
				logger.error("STATIC_CONTENT_BASE_URL not Provided");

			
//homePageUrl
			String homePageUrl =  configProperties.getProperty("homePage.url");
			if(staticContentBaseUrl != null && !"".equals(homePageUrl)){
				servletConfig.getServletContext().setAttribute("homePageUrl", homePageUrl);
				logger.info("homePageUrl Configured value : " + homePageUrl);
			}else
				logger.error("STATIC_CONTENT_BASE_URL not Provided");
			
//loading for marketing site integration
			String portalGlobalUrl =  configProperties.getProperty("portalGlobal.url");
			if(portalGlobalUrl != null && !"".equals(portalGlobalUrl)){
				/*PortalGlobalUrls portalGlobal=new PortalGlobalUrls();
				portalGlobal.setHomePageURL(portalGlobalUrl);*/
				servletConfig.getServletContext().setAttribute("portalglobalUrl",portalGlobalUrl);
				logger.info("portal global Configured value : " + portalGlobalUrl);
			}else
				logger.error("Global_portal not Provided");
			
//Loading login page URL
			String portalGlobalLoginUrl =  configProperties.getProperty("portalGLobalLogin.url");
			if(portalGlobalLoginUrl != null && !"".equals(portalGlobalLoginUrl)){
				/*portalGlobalLoginUrl portalGlobalLoginUrl=new portalGlobalLoginUrl();
				portalGlobalLoginUrl.setHomePageURL(portalGlobalLoginUrl);*/
				servletConfig.getServletContext().setAttribute("portalGlobalLoginUrl",portalGlobalLoginUrl);
				logger.info("portal global Configured value : " + portalGlobalLoginUrl);
			}else
				logger.error("Global_portal not Provided");

			
//loading for noMedia Url
			String noMediaUrl=configProperties.getProperty("flash.noMediaUrl");
			if(noMediaUrl!=null && !"".equals(noMediaUrl))
			{
				servletConfig.getServletContext().setAttribute("noMediaUrl",noMediaUrl);
			logger.info("noMedia Flash Configured value : " + noMediaUrl);
			}
			else
				logger.error("no media_flash not Provided");
			
//loading for supporting countries
			String supportedCountry=configProperties.getProperty("supportedCountries");
			if(supportedCountry!=null && !"".equals(supportedCountry))
			{
				servletConfig.getServletContext().setAttribute("supportedCountry",supportedCountry);
				logger.info("supportedCountry Configured value : " + supportedCountry);
			}
			else
				logger.error("no supportedCountry not Provided");
				
//MarketingLogoutUrl
			String mktgLogoutUrl =  configProperties.getProperty("mktgLogout");
			if(mktgLogoutUrl != null && !"".equals(mktgLogoutUrl)){
				servletConfig.getServletContext().setAttribute("mktgLogoutUrl", mktgLogoutUrl);
				logger.info("MarketingLogoutUrl Configured value : " + mktgLogoutUrl);
			}else
				logger.error("MARKETING_URL not Provided");

//Consent Base URL
			String consentBaseUrl =  configProperties.getProperty("consentBaseUrl");
			if(consentBaseUrl != null && !"".equals(consentBaseUrl)){
				servletConfig.getServletContext().setAttribute("consentBaseUrl", consentBaseUrl);
				logger.info("consentBaseUrl Configured value : " + consentBaseUrl);
			}else
				logger.error("consentBase_URL not Provided");
//TOU_URL
			String TOU_URL =  configProperties.getProperty("TOU_URL");
			if(TOU_URL != null && !"".equals(TOU_URL)){
				servletConfig.getServletContext().setAttribute("TOU_URL", TOU_URL);
				logger.info("TOU_URL Configured value : " + TOU_URL);
			}else
				logger.error("TERMS_OF_USE_URL not Provided");

//PRIVACY_URL
			String PRIVACY_URL =  configProperties.getProperty("PRIVACY_URL");
			if(PRIVACY_URL != null && !"".equals(PRIVACY_URL)){
				servletConfig.getServletContext().setAttribute("PRIVACY_URL", PRIVACY_URL);
				logger.info("PRIVACY_URL Configured value : " + PRIVACY_URL);
			}else
				logger.error("PRIVACY_URL not Provided");
//COPPA_URL
			String COPPA_URL =  configProperties.getProperty("COPPA_URL");
			if(COPPA_URL != null && !"".equals(COPPA_URL)){
				servletConfig.getServletContext().setAttribute("COPPA_URL", COPPA_URL);
				logger.info("COPPA_URL Configured value : " + COPPA_URL);
			}else
				logger.error("COPPA_URL not Provided");
		
//PPANDCOPPA_URL
			String PPANDCOPPA_URL =  configProperties.getProperty("PPANDCOPPA_URL");
			if(PPANDCOPPA_URL != null && !"".equals(PPANDCOPPA_URL)){
				servletConfig.getServletContext().setAttribute("PPANDCOPPA_URL", PPANDCOPPA_URL);
				logger.info("PRIVACY AND COPPA_URL Configured value : " + PPANDCOPPA_URL);
			}else
				logger.error("PRIVACY AND COPPA_URL not Provided");
						
//mediaItemsPerFlashPage
			String mediaItemsPerFlashPage =  configProperties.getProperty("mediaItemsPerFlashPage");
			if(mediaItemsPerFlashPage != null && !"".equals(mediaItemsPerFlashPage)) {
				try {
					servletConfig.getServletContext().setAttribute("mediaItemsPerFlashPage", Integer.parseInt(mediaItemsPerFlashPage.trim()));
					logger.info("MediaItemsPerFlashPage Configured value : "+mediaItemsPerFlashPage);
				} catch (Exception e){
					logger.error("MediaItemsPerFlashPage Configured value not Valid : " + mediaItemsPerFlashPage);
				}
			} else
				logger.error("MediaItemsPerFlashPage not Provided");

			//media.pagesize.all			
			String mediaPageSizeAll =  configProperties.getProperty("media.pagesize.all");
			if(mediaPageSizeAll != null && !"".equals(mediaPageSizeAll)) {
				try {
					servletConfig.getServletContext().setAttribute("mediaPageSizeAll", Integer.parseInt(mediaPageSizeAll.trim()));
					logger.info("mediaPageSizeAll Configured value : "+mediaPageSizeAll);
				} catch (Exception e){
					logger.error("mediaPageSizeAll Configured value not Valid : " + mediaPageSizeAll);
				}
			} else
				logger.error("mediaPageSizeAll not Provided");
			
//contactUsMessageLimit
			String contactUsMessageLimit=  configProperties.getProperty("contactUsMessageLimit");
			if(contactUsMessageLimit != null && !"".equals(contactUsMessageLimit)) {
				try {
					servletConfig.getServletContext().setAttribute("contactUsMessageLimit", Integer.parseInt(contactUsMessageLimit.trim()));
					logger.info("contactUsMessageLimit Configured value : "+contactUsMessageLimit);
				} catch (Exception e){
					logger.error("contactUsMessageLimit Configured value not Valid : " + contactUsMessageLimit);
				}
			} else
				logger.error("contactUsMessageLimit not Provided");
			
//devices.enabled
			String devicesEnabled = configProperties.getProperty("devices.enabled");
			if(devicesEnabled != null && !"".equals(devicesEnabled)){
				try{
					servletConfig.getServletContext().setAttribute("devicesEnabled", devicesEnabled);
					logger.info("devicesEnabled Configured value : "+devicesEnabled);
				}
				catch (Exception e) {
					logger.error("devicesEnabled Configured value not valid : "+devicesEnabled);
				}
			}else
				logger.error("devicesEnabled not Provided");
			

// media.downloads.enabled
			
			String mediaDownloads = configProperties.getProperty("media.downloads.enabled");
			if(mediaDownloads != null && !"".equals(mediaDownloads)){
				try{
					servletConfig.getServletContext().setAttribute("mediaDownloads",mediaDownloads);
					logger.info("mediaDownloads Configured value : "+mediaDownloads);
				}
				catch (Exception e) {
					logger.error("mediaDownloads Configured value not valid : "+mediaDownloads);
				}
			}else
				logger.error("mediaDownloads not Provided");
			
			
//portal.localization
			String portalLocalization = configProperties.getProperty("portal.localization");
			if(portalLocalization!=null && !"".equals(portalLocalization)){
				try{
					servletConfig.getServletContext().setAttribute("portalLocalization", portalLocalization);
					logger.info("portalLocalization configured value: "+portalLocalization);
				}
				catch (Exception e) {
					logger.error("portalLocalization configured value not valid : "+portalLocalization);
				}
			}
			else
				logger.error("portalLocalization not Provided");
			
//usMovieRatingUrl
			String usMovieRatingsSite=configProperties.getProperty("usMovieRatingUrl");
			if(usMovieRatingsSite!=null && !"".equals(usMovieRatingsSite)){
				try{
					servletConfig.getServletContext().setAttribute("usMovieRatingsSite", usMovieRatingsSite);
					logger.info("US Movie Ratings site URL: "+usMovieRatingsSite);
				}
				catch(Exception ex){
					logger.error("usMovieRatingsSite value is not valid: "+ex);
				}
			}
			else{
				logger.error("usMovieRatingsSite not Provided");
			}
			
//usTVRatingUrl
			String usTvRatingsSite=configProperties.getProperty("usTVRatingUrl");
			if(usTvRatingsSite!=null && !"".equals(usTvRatingsSite)){
				try{
					servletConfig.getServletContext().setAttribute("usTvRatingsSite", usTvRatingsSite);
					logger.info("US TV Ratings site URL: "+usTvRatingsSite);
				}
				catch(Exception ex){
					logger.error("usTvRatingsSite is not valid"+ex);
				}
			}
			else{
				logger.error("usTvRatingsSite not Provided");
			}
			
//ukMovieRatingUrl
			String ukMovieRatingsSite=configProperties.getProperty("ukMovieRatingUrl");
			if(ukMovieRatingsSite!=null && !"".equals(ukMovieRatingsSite)){
				try{
					servletConfig.getServletContext().setAttribute("ukMovieRatingsSite", ukMovieRatingsSite);
					logger.info("UK Movie Ratings site URL: "+ukMovieRatingsSite);
				}
				catch(Exception ex){
					logger.error("ukMovieRatingsSite is not valid"+ex);
				}
			}
			else{
				logger.error("ukMovieRatingsSite is not provided");
			}
			
			
//ParentalControlsRatings			
			UIUtilService uiUtilService=ServiceLocator.getUiUtilService();
			HashMap<String, List<RatingsDVO>> ratingsMap=uiUtilService.getRatings();
			if(null!=ratingsMap && ratingsMap.size()>0){
				try{
					logger.info("parental control ratings are setting to the context");
					servletConfig.getServletContext().setAttribute("parentalControlRatings", ratingsMap);
				}catch(Exception ex){
					logger.error("error getting while setting parental control ratings to the context\n");
					ex.printStackTrace();
				}
			}else{
				logger.error("parental controls ratings is not provided");
			}
			
			
			
			
//Recaptcha Public key
			String reCaptchaPublicKey = configProperties.getProperty("recaptcha.public.key");
			if(reCaptchaPublicKey != null && !"".equals(reCaptchaPublicKey)){
				try{
					servletConfig.getServletContext().setAttribute("reCaptchaPublicKey",reCaptchaPublicKey);
					logger.info("reCaptchaPublicKey value is: "+reCaptchaPublicKey);
				}
				catch (Exception e) {
					logger.error("reCaptchaPublicKey Configured value not valid  : "+reCaptchaPublicKey);
				}
			}else
				logger.error("reCaptchaPublicKey is not provided");
//Recaptcha private key
			String reCaptchaPrivateKey = configProperties.getProperty("recaptcha.private.key");
			if(reCaptchaPrivateKey != null && !"".equals(reCaptchaPrivateKey)){
				try{
					servletConfig.getServletContext().setAttribute("reCaptchaPrivateKey",reCaptchaPrivateKey);
					logger.info("reCaptchaPrivateKey value is: "+reCaptchaPrivateKey);
				}
				catch (Exception e) {
					logger.error("reCaptchaPrivateKey Configured value not valid: "+reCaptchaPrivateKey);
				}
			}else
				logger.error("reCaptchaPrivateKey is not Provided");	
			
			
			if(configProperties.getProperty("shostLogin")!=null && !configProperties.getProperty("shostLogin").isEmpty()) 
            {
            	servletConfig.getServletContext().setAttribute("shostLogin", configProperties.getProperty("shostLogin"));
            	
            }
			
			 if(configProperties.getProperty("shostUrl")!=null && !configProperties.getProperty("shostUrl").isEmpty()) 
	            {
	            	servletConfig.getServletContext().setAttribute("shostUrl", configProperties.getProperty("shostUrl"));
	            	
	            }
			 
			 if(configProperties.getProperty("samlPrivateKey")!=null && !configProperties.getProperty("samlPrivateKey").isEmpty()) 
	            {
	            	servletConfig.getServletContext().setAttribute("samlPrivateKey", configProperties.getProperty("samlPrivateKey"));
	            	
	            }
			 
			 if(configProperties.getProperty("samlPublicKey")!=null && !configProperties.getProperty("samlPublicKey").isEmpty()) 
	            {
	            	servletConfig.getServletContext().setAttribute("samlPublicKey", configProperties.getProperty("samlPublicKey"));
	            	
	            }
			 
			 if(configProperties.getProperty("samlPortalMetadata")!=null && !configProperties.getProperty("samlPortalMetadata").isEmpty()) 
	            {
	            	servletConfig.getServletContext().setAttribute("samlPortalMetadata", configProperties.getProperty("samlPortalMetadata"));
	            	
	            }
			
		}catch (Exception e) {
				e.printStackTrace();
		}
		
	}

//    /**
//     * @param servletConfig
//     * Configuring coppaEnabled variable to Application Scope
//     * Called from init method
//     */
//    private void isCoppaEnabled(ServletConfig servletConfig){
//    	try {			
//				servletConfig.getServletContext().setAttribute("coppaEnabled", (ServiceLocator.getUiUtilService().isCoppaEnabled()?"coppaEnabled":"coppaDisabled"));
//				logger.info("Coppa set");
//
//		} catch (UIDeceException e) {
//			e.printStackTrace();
//		}
//    }
}
