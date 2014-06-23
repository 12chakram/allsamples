package biz.neustar.dece.backing.bean.sections;

import java.io.Serializable;


public class AmbiguityUsers implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String displayName;
	private String nodeUserId;
	



	public String getNodeUserId() {
		return nodeUserId;
	}

	public void setNodeUserId(String nodeUserId) {
		this.nodeUserId = nodeUserId;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}



}
