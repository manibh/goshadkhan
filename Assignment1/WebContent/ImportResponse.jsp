<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ page
	import="au.edu.unsw.sltf.services.idl.*,java.util.Calendar, java.text.SimpleDateFormat, java.util.* "%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Import market data results</title>
</head>
<body>
	Import market data operation result
	<form action="">
		<table>
			<tr>
				<td>
					<li><p>
							<b>Result</b>
							<%
								try {
									ImportDownloadServicesStub stub = new ImportDownloadServicesStub(
											"http://localhost:8080/Assignment1/services/ImportDownloadServices");
									ImportMarketData importMarketData = new ImportMarketData();
									importMarketData.setSec(request.getParameter("secId"));
									SimpleDateFormat sdf = new SimpleDateFormat(
											"dd-MMM-yyyy HH:mm:ss");
									Calendar temp = Calendar.getInstance();
									temp.setTime(sdf.parse(request.getParameter("startDate")));
									importMarketData.setStartDate(temp);

									temp = Calendar.getInstance();
									temp.setTime(sdf.parse(request.getParameter("endDate")));
									importMarketData.setEndDate(temp);

									importMarketData
											.setDataSourceURL(new org.apache.axis2.databinding.types.URI(
													request.getParameter("dataSourceUri")));
									ImportMarketDataResponse resp = stub
											.importMarketData(importMarketData);
									out.print("Your file has been uploaded and your EventSetId is: "
											+ resp.getEventSetId());
								} catch (ImportDownloadFaultException exp) {
									out.print("Error: \n");
									out.print("Message: " + exp.getMessage() + "\n");
									out.print("FaultMessage: "
											+ exp.getFaultMessage().getFaultMessage() + "\n");
								} catch (Exception exp) {
									out.print("Error: \n");
									out.print("Message: " + exp.getMessage() + "\n");									
								}
							%>
						</p></li>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>