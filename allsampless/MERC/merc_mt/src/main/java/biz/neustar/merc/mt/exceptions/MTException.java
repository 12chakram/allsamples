/**
 * 
 */
package biz.neustar.merc.mt.exceptions;

import org.apache.http.HttpStatus;

/**
 * @author pkuninti
 * 
 */
public class MTException extends Exception {

	public static enum MTResponseCode {
		BAD_REQUEST, INTERNAL_SERVER_ERROR
	}

	private static final long serialVersionUID = 1L;

	private int errorId;
	private String errorDescription;
	
	public MTException(String errorDescription) {
		super(errorDescription);
		setError(MTResponseCode.INTERNAL_SERVER_ERROR, errorDescription);
	}

	public MTException(MTResponseCode mtResponseCode) {
		setError(mtResponseCode, null);
	}

	public MTException(MTResponseCode mtResponseCode, String errorDescription) {
		super(errorDescription);
		setError(mtResponseCode, errorDescription);
	}

	private void setError(MTResponseCode mtResponseCode, String description) {
		if (mtResponseCode != null) {
			switch (mtResponseCode) {

			case BAD_REQUEST:
				this.errorId = HttpStatus.SC_BAD_REQUEST;
				this.errorDescription = description;
				break;
			case INTERNAL_SERVER_ERROR:
				this.errorId = HttpStatus.SC_INTERNAL_SERVER_ERROR;
				if (description != null && description.trim().length() > 0) {
					this.errorDescription = description;
				} else {
					//TODO - get this from property file
					this.errorDescription = "Unexpected error. please try again..";
				}
				break;
			}
		}
	}

	/**
	 * @return the errorId
	 */
	public int getErrorId() {
		return errorId;
	}

	/**
	 * @param errorId
	 *            the errorId to set
	 */
	public void setErrorId(int errorId) {
		this.errorId = errorId;
	}

	/**
	 * @return the errorDescription
	 */
	public String getErrorDescription() {
		return errorDescription;
	}

	/**
	 * @param errorDescription
	 *            the errorDescription to set
	 */
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MTException [errorId=" + errorId + ", errorDescription="
				+ errorDescription + "]";
	}

}
