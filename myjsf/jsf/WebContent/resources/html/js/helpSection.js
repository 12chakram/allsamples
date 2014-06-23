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
			
			for (i=0,j=9;i < count;i++,j++) {					
				var fileName = x[i].getAttribute('fileName');
				var displayName = escapeHTMLEncode(x[i].getAttribute('displayName'));
				
				var onclickLoad = 'onclick=\'loadFaqs(\"'+ fileName +'\",\"\")\'';
				var anchor = '<a   href=\'#\' title=\''+displayName+'\'  tabindex=\''+(j)+'\' id=\''+ fileName +'BrowseTopic\' ' + onclickLoad+'>';
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

function escapeHTMLEncode(str) {
	  var div = document.createElement('div');
	  var text = document.createTextNode(str);
	  div.appendChild(text);
	  return div.innerHTML;
	 }