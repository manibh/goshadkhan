/**
 * 
 */
package au.edu.unsw.sltf.services.idl.ext;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Calendar;

//import org.apache.axis2.databinding.types.URI;
import org.apache.commons.io.FileUtils;

import au.edu.unsw.sltf.services.idl.ImportMarketData;

/**
 * @author Mani
 *
 */
public class ImportMarketDataOpImpl {

	/**
	 * @param importMarketData0 
	 * 
	 */
	public ImportMarketDataOpImpl(ImportMarketData importMarketData) {
		// TODO Auto-generated constructor stub
		
		Calendar endDate=importMarketData.getEndDate();
		Calendar startDate=importMarketData.getStartDate();
		String securityCode=importMarketData.getSec();
		URI fileUri;
		URL fileUrl;
		try {
			fileUri= new URI(importMarketData.getDataSourceURL().toString());
			fileUrl = fileUri.toURL();
			FileUtils.copyURLToFile(fileUrl, new File("/Users/Mani/Java/"+fileUrl.getFile()));
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

}
