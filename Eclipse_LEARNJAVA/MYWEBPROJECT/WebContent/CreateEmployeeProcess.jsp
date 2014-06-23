<!-- CreateEmployeeProcess.jsp -->

<%@ page errorPage = "/EmployeeForm.jsp" %>

<jsp:useBean id="emp"
class="com.st.vo.Employee"
scope="session"/>



<!--   THIS IS IN SCRIPLET TAG

emp.setEmpno(Integer.parseInt(request.getParameter("empno")));
emp.setName(request.getParameter("name"));
emp.setSal(Double.parseDouble(request.getParameter("sal")));
emp.setDeptno(Integer.parseInt(request.getParameter("deptno")));


 -->

<!-- 
 
<jsp:setProperty property="*" name="emp"/>

 -->
 <!-- 
 
 <jsp:setProperty property="empno" name="emp" param="empno"/>
 <jsp:setProperty property="name" name="emp" param="name"/>
 <jsp:setProperty property="sal" name="emp" param="sal"/>
 <jsp:setProperty property="deptno" name="emp" param="deptno"/> 
 
 
  -->
 
 
 
 
 
 
 
 
 

<jsp:useBean id="v"
class="com.st.jsp.Validator"
scope="session"/>


<%

boolean flag=v.validate(emp);

if(flag){

	%>	
	
	<jsp:forward page="/CreateEmployeeSucess.jsp"/>
	<%
	
}
else{
%>
<i>InvalidInput</i><br/>

<jsp:include page="/EmployeeForm.jsp"/> 

<%

}

%>





