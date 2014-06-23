package biz.neustar.dece.portal.utils;

import biz.neustar.dece.ui.service.UIAccountMergeService;
import biz.neustar.dece.ui.service.UIAccountService;
import biz.neustar.dece.ui.service.UIDeviceService;
import biz.neustar.dece.ui.service.UIGEOService;
import biz.neustar.dece.ui.service.UILoginService;
import biz.neustar.dece.ui.service.UIMediaService;
import biz.neustar.dece.ui.service.UINewsFeedService;
import biz.neustar.dece.ui.service.UIPolicyService;
import biz.neustar.dece.ui.service.UIReverseSAMLService;
import biz.neustar.dece.ui.service.UIStreamService;
import biz.neustar.dece.ui.service.UIUserService;
import biz.neustar.dece.ui.service.UIUtilService;

public class ServiceLocator
{
	private static UILoginService uiLoginService;
	private static UIAccountService uiAccountService;
	private static UIUtilService uiUtilService;
	private static UIUserService uiUserService;
	private static UIMediaService uiMediaService;
	private static UINewsFeedService uiNewsFeedService;
	private static UIDeviceService uiDeviceService;
	private static UIStreamService uiStreamService;
	private static UIPolicyService uiPolicyService;
	private static UIGEOService uiGEOService;
	private static UIReverseSAMLService uiReverseSAMLService;
	private static UIAccountMergeService uiAccountMergeService;

	public static UIStreamService getUiStreamService()
	{
		uiStreamService = (UIStreamService) SpringApplicationContext.getBean("UIStreamService");
		return uiStreamService;
	}

	public static UILoginService getUiLoginService()
	{
		uiLoginService = (UILoginService) SpringApplicationContext.getBean("UILoginService");
		return uiLoginService;
	}

	public static UIAccountService getUiAccountService()
	{
		uiAccountService = (UIAccountService) SpringApplicationContext.getBean("UIAccountService");
		return uiAccountService;
	}

	public static UIUtilService getUiUtilService()
	{
		uiUtilService = (UIUtilService) SpringApplicationContext.getBean("UIUtilService");
		return uiUtilService;
	}

	public static UIUserService getUiUserService()
	{
		uiUserService = (UIUserService) SpringApplicationContext.getBean("UIUserService");
		return uiUserService;
	}

	public static UINewsFeedService getUiNewsFeedService()
	{
		uiNewsFeedService = (UINewsFeedService) SpringApplicationContext
				.getBean("UINewsFeedService");
		return uiNewsFeedService;
	}

	public static UIMediaService getUiMediaService()
	{
		uiMediaService = (UIMediaService) SpringApplicationContext.getBean("UIMediaService");
		return uiMediaService;
	}

	public static UIDeviceService getUiDeviceService()
	{
		uiDeviceService = (UIDeviceService) SpringApplicationContext.getBean("UIDeviceService");
		return uiDeviceService;
	}

	public static UIPolicyService getUIPolicyService()
	{
		uiPolicyService = (UIPolicyService) SpringApplicationContext.getBean("UIPolicyService");
		return uiPolicyService;
	}

	public static UIGEOService getUIGEOService()
	{
		uiGEOService = (UIGEOService) SpringApplicationContext.getBean("UIGEOService");
		return uiGEOService;
	}

	public static UIReverseSAMLService getUiReverseSAMLService()
	{
		uiReverseSAMLService = (UIReverseSAMLService) SpringApplicationContext
				.getBean("UIReverseSAMLService");
		return uiReverseSAMLService;
	}

	public static UIAccountMergeService getUiAccountMergeService()
	{
		uiAccountMergeService = (UIAccountMergeService) SpringApplicationContext
				.getBean("UIAccountMergeService");
		return uiAccountMergeService;
	}
}
