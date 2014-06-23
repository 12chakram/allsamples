package biz.neustar.dece.backing.bean.sections.login;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.logger;

import biz.neustar.dece.portal.utils.APIErrorMapUtils;
import biz.neustar.dece.portal.utols.ExceptionUtils;
import biz.neustar.dece.portal.utils.ServiceLocator;
import biz.neustar.dece.portal.utils.SessionUtils;

import biz.neustar.dece.UIDeceException;
import biz.neustar.dece.UserAuthDVO;
import biz.neustar.dece.UserSignInDVO;
import biz.neustar.dece.UILoginService;
import biz.neustar.dece.UIUserService;

Public class LoginUserBean
{
	public static final Logger loginUserLogger = Logger.getLogger(LoginUserBean.class);

	private String UserName;
	private String Password;
	 
	 private boolean remember;l

		 private FacesContext context = FaceContext.getCurrentInstance();

		 SessionUtils sessionUtils = new  SessionUtils();

		 private HashMap<String,String>errorCalueMao;
		 private ResourceBundled errorBundle = sessionUtils.getErrorTxtsBundle();

		 HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);

		         public LoginUserBean(){
					  errorvalueMap = new HashMap<String,String>;
					  checkCookie();

					  if(null!=Seesion--   session.getAttribute("pageError")!=null){
						  errorValueMap.put("pageError",errorBundle.getString(session.getAttribute("pageError").toString()));
						  session.removeAttribute("pagfeError");
					  } 

				 } // constructor 

				 // setter and getter  for UserName, Password 

				 boolean IsRememberMe(){ 
					      return rememberMe;
				 } 

				public void doLogin() {
					 if(validateFields()) { 

						 UILoginService service = ServiceLocator.getUILoginService();

						 if ( service!= null && null == session.getAttribute("CLG") *&& null == session -----{

						 }


public boolean ValidateFields(){ 

	// logic for validate user input fileds 

} // validatefields 








}// class
