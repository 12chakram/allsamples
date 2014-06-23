/*
 * Copyright (c) 2011 CompuGain, Inc. All Rights Reserved. 
 * ConfigureLocale.java
 */
package biz.neustar.dece.backing.bean.sections.common;

import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UICommand;
import javax.faces.event.ActionEvent;

import biz.neustar.dece.portal.utils.SessionUtils;

/**
 * The <code>ConfigureLocale.java</code> class encapsulates the functionality of changing the user locale
 * @author CompuGain.
 * @version $Revision: 1.0
 */
@ManagedBean(name="uiLocale")
@RequestScoped
public class ConfigureLocale {
	private String userSelectedLocale;
	public ConfigureLocale(){
		
	}

	public String getUserSelectedLocale() {
		SessionUtils sessionUtil = new SessionUtils();
		return sessionUtil.getLabelsTxtsBundle().getString(((null==userSelectedLocale) && (null==sessionUtil.getSession().getAttribute("userLocale")))?"enus":((null==userSelectedLocale)? ((Locale)(sessionUtil.getSession().getAttribute("userLocale"))).getLanguage().concat(((Locale)(sessionUtil.getSession().getAttribute("userLocale"))).getCountry()):userSelectedLocale).toLowerCase());		
	}

	public void setUserSelectedLocale(String userSelectedLocale) {
		this.userSelectedLocale = userSelectedLocale;
	}

	public void changeLocale(ActionEvent evnt)
	{
		UICommand uiCommand = (UICommand)evnt.getComponent();
		String selectedLocale = uiCommand.getId();
		selectedLocale=selectedLocale.substring(selectedLocale.indexOf(":")+1,selectedLocale.length());
		System.out.println("Print of SelectedLocale: "+selectedLocale.toString());
		SessionUtils sessionUtilsObj = new SessionUtils();
		sessionUtilsObj.localeSet(selectedLocale);		
	}
}