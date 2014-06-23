/*
 * Copyright (c) 2011 Compugain, Inc. All Rights Reserved. 
 * CommonValidations.java
 */
package biz.neustar.dece.custom.validation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.context.FacesContext;

import org.apache.commons.validator.GenericValidator;
import org.apache.log4j.Logger;

import biz.neustar.dece.portal.utils.ServiceLocator;
import biz.neustar.dece.portal.utils.SessionUtils;
import biz.neustar.dece.ui.UIDeceException;
import biz.neustar.dece.ui.dvo.CountryRefDataDVO;
import biz.neustar.dece.ui.dvo.UIListDVO;
import biz.neustar.dece.ui.dvo.UserDVO;
/**
 * The <code>CommonValidations.java</code> class encapsulates objects defined
 * for DECE.
 * @author Compugain.
 * @version $Revision: 1.9 $ $Date: 2012/06/12 14:34:55 $
 */
public class CommonValidations {
 	public static FacesContext appContext=FacesContext.getCurrentInstance();
 	static SessionUtils sessionUtils=new SessionUtils();
 	static ResourceBundle bundle = sessionUtils.getErrorTxtsBundle();
 	static ResourceBundle labelsBundle=sessionUtils.getLabelsTxtsBundle();
 	private static final Logger logHandler = Logger.getLogger(CommonValidations.class);
 	/**
 	 * Jan 19,2011.
 	 * Get secret question details.
 	 * @param requestedFromBean
 	 * @return
 	 * @throws UIDeceException
 	 */
    public static LinkedHashMap<String, Long> getSecretQuestionMap(String requestedFromBean) throws UIDeceException {
		LinkedHashMap<String, Long> secretQuestionMap = null;
		String langCode;
		logHandler.info(requestedFromBean + " - Initiating loading of Secret Questions");		
		if(null==secretQuestionMap || secretQuestionMap.size()==0){
			secretQuestionMap=new LinkedHashMap<String, Long>(); 
			try {
					langCode=sessionUtils.getLanguageCode();					
					//currently since language code is set according to media method hard coding the value again
					langCode="en";					
					secretQuestionMap.put(labelsBundle.getString("secretQuestionDefaultDropDownValue"),0L);					
					//now retrieve question from DB					
					UIListDVO uiListDVOObject = ServiceLocator.getUiUtilService().getSecretQuestions(langCode);					
					//get the Questions and QuestionIDs Lists
					List<String> secQuestionsLabelList = uiListDVOObject.getListLabels();
					List<Long> secQuestionsValueList = uiListDVOObject.getListIds();					
					//iterate through list and populate Map
					int size = secQuestionsLabelList.size();
					if( null != secQuestionsLabelList && null != secQuestionsValueList){	
					  for (int i = 0; i < size; i++) 
						secretQuestionMap.put(secQuestionsLabelList.get(i),secQuestionsValueList.get(i));
					}					
					logHandler.info(requestedFromBean + " - Loaded Secret Questions");
				return secretQuestionMap;
			}
			catch(UIDeceException ex){
				//UIDECEException is re-thrown from here so that in the actual page from where this was invoked with process the exception and display message to user
				throw ex;
			}
		}
		return null;
	}
   /**
    * Jan 19,2011.
    * Get country details.
    * @param requestedFromBean
    * @return
    * @throws UIDeceException
    */
	public static LinkedHashMap<String, String> getCountryMap(String requestedFromBean) throws UIDeceException {
		CountryRefDataDVO itemCountry;
		LinkedHashMap<String, String> countryMap = new LinkedHashMap<String, String>();
		//Add the initial default value into the map 
		logHandler.info(requestedFromBean + " - Initiating loading of Country Values");
		try {
				logHandler.info("Starting: Loading of Countries");
				// Add message item into map -- Select a Country --
				//countryMap.put(labelsBundle.getString("countryDefaultDropDownValue"), "");				
				List<CountryRefDataDVO> dbCountryList = ServiceLocator.getUiUtilService().getCountryList();
				Iterator<CountryRefDataDVO> itr= dbCountryList.iterator();				
				while(itr.hasNext()){
					itemCountry=itr.next();	
					logHandler.info("Country Values: "+itemCountry.getCountry_code()+ itemCountry.getCountry());
					countryMap.put(itemCountry.getCountry_code(), itemCountry.getCountry());		
				}
				logHandler.info("Completed: Loading of Countries");
			}catch (UIDeceException ex) {
				//UIDECEException is re-thrown from here so that in the actual page from where this was invoked with process the exception and display message to user
				throw ex;
		}
	return countryMap;
	}
	/**
	 * Jan 19,2011.
	 * Email validation method.
	 * @param email
	 * @return
	 */
  public static String emailValidation(String email){ 
	if (email != null && !"".equals(email))	{	
		//Pattern p=Pattern.compile("[a-z][a-z0-9]*([_\\-.]?[a-z0-9]+){0,}+@[a-z0-9]([_-]?[a-z0-9]){0,}+((.[a-z]{2,}.[a-z]{2,})|(.[a-z]{2,})|(.[a-z]{3,}))");        
		//Matcher m=p.matcher(email.toString().trim().toLowerCase());		
		return (GenericValidator.isEmail(email)? "": bundle.getString("email_not_matches"));
	   }else return bundle.getString("emailReq");
   }
 /**
  * Jan 19,2011.
  * Household name validation.
  * @param houseHoldName
  * @return
  */
  public static String houseHoldNameValidation(String houseHoldName){	
	if(houseHoldName == null || "".equals(houseHoldName))
		return bundle.getString("houseHoldName_not_entered");
     /*if(houseHoldName!=null && !"".equals(houseHoldName)){
	  // Pattern p=Pattern.compile(".*(?=.{1,50})(^[a-zA-Z0-9' ']+$)");
    	Pattern p=Pattern.compile("[a-zA-Z0-9' ']{1,50}");
		Matcher m=p.matcher(houseHoldName.toString().trim());
		return ((m.matches()? "": bundle.getString("houseHoldName_not_matches")));
		 if(m.matches()){ return "";               
		   }else{ return bundle.getString("houseHoldName_not_matches");	}
	  }
	 else{
		return bundle.getString("houseHoldName_not_entered");
    	}*/
	return "";
  }
 /**
  * Jan 19,2011.
  * Password validation.
  * @param password
  * @return
  */
  public static String passwordValidation(String password){
    if(password != null && !"".equals(password)){
    	if(password.length()<6)
    		return bundle.getString("insufficientPassword");
    	else if(password.length()>64)
    		return bundle.getString("exceededPasswordLength");
    	if(password.startsWith(" ") || password.endsWith(" ") || password.contains(" "))
    		return bundle.getString("password_not_matches");

    	//added to handle Extended ASCII characters
    	//password=CommonValidations.hexEquiOfPassword(password);
    	/*
    	//Pattern p=Pattern.compile("[A-Za-z0-9~!\\@#\\$%\\&\\*\\+\\-\\.[^\\p{ASCII}^\\^]]*");
    	Pattern p=Pattern.compile("[A-Za-z0-9~!\\\\@#\\(\\):\\;\\<\\=\\>\\?\\[\\]\\^\\_\\`\\'\\|\\}\\$%\\&\\*\\+\\-\\.[^\\p{ASCII}^\\^]]*");
    	Matcher m =p.matcher(password.toString());
    	Pattern ps=Pattern.compile("([!@#$%&*+~_.]*)(^[a-zA-Z0-9!@#$%&*+~_.]*$)");
    	Matcher ms=ps.matcher(password.toString().trim());
    	//return ((m.matches()? (ms.matches()?"":bundle.getString("password_not_matches")): bundle.getString("password_not_matches")));
    	return ((m.matches()? "" : bundle.getString("password_not_matches")));*/
             /*if(m.matches()){ return "" ; }
             else { return bundle.getString("password_not_matches"); }*/
    	return "";
      }else{
    	return bundle.getString("pass_not_entered");
      }
   }
 /**
  * Jan 19,2011.
  * Password validation.
  * @param userName
  * @param password
  * @return
  */
  public static String passwordValidation(String userName, String password){
	     if(password != null && !"".equals(password)){    	
	    	Pattern p = Pattern.compile("[a-zA-Z0-9!@#$%&*+~_.&&[^[("+ userName.substring(1,5) +")]]]{8,32}");    	
	    	Pattern p1 = Pattern.compile("[a-zA-Z0-9!@#$%&*+~_.]{8,64}");
	    	Matcher m =p.matcher(password.toString().trim());
	    	/*Pattern ps=Pattern.compile("([!@#$%&*+~_.]*)(^[a-zA-Z0-9!@#$%&*+~_.]*$)");
	    	Matcher ms=ps.matcher(password.toString().trim());*/    	
	    	m =p1.matcher(password.toString().trim());    	
	    	return ((m.matches()? "":bundle.getString("password_not_matches")));
	         /*if(m.matches()){ return "" ; }
	         else { return bundle.getString("password_not_matches"); }*/
	      }else{
	    	return bundle.getString("pass_not_entered");
	     }
	   }
  
 
  /**
   * March 07, 2012
   * Password encoding to hexadecimal
   * @param password
   * @return hexPassword
   */
  
  public static String hexEquiOfPassword(String password)
  {
	/*StringBuffer sb1 = new StringBuffer();
	 * StringBuffer sb = new StringBuffer();
	String s;
	for(int i=0;i<password.length();i++){
		String subPassword=Character.toString(password.charAt(i));
		subPassword=Hex.encodeHexString(subPassword.getBytes());
		sb.append("0x"+subPassword);
		int intVal = Integer.parseInt(subPassword, 16);
		char c = (char)intVal;
		sb1.append(c);
		//s+=c;
	}
	logHandler.info("hexVal: "+sb.toString());
	logHandler.info("convertedStr: "+sb1.toString());
	s=sb1.toString();*/
	logHandler.debug("Password: "+password);
	return password;
  }
  /**
   * Jan 19,2011.
   * Password validation. 
   * @param usrDVO
   * @param password
   * @return
   */
  public static String passwordValidation(UserDVO usrDVO, String password){
	if(password != null && !"".equals(password)){    	
		//Check for password criteria       	
		String pwdMatchesCriteria = passwordValidation(password);    	
    	//Check for user info in password 
    	List<String> pwdSubsets = new ArrayList<String>();
    	int startIdx=0;
    	int pwdLen = password.length();
    	// break the password into strings of length 5 from the beginning 
    	while((pwdLen-startIdx)!=0){
    		if((startIdx+5)<=pwdLen){
    		String pwdExtracted = password.substring(startIdx, startIdx+5);
    		pwdSubsets.add(pwdExtracted);
    		++startIdx;
    		}else
    			++startIdx;
    	}
    	//Read the user name and user display name to variables
    	String usrName = usrDVO.getUserName();
    	String usrDisName = usrDVO.getDisplayName();
    	String surname=usrDVO.getSurName();
    	String pwdHasAccountInfo=new String();
    	Iterator<String> pwdSubsetsItr = pwdSubsets.iterator();
    	while(pwdSubsetsItr.hasNext()){
    		String pwdPart = pwdSubsetsItr.next();
    		if(usrName.contains(pwdPart)|| usrDisName.contains(pwdPart) || surname.contains(pwdPart)){
    			pwdHasAccountInfo = bundle.getString("password_contains_account_info");
    			break;
    		}
    	}
    	if(!"".equals(pwdHasAccountInfo) || !"".equals(pwdMatchesCriteria)){
    		if(!"".equals(pwdHasAccountInfo))
    			return pwdHasAccountInfo;
    		else
    			return pwdMatchesCriteria;
    	   }    	
    }else{
    	return bundle.getString("pass_not_entered");
    }	
	return "";
  }
  /**
   * Jan 19,2011.
   * Username validation.
   * @param userName
   * @return
   */
  public static String userNameValidation(String userName){
	    if(userName != null && !"".equals(userName)){
	    	if(userName.length()<6)
	    		return bundle.getString("insufficientUsername");
	    	else{
	    		Pattern p = Pattern.compile(".*(?=.{6,64})(^[a-zA-Z0-9@._\\-]+$)");
	    		Matcher m =p.matcher(userName.toString());
	    		if(!m.matches())
	    			return bundle.getString("invalidUserNamePattern");
	    	}
	      }else
	    	return bundle.getString("userName_mandatory");
	    return "";
	 }
  /**
   * Jan 19,2011.
   * Captcha validation.
   * @param captchaValue
   * @return
   */


	public static FacesContext getAppContext() {
		return appContext;
	}

	public static void setAppContext(FacesContext appContext) {
		CommonValidations.appContext = appContext;
	}
 /**
  * Jan 19,2011.
  * Coppa enabled check.
  * @return
  */
	public static boolean isCoppaEnabled() {
		boolean isCoppaEnabled = false;
		try {
    		isCoppaEnabled = ServiceLocator.getUiUtilService().isCoppaEnabled();
		} catch (UIDeceException e) {
			e.printStackTrace();
			isCoppaEnabled = false;
		}
		return isCoppaEnabled;
    }
 }//Emd of class.
