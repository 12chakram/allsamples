<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Random"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
function setEmbed(id, url){
	//alert("----------" + url);
	var mydiv = document.getElementById(id);
	//alert(mydiv);
	mydiv.innerHTML = '<embed src="http://localhost:8080/jcaptch/'+url+'" autostart="0" loop="0" height="45" width="170"></embed>'
	+ '<object classid="clsid:22D6F312-B0F6-11D0-94AB-0080C74C7E95">'
    + '<param name="FileName" value="http://localhost:8080/jcaptch/'+url+'" />'
	+ '</object>';
	//alert("fdsfdfdf");
}

function getMimeType(){
	var mimeType = "application/x-mplayer2"; //default
	var agt=navigator.userAgent.toLowerCase();
	if (navigator.mimeTypes && agt.indexOf("windows")==-1) {
	//non-IE, no-Windows
	  var plugin=navigator.mimeTypes["audio/mpeg"].enabledPlugin;
	  if (plugin) mimeType="audio/mpeg"; //Mac/Safari & Linux/FFox
	}//end no-Windows
	return mimeType;
	};//end function getMimeType
</script>
	 
</head>
<body>
<%
String captchaId = new Random().nextInt(99999) + "";
System.out.println(captchaId);
%>
<form action="submit" method="post">
    <img src="submit.jpg?captchaId=<%=captchaId %>" /> <input type="text" name="jcaptcha" value="" />
    <input type="submit" value="Submit"/>
</form>

<h2>Simple Audio Captcha Servlet sample</h2>
	<br/>
	<h4><%=request.getParameter("message")==null?"":request.getParameter("message")%></h4>
	<br/>
	
	<form action="asubmit" method="post">
	<!-- <object classid="clsid:22D6F312-B0F6-11D0-94AB-0080C74C7E95">
    <param name="FileName" value="audiojcaptcha.wav" />
	</object> -->
	     <a href="jcaptcha.wav">Download audio Captcha</a><br/>
	     <a href="audiojcaptcha.wav">Download audio Captcha</a>
	     
	     <br/> 
	     <a href="#"onClick="setEmbed('sample', 'jcaptcha.wav?captchaId=<%=captchaId %>');return false;"><img width="10px" height="10px" src="speaker.gif" alt="Sound Captcha" /></a>
	    <!-- 
	    <a href="#" onClick="setEmbed('sample', 'audiojcaptcha.wav.wav?captchaId=<%=captchaId %>');return false;"><img width="10px" height="10px" src="speaker.gif" alt="Sound Captcha" /></a>
	    -->
	   
	     <input type="text" name="jcaptcha" value="" />
	     <div id="sample" style="visibility: hidden;width:0;height:0"></div>
	     <input type="submit"/>
	</form>
</body>
</html>