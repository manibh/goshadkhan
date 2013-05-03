/**
 * 
 */
package au.edu.unsw.sltf.services.idl.ext;

import java.io.File;
import java.net.InetAddress;
import org.apache.axis2.databinding.types.URI;
import au.edu.unsw.sltf.services.idl.DownloadFile;
import au.edu.unsw.sltf.services.idl.DownloadFileResponse;
import au.edu.unsw.sltf.services.idl.ImportDownloadFault;
import au.edu.unsw.sltf.services.idl.ImportDownloadFaultException;
import au.edu.unsw.sltf.services.idl.ImportDownloadFaultType;
import au.edu.unsw.sltf.services.utils.IOUtil;

/**
 * @author Mani
 *
 */
public class DownloadFileOpImpl {
	private static String hostPort="8080";
	private DownloadFile downloadFile;
	
	/**
	 * @param downloadFile2 
	 * 
	 */
	public DownloadFileOpImpl(DownloadFile downloadFile2) {
		this.downloadFile=downloadFile2;
		IOUtil.setupProjectDirectoryStructure();
	}

	public DownloadFileResponse downloadFile() throws ImportDownloadFaultException{
		String fileName=downloadFile.getEventSetId()+".csv";
//		if (fileName.indexOf(ch))
//		StringUtils.
//		Nima khobi ? TODO he he
		URI eventSetIdUrl=null;
		String hostName;
		try {
			hostName=InetAddress.getLocalHost().getHostName();
			File temp= new File(IOUtil.getmarketDataFolder()+"/"+fileName );
			if (!fileName.matches("\\A\\p{ASCII}*\\z")) throw new Exception("CONTAINS_CONTROL_CHAR");
			if (!temp.exists()) throw new Exception("");
			eventSetIdUrl = new URI("http://"+hostName+":"+hostPort+"/Assignment1/market_data/"+fileName);
		} catch (Exception e){
			e.printStackTrace();
			ImportDownloadFaultException exp = new ImportDownloadFaultException();
			ImportDownloadFault loadFaultMsg= new ImportDownloadFault();
			if(e.getMessage().equals("CONTAINS_CONTROL_CHAR")){
				loadFaultMsg.setFaultMessage("Invalid character, EventSetId can not contain control characters");
				loadFaultMsg.setFaultType(ImportDownloadFaultType.InvalidEventSetId);				
			}else{
				loadFaultMsg.setFaultMessage("Fiel can not be found");
				loadFaultMsg.setFaultType(ImportDownloadFaultType.ProgramError);
			}
			exp.setFaultMessage(loadFaultMsg);
			throw exp;
		}
			 
		DownloadFileResponse dlFileResp = new DownloadFileResponse();
		dlFileResp.setDataURL(eventSetIdUrl);
		return dlFileResp;
	}

}
