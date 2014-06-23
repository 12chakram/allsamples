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

function trim(stringToTrim) {
	return stringToTrim.replace(/^\s+|\s+$/g,"");
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
		document.getElementById('AddAccountMsg').style.display='none';
		document.getElementById('ConnectionCodeMsg').style.display='block';
	}
	else if(viewMode=='CLOSE')
	{
		document.getElementById('deviceAddCode').style.display='block';
		document.getElementById('deviceClose').style.display='none';
		document.getElementById('AddAccountMsg').style.display='block';
		document.getElementById('ConnectionCodeMsg').style.display='none';
	}
}

function togglePlayerView(viewMode,itrOf){
	var divElem = document.getElementById('devicesList'+itrOf);
	if(viewMode=='m')
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

function chkDeviceDispVal(errMsgVal)
{
	document.getElementById('updSuccsMsg').style.display="none";
	
	var inputField = trim(document.getElementById('deviceDetailsFormId:deviceDisplayName').value);
	var retVal =(inputField.length < 1)? false:true;	
	if(retVal)
	{
		fireEvent(document.getElementById('deviceDetailsFormId:updBtn'),'click');
		document.getElementById('deviceDetailsFormId:updMsg').innerHTML='';
		document.getElementById('updSuccsMsg').className="SuccessMsg";		
	}
	else
	{
		document.getElementById('deviceDetailsFormId:updMsg').innerHTML=errMsgVal;
		document.getElementById('updSuccsMsg').style.display="block";
		document.getElementById('updSuccsMsg').className="err_msg";
	}
	return retVal;
}

function myKeyPressedEvent(e) {
	var browserName  = navigator.appName;
	 e = e || window.event;
	 var unicode=e.charCode ? e.charCode : e.keyCode ? e.keyCode : 0;
	 if (unicode == 13) {
		 if(browserName=='Microsoft Internet Explorer') {
				 document.getElementById('deviceDetailsFormId:updBtn').click();
			        return false;
		}else {
	        return true;
	    }
	}
}

