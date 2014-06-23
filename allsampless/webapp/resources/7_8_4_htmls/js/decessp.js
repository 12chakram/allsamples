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


function chgParentalControlsState(parentalFormId)
{
	var e = document.getElementById(parentalFormId+':parentalcontrol');
	if(e.checked){
		document.getElementById('tabInnerContent').style.display='block';
		document.getElementById('tabInnerContent1').style.display='block';
	}
	else
	{
		document.getElementById('tabInnerContent').style.display='none';
		document.getElementById('tabInnerContent1').style.display='none';
	}
}

 function toggleHint(divId)
 {
	 
 	var e = document.getElementById(divId+'Hint');
 	if(e.style.display =='block')
 		e.style.display='none';
 	else
 		e.style.display='block';
 		
 }
 
 
 function disableAction(id){
		document.getElementById(id).removeAttribute('href');
		document.getElementById(id).disabled="true"
		document.getElementById(id).setAttribute('style','cursor:default;');
		return true;
	}
	
	function countriesflag(locale){
		if(locale == 'country_language')
			document.getElementById("flags").style.display="block";
		else
			document.getElementById('selectbox').innerHTML = document.getElementById(locale).innerHTML;
			
	}
	function countriesflagclose(){
		document.getElementById("flags").style.display="none";
		
	}
 
 function selectedAccessLvl(frmName)
	{
		var clientBrowser = navigator.appName;
		 var idx=0;		 
		 if('Microsoft Internet Explorer'==clientBrowser)
			 idx=1;
		 var hdnFieldFormName=frmName;
		 if('formFields'== frmName){
			 hdnFieldFormName = 'formFieldsSteps';		 
		 }
		 var radGrp = document.getElementsByName(hdnFieldFormName+':permissionRad');
		 for (i = 0; i < radGrp.length; i++) {
			 if(radGrp[idx+i].checked)
			 {
				 document.getElementById(hdnFieldFormName+':hdnAccessLevel').value=radGrp[idx+i].value;
				 break;
			 }
		 }		 
	}
 
 function selectedAccessLvl(frmName)
	{
		var clientBrowser = navigator.appName;
		 var idx=0;		 
		 if('Microsoft Internet Explorer'==clientBrowser)
			 idx=1;
		 var hdnFieldFormName=frmName;
		 if('formFields'== frmName){
			 hdnFieldFormName = 'formFieldsTab';		 
		 }
		 var radGrp = document.getElementsByName(hdnFieldFormName+':permissionRad');
		 for (i = 0; i < radGrp.length; i++) {
			 if(radGrp[idx+i].checked)
			 {
				 document.getElementById(hdnFieldFormName+':hdnAccessLevel').value=radGrp[idx+i].value;
				 break;
			 }
		 }		 
	}
 
 function configureForm(frmName, accessLvl,txtVal)
 {
		 var clientBrowser = navigator.appName;
		 var idx=0;		 
		 if('Microsoft Internet Explorer'==clientBrowser)
			 idx=1;
		 var memberAgeCat=accessLvl;
		 var hdnFieldFormName=frmName;
		 var copDiv='coppa_tc';
		 var eulaDiv='eula_tc';
		 var requireCOPPA = ( ('coppaEnabled' == document.getElementById(hdnFieldFormName+':hdnCoppaState').value)?true:false);		 
		 if('formFields'== frmName){
			 hdnFieldFormName = 'formFieldsSteps';
			 copDiv='coppa_tc_tab';
			 eulaDiv='eula_tc_tab';
		 }
		 document.getElementById(hdnFieldFormName+':hdnAgeOfMember').value=memberAgeCat;		 
		 document.getElementById(frmName+':selectInputText').value=txtVal; 
		
		// setting COPPA to disabled state
		document.getElementById(copDiv).style.color= '#555';
		document.getElementById(frmName+':coppaAcceptCheck').checked=false;
	 	document.getElementById(frmName+':coppaAcceptCheck').disabled=true;
		
	 	var radGrp = document.getElementsByName(frmName+':permissionRad');		 
			 if('CHILD'==memberAgeCat||'YOUTH'==memberAgeCat)
			 {	
				 for (i = 0; i < radGrp.length; i++) {
					 if(radGrp[idx+0].value=='BASIC')
					 {
						 radGrp[idx+0].checked=true;
						 fireEvent(radGrp[idx+0],'click');
						 break;
					 }
				 }		 	

			 	radGrp[idx+0].disabled=true;
			 	radGrp[idx+1].disabled=true;
			 	radGrp[idx+2].disabled=true;
			 	if('CHILD'==memberAgeCat && requireCOPPA)
			 	{
			 		//coppa required only if configured yes on server read from InitServlet and set to hidden input hdnCoppa State
			 		document.getElementById(copDiv).style.color= '#fff';
			 		document.getElementById(frmName+':coppaAcceptCheck').disabled=false;
			 		document.getElementById(frmName+':coppaAcceptCheck').checked=false;
			 	}
			 	//eula is required
			 	document.getElementById(frmName+':eulaAccept').disabled=false;
		        document.getElementById(eulaDiv).style.color= '#fff';

			 	//document.getElementById(hdnFieldFormName+':hdnPrivilege').value='BASIC';
			 }
		 	if('ADULT'==memberAgeCat)
		 	{	
			 	radGrp[idx+0].disabled=false;
			 	radGrp[idx+1].disabled=false;
			 	radGrp[idx+2].disabled=false;
				var accessVal = 'FULL';				
				/*
				 * if(document.getElementById(hdnFieldFormName+':accessLevel').value!='')
					accessVal=document.getElementById(hdnFieldFormName+':accessLevel').value;
					*/
				//document.getElementById(hdnFieldFormName+':hdnPrivilege').value=accessVal;
				if(radGrp[idx+0].value==accessVal)
			 		radGrp[idx+0].checked=true;
				else if(radGrp[idx+1].value==accessVal)
			 		radGrp[idx+1].checked=true;
				else if(radGrp[idx+2].value==accessVal)
			 		radGrp[idx+2].checked=true;
		 		document.getElementById(frmName+':coppaAcceptCheck').checked=false;
		 		document.getElementById(frmName+':eulaAccept').checked=false;
		 		document.getElementById(frmName+':coppaAcceptCheck').disabled=true;
		 		document.getElementById(frmName+':eulaAccept').disabled=true;
				document.getElementById(copDiv).style.color= '#555';
				document.getElementById(eulaDiv).style.color= '#555';		 		
		 	}

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
		document.getElementById("contactUsFormId:contactUsHelpCategoryHidden").value = helpCategory; 
		fireEvent(document.getElementById("contactUsFormId:helpPageNavigationLink"),'click');
	}
	 
//Login Functions
		function input_load(){
		
				
			if (document.getElementById('FrmLogin:inputUName').value.length==0)
			{
				document.getElementById('FrmLogin:inputUName').style.opacity="0";
				document.getElementById('FrmLogin:inputUName').style.filter="alpha(opacity=0)";
			}
			else
			{
				document.getElementById('FrmLogin:inputUName').style.opacity="1";
				document.getElementById('FrmLogin:inputUName').style.filter="alpha(opacity=100)";
				document.getElementById('FrmLogin:inputUName').style.border="1px solid #fff";
			}
			
			
			if (document.getElementById('FrmLogin:inputselect').value.length==0)
			{
				document.getElementById('FrmLogin:inputselect').style.opacity="0";
				document.getElementById('FrmLogin:inputselect').style.filter="alpha(opacity=0)";
			}
			else
			{
				document.getElementById('FrmLogin:inputselect').style.opacity="1";
				document.getElementById('FrmLogin:inputselect').style.filter="alpha(opacity=100)";
				document.getElementById('FrmLogin:inputselect').style.border="1px solid #fff";
				document.getElementById('FrmLogin:inputselect').Value="";
				
			}		
		}
		
		function input_focus(ctrlId){
			document.getElementById(ctrlId).style.opacity="1";
			document.getElementById(ctrlId).style.filter="alpha(opacity=100)";
			document.getElementById(ctrlId).style.border="1px solid #fff";
		}

		function input_blur(ctrlId){
			var inputsel= document.getElementById(ctrlId).value
			if(inputsel.length <= 0){
			document.getElementById(ctrlId).style.opacity="0";
			document.getElementById(ctrlId).style.filter="alpha(opacity=0)";
			}
		}
		
		
		function clearText(field){
		    if (field.defaultValue == field.value) field.value = '';
		    else if (field.value == '') field.value = field.defaultValue;
		}
//Login Functions	 
	 
	 function delCk(usr, pss){
		 var d = new Date();
		 d.setTime ( d.getTime() - 1 );
		 document.cookie = usr +'=; expires=' + d.toGMTString() + ';' + ';';
		 document.cookie = pss +'=; expires=' + d.toGMTString() + ';' + ';';
		 return true;
	 }
	 