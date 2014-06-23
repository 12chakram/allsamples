<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/dwr/interface/RemoteCarViewer.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/dwr/engine.js"></script>
		<script type="text/javascript">
		var reply = function (data){
			var results = document.getElementById('results');
			results.innerHTML = data + '';
		}
		function displayCustomers(){
			clearResults();
			RemoteCarViewer.getCustomerData(reply);
		}
		function displayEmployees(){
			clearResults();
			RemoteCarViewer.getEmployeeData(reply);
		}
		function displayProducts(){
			clearResults();
			RemoteCarViewer.getProductData(reply);
		}
		
		function clearResults() {
			var results = document.getElementById('results');
			results.innerHTML = "Loading data...";
		}
	</script>
	</head>
	<body>
		<h1>
			Classic Car Information
		</h1>
		<hr width="100%" size="2">
		Page loaded:
		<%=new Date()%><hr>
		<input type="button" value="List Customers" name="button1"
			onclick="displayCustomers()">
		<input type="button" value="List Employees" name="button2"
			onclick="displayEmployees()">
		<input type="button" value="List Products" name="button3"
			onclick="displayProducts()">
		<br>
		<hr width="100%" size="2">
		<div id="results"
			style="background: #f0f0f0; position: relative; width: 100%; height: 100%">
		</div>
		<hr>
		<h3>
			System Summary
		</h3>
		<%@ include file="summary.jspf"%>
	</body>
</html>
