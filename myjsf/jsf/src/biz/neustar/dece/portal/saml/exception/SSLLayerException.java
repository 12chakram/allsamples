/**
 * 
 */
package biz.neustar.dece.portal.saml.exception;

/**
 * @author sjha
 * 
 */
public class SSLLayerException extends Exception
{
	/**
	 * 
	 */
	public SSLLayerException()
	{
	}

	/**
	 * @param message
	 */
	public SSLLayerException(String message)
	{
		super(message);
	}

	/**
	 * @param cause
	 */
	public SSLLayerException(Throwable cause)
	{
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public SSLLayerException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
