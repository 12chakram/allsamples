//LoginNavigationBean.java

package biz.neustar.dece.backing.bean.sections.login;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

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

public class LoginNavigationBean {
	
	private static final Logger loginNavigationBeanlogger = Logger.getLogger(LoginNavigationBean.class);
	SessionUtils sessionUtils=new SessionUtils();
	HttpSession session=(HttpSession)FacesContext.getCurrentInstance(). getExternalContext().getSession(true);
	
	public LoginNavigationBean()
	{
		
	}
	public String loginAction(){
		UserSignInDVO signin = (UserSignInDVO)session.getAttribute("SignIn");
		session.removeAttribute("HOSTAGE");
		session.setAttribute("DASHBOARD_EMAIL_EXP_TIME", signin.getDisplayEmailVerificationExpiryDate());
		ChildYouthUserDVO firstUser=null;
		String userId=null;
		String userDisplayName="";
		if(null!=session && null!=session.getAttribute("RememberMe") && "true".equalsIgnoreCase(session.getAttribute("RememberMe").toString())){
			if("UK".equalsIgnoreCase(signin.getCountry().toString())){
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
			RSAMLParamsDVO rSAMLDVO = (RSAMLParamsDVO)sessionUtils.getSession().getAttribute("RSAMLParamsDVO");
			loginNavigationBeanlogger.info("rSAMLDVO==" + rSAMLDVO);
			String retPath="";
			if(null != rSAMLDVO)
			{
				String targetURL = rSAMLDVO.getTargetURL();
				if ( targetURL.contains("/views/accountDashboardPage.jsf") )
					retPath="accountDashboardPage";
				else if ( targetURL.contains("/views/userProfilePage.jsf") ){
					session.setAttribute("currUserViewId", rSAMLDVO.getPortalNodeUserId());
					retPath = "userProfilePage";
				}
				else if ( targetURL.contains("/views/ourDevicesPage.jsf") )
					retPath="ourDevicesPage";
				else if ( targetURL.contains("/views/mediaPage.jsf") )
					retPath="mediaSection";
			}else if(UIUserVerificationTokenType.FORGOT_PASSWORD.equals(signin.getUserTokenType())){
				retPath="pendingAccountDashboardPage";
			}else
				retPath= "accountDashboardPage";
			
			return retPath;
		}
		else if (signin.getNextPage().equals(UINextPageEnum.TOU_SELF)){
			session.setAttribute("toShowPopupOf", "TOU_SELF");
			return "pendingAccountDashboardPage";
		}else if (signin.getNextPage().equals(UINextPageEnum.DASHBOARD_SECURITY_QUESTIONS)){
			session.setAttribute("toShowPopupOf", "SECRET_QUESTIONS");
			AccountDashboardBean accuntDashBoard=new AccountDashboardBean();
			accuntDashBoard.getUserDetailsForQuestions();
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
					if(null!=signin.getCountry()&& "UK".equalsIgnoreCase(signin.getCountry().toString())){
						session.setAttribute("toShowPopupOf", "JCOPPA_OTHERS");
						sessionUtils.getSession().setAttribute("childOrYouthAge",firstUser.getAgeClass().toString());
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
			return "hostagePage";
		}
		else if (signin.getNextPage().equals(UINextPageEnum.DASHBOARD_EMAIL_NOT_VERIFIED)){
			if(null==session.getAttribute("ADMIN_POPUP")){
				session.setAttribute("toShowPopupOf", "EMAIL_CONFIRMATION_PENDING");
				session.setAttribute("DASHBOARD_EMAIL_EXP_TIME", signin.getDisplayEmailVerificationExpiryDate());
			}
			return "pendingAccountDashboardPage";
		}
		return null;
	}
	
	
	/**
	 * Add Login details to Cookie
	 * @param UserSignInDVO
	 */
	public void setDetailsIntoSession(UserSignInDVO signin){
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String remoteHost = request.getRemoteHost();
		request = null;
		UserAuthDVO auth = new UserAuthDVO();
		auth.setClientIP(remoteHost);
		auth.setNodeAccountId(signin.getNodeAccountId());
		auth.setNodeUserId(signin.getNodeUserId());		
		//create the required attributes in session
		session.setAttribute("householdName", signin.getAccountName());
		session.setAttribute("User", signin.getUserName());
		session.setAttribute("userId", signin.getNodeUserId());
		session.setAttribute("userAge", signin.getUserAgeClass().toString());
		session.setAttribute("privilege", signin.getPrivilege());
		session.setAttribute("country", signin.getCountry().toString());
		session.setAttribute("Auth", auth);
		session.setAttribute("SignIn", signin);
		
		Locale usrLocale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		if(null != usrLocale)
		{
			/*if("UK".equalsIgnoreCase(signin.getCountry().toString()))
				sessionUtils.localeSet("en-uk");
			else*/
				sessionUtils.localeSet("en-us");
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
