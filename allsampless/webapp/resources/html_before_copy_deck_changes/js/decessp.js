/* <![CDATA[ */
function toggleHintMsg(msgId,styleAttr){
 document.getElementById(msgId).style.display=styleAttr;
 return true;
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

function Clickheretoprint(){ 
	  var disp_setting="toolbar=no,location=no,directories=no,menubar=no,"; 
	  disp_setting+="scrollbars=yes,width=650, height=600, left=100, top=25"; 
    var myIFrame = document.getElementById('printable_area');
    var content_vlue = myIFrame.contentWindow.document.body.innerHTML;
	  var docprint=window.open("","",disp_setting); 
	   docprint.document.open(); 
	   docprint.document.write('<html><head><title>UltraViolet - Terms of Use - Print</title>'); 
	   docprint.document.write('</head><body onLoad="self.print(); self.close();"><center>'); 
	   docprint.document.write ('<div style="width:900px; margin:0px auto; font-family:arial; text-align:justify; font-size:12px; padding:10px; background:#fff; color:#000;">');        
	   docprint.document.write('<div style="clear:both; text-align:left; float:left; "> <img src="'+ '#{applicationScope.staticContentBaseUrl}' +'/images/deceBrand_logo.jpg"/> </div>');
	   docprint.document.write('<div style="font-size:18px; clear:both; padding:15px 0 5px 0; text-align:left;  font-weight:bold;">Agree to Terms of Use </div>');
	   docprint.document.write('<div style="padding-left:15px;">');
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

///////////////////////////////////////Media Section related javascript functions/////////////////////////////////

var prevTypeElement;
var prevGenreElement;
var prevMyPurchasedMediaElement


	function setVal(fromElement,forElement, assignVal)
	{
		//alert('This is for the element '+forElement+' and the value is '+assignVal);
		
		if(forElement!='viewMediaTitle' && document.getElementById('frmMedia:pageView').value =='DETAIL')
			document.getElementById('frmMedia:pageView').value='LIST';
					
		document.getElementById('frmMedia:'+forElement).value=assignVal;
		//alert(document.getElementById('frmMedia:'+forElement).value);			
		
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
			//alert(document.getElementById('frmMedia:viewMediaTitle').value);
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
/////////////////////////////////////Media Section related javascript functions/////////////////////////////////
/* ]]> */