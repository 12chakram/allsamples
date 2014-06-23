<!-- Home2.jsp -->

<%@ page isErrorPage = "true" %>


<%


if (exception!=null) {
%>

<i><b>Error:</b>

<%= exception.getMessage() %></i>
<%

}
%>

<html>

<form action = "ArthimaticServices.jsp">


OP1:<input type= "text" name = "op1"/>

OP2:<input type = "text" name = "op2"/>


<input type="submit" name="submit" value = "Add"/>

<input type ="submit" name= "submit" value = "Subtract"/>







</form>




</html>