<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri ="http://struts.apache.org/tags-html" prefix="html" %>
    <%@ taglib uri ="http://struts.apache.org/tags-bean" prefix="bean" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Flights Search Page </title>
	</head>
		<script type="text/javascript">

	    
	       var IE =document.all?true:false;
		
			function flightSerachAjax() { 
				//alert("in ajax");
	             if(!IE){ 

	                 var objXml = new XMLHttpRequest();
	                 

	                 }else {
	                     var objXml = new ActiveXObject("Microsoft.XMLHTTP");          
	                 } 
	              var searchUrl = "flightsearch.do?from="+document.getElementById("f").value+"&to="+document.getElementById("t").value+"&date="
	                               +document.getElementById("d").value;

              // alert(searchUrl);

               objXml.open("POST",searchUrl,true);
               objXml.setRequestHeader("Cache-Control", "no-cache");
				 


    objXml.onreadystatechange = function(){ 

			if(objXml.readyState==4){  
              // alert(objXml.readyState);
              // alert(objXml.status);
                  if (objXml.status!=404){  
                    //  alert(objXml.responseText);
                          
                         if((objXml.responseText).length>0){  
								document.getElementById("searchresult").innerHTML = objXml.responseText;
                             }
                      }

				}

 	   
        };
		         objXml.send(null);
                 return false;			                 
			} // function 





		
			     function validateform(){ 
                     var from = document.getElementById("f").value;
                     var to = document.getElementById("t").value;
                     var date = document.getElementById("d").value;

                            if( from == ""){ 
                                alert ("please select source"); 
                                return false; 
                                }
                            else if (to == ""){
                                 alert ("please select destination"); 
                                 return false;
                                 }
                            else if (from == to){   
                                alert ("source and destination should not be the same "); 
                                return false; 
                                }
                            else if(date == ""){
                                 alert ("please enter the date") 
                                 return false;
                            }
                                 
                              return  flightSerachAjax();
                            }   


              var currentDate =new Date();
              var day = currentDate.getDate();
              var month =currentDate.getMonth()+1;
              var year =currentDate.getFullYear();
 document.write("<p align = 'right'><b>" + day + "/" +month + "/" + year + "</b></p>");<br/>                      

 </script>
		
		
		<body style="border: double;border-color:red; border-bottom-style: outset; border-spacing: 9px;">
				<center style="background-image: url('bg-Sachin.jpg');"><b style="font-size: x-large; color: yellow;">Select From and To and Click to serach</b></center><br/><br/> 
		 	<html:form action ="flightsearch.do">
		 		<b>From:</b><html:select  property="from" styleId ="f" style=" width : 110px; height : 30px;">
		 			 <html:option value="">--None--</html:option>
					 <html:option value="hyd">Hyderabad</html:option>	 		
		 		     <html:option value="mum">Mumbai</html:option>	 		
		 		     <html:option value="delhi">Delhi</html:option>	 		
		 		     <html:option value="chn">Chennai</html:option>	 		
		 		     <html:option value="bag">Bangalore</html:option>	 		
		 		</html:select>
		 	    To:<html:select property="to" styleId = "t" style=" width : 110px; height : 30px;" >
		 			 <html:option value="">--None--</html:option>
					 <html:option value="hyd">Hyderabad</html:option>	 		
		 		     <html:option value="mum" >Mumbai</html:option>	 		
		 		     <html:option value="delhi">Delhi</html:option>	 		
		 		     <html:option value="chn">Chennai</html:option>	 		
		 		     <html:option value="bag">Bangalore</html:option>	 		
		 		</html:select><br><br>
		 		Enter Date:<html:text property="date"  styleId ="d" style="border-color:Cyan;border-bottom-color:SeaGreen;"/>
		 	    <html:submit value="search"  onclick= "return validateform();" style="FONT-SIZE: medium; BACKGROUND-COLOR: #80ff00;"/>

		 	</html:form> <br/> <br/> <br/>
		 	<hr>
		 	<div id ="searchresult"/>

		 
		 
		 </body>
</html>