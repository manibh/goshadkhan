package au.edu.unsw.sltf.services.bts.ext;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.UUID;

import au.edu.unsw.sltf.services.bts.BuildTimeSeries;
import au.edu.unsw.sltf.services.bts.BuildTimeSeriesResponse;
import au.edu.unsw.sltf.services.utils.IOUtil;

public class TimeSeriesBuildingServiceExt {
	
   public BuildTimeSeriesResponse buildTimeSeries(BuildTimeSeries buildTimeSeriesRequest){
   	
	   Runtime runtime = Runtime.getRuntime();
	   try {
		   IOUtil.setupProjectDirectoryStructure();
		   File systemVarsFile = new File (IOUtil.getRootFolder(), "SystemVars.txt");
		   if (systemVarsFile.exists()){
			   systemVarsFile.delete();
		   }
		   systemVarsFile.createNewFile();
		   
		   PrintWriter out = new PrintWriter(systemVarsFile);
		   out.println("-- This is a comment line.");
		   out.println("input_file," + buildTimeSeriesRequest.getEventSetId());
		   out.println("input_file_dir,./"+IOUtil.marketDataFolderName+"/");
		   out.println("output_file_dir,./" + IOUtil.timeSeriesDataFolderName + "/");
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
		   
		   out.flush();
		   out.close();
		   //systemVarsFile.
		   //"SystemVars.txt"
		   Process process = runtime.exec(IOUtil.getgenericaggregateWinFile().getAbsolutePath()
				   , null, IOUtil.getRootFolder());

	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
   	 BuildTimeSeriesResponse response = new BuildTimeSeriesResponse();
   	 response.setEventSetId(UUID.randomUUID().toString());
   	 return response;
   }

}
