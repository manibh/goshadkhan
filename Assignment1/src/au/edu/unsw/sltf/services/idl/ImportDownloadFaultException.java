
/**
 * ImportDownloadFaultException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package au.edu.unsw.sltf.services.idl;

public class ImportDownloadFaultException extends java.lang.Exception{

    private static final long serialVersionUID = 1366526707841L;
    
    private au.edu.unsw.sltf.services.idl.ImportDownloadFault faultMessage;

    
        public ImportDownloadFaultException() {
            super("ImportDownloadFaultException");
        }

        public ImportDownloadFaultException(java.lang.String s) {
           super(s);
        }

        public ImportDownloadFaultException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public ImportDownloadFaultException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(au.edu.unsw.sltf.services.idl.ImportDownloadFault msg){
       faultMessage = msg;
    }
    
    public au.edu.unsw.sltf.services.idl.ImportDownloadFault getFaultMessage(){
       return faultMessage;
    }
}
    