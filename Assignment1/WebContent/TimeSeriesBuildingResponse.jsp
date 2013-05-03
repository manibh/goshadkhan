<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="au.edu.unsw.sltf.services.clients.bts.TimeSeriesBuildingServiceStub.BuildTimeSeries,au.edu.unsw.sltf.services.clients.bts.TimeSeriesBuildingServiceStub,java.math.BigInteger" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Time Series Building Web Service Response</title>
</head>
<body>
<%

	try{
   
	    TimeSeriesBuildingServiceStub stub = new TimeSeriesBuildingServiceStub("http://localhost:8080/Assignment1/services/TimeSeriesBuildingService");
		BuildTimeSeries buildTimeSeries = new BuildTimeSeries();
		buildTimeSeries.setEventSetId(request.getParameter("inEventSetId"));
		BigInteger timeIntervalDuration =  BigInteger.valueOf(Long.valueOf(request.getParameter("inTimeIntervalDuration")));
		buildTimeSeries.setTimeIntervalDuration(timeIntervalDuration);
		buildTimeSeries.setTimeIntervalUnit(request.getParameter("inTimeIntervalUnit"));
		
		TimeSeriesBuildingServiceStub.BuildTimeSeriesResponse buildTimeSeriesResponse = stub.buildTimeSeries(buildTimeSeries);
		out.print("EventSetId: " + buildTimeSeriesResponse.getEventSetId());
	
	}catch(au.edu.unsw.sltf.services.clients.bts.BuildTimeSeriesFaultException exp){
		out.print("Error: \n");
		out.print("Message: " + exp.getMessage() + "\n");
		out.print("FaultMessage: " + exp.getFaultMessage().getFaultMessage()+ "\n");
		
	}
	/* */

%>

</body>
</html>