/**
 * 
 */
package au.edu.unsw.sltf.services.idl.ext;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

//import org.apache.axis2.databinding.types.URI;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import au.edu.unsw.sltf.services.idl.ImportMarketData;

/**
 * @author Mani
 * 
 */
public class ImportMarketDataOpImpl {

	private ImportMarketData importMarketData;

	/**
	 * @param importMarketData0
	 * 
	 */
	public ImportMarketDataOpImpl(ImportMarketData importMarketData) {
		// TODO Auto-generated constructor stub
		this.importMarketData = importMarketData;
		downloadFileFromUri("/Users/Mani/Java/");
		createFilteredFile("/Users/Mani/Java/","temp.csv");

	}

	private void downloadFileFromUri(String path) {
		URI fileUri;
		URL fileUrl;
		try {
			fileUri = new URI(importMarketData.getDataSourceURL().toString());
			fileUrl = fileUri.toURL();
			FileUtils.copyURLToFile(fileUrl, new File(path + "temp.csv"));
			System.out.println("file has been copied");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String createFilteredFile(String path, String name) {
		Calendar endDate = importMarketData.getEndDate();
		Calendar startDate = importMarketData.getStartDate();
		String securityCode = importMarketData.getSec();
		String eventSetID= RandomStringUtils.randomAscii(10)+".csv";
		CSVReader reader;
		CSVWriter writer;
		try {
			reader = new CSVReader(new FileReader(path + name));
			writer = new CSVWriter(new FileWriter(path+eventSetID), ',', CSVWriter.NO_QUOTE_CHARACTER,CSVWriter.DEFAULT_SEPARATOR);
			List <String[]> rows= reader.readAll();
			writer.writeNext(rows.get(0));
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss.mmm");
			Calendar temp=Calendar.getInstance();
			for(int i=1;i<rows.size();i++){
				temp.setTime(sdf.parse(rows.get(i)[1] + " " + rows.get(i)[2]));
				if(securityCode.equals(rows.get(i)[0]) && 
				   startDate.before(temp) &&
				   endDate.after(temp)){
					writer.writeNext(rows.get(i));
				}
			}
			writer.close();
			rows.clear();
			File tmp=new File(path+name);
			tmp.delete();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return eventSetID;

	}
}
