<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Market Data Import and Download Page</title>
</head>
<body>
	Marrket data import and download page
	<form action="ImportResponse.jsp" method="post">
		Import Market Data
		<table>
			<tr>
				<td>Security ID</td>
				<td><input name=secId></td>
			</tr>
			<tr>
				<td>Start Date</td>
				<td><input name=startDate type="text"
					value="dd-MMM-yyyy HH:mm:ss.mmm" style="width: 233px;"></td>
			</tr>
			<tr>
				<td>End Date</td>
				<td><input name=endDate type="text"
					value="dd-MMM-yyyy HH:mm:ss.mmm" style="width: 233px;">
			</tr>
			<tr>
				<td>Data Source URI</td>
				<td><input name=dataSourceUri style="width: 600px;"></td>
			</tr>
		</table>
		<input value="Submit" type="submit">
	</form>
	<br>
	<br>
	<form action="DownloadResponse.jsp" method="post">
		Download Operation
		<table>
			<tr>
				<td>EventSetID</td>
				<td><input name=eventSetId></td>
			</tr>
		</table>
		<input value="Submit" type="submit">

	</form>

</body>
</html>