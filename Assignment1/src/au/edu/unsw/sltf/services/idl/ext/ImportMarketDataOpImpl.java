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
import au.edu.unsw.sltf.services.idl.ImportDownloadFault;
import au.edu.unsw.sltf.services.idl.ImportDownloadFaultException;
import au.edu.unsw.sltf.services.idl.ImportDownloadFaultType;
import au.edu.unsw.sltf.services.idl.ImportMarketData;
import au.edu.unsw.sltf.services.idl.ImportMarketDataResponse;
import au.edu.unsw.sltf.services.utils.IOUtil;

/**
 * @author Mani
 * 
 */
public class ImportMarketDataOpImpl {

	private static String path;
	private ImportMarketData importMarketData;

	/**
	 * @param importMarketData0
	 * 
	 */
	public ImportMarketDataOpImpl(ImportMarketData importMarketData) {
		this.importMarketData = importMarketData;
		IOUtil.setupProjectDirectoryStructure();
		this.path = IOUtil.getmarketDataFolder().getPath() + "/";
	}

	/**
	 * public method to be accessed from skeleton class and obtain eventSetId
	 * for response
	 * 
	 * @param path
	 * @return Event Set Id
	 * @throws ImportDownloadFaultException
	 */
	public ImportMarketDataResponse importMarketData()
			throws ImportDownloadFaultException {
		uploadFileFromUri(path);
		String eventSetId = createFilteredFile(path, "temp");
		ImportMarketDataResponse marketDataResp = new ImportMarketDataResponse();
		marketDataResp.setEventSetId(eventSetId);
		return marketDataResp;
	}

	private void uploadFileFromUri(String path)
			throws ImportDownloadFaultException {
		URI fileUri;
		URL fileUrl;
		try {
			fileUri = new URI(importMarketData.getDataSourceURL().toString());
			fileUrl = fileUri.toURL();
			FileUtils.copyURLToFile(fileUrl, new File(path + "temp"));
			System.out.println("file has been copied");
		} catch (Exception e) {
			e.printStackTrace();
			ImportDownloadFaultException exp = new ImportDownloadFaultException();
			ImportDownloadFault loadFaultMsg = new ImportDownloadFault();
			ImportDownloadFaultType idFaultType = ImportDownloadFaultType.InvalidURL;
			loadFaultMsg.setFaultMessage("You have entered invalid URL");
			loadFaultMsg.setFaultType(idFaultType);
			exp.setFaultMessage(loadFaultMsg);
			throw exp;

		}
	}

	private String createFilteredFile(String path, String name)
			throws ImportDownloadFaultException {
		Calendar endDate = importMarketData.getEndDate();
		Calendar startDate = importMarketData.getStartDate();
		String securityCode = importMarketData.getSec();
		String eventSetID = RandomStringUtils.randomAlphanumeric(15);
		CSVReader reader = null;
		CSVWriter writer = null;
		;
		List<String[]> rows = null;
		try {
			reader = new CSVReader(new FileReader(path + name));
			writer = new CSVWriter(new FileWriter(path + eventSetID + ".csv"),
					',', CSVWriter.NO_QUOTE_CHARACTER,
					CSVWriter.DEFAULT_SEPARATOR);
			rows = reader.readAll();
			writer.writeNext(rows.get(0));
			SimpleDateFormat sdf = new SimpleDateFormat(
					"dd-MMM-yyyy HH:mm:ss.mmm");
			Calendar temp = Calendar.getInstance();
			// TODO don't know what to do with following fault type
			// ImportDownloadFaultType._InvalidSECCode
			boolean isSecCodeValid = false;
			for (int i = 1; i < rows.size(); i++) {
				temp.setTime(sdf.parse(rows.get(i)[1] + " " + rows.get(i)[2]));
				if (securityCode.equals(rows.get(i)[0])
						&& startDate.before(temp) && endDate.after(temp)) {
					writer.writeNext(rows.get(i));
					isSecCodeValid = true;
				}
				if (!isSecCodeValid)
					throw new Exception("INVALID_SEC");
			}
		} catch (Exception e) {
			ImportDownloadFaultException exp = new ImportDownloadFaultException();
			ImportDownloadFault loadFaultMsg = new ImportDownloadFault();
			if (e.getMessage().equals("INVALID_SEC")) {
				loadFaultMsg
						.setFaultMessage("Given Security code does not exist");
				loadFaultMsg.setFaultType(ImportDownloadFaultType.InvalidSECCode);
			} else {
				loadFaultMsg
						.setFaultMessage("Parsing error due to malformed file structure");
				loadFaultMsg.setFaultType(ImportDownloadFaultType.ProgramError);
			}
			exp.setFaultMessage(loadFaultMsg);
			throw exp;
		}finally {
			try {
				reader.close();
				writer.flush();
				writer.close();
				rows.clear();
				File tmp = new File(path + name);
				tmp.delete();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		return eventSetID;

	}
}
