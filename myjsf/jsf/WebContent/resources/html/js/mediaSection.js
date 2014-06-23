
function fireEvent(obj,evt){
	var fireOnThis = obj;
	if( document.createEvent ) {
	  var evObj = document.createEvent('MouseEvents');
	  evObj.initEvent( evt, true, false );
	  fireOnThis.dispatchEvent(evObj);
	} else if( document.createEventObject ) {
	  fireOnThis.fireEvent('on'+evt);
	}
}


var prevTypeElement;
var prevGenreElement;
var prevMyPurchasedMediaElement;
var prePageSizeElement;
var prevElement;
var prevAllGenreElement;
var prevRetailerElement;
var prevAllRetailerElement;
var prevAllUsers;
var prevFavSelected;
var prevSelectedUserType;
var prevFavCount=0;
var prevMyPurchaseCount=0;

	function setVal(fromElement,forElement, assignVal,typeName)
	{
		var toClick=true;
		prevQid=null;
		episodePrevMreID=null;
		if(forElement=='myPurchasedMedia' || forElement=='selectedType' || forElement=='selectedGenre' || forElement=='selectedRetailer' || forElement=='pageView' || forElement=='myFavortiesId')
		{			
			if(assignVal=='GRID')
			{
				document.getElementById('frmMedia:pageView').value='GRID';
				document.getElementById('frmMedia:pageSize').value='8';
				document.getElementById('frmMedia:prevPageSize').value='8';
			}
			else if(assignVal=='LIST' || document.getElementById('frmMedia:pageSize').value==1 || document.getElementById('frmMedia:pageView').value=='SEARCH')
			{
				document.getElementById('frmMedia:pageView').value='LIST';
				document.getElementById('frmMedia:pageSize').value='4';
			}
		}
		document.getElementById('frmMedia:favAction').value=forElement;
		if(forElement=='AddFav'||forElement=='RemFav'){
			document.getElementById('frmMedia:viewMediaTitle').value=assignVal;
			document.getElementById('frmMedia:favAction').value=forElement;
		}
		else
			document.getElementById('frmMedia:'+forElement).value=assignVal;
		
		if(forElement=='myPurchasedMedia'){
			if(prevMyPurchasedMediaElement==null)
				prevMyPurchasedMediaElement=document.getElementById('lnkmyPurchasedMeida');
			prevMyPurchasedMediaElement.style.fontWeight = 'normal';
			prevMyPurchasedMediaElement=fromElement;
		}
		else if(forElement=='selectedType'){
			if(prevTypeElement==null)
				prevTypeElement=document.getElementById('selectedTypeAll');
			prevTypeElement.style.fontWeight = 'normal';
			prevTypeElement=fromElement;
		}
		else if(forElement=='selectedGenre'){
			if(prevGenreElement==null)
				prevGenreElement=document.getElementById('selectedGenreAll');
			prevGenreElement.style.fontWeight = 'normal';
			prevGenreElement=fromElement;
		}
		else if(forElement=='selectedRetailer'){
			if(prevRetailerElement==null)
				prevRetailerElement=document.getElementById('selectedRetailerAll');
			prevRetailerElement.style.fontWeight = 'normal';
			prevRetailerElement=fromElement;
		}
		else if(forElement=='viewMediaTitle' && typeName !='srchToDetailVi'){
			document.getElementById('frmMedia:pageView').value='DETAILLIST';
			document.getElementById('frmMedia:pageSize').value='1';
			//document.getElementById('frmMedia:mediaTitleId').value=assignVal;
		}
		else if(forElement=='viewMediaTitle' && typeName =='srchToDetailVi'){
			document.getElementById('frmMedia:pageView').value='DETAIL';
			document.getElementById('frmMedia:pageSize').value='1';
			document.getElementById('frmMedia:mediaTitleId').value=assignVal;
		}
		else if(forElement=='pageSize' && assignVal!=4){
			var prevPageSize=document.getElementById('frmMedia:prevPageSize').value;
			if(prevPageSize==assignVal)
				toClick=false;			
		}
		if(document.getElementById('frmMedia:pageView').value=='GRID' && document.getElementById('frmMedia:pageSize').value==4)
			document.getElementById('frmMedia:pageSize').value=8;
		if(forElement!='sortViewBy')
		{
			if(typeName=='selectedType')
			{
				if(prevElement!=null )
				{		if(forElement!='paginationAction'){
					prevElement.style.fontWeight = 'normal';
					prevElement.style.color = '#9AA2DC';}
				}
				if(forElement!='paginationAction')
					prevElement=fromElement;
				fromElement.style.fontWeight = 'bold';
				fromElement.style.color = '#fff';
			}
			else if(typeName=='allTypes')
			{
					if(prevElement!=null )
					{		if(forElement!='paginationAction'){
						prevElement.style.fontWeight = 'normal';
						prevElement.style.color = '#9AA2DC';}
					}
					if(forElement!='paginationAction')
						fromElement.style.fontWeight = 'bold';
						fromElement.style.color = '#fff';
			}
			else if(typeName=='selectedGenre')
			{
					if(prevAllGenreElement!=null )
					{		if(forElement!='paginationAction'){
						prevAllGenreElement.style.fontWeight = 'normal';
						prevAllGenreElement.style.color = '#9AA2DC';}
					}
					if(forElement!='paginationAction')
						prevAllGenreElement=fromElement;
					fromElement.style.fontWeight = 'bold';
					fromElement.style.color = '#fff';
			}
			else if(typeName=='allGenres')
			{
					if(prevAllGenreElement!=null )
					{		if(forElement!='paginationAction'){
						prevAllGenreElement.style.fontWeight = 'normal';
						prevAllGenreElement.style.color = '#9AA2DC';}
					}
					if(forElement!='paginationAction')
					fromElement.style.fontWeight = 'bold';
					fromElement.style.color = '#fff';
			}
			else if(typeName=='selectedRetailer')
			{
					if(prevAllRetailerElement!=null )
					{		if(forElement!='paginationAction'){
						prevAllRetailerElement.style.fontWeight = 'normal';
						prevAllRetailerElement.style.color = '#9AA2DC';}
					}
					if(forElement!='paginationAction')
						prevAllRetailerElement=fromElement;
					fromElement.style.fontWeight = 'bold';
					fromElement.style.color = '#fff';
			}
			else if(typeName=='allRetailers')
			{
					if(prevAllRetailerElement!=null )
					{		if(forElement!='paginationAction'){
						prevAllRetailerElement.style.fontWeight = 'normal';
						prevAllRetailerElement.style.color = '#9AA2DC';}
					}
					if(forElement!='paginationAction')
					fromElement.style.fontWeight = 'bold';
					fromElement.style.color = '#fff';
			}
			else if(typeName=='allUsers')
			{
					if(prevAllUsers!=null)
					{		if(forElement!='paginationAction'){
						prevAllUsers.style.fontWeight = 'normal';
						prevAllUsers.style.color = '#9AA2DC';}
					}
					if(prevFavSelected!=null)
						{
							prevFavSelected.style.fontWeight = 'normal';
							prevFavSelected.style.color = '#9AA2DC';
							if(prevFavCount!=0)
								{
									prevFavCount=0;
									document.getElementById('frmMedia:myFavortiesId').value='';
								}
						}
					if(prevSelectedUserType!=null)
						{
							prevSelectedUserType.style.fontWeight = 'normal';
							prevSelectedUserType.style.color = '#9AA2DC';
							if(prevMyPurchaseCount!=0)
								prevMyPurchaseCount=0;
						}
					if(forElement!='paginationAction')
					fromElement.style.fontWeight = 'bold';
					fromElement.style.color = '#fff';
			}
			else if(typeName=='userSelectedType')
			{
				prevMyPurchaseCount=prevMyPurchaseCount+1;
				if(prevMyPurchaseCount>1 && prevMyPurchaseCount!=null)
				{
					if(prevMyPurchaseCount%2==0)
						{
							if(forElement!='paginationAction')
							{
								document.getElementById('frmMedia:myPurchasedMedia').value='all';
								prevSelectedUserType.style.fontWeight = 'normal';
								prevSelectedUserType.style.color = '#9AA2DC';
							}
						}
					else
						{
							if(forElement!='paginationAction')
							{
								document.getElementById('frmMedia:myPurchasedMedia').value='my';
								prevSelectedUserType=fromElement;
								fromElement.style.fontWeight = 'bold';
								fromElement.style.color = '#fff';
							}
						}
				}
				else
					{
						if(forElement!='paginationAction')
							prevSelectedUserType=fromElement;
						fromElement.style.fontWeight = 'bold';
						fromElement.style.color = '#fff';
					}
			}
			else if(typeName=='selectedFavortie')
				{
				  prevFavCount=prevFavCount+1;
				  if(prevFavCount>1 && prevFavSelected!=null)
					{
		  					if(prevFavCount%2==0)
		  					{
		  						if(forElement!='paginationAction')
								{
		  					      document.getElementById('frmMedia:myFavortiesId').value='';
		  					      prevFavSelected.style.fontWeight = 'normal';
								  prevFavSelected.style.color = '#9AA2DC';
								}
										  
		  					}
		            else
		            {
		            	if(forElement!='paginationAction')
						{
		                  document.getElementById('frmMedia:myFavortiesId').value='fav';
		                  fromElement.style.fontWeight = 'bold';
						  fromElement.style.color = '#fff';
						}
		            }
				}
				  else
					  {
						  if(forElement!='paginationAction')
								prevFavSelected=fromElement;
							fromElement.style.fontWeight = 'bold';
							fromElement.style.color = '#fff';
					  }
				}
		}	

		if(toClick)
			fireEvent(document.getElementById('frmMedia:submitLink'),'click');
		
	
	}

function ShowLessDetails(forDiv){
document.getElementById(forDiv+'Less').style.display="none";
document.getElementById(forDiv+'More').style.display="block";
}
						

function ShowMoreDetails(forDiv){
document.getElementById(forDiv+'Less').style.display="block";
document.getElementById(forDiv+'More').style.display="none";
}

function setPgLinks()
{
	if(document.getElementById('frmMedia:pageView').value =='LIST' || document.getElementById('frmMedia:pageView').value =='GRID' || document.getElementById('frmMedia:pageView').value=='DETAILLIST' || document.getElementById('frmMedia:pageView').value=='SEARCH')
	{
		document.getElementById('viewSort').value=document.getElementById('frmMedia:sortViewBy').value;
		/*var pageSizeEle = document.getElementById('hdr'+document.getElementById('frmMedia:pageSize').value);
		pageSizeEle.style.color="#ffffff";
		pageSizeEle.style.fontWeight="bold";
		
		
		pageSizeEle = document.getElementById('ftr'+document.getElementById('frmMedia:pageSize').value);
		pageSizeEle.style.color="#ffffff";
		pageSizeEle.style.fontWeight="bold";*/
		return true;
	}
}

function toSetFavFlag(pageElement,flagStatus,mediaId)
{
	    document.getElementById('frmMedia:mediaTitleId').value=mediaId;
		document.getElementById('frmMedia:mediaFlagID').value=flagStatus;
		fireEvent(document.getElementById('frmMedia:detailMediaForFav'),'click');
}	

//functions used for autoComplete
function processObjectsChange(suggestionBox) {            
    var items = suggestionBox.getSelectedItems();
    if (items && items.length > 0) {
        var html = "<table cellspaing='8'><thead>" + 
            "<tr><td></td><td>Title</td></tr>" + 
            "</thead><tbody>";
        for ( var i = 0; i < items.length; i++) {
            html += "<tr>";
            html += "<td><img src='";
            html += items[i].imageUrl+"'/>";
            html += "</td>";                    
            html += "<td>";
            html += items[i].displayName;
            html += "</td>";
            html += "</tr>";
        }
        html += "</tbody></table>"                
        $('myDiv').innerHTML = html;                
    } else {
        $('myDiv').innerHTML = "";              
    }
}

function mediaClearText(fieldId, action){
	var field = document.getElementById(fieldId);
    if ('No match found' == field.value && action == 'onfocus') 
	    field.value = '';
    else if ('' == field.value && action == 'onblur')
	    field.value = 'Search Title';
}

function toggleAvailableInfo(divId, vState)
{
	document.getElementById(divId).style.display=vState;
	
}
var prevEpisodeItr;
function toggleEpisode(divItrId,vState)
{
	if(prevEpisodeItr!=null)
	{
		document.getElementById('episodeTitle'+prevEpisodeItr).className='MediaTitle';
		document.getElementById('episodeDesc'+prevEpisodeItr).style.display='none';	
	}
	if(vState=='block')
	{
		//episode title should be in white color 14pt text
		document.getElementById('episodeTitle'+divItrId).className='ActiveState';		
		document.getElementById('episodeDesc'+divItrId).style.display=vState;
	}
	else
	{
		// episode title shoule become violet color (default)
		document.getElementById('episodeTitle'+divItrId).className='MediaTitle';
		document.getElementById('episodeDesc'+divItrId).style.display=vState;
	}
	prevEpisodeItr=divItrId;
}

function toggleMediaDescription(fromDivid,toDivId,styleId)
{
	if(fromDivid!=null && toDivId!=null && styleId!=null)
		{
			document.getElementById(fromDivid).style.display='none';
			document.getElementById(toDivId).style.display='block';
		}
}

var prevQid;
var episodePrevMreID;
function toggleEpisodes(itr)
{
		var sameItemCollpase=false;
		var sameItemExpand=false;
		
		if(prevQid==itr && document.getElementById('episodeDesc'+itr).style.display=="block")
		{
			sameItemCollpase=true;
			sameItemExpand=false;
			if(episodePrevMreID!=null && episodePrevMreID!=undefined && episodePrevMreID!='')
				{
					document.getElementById('episodeShowMoreID'+episodePrevMreID).style.display="block";
					document.getElementById('episodeFullView'+episodePrevMreID).style.display="none";
				}
		}
		if(prevQid==itr && document.getElementById('episodeDesc'+itr).style.display=="none")
		{
			sameItemCollpase=false;
			sameItemExpand=true;
		}
		
		if(sameItemCollpase || prevQid!=null )
		{
			document.getElementById('episodeDesc'+prevQid).style.display="none";
			document.getElementById('expand'+prevQid).style.display="block";
			document.getElementById('collapse'+prevQid).style.display="none";
		}
		if(itr!=prevQid || sameItemExpand)
		{
			document.getElementById('episodeDesc'+itr).style.display="block";
			document.getElementById('expand'+itr).style.display="none";
			document.getElementById('collapse'+itr).style.display="block";
			if(episodePrevMreID!=null && episodePrevMreID!=undefined && episodePrevMreID!='')
			{
				document.getElementById('episodeShowMoreID'+episodePrevMreID).style.display="block";
				document.getElementById('episodeFullView'+episodePrevMreID).style.display="none";
			}
		}
		prevQid=itr;
}
function showMoreEpisodeData(itr,displayStyle,fromId)
{
	if(fromId=='episodeShowMoreID')
		{
			document.getElementById('episodeShowMoreID'+itr).style.display="none";
			document.getElementById('episodeFullView'+itr).style.display="block";
			episodePrevMreID=itr;
		}
	if(fromId=='episodeFullView')
		{
			episodePrevMreID=null;
			document.getElementById('episodeShowMoreID'+itr).style.display="block";
			document.getElementById('episodeFullView'+itr).style.display="none";
		}
}