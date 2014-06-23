package biz.neustar.dece.portal.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class APIErrorMapUtil {

		public static HashMap<String, String> uilsDoLoginErrorMap;
		public static HashMap<String, String> uiGetAccountErrorMap;
		public static HashMap<String, String> uiSetHouseholdNewsFeedPrefErrorMap;		
		public static HashMap<String, String> uiasCreateAccountErrorMap;
		public static HashMap<String, String> uiasCreateUserErrorMap;
		public static HashMap<String, String> uiGetAccountUsersErrorMap;
		public static HashMap<String, String> uiUpdateAccountErrorMap;	
		public static HashMap<String, String> uiDeleteAccountErrorMap;
		public static HashMap<String, String> uiVerifySecretAnswersAndSendResetTokenErrorMap;
		public static HashMap<String,String> uiDeleteUserErrorMap;
		public static HashMap<String,String> uiGetHouseholdNewsFeedErrorMap;
		public static HashMap<String,String> uiCommonErrorCodesMap;		
		public static HashMap<String,String> uiGetFavoriteMediaMap;
		
		//Error Field Map
		public static HashMap<String,String> uiConfirmMyDetailsMap;
		public static HashMap<String,String> uiResetPasswordMap;
		public static HashMap<String,String> uiHouseholdRegistrationMap;
		public static HashMap<String,String> uiForgotPasswordMap;
		public static HashMap<String,String> uiUnlockMyProfileMap;
		public static HashMap<String,String> uiReactivateHouseholdMap;
		public static HashMap<String,String> uiLoginMap;
		

		public static HashMap<String, String> uiGetHouseHoldMediaByFilterErrorMap;		
		public static HashMap<String, String> uiSearchHouseholdMediaErrorMap;
		public static HashMap<String, String> uiHouseHoldMediaSummaryErrorMap;
		
		//Error Key & Code Map
		public static HashMap<String,String> uiAPIErrorCodesList;		
		public static List<String> uiAPIErrorCodes;
		public static HashMap<String,String> uiErrorCodeFieldMap;
		static {
			
			//****************************************  uiAPIErrorCodes **************************************//
			uiAPIErrorCodes = new ArrayList<String>();
			uiAPIErrorCodes.add("UNLOCKME_TOKEN_REQUEST_NOT_VALID");
			uiAPIErrorCodes.add("ACCOUNT_NOT_ACTIVE");
			uiAPIErrorCodes.add("ACCOUNT_STATUS_CANNOT_BE_MODIFIED");
			uiAPIErrorCodes.add("ACCOUNT_UPDATE_STATUS_INVALID");
			uiAPIErrorCodes.add("ACCOUNT_USER_EXCEEDED_ALLOWED_FAILED_LOGIN_ATTEMPTS");
			uiAPIErrorCodes.add("ACCOUNT_USER_EXCEEDED_ALLOWED_FAILED_TOKEN_VALIDATION_ATTEMPTS");
			uiAPIErrorCodes.add("ACCOUNT_USER_LANGUAGE_NOT_VALID");
			uiAPIErrorCodes.add("ACCOUNT_USER_MEDIA_EXISTS_IN_FAVORITE_MEDIA_LIST");
			uiAPIErrorCodes.add("ACCOUNT_USER_RECOVERY_TOKENS_REQUIRED");
			uiAPIErrorCodes.add("ACCOUNT_USER_SECURITY_QUESTION_DUPLICATED");
			uiAPIErrorCodes.add("ACCOUNT_USER_STATUS_DELETED");
			uiAPIErrorCodes.add("ACCOUNT_USER_STATUS_LOCKED");
			uiAPIErrorCodes.add("ACCOUNT_USER_STATUS_PENDING");
			uiAPIErrorCodes.add("ACCOUNT_USER_SURNAME_NOT_VALID");
			uiAPIErrorCodes.add("ACCOUNT_USER_TOKEN_EXPIRED");
			uiAPIErrorCodes.add("BASICUSER_CANNOT_BE_PROMOTED_WHEN_AGE_RELATED_POLICIES_EXIST");
			uiAPIErrorCodes.add("IMAGE_SIZE_LARGE");
			uiAPIErrorCodes.add("LAST_FULLACCESSUSER_CANNOT_BE_DEMOTED_TO_STANDARD_OR_BASIC_PRIVILEGE");
			uiAPIErrorCodes.add("REQUEST_ACCOUNT_ACTIVE_USER_COUNT_REACHED_MAX_LIMIT");
			uiAPIErrorCodes.add("REQUEST_ACCOUNT_COUNTRY_CODE_CANNOT_BE_NULL");
			uiAPIErrorCodes.add("REQUEST_ACCOUNT_COUNTRY_CODE_INVALID");
			uiAPIErrorCodes.add("REQUEST_ACCOUNT_DELETED");
			uiAPIErrorCodes.add("REQUEST_ACCOUNT_DISPLAY_NAME_INVALID");
			uiAPIErrorCodes.add("REQUEST_ACCOUNT_LAST_FULL_ACCESS_USER_CANNOT_BE_DELETED");
			uiAPIErrorCodes.add("REQUEST_ACCOUNT_NOT_IN_DELETED_STATUS");
			uiAPIErrorCodes.add("REQUEST_ACCOUNT_STATUS_INVALID");
			uiAPIErrorCodes.add("REQUEST_ACCOUNT_STATUS_NOT_ACTIVE");
			uiAPIErrorCodes.add("REQUEST_ACCOUNT_USER_ALREADY_DELETED");
			uiAPIErrorCodes.add("REQUEST_ACCOUNT_USER_ALTERNATE_EMAIL_INVALID");
			uiAPIErrorCodes.add("REQUEST_ACCOUNT_USER_BIRTH_DATE_INVALID");
			uiAPIErrorCodes.add("REQUEST_ACCOUNT_USER_BIRTH_DATE_REQUIRED");
			uiAPIErrorCodes.add("REQUEST_ACCOUNT_USER_CREDENTIALS_INVALID");
			uiAPIErrorCodes.add("REQUEST_ACCOUNT_USER_EXCEEDED_ALLOWED_FAILED_SECURITY_QA_ATTEMPTS");
			uiAPIErrorCodes.add("REQUEST_ACCOUNT_USER_FAVORITE_MEDIA_LIST_IS_FULL");
			uiAPIErrorCodes.add("REQUEST_ACCOUNT_USER_GIVEN_NAME_INVALID");
			uiAPIErrorCodes.add("REQUEST_ACCOUNT_USER_PRIMARY_EMAIL_INVALID");
			uiAPIErrorCodes.add("REQUEST_ACCOUNT_USER_PRIMARY_LANGUAGE_INVALID");
			uiAPIErrorCodes.add("REQUEST_ACCOUNT_USER_PRIVILEGE_INSUFFICIENT");
			uiAPIErrorCodes.add("REQUEST_ACCOUNT_USER_SECURITY_ANSWER_INVALID");
			uiAPIErrorCodes.add("REQUEST_ACCOUNT_USER_SECURITY_CREDENTIALS_INVALID");
			uiAPIErrorCodes.add("REQUEST_ACCOUNT_USER_STATUS_NOT_ACTIVE");
			uiAPIErrorCodes.add("REQUEST_ACCOUNT_USER_TOKEN_CREDENTIALS_INVALID");
			uiAPIErrorCodes.add("REQUEST_ACCOUNT_USERID_INVALID");
			uiAPIErrorCodes.add("REQUEST_ACCOUNT_USERNAME_INVALID");
			uiAPIErrorCodes.add("REQUEST_ACCOUNT_USERNAME_REGISTERED");
			uiAPIErrorCodes.add("REQUESTOR_NOT_FOUND");
			uiAPIErrorCodes.add("REQUESTOR_PRIVILEGE_INSUFFICIENT");
			uiAPIErrorCodes.add("REQUESTOR_PRIVILEGE_INSUFFICIENT_TO_PROMOTE_USER_TO_FULL_ACCESS_PRIVILEGE");
			uiAPIErrorCodes.add("REQUESTOR_PRIVILEGE_INSUFFICIENT_TO_SET_PARENTAL_CONTROL_POLICIES");
			uiAPIErrorCodes.add("REQUESTOR_PRIVILEGE_INSUFFICIENT_TO_UPDATE_USER");
			uiAPIErrorCodes.add("REQUESTOR_PRIVILEGE_INSUFFICIENT_TO_UPDATE_USER_BIRTH_DATE");
			uiAPIErrorCodes.add("REQUESTOR_PRIVILEGE_INSUFFICIENT_TO_UPDATE_USER_POLICIES");
			uiAPIErrorCodes.add("REQUESTOR_PRIVILEGE_INSUFFICIENT_TO_UPDATE_USER_STATUS");
			uiAPIErrorCodes.add("REQUESTOR_STATUS_NOT_ACTIVE");
			uiAPIErrorCodes.add("UNAUTHORIZED");
			uiAPIErrorCodes.add("UNEXPECTED_ERROR");
			uiAPIErrorCodes.add("USER_NOT_ACTIVE");
			uiAPIErrorCodes.add("USER_STATUS_NOT_ACTIVE");
			uiAPIErrorCodes.add("USER_SUSPENDED_OR_DELETE");
			uiAPIErrorCodes.add("ACCOUNT_USER_PRIMARY_EMAIL_NOT_VERIFIED");
			uiAPIErrorCodes.add("ACCOUNT_USER_PASSWORD_NOT_VALID");
			uiAPIErrorCodes.add("TOU_NOT_ACCEPTED_BY_CLG");
			uiAPIErrorCodes.add("COPPA_NOT_ACCEPTED_BY_CLG");
			uiAPIErrorCodes.add("ACCOUNT_USER_STATUS_BLOCKED_CLG");
			uiAPIErrorCodes.add("LAST_FULL_ACCESS_USER_CANNOT_BE_DEMOTED_TO_STANDARD_OR_BASIC_PRIVILEGE");
			uiAPIErrorCodes.add("UserPrivilegeCannotBeChanged");
			uiAPIErrorCodes.add("UserIdUnmatched");
			uiAPIErrorCodes.add("CHILD_YOUTH_MEMBERS_MUST_HAVE_LEGAL_GUARDIAN_CONNECTED");
			uiAPIErrorCodes.add("STANDARD_USER_NOT_ALLOWED_TO_UPDATE_FULL_ACCESS_USER_INFORMATION");
			uiAPIErrorCodes.add("FIRST_USER_MUST_BE_18_OR_OLDER");
			uiAPIErrorCodes.add("REQUESTOR_NOT_ALLOWED_TO_CREATE_USERS");
			uiAPIErrorCodes.add("FULL_ACCESS_USER_MUST_BE_18_OR_OLDER");
			uiAPIErrorCodes.add("REQUESTOR_NOT_ALLOWED_TO_CREATE_USERS_BETWEEN_AGES_13AND17");
			uiAPIErrorCodes.add("USER_CANNOT_BE_UNDER_13_YEARS_OF_AGE");
			uiAPIErrorCodes.add("ACCOUNT_IN_SUSPENDED_OR_FORCEDDELETE_OR_OTHER_STATUS");
			uiAPIErrorCodes.add("UNAUTHORIZED_TO_DELETE_USERS_IN_SUSPENDED_STATUS");
			uiAPIErrorCodes.add("REQUESTOR_PRIVILEGE_INSUFFICIENT_TO_CREATE_FULL_ACCESS_USER");
			uiAPIErrorCodes.add("REQUESTOR_NOT_ALLOWED_TO_CREATE_CHILD_OR_YOUTH_USERS");
			uiAPIErrorCodes.add("REQUESTOR_PRIVILEGE_INSUFFICIENT_TO_DELETE_FULL_ACCESS_USER");
			uiAPIErrorCodes.add("REQUESTOR_NOT_ALLOWED_TO_UPDATE_USER_INFORMATION");
			uiAPIErrorCodes.add("REQUESTOR_NOT_ALLOWED_TO_UPDATE_USER_PI");
			uiAPIErrorCodes.add("REQUESTOR_NOT_ALLOWED_TO_UPDATE_USER_ACCESSLEVEL");
			uiAPIErrorCodes.add("LEGAL_GUARDIAN_MUST_BE_FULL_ACCESS_USER");
			uiAPIErrorCodes.add("USER_PRIVILEGE_CHANGE_NOT_ALLOWED_FOR_CLG_USERS");
			uiAPIErrorCodes.add("REQUESTOR_PERMISSION_INSUFFICIENT_TO_DELETE_USER");
			uiAPIErrorCodes.add("REQUESTOR_NOT_ALLOWED_TO_UPDATE_OTHER_USERS");
			uiAPIErrorCodes.add("CHILD_MEMBERS_WITHOUT_COPPA_POLICY_CANNOT_BE_UPDATED");
			uiAPIErrorCodes.add("DEVICE_REMOVE_LIMIT_REACHED_FOR_HOUSEHOLD");
			uiAPIErrorCodes.add("LEGAL_GUARDIAN_USER_CANNOT_BE_DELETED");
			uiAPIErrorCodes.add("ACCOUNT_USER_STATUS_SUSPENDED");
			uiAPIErrorCodes.add("REQUEST_USER_ID_UNMATCHED");
			uiAPIErrorCodes.add("ACCOUNT_MAX_USER_CREATION_DELETION_LIMIT_REACHED");
			uiAPIErrorCodes.add("FORGOT_PASSWORD_TOKEN_REQUEST_NOT_VALID"); 
			uiAPIErrorCodes.add("ACCOUNT_DEVICE_COUNT_EXCEED_MAX_LIMIT");
			uiAPIErrorCodes.add("DEVICE_ID_IS_INVALID");
			 

			//****************************************  uiAPIErrorCodes **************************************//
			
			
			//****************************************  uiErrorCodeFieldMap **************************************//
			uiErrorCodeFieldMap = new HashMap<String, String>();
			uiErrorCodeFieldMap.put("ACCOUNT_NOT_ACTIVE","pageError");
			uiErrorCodeFieldMap.put("ACCOUNT_STATUS_CANNOT_BE_MODIFIED","pageError");
			uiErrorCodeFieldMap.put("ACCOUNT_UPDATE_STATUS_INVALID","pageError");
			uiErrorCodeFieldMap.put("ACCOUNT_USER_EXCEEDED_ALLOWED_FAILED_LOGIN_ATTEMPTS","pageError");
			uiErrorCodeFieldMap.put("ACCOUNT_USER_EXCEEDED_ALLOWED_FAILED_TOKEN_VALIDATION_ATTEMPTS","pageError");
			uiErrorCodeFieldMap.put("ACCOUNT_USER_LANGUAGE_NOT_VALID","pageError");
			uiErrorCodeFieldMap.put("ACCOUNT_USER_MEDIA_EXISTS_IN_FAVORITE_MEDIA_LIST","pageError");
			uiErrorCodeFieldMap.put("ACCOUNT_USER_RECOVERY_TOKENS_REQUIRED","pageError");
			uiErrorCodeFieldMap.put("ACCOUNT_USER_SECURITY_QUESTION_DUPLICATED","secQuestion2Error");
			uiErrorCodeFieldMap.put("ACCOUNT_USER_STATUS_DELETED","pageError");
			uiErrorCodeFieldMap.put("ACCOUNT_USER_STATUS_LOCKED","pageError");
			uiErrorCodeFieldMap.put("ACCOUNT_USER_STATUS_PENDING","pageError");
			uiErrorCodeFieldMap.put("ACCOUNT_USER_SURNAME_NOT_VALID","pageError");
			uiErrorCodeFieldMap.put("ACCOUNT_USER_TOKEN_EXPIRED","pageError");
			uiErrorCodeFieldMap.put("BASICUSER_CANNOT_BE_PROMOTED_WHEN_AGE_RELATED_POLICIES_EXIST","pageError");
			uiErrorCodeFieldMap.put("IMAGE_SIZE_LARGE","pageError");
			uiErrorCodeFieldMap.put("LAST_FULLACCESSUSER_CANNOT_BE_DEMOTED_TO_STANDARD_OR_BASIC_PRIVILEGE","pageError");
			uiErrorCodeFieldMap.put("REQUEST_ACCOUNT_ACTIVE_USER_COUNT_REACHED_MAX_LIMIT","pageError");
			uiErrorCodeFieldMap.put("REQUEST_ACCOUNT_COUNTRY_CODE_CANNOT_BE_NULL","countryError");
			uiErrorCodeFieldMap.put("REQUEST_ACCOUNT_COUNTRY_CODE_INVALID","countryError");
			uiErrorCodeFieldMap.put("REQUEST_ACCOUNT_DELETED","pageError");
			uiErrorCodeFieldMap.put("REQUEST_ACCOUNT_DISPLAY_NAME_INVALID","displayNameError");
			uiErrorCodeFieldMap.put("REQUEST_ACCOUNT_LAST_FULL_ACCESS_USER_CANNOT_BE_DELETED","pageError");
			uiErrorCodeFieldMap.put("REQUEST_ACCOUNT_NOT_IN_DELETED_STATUS","reactivatePageError");
			uiErrorCodeFieldMap.put("REQUEST_ACCOUNT_STATUS_INVALID","pageError");
			uiErrorCodeFieldMap.put("REQUEST_ACCOUNT_STATUS_NOT_ACTIVE","pageError");
			uiErrorCodeFieldMap.put("REQUEST_ACCOUNT_USER_ALREADY_DELETED","pageError");
			uiErrorCodeFieldMap.put("REQUEST_ACCOUNT_USER_ALTERNATE_EMAIL_INVALID","alternateEmailError");
			uiErrorCodeFieldMap.put("REQUEST_ACCOUNT_USER_BIRTH_DATE_INVALID","ageGroupError");
			uiErrorCodeFieldMap.put("REQUEST_ACCOUNT_USER_BIRTH_DATE_REQUIRED","ageGroupError");
			uiErrorCodeFieldMap.put("REQUEST_ACCOUNT_USER_CREDENTIALS_INVALID","pageError");
			uiErrorCodeFieldMap.put("REQUEST_ACCOUNT_USER_EXCEEDED_ALLOWED_FAILED_SECURITY_QA_ATTEMPTS","pageError");
			uiErrorCodeFieldMap.put("REQUEST_ACCOUNT_USER_FAVORITE_MEDIA_LIST_IS_FULL","pageError");
			uiErrorCodeFieldMap.put("REQUEST_ACCOUNT_USER_GIVEN_NAME_INVALID","displayNameError");
			uiErrorCodeFieldMap.put("REQUEST_ACCOUNT_USER_PRIMARY_EMAIL_INVALID","emailError");
			uiErrorCodeFieldMap.put("REQUEST_ACCOUNT_USER_PRIMARY_LANGUAGE_INVALID","pageError");
			uiErrorCodeFieldMap.put("REQUEST_ACCOUNT_USER_PRIVILEGE_INSUFFICIENT","pageError");
			uiErrorCodeFieldMap.put("REQUEST_ACCOUNT_USER_SECURITY_ANSWER_INVALID","pageError");
			uiErrorCodeFieldMap.put("REQUEST_ACCOUNT_USER_SECURITY_CREDENTIALS_INVALID","pageError");
			uiErrorCodeFieldMap.put("REQUEST_ACCOUNT_USER_STATUS_NOT_ACTIVE","pageError");
			uiErrorCodeFieldMap.put("REQUEST_ACCOUNT_USER_TOKEN_CREDENTIALS_INVALID","pageError");
			uiErrorCodeFieldMap.put("REQUEST_ACCOUNT_USERID_INVALID","pageError");
			uiErrorCodeFieldMap.put("REQUEST_ACCOUNT_USERNAME_INVALID","usernameError");
			uiErrorCodeFieldMap.put("REQUEST_ACCOUNT_USERNAME_REGISTERED","usernameAvailable");
			uiErrorCodeFieldMap.put("REQUESTOR_NOT_FOUND","pageError");
			uiErrorCodeFieldMap.put("REQUESTOR_PRIVILEGE_INSUFFICIENT","pageError");
			uiErrorCodeFieldMap.put("REQUESTOR_PRIVILEGE_INSUFFICIENT_TO_PROMOTE_USER_TO_FULL_ACCESS_PRIVILEGE","pageError");
			uiErrorCodeFieldMap.put("REQUESTOR_PRIVILEGE_INSUFFICIENT_TO_SET_PARENTAL_CONTROL_POLICIES","pageError");
			uiErrorCodeFieldMap.put("REQUESTOR_PRIVILEGE_INSUFFICIENT_TO_UPDATE_USER","pageError");
			uiErrorCodeFieldMap.put("REQUESTOR_PRIVILEGE_INSUFFICIENT_TO_UPDATE_USER_BIRTH_DATE","pageError");
			uiErrorCodeFieldMap.put("REQUESTOR_PRIVILEGE_INSUFFICIENT_TO_UPDATE_USER_POLICIES","pageError");
			uiErrorCodeFieldMap.put("REQUESTOR_PRIVILEGE_INSUFFICIENT_TO_UPDATE_USER_STATUS","pageError");
			uiErrorCodeFieldMap.put("REQUESTOR_STATUS_NOT_ACTIVE","pageError");
			uiErrorCodeFieldMap.put("UNAUTHORIZED","pageError");
			uiErrorCodeFieldMap.put("UNEXPECTED_ERROR","pageError");
			uiErrorCodeFieldMap.put("USER_NOT_ACTIVE","pageError");
			uiErrorCodeFieldMap.put("USER_STATUS_NOT_ACTIVE","pageError");
			uiErrorCodeFieldMap.put("ACCOUNT_USER_PRIMARY_EMAIL_NOT_VERIFIED","pageError");	
			uiErrorCodeFieldMap.put("ACCOUNT_USER_PASSWORD_NOT_VALID","passwordError");
			uiErrorCodeFieldMap.put("TOU_NOT_ACCEPTED_BY_CLG","pageError");
			uiErrorCodeFieldMap.put("COPPA_NOT_ACCEPTED_BY_CLG","pageError");
			uiErrorCodeFieldMap.put("ACCOUNT_USER_STATUS_BLOCKED_CLG","pageError");
			uiErrorCodeFieldMap.put("LAST_FULL_ACCESS_USER_CANNOT_BE_DEMOTED_TO_STANDARD_OR_BASIC_PRIVILEGE","pageError");
			uiErrorCodeFieldMap.put("UserPrivilegeCannotBeChanged","pageError");
			uiErrorCodeFieldMap.put("UserIdUnmatched","pageError");
			uiErrorCodeFieldMap.put("CHILD_YOUTH_MEMBERS_MUST_HAVE_LEGAL_GUARDIAN_CONNECTED","pageError");
			uiErrorCodeFieldMap.put("STANDARD_USER_NOT_ALLOWED_TO_UPDATE_FULL_ACCESS_USER_INFORMATION","pageError");
			uiErrorCodeFieldMap.put("FIRST_USER_MUST_BE_18_OR_OLDER","pageError");
			uiErrorCodeFieldMap.put("REQUESTOR_NOT_ALLOWED_TO_CREATE_USERS","pageError");
			uiErrorCodeFieldMap.put("FULL_ACCESS_USER_MUST_BE_18_OR_OLDER","pageError");
			uiErrorCodeFieldMap.put("REQUESTOR_NOT_ALLOWED_TO_CREATE_USERS_BETWEEN_AGES_13AND17","pageError");
			uiErrorCodeFieldMap.put("USER_CANNOT_BE_UNDER_13_YEARS_OF_AGE","pageError");
			uiErrorCodeFieldMap.put("ACCOUNT_IN_SUSPENDED_OR_FORCEDDELETE_OR_OTHER_STATUS","pageError");
			uiErrorCodeFieldMap.put("UNAUTHORIZED_TO_DELETE_USERS_IN_SUSPENDED_STATUS","pageError");
			uiErrorCodeFieldMap.put("REQUESTOR_PRIVILEGE_INSUFFICIENT_TO_CREATE_FULL_ACCESS_USER","pageError");
			uiErrorCodeFieldMap.put("REQUESTOR_NOT_ALLOWED_TO_CREATE_CHILD_OR_YOUTH_USERS","pageError");
			uiErrorCodeFieldMap.put("REQUESTOR_PRIVILEGE_INSUFFICIENT_TO_DELETE_FULL_ACCESS_USER","pageError");
			uiErrorCodeFieldMap.put("REQUESTOR_NOT_ALLOWED_TO_UPDATE_USER_INFORMATION","pageError");
			uiErrorCodeFieldMap.put("REQUESTOR_NOT_ALLOWED_TO_UPDATE_USER_PI","pageError");
			uiErrorCodeFieldMap.put("REQUESTOR_NOT_ALLOWED_TO_UPDATE_USER_ACCESSLEVEL","pageError");
			uiErrorCodeFieldMap.put("LEGAL_GUARDIAN_MUST_BE_FULL_ACCESS_USER","pageError");
			uiErrorCodeFieldMap.put("USER_PRIVILEGE_CHANGE_NOT_ALLOWED_FOR_CLG_USERS","pageError");
			uiErrorCodeFieldMap.put("REQUESTOR_PERMISSION_INSUFFICIENT_TO_DELETE_USER","pageError");
			uiErrorCodeFieldMap.put("REQUESTOR_NOT_ALLOWED_TO_UPDATE_OTHER_USERS","pageError");
			uiErrorCodeFieldMap.put("CHILD_MEMBERS_WITHOUT_COPPA_POLICY_CANNOT_BE_UPDATED","pageError");
			uiErrorCodeFieldMap.put("JUNIOR_MEMBERS_WITHOUT_COPPA_POLICY_CANNOT_BE_UPDATED","pageError");
			uiErrorCodeFieldMap.put("DEVICE_REMOVE_LIMIT_REACHED_FOR_HOUSEHOLD","pageError");
			uiErrorCodeFieldMap.put("LEGAL_GUARDIAN_USER_CANNOT_BE_DELETED","pageError");
			uiErrorCodeFieldMap.put("ACCOUNT_USER_STATUS_SUSPENDED","pageError");
			uiErrorCodeFieldMap.put("REQUEST_USER_ID_UNMATCHED","pageError");
			uiErrorCodeFieldMap.put("ACCOUNT_MAX_USER_CREATION_DELETION_LIMIT_REACHED","pageError");
			uiErrorCodeFieldMap.put("UNLOCKME_TOKEN_REQUEST_NOT_VALID","pageError");
			uiErrorCodeFieldMap.put("FORGOT_PASSWORD_TOKEN_REQUEST_NOT_VALID","pageError");
			uiErrorCodeFieldMap.put("ACCOUNT_DEVICE_COUNT_EXCEED_MAX_LIMIT","pageError");
			uiErrorCodeFieldMap.put("DEVICE_ID_IS_INVALID","pageError");
			
			

			//****************************************  uiErrorCodeFieldMap **************************************//
			
			
			
			//****************************************  uiConfirmMyDetails **************************************//
			uiConfirmMyDetailsMap = new HashMap<String, String>();
			uiConfirmMyDetailsMap.put("REQUEST_ACCOUNT_USER_TOKEN_CREDENTIALS_INVALID", "pageError");
			uiConfirmMyDetailsMap.put("REQUEST_ACCOUNT_USER_STATUS_NOT_ACTIVE", "pageError");		
			uiConfirmMyDetailsMap.put("REQUEST_ACCOUNT_USER_STATUS_PENDING", "userNameError");
			uiConfirmMyDetailsMap.put("REQUEST_ACCOUNT_USER_EXCEEDED_ALLOWED_FAILED_TOKEN_VALIDATION_ATTEMPTS", "pageError");
			uiConfirmMyDetailsMap.put("INTERNAL_SERVER_ERROR", "pageError");
			//****************************************  uiConfirmMyDetails **************************************//

			//****************************************  uiResetPasswordMap **************************************//
			uiResetPasswordMap = new HashMap<String, String>();
			uiResetPasswordMap.put("REQUEST_ACCOUNT_USER_SECURITY_CREDENTIALS_INVALID", "pageError");
			uiResetPasswordMap.put("REQUEST_ACCOUNT_USER_STATUS_NOT_ACTIVE", "pageError");		
			uiResetPasswordMap.put("REQUEST_ACCOUNT_USER_STATUS_PENDING", "pageError");			
			uiResetPasswordMap.put("INTERNAL_SERVER_ERROR", "pageError");
			//****************************************  uiResetPasswordMap **************************************//
			
			
			//****************************************  uiHouseholdRegistrationMap **************************************//
			uiHouseholdRegistrationMap = new HashMap<String, String>();
			uiHouseholdRegistrationMap.put("REQUEST_ACCOUNT_USER_SECURITY_CREDENTIALS_INVALID", "pageError");
			uiHouseholdRegistrationMap.put("REQUEST_ACCOUNT_USER_STATUS_NOT_ACTIVE", "pageError");		
			uiHouseholdRegistrationMap.put("REQUEST_ACCOUNT_USER_STATUS_PENDING", "pageError");			
			uiHouseholdRegistrationMap.put("INTERNAL_SERVER_ERROR", "pageError");
			uiHouseholdRegistrationMap.put("REQUEST_ACCOUNT_USERNAME_REGISTERED","usernameError");
			uiHouseholdRegistrationMap.put("REQUEST_ACCOUNT_USER_PASSWORD_INVALID","passwordError");
			uiHouseholdRegistrationMap.put("REQUEST_ACCOUNT_USER_GIVEN_NAME_INVALID","displayNameError");
			uiHouseholdRegistrationMap.put("REQUEST_ACCOUNT_ACTIVE_USER_COUNT_REACHED_MAX_LIMIT","pageError");
			uiHouseholdRegistrationMap.put("INTERNAL_SERVER_ERROR", "pageError");
			//****************************************  uiHouseholdRegistrationMap **************************************//
			
			//****************************************  uiForgotPasswordMap **************************************//
			uiForgotPasswordMap = new HashMap<String, String>();
			uiForgotPasswordMap.put("REQUEST_ACCOUNT_USER_SECURITY_CREDENTIALS_INVALID", "pageError");
			uiForgotPasswordMap.put("REQUEST_ACCOUNT_USER_STATUS_NOT_ACTIVE", "pageError");		
			uiForgotPasswordMap.put("REQUEST_ACCOUNT_USER_STATUS_PENDING", "pageError");			
			uiForgotPasswordMap.put("INTERNAL_SERVER_ERROR", "pageError");
			uiForgotPasswordMap.put("REQUEST_ACCOUNT_USER_EXCEEDED_ALLOWED_FAILED_SECURITY_QA_ATTEMPTS","pageError");
			uiForgotPasswordMap.put("FORGOT_PASSWORD_TOKEN_REQUEST_NOT_VALID","pageError");
			//****************************************  uiForgotPasswordMap **************************************//
			
			//****************************************  uiUnlockMyProfileMap **************************************//
			uiUnlockMyProfileMap = new HashMap<String, String>();
			uiUnlockMyProfileMap.put("REQUEST_ACCOUNT_USER_SECURITY_CREDENTIALS_INVALID", "pageError");
			uiUnlockMyProfileMap.put("REQUEST_ACCOUNT_USER_STATUS_NOT_ACTIVE", "pageError");		
			uiUnlockMyProfileMap.put("REQUEST_ACCOUNT_USER_STATUS_PENDING", "pageError");			
			uiUnlockMyProfileMap.put("INTERNAL_SERVER_ERROR", "pageError");
			uiUnlockMyProfileMap.put("REQUEST_ACCOUNT_USER_EXCEEDED_ALLOWED_FAILED_SECURITY_QA_ATTEMPTS","pageError");		
			uiUnlockMyProfileMap.put("UNLOCKME_TOKEN_REQUEST_NOT_VALID","pageError");
			//****************************************  uiUnlockMyProfileMap **************************************//
			
			//****************************************  uiReactivateHouseholdMap **************************************//
			uiReactivateHouseholdMap = new HashMap<String, String>();
			uiReactivateHouseholdMap.put("BAD_REQUEST", "pageError");
			uiReactivateHouseholdMap.put("REQUEST_ACCOUNT_USERID_INVALID", "pageError");		
			uiReactivateHouseholdMap.put("REQUEST_ACCOUNT_USER_PASSWORD_INVALID", "pageError");
			uiReactivateHouseholdMap.put("REQUEST_ACCOUNT_USER_PRIVILEGE_INSUFFICIENT", "pageError");
			uiReactivateHouseholdMap.put("REQUEST_ACCOUNT_USER_STATUS_LOCKED", "pageError");
			uiReactivateHouseholdMap.put("REQUEST_ACCOUNT_USER_EXCEEDED_ALLOWED_FAILED_SECURITY_QA_ATTEMPTS","pageError");
			uiReactivateHouseholdMap.put("REQUEST_ACCOUNT_USER_CREDENTIALS_INVALID", "pageError");						
			uiReactivateHouseholdMap.put("INTERNAL_SERVER_ERROR", "pageError");
			
						
			//****************************************  uiReactivateHouseholdMap **************************************//
			
			
			//****************************************  uiLoginMap **************************************//
			uiLoginMap = new HashMap<String, String>();
			uiLoginMap.put("SECURITY_NODE_NOT_ACTIVE", "pageError");
			uiLoginMap.put("SECURITY_ROLE_INVALID", "pageError");		
			uiLoginMap.put("SECURITY_ROLE_INVALID", "pageError");
			uiLoginMap.put("REQUEST_ACCOUNT_USER_STATUS_LOCKED", "pageError");
			uiLoginMap.put("REQUEST_ACCOUNT_USER_STATUS_NOT_ACTIVE", "pageError");
			uiLoginMap.put("REQUEST_ACCOUNT_USER_NOT_FOUND","pageError");
			uiLoginMap.put("REQUEST_ACCOUNT_USER_PASSWORD_INVALID", "pageError");
			uiLoginMap.put("REQUEST_ACCOUNT_USER_EXCEEDED_ALLOWED_FAILED_LOGIN_ATTEMPTS", "pageError");			
			uiLoginMap.put("INTERNAL_SERVER_ERROR", "pageError");			
			uiLoginMap.put("ACCOUNT_USER_PRIMARY_EMAIL_NOT_VERIFIED", "pageError");

			//****************************************  uiLoginMap **************************************//
			
			uilsDoLoginErrorMap = new HashMap<String, String>();
			uilsDoLoginErrorMap.put("urn:dece:errorid:org:dece:Request:UserIDInvalid", "invalidCredentials");// Not In Document
			uilsDoLoginErrorMap.put("urn:dece:errorid:org:dece:Request:UserIdUnmatched", "invalidCredentials");// Not In Document
			uilsDoLoginErrorMap.put("urn:dece:errorid:org:dece:Security:NodeNotActive", "contactcustomerservice");
			uilsDoLoginErrorMap.put("urn:dece:errorid:org:dece:Security:RoleInvalid", "contactcustomerservice");
			uilsDoLoginErrorMap.put("urn:dece:errorid:org:dece:Database:InternalServerError", "unexpectedError");
			uilsDoLoginErrorMap.put("urn:dece:errorid:org:dece:Database:InternalServerErrorRetry", "unexpectedError");
			uilsDoLoginErrorMap.put("urn:dece:errorid:org:dece:Unauthorized", "contactcustomerservice");
			uilsDoLoginErrorMap.put("urn:dece:errorid:org:dece:Request:AccountUserStatusLocked", "contactcustomerservice");
			uilsDoLoginErrorMap.put("urn:dece:errorid:org:dece:Request:AccountUserStatusNotActive", "invalidCredentials");
			uilsDoLoginErrorMap.put("urn:dece:errorid:org:dece:Request:AccountUserNotFound", "invalidCredentials");	
			uilsDoLoginErrorMap.put("urn:dece:errorid:org:dece:Request:AccountUserPasswordInvalid", "invalidCredentials");
			uilsDoLoginErrorMap.put("urn:dece:errorid:org:dece:Request:AccountUserExceededAllowedFailedLoginAttempts", "exceededAttempts");
			uilsDoLoginErrorMap.put("urn:dece:errorid:org:dece:Request:AccountUserCredentialsInvalid","unexpectedError");
			uilsDoLoginErrorMap.put("urn:dece:errorid:org:dece:Request:AccountUserStatusPending","pendingAccountUserStatus");
			
			uiGetAccountErrorMap= new HashMap<String, String>();
			uiGetAccountErrorMap.put("urn:dece:errorid:org:dece:BadRequest", "userAuthAccountDVOCheck");
			uiGetAccountErrorMap.put("urn:dece:errorid:org:dece:InternalServerError", "unexpectedError");
			uiGetAccountErrorMap.put("urn:dece:errorid:org:dece:Request:AccountDisplayNameInvalid", "accountDisplayNameInvalid");
			
			
			uiGetAccountUsersErrorMap=new HashMap<String,String>();
			uiGetAccountErrorMap.put("urn:dece:errorid:org:dece:BadRequest", "userAuthAccountDVOCheck");
			uiGetAccountErrorMap.put("urn:dece:errorid:org:dece:InternalServerError", "unexpectedError");
			uiGetAccountErrorMap.put("urn:dece:errorid:org:dece:Request:AccountDisplayNameInvalid", "accountDisplayNameInvalid");
			
			uiUpdateAccountErrorMap=new HashMap<String,String>();
			uiUpdateAccountErrorMap.put("urn:dece:errorid:org:dece:Request:AccountStatusNotActive", "accountStatusNotActive");
			uiUpdateAccountErrorMap.put("urn:dece:errorid:org:dece:Request:AccountDisplayNameInvalid", "accountDisplayNameInvalid");
			uiUpdateAccountErrorMap.put("urn:dece:errorid:org:dece:Request:LockerOptInUserInactive", "userInactive");
			uiUpdateAccountErrorMap.put("urn:dece:errorid:org:dece:Request:AccountUserPrivilegeInsufficient", "accountUserPrivilegeInsufficient");
			
			uiGetHouseholdNewsFeedErrorMap=new HashMap<String,String>();
			uiGetHouseholdNewsFeedErrorMap.put("urn:dece:errorid:org:dece:Request:AccountStatusNotActive", "accountStatusNotActive");
			
			
			uiSetHouseholdNewsFeedPrefErrorMap=new HashMap<String, String>();
			uiSetHouseholdNewsFeedPrefErrorMap.put("urn:dece:errorid:org:dece:Security:UserIdInvalid", "userIdInvalid");
			uiSetHouseholdNewsFeedPrefErrorMap.put("urn:dece:errorid:org:dece:Request:AccountUserNotFound", "accountUserNotFound");
			uiSetHouseholdNewsFeedPrefErrorMap.put("urn:dece:errorid:org:dece:Request:AccountUserPrivilegeInsufficient", "accountUserPrivilegeInsufficient");		
			
			uiDeleteAccountErrorMap= new HashMap<String, String>();
			//uiDeleteAccountErrorMap.put("urn:dece:errorid:org:dece:BadRequest", "userAuthAccountDVOCheck");
			//uiDeleteAccountErrorMap.put("urn:dece:errorid:org:dece:InternalServerError", "unexpectedError");
			uiDeleteAccountErrorMap.put("urn:dece:errorid:org:dece:Request:AccountIdUnmatched", "accountIdUnmatched");
			uiDeleteAccountErrorMap.put("urn:dece:errorid:org:dece:Request:AccountUserPrivilegeInsufficient", "accountUserPrivilegeInsufficient");
			uiDeleteAccountErrorMap.put("urn:dece:errorid:org:dece:Request:AccountStatusNotActive", "accountStatusNotActive");
			uiDeleteAccountErrorMap.put("urn:dece:errorid:org:dece:Request:AccountDeleted", "accountDelete");			
			
			
			uiDeleteUserErrorMap=new HashMap<String, String>();
			
			
			uiasCreateAccountErrorMap = new HashMap<String, String>();
			// NEED TO ADD THE CORRECT ERRORS
			
			uiasCreateAccountErrorMap.put("DeceErrorID.REQUEST_ACCOUNT_USERNAME_REGISTERED","invalidCredentials");
			uiasCreateAccountErrorMap.put("urn:dece:errorid:org:dece:Request:AccountUserPasswordInvalid","invalidpassword");
			uiasCreateAccountErrorMap.put("urn:dece:errorid:org:dece:Request:AccountUserGivenNameInvalid","invaliddisplayname");
			uiasCreateAccountErrorMap.put("urn:dece:errorid:org:dece:Request:AccountActiveUserCountReachedMaxLimit","usercountmaxlimit");
			uiasCreateAccountErrorMap.put("urn:dece:errorid:org:dece:InternalServerError","invalidCredentials");

//			uiasCreateAccountErrorMap.put("urn:dece:errorid:org:dece:Request:AccountUserExceededAllowedFailedLoginAttempts", "exceededAttempts");
//			uiasCreateAccountErrorMap.put("urn:dece:errorid:org:dece:Request:AccountUserStatusLocked", "accountLocked");
//			uiasCreateAccountErrorMap.put("urn:dece:errorid:org:dece:Request:AccountUserPasswordInvalid", "invalidCredentials");
//			uiasCreateAccountErrorMap.put("urn:dece:errorid:org:dece:Request:AccountUserStatusNotActive", "invalidCredentials");
//			uiasCreateAccountErrorMap.put("urn:dece:errorid:org:dece:Request:UserIdUnmatched", "invalidCredentials");
			
			uiasCreateUserErrorMap = new HashMap<String,String>();
			
			//Unlock and forgot password error details.
			uiVerifySecretAnswersAndSendResetTokenErrorMap=new HashMap<String, String>();			
			uiVerifySecretAnswersAndSendResetTokenErrorMap.put("urn:dece:errorid:org:dece:Request:AccountUserSecurityCredentialsInvalid","accountUserSecurityCredentialsInvalid");
			uiVerifySecretAnswersAndSendResetTokenErrorMap.put("urn:dece:errorid:org:dece:InternalServerError","invalidCredentials");
			uiVerifySecretAnswersAndSendResetTokenErrorMap.put("urn:dece:errorid:org:dece:Request:AccountUserStatusPending","pendingAccountUserStatus");
			 
			//Common Error codes.
			uiCommonErrorCodesMap= new HashMap<String,String>();
			uiCommonErrorCodesMap.put("urn:dece:errorid:org:dece:Security:AdminAccessDenied","adminAccessDenied");
			uiCommonErrorCodesMap.put("urn:dece:errorid:org:dece:Security:AccountIdInvalid","accountIdInvalid");
			uiCommonErrorCodesMap.put("urn:dece:errorid:org:dece:Security:NodeNotActive","nodeNotActive");
			uiCommonErrorCodesMap.put("urn:dece:errorid:org:dece:Security:UserNotActive","userNotActive");
			uiCommonErrorCodesMap.put("urn:dece:errorid:org:dece:Security:RoleInvalid","roleInvalid");
			uiCommonErrorCodesMap.put("urn:dece:errorid:org:dece:Security:LoginSuccessful","loginSuccessful");
			uiCommonErrorCodesMap.put("urn:dece:errorid:org:dece:Security:NodeIdInvalid","nodeIdInvalid");
			uiCommonErrorCodesMap.put("urn:dece:errorid:org:dece:Security:UserIdInvalid","userIdInvalid");
			uiCommonErrorCodesMap.put("urn:dece:errorid:org:dece:Security:UserIdRequired","userIdRequired");			
			uiCommonErrorCodesMap.put("urn:dece:errorid:org:dece:Security:AccountNotActive","accountNotActive");
			uiCommonErrorCodesMap.put("urn:dece:errorid:org:dece:Security:UserNotInAccount","userNotInAccount");			
			uiCommonErrorCodesMap.put("urn:dece:errorid:org:dece:Database:InternalServerError","internalServerError");
			uiCommonErrorCodesMap.put("urn:dece:errorid:org:dece:InternalServerError","internalServerError");
			
			
			uiGetFavoriteMediaMap= new HashMap<String,String>();
			uiGetFavoriteMediaMap.put("urn:dece:errorid:org:dece:Request:AccountUserPrivilegeInsufficient", "accountUserPrivilegeInsufficient");
			uiGetFavoriteMediaMap.put("urn:dece:errorid:org:dece:Request:UserIdUnmatched", "invalidCredentials");
			uiGetFavoriteMediaMap.put("urn:dece:errorid:org:dece:Request:AccountUserNotFound", "accountUserNotFound");
			uiGetFavoriteMediaMap.put("urn:dece:errorid:org:dece:BadRequest", "userAuthAccountDVOCheck");
			uiGetFavoriteMediaMap.put("urn:dece:errorid:org:dece:Database:InternalServerError","internalServerError");
			
			//Our Media
			uiGetHouseHoldMediaByFilterErrorMap = new HashMap<String, String>();
			uiGetHouseHoldMediaByFilterErrorMap.put("urn:dece:errorid:org:dece:BadRequest", "BAD_REQUEST");
			uiGetHouseHoldMediaByFilterErrorMap.put("urn:dece:errorid:org:dece:InternalServerError", "INTERNAL_SERVER_ERROR");
			//Our Media			
			uiSearchHouseholdMediaErrorMap = new HashMap<String, String>();
			uiSearchHouseholdMediaErrorMap.put("urn:dece:errorid:org:dece:BadRequest", "BAD_REQUEST");
			uiSearchHouseholdMediaErrorMap.put("urn:dece:errorid:org:dece:InternalServerError", "INTERNAL_SERVER_ERROR");
			//Our Media
			uiHouseHoldMediaSummaryErrorMap = new HashMap<String, String>();
			uiHouseHoldMediaSummaryErrorMap.put("urn:dece:errorid:org:dece:BadRequest", "BAD_REQUEST");
			uiHouseHoldMediaSummaryErrorMap.put("urn:dece:errorid:org:dece:InternalServerError", "INTERNAL_SERVER_ERROR");
			
			uiAPIErrorCodesList = new HashMap<String,String>();
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:","NS_PREFIX");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:BadRequest","BAD_REQUEST");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Database:InternalServerError","DATABASE_INTERNAL_SERVER_ERROR");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Database:InternalServerErrorRetry","DATABASE_INTERNAL_SERVER_ERROR_RETRY");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Database:NodeAccountIdFailure","DATABASE_NODE_ACCOUNT_ID_FAILURE");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Database:NodeUserIdFailure","DATABASE_NODE_USER_ID_FAILURE");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:InternalServerError","INTERNAL_SERVER_ERROR");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:MethodNotSupported","METHOD_NOT_SUPPORTED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:NotFound","NOT_FOUND");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:NotImplemented","NOT_IMPLEMENTED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountAccountIdInvalid","REQUEST_ACCOUNT_ACCOUNT_ID_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountDeleted","REQUEST_ACCOUNT_DELETED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountDisplayNameInvalid","REQUEST_ACCOUNT_DISPLAY_NAME_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountCountryCodeInvalid","REQUEST_ACCOUNT_COUNTRY_CODE_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountCountryCodeCannotBeNull","REQUEST_ACCOUNT_COUNTRY_CODE_CANNOT_BE_NULL");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountIdUnmatched","REQUEST_ACCOUNT_ID_UNMATCHED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountLanguageIdInvalid","REQUEST_ACCOUNT_LANGUAGE_ID_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountStatusNotActive","REQUEST_ACCOUNT_STATUS_NOT_ACTIVE");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountStatusInvalid","REQUEST_ACCOUNT_STATUS_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountStatusInvalidForUpdate","ACCOUNT_UPDATE_STATUS_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountUserAccountIdNotFound","REQUEST_ACCOUNT_USER_ACCOUNT_ID_NOT_FOUND");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountUserAddressInvalid","REQUEST_ACCOUNT_USER_ADDRESS_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountUserAllowedRatingDuplicated","REQUEST_ACCOUNT_USER_ALLOWED_RATING_DUPLICATED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountUserAllowedRatingNotAvailable","REQUEST_ACCOUNT_USER_ALLOWED_RATING_NOT_AVAILABLE");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountUserAlternateEmailInvalid","REQUEST_ACCOUNT_USER_ALTERNATE_EMAIL_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountUserBirthDateInvalid","REQUEST_ACCOUNT_USER_BIRTH_DATE_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountUserBirthDateRequired","REQUEST_ACCOUNT_USER_BIRTH_DATE_REQUIRED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountActiveUserCountReachedMaxLimit","REQUEST_ACCOUNT_ACTIVE_USER_COUNT_REACHED_MAX_LIMIT");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountUserDisplayNameInvalid","REQUEST_ACCOUNT_USER_DISPLAY_NAME_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountUserDisplayNameRegistered","REQUEST_ACCOUNT_USER_DISPLAY_NAME_REGISTERED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountUserEmailAddressDuplicated","REQUEST_ACCOUNT_USER_EMAIL_ADDRESS_DUPLICATED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountUserFamilyNameInvalid","REQUEST_ACCOUNT_USER_FAMILY_NAME_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountUserGivenNameInvalid","REQUEST_ACCOUNT_USER_GIVEN_NAME_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountUserInfoRequired","REQUEST_ACCOUNT_USER_INFO_REQUIRED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountUserLanguageDuplicated","REQUEST_ACCOUNT_USER_LANGUAGE_DUPLICATED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountUserMonikerInvalid","REQUEST_ACCOUNT_USER_MONIKER_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountUserNameSuffixInvalid","REQUEST_ACCOUNT_USER_NAME_SUFFIX_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountUserNotFound","REQUEST_ACCOUNT_USER_NOT_FOUND");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountUserPasswordInvalid","REQUEST_ACCOUNT_USER_PASSWORD_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountUserTelephoneNumberInvalid","REQUEST_ACCOUNT_USER_TELEPHONE_NUMBER_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountUserMobilePhoneNumberInvalid","REQUEST_ACCOUNT_USER_MOBILE_PHONE_NUMBER_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountUserPrimaryEmailInvalid","REQUEST_ACCOUNT_USER_PRIMARY_EMAIL_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountUserPrimaryEmailRegistered","REQUEST_ACCOUNT_USER_PRIMARY_EMAIL_REGISTERED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountUserPrimaryLanguageInvalid","REQUEST_ACCOUNT_USER_PRIMARY_LANGUAGE_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountUserPrivilegeInsufficient","REQUEST_ACCOUNT_USER_PRIVILEGE_INSUFFICIENT");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountUserCannotPromoteUserToHigherPrivilege","REQUEST_ACCOUNT_USER_CANNOT_PROMOTE_USER_TO_HIGHER_PRIVILEGE");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountUserRatingPinInvalid","REQUEST_ACCOUNT_USER_RATING_PIN_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountUserSurnameInvalid","REQUEST_ACCOUNT_USER_SURNAME_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountUserSortNameInvalid","REQUEST_ACCOUNT_USER_SORT_NAME_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountUserStatusNotActive","REQUEST_ACCOUNT_USER_STATUS_NOT_ACTIVE");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountUserUserLanguageInvalid","REQUEST_ACCOUNT_USER_USER_LANGUAGE_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountUserUserLanguageRequired","REQUEST_ACCOUNT_USER_USER_LANGUAGE_REQUIRED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountUsernameInvalid","REQUEST_ACCOUNT_USERNAME_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountUsernameRegistered","REQUEST_ACCOUNT_USERNAME_REGISTERED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountUserAlreadyDeleted","REQUEST_ACCOUNT_USER_ALREADY_DELETED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountUserStatusDeleted","REQUEST_ACCOUNT_USER_STATUS_DELETED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountUserStatusPending","REQUEST_ACCOUNT_USER_STATUS_PENDING");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountUserSecurityAnswerInvalid","REQUEST_ACCOUNT_USER_SECURITY_ANSWER_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountUserExceededAllowedFailedLoginAttempts","REQUEST_ACCOUNT_USER_EXCEEDED_ALLOWED_FAILED_LOGIN_ATTEMPTS");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountUserExceededAllowedFailedTokenValidationAttempts","REQUEST_ACCOUNT_USER_EXCEEDED_ALLOWED_FAILED_TOKEN_VALIDATION_ATTEMPTS");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountUserStatusLocked","REQUEST_ACCOUNT_USER_STATUS_LOCKED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountUserCredentialsInvalid","REQUEST_ACCOUNT_USER_CREDENTIALS_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountUserSecurityCredentialsInvalid","REQUEST_ACCOUNT_USER_SECURITY_CREDENTIALS_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountUserRecoveryTokensRequired","REQUEST_ACCOUNT_USER_RECOVERY_TOKENS_REQUIRED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountUserTokenCredentialsInvalid","REQUEST_ACCOUNT_USER_TOKEN_CREDENTIALS_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountUserExceededAllowedFailedSecurityQAAttempts","REQUEST_ACCOUNT_USER_EXCEEDED_ALLOWED_FAILED_SECURITY_QA_ATTEMPTS");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:LastUserofAccountCannotBeDeleted","REQUEST_ACCOUNT_LAST_USER_CANNOT_BE_DELETED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:LastFullAccessUserofAccountCannotBeDeleted","REQUEST_ACCOUNT_LAST_FULL_ACCESS_USER_CANNOT_BE_DELETED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AdminConfigCategoryClassInvalid","REQUEST_ADMIN_CONFIG_CATEGORY_CLASS_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AdminConfigInvalid","REQUEST_ADMIN_CONFIG_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AdminConfigNameNotFound","REQUEST_ADMIN_CONFIG_NAME_NOT_FOUND");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AdminConfigTypeInvalid","REQUEST_ADMIN_CONFIG_TYPE_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AdminConfigTypeMismatch","REQUEST_ADMIN_CONFIG_TYPE_MISMATCH");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AdminConfigUpdated","REQUEST_ADMIN_CONFIG_UPDATED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AdminConfigValueInvalid","REQUEST_ADMIN_CONFIG_VALUE_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AlidInvalid","REQUEST_ALID_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:ApidInvalid","REQUEST_APID_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:BundleIdInvalid","REQUEST_BUNDLE_ID_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:ContentIdDuplicated","REQUEST_CONTENT_ID_DUPLICATED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:ContentIdInvalid","REQUEST_CONTENT_ID_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:NodeIdUnmatched","REQUEST_NODE_ID_UNMATCHED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:OrgIdUnmatched","REQUEST_ORG_ID_UNMATCHED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:ParameterInvalid","REQUEST_PARAMETER_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:ParameterUnmatched","REQUEST_PARAMETER_UNMATCHED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsAccountInvalid","REQUEST_RIGHTS_ACCOUNT_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsAccountNotActive","REQUEST_RIGHTS_ACCOUNT_NOT_ACTIVE");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsAccountNotFound","REQUEST_RIGHTS_ACCOUNT_NOT_FOUND");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsAdultContentNotAllowed","REQUEST_RIGHTS_ADULT_CONTENT_NOT_ALLOWED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsAlidInvalid","REQUEST_RIGHTS_ALID_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsAlidNotActive","REQUEST_RIGHTS_ALID_NOT_ACTIVE");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsAlidNotFound","REQUEST_RIGHTS_ALID_NOT_FOUND");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsApidInvalid","REQUEST_RIGHTS_APID_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsBundleIdNotActive","REQUEST_RIGHTS_BUNDLE_ID_NOT_ACTIVE");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsBundleIdNotFound","REQUEST_RIGHTS_BUNDLE_ID_NOT_FOUND");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsContentHasAgeRestriction","REQUEST_RIGHTS_CONTENT_HAS_AGE_RESTRICTION");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsContentIdNotActive","REQUEST_RIGHTS_CONTENT_ID_NOT_ACTIVE");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsContentIdNotFound","REQUEST_RIGHTS_CONTENT_ID_NOT_FOUND");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsDataInvalidNumber","REQUEST_RIGHTS_DATA_INVALID_NUMBER");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsDataInvalidProfile","REQUEST_RIGHTS_DATA_INVALID_PROFILE");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsDataMissing","REQUEST_RIGHTS_DATA_MISSING");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsDataMissingProfile","REQUEST_RIGHTS_DATA_MISSING_PROFILE");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsDataNoValidRights","REQUEST_RIGHTS_DATA_NO_VALID_RIGHTS");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsDisplayLanguageInvalid","REQUEST_RIGHTS_DISPLAY_LANGUAGE_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsDisplayNameInvalid","REQUEST_RIGHTS_DISPLAY_NAME_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsDuplicatedTransaction","REQUEST_RIGHTS_DUPLICATED_TRANSACTION");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsExclsuiveAccessUserIdInvalid","REQUEST_RIGHTS_EXCLSUIVE_ACCESS_USER_ID_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsExclusiveAccessUserIdNotActive","REQUEST_RIGHTS_EXCLUSIVE_ACCESS_USER_ID_NOT_ACTIVE");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsExclusiveAccessUserIdNotFound","REQUEST_RIGHTS_EXCLUSIVE_ACCESS_USER_ID_NOT_FOUND");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsExclusiveAccessUserIdNotInAccount","REQUEST_RIGHTS_EXCLUSIVE_ACCESS_USER_ID_NOT_IN_ACCOUNT");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsFulfillmentLocDuplicated","REQUEST_RIGHTS_FULFILLMENT_LOC_DUPLICATED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsFulfillmentLocInvalid","REQUEST_RIGHTS_FULFILLMENT_LOC_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsFulfillmentLocInvalidType","REQUEST_RIGHTS_FULFILLMENT_LOC_INVALID_TYPE");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsFulfillmentLocMissing","REQUEST_RIGHTS_FULFILLMENT_LOC_MISSING");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsFulfillmentManifestLocInvalidNumber","REQUEST_RIGHTS_FULFILLMENT_MANIFEST_LOC_INVALID_NUMBER");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsFulfillmentWebLocInvalidNumber","REQUEST_RIGHTS_FULFILLMENT_WEB_LOC_INVALID_NUMBER");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsHdNotAllowed","REQUEST_RIGHTS_HD_NOT_ALLOWED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsInvalidPurchaseAccountId","REQUEST_RIGHTS_INVALID_PURCHASE_ACCOUNT_ID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsInvalidPurchaseTime","REQUEST_RIGHTS_INVALID_PURCHASE_TIME");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsInvalidPurchaseUserId","REQUEST_RIGHTS_INVALID_PURCHASE_USER_ID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsInvalidRetailerId","REQUEST_RIGHTS_INVALID_RETAILER_ID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsInvalidRetailerTransactionId","REQUEST_RIGHTS_INVALID_RETAILER_TRANSACTION_ID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsLicenseAcqLocDuplicated","REQUEST_RIGHTS_LICENSE_ACQ_LOC_DUPLICATED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsLicenseAcqLocInvalid","REQUEST_RIGHTS_LICENSE_ACQ_LOC_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsLicenseAcqLocInvalidDrm","REQUEST_RIGHTS_LICENSE_ACQ_LOC_INVALID_DRM");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsLicenseAcqLocInvalidNumber","REQUEST_RIGHTS_LICENSE_ACQ_LOC_INVALID_NUMBER");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsLicenseAcqLocMissing","REQUEST_RIGHTS_LICENSE_ACQ_LOC_MISSING");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsPdNotAllowed","REQUEST_RIGHTS_PD_NOT_ALLOWED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsPurchaseAccountIdNotFound","REQUEST_RIGHTS_PURCHASE_ACCOUNT_ID_NOT_FOUND");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsPurchaseUserIdNotFound","REQUEST_RIGHTS_PURCHASE_USER_ID_NOT_FOUND");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsRentalAbsExpDate","REQUEST_RIGHTS_RENTAL_ABS_EXP_DATE");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsRestrictedContentHidden","REQUEST_RIGHTS_RESTRICTED_CONTENT_HIDDEN");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsRestrictedContentNoPurchase","REQUEST_RIGHTS_RESTRICTED_CONTENT_NO_PURCHASE");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsRetailerIdNotFound","REQUEST_RIGHTS_RETAILER_ID_NOT_FOUND");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsSdNotAllowed","REQUEST_RIGHTS_SD_NOT_ALLOWED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsSoldAsContentIdNotFound","REQUEST_RIGHTS_SOLD_AS_CONTENT_ID_NOT_FOUND");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsTokenAccessNotAllowed","REQUEST_RIGHTS_TOKEN_ACCESS_NOT_ALLOWED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsTokenDeleted","REQUEST_RIGHTS_TOKEN_DELETED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsTokenIdInvalid","REQUEST_RIGHTS_TOKEN_ID_INVALID");			
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:RightsTokenNotFound","REQUEST_RIGHTS_TOKEN_NOT_FOUND");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsTokenNotFound","REQUEST_RIGHTS_TOKEN_NOT_FOUND");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsUnratedContentBlocked","REQUEST_RIGHTS_UNRATED_CONTENT_BLOCKED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsUserIdInvalid","REQUEST_RIGHTS_USER_ID_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsUserIdNotActive","REQUEST_RIGHTS_USER_ID_NOT_ACTIVE");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsUserIdNotSpecified","REQUEST_RIGHTS_USER_ID_NOT_SPECIFIED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsUserIdUnmatched","REQUEST_RIGHTS_USER_ID_UNMATCHED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsUserNotActive","REQUEST_RIGHTS_USER_NOT_ACTIVE");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsUserNotFound","REQUEST_RIGHTS_USER_NOT_FOUND");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsViewControlUserIdInvalid","REQUEST_RIGHTS_VIEW_CONTROL_USER_ID_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsViewControlUserIdMissing","REQUEST_RIGHTS_VIEW_CONTROL_USER_ID_MISSING");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsViewControlUserIdNotActive","REQUEST_RIGHTS_VIEW_CONTROL_USER_ID_NOT_ACTIVE");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsViewControlUserIdNotFound","REQUEST_RIGHTS_VIEW_CONTROL_USER_ID_NOT_FOUND");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsViewControlUserIdNotInAccount","REQUEST_RIGHTS_VIEW_CONTROL_USER_ID_NOT_IN_ACCOUNT");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:StreamAccountInvalid","REQUEST_STREAM_ACCOUNT_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:StreamAccountMismatch","REQUEST_STREAM_ACCOUNT_MISMATCH");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:StreamNotActive","REQUEST_STREAM_NOT_ACTIVE");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:StreamAdultContentNotAllowed","REQUEST_STREAM_ADULT_CONTENT_NOT_ALLOWED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:StreamAssetNotActive","REQUEST_STREAM_ASSET_NOT_ACTIVE");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:StreamAssetNotFound","REQUEST_STREAM_ASSET_NOT_FOUND");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:StreamAssetWindowNotAllowed","REQUEST_STREAM_ASSET_WINDOW_NOT_ALLOWED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:StreamContentHasAgeRestriction","REQUEST_STREAM_CONTENT_HAS_AGE_RESTRICTION");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:StreamContentIdNotActive","REQUEST_STREAM_CONTENT_ID_NOT_ACTIVE");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:StreamContentIdNotFound","REQUEST_STREAM_CONTENT_ID_NOT_FOUND");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:StreamCountExceedMaxLimit","REQUEST_STREAM_COUNT_EXCEED_MAX_LIMIT");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:StreamDeleted","REQUEST_STREAM_DELETED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:StreamHandleInvalid","REQUEST_STREAM_HANDLE_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:StreamHandleRequired","REQUEST_STREAM_HANDLE_REQUIRED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:StreamLlaspNotBounded","REQUEST_STREAM_LLASP_NOT_BOUNDED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:StreamMaxNumberOfStreamsInvalid","REQUEST_STREAM_MAX_NUMBER_OF_STREAMS_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:StreamNotFound","REQUEST_STREAM_NOT_FOUND");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:StreamOwnerMismatch","REQUEST_STREAM_OWNER_MISMATCH");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:StreamRestrictedContentHidden","REQUEST_STREAM_RESTRICTED_CONTENT_HIDDEN");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:StreamRestrictedContentNoPurchase","REQUEST_STREAM_RESTRICTED_CONTENT_NO_PURCHASE");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:StreamRightsNotGranted","REQUEST_STREAM_RIGHTS_NOT_GRANTED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:StreamRightsRentalExpired","REQUEST_STREAM_RIGHTS_RENTAL_EXPIRED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:StreamRightsTokenIdInvalid","REQUEST_STREAM_RIGHTS_TOKEN_ID_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:StreamRightsTokenIdNotActive","REQUEST_STREAM_RIGHTS_TOKEN_ID_NOT_ACTIVE");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:StreamRightsTokenNotActive","REQUEST_STREAM_RIGHTS_TOKEN_NOT_ACTIVE");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:StreamRightsTokenNotFound","REQUEST_STREAM_RIGHTS_TOKEN_NOT_FOUND");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:StreamTransactionIdDuplicated","REQUEST_STREAM_TRANSACTION_ID_DUPLICATED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:StreamTransactionIdInvalid","REQUEST_STREAM_TRANSACTION_ID_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:StreamUnratedContentBlocked","REQUEST_STREAM_UNRATED_CONTENT_BLOCKED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:StreamUserIdInvalid","REQUEST_STREAM_USER_ID_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:StreamUserIdNotActive","REQUEST_STREAM_USER_ID_NOT_ACTIVE");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:StreamUserIdNotSpecified","REQUEST_STREAM_USER_ID_NOT_SPECIFIED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:StreamUserIdUnmatched","REQUEST_STREAM_USER_ID_UNMATCHED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:StreamUserNoDlaspPrivilege","REQUEST_STREAM_USER_NO_DLASP_PRIVILEGE");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:StreamUserNoViewAccess","REQUEST_STREAM_USER_NO_VIEW_ACCESS");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:UserDeleted","REQUEST_USER_DELETED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:UserIdUnmatched","REQUEST_USER_ID_UNMATCHED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Security:AccountIdInvalid","SECURITY_ACCOUNT_ID_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Security:AccountNotActive","SECURITY_ACCOUNT_NOT_ACTIVE");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Security:AdminAccessDenied","SECURITY_ADMIN_ACCESS_DENIED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Security:NodeIdInvalid","SECURITY_NODE_ID_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Security:NodeNotActive","SECURITY_NODE_NOT_ACTIVE");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Security:RoleInvalid","SECURITY_ROLE_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Security:UserIdInvalid","SECURITY_USER_ID_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Security:UserIdRequired","SECURITY_USER_ID_REQUIRED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Security:UserNotActive","SECURITY_USER_NOT_ACTIVE");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Security:UserNotInAccount","SECURITY_USER_NOT_IN_ACCOUNT");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:ServiceUnavailable","SERVICE_UNAVAILABLE");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Unauthorized","UNAUTHORIZED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:OrgIDInvalid","REQUEST_ORG_ID_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:OrgIDRequired","REQUEST_ORG_ID_REQUIRED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:GrantingUserIDRequired","REQUEST_GRANTING_USER_ID_REQUIRED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:UserIDInvalid","REQUEST_ACCOUNT_USERID_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:LockerOptInRoleNotRtrLasp","REQUEST_LOCKER_OPT_IN_ROLE_NOT_RTR_LASP");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:LockerOptInUserInactive","REQUEST_USER_INACTIVE");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsOptInCountExceededForAccount","REQUEST_ACCOUNT_LOCKER_OPT_IN_COUNT_EXCEEDED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:LASPBindDataAlreadyClosed","REQUEST_LASPBIND_DATA_ALREADY_CLOSED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:LASPBindDataInfoNotFound","REQUEST_LASPBIND_DATA_INFO_NOT_FOUND");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:LlaspAccountInfoNotFound","REQUEST_LLASP_ACCOUNT_INFO_NOT_FOUND");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:BindingUserIdAccountUserIdUnmatched","REQUEST_LLASP_BIND_USER_ID_ACCOUNT_USER_ID_NOT_MATCH");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:LaspIdNotALinkedLASP","REQUEST_LLASP_BIND_LASP_ID_PROVIDED_IS_NOT_LLASP");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountLLaspBindLimitReached","REQUEST_LLASP_BIND_ACCOUNT_LLASP_BIND_LIMIT_REACHED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountUserDoesNotHaveFullPrivilege","REQUEST_LLASP_BIND_ACCOUNT_USER_NOT_HAVE_FULL_PRIV");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:LLaspBindingAlreadyExists","REQUEST_LLASP_BIND_ALREADY_EXISTS");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:LLaspBindingDoesNotExist","REQUEST_LLASP_BIND_DOES_NOT_EXIST");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:LlaspLlaspIDInvalid","REQUEST_LLASP_LLASP_ID_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:LlaspLlaspIDNodeIdNotMatch","REQUEST_LLASP_LLASP_ID_NODE_ID_NOT_MATCH");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsOptInDataAlreadyClosed","REQUEST_RIGHTS_OPT_IN_DATA_ALREADY_CLOSED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsOptInDataAlreadyExpired","REQUEST_RIGHTS_OPT_IN_DATA_ALREADY_EXPIRED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsOptInDataDeleted","REQUEST_RIGHTS_OPT_IN_DATA_DELETED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsOptInDataInfoNotFound","REQUEST_RIGHTS_OPT_IN_DATA_INFO_NOT_FOUND");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsOptInAccountInfoNotFound","REQUEST_RIGHTS_OPT_IN_ACCOUNT_INFO_NOT_FOUND");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:NodeIdOrgIdUnmatched","REQUEST_NODE_ID_ORG_ID_UNMATCHED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:MdBasicMetadataAlreadyExist","MDBASIC_METADATA_ALREADYEXIST");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:OrganizationAddressInvalid","REQUEST_ORGANIZATION_ADDRESS_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:OrganizationAlternateEmailInvalid","REQUEST_ORGANIZATION_ALTERNATE_EMAIL_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:OrganizationPrimaryEmailInvalid","REQUEST_ORGANIZATION_PRIMARY_EMAIL_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:OrganizationSortNameInvalid","REQUEST_ORGANIZATION_SORT_NAME_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:OrganizationFirstGivenNameInvalid","REQUEST_ORGANIZATION_FIRST_GIVEN_NAME_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:OrganizationWebsiteInvalid","REQUEST_ORGANIZATION_WEBSITE_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:OrganizationPhoneNumberInvalid","REQUEST_ORGANIZATION_PHONE_NUMBER_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:NodeRoleInvalid","REQUEST_NODE_ROLE_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:NodeStatusInvalid","REQUEST_NODE_STATUS_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:OrganizationAlreadyExists","REQUEST_ORGANIZATION_ALREADY_EXISTS");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:NodeAlreadyExists","REQUEST_NODE_ALREADY_EXISTS");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:MdDigitalMetadataAlreadyExist","MDDIGITAL_METADATA_ALREADYEXIST");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:MdBasicRecordDoesNotExist","METADATA_BASIC_NO_RECORD");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:OrganizationDoesNotExist","REQUEST_ORGANIZATION_DOES_NOT_EXIST");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:BundleAlreadyExist","BUNDLE_ALREADYEXIST");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:MdDigitalRecordDoesNotExist","METADATA_DIGITAL_NO_RECORD");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:MdPhysicalDeleted","METADATA_PHYSICAL_DELETE_SUCCESS");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:MdBundleRecordDoesNotExist","METADATA_BUNDLE_NO_RECORD");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:Policy:UnratedContentBlocked","POLICY_UNRATED_CONTENT_BLOCKED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:MdNodeIdDiffrentFromCreateRequest","MD_NODEID_DIFFERENT_FROM_CREATE_REQUEST");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:Policy:HideRestrictedContentPolicy","REQUEST_HIDERESTRICTEDCONTENT");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:Policy:NoPurchaseRestrictedContentPolicy","POLICY_REQUEST_NOPURCHASERESTRICTEDCONTENT");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:Policy:AdultContentNotAllowed","POLICY_REQUEST_ADULT_CONTENT_NOTALLOWED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:Policy:UserDataUsageConsentRequired","REQUEST_USERDATAUSAGE_CONSENT_REQUIRED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:Policy:EnableUserDataUsageConsentRequired","REQUEST_ENABLE_USERDATAUSAGE_CONSENT_REQUIRED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:LogicalAssetAlreadyExist","LOGICAL_ASSET_ALREADY_EXIST");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:LogicalAssetDoesNotExist","LOGICAL_ASSET_NO_RECORD");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:StreamRenewExceedsMaximumTimeAllowed","STREAM_RENEWAL_EXCEEDS_MAX_TIME");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AssetidInvalid","REQUEST_ASSETID_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AssetProfileInvalid","REQUEST_ASSET_PROFILE_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AssetProfileDoesNotExist","REQUEST_ASSET_PROFILE_DOES_NOT_EXIST");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RequestNodeIdInvalid","REQUEST_NODE_ID_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RequestNodeDoesNotExist","REQUEST_NODE_DOES_NOT_EXIST");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RequestNodeProxyOrgIdDoesNotExist","REQUEST_NODE_PROXY_ORG_ID_DOES_NOT_EXIST");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:Policy:IncomingPoliciesOrExistingPoliciesAreInvalid","INCOMING_POLICIES_OR_EXISTING_POLICIES_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:Policy:IncomingPolicyUserDataUsageConsentCannotBeAdded","INCOMING_POLICY_USER_DATA_USAGE_CONSENT_CANNOT_BE_ADDED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:Policy:IncomingPolicyUnderLegalAgePolicyCannotBeAdded","INCOMING_POLICY_UNDER_LEGAL_AGE_POLICY_CANNOT_BE_ADDED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:Policy:IncomingPolicyRatingPolicyCannotBeAdded","INCOMING_POLICY_RATING_POLICY_CANNOT_BE_ADDED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:Policy:IncomingPolicyBlockUnratedContentCannotBeAdded","INCOMING_POLICY_BLOCK_UNRATED_CONTENT_POLICY_CANNOT_BE_ADDED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:Policy:IncomingPolicyManegeUserConsentCannotBeAdded","INCOMING_POLICY_MANAGE_USER_CONSENT_CANNOT_BE_ADDED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:Policy:EnableManageUserConsentRequired","POLICY_ENABLE_MANAGE_USER_CONSENT_REQUIRED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:Policy:ManageUserConsentRequired","POLICY_MANAGE_USER_CONSENT_REQUIRED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:Policy:LockerDataUsageConsentRequired","POLICY_LOCKER_DATA_USAGE_CONSENT_REQUIRED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:Policy:LockerViewAllConsentRequired","POLICY_LOCKER_VIEW_ALL_CONSENT_REQUIRED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:Policy:RatingPolicyExists","POLICY_RATING_POLICY_EXISTS");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsTokenNotActive","REQUEST_RIGHTS_TOKEN_NOT_ACTIVE");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RequestorUserNotFound","REQUESTOR_NOT_FOUND");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:InvalidUserID","REQUEST_USER_ID_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:InvalidLanguage","INVALID_LANGUAGE");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:InvalidAudioCodec","INVALID_AUDIO_CODEC");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:InvalidAudioType","INVALID_AUDIO_TYPE");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:InvalidVideoCodec","INVALID_VIDEO_CODEC");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:InvalidVideoType","INVALID_VIDEO_TYPE");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:InvalidVideoMpegLevel","INVALID_VIDEO_MPEG_LEVEL");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:InvalidVideoAspectRatio","INVALID_VIDEO_ASPECT_RATIO");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:InvalidSubtitleFormat","INVALID_SUBTITLE_FORMAT");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:CidDoesNotExist","CID_DOES_NOT_EXIST");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AlidDoesNotExist","ALID_DOES_NOT_EXIST");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:DuplicateContentId","DUPLICATE_CONTENT_ID_EXIST");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:InvalidWorkType","INVALID_WORK_TYPE");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:InvalidPictureFormat","INVALID_PICTURE_FORMAT");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:InvalidReleaseType","INVALID_RELEASE_TYPE");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:InvalidJobFunctionValue","INVALID_JOB_FUNCTION");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:InvalidScheme","INVALID_SCHEME");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:InvalidSSID","INVALID_SSID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:InvalidResolution","INVALID_RESOLUTION");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:InvalidResolutionWidthHeight","INVALID_WIDTH_HEIGHT");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:InvalidURIResolution","INVALID_URI_RESOLUTION");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:InvalidDisplayIndicator","INVALID_DISPLAY_INDICATOR");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:InvalidGenre","INVALID_GENRE");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:InvalidKeyword","INVALID_KEYWORD");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:InvalidReleaseHistory","INVALID_RELEASE_HISTORY");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:InvalidPeopleLocalNameIdentifier","INVALID_NAME_ID_PEOPLELOCAL");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:InvalidPeopleNameIdentifier","INVALID_NAME_ID_PEOPLE");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:DuplicateParent","DUPLICATE_PARENT");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:InvalidParentID","INVALID_PARENT_ID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:InvalidContentParentID","INVALID_CONTENT_PARENT_ID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:InvalidContentRating","INVALID_CONTENT_RATING");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:DuplicateContentRating","DUPLICATE_CONTENT_RATING");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:UserSAMLTokenDeleteFailed","USER_SAML_TOKEN_DELETE_FAILED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:NodeUnauthorizedToUpdateUserPassword","NODE_UNAUTHORIZED_TO_UPDATE_USERPASSWORD");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:NodeUnauthorizedToUpdateUserCredentials","NODE_UNAUTHORIZED_TO_UPDATE_USER_CREDENTIALS");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:NodeUnauthorizedToUpdateUserStatus","NODE_UNAUTHORIZED_TO_UPDATE_USERSTATUS");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:NodeUnauthorizedToUpdateUserBirthDate","NODE_UNAUTHORIZED_TO_UPDATE_USER_BIRTHDATE");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:NodeUnauthorizedToUpdateUserPolicies","NODE_UNAUTHORIZED_TO_UPDATE_USER_POLICIES");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:NodeUnauthorizedToUpdateUserRecoveryTokens","NODE_UNAUTHORIZED_TO_UPDATE_USER_RECOVERY_TOKENS");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:UserPrivilegeInsufficientToUpdateUserPolicies","INSUFFFICIENT_USER_PRIVILEGE_TO_UPDATE_POLICIES");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:PolicyClassInvalid","INVALID_POLICY_CLASS");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountUserCountryInvalid","REQUEST_ACCOUNTUSER_COUNTRY_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RequestorStatusNotActive","REQUESTOR_STATUS_NOT_ACTIVE");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:Policy:EnableUserDataUsageConsentRequired","POLICY_ENABLE_USER_DATA_USAGE_CONSENT_REQUIRED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:Policy:UserDataUsageConsentRequired","POLICY_USER_DATA_USAGE_CONSENT_REQUIRED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:RequestorUserPrivilegeInsufficient","REQUESTOR_PRIVILEGE_INSUFFICIENT");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AvatarImageSizeLarge","IMAGE_SIZE_LARGE");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountUserSecurityQuestionDuplicated","REQUEST_ACCOUNT_USER_SECURITY_QUESTION_DUPLICATED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsLockerNodFound","REQUEST_RIGHTS_LOCKER_NOT_FOUND");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountNotInDeletedStatus","REQUEST_ACCOUNT_NOT_IN_DELETED_STATUS");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:ActiveApidInvalid","REQUEST_ACTIVE_APID_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:ReplacedAPIDInvalid","REQUEST_REPLACED_APID_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RecalledAPIDInvalid","REQUEST_RECALLED_APID_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:ReplacedAPIDsInvalidForCreateRequest","REQUEST_REPLACED_APIDS_INVALID_FOR_CREATE_MAP");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RecalledAPIDsInvalidForCreateRequest","REQUEST_RECALLED_APIDS_INVALID_FOR_CREATE_MAP");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:ActiveApidDoesNotExist","REQUEST_ACTIVE_APID_DOES_NOT_EXIST");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:ReplacedAPIDDoesNotExist","REQUEST_REPLACED_APID_DOES_NOT_EXIST");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RecalledAPIDDoesNotExist","REQUEST_RECALLED_APID_DOES_NOT_EXIST");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:ReplacedAPIDDoesNotExistInMAP","REQUEST_REPLACED_APID_DOES_NOT_EXIST_IN_MAP");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RecalledAPIDDoesNotExistInMAP","REQUEST_RECALLED_APID_DOES_NOT_EXIST_IN_MAP");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:DuplicateAPIDNotAllowed","REQUEST_DUPLICATE_APID__NOT_ALLOWED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsTokenIdOrAccountIdInvalid","REQUEST_RIGHTS_TOKEN_ID_OR_ACCOUNTID_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsTokenIdAlreadyDeleted","REQUEST_RIGHTS_TOKEN_ALREADY_DELETED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsTokenIdDoesNotExist","REQUEST_RIGHTS_TOKEN_DOESNOT_EXIST");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsTokenIdNodeIsNotIssuer","REQUEST_RIGHTS_TOKEN_NODE_IS_NOT_ISSUER");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:DiscreteMediaProfileInvalid","REQUEST_DISCRETE_MEDIA_PROFILE_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:DiscreteMediaProfileDoesNotExist","REQUEST_DISCRETE_MEDIA_PROFILE_DOES_NOT_EXIST");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:InvalidDiscreteMediaProfileAndContentProfielCombination","REQUEST_DISCRETE_MEDIA_PROFILE_AND_CONTENT_PROFILE_COMBINATION");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:DiscreteMediaProfileNotValidForRightsToken","REQUEST_DISCRETE_MEDIA_PROFILE_NOT_VALID_FOR_RIGHTS_TOKEN");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:DiscreteMediaRightAccountMismatch","REQUEST_DISCRETE_MEDIA_RIGHT_ACCOUNT_MISMATCH");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:DiscreteMediaRightAccountNotActive","REQUEST_DISCRETE_MEDIA_RIGHT_ACCOUNT_NOT_ACTIVE");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:DiscreteMediaRightAccountNotFound","REQUEST_DISCRETE_MEDIA_RIGHT_ACCOUNT_NOT_FOUND");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:DiscreteMediaRightContentIdNotActive","REQUEST_DISCRETE_MEDIA_RIGHT_CONTENT_ID_NOT_ACTIVE");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:DiscreteMediaRightContentIdNotFound","REQUEST_DISCRETE_MEDIA_RIGHT_CONTENT_ID_NOT_FOUND");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:DiscreteMediaRightContentProfileInvalid","REQUEST_DISCRETE_MEDIA_RIGHT_CONTENT_PROFILE_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:DiscreteMediaRightContentProfileNotValidForRightsToken","REQUEST_DISCRETE_MEDIA_RIGHT_CONTENT_PROFILE_NOT_VALID_FOR_RIGHTS_TOKEN");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:DiscreteMediaRightIdInvalid","REQUEST_DISCRETE_MEDIA_RIGHT_ID_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:DiscreteMediaRightIdRequired","REQUEST_DISCRETE_MEDIA_RIGHT_ID_REQUIRED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:DiscreteMediaRightMaxLimitExceeded","REQUEST_DISCRETE_MEDIA_RIGHT_MAX_LIMIT_EXCEEDED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:DiscreteMediaRightNotFound","REQUEST_DISCRETE_MEDIA_RIGHT_NOT_FOUND");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:DiscreteMediaRightNotActive","REQUEST_DISCRETE_MEDIA_RIGHT_NOT_ACTIVE");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:DiscreteMediaRightOwnerMismatch","REQUEST_DISCRETE_MEDIA_RIGHT_OWNER_MISMATCH");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:DiscreteMediaRightRightsRentalExpired","REQUEST_DISCRETE_MEDIA_RIGHT_RIGHTS_RENTAL_EXPIRED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:DiscreteMediaRightRightsTokenIdInvalid","REQUEST_DISCRETE_MEDIA_RIGHT_RIGHTS_TOKEN_ID_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:DiscreteMediaRightRightsTokenTypeConsumed","REQUEST_DISCRETE_MEDIA_RIGHT_RIGHTS_TOKEN_TYPE_CONSUMED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:DiscreteMediaRightRightsTokenIdNotActive","REQUEST_DISCRETE_MEDIA_RIGHT_RIGHTS_TOKEN_ID_NOT_ACTIVE");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:DiscreteMediaRightRightsTokenNotFound","REQUEST_DISCRETE_MEDIA_RIGHT_RIGHTS_TOKEN_NOT_FOUND");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:DiscreteMediaRightUserNotActive","REQUEST_DISCRETE_MEDIA_RIGHT_USER_NOT_ACTIVE");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:DiscreteMediaRightUserNotFound","REQUEST_DISCRETE_MEDIA_RIGHT_USER_NOT_FOUND");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:DiscreteMediaRightLockerOrRightsTokenAccessRestricted","REQUEST_DISCRETE_MEDIA_RIGHT_LOCKER_OR_RIGHTS_TOKEN_ACCESS_RESTRICTED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:DiscreteMediaRightPurchaseProfilesNotFound","REQUEST_DISCRETE_MEDIA_RIGHT_PURCHASE_PROFILES_NOT_FOUND");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:DiscreteMediaRightRemainingCountRestriction","REQUEST_DISCRETE_MEDIA_RIGHT_REMAINING_COUNT_RESTRICTION");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:DiscreteMediaRightUserPrivlegeAccessRestricted","REQUEST_DISCRETE_MEDIA_RIGHT_USER_PRIVLEGE_ACCESS_RESTRICTED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:NodeListIsEmpty","REQUEST_NODELIST_IS_EMPTY");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountUserFavoriteMediaListSizeInvalid","REQUEST_ACCOUNT_USER_FAVORITE_MEDIA_LIST_SIZE_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountUserFavoriteMediaListIsFull","REQUEST_ACCOUNT_USER_FAVORITE_MEDIA_LIST_IS_FULL");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:AccountUserMediaExistsInFavoriteMediaList","REQUEST_ACCOUNT_USER_MEDIA_EXISTS_IN_FAVORITE_MEDIA_LIST");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsTokenContentProfileDoesnotExist","REQUEST_RIGHTS_TOKEN_CONTENT_PROFILE_DOESNOT_EXIST");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsTokenInvalidStatusChange","REQUEST_RIGHTS_TOKEN_INVALID_STATUS_CHANGE");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsTokenPurchaseNodeUserOidInvalid","REQUEST_RIGHTS_TOKEN_PURCHASE_NODE_USEROID_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsTokenPurchaseInfoIsInvalid","REQUEST_RIGHTS_TOKEN_PURCHASE_INFO_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsTokenLicenseAquisitionLocationIsNotUnique","REQUEST_RIGHTS_TOKEN_LICENCE_ACQ_LOC_NOT_UNIQUE");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsTokenFullfilmentLocationIsNotUnique","REQUEST_RIGHTS_TOKEN_FULLFILMENT_LOC_NOT_UNIQUE");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:DeviceRecordDoesNotExist","REQUEST_DEVICE_RECORD_DOES_NOT_EXIST");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:RightsAlidsDoesNotExistForAPID","REQUEST_RIGHTS_ALIDS_DOES_NOT_EXIST_FOR_APID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:InvalidDeviceId","REQUEST_DEVICEID_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:DeviceAlreadyExist","REQUEST_DEVICE_ALREADY_EXIST");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:ReachedMaxRegisteredLegacyDevice","REACHED_MAX_REGISTERED_LEGACY_DEVICE");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:DeceProtocolVersionNotProper","DECE_PROTOCOL_VERSION_NOT_PROPER");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:InvalidBrand","INVALID_BRAND");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:InvalidManufacturer","INVALID_MANUFACTURER");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:InvalidModel","INVALID_MODEL");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:InvalidDrmSupported","INVALID_DRM_SUPPORTED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:DeviceIdNotMatchingWiththeXMLDeviceID","DEVICEID_NOT_MATCHING_WITH_THE_XML_DEVICEID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:DeviceNotExist","DEVICE_NOT_EXIST");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:DeviceNodeIdDiffrentFromCreateRequest","DEVICE_NODEID_DIFFRENT_FROM_CREATE_REQUEST");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:InvalidDRMClientId","INVALID_DRM_CLIENTID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:DuplicateDRMClientId","DUPLICATE_DRM_CLIENTID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:DRMClientIdAlreadyExist","DRMCLIENTID_LINKED_TO_ANOTHER_DEVICE");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:OrganizationDisplayNameInvalid","REQUEST_ORGANIZATION_DISPLAY_NAME_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:MdGenreInvalid","REQUEST_MD_GENRE_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:OrganizationDisplayNameInvalid","REQUEST_ORGANIZATION_DISPLAY_NAME_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:MdGenreInvalid","REQUEST_MD_GENRE_INVALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:DuplicatePolicyCannotBeAdded","REQUEST_DUP_POLICY_CANNOT_ADDED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:Request:LastFullAccessUserofAccountCannotBeDeleted","LAST_FULL_ACCESS_CANNOT_DELETED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:AccountIdUnmatched","REQUEST_ACCOUNT_ID_UNMATCHED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:AccountIDNotValid","REQUEST_ACCOUNT_ID_UNMATCHED");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:UnlockMeTokenRequestNotValid","UNLOCKME_TOKEN_REQUEST_NOT_VALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:ForgotPasswordTokenRequestNotValid","FORGOT_PASSWORD_TOKEN_REQUEST_NOT_VALID");
			uiAPIErrorCodesList.put("urn:dece:errorid:org:dece:AccountDeviceCountExceedMaxLimit","ACCOUNT_DEVICE_COUNT_EXCEED_MAX_LIMIT");
			
		}
		
}
