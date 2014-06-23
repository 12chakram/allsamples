package biz.neustar.dece.backing.bean.sections.login;



import java.util.HashMap;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import biz.neustar.dece.portal.utils.APIErrorMapUtil;
import biz.neustar.dece.portal.utils.ExceptionUtils;
import biz.neustar.dece.portal.utils.ServiceLocator;
import biz.neustar.dece.portal.utils.SessionUtils;
import biz.neustar.dece.ui.UIDeceException;
import biz.neustar.dece.ui.service.UIUtilService;

import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaFactory;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;



@ManagedBean
@RequestScoped
public class RecaptchBean {
	
	private String recaptchaHtml;
	private String captchaError ="";
	private FacesContext context=FacesContext.getCurrentInstance();
	private String publickKey="";
	private String privateKey="";
	private static final Logger recaptchBeanlogger = Logger.getLogger(RecaptchBean.class);
	private ResourceBundle errorsBundle= (new SessionUtils()).getErrorTxtsBundle();
	private HashMap<String,String> errorValueMap=new HashMap<String,String>();


	public String displayRecaptch()
	{
		if(null!=context.getExternalContext().getApplicationMap().get("reCaptchaPublicKey") && null!=context.getExternalContext().getApplicationMap().get("reCaptchaPrivateKey"))
		{
			publickKey=context.getExternalContext().getApplicationMap().get("reCaptchaPublicKey").toString();
			privateKey= context.getExternalContext().getApplicationMap().get("reCaptchaPrivateKey").toString();
		}
		 ReCaptcha c = ReCaptchaFactory.newSecureReCaptcha(publickKey,privateKey, false);
		 String captchaHTML = c.createRecaptchaHtml(null, null);
		 recaptchBeanlogger.info("recaptcha html:::::::::::"+captchaHTML);
		 if(null!=captchaHTML && captchaHTML.contains("https://api-secure.recaptcha.net")){
			 captchaHTML=captchaHTML.replace("https://api-secure.recaptcha.net", "https://www.google.com/recaptcha/api");
		 }
		 recaptchBeanlogger.info("After replace recaptcha html:::::::::::"+captchaHTML);
		 return captchaHTML;
	}
	
		public  boolean validateRecaptcha()
			{
					HttpServletRequest request = (HttpServletRequest)context. getExternalContext().getRequest();
					String remoteAddr = request.getRemoteAddr();
					ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
					if( null!=context.getExternalContext().getApplicationMap().get("reCaptchaPrivateKey"))
					this.privateKey=context.getExternalContext().getApplicationMap().get("reCaptchaPrivateKey").toString();
					reCaptcha.setPrivateKey(privateKey);
					String challenge = request.getParameter("recaptcha_challenge_field");
					String uresponse = request.getParameter("recaptcha_response_field");
					boolean response=false;
					UIUtilService service =ServiceLocator.getUiUtilService();
					try {
						 response=service.verifyCaptcha(remoteAddr, challenge, uresponse);
						
					} catch (UIDeceException e) {
						ExceptionUtils.processUIDECEException("RECAPTCHABEAN","ClassName:RECAPTCHABEAN_MethodName:validateRecaptcha",e, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, errorsBundle);
					}
					return response;
			}

	public String getCaptchaError() {
		return captchaError;
	}

	public void setCaptchaError(String captchaError) {
		this.captchaError = captchaError;
	}
	public String getRecaptchaHtml() {
		if(recaptchaHtml==null){
			recaptchaHtml = displayRecaptch();
		}
		return recaptchaHtml;
	}

	public void setRecaptchaHtml(String recaptchaHtml) {
		this.recaptchaHtml = recaptchaHtml;
	}
	public String getPublickKey() {
		return publickKey;
	}

	public void setPublickKey(String publickKey) {
		this.publickKey = publickKey;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
  
}

