<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ page
	import="au.edu.unsw.sltf.services.idl.*,java.util.Calendar, java.text.SimpleDateFormat, java.util.* "%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Insert title here</title>
</head>
<body>
	Download operation result page
	<form action="">
		<table>
			<tr>
				<td>
					<li><p>

							<%
								try {
									ImportDownloadServicesStub stub = new ImportDownloadServicesStub(
											"http://localhost:8080/Assignment1/services/ImportDownloadServices");
									DownloadFile dlFile = new DownloadFile();
									dlFile.setEventSetId(request.getParameter("eventSetId"));
									DownloadFileResponse resp = stub.downloadFile(dlFile);
									out.print("EventSetId download URL: " + "<a href ="
											+ resp.getDataURL().toString() + ">");
									out.print(resp.getDataURL().toString() + "</a>");
								} catch (ImportDownloadFaultException exp) {
									out.print("Error: \n");
									out.print("Message: " + exp.getMessage() + "\n");
									out.print("FaultMessage: "
											+ exp.getFaultMessage().getFaultMessage() + "\n");
								}
							%>
						</p></li>
				</td>
			</tr>
		</table>

	</form>
</body>
</html>