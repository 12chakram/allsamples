package biz.neustar.dece.backing.bean.sections.login;

import java.util.HashMap;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import biz.neustar.dece.portal.utils.APIErrorMapUtil;
import biz.neustar.dece.portal.utils.ExceptionUtils;
import biz.neustar.dece.portal.utils.ServiceLocator;
import biz.neustar.dece.portal.utils.SessionUtils;
import biz.neustar.dece.ui.UIDeceException;
import biz.neustar.dece.ui.dvo.AccountDVO;
import biz.neustar.dece.ui.dvo.UserSignInDVO;


@ManagedBean(name="reactivateHouseHold")
@RequestScoped
public class ReactivateHouseHold {

	
	private static final Logger logHandler = Logger.getLogger(ReactivateHouseHold.class);
	
	
	private String username;	
	private String  password;
	private Boolean acceptTermsCheckbox=Boolean.FALSE;
	private HashMap<String,String> errorValueMap;
	private String step1Render="step1FirstPage";
	private ResourceBundle errorsBundle;
	HttpSession session=new SessionUtils().getSession();
	
	
	public ReactivateHouseHold()
	{
		SessionUtils sessionVars = new SessionUtils();
		errorsBundle  = sessionVars.getErrorTxtsBundle();
		errorValueMap=new HashMap<String,String>();
	}

	public String getStep1Render() {
		return step1Render;
	}
	public void setStep1Render(String step1Render) {
		this.step1Render = step1Render;
	}
	public String getUsername() {
		return username;
	}
	public HashMap<String, String> getErrorValueMap() {
		return errorValueMap;
	}
	public void setErrorValueMap(HashMap<String, String> errorValueMap) {
		this.errorValueMap = errorValueMap;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public boolean validation() {
		boolean flag = true;
		if(username==null ||"".equals(username.trim())){
			errorValueMap.put("usernameError",errorsBundle.getString("username_required"));	
			flag = false;
		}	
		if (password == null || "".equals(password.trim())) {
			errorValueMap.put("passwordError",errorsBundle.getString("password_mandatory"));
			flag=false;
			}
		if(!flag)
			errorValueMap.put("isValidForm", String.valueOf(flag));
		logHandler.info("Reactivate Household - Validation completed with Status: "+flag);
		return flag;
	}
	
	public String reactivateSubmitAction(){
		String returnPage=null;
			if (validation()) {				
				try {
					logHandler.info("username: "+username+"  password: "+password+"  acceptTermsCheckbox: "+acceptTermsCheckbox);
					UserSignInDVO userSigninDVO=ServiceLocator.getUiAccountService().reactivateAccount(username.trim(), password.trim());
					logHandler.info(userSigninDVO.toString());
					if(userSigninDVO.getNodeAccountId() != null || userSigninDVO.getUserName()!=null)
					{
						//session.setAttribute("userSigninDVO", userSigninDVO);
						session.setAttribute("country", userSigninDVO.getCountry());
						session.setAttribute("uname", username.trim());
						session.setAttribute("uvk", password.trim());
						step1Render="regStep2Page";
						returnPage="reactivateTermsOfUsePage";						
					}
				} catch (UIDeceException ex) 
				{	errorValueMap.put("isValidForm", String.valueOf(Boolean.FALSE));
					ExceptionUtils.processUIDECEException("REACTIVATE_HOUSEHOLD","ClassName:ReactivateHouseHold_MethodName:reactivateSubmitAction",ex, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, errorsBundle);
				}	
		}
		logHandler.info("RETURN Page: "+returnPage);
		return returnPage;
	}
	public String reactivateCancelAction(){
		return "homePage";
		
	}
	public String reactivateContinue(){
		String returnPage=null;
		boolean isValidFormFlag=true;
		if (!acceptTermsCheckbox) {
			isValidFormFlag=false;
			errorValueMap.put("isValidFormFlag", String.valueOf(isValidFormFlag));
			errorValueMap.put("termsOfUseError",errorsBundle.getString("reactivate_termsOfUse_error"));
			step1Render="regStep2Page";
		}else{
			try{
				if(null!=session)
				{
					username=session.getAttribute("uname").toString();
					password=session.getAttribute("uvk").toString();
					AccountDVO accountDVO = ServiceLocator.getUiAccountService().reactivateAccount(username.trim(), password.trim(),acceptTermsCheckbox);
					if(null!=accountDVO && accountDVO.getNodeAccountId()!=null){
						logHandler.info(accountDVO.toString());
						step1Render="regStep3Page";
						returnPage="reactivateTermsOfUsePage";
					}
				}
				else {
					step1Render="regStep1Page";
					returnPage="reactivateTermsOfUsePage";
				}
			}catch(UIDeceException ex){
				isValidFormFlag=false;
				errorValueMap.put("isValidFormFlag", String.valueOf(isValidFormFlag));
				step1Render="regStep2Page";
				ExceptionUtils.processUIDECEException("REACTIVATE_HOUSEHOLD","ClassName:ReactivateHouseHold_MethodName:reactivateContinue",ex, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, errorsBundle);
			}
		}
		return returnPage;
	}
	public String reactivateAutoLoginAction()
	{
		String returnPage=null;
		if(session!=null && null!=session.getAttribute("uname")){
			LoginUserBean toLogin = new LoginUserBean();
			toLogin.setUserName(session.getAttribute("uname").toString());		
			toLogin.setPassword(session.getAttribute("uvk").toString());
			session.removeAttribute("uname");
			session.removeAttribute("uvk");
			toLogin.doLogin();
			LoginNavigationBean loginNavigationBean=new LoginNavigationBean();
			returnPage=loginNavigationBean.loginAction();
		}
		else{
			LoginNavigationBean loginNavigationBean=new LoginNavigationBean();
			loginNavigationBean.navigateToHomePage();
		}
	return returnPage;
	}
	
	public String navigateToOurDevicesPage()
	{
		String returnPage=null;
		if(session!=null && null!=session.getAttribute("uname")){
			LoginUserBean toLogin = new LoginUserBean();
			toLogin.setUserName(session.getAttribute("uname").toString());		
			toLogin.setPassword(session.getAttribute("uvk").toString());
			session.removeAttribute("uname");
			session.removeAttribute("uvk");
			toLogin.doLogin();
			returnPage="ourDevicesPage";
		}
		else{
			LoginNavigationBean loginNavigationBean=new LoginNavigationBean();
			loginNavigationBean.navigateToHomePage();
		}
	return returnPage;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getAcceptTermsCheckbox() {
		return acceptTermsCheckbox;
	}
	public void setAcceptTermsCheckbox(Boolean acceptTermsCheckbox) {
		this.acceptTermsCheckbox = acceptTermsCheckbox;
	}
}