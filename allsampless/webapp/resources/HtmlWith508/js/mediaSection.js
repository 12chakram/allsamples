
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


	function setVal(fromElement,forElement, assignVal)
	{
		var toClick=true;
		if(forElement=='myPurchasedMedia' || forElement=='selectedType' || forElement=='selectedGenre' || forElement=='selectedRetailer' || forElement=='pageView')
		{			
			if(assignVal=='GRID')
			{
				document.getElementById('frmMedia:pageView').value='GRID';
				document.getElementById('frmMedia:pageSize').value='8';
			}
			else if(assignVal=='LIST' || document.getElementById('frmMedia:pageSize').value==1)
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
				prevMyPurchasedMediaElement=document.getElementById('myPurchasedMediaAll');
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
			if(prevGenreElement==null)
				prevGenreElement=document.getElementById('selectedRetailerAll');
			prevGenreElement.style.fontWeight = 'normal';
			prevGenreElement=fromElement;
		}
		else if(forElement=='viewMediaTitle'){
			document.getElementById('frmMedia:pageView').value='DETAIL';
			document.getElementById('frmMedia:pageSize').value='1';
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
	document.getElementById('viewSort').value=document.getElementById('frmMedia:sortViewBy').value;
	var pageSizeEle = document.getElementById('hdr'+document.getElementById('frmMedia:pageSize').value);
	pageSizeEle.style.color="#ffffff";
	pageSizeEle.style.fontWeight="bold";
	
	
	pageSizeEle = document.getElementById('ftr'+document.getElementById('frmMedia:pageSize').value);
	pageSizeEle.style.color="#ffffff";
	pageSizeEle.style.fontWeight="bold";
	return true;
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

