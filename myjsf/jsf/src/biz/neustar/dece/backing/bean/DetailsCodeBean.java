package biz.neustar.dece.backing.bean;

import java.io.IOException;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import biz.neustar.dece.portal.utils.SessionUtils;

@ManagedBean(name="detailsCodeBean")
@RequestScoped

public class DetailsCodeBean{
	
	private String emailCode;
	private ResourceBundle errorsBundle;
	private HashMap<String,String> errorValueMap;
	private boolean isValidForm = false;
	HttpSession session=(HttpSession)FacesContext.getCurrentInstance(). getExternalContext().getSession(true);
	private static final Logger logHandler = Logger.getLogger( RecoveringSignInNameBean.class);
	
	public String getEmailCode() {
		return emailCode;
	}

	public void setEmailCode(String emailCode) {
		this.emailCode = emailCode;
	}
	public DetailsCodeBean()
	{
		SessionUtils sessionVars = new SessionUtils();
		errorsBundle  = sessionVars.getErrorTxtsBundle();
		errorValueMap = new HashMap<String,String>();	
	}
	public HashMap<String, String> getErrorValueMap() {
		return errorValueMap;
	}
	public void setErrorValueMap(HashMap<String, String> errorValueMap) {
		this.errorValueMap = errorValueMap;
		
	}
	public String sendEmail(){
		if(validateSubmittedValues()){
			return null;
		}
		
		return null;
		
	}
	// for validating the code entered by the user
	private boolean validateSubmittedValues(){
		
		isValidForm = true;
		logHandler.info("Details Code - Call to validate submitted values");
		
		if("".equals(emailCode)){
			errorValueMap.put("emailCodeError",errorsBundle.getString("emailCode_required"));	
			isValidForm = false;
		}			
		
		if(!isValidForm)
			errorValueMap.put("isValidForm",String.valueOf(isValidForm));
		logHandler.info("Details Code - Validation completed with Status: "+isValidForm);
		return isValidForm;
	}
	 public void navigateToHomePage(){
	        String portalHomeURL=null;
	        FacesContext context=FacesContext.getCurrentInstance();
	        if(null!=context.getExternalContext().getApplicationMap().get("portalglobalUrl"))
	        {
	              portalHomeURL = (String) context.getExternalContext().getApplicationMap().get("portalglobalUrl");
	              try {
	                    context.getExternalContext().redirect(portalHomeURL);
	              } catch (IOException e) {
	                    e.printStackTrace();
	              }
	        }
	  }
	 public void navigationToUnauthHomePage(){
	        
			if(session!=null)
	              session.invalidate();
	        navigateToHomePage();
	  }
	 /**
      * method to show the Help page
      * 
      */
      public void navigateToUnAuthHelpPage(){
            try{
                  
                  FacesContext context=FacesContext.getCurrentInstance();
                  context.getExternalContext().redirect("../public/helpPage.jsf");
            }catch (IOException ioe) {
                  if(logHandler.isDebugEnabled())   {           
                        logHandler.debug(ioe.getMessage());
                  }
            }
      }

}