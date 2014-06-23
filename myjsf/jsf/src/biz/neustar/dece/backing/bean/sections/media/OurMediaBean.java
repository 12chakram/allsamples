package biz.neustar.dece.backing.bean.sections.media;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import biz.neustar.dece.portal.utils.APIErrorMapUtil;
import biz.neustar.dece.portal.utils.ExceptionUtils;
import biz.neustar.dece.portal.utils.ServiceLocator;
import biz.neustar.dece.portal.utils.SessionUtils;
import biz.neustar.dece.ui.UIDeceException;
import biz.neustar.dece.ui.dvo.MediaDVO;
import biz.neustar.dece.ui.dvo.MediaDashBoardDVO;
import biz.neustar.dece.ui.dvo.MediaFilterDVO;
import biz.neustar.dece.ui.dvo.MediaSummaryCategoryDVO;
import biz.neustar.dece.ui.dvo.MediaSummaryDVO;
import biz.neustar.dece.ui.dvo.PaginatedMediaDVO;
import biz.neustar.dece.ui.dvo.PaginationDVO;
import biz.neustar.dece.ui.dvo.UserAuthDVO;
import biz.neustar.dece.ui.service.UIMediaService;
import biz.neustar.dece.ui.util.MediaFilterConstants;

@ManagedBean(name="ourMediaBean")
@RequestScoped
public class OurMediaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger mediaBeanLogger = Logger.getLogger(OurMediaBean.class);
	
	private List<String> byTypeList;
	private List<String> byGenreList;
	private List<String> byRetailerList;
	private String selectedType="All";
	private String selectedGenre="All";
	private String selectedRetailer="All";
	private String myPurchasedMedia="all";
	private String pageView="LIST";
	private String sortViewBy="ALPHA";
	private String viewMediaTitle;
	private int currentPage=0;
	private int pageSize=4;
	private int prevPageSize=4;
	private int totalPages;
	private String paginationAction="";
	private List<MediaDVO> uiMediaList; 
	private HashMap<String,String> errorValueMap=new HashMap<String, String>();
	private int mediaListSize=-1;
	private String favAction="";
	private String searchMediaTxt="Search for a Media Title";
	private String mediaTitleId;
	private String forFavFlagStatus;
	private String itemFmt = "";
	private boolean inFirstPage=false;
	private boolean hasPrevPage=false;
	private boolean hasNextPage=false;
	private boolean inLastPage=false;
	
	private String concatenateText;
	
	private SessionUtils sessionUtilObj = new SessionUtils();
	private ResourceBundle errorsBundle=sessionUtilObj.getErrorTxtsBundle();
	private String decideMediaPrsnt;
	private String favortiesObj;
	
	
	public OurMediaBean()
	{
			if(null!=sessionUtilObj && null!=sessionUtilObj.getSession())
			{
				if(sessionUtilObj.getSession().getAttribute("checkHouseholdmedia")==null && sessionUtilObj.getSession().getAttribute("hmediacheckover")==null)
					checkHouseholdMedia();
			}
	}
	
	public void loadMediaPage()
	{
		UIMediaService ms = ServiceLocator.getUiMediaService();
		List<MediaSummaryCategoryDVO> medSummary=null;
		try {
			medSummary = ms.getHouseHoldMediaSummary(sessionUtilObj.getUserAuth());
			mediaBeanLogger.info("Lang + Country - "+sessionUtilObj.getLanguageCode()+"  "+sessionUtilObj.getCountryCode());
			//medSummary = ms.getHouseHoldMediaSummary(sessionUtilObj.getUserAuth(), "en-US", "US");
		} catch (UIDeceException e) {
			ExceptionUtils.processUIDECEException("Media Content","ClassName:OurMediaBean::Method:loadMediaPage",e,APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap,errorsBundle );	
		}
		if(null!= medSummary && medSummary.size()>0)
		{
			//System.out.println("medSummary.size(): "+ String.valueOf(medSummary.size()));
			Iterator<MediaSummaryCategoryDVO> itr=medSummary.iterator();
			
			while(itr.hasNext()){
				MediaSummaryCategoryDVO msCategoryDVO = itr.next();
				//System.out.println("Media Category: "+msCategoryDVO.getItemCategory());
				if("by_type".equals(msCategoryDVO.getItemCategory()))
					byTypeList=populateList(msCategoryDVO.getMediaSummaryList());					
				if("by_genre".equals(msCategoryDVO.getItemCategory()))
					byGenreList=populateList(msCategoryDVO.getMediaSummaryList());					
				if("by_retailer".equals(msCategoryDVO.getItemCategory()))
					byRetailerList=populateList(msCategoryDVO.getMediaSummaryList());
					
				
			}//end of while
		}
		/*else
			System.out.println("MediaSummaryDVO is NULL");*/
	}
	
	public void refreshData()
	{
		mediaBeanLogger.info("Received Values are: Type-"+selectedType+", Genre-"+selectedGenre+", Retailer-"+selectedRetailer+", MyPurchasedMedia-"+myPurchasedMedia);
		concatenateText="Received Values are: "+selectedType+", "+selectedGenre+", "+selectedRetailer;
		if(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("mediaTitleId")!=null){
			viewMediaTitle=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("mediaTitleId").toString();
			pageView="DETAIL";
			pageSize=1;
			favAction="viewMediaTitle";
			currentPage=0;
			prevPageSize=0;
			mediaBeanLogger.info("In DETAIL VIEW CONFIG BASED ON mediaTitleId from Query String");
		}
		//MediaFilterDVO uiFilterValVO = new MediaFilterDVO();	
		PaginatedMediaDVO filterResults=null;
		PaginationDVO pgnInputs = null;		
		MediaFilterDVO filInputs = null;
		//System.out.println("viewMediaTitle: "+viewMediaTitle);
		
		try {
			
				if(! ("".equals(favAction) || null==favAction))
				{
					if("AddFav".equals(favAction))
						ServiceLocator.getUiMediaService().createOrUpdateFavoriteMedia(sessionUtilObj.getUserAuth(), viewMediaTitle);
					else if("RemFav".equals(favAction))
						ServiceLocator.getUiMediaService().removeMyFavoriteMedia(sessionUtilObj.getUserAuth(), viewMediaTitle);
					//favAction="";
				}
				if("SEARCH".equals(pageView))
				{
					uiMediaList = ServiceLocator.getUiMediaService().searchHouseholdForMedia(sessionUtilObj.getUserAuth(), searchMediaTxt, sortViewBy);
					if(uiMediaList == null || uiMediaList.size() == 0) {
						uiMediaList = new ArrayList<MediaDVO>();
						mediaListSize=0;					
					}
					else
					{
						pageView="SEARCH";
						mediaListSize=uiMediaList.size();
						currentPage=1;
						totalPages=1;
						inFirstPage=true;
						inLastPage=true;
						FacesContext context=FacesContext.getCurrentInstance();
						if(null!=context.getExternalContext().getApplicationMap().get("mediaPageSizeAll"))
							pageSize = Integer.parseInt(context.getExternalContext().getApplicationMap().get("mediaPageSizeAll").toString());
						else
							pageSize=20000;
					}
				}
				else if("DETAIL".equals(pageView)){
					uiMediaList=null;
					uiMediaList=ServiceLocator.getUiMediaService().getMediaById(sessionUtilObj.getUserAuth(), viewMediaTitle).getMediaList();
					mediaListSize=1;
				}
				else
				{
					filterResults=null;
					pgnInputs = getUIPagination();		
					filInputs = getUIMediaFilters();
					prevPageSize=pageSize;
					paginationAction="";
					filterResults=null;
					filterResults= ServiceLocator.getUiMediaService().getHouseHoldMediaByFilter(sessionUtilObj.getUserAuth(), filInputs, pgnInputs);
				}
		} catch (UIDeceException ex) {
			ExceptionUtils.processUIDECEException("Filter Media","ClassName:OurMediaBean::Method:refreshData",ex,APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap,errorsBundle);			
		}
		if(null!=filterResults){
				if("DETAILLIST".equals(pageView))
				{
					totalPages = (int)( filterResults.getTotalMediaCount()/pageSize + ((filterResults.getTotalMediaCount()% pageSize > 0?1:0)));
					currentPage = (pgnInputs.getStartIndex()/pageSize);
				}
				else
				{
						totalPages = (int)( filterResults.getTotalMediaCount()/pageSize + ((filterResults.getTotalMediaCount()% pageSize > 0?1:0)));
						if(filterResults.getTotalMediaCount()==pgnInputs.getStartIndex())
							currentPage=totalPages;
						else
						currentPage = (pgnInputs.getStartIndex()/pageSize)+1;	
				}
				updatePageNavigationIndicators();
				//System.out.println("currentPageinMethod:"+currentPage);
				//System.out.println("totalPagesinMethod:"+filterResults.getTotalMediaCount());
				uiMediaList = filterResults.getMediaList();
				/*for (Iterator mediaL = uiMediaList.iterator(); mediaL.hasNext();) {
					MediaDVO title = (MediaDVO) mediaL.next();
					System.out.println("title name"+title.getDisplayName());
					List<MediaFormatDVO> format=title.getPurchasedFormat();
					for (Iterator iterator = format.iterator(); iterator.hasNext();) {
						MediaFormatDVO mediaFormatDVO = (MediaFormatDVO) iterator.next();
						System.out.println("flag"+mediaFormatDVO.isMediaConsumeFlag());
						System.out.println("format"+mediaFormatDVO.getMediaFormat());
						
						
						
					}
					System.out.println("*****************************************************");	
				}*/
				mediaListSize=uiMediaList.size();
		}
		else
		{
			if(null==uiMediaList)
				mediaListSize=0;
		}
	}
	
	private void updatePageNavigationIndicators()
	{
		
		if(currentPage==1 && currentPage==totalPages)
		{
			inFirstPage=true;
			inLastPage=true;
			hasNextPage=false;
			hasPrevPage=false;
		}
		else if(currentPage==1)
		{
			inFirstPage=true;
			hasPrevPage=false;
			hasNextPage=true;
			inLastPage=false;
			
		}
		else if(currentPage>1 && currentPage==totalPages)
		{
			hasPrevPage=true;
			hasNextPage=false;
			inLastPage=true;
			inFirstPage=false;
		}
		else
		{
			hasNextPage=true;
			hasPrevPage=true;
			inFirstPage=false;
			inLastPage=false;
		}
	}
	private List<String> populateList(List<MediaSummaryDVO> dvoList){
		
		List<String> uiList=new ArrayList<String>();
		System.out.println("InputListSize: "+ String.valueOf(dvoList.size()));
		
		Iterator<MediaSummaryDVO> itr = dvoList.iterator();
		
		while(itr.hasNext()){
			MediaSummaryDVO summaryDVO = itr.next();			
			uiList.add(summaryDVO.getItemName()+" ("+summaryDVO.getItemValue()+")");
			System.out.println(summaryDVO.getItemName()+"("+summaryDVO.getItemValue()+")");
			
		}//end of while		
		System.out.println("OuputListSize: "+ String.valueOf(uiList.size()));
		return uiList;
	}
	// commented to change retailer to list type
	/*private HashMap<String,String> populateMap(List<MediaSummaryDVO> dvoList){
		
		HashMap<String,String> uiMap=new HashMap<String,String>();
		uiMap.put(sessionUtilObj.getLabelsTxtsBundle().getString("selectRetailer"),"0");
		mediaBeanLogger.info("InputListSize: "+ String.valueOf(dvoList.size()));
		
		Iterator<MediaSummaryDVO> itr = dvoList.iterator();
		
		while(itr.hasNext()){
			MediaSummaryDVO summaryDVO = itr.next();
			uiMap.put(summaryDVO.getItemName(), summaryDVO.getItemValue());			
			mediaBeanLogger.info(summaryDVO.getItemName()+"("+summaryDVO.getItemValue()+")");
			
		}//end of while		
		mediaBeanLogger.info("OuputListSize: "+ String.valueOf(uiMap.size()));
		return uiMap;
	}*/
	
	//ending of retailer conversion
	private PaginationDVO getUIPagination()
	{
		PaginationDVO retPaginationDetails = new PaginationDVO();
		if("pageSize".equals(favAction))
		{
			if(pageSize>prevPageSize)
			{
				int currIdx = ((currentPage-1)*prevPageSize)+1;
				if(currIdx>pageSize)
				{
					currentPage=((currIdx-1)/pageSize)+1;
					retPaginationDetails.setStartIndex((currentPage-1)*pageSize+1);
					retPaginationDetails.setPageSize((currentPage-1)*pageSize+pageSize);
				}
				else
				{
					retPaginationDetails.setStartIndex(1);
					retPaginationDetails.setPageSize(pageSize);
				}
			}
			else
			{
				if(currentPage>1){
					currentPage = (((currentPage-1)*prevPageSize)/pageSize)+1;
					retPaginationDetails.setStartIndex((currentPage-1)*pageSize+1);
					retPaginationDetails.setPageSize((currentPage-1)*pageSize+pageSize);
				}
				else
				{
					retPaginationDetails.setStartIndex(1);
					retPaginationDetails.setPageSize(pageSize);
				}
				
			}
		}
		else if ("paginationAction".equals(favAction))	
		{
			retPaginationDetails.setStartIndex(getStartIdx());
			retPaginationDetails.setPageSize(getStartIdx()+pageSize-1);
		}
		else if ("viewMediaTitle".equals(favAction))
		{
			
			retPaginationDetails.setStartIndex((currentPage-1)*prevPageSize+Integer.parseInt(viewMediaTitle));
			 pageSize=1;
			 retPaginationDetails.setPageSize(pageSize);
		}
		
	    else if("RemFav".equals(favAction)||"AddFav".equals(favAction))
		{
	    	 retPaginationDetails.setStartIndex((currentPage-1)*prevPageSize+1);
	    	 pageSize=prevPageSize;
	    	 retPaginationDetails.setPageSize(getStartIdx()+pageSize-1);
		}
	    else if(pageView.equalsIgnoreCase("GRID") )
	    { 
	    	if("sortViewBy".equals(favAction))
	    	{
	    		retPaginationDetails.setStartIndex(1);
	    		 pageSize=prevPageSize;
			    retPaginationDetails.setPageSize(pageSize);
	    	}
	    	else
	    	{
	    		retPaginationDetails.setStartIndex(1);
	    		pageSize=prevPageSize;
	    		retPaginationDetails.setPageSize(pageSize);	
	    	}
	    }
	    else
	    {
			retPaginationDetails.setStartIndex(1);
		    retPaginationDetails.setPageSize(pageSize);
	    }
		return retPaginationDetails;		
	}
	
	private MediaFilterDVO getUIMediaFilters()
	{
		MediaFilterDVO retMediaFilters = new MediaFilterDVO();		
		if(!"All".equals(selectedGenre)) retMediaFilters.setGenre(selectedGenre);
		if(!"All".equals(selectedType)) retMediaFilters.setType(selectedType);
		if(!"all".equals(myPurchasedMedia)) retMediaFilters.setPurchaseFilter(myPurchasedMedia);
			else
				retMediaFilters.setPurchaseFilter(myPurchasedMedia);
		if(null==favortiesObj || favortiesObj.equals(""))retMediaFilters.setFavorite(null);
			else
				retMediaFilters.setFavorite(favortiesObj);
		if(!"All".equals(selectedRetailer))retMediaFilters.setRetailer(selectedRetailer.replace(")", ""));
		retMediaFilters.setSortBy("ALPHA".equals(sortViewBy)?MediaFilterConstants.SORT_CONSTANT_ALPHABETS:MediaFilterConstants.SORT_CONSTANT_RECENTLY);
		return retMediaFilters;
	}
	
	private int getStartIdx()
	{
		int retStartIdx=(currentPage>0?(currentPage-1)*pageSize+1:1);
		if("FIRST".equals(paginationAction))
			retStartIdx=1;
		else if("PREVIOUS".equals(paginationAction))
			retStartIdx= ((currentPage-1)-1)*pageSize+1;
		else if("NEXT".equals(paginationAction))
			retStartIdx=currentPage*pageSize+1;
		else if("LAST".equals(paginationAction))
			retStartIdx=(totalPages-1)*pageSize+1;		
		//System.out.println("currentPage: "+currentPage+", startIdx: "+retStartIdx);
		return retStartIdx;
	}
	
	
	/**
	 * Method Used for displaying search help items while typing in search
	 * @param chars
	 * @return FavoriteMediaDVO List
	 */
	public List<MediaDVO> autoComplete(String prefix) {
		mediaBeanLogger.info("In AutoComplete");
		List<MediaDVO> lstSrchResutls = null;
		try {
			if(prefix!=null){
				if(prefix.length()>2){
					UserAuthDVO  auth = sessionUtilObj.getUserAuth();
					auth.setPreviousWebPage(auth.getCurrentWebPage());
					auth.setCurrentWebPage("AUTH_MEDIA_LIST_VIEW");				
					lstSrchResutls = ServiceLocator.getUiMediaService().searchHouseholdForMedia(auth, ((String)prefix).trim(), sortViewBy);
					if(null==lstSrchResutls)
					{
						lstSrchResutls = new ArrayList<MediaDVO>();
						MediaDVO mediaDVOObj = new MediaDVO();
						mediaDVOObj.setDisplayName("No media found");
						lstSrchResutls.add(mediaDVOObj);
						return lstSrchResutls;
					}
						
				} else{
					lstSrchResutls = new ArrayList<MediaDVO>();
					MediaDVO mediaDVOObj = new MediaDVO();
					mediaDVOObj.setDisplayName(sessionUtilObj.getLabelsTxtsBundle().getString("mediaSearchTextInsufficient"));
					lstSrchResutls.add(mediaDVOObj);
				}
			}
			if(lstSrchResutls != null && lstSrchResutls.size()!=0)
				return lstSrchResutls;
			else if(lstSrchResutls.size()==0 && prefix.length()>2){
					lstSrchResutls = new ArrayList<MediaDVO>();
					MediaDVO mediaDVOObj = new MediaDVO();
					mediaDVOObj.setDisplayName("No media found");
					lstSrchResutls.add(mediaDVOObj);
					return lstSrchResutls;
				}
				else{
					return (new ArrayList<MediaDVO>());
				}
			
				 
		} catch (UIDeceException ex) {			
			ExceptionUtils.processUIDECEException("MEDIA_PAGE", "ClassName:Mediabean_MethodName:autoComplete", ex, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, errorsBundle);
		}
		return (new ArrayList<MediaDVO>());
	}
	
	
	public String searchFilterAction(){
		if(searchMediaTxt != null && !searchMediaTxt.toString().trim().equalsIgnoreCase(sessionUtilObj.getLabelsTxtsBundle().getString("mediaSearchDefaultText").toString())){
			UserAuthDVO  auth = sessionUtilObj.getUserAuth();
			try {
				if(searchMediaTxt.length()>2){
					uiMediaList = ServiceLocator.getUiMediaService().searchHouseholdForMedia(auth, searchMediaTxt, sortViewBy);				
					if(uiMediaList == null || uiMediaList.size() == 0) {
						uiMediaList = new ArrayList<MediaDVO>();
						mediaListSize=0;
						/*MediaDVO mediaDVOObj = new MediaDVO();
						mediaDVOObj.setDisplayName(sessionUtilObj.getLabelsTxtsBundle().getString("mediaNotAvailable"));
						uiMediaList.add(mediaDVOObj);*/
					}
					else
					{
						pageView="SEARCH";
						mediaListSize=uiMediaList.size();
						currentPage=1;
						totalPages=1;
						inFirstPage=true;
						inLastPage=true;
						FacesContext context=FacesContext.getCurrentInstance();
						if(null!=context.getExternalContext().getApplicationMap().get("mediaPageSizeAll"))
							pageSize = Integer.parseInt(context.getExternalContext().getApplicationMap().get("mediaPageSizeAll").toString());
						else
							pageSize=20000;
					}
				} 
				else 
				{
					/*uiMediaList= new ArrayList<MediaDVO>();
					MediaDVO mediaDVOObj = new MediaDVO();
					mediaDVOObj.setDisplayName(sessionUtilObj.getLabelsTxtsBundle().getString("mediaSearchTextInsufficient"));
					uiMediaList.add(mediaDVOObj);*/
					uiMediaList = new ArrayList<MediaDVO>();
					mediaListSize=0;
				}
			}catch (UIDeceException ex) {			
				ExceptionUtils.processUIDECEException("MEDIA_PAGE", "ClassName:Mediabean_MethodName:searchFilterAction", ex, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, errorsBundle);
			}
			//mediaListSize=uiMediaList.size();
			return "mediaSection";
		}
		else
			searchMediaTxt="Search for a Media Title";
		return "mediaSection";
		
	}
	
	public String forFavortiesNav()
	{
		if(null !=mediaTitleId )
		{
			UserAuthDVO  auth = sessionUtilObj.getUserAuth();
			try {
				PaginatedMediaDVO mediaDvoList = ServiceLocator.getUiMediaService().getMediaById(auth, mediaTitleId);
				if(null!=mediaDvoList)
				uiMediaList=mediaDvoList.getMediaList();
				if(uiMediaList.size()==1)
				{
					pageView="DETAIL";
					pageSize=1;
					currentPage=1;
					totalPages=1;
					inFirstPage=true;
					inLastPage=true;
				}
				else 
				{
					pageView="SEARCH";
					mediaListSize=uiMediaList.size();
					currentPage=1;
					totalPages=1;
					inFirstPage=true;
					inLastPage=true;
					FacesContext context=FacesContext.getCurrentInstance();
					if(null!=context.getExternalContext().getApplicationMap().get("mediaPageSizeAll"))
						pageSize = Integer.parseInt(context.getExternalContext().getApplicationMap().get("mediaPageSizeAll").toString());
					else
						pageSize=200;
				}
				if(uiMediaList!=null)
					mediaListSize=uiMediaList.size();
			} catch (UIDeceException ex) {
				ExceptionUtils.processUIDECEException("MEDIA_PAGE", "ClassName:Mediabean_MethodName:forFavortiesNav", ex, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, errorsBundle);
			}
		}
		return "mediaSection";
	}
	
	/*checking media present in the account are not
	 * 
	 * 
	 */
	public void checkHouseholdMedia()
	{
		UIMediaService mediaServiceObj = ServiceLocator.getUiMediaService();
		if(null!=sessionUtilObj &&null!=sessionUtilObj.getUserAuth())
		{
			try 
			{
				MediaDashBoardDVO mediaDashBoardDVO	=mediaServiceObj.getHouseHoldMediaDashBoardsCounts(sessionUtilObj.getUserAuth());
				if(null!=mediaDashBoardDVO)
				{
					if(mediaDashBoardDVO.getMediaCounts()>0)
					{
						if(null!=sessionUtilObj.getSession())
						{
							sessionUtilObj.getSession().setAttribute("checkHouseholdmedia", true);
						}
					}
					else
					{
						if(null!=sessionUtilObj.getSession())
						{
							sessionUtilObj.getSession().setAttribute("checkHouseholdmedia", false);
							sessionUtilObj.getSession().setAttribute("hmediacheckover", true);
						}
					}
				}
			}
			catch (UIDeceException e) 
			{
				ExceptionUtils.processUIDECEException("checkHouseholdMedia","ClassName:OurMediaBean::Method:checkHouseholdMedia",e,APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap,errorsBundle );
			}
		}
		else
		{
			try{
				FacesContext context=FacesContext.getCurrentInstance();
				context.getExternalContext().redirect("../public/expiryPage.jsf");
			}catch (IOException ioe) {
				if(mediaBeanLogger.isDebugEnabled())   {           
					mediaBeanLogger.debug(ioe.getMessage());
				}
			}
		}
	}
	
	/*public String forFavortiesFlagSet()
	{
		if(null!=forFavFlagStatus && null!= mediaTitleId )
		{
			try 
			{
					if("AddFav".equalsIgnoreCase(forFavFlagStatus))
						ServiceLocator.getUiMediaService().createOrUpdateFavoriteMedia(sessionUtilObj.getUserAuth(), mediaTitleId);
					else if("RemFav".equalsIgnoreCase(forFavFlagStatus))
						ServiceLocator.getUiMediaService().removeMyFavoriteMedia(sessionUtilObj.getUserAuth(), mediaTitleId);
					UserAuthDVO  auth = sessionUtilObj.getUserAuth();
					PaginatedMediaDVO mediaDvoList = ServiceLocator.getUiMediaService().getMediaById(auth, mediaTitleId);
					if(null!=mediaDvoList)
					uiMediaList=mediaDvoList.getMediaList();
					if(uiMediaList.size()==1)
					{
						pageView="DETAIL";
						pageSize=1;
						if(currentPage>1 && totalPages>1)
							updatePageNavigationIndicators();
						else
						{
							currentPage=1;
							totalPages=1;
							inFirstPage=true;
							inLastPage=true;
						}
					}
					else 
					{
						pageView="LIST";
						mediaListSize=uiMediaList.size();
						currentPage=1;
						totalPages=1;
						inFirstPage=true;
						inLastPage=true;
						FacesContext context=FacesContext.getCurrentInstance();
						if(null!=context.getExternalContext().getApplicationMap().get("mediaPageSizeAll"))
							pageSize = Integer.parseInt(context.getExternalContext().getApplicationMap().get("mediaPageSizeAll").toString());
						else
							pageSize=200;
					}
					if(uiMediaList!=null)
						mediaListSize=uiMediaList.size();
			}
			catch (UIDeceException ex) {
				ExceptionUtils.processUIDECEException("MEDIA_PAGE", "ClassName:Mediabean_MethodName:forFavortiesFlagSet", ex, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, errorsBundle);
				}
		}
		return "mediaSection";
	}*/
	
	public List<String> getByTypeList() {
		if(null==byTypeList){
			loadMediaPage();
		}
		return byTypeList;
	}

	public void setByTypeList(List<String> byTypeList) {
		this.byTypeList = byTypeList;
	}

	public void setByGenreList(List<String> byGenreList) {
		this.byGenreList = byGenreList;
	}

	public List<String> getByGenreList() {
		if(null==byGenreList)
		{
			loadMediaPage();
		}
		return byGenreList;
	}


	public void setSelectedType(String selectedType) {
		this.selectedType = ((selectedType.indexOf(" (")>=0)?selectedType.substring(0, selectedType.indexOf(" (")):selectedType);
	}

	public String getSelectedType() {
		if(null==selectedType){
			loadMediaPage();
		}
		return selectedType;
	}

	public void setSelectedGenre(String selectedGenre) {
		this.selectedGenre = ((selectedGenre.indexOf(" (")>=0)?selectedGenre.substring(0, selectedGenre.indexOf(" (")):selectedGenre);
	}

	public String getSelectedGenre() {
		return selectedGenre;
	}

	public void setByRetailerList(List<String> byRetailerList) {
		this.byRetailerList = byRetailerList;
	}

	public List<String> getByRetailerList() {
		return byRetailerList;
	}

	public void setSelectedRetailer(String selectedRetailer) {
		this.selectedRetailer = ((selectedRetailer.indexOf(" (")>=0)?selectedRetailer.substring(0, selectedRetailer.indexOf(" (")):selectedRetailer);
	}

	public String getSelectedRetailer() {
		return selectedRetailer;
	}

	public void setMyPurchasedMedia(String myPurchasedMedia) {
		this.myPurchasedMedia = myPurchasedMedia;
	}

	public String getMyPurchasedMedia() {
		return myPurchasedMedia;
	}

	public String getConcatenateText() {
		return concatenateText;
	}

	public void setConcatenateText(String concatenateText) {
		this.concatenateText = concatenateText;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setMediaListSize(int mediaListSize) {
		this.mediaListSize = mediaListSize;
	}

	public int getMediaListSize() {
		if(-1==mediaListSize)
			refreshData();
		return mediaListSize;
	}

	public void setPaginationAction(String paginationAction) {
		this.paginationAction = paginationAction;
	}

	public String getPaginationAction() {
		return paginationAction;
	}

	public void setUiMediaList(List<MediaDVO> uiMediaList) {
		this.uiMediaList = uiMediaList;
	}

	public List<MediaDVO> getUiMediaList() {		
		if(null==uiMediaList)
			refreshData();
		return uiMediaList;
	}

	public void setPageView(String pageView) {
		this.pageView = pageView;
	}

	public String getPageView() {
		return pageView;
	}

	public void setSortViewBy(String sortViewBy) {
		this.sortViewBy = sortViewBy;
	}

	public String getSortViewBy() {
		return sortViewBy;
	}

	public void setViewMediaTitle(String viewMediaTitle) {
		this.viewMediaTitle = viewMediaTitle;
	}

	public String getViewMediaTitle() {
		return viewMediaTitle;
	}

	public void setFavAction(String favAction) {
		this.favAction = favAction;
	}

	public String getFavAction() {
		return favAction;
	}

	public void setInFirstPage(boolean inFirstPage) {
		this.inFirstPage = inFirstPage;
	}

	public boolean isInFirstPage() {
		return inFirstPage;
	}

	public void setHasPrevPage(boolean hasPrevPage) {
		this.hasPrevPage = hasPrevPage;
	}

	public boolean isHasPrevPage() {
		return hasPrevPage;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	public boolean isHasNextPage() {
		return hasNextPage;
	}

	public void setInLastPage(boolean inLastPage) {
		this.inLastPage = inLastPage;
	}

	public boolean isInLastPage() {
		return inLastPage;
	}

	public void setPrevPageSize(int prevPageSize) {
		this.prevPageSize = prevPageSize;
	}

	public int getPrevPageSize() {
		return prevPageSize;
	}

	public void setSearchMediaTxt(String searchMediaTxt) {
		this.searchMediaTxt = searchMediaTxt;
	}

	public String getSearchMediaTxt() {
		if(pageView!="SEARCH")
			searchMediaTxt="Search for a Media Title";
		return searchMediaTxt;
	}

	public void setMediaTitleId(String mediaTitleId) {
		this.mediaTitleId = mediaTitleId;
	}

	public String getMediaTitleId() {
		return mediaTitleId;
	}

	public void setForFavFlagStatus(String forFavFlagStatus) {
		this.forFavFlagStatus = forFavFlagStatus;
	}

	public String getForFavFlagStatus() {
		return forFavFlagStatus;
	}

	public String getItemFmt() {
		return itemFmt;
	}

	public void setItemFmt(String itemFmt) {
		this.itemFmt = itemFmt;
	}

	public String getDecideMediaPrsnt() 
	{
		if(null==decideMediaPrsnt)
		{
			if(null!=sessionUtilObj && null!=sessionUtilObj.getSession())
			{
				if(null!=sessionUtilObj.getSession().getAttribute("checkHouseholdmedia"))
					this.decideMediaPrsnt=sessionUtilObj.getSession().getAttribute("checkHouseholdmedia").toString();
				else
					decideMediaPrsnt="";
			}
			else
			{
				decideMediaPrsnt="";
			}
		}
		return decideMediaPrsnt;
	}

	public void setDecideMediaPrsnt(String decideMediaPrsnt) {
		this.decideMediaPrsnt = decideMediaPrsnt;
	}

	public String getFavortiesObj() {
		return favortiesObj;
	}

	public void setFavortiesObj(String favortiesObj) {
		this.favortiesObj = favortiesObj;
	}

	
}