package au.edu.unsw.sltf.services.bts.ext;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.UUID;

import au.edu.unsw.sltf.services.bts.BuildTimeSeries;
import au.edu.unsw.sltf.services.bts.BuildTimeSeriesFault;
import au.edu.unsw.sltf.services.bts.BuildTimeSeriesFaultException;
import au.edu.unsw.sltf.services.bts.BuildTimeSeriesResponse;
import au.edu.unsw.sltf.services.bts.TimeSeriesFaultType;
import au.edu.unsw.sltf.services.clients.bts.TimeSeriesBuildingServiceStub;
import au.edu.unsw.sltf.services.utils.IOUtil;

public class TimeSeriesBuildingServiceExt {
	
   public BuildTimeSeriesResponse buildTimeSeries(BuildTimeSeries buildTimeSeriesRequest) throws BuildTimeSeriesFaultException{
	   
	   
	  if (!buildTimeSeriesRequest.getEventSetId().matches("\\A\\p{ASCII}*\\z")){
		  BuildTimeSeriesFaultException exp = new BuildTimeSeriesFaultException();
		   BuildTimeSeriesFault buildTimeSeriesFault  = new BuildTimeSeriesFault();
		   buildTimeSeriesFault.setFaultMessage("Your EventSetId is not valid");
		   buildTimeSeriesFault.setFaultType(TimeSeriesFaultType.InvalidEventSetId);
		   exp.setFaultMessage(buildTimeSeriesFault);
		   throw exp;
	  }
	  
	  if (buildTimeSeriesRequest.getTimeIntervalDuration().intValue() <= 0 ){
		   BuildTimeSeriesFaultException exp = new BuildTimeSeriesFaultException();
		   BuildTimeSeriesFault buildTimeSeriesFault  = new BuildTimeSeriesFault();
		   buildTimeSeriesFault.setFaultMessage("TimeIntervalDuration should be a positive integer.");
		   buildTimeSeriesFault.setFaultType(TimeSeriesFaultType.InvalidInterval);
		   exp.setFaultMessage(buildTimeSeriesFault);
		   
		   throw exp;
	   }
	  
	  if (!buildTimeSeriesRequest.getTimeIntervalUnit().equals("hour") &&  !buildTimeSeriesRequest.getTimeIntervalUnit().equals("min")){
		   BuildTimeSeriesFaultException exp = new BuildTimeSeriesFaultException();
		   BuildTimeSeriesFault buildTimeSeriesFault  = new BuildTimeSeriesFault();
		   buildTimeSeriesFault.setFaultMessage("TimeIntervalUnit should be hour or min.");
		   buildTimeSeriesFault.setFaultType(TimeSeriesFaultType.InvalidInterval);
		   exp.setFaultMessage(buildTimeSeriesFault);
		   
		   throw exp;
	   }
	  
	  
	   String errorMessage = "";
	   Runtime runtime = Runtime.getRuntime();
	   try {
		   IOUtil.setupProjectDirectoryStructure();
		   File systemVarsFile = new File (IOUtil.getgenericaggregateWinFile().getParentFile(), "SystemVars.txt");
		   
		   if (systemVarsFile.exists()){
			   systemVarsFile.delete();
		   }
		   systemVarsFile.createNewFile();
		   
		   
		   PrintWriter out = new PrintWriter(systemVarsFile);
		   out.println("-- This is a comment line.");
		   out.println("input_file," + buildTimeSeriesRequest.getEventSetId());
		   out.println("input_file_dir,./../../"+IOUtil.marketDataFolderName+"/");
		   out.println("output_file_dir,./../../" + IOUtil.timeSeriesDataFolderName + "/");
		   out.println("timeIntervalDuration," + buildTimeSeriesRequest.getTimeIntervalDuration());
		   out.println("timeIntervalUnit," + buildTimeSeriesRequest.getTimeIntervalUnit());
		   out.println("inputBlockSize,500");
		   out.println("minOutputBlockSize,10");
		   out.println("stopAfterNoOutputBlocks,10000");
		   out.println("startTradingTime,00:00:00");
		   out.println("limitTradingTime,23:59:59");
		   out.println("targetSecs,ALL");
		   out.println("aggMeasuresFilePrelimHeader,Security,DateOfTrade,StartPeriod,EndPeriod");
		   out.println("measuresUsed,NumTrade,LstPrice,AvPrice");
		   ///
		   out.flush();
		   out.close();
		   
		   
		   Process process = runtime.exec(IOUtil.getgenericaggregateWinFile().getAbsolutePath() //+ " >>" + new File(IOUtil.getgenericaggregateWinFile().getParentFile(), "out.txt").getAbsolutePath()
				   , null, IOUtil.getgenericaggregateWinFile().getParentFile());
		 String line1 = "";
		   BufferedReader in = new BufferedReader(
	               new InputStreamReader(process.getInputStream()) );
	       while ((line1 = in.readLine()) != null) {
	         System.out.println(line1);
	       }
	       in.close();
	       
	   
		   File errorFile = new File (IOUtil.getgenericaggregateWinFile().getParentFile(), "error.txt");
		   if(errorFile.exists()){
			   InputStream    fis;
			   BufferedReader br;
			   String         line;
			   
			   fis = new FileInputStream(errorFile);
			   br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));
			   boolean hasError = false;
			   
			   while ((line = br.readLine()) != null) {
			      if(line.equals("ERROR")){
			    	  hasError = true;
			      }
			      if (hasError){
			    	  errorMessage = line;
			      }
			   }
			   // Done with the file
			   br.close();
			   br = null;
			   fis = null;
		   } 
	   }catch (Exception e) {
		   BuildTimeSeriesFaultException exp = new BuildTimeSeriesFaultException();
		   BuildTimeSeriesFault buildTimeSeriesFault  = new BuildTimeSeriesFault();
		   buildTimeSeriesFault.setFaultMessage(e.getMessage());
		   buildTimeSeriesFault.setFaultType(TimeSeriesFaultType.ProgramError);
		   exp.setFaultMessage(buildTimeSeriesFault);
		   
		   throw exp;
		} 
			   
	   if(!errorMessage.equals("")){
		   BuildTimeSeriesFaultException exp = new BuildTimeSeriesFaultException();
		   BuildTimeSeriesFault buildTimeSeriesFault  = new BuildTimeSeriesFault();
		   buildTimeSeriesFault.setFaultMessage(errorMessage);
		   buildTimeSeriesFault.setFaultType(TimeSeriesFaultType.ProgramError);
		   exp.setFaultMessage(buildTimeSeriesFault);
		   
		   throw exp;
	   }
	   BuildTimeSeriesResponse response = new BuildTimeSeriesResponse();
	   response.setEventSetId(buildTimeSeriesRequest.getEventSetId()+"_output");
	   return response;
	}
		   	
}


