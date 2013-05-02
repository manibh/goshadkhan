/**
 * 
 */
package au.edu.unsw.sltf.services.idl.ext;

import org.apache.axis2.databinding.types.URI;
import org.apache.axis2.databinding.types.URI.MalformedURIException;

import au.edu.unsw.sltf.services.idl.DownloadFile;
import au.edu.unsw.sltf.services.idl.DownloadFileResponse;
import au.edu.unsw.sltf.services.utils.IOUtil;

/**
 * @author Mani
 *
 */
public class DownloadFileOpImpl {
	private static String dlHttpUriPrefix;
	private DownloadFile downloadFile;
	
	/**
	 * @param downloadFile2 
	 * 
	 */
	public DownloadFileOpImpl(DownloadFile downloadFile2) {
		// TODO Auto-generated constructor stub
		this.downloadFile=downloadFile2;
		IOUtil.setupProjectDirectoryStructure();
		this.dlHttpUriPrefix="http://localhost:8080/Assignment1/marketDataDl/";
	}

	public DownloadFileResponse downloadFile() {
		// TODO Auto-generated method stub
		String fileName=downloadFile.getEventSetId();
		URI eventSetIdUrl=null;
		try {
			eventSetIdUrl = new URI(dlHttpUriPrefix+fileName);
		} catch (MalformedURIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DownloadFileResponse dlFileResp = new DownloadFileResponse();
		dlFileResp.setDataURL(eventSetIdUrl);
		return dlFileResp;
	}

}
