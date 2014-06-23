/*
 * Copyright (c) 2011 Compugain, Inc. All Rights Reserved. 
 * HouseholdSettings.java
 */

package biz.neustar.dece.backing.bean.sections.common;

import java.util.List;

import biz.neustar.dece.ui.dvo.UserDVO;

/**
 * The <code>HouseholdSettings.java</code> class encapsulates objects defined for DECE.
 * @author Compugain. 
 * @version $Revision: 1.3 $ $Date: 2012/03/13 04:44:34 $
 */

public class UIConsentsDVO {

	private String nodeName;
	private String nodeImageURL;
	private String nodeId;
	private List<ConsentsInfoDVO> consentsObj;
	private List<UserDVO> linkedUsers;
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeImageURL(String nodeImageURL) {
		this.nodeImageURL = nodeImageURL;
	}
	public String getNodeImageURL() {
		return nodeImageURL;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public String getNodeId() {
		return nodeId;
	}
	public void setConsentsObj(List<ConsentsInfoDVO> consentsObj) {
		this.consentsObj = consentsObj;
	}
	public List<ConsentsInfoDVO> getConsentsObj() {
		return consentsObj;
	}
	public void setLinkedUsers(List<UserDVO> linkedUsers) {
		this.linkedUsers = linkedUsers;
	}
	public List<UserDVO> getLinkedUsers() {
		return linkedUsers;
	}
	
}