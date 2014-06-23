function toggleHintMsg(msgId,styleAttr){
 document.getElementById(msgId).style.display=styleAttr;
}
function parentalControlsRender(frmName,showOrHide){	
	if(showOrHide == 'enable'){				
		document.getElementById(frmName+':parentalControlsRenderFlag').value='false';
		document.getElementById('enableParentalControls').style.display='block';
		document.getElementById('disableParentalControls').style.display='none';
		document.getElementById('parentalControlsPanel').style.display='none';
	}else{
		document.getElementById(frmName+':parentalControlsRenderFlag').value='true';
		document.getElementById('disableParentalControls').style.display='block';
		document.getElementById('enableParentalControls').style.display='none';
		document.getElementById('parentalControlsPanel').style.display='block';
	}
}

function parentalControls(frmName,showOrHide){
	if(showOrHide == 'enable'){
		document.getElementById(frmName+':parentalControlsEnabledFlag').value=false;
		document.getElementById('enableParentalControls').style.display='block';
		document.getElementById('disableParentalControls').style.display='none';
		document.getElementById('parentalControlsSection').style.display='none';
	}else{
		document.getElementById(frmName+':parentalControlsEnabledFlag').value=true;
		document.getElementById('disableParentalControls').style.display='block';
		document.getElementById('enableParentalControls').style.display='none';
		document.getElementById('parentalControlsSection').style.display='block';
	}
}

function Clickheretoprint(url){ 
	  var disp_setting="toolbar=no,location=no,directories=no,menubar=no,"; 
	  disp_setting+="scrollbars=yes,width=1000, height=500, left=100, top=25"; 
  var myIFrame = document.getElementById('printable_area');
  var content_vlue = myIFrame.contentWindow.document.body.innerHTML;
	  var docprint=window.open("","",disp_setting); 
	   docprint.document.open(); 
	   docprint.document.write('<html><h:head><title>UltraViolet - Terms of Use - Print</title>'); 
	   docprint.document.write('</h:head><body><center>'); 
	   docprint.document.write ('<div style="width:900px; margin:0px auto; font-family:arial; text-align:justify; font-size:12px; padding:10px; background:#fff; color:#000;">');        
	   docprint.document.write('<div style="clear:both; text-align:left; float:left; background-color:#000; margin-bottom:30px;"> <img src="'+url+'/images/deceBrand_logo.png"/> </div>');
	   docprint.document.write('<div style="clear:both;"><div style="font-size:18px; clear:both; padding:0px 0 5px 0; text-align:left;  font-weight:bold; width:700px; float:left;">UltraViolet Terms of Use </div>');
	   docprint.document.write('<div style="float:right; position:relative;"><input type="button" value="Print this page" onclick="self.print();"/></div></div>');
	   docprint.document.write('<div style="clear:both;">');
	   docprint.document.write(content_vlue);        
	   docprint.document.write('</div></div></center></body></html>'); 
	   docprint.document.close();
	   docprint.focus();
}
function Clickheretoprintcoppa(){ 
	  var disp_setting="toolbar=no,location=no,directories=no,menubar=no,"; 
	  disp_setting+="scrollbars=yes,width=1000, height=500, left=100, top=25"; 
var myIFrame = document.getElementById('printable_area');
var content_vlue = myIFrame.contentWindow.document.body.innerHTML;
	  var docprint=window.open("","",disp_setting); 
	   docprint.document.open(); 
	   docprint.document.write("<html><h:head><title>UltraViolet Children's Privacy Policy and Consent - Print</title>"); 
	   docprint.document.write('</h:head><body><center>'); 
	   docprint.document.write ('<div style="width:900px; margin:0px auto; font-family:arial; text-align:justify; font-size:12px; padding:10px; background:#fff; color:#000;">');        
	   docprint.document.write('<div style="clear:both; text-align:left; float:left; background-color:#000; margin-bottom:30px;"> <img src="/images/deceBrand_logo.png"/> </div>');
	   docprint.document.write("<div style='clear:both;'><div style='font-size:18px; clear:both; float:left; padding:0px 0 5px 0; text-align:left;  font-weight:bold; width:700px'>UltraViolet Children's Privacy Policy and Consent </div>");
	   docprint.document.write('<div style="float:right; position:relative;"><input type="button" value="Print this page" onclick="self.print();"/></div></div>');
	   docprint.document.write('<div style="clear:both;">');
	   docprint.document.write(content_vlue);        
	   docprint.document.write('</div></div></center></body></html>'); 
	   docprint.document.close();
	   docprint.focus();
}
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

/*
var prevTypeElement;
var prevGenreElement;
var prevMyPurchasedMediaElement


	function setVal(fromElement,forElement, assignVal)
	{
		
		if((forElement!='viewMediaTitle'&& forElement !='AddFav'&& forElement!='RemFav') && document.getElementById('frmMedia:pageView').value =='DETAIL')
			document.getElementById('frmMedia:pageView').value='LIST';
		
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
		else if(forElement=='viewMediaTitle'){
			document.getElementById('frmMedia:pageView').value='DETAIL';
		}
		if(document.getElementById('frmMedia:pageView').value=='GRID' && document.getElementById('frmMedia:pageSize').value==4)
			document.getElementById('frmMedia:pageSize').value=8;
		
		fromElement.style.fontWeight = 'bold';
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
}


 var topicFileNameList = ''; 
 function loadHelpBrowseContent(staticContentBaseUrl){
		if (window.XMLHttpRequest) 
			xhttp=new XMLHttpRequest();
		else 
			xhttp=new ActiveXObject("Microsoft.XMLHTTP");
		xhttp.open("GET",staticContentBaseUrl, false);					
		xhttp.send("");
		xmlDoc=xhttp.responseXML;
		var x=xmlDoc.getElementsByTagName("Topic");
		var count = x.length;
		var browseList = "";
		var liLstDD = "";
		for (i=0;i < count;i++) {					
			var fileName = x[i].getAttribute('fileName');
			var displayName = x[i].getAttribute('displayName');
			
			var onclickLoad = 'onclick=\'loadFaqs(\"'+ fileName +'\",\"\")\'';
			var anchor = '<a href=\'#\' id=\''+ fileName +'BrowseTopic\' ' + onclickLoad+'>';
			browseList  = browseList + '<li>' + anchor + displayName + '</a></li>';

			var onclickAttrDD = 'onclick=\'helpDropDown(\"'+ fileName +'\",\"\")\'';
			var anchorDD = '<a href=\'#\' id=\''+ fileName +'BrowseTopic\' '+ onclickAttrDD +' style=\'padding-left:10px; width:150px;\' >';
			liLstDD  = liLstDD + '<li>' + anchorDD + displayName + '</a></li>';
			topicFileNameList = topicFileNameList + ','+ fileName;
		}
		var listTopicId = document.getElementById('listTopicId');
		listTopicId.innerHTML = browseList;
		
		var helpTopicDropdown = document.getElementById('helpTopicDropdown');
		helpTopicDropdown.innerHTML = liLstDD;
}

 var contactUstopicFileNameList = ''; 
 function loadContactUsBrowseContent(staticContentBaseUrl){
		if (window.XMLHttpRequest) 
			xhttp=new XMLHttpRequest();
		else 
			xhttp=new ActiveXObject("Microsoft.XMLHTTP");
		xhttp.open("GET",staticContentBaseUrl, false);					
		xhttp.send("");
		xmlDoc=xhttp.responseXML;
		var x=xmlDoc.getElementsByTagName("Topic");
		var count = x.length;
		var browseList = "";
		for (i=0;i < count;i++) {					
			var fileName = x[i].getAttribute('fileName');
			var displayName = x[i].getAttribute('displayName');
			
			var onclickLoad = 'onclick=\'helpPageNavigationFunc(\"'+ fileName +'\",\"'+ fileName +'\")\'';
			var anchor = '<a href=\'#\' id=\''+ fileName +'BrowseTopic\' ' + onclickLoad+'>';
			browseList  = browseList + '<li>' + anchor + displayName + '</a></li>';

			contactUstopicFileNameList = contactUstopicFileNameList + ','+ fileName;
		}
		var listTopicId = document.getElementById('listContactUsTopicId');
		listTopicId.innerHTML = browseList;
}

 function helpPageNavigationFunc(helpCategory, fileName){
		document.getElementById("frmContactUS:contactUsHelpCategoryHidden").value = helpCategory; 
		fireEvent(document.getElementById("frmContactUS:helpPageNavigationLink"),'click');
	}
 

function navigateViewDevice(forType,deviceId,devType,viewForm){
	if('DEVICE'==forType)
	{
		document.getElementById(viewForm + ':hdnDeviceId').value=deviceId;
		document.getElementById(viewForm + ':hdnDeviceType').value=devType;
		fireEvent(document.getElementById(viewForm+':navViewDevice'),'click');
	}
	else
	{
		document.getElementById(viewForm + ':hdnPlayerId').value=deviceId;
		fireEvent(document.getElementById(viewForm+':navViewPlayer'),'click');
	}
}

function chkDeviceAddShowOrNavigate(){
	var hdnAddForm = document.getElementById('frmDevicesDashboard:hdnviewAddForm');
	if(hdnAddForm==null){
		document.getElementById('frmAccountDashboard:hdnviewAddForm').value='true';
		fireEvent(document.getElementById('frmAccountDashboard:navAddDevice'),'click');
	}
	return true;
}

function chgAddDeviceDivs(viewMode){
	if(viewMode=='GETCODE')
	{
		document.getElementById('deviceAddCode').style.display='none';
		document.getElementById('deviceClose').style.display='block';
	}
	else if(viewMode=='CLOSE')
	{
		document.getElementById('deviceAddCode').style.display='block';
		document.getElementById('deviceClose').style.display='none';
	}
}

function togglePlayerView(viewMode,itrOf){
	var divElem = document.getElementById('devicesList'+itrOf);
	if(viewMode='m')
	{
		divElem.style.display="block";
		document.getElementById('lessLbl'+itrOf).style.display="block";
		document.getElementById('moreLbl'+itrOf).style.display="none";
	}
	else
	{
		divElem.style.display="none";
		document.getElementById('lessLbl'+itrOf).style.display="none";
		document.getElementById('moreLbl'+itrOf).style.display="block";
	}
}

function chkDeviceDispVal()
{
	document.getElementById('deviceDetailsFormId:updSuccsMsg').style.display="none";	
	var retVal =(document.getElementById('deviceDetailsFormId:deviceDisplayName').value.length < 1)? false:true;	
	if(retVal)
		fireEvent(document.getElementById('deviceDetailsFormId:updBtn'),'click');
	return retVal;
}
*/