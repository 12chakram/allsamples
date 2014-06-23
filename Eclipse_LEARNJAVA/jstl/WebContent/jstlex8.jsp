<%@ taglib prefix="x" uri="http://java.sun.com/jstl/xml"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>



<c:import url="emps.xml" var="emps" scope="request"/>

<x:parse doc ="${requestScope.emps}" varDom="empsDom"/>

<x:out select="$empsdom/emps/emp/name"/>

