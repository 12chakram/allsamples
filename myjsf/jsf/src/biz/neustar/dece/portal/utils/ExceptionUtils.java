package biz.neustar.dece.portal.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import biz.neustar.dece.ui.UIDeceException;
import biz.neustar.dece.ui.UIError;

public class ExceptionUtils {
	


	private static Logger logger = Logger.getLogger(ExceptionUtils.class);
	
	public static String processUIDECEException(String scenario, String calledFromMethod, UIDeceException apiException,HashMap<String,String> errorFieldMap, HashMap<String,String> errorValueMap, ResourceBundle bundle )
	{		
		UIError errorInstance;
		String deceErrorConstant; 
		String uiErrorVariable;
		String returnPath="";
		List<String> al = new ArrayList<String>();
		SessionUtils sessionUtils = new SessionUtils();
		
		Iterator<UIError> itr = apiException.getErrors().iterator();
		
		StringBuffer buffer = new StringBuffer("\n \n *********** LOGGING UIDECEEXCEPTION ********** \n");
		buffer.append("Scenario: " + scenario +" \n ");
		buffer.append("Bean.MethodName: " + calledFromMethod +" \n ");	
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// error handling revised as per the error codes shared by Vikas
		while(itr.hasNext())
		{
			try{
			errorInstance = itr.next();
			
			deceErrorConstant = getErrorCode(errorInstance.getErrorCode());	
			errorValueMap.put("APIErrorCode",deceErrorConstant);
			if(deceErrorConstant.equals("CHILD_MEMBERS_WITHOUT_COPPA_POLICY_CANNOT_BE_UPDATED"))
				if(null!=sessionUtils && null!=sessionUtils.getSession() && sessionUtils.getSession().getAttribute("country").equals("GB"))
					deceErrorConstant="JUNIOR_MEMBERS_WITHOUT_COPPA_POLICY_CANNOT_BE_UPDATED";
			
			logger.info("deceErrorConstant: "+deceErrorConstant);
			if(""==deceErrorConstant) deceErrorConstant="UNEXPECTED_ERROR";
			al.add(deceErrorConstant);
			uiErrorVariable = APIErrorMapUtil.uiErrorCodeFieldMap.get(deceErrorConstant);			
			//errorValueMap.put((((uiErrorVariable == null))? "pageError":uiErrorVariable), bundle.getString(deceErrorConstant));
			errorValueMap.put((((uiErrorVariable == null) ||(uiErrorVariable.length()==0))? "pageError":uiErrorVariable), bundle.getString(deceErrorConstant));					
			buffer.append(errorInstance.getErrorCode() + " : " + errorInstance.getErrorDescription() + " \n ");
			}
			catch(Exception ex){
				logger.error(buffer.toString());
			}
		}
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		buffer.append("************ END LOGGING UIDECEEXCEPTION ************************** \n \n");
		logger.info(buffer.toString());		
		/*if(al.contains("ACCOUNT_USER_STATUS_BLOCKED_CLG") || al.contains("TOU_NOT_ACCEPTED_BY_CLG") || al.contains("COPPA_NOT_ACCEPTED_BY_CLG")) {

			String dispFor=null;
			dispFor =(al.contains("TOU_NOT_ACCEPTED_BY_CLG")?"TOU":"COPPA");
			try {
				FacesContext context=FacesContext.getCurrentInstance();
				context.getExternalContext().redirect("../public/pendingUserMessagePage.jsf?dispFor="+dispFor);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}*/
//		if(al.contains("REQUEST_ACCOUNT_DELETED") ||al.contains("REQUEST_ACCOUNT_USER_STATUS_DELETED") || al.contains("REQUEST_USER_ID_UNMATCHED") ||	al.contains("SECURITY_ACCOUNT_ID_INVALID") ||	al.contains("SECURITY_USER_ID_INVALID") ||	al.contains("REQUEST_ACCOUNT_USERID_INVALID") ||	al.contains("REQUEST_ACCOUNT_ACCOUNT_ID_INVALID"))
		if(al.contains("REQUESTOR_NOT_ACTIVE") || al.contains("RequestorNotActive") ||  al.contains("UserNotActive")) {
			try {
				FacesContext context=FacesContext.getCurrentInstance();
				context.getExternalContext().redirect("../public/hostagePage.jsf");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(al.contains("REQUESTOR_STATUS_NOT_ACTIVE") || al.contains("REQUEST_ACCOUNT_STATUS_NOT_ACTIVE")|| al.contains("ACCOUNT_USER_STATUS_DELETED") || al.contains("REQUEST_ACCOUNT_USER_ALREADY_DELETED") || al.contains("USER_SUSPENDED_OR_DELETE") || al.contains("AccountDeleted") || al.contains("UserDeleted") || al.contains("REQUEST_ACCOUNT_DELETED") || al.contains("REQUEST_ACCOUNT_USER_STATUS_DELETED"))
		{ 
			try {
				FacesContext context=FacesContext.getCurrentInstance();
				context.getExternalContext().redirect("../public/logout.jsf?errorCode="+al.get(0));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//apiException.printStackTrace();
		return returnPath;
	}
	
	public static void processException(String scenario, String calledFromMethod, Exception exception,HashMap<String,String> errorValueMap, ResourceBundle bundle)
	{			
		String errorConstant; 
		String uiErrorVariable;		
		StringBuffer buffer = new StringBuffer("\n \n *********** LOGGING UIDECEEXCEPTION ********** \n");
		buffer.append("Scenario: " + scenario +" \n ");
		buffer.append("Bean.MethodName: " + calledFromMethod +" \n ");	
		
		//errorConstant = APIErrorMapUtil.uiErrorCodesList.get(errorInstance.getErrorCode());
		/*uiErrorVariable = "pageError";			
		errorValueMap.put((((uiErrorVariable==null) ||(uiErrorVariable.length()==0))? "pageError":uiErrorVariable), bundle.getString(errorConstant));*/					
		buffer.append("CAUSE: "+exception.getCause() + "\n \n ");
		buffer.append("Description: "+exception.getMessage() + "\n \n ");		
		buffer.append("************ END LOGGING UIDECEEXCEPTION ************************** \n \n");
		logger.error(buffer.toString());
	}
	
	
	private static String getErrorCode(String apiErrorCode)
	{
		String eCode="";
		Iterator<String> itr = APIErrorMapUtil.uiAPIErrorCodes.iterator();
		
		while(itr.hasNext())
		{
			eCode = itr.next();			
			if(apiErrorCode.indexOf(eCode)>=0)
			{	break; }
		}
		return eCode;	
	}
	
}
