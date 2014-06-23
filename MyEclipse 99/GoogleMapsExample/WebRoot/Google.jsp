<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>MyEclipse Google Maps Example</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">

		<script type="text/javascript"
			src="http://www.google.com/jsapi?key=ABQIAAAA7rD1jbUY1XVWsL0ov0bKehRP189eykzPrrAV7tRgT92eURxrGxQHDNt6Nc2DQaubssvpI9493dhTGg"></script>
		<script type="text/javascript">
	  		google.load("maps", "2");
	  		var map = null;
	  		var geocoder = null;
	  		google.setOnLoadCallback(function initmap() {
	  			map = new google.maps.Map2(document.getElementById("map"));
	  			geocoder = new google.maps.ClientGeocoder();
	  		});  		
		</script>
		<script type="text/javascript" src="resources/map.js"></script>
	</head>
	<body>
		<h1>
			MyEclipse Google Maps Example
		</h1>
		
		<hr>
		<table>
			<tr>
				<td valign="top">
					<table>
						<tr>
							<td valign="top" align="left">
								Address:
								<br>
							</td>
							<td />
						</tr>
						<tr>
							<td valign="top" colspan="2">
								<form onsubmit="updateAddress(addrField.value); return false">
									<input type="text" id="addrField">
									<input type="submit" value="Find" />
								</form>
							</td>
						</tr>
						<tr>
							<td align="right">
								Latitude:
							</td>
							<td>
								<input type="text" id="latField" style="width: 95px" />
							</td>
						</tr>
						<tr>
							<td align="right">
								Longitude:
							</td>
							<td valign="top">
								<input type="text" id="lngField" style="width: 95px">
							</td>
						</tr>
					</table>
				</td>
				<td>
					<div id="map"
						style="border: blue 1px solid; width: 320px; height: 240px">
					</div>
				</td>
			</tr>
		</table>
	</body>
</html>
