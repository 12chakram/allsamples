/**
 * 
 */
package biz.neustar.dece.portal.saml.exception;

/**
 * @author sjha
 * 
 */
public class InvalidSignatureException extends Exception
{
	/**
	 * 
	 */
	public InvalidSignatureException()
	{
	}

	/**
	 * @param message
	 */
	public InvalidSignatureException(String message)
	{
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public InvalidSignatureException(Throwable cause)
	{
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public InvalidSignatureException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
