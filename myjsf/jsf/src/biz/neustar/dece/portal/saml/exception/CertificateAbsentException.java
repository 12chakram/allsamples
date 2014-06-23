/**
 * 
 */
package biz.neustar.dece.portal.saml.exception;

/**
 * @author sjha
 * 
 */
public class CertificateAbsentException extends Exception
{
	/**
	 * 
	 */
	public CertificateAbsentException()
	{
	}

	/**
	 * @param message
	 */
	public CertificateAbsentException(String message)
	{
		super(message);
	}

	/**
	 * @param cause
	 */
	public CertificateAbsentException(Throwable cause)
	{
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public CertificateAbsentException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
