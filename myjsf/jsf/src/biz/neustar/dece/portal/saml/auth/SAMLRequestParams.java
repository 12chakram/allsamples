/**
 * 
 */
package biz.neustar.dece.portal.saml.auth;

import java.util.Arrays;

/**
 * @author pkuninti
 * 
 */
public class SAMLRequestParams
{
	private String certificateURL;
	private String certificateKeyURL;
	private String userName;
	private String coordinatorURL;
	private String issuer;
	private String policies;
	private String[] childPolicies;
	private String[] audience;
	

	/**
	 * @return the certificateURL
	 */
	public String getCertificateURL()
	{
		return certificateURL;
	}

	/**
	 * @param certificateURL
	 *            the certificateURL to set
	 */
	public void setCertificateURL(String certificateURL)
	{
		this.certificateURL = certificateURL;
	}

	/**
	 * @return the certificateKeyURL
	 */
	public String getCertificateKeyURL()
	{
		return certificateKeyURL;
	}

	/**
	 * @param certificateKeyURL
	 *            the certificateKeyURL to set
	 */
	public void setCertificateKeyURL(String certificateKeyURL)
	{
		this.certificateKeyURL = certificateKeyURL;
	}


	/**
	 * @return the userName
	 */
	public String getUserName()
	{
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	/**
	 * @return the coordinatorURL
	 */
	public String getCoordinatorURL()
	{
		return coordinatorURL;
	}

	/**
	 * @param coordinatorURL
	 *            the coordinatorURL to set
	 */
	public void setCoordinatorURL(String coordinatorURL)
	{
		this.coordinatorURL = coordinatorURL;
	}

	/**
	 * @return the policies
	 */
	public String getPolicies()
	{
		return policies;
	}

	/**
	 * @param policies
	 *            the policies to set
	 */
	public void setPolicies(String policies)
	{
		this.policies = policies;
	}

	/**
	 * @return the audience
	 */
	public String[] getAudience()
	{
		return audience;
	}

	/**
	 * @param audience
	 *            the audience to set
	 */
	public void setAudience(String[] audience)
	{
		this.audience = audience;
	}

	/**
	 * @return the childPolicies
	 */
	public String[] getChildPolicies()
	{
		return childPolicies;
	}

	/**
	 * @param childPolicies
	 *            the childPolicies to set
	 */
	public void setChildPolicies(String[] childPolicies)
	{
		this.childPolicies = childPolicies;
	}
	
	/**
	 * @return the issuer
	 */
	public String getIssuer()
	{
		return issuer;
	}

	/**
	 * @param issuer
	 *            the issuer to set
	 */
	public void setIssuer(String issuer)
	{
		this.issuer = issuer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "SAMLRequestParams [audience=" + Arrays.toString(audience)
				+ ", certificateKeyURL="
				+ certificateKeyURL + ", certificateURL=" + certificateURL
				+ ", childPolicies=" + Arrays.toString(childPolicies)
				+ " , coordinatorURL=" + coordinatorURL + ", issuer=" + issuer + ", policies="
				+ policies + ", userName=" + userName + "]";
	}
}
