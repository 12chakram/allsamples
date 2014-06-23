package biz.neustar.dece.portal.utils;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import biz.neustar.dece.ui.UIDeceException;
import biz.neustar.dece.ui.dvo.MediaDVO;
import biz.neustar.dece.ui.dvo.MediaFilterDVO;
import biz.neustar.dece.ui.dvo.PaginatedMediaDVO;
import biz.neustar.dece.ui.dvo.PaginationDVO;
import biz.neustar.dece.ui.dvo.UserAuthDVO;
import biz.neustar.dece.ui.util.MediaFilterConstants;

public class ResourceServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger resourceServletLogger = Logger.getLogger(ResourceServlet.class);
	
	/**
	 * Used for Response Stream's for Different Resources like Flash, Images, QuickTips and Faq's etc.
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		HttpSession session = request.getSession(false);
		//response.setHeader("Cache-Control", "no-store");
		//response.setHeader("Pragma", "no-cache");
		//response.setDateHeader("Expires", 0);
		Locale userLocale = new Locale("en", "US");
		if (null!=session && session.getAttribute("userLocale") != null)
			userLocale = (Locale) session.getAttribute("userLocale");
		
		
		if("getContentForFlash".equals(type))
			getContentForFlash(request,response);
		else if("bytesOfImage".equalsIgnoreCase(type))
			avatarbytesOfImageResponseStream(request, response);
		else if("helpSearch".equalsIgnoreCase(type))
			helpSearch(request, response, userLocale);

				
	}
	/**
	 * For Avatar response Stream at Edit Family Member
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void avatarbytesOfImageResponseStream(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession(false);
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		byte[] b =null;
		if(null!=session)
			b  = (byte[])session.getAttribute("bytesOfImage");
		ServletOutputStream responseOutputStream = null;
		if (b != null && b.length>0) {
			try {
				responseOutputStream = response.getOutputStream();
				responseOutputStream.write(b,0,b.length);
			} catch (Exception ex) {
				resourceServletLogger.error("Unable to write image to output stream", ex);
			} finally {
				if (responseOutputStream != null) {
					responseOutputStream.flush();
					responseOutputStream.close();
				}
			}
		}else {
				responseOutputStream = response.getOutputStream();
				BufferedInputStream buf = null;
				try {		
					
					b=getNoImageOfAvatar();
					responseOutputStream.write(b, 0, b.length);
				} 
				finally {
					if (buf != null) {
						buf.close();
					}
				}
			}
		}
	
	

	private void helpSearch(HttpServletRequest request, HttpServletResponse response, Locale userLocale) throws ServletException, IOException {
		OutputStream out = null;
		try {
			String searchTextValue = request.getParameter("searchText");	
			String topicFileNameList = request.getParameter("topicFileNameList");
			String[] topicFileNameListArray = topicFileNameList.substring(1).split(",");
			if(searchTextValue != null && !"".equals(searchTextValue.trim())){
				XmlParser xmlParser = new XmlParser();	
				List<QuestionAndAnswer> questionAndAnswerList = new ArrayList<QuestionAndAnswer>();
				StringBuffer sb = new StringBuffer();
				String staticContentBaseUrl = getServletContext().getAttribute("staticContentBaseUrl").toString();
				for (int i = 0; i < topicFileNameListArray.length; i++) {
					sb = new StringBuffer();
					sb.append(staticContentBaseUrl).append("/faqs/").append(topicFileNameListArray[i]).append("_")
					  .append(userLocale.getLanguage()).append("_").append(userLocale.getCountry()).append(".xml");
					if(topicFileNameListArray[i].trim() != "")
						questionAndAnswerList.addAll(xmlParser.getFaqs(sb.toString(), searchTextValue.trim()));
				}
				response.setContentType("text/xml");
				sb = new StringBuffer();	
				sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
				sb.append("<SEARCH_RESULT>");
				for(int i=0; i<questionAndAnswerList.size(); i++)
				{
						if(null!=questionAndAnswerList.get(i).getQuestion()|| null!=questionAndAnswerList.get(i).getAnswer())
						{
						sb.append("<FAQ>");
							sb.append("<Q>" + encodeHTML(questionAndAnswerList.get(i).getQuestion()) + "</Q>");
							sb.append("<A>" + encodeHTML(questionAndAnswerList.get(i).getAnswer()) + "</A>");
						sb.append("</FAQ>");
						}
					}
				if(questionAndAnswerList.size() == 0){
					sb.append("<FAQ>");
						sb.append("<Q>No Search Results Found</Q>");
						sb.append("<A> </A>");
					sb.append("</FAQ>");					
				}
				sb.append("</SEARCH_RESULT>");
				String strMediaContent = sb.toString();
				byte[] bytes;
				bytes= strMediaContent.getBytes();
				response.setContentType("text/xml");
				out =(OutputStream)response.getOutputStream();
				out.write( bytes);
			}
		} catch (Exception e) {
			resourceServletLogger.error("Error writing the Help Search result xml", e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}
	private String encodeHTML(String s)
	{
	    StringBuffer out = new StringBuffer();
	    if(s!="" && s!=null)
	    {
		    s = s.trim();
		    for(int i=0; i<s.length(); i++)
		    {
		        char c = s.charAt(i);
		        if(c > 127 || c=='"' || c=='<' || c=='>'|| c=='&')
		        {
		           out.append("&#"+(int)c+";");
	//	        } else if(c==' '){
	//	        	out.append("%20");
		        } else
		        {
		            out.append(c);
		        }
		    }
		    return out.toString();
	    }
	   return "";
	}
	
	private String encodeImageUrl(String s)
	{
	    StringBuffer out = new StringBuffer();
	    s = s.trim();
	    for(int i=0; i<s.length(); i++)
	    {
	        char c = s.charAt(i);
	        if(c > 127 || c=='"' || c=='<' || c=='>')
	        {
	           out.append("&#"+(int)c+";");
	        } else if(c==' '){
	        	out.append("%20");
	        } else
	        {
	            out.append(c);
	        }
	    }
	    return out.toString();
	}
	/**
	 * @param responseOutputStream
	 * @param file
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	private void writeFileToResponse( ServletContext servletContext, HttpServletResponse response, String fileName )
	{
		String apacheHttpServerAddress = null;
		if( servletContext.getAttribute("staticContentBaseUrl") != null )
		{
			apacheHttpServerAddress = servletContext.getAttribute("staticContentBaseUrl").toString();
		}
		String file = apacheHttpServerAddress + fileName;
		//String file = "http://www.2mhphotos.com/no_image_bg.png";
		resourceServletLogger.info("Get file [" + file + "]");
		ServletOutputStream responseOutputStream = null;
		InputStream inputStream = null;
		BufferedInputStream buf = null;
		try
		{
			responseOutputStream = response.getOutputStream();
			UrlConnectionUtil urlConnectionUtil = new UrlConnectionUtil();
			URLConnection urlConn = urlConnectionUtil.openUrlConnection(servletContext, file);
			inputStream = urlConn.getInputStream();
			buf = new BufferedInputStream(inputStream);
			int readBytes = buf.available();
			byte[] b = new byte[readBytes];
			while( (readBytes = buf.read(b)) != -1 )
			{
				responseOutputStream.write(b);
			}
		}
		catch( FileNotFoundException e )
		{
			resourceServletLogger.error("Unable to locate the file [" + file + "]", e);
		}
		catch( IOException e )
		{
			resourceServletLogger.error("Unable to read from file [" + file + "]", e);
		}

		catch( Exception ex )
		{
			resourceServletLogger.error("Unable to write to output stream", ex);
		}
		finally
		{
			closeStreams(responseOutputStream, inputStream, buf);

		}
	}

	/**
	 * @param responseOutputStream
	 * @param inputStream
	 * @param buf
	 */
	private void closeStreams( ServletOutputStream responseOutputStream, InputStream inputStream,
	        BufferedInputStream buf )
	{

		resourceServletLogger.info("Closing streams...");

		if( buf != null )
		{
			try
			{
				buf.close();
			}
			catch( IOException ex )
			{}
		}
		if( inputStream != null )
		{
			try
			{
				inputStream.close();
			}
			catch( IOException ex )
			{

			}
		}
		if( responseOutputStream != null )
		{
			try
			{
				responseOutputStream.flush();
			}
			catch( IOException ex )
			{

			}
			try
			{
				responseOutputStream.close();
			}
			catch( IOException ex )
			{

			}
		}
		resourceServletLogger.info("Streams closed");

	}

	private byte[] getNoImageOfAvatar() throws IOException
	{
		byte[] noImageStream=null;
		String apacheHttpServerAddress = null;
		if(getServletContext().getAttribute("staticContentBaseUrl") != null)
			apacheHttpServerAddress = getServletContext().getAttribute("staticContentBaseUrl").toString();
		String file = apacheHttpServerAddress + "/images/no_image_bg.png";
		
		UrlConnectionUtil urlUtil = new UrlConnectionUtil();
		noImageStream=urlUtil.getByteStream(getServletContext(), file);
		
		return noImageStream;
	}
	
	/**
	 * For generating the XML response Stream for flash component
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void getContentForFlash(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		List<MediaDVO> mediaLst=null;
		StringBuffer contentXML;
		OutputStream out = null;
		
		String servletImageURL = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/resourceServlet?type=flashImage";
		String navURL = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/views/mediaPage.jsf?mediaTitleId=";
		HttpSession session = request.getSession(false);	
		if(null!=session){
			UserAuthDVO userAuthDVO = (UserAuthDVO)session.getAttribute("Auth");
			if(null!= userAuthDVO){
				MediaFilterDVO fltrDVO = new MediaFilterDVO();		
				fltrDVO.setSortBy(MediaFilterConstants.SORT_CONSTANT_ALPHABETS);
				PaginationDVO pgMedDVO = new PaginationDVO();
				pgMedDVO.setStartIndex(1);		
				if(null!=session.getServletContext().getAttribute("mediaPageSizeAll")){
					int mediaPageSizeAll=Integer.parseInt(session.getServletContext().getAttribute("mediaPageSizeAll").toString());
					pgMedDVO.setPageSize(mediaPageSizeAll);
		        }else
		        	pgMedDVO.setPageSize(200);
		
				try {
					PaginatedMediaDVO pgMVDO = ServiceLocator.getUiMediaService().getHouseHoldMediaByFilter(userAuthDVO, fltrDVO, pgMedDVO);
					if(pgMVDO!=null)
						mediaLst=pgMVDO.getMediaList();
					else
						mediaLst=null;
				} catch (UIDeceException ex) {
					ExceptionUtils.processUIDECEException("REQUEST_FOR_MEDIA_LIST_FOR_FLASH", "ResourceServlet::getContentForFlash", ex, null, null, null);
				}
			}
		}
		if(null==mediaLst || 0 == mediaLst.size())
		{
			mediaLst=new ArrayList<MediaDVO>();
			MediaDVO mediaDVO = new MediaDVO();
			
			mediaDVO.setImageUrl("");
			mediaDVO.setMediaDescription("No Items found");
			mediaLst.add(mediaDVO);			
		}
		else{
			resourceServletLogger.info("Media List Size: "+ String.valueOf(mediaLst.size()));
			response.setContentType("text/xml");
			contentXML = new StringBuffer();
			contentXML.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
			contentXML.append("<data>");
			Iterator<MediaDVO> itrMediaLst = mediaLst.iterator();
			int i=1;
			while(itrMediaLst.hasNext()){
				MediaDVO listItem = itrMediaLst.next();
				contentXML.append("<scrollItem "); 
				contentXML.append(" thumbPath=\""+((listItem.getImageUrl()==null)?"":encodeImageUrl(listItem.getImageUrl()))+"\"" );
				contentXML.append(" clickURL=\""+navURL + listItem.getMediaId()+"\"" );
				contentXML.append(" titleLabel=\""+ encodeHTML(listItem.getDisplayName())+"\"");
				contentXML.append(" yearLabel=\""+ listItem.getReleaseYear()+"\"");
				contentXML.append(" ratingLabel=\""+ listItem.getRatings()+"\"");
				contentXML.append(" genreLabel=\""+ encodeHTML(listItem.getGenre())+"\"");
				contentXML.append(" timeLabel=\""+ listItem.getRunningTime()+"\"");
				contentXML.append(" favorite=\""+ ((listItem.isFavMediaFlag())?"true":"false")+"\"");	
				contentXML.append(" />");
				i++;
			}
			contentXML.append("</data>");
			String strMediaContent = contentXML.length()>0? contentXML.toString():"";
			byte[] bytes;
			bytes= strMediaContent.getBytes();
			out = response.getOutputStream();
			out.write( bytes);
			out.close();
		}
		contentXML = null;
	}
}