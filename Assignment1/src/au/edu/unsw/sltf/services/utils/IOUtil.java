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
		ClassLoader classLoader = TimeSeriesBuildingServiceExt.class.getClassLoader();
		String osName = System.getProperty("os.name");
		String extension = "";
		if (osName.contains("Windows"))
		{
			extension = ".exe";
		}
		return new File(classLoader.getResource(genericaggregateFileName + extension).getPath());
				//(getRootFolder(), genericaggregateFileName+".exe");
	}
	
	public static File getTimeSeriesDataFolder(){
		return new File(getRootFolder(), timeSeriesDataFolderName);
	}
	
	
	public static File getmarketDataFolder(){
		return new File(getRootFolder(), marketDataFolderName);
	}
	
	public static File getRootFolder(){
		ClassLoader classLoader = TimeSeriesBuildingServiceExt.class.getClassLoader();
		File temp = new File(classLoader.getResource("").getPath());
		temp = temp.getParentFile().getParentFile();
		return temp;
	}
}
