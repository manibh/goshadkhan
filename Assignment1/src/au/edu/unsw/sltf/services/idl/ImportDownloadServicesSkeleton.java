/**
 * ImportDownloadServicesSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */
package au.edu.unsw.sltf.services.idl;

import au.edu.unsw.sltf.services.idl.ext.DownloadFileOpImpl;
import au.edu.unsw.sltf.services.idl.ext.ImportMarketDataOpImpl;

/**
 * ImportDownloadServicesSkeleton java skeleton for the axisService
 */
public class ImportDownloadServicesSkeleton implements
		ImportDownloadServicesSkeletonInterface {

	/**
	 * Auto generated method signature
	 * 
	 * @param importMarketData0
	 * @return importMarketDataResponse1
	 * @throws ImportDownloadFaultException
	 */

	public au.edu.unsw.sltf.services.idl.ImportMarketDataResponse importMarketData(
			au.edu.unsw.sltf.services.idl.ImportMarketData importMarketData0)
			throws ImportDownloadFaultException {
		// TODO : fill this with the necessary business logic
		System.out.println("request resid");
		ImportMarketDataOpImpl importOp = new ImportMarketDataOpImpl(importMarketData0);
 
		return importOp.importMarketData();
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param downloadFile2
	 * @return downloadFileResponse3
	 * @throws ImportDownloadFaultException
	 */

	public au.edu.unsw.sltf.services.idl.DownloadFileResponse downloadFile(
			au.edu.unsw.sltf.services.idl.DownloadFile downloadFile2)
			throws ImportDownloadFaultException {
		// TODO : fill this with the necessary business logic
		DownloadFileOpImpl downloadOp = new DownloadFileOpImpl(downloadFile2);
		return downloadOp.downloadFile();
//		throw new java.lang.UnsupportedOperationException("Please implement "
//				+ this.getClass().getName() + "#downloadFile");
	}

}
