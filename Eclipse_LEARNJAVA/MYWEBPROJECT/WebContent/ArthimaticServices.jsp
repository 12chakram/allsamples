<!--  ArthimaticServices.jsp -->

<%@ page errorPage = "/Home2.jsp" %>




<%


int op1 = Integer.parseInt(request.getParameter("op1"));
 
int op2 = Integer.parseInt(request.getParameter("op2"));

String submit = request.getParameter("submit");

if (submit.equals("Add")){
	
	%>
	
	<jsp:forward page="Add.jsp"></jsp:forward>
	
	<%
	
}

else {
	
	%>
	
	<jsp:forward page="Subtract.jsp"></jsp:forward>
	
	<%
	
	
}
	
	%>
	

	
	
	
	
	
















