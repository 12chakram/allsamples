package biz.neustar.dece.backing.bean.sections.accountMerge;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import biz.neustar.dece.backing.bean.sections.login.LoginUserBean;
import biz.neustar.dece.backing.bean.sections.login.RecaptchBean;
import biz.neustar.dece.portal.utils.APIErrorMapUtil;
import biz.neustar.dece.portal.utils.ExceptionUtils;
import biz.neustar.dece.portal.utils.ServiceLocator;
import biz.neustar.dece.portal.utils.SessionUtils;
import biz.neustar.dece.ui.UIDeceException;
import biz.neustar.dece.ui.UIError;
import biz.neustar.dece.ui.dvo.AccountMergeDVO;
import biz.neustar.dece.ui.dvo.AccountUserRelationshipDVO;
import biz.neustar.dece.ui.dvo.UserAuthDVO;
import biz.neustar.dece.ui.dvo.UserSignInDVO;
import biz.neustar.dece.ui.service.UILoginService;

@ManagedBean(name = "accountMergeBean")
@RequestScoped
public class AccountMergeBean
{
	private String stepRender = "stepOne";
	private static final Logger ACCOUNTMERGELOGGER = Logger.getLogger(LoginUserBean.class);
	private String userName;
	private String password;
	private boolean exceededAtttempts = false;
	private String forErrStatus;
	private FacesContext context = FacesContext.getCurrentInstance();
	private SessionUtils sessionUtils = new SessionUtils();
	private HashMap<String, String> errorValueMap;
	private ResourceBundle errorsBundle = sessionUtils.getErrorTxtsBundle();
	private HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
			.getExternalContext().getSession(true);
	private String forCaptchaRefresh = "no";
	private String captchaId;
	private String captchaValue;
	private boolean loginUserSelected = false;

	private long ownAccountMediaCount = 0;
	private long mergingAccountMediaCount = 0;
	private long mergedAccountMediaCount = 0;

	private int ownAccountMemberCount = 0;
	private int mergingAccountMemberCount = 0;
	private int mergedAccountMemberCount = 0;

	private List<String> ownAccountDeviceList = null;
	private List<String> mergingAccountDeviceList = null;
	private List<String> mergedAccountDeviceList = null;

	private int ownAccountDeviceCount = 0;
	private int mergeAccountDeviceCount = 0;

	private String ownAccRecentAddedMediaFile = null;
	private String mergeAccRecentAddedMediaFile = null;

	private Map<String, AccountUserRelationshipDVO> ownAccountMemberMap = null;
	private Map<String, AccountUserRelationshipDVO> mergingAccountMemberMap = null;
	private Map<String, AccountUserRelationshipDVO> mergedAccountMemberMap = null;

	private Map<String, String> selectedMembersMap = null;

	private String ownRecentlyAddedMedia = "";
	private String mergeRecentlyAddedMedia = "";

	public boolean isLoginUserSelected()
	{
		return loginUserSelected;
	}

	public void setLoginUserSelected(boolean loginUserSelected)
	{
		this.loginUserSelected = loginUserSelected;
	}

	public Map<String, String> getSelectedMembersMap()
	{
		return selectedMembersMap;
	}

	public void setSelectedMembersMap(Map<String, String> selectedMembersMap)
	{
		this.selectedMembersMap = selectedMembersMap;
	}

	private List<String> mergingMemberList = null;

	public String getOwnRecentlyAddedMedia()
	{
		return ownRecentlyAddedMedia;
	}

	public void setOwnRecentlyAddedMedia(String ownRecentlyAddedMedia)
	{
		this.ownRecentlyAddedMedia = ownRecentlyAddedMedia;
	}

	public String getMergeRecentlyAddedMedia()
	{
		return mergeRecentlyAddedMedia;
	}

	public void setMergeRecentlyAddedMedia(String mergeRecentlyAddedMedia)
	{
		this.mergeRecentlyAddedMedia = mergeRecentlyAddedMedia;
	}

	public List<String> getMergingMemberList()
	{
		return mergingMemberList;
	}

	public void setMergingMemberList(List<String> mergingMemberList)
	{
		this.mergingMemberList = mergingMemberList;
	}

	public long getMergedAccountMediaCount()
	{
		return mergedAccountMediaCount;
	}

	public void setMergedAccountMediaCount(long mergedAccountMediaCount)
	{
		this.mergedAccountMediaCount = mergedAccountMediaCount;
	}

	public Map<String, AccountUserRelationshipDVO> getMergedAccountMemberMap()
	{
		return mergedAccountMemberMap;
	}

	public void setMergedAccountMemberMap(
			Map<String, AccountUserRelationshipDVO> mergedAccountMemberMap)
	{
		this.mergedAccountMemberMap = mergedAccountMemberMap;
	}

	public Map<String, AccountUserRelationshipDVO> getOwnAccountMemberMap()
	{
		return ownAccountMemberMap;
	}

	public void setOwnAccountMemberMap(Map<String, AccountUserRelationshipDVO> ownAccountMemberMap)
	{
		this.ownAccountMemberMap = ownAccountMemberMap;
	}

	public Map<String, AccountUserRelationshipDVO> getMergingAccountMemberMap()
	{
		return mergingAccountMemberMap;
	}

	public void setMergingAccountMemberMap(
			Map<String, AccountUserRelationshipDVO> mergingAccountMemberMap)
	{
		this.mergingAccountMemberMap = mergingAccountMemberMap;
	}

	public String getOwnAccRecentAddedMediaFile()
	{
		return ownAccRecentAddedMediaFile;
	}

	public void setOwnAccRecentAddedMediaFile(String ownAccRecentAddedMediaFile)
	{
		this.ownAccRecentAddedMediaFile = ownAccRecentAddedMediaFile;
	}

	public String getMergeAccRecentAddedMediaFile()
	{
		return mergeAccRecentAddedMediaFile;
	}

	public void setMergeAccRecentAddedMediaFile(String mergeAccRecentAddedMediaFile)
	{
		this.mergeAccRecentAddedMediaFile = mergeAccRecentAddedMediaFile;
	}

	public List<String> getMergedAccountDeviceList()
	{
		return mergedAccountDeviceList;
	}

	public void setMergedAccountDeviceList(List<String> mergedAccountDeviceList)
	{
		this.mergedAccountDeviceList = mergedAccountDeviceList;
	}

	public int getOwnAccountDeviceCount()
	{
		return ownAccountDeviceCount;
	}

	public void setOwnAccountDeviceCount(int ownAccountDeviceCount)
	{
		this.ownAccountDeviceCount = ownAccountDeviceCount;
	}

	public int getMergeAccountDeviceCount()
	{
		return mergeAccountDeviceCount;
	}

	public void setMergeAccountDeviceCount(int mergeAccountDeviceCount)
	{
		this.mergeAccountDeviceCount = mergeAccountDeviceCount;
	}

	public List<String> getOwnAccountDeviceList()
	{
		return ownAccountDeviceList;
	}

	public void setOwnAccountDeviceList(List<String> ownAccountDeviceList)
	{
		this.ownAccountDeviceList = ownAccountDeviceList;
	}

	public List<String> getMergingAccountDeviceList()
	{
		return mergingAccountDeviceList;
	}

	public void setMergingAccountDeviceList(List<String> mergingAccountDeviceList)
	{
		this.mergingAccountDeviceList = mergingAccountDeviceList;
	}

	public long getOwnAccountMediaCount()
	{
		return ownAccountMediaCount;
	}

	public void setOwnAccountMediaCount(long ownAccountMediaCount)
	{
		this.ownAccountMediaCount = ownAccountMediaCount;
	}

	public long getMergingAccountMediaCount()
	{
		return mergingAccountMediaCount;
	}

	public void setMergingAccountMediaCount(long mergeAccAccountMediaCount)
	{
		this.mergingAccountMediaCount = mergeAccAccountMediaCount;
	}

	public int getMergingAccountMemberCount()
	{
		return mergingAccountMemberCount;
	}

	public void setMergingAccountMemberCount(int mergingAccountMemberCount)
	{
		this.mergingAccountMemberCount = mergingAccountMemberCount;
	}

	public int getMergedAccountMemberCount()
	{
		return mergedAccountMemberCount;
	}

	public void setMergedAccountMemberCount(int mergedAccountMemberCount)
	{
		this.mergedAccountMemberCount = mergedAccountMemberCount;
	}

	public int getOwnAccountMemberCount()
	{
		return ownAccountMemberCount;
	}

	public void setOwnAccountMemberCount(int ownAccountMemberCount)
	{
		this.ownAccountMemberCount = ownAccountMemberCount;
	}

	public String getStepRender()
	{
		return stepRender;
	}

	public void setStepRender(String stepRender)
	{
		this.stepRender = stepRender;
	}

	public String getCaptchaId()
	{
		return captchaId;
	}

	public void setCaptchaId(String captchaId)
	{
		this.captchaId = captchaId;
	}

	public String getCaptchaValue()
	{
		return captchaValue;
	}

	public void setCaptchaValue(String captchaValue)
	{
		this.captchaValue = captchaValue;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public HashMap<String, String> getErrorValueMap()
	{
		return errorValueMap;
	}

	public void setErrorValueMap(HashMap<String, String> errorValueMap)
	{
		this.errorValueMap = errorValueMap;
	}

	public long getCurrentTime()
	{
		return new Date().getTime();
	}

	public void setForErrStatus(String forErrStatus)
	{
		this.forErrStatus = forErrStatus;
	}

	public String getForErrStatus()
	{
		return forErrStatus;
	}

	public boolean isExceededAtttempts()
	{
		return exceededAtttempts;
	}

	public void setExceededAtttempts(boolean exceededAtttempts)
	{
		this.exceededAtttempts = exceededAtttempts;
	}

	public String getForCaptchaRefresh()
	{
		return forCaptchaRefresh;
	}

	public void setForCaptchaRefresh(String forCaptchaRefresh)
	{
		this.forCaptchaRefresh = forCaptchaRefresh;
	}

	public AccountMergeBean()
	{
		errorValueMap = new HashMap<String, String>();
		// captchaId = session.getId();
		if (null != session && session.getAttribute("pageError") != null)
		{
			if (null != session.getAttribute("accountLockedErr")
					&& session.getAttribute("accountLockedErr").equals("forAccountLockedError"))
			{
				forErrStatus = "accountLocked";
				session.removeAttribute("accountLockedErr");
			}
			else if (null != session.getAttribute("memberRemovedErr")
					&& session.getAttribute("memberRemovedErr").equals("forMemberRemovedError"))
			{
				forErrStatus = "memberRemoved";
				session.removeAttribute("memberRemovedErr");
			}
			errorValueMap.put("pageError",
					errorsBundle.getString(session.getAttribute("pageError").toString()));
			session.removeAttribute("pageError");
		}
	}

	/**
	 * Login credential Check and Navigation control
	 * 
	 * @return navigation string.
	 */
	public void doLogin()
	{
		if (validateFields())
		{
			UILoginService service = ServiceLocator.getUiLoginService();
			if (service != null && userName != null && password != null)
			{
				try
				{
					HttpServletRequest request = (HttpServletRequest) context.getExternalContext()
							.getRequest();
					UserSignInDVO signinDVO = service.login(userName.trim(), password.trim(),
							request.getRemoteHost());
					if (signinDVO != null && signinDVO.getUserName() != null)
					{
						String remoteHost = request.getRemoteHost();
						UserAuthDVO auth = new UserAuthDVO();
						auth.setClientIP(remoteHost);
						auth.setNodeAccountId(signinDVO.getNodeAccountId());
						auth.setNodeUserId(signinDVO.getNodeUserId());
						auth.setInternalAccountId(signinDVO.getInternalAccountId());
						auth.setInternalUserId(signinDVO.getInternalUserId());
						// @@TODO : Need to add corresponding pages below
						auth.setPreviousWebPage("MYHOUSEHOLD_DASHBOARD");
						auth.setCurrentWebPage("MYHOUSEHOLD_DASHBOARD");
						session.setAttribute("MergeAccountAuth", auth);
						session.setAttribute("MergeAccountDisplayName",
								signinDVO.getUserDisplayName());
						ACCOUNTMERGELOGGER.info("SignIn success; Privilege : "
								+ signinDVO.getPrivilege()
								+ "; isAcceptedRecentTermsAndConditions: "
								+ signinDVO.getNextPage());
						errorValueMap.remove("pageError");
					}
					else
					{
						ACCOUNTMERGELOGGER.error("SignIn Failed UserSignInDVO Object is null");
					}
				}
				catch (UIDeceException ex)
				{
					ExceptionUtils.processUIDECEException("HOMEPAGE_LOGIN",
							"ClassName:AccountMergeBean_MethodName:doLogin", ex,
							APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, errorsBundle);
					Iterator<UIError> itrObj = ex.getErrors().iterator();
					while (itrObj.hasNext())
					{
						UIError uiErrObj = itrObj.next();
						String errorCodeObj = uiErrObj.getErrorCode();
						if (errorCodeObj
								.equalsIgnoreCase("ACCOUNT_USER_EXCEEDED_ALLOWED_FAILED_LOGIN_ATTEMPTS"))
						{
							forErrStatus = "ExceededAttempts";
							this.exceededAtttempts = true;
							if (null != forCaptchaRefresh && forCaptchaRefresh.equals("no"))
								forCaptchaRefresh = "doRefresh";
							errorValueMap
									.put("pageError",
											errorsBundle
													.getString("ACCOUNT_USER_EXCEEDED_ALLOWED_FAILED_LOGIN_ATTEMPTS"));
						}
						else if (errorCodeObj.equalsIgnoreCase("ACCOUNT_USER_STATUS_LOCKED"))
						{
							forErrStatus = "locked";
							errorValueMap.put("pageError",
									errorsBundle.getString("ACCOUNT_USER_STATUS_LOCKED"));
						}
						else if (errorCodeObj
								.equalsIgnoreCase("REQUEST_ACCOUNT_USER_CREDENTIALS_INVALID"))
						{
							errorValueMap.put("pageError", errorsBundle
									.getString("REQUEST_ACCOUNT_USER_CREDENTIALS_INVALID"));
						}
					}
				}

			}
		}
	}

	/**
	 * will add merged account members into a list to display in UI
	 * 
	 * @return null
	 */
	public String getAccountsDetail()
	{
		try
		{
			readAccountsUserDetail();
			mergedAccountMemberCount = ownAccountMemberCount + mergingAccountMemberCount;
			mergedAccountMemberMap = new HashMap<String, AccountUserRelationshipDVO>();
			loginUserSelected = true;

			/*
			 * Checking for the member with display name as null. It exist, then
			 * logging error message.
			 * 
			 * Note: error messages need to be replaced, so that they support
			 * I18n.
			 */
			if (!ownAccountMemberMap.containsKey(null)
					&& !mergingAccountMemberMap.containsKey(null))
			{
				for (String user : ownAccountMemberMap.keySet())
				{
					if (ownAccountMemberMap.get(user).getChildDetails() != null)
					{
						if (!ownAccountMemberMap.get(user).getChildDetails().containsValue(null))
						{
							mergedAccountMemberMap.put(user, ownAccountMemberMap.get(user));
						}
						else
						{
							errorValueMap.put("pageError",
									"One of your account member do not have display name!");
							break;
						}
					}
					else
					{
						mergedAccountMemberMap.put(user, ownAccountMemberMap.get(user));
					}
				}

				if (errorValueMap.get("pageError") == null)
				{
					for (String user : mergingAccountMemberMap.keySet())
					{

						if (mergingAccountMemberMap.get(user).getChildDetails() != null)
						{
							if (!mergingAccountMemberMap.get(user).getChildDetails()
									.containsValue(null))
							{
								mergedAccountMemberMap.put(user, mergingAccountMemberMap.get(user));
							}
							else
							{
								errorValueMap.put("pageError",
										"One of merging account member do not have display name!");
								break;
							}
						}
						else
						{
							mergedAccountMemberMap.put(user, mergingAccountMemberMap.get(user));
						}
					}

					if (errorValueMap.get("pageError") == null)
					{
						if (mergedAccountMemberCount > 6)
						{
							mergedAccountMemberMap = null;
							mergedAccountMemberCount = 0;
						}

						selectedMembersMap = new HashMap<String, String>();
						int userCount = 1;
						for (String ownAccMember : ownAccountMemberMap.keySet())
						{
							selectedMembersMap.put("ownAccMember" + userCount, "true");
							userCount++;

							int i = 0;
							if (ownAccountMemberMap.get(ownAccMember).getChildDetails() != null)
							{
								int mapSize = ownAccountMemberMap.get(ownAccMember)
										.getChildDetails().size();
								while (i <= mapSize)
								{
									selectedMembersMap.put("ownAccMember" + userCount, "true");
									userCount++;
									i++;
								}
							}
						}
						userCount = 1;
						for (String ownAccMember : mergingAccountMemberMap.keySet())
						{
							selectedMembersMap.put("mergingAccMember" + userCount, "true");
							userCount++;

							int i = 0;
							if (mergingAccountMemberMap.get(ownAccMember).getChildDetails() != null)
							{
								int mapSize = mergingAccountMemberMap.get(ownAccMember)
										.getChildDetails().size();
								while (i <= mapSize)
								{
									selectedMembersMap.put("mergingAccMember" + userCount, "true");
									userCount++;
									i++;
								}
							}
						}

						stepRender = "stepTwo";
					}
				}
			}
			else
			{
				if (ownAccountMemberMap.containsKey(null))
				{
					errorValueMap.put("pageError",
							"One of your account member do not have display name!");
				}
				else
				{
					errorValueMap.put("pageError",
							"One of merging account member do not have display name!");
				}
			}

			/* Need to set this if merge exceed its max count. */
			// stepRender = "stepOneError";
		}
		catch (UIDeceException exe)
		{
			ExceptionUtils.processUIDECEException("MYHOUSEHOLD_DASHBOARD",
					"ClassName:AccountMergeBean_MethodName:getAccountsDetail", exe,
					APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, errorsBundle);
		}
		return null;
	}

	/**
	 * will validate the user count for account merging based on the check box
	 * selection in UI. If count is not exceeds max count, then add the selected
	 * counts to merged account.
	 */
	public void validateMergeUserCount()
	{
		try
		{
			readAccountsUserDetail();
			getSelectedAccountMembers();
			if (mergedAccountMemberCount > 6 || !loginUserSelected)
			{
				mergedAccountMemberMap = null;
				mergedAccountMemberCount = 0;
			}
		}
		catch (UIDeceException exe)
		{
			ExceptionUtils.processUIDECEException("MYHOUSEHOLD_DASHBOARD",
					"ClassName:AccountMergeBean_MethodName:myHouseholdDisplayAction", exe,
					APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, errorsBundle);
		}
		stepRender = "stepTwo";
	}

	/**
	 * 
	 */
	public void doValidateStepTwo()
	{
		try
		{
			readAccountsUserDetail();
			getSelectedAccountMembers();
			String selectedPlayers = context.getExternalContext().getRequestParameterMap()
					.get("frmAccountMerge:selectedPlayers");
			String deviceAccDisplayName = "";
			if (selectedPlayers.equals("ownAccPlayers"))
			{
				mergedAccountDeviceList = ownAccountDeviceList;
				deviceAccDisplayName = (String) session.getAttribute("DispName");
			}
			else
			{
				mergedAccountDeviceList = mergingAccountDeviceList;
				deviceAccDisplayName = (String) session.getAttribute("MergeAccountDisplayName");
			}
			// To display below contents in next page, adding them into session
			session.setAttribute("mergedAccountMemberMap", mergedAccountMemberMap);
			session.setAttribute("mergedAccountDeviceList", mergedAccountDeviceList);
			session.setAttribute("mergedAccountDeviceCount", mergedAccountDeviceList.size());
			session.setAttribute("deviceAccDisplayName", deviceAccDisplayName);
			stepRender = "stepThree";
		}
		catch (UIDeceException exe)// need to add proper error message
		{
			ExceptionUtils.processUIDECEException("MYHOUSEHOLD_DASHBOARD",
					"ClassName:AccountMergeBean_MethodName:validateMergeUserCount", exe,
					APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, errorsBundle);
		}
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void mergeAccounts()
	{
		mergedAccountMemberMap = (Map<String, AccountUserRelationshipDVO>) session
				.getAttribute("mergedAccountMemberMap");
		mergedAccountDeviceList = (List<String>) session.getAttribute("mergedAccountDeviceList");
		mergedAccountMediaCount = Integer.parseInt(context.getExternalContext()
				.getRequestParameterMap().get("frmAccountMergeStep3:mergedMediaCount"));
		mergedAccountMemberCount = Integer.parseInt(context.getExternalContext()
				.getRequestParameterMap().get("frmAccountMergeStep3:mergedAccMemberCount"));
		stepRender = "accountMergeSuccess";
	}

	/**
	 * Validates Login Fields
	 * 
	 * @return boolean
	 */
	private boolean validateFields()
	{
		boolean validationFlag = true;
		if (userName.trim() == null || "".equals(userName.trim()) || password.trim() == null
				|| "".equals(password.trim()))
		{
			validationFlag = false;
			errorValueMap.put("pageError", errorsBundle.getString("invalidCredentialsError"));
		}
		if (exceededAtttempts)
		{
			forErrStatus = "";
			HttpServletRequest request = (HttpServletRequest) context.getExternalContext()
					.getRequest();
			String captchaValue = request.getParameter("recaptcha_response_field");
			if (captchaValue == null || "".equals(captchaValue))
			{
				validationFlag = false;
				forCaptchaRefresh = "yes";
				errorValueMap
						.put("pageError", errorsBundle.getString("CAPTCHA_INPUT_NOT_PROVIDED"));
			}
			else
			{
				RecaptchBean recaptchaBean = new RecaptchBean();
				validationFlag = recaptchaBean.validateRecaptcha();
				if (!validationFlag)
				{
					forCaptchaRefresh = "yes";
					errorValueMap
							.put("pageError", errorsBundle.getString("CAPTCHA_INPUT_MISMATCH"));
				}
				else
					forCaptchaRefresh = "no";
			}
		}
		return validationFlag;
	}

	/**
	 * 
	 * @throws UIDeceException
	 */
	private void readAccountsUserDetail() throws UIDeceException
	{
		AccountMergeDVO mergeDVO = ServiceLocator.getUiAccountMergeService()
				.retrieveAccountInformation(sessionUtils.getUserAuth(),
						(UserAuthDVO) session.getAttribute("MergeAccountAuth"));
		/*** Reading account members starts ***/
		ownAccountMemberCount = mergeDVO.getRetainingAccountMemberCount();
		mergingAccountMemberCount = mergeDVO.getRetiringAccountMemberCount();

		ownAccountMemberMap = mergeDVO.getRetainingAccountMemberMap();
		mergingAccountMemberMap = mergeDVO.getRetiringAccountMemberMap();

		/*** Reading account media starts ***/
		ownAccountMediaCount = mergeDVO.getRetainingAccountMediaCount();
		mergingAccountMediaCount = mergeDVO.getRetiringAccountMediaCount();
		mergedAccountMediaCount = ownAccountMediaCount + mergingAccountMediaCount;

		/*** Reading account device and their count starts ***/
		ownAccountDeviceList = mergeDVO.getRetainingAccountDeviceList();
		ownAccountDeviceCount = ownAccountDeviceList.size();

		mergingAccountDeviceList = mergeDVO.getRetiringAccountDeviceList();
		mergeAccountDeviceCount = mergingAccountDeviceList.size();

		ownRecentlyAddedMedia = mergeDVO.getRetainingAccountMostRecentlyAddedMovieOrTvShow();
		mergeRecentlyAddedMedia = mergeDVO.getRetiringAccountMostRecentlyAddedMovieOrTvShow();
	}

	/**
	 * will read all the selected members and add them into merged account map.
	 * 
	 * @author Md.Chand
	 */
	private void getSelectedAccountMembers()
	{
		mergedAccountMemberMap = new HashMap<String, AccountUserRelationshipDVO>();
		selectedMembersMap = new HashMap<String, String>();
		// int memberCount = 0;
		String childUser = "";
		int userCount = 0;

		/* Reading login/retaining account members */
		for (String key : ownAccountMemberMap.keySet())
		{
			++userCount;
			String user = context.getExternalContext().getRequestParameterMap()
					.get("frmAccountMerge:ownAccMember" + userCount);
			if (user != null)
			{
				// ++memberCount;
				selectedMembersMap.put("ownAccMember" + userCount, "true");

				AccountUserRelationshipDVO userRelationshipDVO = new AccountUserRelationshipDVO();
				Map<String, String> childDetails = null;
				userRelationshipDVO.setParentName(ownAccountMemberMap.get(key).getParentName());
				mergedAccountMemberMap.put(key, userRelationshipDVO);

				Map<String, String> childMap = ownAccountMemberMap.get(key).getChildDetails();
				if (childMap != null)
				{
					childDetails = new HashMap<String, String>();
					for (String childMapKey : childMap.keySet())
					{
						++userCount;
						childUser = context.getExternalContext().getRequestParameterMap()
								.get("frmAccountMerge:ownAccMember" + userCount);
						if (childUser != null)
						{
							// ++memberCount;
							childDetails.put(childMapKey, childMap.get(childMapKey));
							selectedMembersMap.put("ownAccMember" + userCount, "true");
						}
					}
					if (childDetails.size() > 0)
					{
						userRelationshipDVO.setChildDetails(childDetails);
					}
				}
				checkForLoggedinUser(key);
			}
			else
			{
				if (ownAccountMemberMap.get(key).getChildDetails() != null)
				{
					userCount += ownAccountMemberMap.get(key).getChildDetails().size();
				}
			}
		}

		/* Reading merging/retiring account members */
		userCount = 0;
		for (String key : mergingAccountMemberMap.keySet())
		{
			++userCount;
			String user = context.getExternalContext().getRequestParameterMap()
					.get("frmAccountMerge:mergingAccMember" + userCount);
			if (user != null)
			{
				// ++memberCount;
				selectedMembersMap.put("mergingAccMember" + userCount, "true");

				AccountUserRelationshipDVO userRelationshipDVO = new AccountUserRelationshipDVO();
				Map<String, String> childDetails = null;
				userRelationshipDVO.setParentName(mergingAccountMemberMap.get(key).getParentName());
				mergedAccountMemberMap.put(key, userRelationshipDVO);

				Map<String, String> childMap = mergingAccountMemberMap.get(key).getChildDetails();
				if (childMap != null)
				{
					childDetails = new HashMap<String, String>();
					for (String childMapKey : childMap.keySet())
					{
						++userCount;
						childUser = context.getExternalContext().getRequestParameterMap()
								.get("frmAccountMerge:mergingAccMember" + userCount);
						if (childUser != null)
						{
							// ++memberCount;
							childDetails.put(childMapKey, childMap.get(childMapKey));
							selectedMembersMap.put("mergingAccMember" + userCount, "true");
						}
					}
					if (childDetails.size() > 0)
					{
						userRelationshipDVO.setChildDetails(childDetails);
					}
				}
				checkForLoggedinUser(key);
			}
			else
			{
				if (mergingAccountMemberMap.get(key).getChildDetails() != null)
				{
					userCount += mergingAccountMemberMap.get(key).getChildDetails().size();
				}
			}
		}
		mergedAccountMemberCount = selectedMembersMap.size(); // memberCount;

		/*for (String key : mergedAccountMemberMap.keySet())
		{
			System.out.println(mergedAccountMemberMap.get(key).getParentName() + " : " + key);
			if (mergedAccountMemberMap.get(key).getChildDetails() != null)
			{
				for (String subKey : mergedAccountMemberMap.get(key).getChildDetails().keySet())
				{
					System.out.println("Child Name :"
							+ mergedAccountMemberMap.get(key).getChildDetails().get(subKey) + " : "
							+ subKey);
				}
			}
			else
			{
				System.out.println("No kids for this parent");
			}
		}*/

	}

	/**
	 * 
	 * @param nodeId
	 */
	private void checkForLoggedinUser(String nodeId)
	{
		if (!loginUserSelected
				&& (nodeId.equals(sessionUtils.getUserAuth().getNodeUserId()) || nodeId
						.equals(((UserAuthDVO) session.getAttribute("MergeAccountAuth"))
								.getNodeUserId())))
		{
			loginUserSelected = true;
		}
	}

}
