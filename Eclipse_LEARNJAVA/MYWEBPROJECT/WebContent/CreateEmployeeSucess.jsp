<!-- CreateEmployeeSucess.jsp -->

<jsp:useBean id="emp"

type="com.st.vo.Employee"
scope="session"></jsp:useBean>

<b>EmployeeCreatedSucessfully:</b><br/>



Empno:<jsp:getProperty name="emp" property="empno"  /><br/>
Name:<jsp:getProperty name="emp" property="name" /><br/>
Sal:<jsp:getProperty name="emp" property="sal" /><br/>
Deptno:<jsp:getProperty name="emp" property="deptno" /><br/>




<!--  

Empno:<%=emp.getEmpno()%> <br/>
Name:<%=emp.getName()%> <br/>
Sal:<%=emp.getSal()%> <br/>
Deptno:<%=emp.getDeptno()%>

-->