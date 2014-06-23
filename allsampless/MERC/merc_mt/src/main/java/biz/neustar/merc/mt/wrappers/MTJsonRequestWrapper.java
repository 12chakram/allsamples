package biz.neustar.merc.mt.wrappers;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.log4j.Logger;

import biz.neustar.merc.mt.exceptions.MTException;
import biz.neustar.merc.mt.filter.impl.JsonXmlConverter;

public class MTJsonRequestWrapper extends HttpServletRequestWrapper {

	private static Logger logger = Logger.getLogger(MTJsonRequestWrapper.class);
	private final String requestBody;

	public MTJsonRequestWrapper(HttpServletRequest request, JsonXmlConverter jxConv) throws IOException,MTException {
		super(request);
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;
		try {
			InputStream inputStream = request.getInputStream();
			if (inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(
						inputStream));
				char[] charBuffer = new char[1024];
				int bytesRead = -1;
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, bytesRead);
				}
			} else {
				stringBuilder.append("");
			}
		} catch (IOException ex) {
			throw new MTException(ex.getMessage());
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException ex) {
					throw new MTException(ex.getMessage());
				}
			}
		}
		
		logger.info("Original request payload : " + stringBuilder.toString());
		
		// call json to xml conv
		requestBody = jxConv.convertToXml(stringBuilder.toString());
		
		logger.info("Modified request payload : " + requestBody);
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(requestBody.getBytes());
		ServletInputStream servletInputStream = new ServletInputStream() {
			public int read() throws IOException {
				return byteArrayInputStream.read();
			}
		};
		return servletInputStream;
	}

	@Override
	public BufferedReader getReader() throws IOException {
		return new BufferedReader(new InputStreamReader(this.getInputStream()));
	}

	public String getRequestBody() {
		return this.requestBody;
	}
}
