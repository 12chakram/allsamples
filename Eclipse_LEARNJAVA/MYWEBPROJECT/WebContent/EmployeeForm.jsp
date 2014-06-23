<!-- EmployeeForm.jsp -->

<jsp:useBean id="emp" 
class="com.st.vo.Employee"
scope="session"/>


<form action = "CreateEmployeeProcess.jsp">

<!-- 
Empno:<input type="text" name="empno" value="<%=emp.getEmpno() %>"/>

<br/>

Name:<input type="text" name="name" value="<%=emp.getName() %>"/>
<br/>

Sal:<input type="text" name="sal" value="<%=emp.getSal() %>"/>

<br/>

Deptno:<input type="text" name="deptno" value="<%=emp.getDeptno() %>"/>

  -->


Empno:<input type="text" name="empno" value='<jsp:getProperty name="emp" property ="empno" />'/><br/>
Name:<input type="text" name="name" value='<jsp:getProperty name="emp" property="name"/>'/><br/>
Sal:<input type="text" name="sal" value='<jsp:getProperty name="emp" property="sal" />'/><br/>
Deptno:<input type="text" name="deptno" value='<jsp:getProperty name="emp" property="deptno" />'/>



<input type="submit" value="CreateEmployee">


</form>





