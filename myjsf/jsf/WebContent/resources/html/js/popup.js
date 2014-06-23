var popupStatus = 0;

//loading popup with jQuery magic!
function loadPopup1(pcontact, bgcontact){
	//loads popup only if it is disabled
	var popupdiv = document.getElementById(pcontact);
	var pagebgdiv = document.getElementById(bgcontact);
	if(popupStatus==0){
		jQuery("#"+pagebgdiv.id).css({
			"opacity": "0.4"
		});
		
		jQuery("#"+pagebgdiv.id).fadeIn("slow");
		jQuery("#"+popupdiv.id).fadeIn("slow");
		popupStatus = 1;
	}
}

//disabling popup with jQuery magic!
function disablePopup1(pcontact, bgcontact){
	//disables popup only if it is enabled
	var popupdiv = document.getElementById(pcontact);
	var pagebgdiv = document.getElementById(bgcontact);
	if(popupStatus==1){
		jQuery("#"+pagebgdiv.id).fadeOut("slow");
		jQuery("#"+popupdiv.id).fadeOut("slow");
		popupStatus = 0;
		
	}
	
}

//centering popup
function centerPopup1(pcontact, bgcontact){
	//request data for centering
	var windowWidth = document.documentElement.clientWidth;
	var windowHeight = document.documentElement.clientHeight;
	//var windowHeight = window.innerHeight;
	
	var popupdiv = document.getElementById(pcontact);
	var pagebgdiv = document.getElementById(bgcontact);
	
	var popupHeight = jQuery("#"+popupdiv.id).height();
	var popupWidth = jQuery("#"+popupdiv.id).width();

	if(windowWidth/2 >= (windowWidth/2-popupWidth/2))
	{
	//centering
		jQuery("#"+popupdiv.id).css({
			
			"position": "fixed",
			"top": windowHeight/2-popupHeight/2,
			"left": windowWidth/2-popupWidth/2
		});
	}
	else
	{
		jQuery("#"+popupdiv.id).css({
			
			"position": "fixed",
			"top": windowHeight/2-popupHeight/2,
			"left": windowWidth/2-popupWidth/2
		});
	}
	//only need force for IE6
	
	jQuery("#"+pagebgdiv.id).css({
		"height": windowHeight
		
	});
	
	
}

function openPopup(maindiv, bgdiv)
{
		var popupdiv = document.getElementById(maindiv);
		var pagebgdiv = document.getElementById(bgdiv);
		
		//centering with css
		centerPopup1(popupdiv.id,pagebgdiv.id);

		//load popup
		loadPopup1(popupdiv.id,pagebgdiv.id);
		

}
