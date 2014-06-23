package biz.neustar.dece.portal.saml.auth;

public class SAMLResponseBean
{

	private String userId = null;
	private String accountId = null;

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getAccountId()
	{
		return accountId;
	}

	public void setAccountId(String accountId)
	{
		this.accountId = accountId;
	}
}
