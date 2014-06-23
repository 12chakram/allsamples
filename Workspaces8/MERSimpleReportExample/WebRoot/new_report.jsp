<%@ taglib uri="/birt.tld" prefix="birt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>MyEclipse Sample Report Access Webpage</title>
	</head>
	<body>
		<birt:viewer id="birtViewer"
			reportDesign="reports/new_report.rptdesign" pattern="frameset"
			format="HTML" isHostPage="true">
		</birt:viewer>
	</body>
</html>