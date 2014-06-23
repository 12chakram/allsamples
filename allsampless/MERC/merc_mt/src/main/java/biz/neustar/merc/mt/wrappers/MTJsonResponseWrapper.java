package biz.neustar.merc.mt.wrappers;



import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * Custom Response Wrapper to have access to response. 
 * From this wrapper, we will modify the response to JSON.
 *
 */
public class MTJsonResponseWrapper extends HttpServletResponseWrapper {
	
	private ByteArrayOutputStream output;
	private int contentLen;
	private String contentType;
	
	public MTJsonResponseWrapper(HttpServletResponse response) {
		super(response);
		output = new ByteArrayOutputStream();
	}
	
	public byte[] getData() {
		return output.toByteArray();
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		ServletOutputStream servletOutputStream = new ServletOutputStream() {
			public void write(int c) throws IOException {
				output.write(c);
			}
			public void write(byte[] b, int off, int len) throws IOException {
				output.write(b, off, len);
			}
		};
		return servletOutputStream;
	}
	
	public PrintWriter getWriter() throws IOException {
		return (new PrintWriter(getOutputStream(), true));
	}
	
	public void setContentLength(int length) {
		this.contentLen = length;
		super.setContentLength(length);
	}
	
	public int getContentLength() {
		return contentLen;
	}

	public void setContentType(String type) {
		this.contentType = type;
		super.setContentType(type);
	}
	
	public String getContentType() {
		return contentType;
	}

}
