function Clickheretoprint(url,iframeId){ 
	  var disp_setting="toolbar=no,location=no,directories=no,menubar=no,"; 
	  disp_setting+="scrollbars=yes,width=1000, height=500, left=100, top=25";
  var myIFrame = document.getElementById(iframeId);
  var content_vlue = myIFrame.contentWindow.document.body.innerHTML;
	  var docprint=window.open("","",disp_setting); 
	   docprint.document.open(); 
	   docprint.document.write('<html><head><title>UltraViolet - Terms of Use - Print</title>'); 
	   docprint.document.write('</head><body><center>'); 
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
function Clickheretoprintcoppa(url,iframeId){ 
	  var disp_setting="toolbar=no,location=no,directories=no,menubar=no,"; 
	  disp_setting+="scrollbars=yes,width=1000, height=500, left=100, top=25"; 
var myIFrame = document.getElementById(iframeId);
var content_vlue = myIFrame.contentWindow.document.body.innerHTML;
	  var docprint=window.open("","",disp_setting); 
	   docprint.document.open(); 
	   docprint.document.write("<html><head><title>UltraViolet Children's Privacy Policy and Consent - Print</title>"); 
	   docprint.document.write('</head><body><center>'); 
	   docprint.document.write ('<div style="width:900px; margin:0px auto; font-family:arial; text-align:justify; font-size:12px; padding:10px; background:#fff; color:#000;">');        
	   docprint.document.write('<div style="clear:both; text-align:left; float:left; background-color:#000; margin-bottom:30px;"> <img src="'+url+'/images/deceBrand_logo.png"/> </div>');
	   docprint.document.write("<div style='clear:both;'><div style='font-size:18px; clear:both; float:left; padding:0px 0 5px 0; text-align:left;  font-weight:bold; width:700px'>UltraViolet Children's Privacy Policy and Consent </div>");
	   docprint.document.write('<div style="float:right; position:relative;"><input type="button" value="Print this page" onclick="self.print();"/></div></div>');
	   docprint.document.write('<div style="clear:both;">');
	   docprint.document.write(content_vlue);        
	   docprint.document.write('</div></div></center></body></html>'); 
	   docprint.document.close();
	   docprint.focus();
}
function ClickheretoprintJcoppa(url,iframeId){ 
	  var disp_setting="toolbar=no,location=no,directories=no,menubar=no,"; 
	  disp_setting+="scrollbars=yes,width=1000, height=500, left=100, top=25"; 
var myIFrame = document.getElementById(iframeId);
var content_vlue = myIFrame.contentWindow.document.body.innerHTML;
	  var docprint=window.open("","",disp_setting); 
	   docprint.document.open(); 
	   docprint.document.write("<html><head><title>UltraViolet Junior's Privacy Policy and Consent - Print</title>"); 
	   docprint.document.write('</head><body><center>'); 
	   docprint.document.write ('<div style="width:900px; margin:0px auto; font-family:arial; text-align:justify; font-size:12px; padding:10px; background:#fff; color:#000;">');        
	   docprint.document.write('<div style="clear:both; text-align:left; float:left; background-color:#000; margin-bottom:30px;"> <img src="'+url+'/images/deceBrand_logo.png"/> </div>');
	   docprint.document.write("<div style='clear:both;'><div style='font-size:18px; clear:both; float:left; padding:0px 0 5px 0; text-align:left;  font-weight:bold; width:700px'>UltraViolet Junior's Privacy Policy and Consent </div>");
	   docprint.document.write('<div style="float:right; position:relative;"><input type="button" value="Print this page" onclick="self.print();"/></div></div>');
	   docprint.document.write('<div style="clear:both;">');
	   docprint.document.write(content_vlue);        
	   docprint.document.write('</div></div></center></body></html>'); 
	   docprint.document.close();
	   docprint.focus();
}