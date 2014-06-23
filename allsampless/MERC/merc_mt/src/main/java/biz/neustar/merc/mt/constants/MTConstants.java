/**
 * 
 */
package biz.neustar.merc.mt.constants;

/**
 * @author pkuninti
 * 
 */
public class MTConstants {

	// Merc Backend namespace constants
	public static final String ACCOUNT_ID_NAMESPACE = "urn:nsr:merc:accountid:org:nsr:merc:";
	public static final String ENTITLEMENT_ID_NAMESPACE = "urn:nsr:merc:entitlementid:";

	// HTTP Headers related constants
	public static final String HTTP_HEADER_CONTENT_TYPE = "Content-Type";
	public static final String HTTP_HEADER_CONTENT_TYPE_VALUE_JSON = "application/json";
	public static final String HTTP_HEADER_LOCATION = "Location";

	//HTTP request method constants
	public static final String REQUEST_METHOD_POST = "POST";
	public static final String REQUEST_METHOD_PUT = "PUT";
	public static final String REQUEST_METHOD_GET = "GET";
	public static final String REQUEST_METHOD_DELETE = "DELETE";
	
	//Payload Constants
	public static final String ACCOUNT_ID = "{AccountId}";
	public static final String USER_ID = "{UserId}";
	public static final String ENTITLEMENT_ID = "{EntitlementId}";
	public static final String PATH_VARIABLE = "{PathVariable}";
}
