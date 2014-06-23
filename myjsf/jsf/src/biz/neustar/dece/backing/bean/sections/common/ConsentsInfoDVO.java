/*
 * Copyright (c) 2011 Compugain, Inc. All Rights Reserved. 
 * HouseholdSettings.java
 */

package biz.neustar.dece.backing.bean.sections.common;


/**
 * The <code>HouseholdSettings.java</code> class encapsulates objects defined for DECE.
 * @author Compugain. 
 * @version $Revision: 1.2 $ $Date: 2012/03/13 04:44:34 $
 */
public class ConsentsInfoDVO {

	private boolean consentAccepted;
	private String consentHdnName;
	private String consentName;
	private String consentShortDesc;
	private String consentDesc;
	private boolean consentEditable;
	public void setConsentAccepted(boolean consentAccepted) {
		this.consentAccepted = consentAccepted;
	}
	public boolean isConsentAccepted() {
		return consentAccepted;
	}
	public void setConsentName(String consentName) {
		this.consentName = consentName;
	}
	public String getConsentName() {
		return consentName;
	}
	public void setConsentShortDesc(String consentShortDesc) {
		this.consentShortDesc = consentShortDesc;
	}
	public String getConsentShortDesc() {
		return consentShortDesc;
	}
	public void setConsentDesc(String consentDesc) {
		this.consentDesc = consentDesc;
	}
	public String getConsentDesc() {
		return consentDesc;
	}
	public void setConsentHdnName(String consentHdnName) {
		this.consentHdnName = consentHdnName;
	}
	public String getConsentHdnName() {
		return consentHdnName;
	}
	public void setConsentEditable(boolean consentEditable) {
		this.consentEditable = consentEditable;
	}
	public boolean isConsentEditable() {
		return consentEditable;
	}
	
	
}