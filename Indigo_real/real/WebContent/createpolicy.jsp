<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri ="http://struts.apache.org/tags-bean" prefix="bean" %>

<html>
<head>
	<script type="text/javascript">
		    function validatePolicy(){
			     alert("on click ok");
                var formNo = document.getElementById("fno").value;
                var policyNo = document.getElementById("pno").value;
                var policyName = document.getElementById("pname").value;
                var primimum = document.getElementById("pprimimum").value;
                var paytype = document.getElementById("pptype").value;
                var lifecover = document.getElementById("lifecover").value;
                var cname = document.getElementById("cname").value;
                var age = document.getElementById("age").value;
                var sex = document.getElementById("sex").value;
                var mobile = document.getElementById("mobile").value;
                var mail = document.getElementById("mail").value;

                if(formNo == 0){  
                    alert ("please enter form no"); 
                    return false;
                     
                    }else if (policyNo == 0){   
                        alert ("please enter PolicyNo");  
                        return false;
                    }else if(age>=60){ 
                        alert ("if age is 60 and above this policy is not applicable "); 
                         return false;
                         }	
                                return true;
                
			    }
		    
		</script>

 <link type = "text/css" href = "css/createpolicy.css" rel ="stylesheet" media ="screen"  />
			
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Policy Page</title>
</head>
<body>
	<html:form action="policy.do" >
			<center>
				<table border ="1">
					<tr >
						<td>Formno:<html:text property= "formno" styleId="fno" /></td>
					</tr>
					<tr>	
						<td>policeNumber:<html:text property= "policyno" styleId="pno" /></td>
					</tr>
					<tr>	
						<td>PoliceName:<html:text property= "policyname" styleId="pname" /></td>
					</tr>
					 <tr><td>Primiumamount<html:text property= "policyprimium" styleId="pprimimum"  /></td></tr>
					 <tr><td>paymenttype:<html:text property= "paymenttype" styleId="pptype" /></td></tr>
					 <tr><td>Lifecover<html:text property= "lifecover" styleId="lifecover" /></td></tr>
					 <tr><td>Name:<html:text property= "cname" styleId="cname" /><td></tr>
                     <tr><td>Age:<html:text property= "age" styleId="age" /></td></tr>
                     <tr><td>Gender:<html:text property= "sex" styleId="sex" /></td></tr>
					 <tr><td>Phone:<html:text property= "mobile" styleId="mobile" /></td></tr>
					 <tr><td>mail:<html:text property= "mail" styleId="mail" /></td></tr>
				   
	    
			  </table>
			  <p><html:submit property="method" value= "createPolicy" onclick="return validatePolicy();" style="BACKGROUND-COLOR: #80ff00;" /></p>
		   </center>
		</html:form>
		
		<html:form action="policy.do" >


			PolicyNumber:<html:text property="policyno"/>
			<html:submit property ="method" value="retrievePolicy" />
		</html:form>
		
		
</body>
</html>