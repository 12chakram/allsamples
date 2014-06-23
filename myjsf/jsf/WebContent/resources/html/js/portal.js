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
var componentId;
var closePopup=false;
function handleSaveChangesPopup(id){
	componentId=id;
	if(!closePopup){
		openPopup('accountNameUnsavedChanges', 'backgroundPopup');
	}
	return closePopup;
}
function handleNavigationFromPopup(){
	disablePopup1('accountNameUnsavedChanges', 'backgroundPopup');
	closePopup=true;
	var fireOnThis = document.getElementById(componentId);
	if( document.createEvent ) {
	  var evObj = document.createEvent('MouseEvents');
	  evObj.initEvent( 'click', true, false );
	  fireOnThis.dispatchEvent(evObj);
	} else if( document.createEventObject ) {
	  fireOnThis.fireEvent('onclick');
	}	
}