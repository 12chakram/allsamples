
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>



<fmt:parseNumber 

value="${param.salary}" var="sal" scope="request" pattern="00,000.00"/>

Salary:<fmt:formatNumber value="${requestscope.sal*1.1 }" pattern="00,000.00"/>