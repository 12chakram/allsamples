/**
 * 
 */
package biz.neustar.dece.portal.saml.exception;

/**
 * @author sjha
 * 
 */
public class NoCertProvisioned extends Exception
{
	/**
	 * 
	 */
	public NoCertProvisioned()
	{
	}

	/**
	 * @param message
	 */
	public NoCertProvisioned(String message)
	{
		super(message);
	}

	/**
	 * @param cause
	 */
	public NoCertProvisioned(Throwable cause)
	{
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public NoCertProvisioned(String message, Throwable cause)
	{
		super(message, cause);
	}
}
