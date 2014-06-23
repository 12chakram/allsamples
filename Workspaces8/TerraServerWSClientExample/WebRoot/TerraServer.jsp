<%@ page language="java"
	import="com.myeclipseide.examples.terraws.client.*"
	pageEncoding="ISO-8859-1"%>

<%@ page import="com.terraservice_usa.Place"%>
<%@ page import="com.terraservice_usa.LonLatPt"%>

<%!// Create a default service endpoint
	TerraServiceClient client = new TerraServiceClient();

	TerraServiceSoap service = client.getTerraServiceSoap();

	//Process result
	LonLatPt result = new LonLatPt();

	String longtitude = "";

	String latitude = "";%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>Terra Server Client</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">

		<script type="text/javascript">
			function checkform()
			{
				if (document.wsexampleform.city.value == "" )
				{
					alert("Please enter a U.S. city name");
					return false;
				}
				else if (document.wsexampleform.state.value == "" )
				{
					alert("Please enter a U.S. state name");
					return false;
				}
				
				return true;
			}
		</script>
	</head>
	<body>
		<form method="get" name="wsexampleform" onsubmit="return checkform()"
			action="">
			<table width="600" border="1">
				<tbody>
					<tr>
						<td colspan="2" align="center">
							<h2>
								Please Enter U.S. City and State
								<br>
							</h2>
						</td>
					</tr>
					<tr>
						<td align="right">
							&nbsp;U.S. City:
							<br>
						</td>
						<td>
							&nbsp;
							<input type="text" name="city">
							<em>Example: &quot;Dallas&quot;</em>
						</td>
					</tr>
					<tr>
						<td align="right">
							&nbsp;U.S. State:
							<br>
						</td>
						<td>
							&nbsp;
							<input type="text" name="state">
							<em>Example: &quot;TX&quot; or &quot;Texas&quot;</em>
						</td>
					</tr>
					<tr>
						<td valign="top">
							<br>
						</td>
						<td valign="top">
							&nbsp;
							<input type="submit" value="Submit">
							&nbsp;
							<input type="reset" value="Reset">
						</td>
					</tr>
					<%
						if ((request.getParameter("city") != null)
								&& (request.getParameter("state") != null)) {

							// Create a default service endpoint
							service = client.getTerraServiceSoap();

							// Setup Query
							Place place = new Place();
							place.setCity(request.getParameter("city"));
							place.setState(request.getParameter("state"));
							place.setCountry("USA");

							//Process result
							result = service.convertPlaceToLonLatPt(place);
							longtitude = String.valueOf(result.getLon());
							latitude = String.valueOf(result.getLat());
						} else {
							longtitude = "";
							latitude = "";
						}
					%>
					<tr>
						<td colspan="2" bgcolor="#c0c0c0">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td align="right">
							Latitude:
						</td>
						<td>
							<input type="text" name="latitude" value="<%=latitude%>">
						</td>
					</tr>
					<tr>
						<td align="right">
							Longitude:
						</td>
						<td>
							<input type="text" name="longitude" value="<%=longtitude%>">
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</body>
</html>
