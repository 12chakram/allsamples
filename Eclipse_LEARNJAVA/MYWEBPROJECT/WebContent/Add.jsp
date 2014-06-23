<!-- Add.jsp -->

<%

int op1 = Integer.parseInt(request.getParameter("op1"));


int op2 = Integer.parseInt(request.getParameter("op2"));


int result = op1+op2;



%>


<jsp:forward page="Result.jsp">

<jsp:param  name="result" value="<%=result%>" />



</jsp:forward>










