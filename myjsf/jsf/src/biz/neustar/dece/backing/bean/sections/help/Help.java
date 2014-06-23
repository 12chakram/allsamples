package biz.neustar.dece.backing.bean.sections.help;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import biz.neustar.dece.portal.utils.QuestionAndAnswer;
import biz.neustar.dece.portal.utils.SessionUtils;

@ManagedBean(name="help")
@RequestScoped
public class Help {

	private List<QuestionAndAnswer> questionAndAnswerList =new ArrayList<QuestionAndAnswer>();
	private String helpNav;
	private String xmlFile = "";
	HttpSession session=(HttpSession)FacesContext.getCurrentInstance(). getExternalContext().getSession(true);
	SessionUtils sessionUtils = new SessionUtils();
	private ResourceBundle labelsBundle=sessionUtils.getLabelsTxtsBundle();
	private String searchTextHelp =labelsBundle.getString("searchTopic");
	private String qustnId;
	
	
	public Help(){
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		if(request.getParameter("helpNav") !=null)
		{
			helpNav=request.getParameter("helpNav").toString();
			setHelpNav(helpNav);
		}
		if(request.getParameter("topicVal") !=null && request.getParameter("questionId")!=null)
		{
			setHelpNav(request.getParameter("topicVal").toString());
			setQustnId(request.getParameter("questionId").toString());
		}
		else if(sessionUtils.getSession().getAttribute("helpCategory") == null)
			setHelpNav("AllTitle");
		if(sessionUtils.getSession().getAttribute("userLocale") == null){
			SessionUtils sessionUtilsObj = new SessionUtils();
			sessionUtilsObj.localeSet(null);
		}
	}
	
	public List<QuestionAndAnswer> getQuestionAndAnswerList() {
		return questionAndAnswerList;
	}

	public void setQuestionAndAnswerList(List<QuestionAndAnswer> questionAndAnswerList) {
		this.questionAndAnswerList = questionAndAnswerList;
	}

	public String getSearchTextHelp() {
		return searchTextHelp.trim();
	}

	public void setSearchTextHelp(String searchTextHelp) {
		this.searchTextHelp = searchTextHelp;
	}

	public String getHelpNav() { 
		return helpNav;
	}

	public void setHelpNav(String helpNav) {
		xmlFile = helpNav;
		if(helpNav != null && !"".equalsIgnoreCase(helpNav) && !"AllTitle".equalsIgnoreCase(helpNav))
			sessionUtils.getSession().setAttribute("helpCategory", "faqs_"+helpNav);
		if("AllTitle".equalsIgnoreCase(helpNav))
			sessionUtils.getSession().setAttribute("helpCategory", "faqs_account");
		this.helpNav = helpNav;
	}
	
	public String getXmlFile() {
		return xmlFile;
	}

	public void setXmlFile(String xmlFile) {
		this.xmlFile = xmlFile;
	}
	
	public String loadXmlFile(){
		return null;
	}
	
	public String helpAuthNavigation(){
		String helpCategory = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("contactUsFormId:contactUsHelpCategoryHidden");
		if(helpCategory != null && !"".equalsIgnoreCase(helpCategory))
			sessionUtils.getSession().setAttribute("helpCategory", helpCategory);

		if(sessionUtils.getSession().getAttribute("helpCategory") == null || "".equals(sessionUtils.getSession().getAttribute("helpCategory")))
			sessionUtils.getSession().setAttribute("helpCategory", "faqs_OurMedia");
		
		if(sessionUtils.getSession().getAttribute("User") != null)
			return "helpPage"; 
		else
			return "unAuthenticatedHelpPage";
	}
	
	public String helpSearchResults(){
		loadXmlFile();
		return null;
	}

	public String getQustnId() {
		return qustnId;
	}

	public void setQustnId(String qustnId) {
		this.qustnId = qustnId;
	}
	
}
