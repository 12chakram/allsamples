// JavaScript Document
if(typeof String.prototype.trim !== 'function') { 
  String.prototype.trim = function() { 
    return this.replace(/^\s+|\s+$/g, '');  
  } 
}


var tabsH = null;

var topIndex = 0;
var bottomIndex = 3;

function openTab(itemid, contentid)
{
	if(itemid == 'onload' && contentid == 'onload' && tabsH == null){
		var idArray = document.getElementById('linkedAccountsIdsReferenceJS');
		var ids = idArray.innerHTML.trim().split(",");
		var rtabItem = null;
		var rtabContent = null;
		for(i=0;i<ids.length;i++)
			if(ids[i].trim().length != 0){
				rtabItem = document.getElementById("aca"+ids[i].trim());
				rtabContent = document.getElementById("acdiv"+ids[i].trim());		
				rtabItem.className = "";
				rtabContent.style.display = "none";
			}
			rtabItem = document.getElementById("aca"+ids[0].trim());
			rtabContent = document.getElementById("acdiv"+ids[0].trim());
			rtabItem.className = "active";
			rtabContent.style.display = "inline";
			tabsH = "";
			return;
	}
	var tabItem = document.getElementById(itemid);
	var tabContent = document.getElementById(contentid);
	var find_ac_Item =  tabItem.id.indexOf('aca');
	
	if(find_ac_Item	!= -1)
	{
		var idArray = document.getElementById('linkedAccountsIdsReferenceJS');
		var ids = idArray.innerHTML.trim().split(",");
		for(i=0;i<ids.length;i++)
			if(ids[i].trim().length != 0){
				var rtabItem = document.getElementById("aca"+ids[i].trim());
				var rtabContent = document.getElementById("acdiv"+ids[i].trim());		
				rtabItem.className = "";
				rtabContent.style.display = "none";
			}
			tabItem.className = "active";
			tabContent.style.display = "inline";
	}
	return;
}
	
	function tabControl(){
		var idArray = document.getElementById('linkedAccountsIdsReferenceJS');
		var ids = idArray.innerHTML.trim().split(",");
		for(i=0;i<ids.length;i++) {
			if(ids[i].trim().length != 0){
				var rtabItem = document.getElementById("aca"+ids[i].trim());
				rtabItem.style.display= "none";
			}
		}
		for(i=topIndex;i<=bottomIndex;i++){
			if(ids[i].trim().length != 0){
				var rtabItem = document.getElementById("aca"+ids[i].trim());
				rtabItem.style.display= "block";
			}
		}
	}
	
	function nextAccounts(){
		topIndex = topIndex + 4;
		bottomIndex = bottomIndex +4 ;
	}
	
	function prevAccounts(){
		if(topIndex > 0 )
			topIndex = topIndex - 4;
		if(bottomIndex > 0)
		bottomIndex = bottomIndex - 4;
	}