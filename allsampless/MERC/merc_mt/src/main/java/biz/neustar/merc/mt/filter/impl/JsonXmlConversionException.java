package biz.neustar.merc.mt.filter.impl;



/**
 * Json <-> Xml conversion exception
 *
 */
public class JsonXmlConversionException extends RuntimeException {

	private static final long serialVersionUID = -6421854546642329778L;

	/**
	 * @param message
	 * @param throwable
	 */
	public JsonXmlConversionException(String message, Throwable throwable)
	{
		super(message, throwable);
	}

	/**
	 * @param message
	 */
	public JsonXmlConversionException(String message) 
	{
		super(message);
	}
}
