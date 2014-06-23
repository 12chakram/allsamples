package biz.neustar.dece.backing.bean.sections.login;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import biz.neustar.dece.backing.bean.sections.dashboard.AccountDashboardBean;
import biz.neustar.dece.portal.utils.SessionUtils;
import biz.neustar.dece.ui.dvo.ChildYouthUserDVO;
import biz.neustar.dece.ui.dvo.RSAMLParamsDVO;
import biz.neustar.dece.ui.dvo.UserAuthDVO;
import biz.neustar.dece.ui.dvo.UserSignInDVO;
import biz.neustar.dece.ui.enumeration.UINextPageEnum;
import biz.neustar.dece.ui.enumeration.UIUserVerificationTokenType;

@ManagedBean(name="loginNavigationBean")
@RequestScoped
public class LoginNavigationBean {
	
	private static final Logger loginNavigationBeanlogger = Logger.getLogger(LoginNavigationBean.class);
	HttpSession session=(HttpSession)FacesContext.getCurrentInstance(). getExternalContext().getSession(true);
	
	public LoginNavigationBean(){}
	
	public String loginAction(){
		if(null!=session)
		{
			UserSignInDVO signin = (UserSignInDVO)session.getAttribute("SignIn");
			if(null!=signin)
			{
						session.removeAttribute("HOSTAGE");
						if(null!=signin.getDisplayEmailVerificationExpiryDate())
							session.setAttribute("DASHBOARD_EMAIL_EXP_TIME", signin.getDisplayEmailVerificationExpiryDate());
						ChildYouthUserDVO firstUser=null;
						String userId=null;
						String userDisplayName="";
						if( null!=session.getAttribute("RememberMe") && "true".equalsIgnoreCase(session.getAttribute("RememberMe").toString())){
							if("GB".equalsIgnoreCase(signin.getCountry().toString())){
								long displayTime=(long)signin.getCookieExpiryTimeInSeconds();
								TimeUnit tu=TimeUnit.SECONDS;
								int CookieStayTime=(int)((tu.toDays(displayTime)==0)?(tu.toHours(displayTime)==0?1:tu.toHours(displayTime)):tu.toDays(displayTime));
								session.setAttribute("TodecideHourOrDay", tu.toDays(displayTime));
								session.setAttribute("toDecicdeHour", tu.toHours(displayTime));
								session.setAttribute("maxAgeForCookie",CookieStayTime);
								session.setAttribute("toShowPopupOf", "COOKIE_REMEMBERME");
								return "pendingAccountDashboardPage";
							}
							else{
								LoginUserBean loginBeanObj=new LoginUserBean();
								loginBeanObj.addToCookie();
							}
						}
						
						if (signin.getNextPage().equals(UINextPageEnum.DASHBOARD))
						{
							session.removeAttribute("toShowPopupOf");
							session.removeAttribute("ADMIN_POPUP");
							session.removeAttribute("maxAgeForCookie");		
							session.removeAttribute("TodecideHourOrDay");
							session.removeAttribute("toDecicdeHour");
							RSAMLParamsDVO rSAMLDVO = (RSAMLParamsDVO)session.getAttribute("RSAMLParamsDVO");
							loginNavigationBeanlogger.info("rSAMLDVO==" + rSAMLDVO);
							String retPath="";
							if(null != rSAMLDVO)
							{
								String targetURL = rSAMLDVO.getTargetURL();
								if ( targetURL.contains("/views/accountDashboardPage.jsf") )
									retPath="mediaSection";
								else if ( targetURL.contains("/views/userProfilePage.jsf") ){
									if(null!=rSAMLDVO.getPortalNodeUserId())
									session.setAttribute("currUserViewId", rSAMLDVO.getPortalNodeUserId());
									retPath = "userProfilePage";
								}
								else if ( targetURL.contains("/views/ourDevicesPage.jsf") )
									retPath="ourDevicesPage";
								else if ( targetURL.contains("/views/mediaPage.jsf") )
									retPath="mediaSection";
							}else if(UIUserVerificationTokenType.FORGOT_PASSWORD.equals(signin.getUserTokenType())){
								retPath="pendingAccountDashboardPage";
							}
							else if(null!=session.getAttribute("tokenRST") && session.getAttribute("tokenRST").toString().equals("yes"))
							{
								session.setAttribute("fromReset", "true");
			                    session.setAttribute("currUserViewId", signin.getNodeUserId());
			                    try {
			                    	  FacesContext context=FacesContext.getCurrentInstance();
			                          context.getExternalContext().redirect("../views/userProfilePage.jsf");
			                    } catch (IOException e) {
			                         e.printStackTrace();
			                    } 
							}
							else
							{
								if(null==session.getAttribute("shost"))
								{
									if(!(null!=session.getAttribute("page") && session.getAttribute("page").toString().equalsIgnoreCase("confirmMyDetails"))){
										FacesContext context=FacesContext.getCurrentInstance();
										try {
											context.getExternalContext().redirect("../views/mediaPage.jsf");
										} catch (IOException e) {
											e.printStackTrace();
										}
									}
										else
											retPath="mediaSection";
								}
								else
								retPath="mediaSection";
							}
							return retPath;
						}else if (signin.getNextPage().equals(UINextPageEnum.TOU_SELF)){
							session.setAttribute("toShowPopupOf", "TOU_SELF");
							return "pendingAccountDashboardPage";
						}else if (signin.getNextPage().equals(UINextPageEnum.DASHBOARD_SECURITY_QUESTIONS)){
							session.setAttribute("toShowPopupOf", "SECRET_QUESTIONS");
							AccountDashboardBean accuntDashBoard=new AccountDashboardBean();
							accuntDashBoard.getUserDetailsForQuestions();
							RSAMLParamsDVO rSAMLDVO = (RSAMLParamsDVO)session.getAttribute("RSAMLParamsDVO");
							if(null ==rSAMLDVO)
							{
								try{
									FacesContext context=FacesContext.getCurrentInstance();
									context.getExternalContext().redirect("../views/pendingAccountDashboardPage.jsf");
								}catch (IOException ioe) {
									if(loginNavigationBeanlogger.isDebugEnabled())   {           
										loginNavigationBeanlogger.debug(ioe.getMessage());
									}
								}
							}else
								return "pendingAccountDashboardPage";
						}
						else if (signin.getNextPage().equals(UINextPageEnum.DASHBOARD_TOU_OR_COPPA_ON_BEHALF_OF_CHILD_OR_YOUTH)){
							List<ChildYouthUserDVO> childYouthList=signin.getChildYouthUserList();
							if(null!=childYouthList){
								if((null==session.getAttribute("CLG"))&& (null==session.getAttribute("CLD"))){
									firstUser=childYouthList.get(0);
									userDisplayName=firstUser.getDisplayName();
									userId=firstUser.getNodeUserId();
								}else{
									String CLD = session.getAttribute("CLD").toString();
									session.removeAttribute("CLD");
									session.removeAttribute("CLG");
									Iterator<ChildYouthUserDVO> itr = childYouthList.iterator();
									while(itr.hasNext())
									{
										firstUser=itr.next();
										if(firstUser.getNodeUserId().contains(CLD))
										{
											userDisplayName=firstUser.getDisplayName();
											userId=firstUser.getNodeUserId();
											session.setAttribute("ChildCoppaAccepted","NO");
										}
									}
								}
								if(null==session.getAttribute("ADMIN_POPUP")){
									session.setAttribute("ADMIN_POPUP", "YES");
									session.setAttribute("toShowPopupOf", "ADMIN_POPUP");
								}else if (firstUser.isClgTransferred() && firstUser.isEulaAcceptanceRequired())
									session.setAttribute("toShowPopupOf", "CLG_TRANSFER");
								else if(firstUser.isEulaAcceptanceRequired())
									session.setAttribute("toShowPopupOf", "TOU_OTHERS");
								else if (firstUser.isCoppaAcceptanceRequired())
								{
									if(null!=signin.getCountry()&& "GB".equalsIgnoreCase(signin.getCountry().toString())){
										session.setAttribute("toShowPopupOf", "JCOPPA_OTHERS");
										session.setAttribute("childOrYouthAge",firstUser.getAgeClass().toString());
									}else {
										session.setAttribute("toShowPopupOf", "COPPA_OTHERS");
									}
								}
								session.setAttribute("childOrYouthUser", userDisplayName);
								session.setAttribute("userNodeId", userId);
							}
							return "pendingAccountDashboardPage";
						}else if (signin.getNextPage().equals(UINextPageEnum.HOSTAGE))
						{
							session.setAttribute("HOSTAGE", "YES");
							session.removeAttribute("toShowPopupOf");
							return "hostagePage";
						}
						else if (signin.getNextPage().equals(UINextPageEnum.DASHBOARD_EMAIL_NOT_VERIFIED)){
							if(null==session.getAttribute("ADMIN_POPUP")){
								session.setAttribute("toShowPopupOf", "EMAIL_CONFIRMATION_PENDING");
								if(null!= signin.getDisplayEmailVerificationExpiryDate())
								session.setAttribute("DASHBOARD_EMAIL_EXP_TIME", signin.getDisplayEmailVerificationExpiryDate());
							}
							return "pendingAccountDashboardPage";
						}
			}
			else
			{
				navigateToHomePage();
			}
		}
		else
			navigateToHomePage();
		return null;
	}
	
	
	/**
	 * Add Login details to Cookie
	 * @param UserSignInDVO
	 */
	public void setDetailsIntoSession(UserSignInDVO signin,HttpSession session){
		if(null!=session)
		{
				HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
				String remoteHost = request.getRemoteHost();
				UserAuthDVO auth = new UserAuthDVO();
				auth.setClientIP(remoteHost);
				auth.setNodeAccountId(signin.getNodeAccountId());
				auth.setNodeUserId(signin.getNodeUserId());	
				//auth.setSessionId(session.getId());
				//create the required attributes in session
				session.setAttribute("householdName", signin.getAccountName());
				session.setAttribute("User", signin.getUserName());
				session.setAttribute("userId", signin.getNodeUserId());
				session.setAttribute("userAge", signin.getUserAgeClass().toString());
				session.setAttribute("privilege", signin.getPrivilege());
				session.setAttribute("country", signin.getCountry().toString());
				session.setAttribute("Auth", auth);
				session.setAttribute("SignIn", signin);
				session.setAttribute("DispName", ((null==signin.getUserDisplayName()|| "".equals(signin.getUserDisplayName())?signin.getUserName():signin.getUserDisplayName())));
				SessionUtils sessionUtils=new SessionUtils();
				Locale usrLocale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
				if(null != usrLocale)
				{
					if("GB".equalsIgnoreCase(signin.getCountry().toString()))
						sessionUtils.localeSet("en-uk");
					else
						sessionUtils.localeSet("en-us");
				}
		}
		else
		{
			navigateToExpiryPage();
		}
	}
	
	public void navigateToExpiryPage()
	{
		try{
			FacesContext context=FacesContext.getCurrentInstance();
			context.getExternalContext().redirect("../public/expiryPage.jsf");
		}catch (IOException ioe) {
			if(loginNavigationBeanlogger.isDebugEnabled())   {           
				loginNavigationBeanlogger.debug(ioe.getMessage());
			}
		}
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
	
	public void navigateToLoginPage(){
		if(session!=null){
			session.invalidate();
		}
		String portalLoginURL=null;
		FacesContext cntxt=FacesContext.getCurrentInstance();
		if(null!=cntxt.getExternalContext().getApplicationMap().get("portalGlobalLoginUrl")){
			portalLoginURL=(String)cntxt.getExternalContext().getApplicationMap().get("portalGlobalLoginUrl");
			try{
				cntxt.getExternalContext().redirect(portalLoginURL);
			}catch(IOException ie){
				ie.printStackTrace();
			}
		}
	}
	
	public void navigateToUnAuthHelpPage(){
		try{
			FacesContext context=FacesContext.getCurrentInstance();
			context.getExternalContext().redirect("../public/helpPage.jsf");
		}catch (IOException ioe) {
			if(loginNavigationBeanlogger.isDebugEnabled())   {           
				loginNavigationBeanlogger.debug(ioe.getMessage());
			}
		}
		
	}
	public void navigateToAuthHelpPage(){
		try{
			FacesContext context=FacesContext.getCurrentInstance();
			context.getExternalContext().redirect("../views/authHelpPage.jsf");
		}catch (IOException ioe) {
			if(loginNavigationBeanlogger.isDebugEnabled())   {           
				loginNavigationBeanlogger.debug(ioe.getMessage());
			}
		}
		
	}
	public void navigationFromErrorPage(){
		if(session!=null)
			session.invalidate();
		navigateToHomePage();
	}
	public void navigationToUnauthHomePage(){
		if(session!=null)
			session.invalidate();
		navigateToHomePage();
	}
}