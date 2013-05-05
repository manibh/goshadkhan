<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Time Series Building Web Service</title>
</head>
<body>

<form action="TimeSeriesBuildingResponse.jsp" method="POST" target="_blank">
	<table>
	 <tr>
	 	<td>EventSetId</td>
	 	<td><input type="text" value="" name="inEventSetId"/></td>
	 </tr>
	  <tr>
	 	<td>TimeIntervalDuration</td>
	 	<td><input type="text" value="4" name="inTimeIntervalDuration"/></td>
	 </tr> 
	  <tr>
	 	<td>TimeIntervalUnit</td>
	 	<td><input type="text" value="hour" name="inTimeIntervalUnit"/></td>
	 </tr>  
	 <tr>
	 	<td></td>
	 	<td><input  type="submit" value="Submit" /></td>
	 </tr>
	</table>
</form>
</body>
</html>