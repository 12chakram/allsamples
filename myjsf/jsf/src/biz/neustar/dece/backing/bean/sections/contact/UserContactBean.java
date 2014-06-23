/**
 * Created on July 08,2010.
 * Copyright (c) 2011 Compugain, Inc. All Rights Reserved. 
 * UserContactBean.java
 */
package biz.neustar.dece.backing.bean.sections.contact;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import biz.neustar.dece.custom.validation.CommonValidations;
import biz.neustar.dece.portal.utils.APIErrorMapUtil;
import biz.neustar.dece.portal.utils.ExceptionUtils;
import biz.neustar.dece.portal.utils.ServiceLocator;
import biz.neustar.dece.portal.utils.SessionUtils;
import biz.neustar.dece.ui.UIDeceException;
import biz.neustar.dece.ui.dvo.ContactUsDVO;
import biz.neustar.dece.ui.dvo.ContactUsTopicDVO;
import biz.neustar.dece.ui.dvo.UserAuthDVO;
import biz.neustar.dece.ui.service.UIUtilService;
/**
 * The <code>UserContactBean.java</code> class encapsulates objects defined
 * for DECE.
 * @author Compugain.
 * @version $Revision: 1.4 $ $Date: 2012/06/12 14:34:55 $
 */
@ManagedBean(name="userContactBean")
@RequestScoped
public class UserContactBean {

private static final Logger logger = Logger.getLogger(UserContactBean.class);	
private String topicValue="";
private LinkedHashMap<String,Long> topicDetailsList=null;
private String contactMessage;

private String contactEmailAddress;
private String emailErrorMesg;
private HashMap<String,String> errorValueMap;
private List<ContactUsTopicDVO> uiContactTopics;
private String forColor = "";
SessionUtils sessionUtilsObj = new SessionUtils();
FacesContext context = FacesContext.getCurrentInstance();
ResourceBundle errorBundle = ResourceBundle.getBundle("biz.neustar.dece.decessp.i18n.errorTxts", context.getViewRoot().getLocale());
ResourceBundle labelBundle = ResourceBundle.getBundle("biz.neustar.dece.decessp.i18n.labelTxts", context.getViewRoot().getLocale());
HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);


public UserContactBean(){
	errorValueMap=new HashMap<String,String>();
	HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
	if(request.getParameter("topicValue") !=null)
	{
		setTopicValue(request.getParameter("topicValue"));
	}
}

public String getForColor() {
	return forColor;
}

public void setForColor(String forColor) {
	this.forColor = forColor;
}


public String getEmailErrorMesg() {
	return emailErrorMesg;
}
public void setEmailErrorMesg(String emailErrorMesg) {
	this.emailErrorMesg = emailErrorMesg;
}
public HashMap<String, String> getErrorValueMap() {
	return errorValueMap;
}
public void setErrorValueMap(HashMap<String, String> errorValueMap) {
	this.errorValueMap = errorValueMap;
}
/**
 * Jan 18,2011.
 * Show default email address.
 * @return String
 */
public String getContactEmailAddress() {	
	if((contactEmailAddress==null ) && session != null && session.getAttribute("User") != null){
		try{
			//Added to track the Previous and next page in UserAuthDVO
			UserAuthDVO  auth = sessionUtilsObj.getUserAuth();
			auth.setPreviousWebPage(auth.getCurrentWebPage());
			auth.setCurrentWebPage("AUTH_CONTACTUS");
		return ServiceLocator.getUiUserService().getUserProfile(auth, new SessionUtils().getUserId()).getEmail();
		}catch(UIDeceException ex){
		 logger.error("Failed while retrieving contact us email address.");	
		 ExceptionUtils.processUIDECEException("CONTACT_US_EMAIL_GET", "ClassName:UserContactBean:getContactEmailAddress", ex, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, errorBundle);
		}
	}	
	return contactEmailAddress;
}
public void setContactEmailAddress(String contactEmailAddress) {
	this.contactEmailAddress = contactEmailAddress.trim();
}
public String getContactMessage() {
	return contactMessage;
}
public void setContactMessage(String contactMessage) {
	this.contactMessage = contactMessage.trim();
}
public String getTopicValue() {
	return topicValue;
}
public void setTopicValue(String topicValue) {
	this.topicValue = topicValue;
}
/**
 * Jan 18,2011.
 * Display topic details.
 * @return LinkedHashMap<String,Long>
 */
public LinkedHashMap<String,Long> getTopicDetailsList() {
	if(null==topicDetailsList || topicDetailsList.size()==0){
		topicDetailsList = new LinkedHashMap<String,Long>();
		UIUtilService uiUtilServiceObj=ServiceLocator.getUiUtilService();
		try {
			List<ContactUsTopicDVO> contactUsTopicDVOList=uiUtilServiceObj.getContactUsTopics();
			logger.info("Successfully retrieved contact us topic details. "+contactUsTopicDVOList.size());
			for (Iterator<ContactUsTopicDVO> iterator = contactUsTopicDVOList.iterator(); iterator.hasNext();) {
				ContactUsTopicDVO contactUsTopicDVO = (ContactUsTopicDVO) iterator.next();
				topicDetailsList.put(contactUsTopicDVO.getTopicDescription(),contactUsTopicDVO.getTopicId());				
			}			
		} catch (UIDeceException exe) {	
			logger.error("Failed while retrieving contact us topic details.");
			ExceptionUtils.processUIDECEException("CONTACTUS_GET_TOPIC","ClassName:UserContactBean:getTopicDetailsList",exe, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, errorBundle);
	     }
	}
	return topicDetailsList;
}
public void setTopicDetailsList(LinkedHashMap<String,Long> topicDetailsList) {
	this.topicDetailsList = topicDetailsList;
}
/**
 * July 08,2010.
 * Method for insert contact us details in DB.
 * @return String
 */
 public String contactUsSubmitAction() {
	 int messageLimit=4000;
	if(contactUsValidation()){
		ContactUsDVO contactUsDVOObj=new ContactUsDVO();
		contactUsDVOObj.setMessage(this.contactMessage);
		contactUsDVOObj.setEmailMeFlag("Y");
		contactUsDVOObj.setTopicId(new Long(this.topicValue));		
		contactUsDVOObj.setUserEmailAddress(this.contactEmailAddress);
		try {
			UIUtilService uiUtilServiceObj=ServiceLocator.getUiUtilService();
			if(null!=context.getExternalContext().getApplicationMap().get("contactUsMessageLimit"))
				messageLimit=Integer.parseInt(context.getExternalContext().getApplicationMap().get("contactUsMessageLimit").toString());
			if(contactUsDVOObj.getMessage().length()<=messageLimit)
			{
				uiUtilServiceObj.contactUs(contactUsDVOObj);	
				logger.info("Succcessfully inserted contact us details.");	
				errorValueMap.put("contactUsSuccessMsg",labelBundle.getString("contactUsSuccessMsg"));
				contactMessage="";
				if(session != null && session.getAttribute("User") == null)
					contactEmailAddress="";
			}
			else
			{
				errorValueMap.put("contactMessageError",labelBundle.getString("msgLengthError"));
			}
			
		} catch (UIDeceException e) {
			logger.info("Failed contactUs method insertion.");
			ExceptionUtils.processUIDECEException("CONTACTUS_SUBMIT","ClassName:UserContactBean:contactUsSubmitAction",e, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, errorBundle);
		}		
	}
	return null;
 }
/**
 * July 08,2010.
 * Contact us validation method.
 * @return boolean
 */
private boolean contactUsValidation(){
	boolean flagValue = true;
	if(contactMessage == null || "".equals(contactMessage)){
		errorValueMap.put("contactMessageError",  errorBundle.getString("messageReq"));
		flagValue = false;
	}
	if(topicValue == null || "".equals(topicValue)){
		errorValueMap.put("topicValueError", errorBundle.getString("topicReq"));
		flagValue = false;
	}
	if (contactEmailAddress != null && !"".equals(contactEmailAddress)) {
		emailErrorMesg = "";
		emailErrorMesg = CommonValidations.emailValidation(contactEmailAddress.toLowerCase());
		if(!"".equals(emailErrorMesg))
			flagValue = false;
	}else{
		emailErrorMesg=errorBundle.getString("emailReq");
		flagValue = false;
	}
  return flagValue;
 }


public void setUiContactTopics(List<ContactUsTopicDVO> uiContactTopics) {
	this.uiContactTopics = uiContactTopics;
}

private void forListOfTopics()
{
	UIUtilService uiUtilServiceObj=ServiceLocator.getUiUtilService();
	try
	{
		uiContactTopics=uiUtilServiceObj.getContactUsTopics();
	}catch(UIDeceException e)
	{
		ExceptionUtils.processUIDECEException("CONTACTUS_GETUICONTACTOPICS","ClassName:UserContactBean:getUicontactTopics",e, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, errorBundle);
	}
}
public List<ContactUsTopicDVO> getUiContactTopics() {
	if(null==uiContactTopics)
		forListOfTopics();
	return uiContactTopics;
}
}
