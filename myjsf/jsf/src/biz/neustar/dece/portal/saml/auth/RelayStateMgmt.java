/**
 * 
 */
package biz.neustar.dece.portal.saml.auth;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author sjha
 * 
 */
public class RelayStateMgmt
{

	String inputString = null;
	boolean relayStateExist = false;
	String requestType;
	String request;
	String relayState;
	private final String relaystate = "RelayState";

	public String getRequest()
	{
		return request;
	}

	public void setRequest(String request)
	{
		this.request = request;
	}

	public String getRelayState() throws UnsupportedEncodingException
	{
		if (this.isRelayStateExist())
			return URLDecoder.decode(relayState, "UTF-8");
		return null;
	}

	public void setRelayState(String relayState)
	{
		this.relayState = relayState;
	}

	/**
	 * 
	 */
	public RelayStateMgmt(String s, String requestOrResponse)
	{
		this.inputString = s.trim();
		this.setRequestType(requestOrResponse);
		this.retriveString();

	}

	private int isRelayState()
	{
		int index = inputString.indexOf(this.relaystate);
		if (index >= 0)
		{
			this.setRelayStateExist(true);
		}
		return index;
	}

	private void retrieveData(String[] data)
	{
		for (int i = 0; i < data.length; i++)
		{
			if (data[i].trim().startsWith(this.relaystate))
				this.setRelayState(data[i].split("=")[1]);
			if (data[i].trim().startsWith(this.getRequestType()))
				this.setRequest(data[i]);
		}

	}

	private void retriveString()
	{
		int pos = this.isRelayState();
		if (this.isRelayStateExist())
		{
			String[] fragments = this.inputString.split("&");
			this.retrieveData(fragments);
			return;

		}
		this.setRequest(this.inputString);
	}

	public boolean isRelayStateExist()
	{
		return relayStateExist;
	}

	public void setRelayStateExist(boolean relayStateExist)
	{
		this.relayStateExist = relayStateExist;
	}

	public String getInputString()
	{
		return inputString;
	}

	public void setInputString(String inputString)
	{
		this.inputString = inputString;
	}

	public String getRequestType()
	{
		return requestType;
	}

	public void setRequestType(String requestType)
	{
		this.requestType = requestType;
	}

}
