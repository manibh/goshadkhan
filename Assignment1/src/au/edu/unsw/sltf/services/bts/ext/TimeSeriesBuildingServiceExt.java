package au.edu.unsw.sltf.services.bts.ext;

import java.util.UUID;

import au.edu.unsw.sltf.services.bts.BuildTimeSeries;
import au.edu.unsw.sltf.services.bts.BuildTimeSeriesResponse;

public class TimeSeriesBuildingServiceExt {
	
   public BuildTimeSeriesResponse buildTimeSeries(BuildTimeSeries buildTimeSeriesRequest){
   	
   	 BuildTimeSeriesResponse response = new BuildTimeSeriesResponse();
   	 response.setEventSetId(UUID.randomUUID().toString());
   	 return response;
   }

}
