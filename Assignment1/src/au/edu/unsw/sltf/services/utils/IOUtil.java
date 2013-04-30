package au.edu.unsw.sltf.services.utils;

import java.io.File;

import au.edu.unsw.sltf.services.bts.ext.TimeSeriesBuildingServiceExt;

public class IOUtil {

	public static final String timeSeriesDataFolderName = "time_series_data";
	
	public static final String marketDataFolderName = "market_data";
	
	public static final String genericaggregateFileName = "genericaggregate";

	
	public static void setupProjectDirectoryStructure(){
		 File timeSeriesDataFolder = getTimeSeriesDataFolder();
		 File marketDataFolder = getmarketDataFolder();
		 
		 if (!timeSeriesDataFolder.exists()){
			 timeSeriesDataFolder.mkdir();
		 }
		 
		 if (!marketDataFolder.exists()){
			 marketDataFolder.mkdir();
		 }
	}
	
	public static File getgenericaggregateWinFile(){
		return new File(getRootFolder(), genericaggregateFileName+".exe");
	}
	
	public static File getTimeSeriesDataFolder(){
		return new File(getRootFolder(), timeSeriesDataFolderName);
	}
	
	
	public static File getmarketDataFolder(){
		return new File(getRootFolder(), marketDataFolderName);
	}
	
	public static File getRootFolder(){
		ClassLoader classLoader = TimeSeriesBuildingServiceExt.class.getClassLoader();
		return new File(classLoader.getResource("").getPath());
	}
}
