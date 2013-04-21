
/**
 * BuildTimeSeriesFaultException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package au.edu.unsw.sltf.services.bts;

public class BuildTimeSeriesFaultException extends java.lang.Exception{

    private static final long serialVersionUID = 1366526487732L;
    
    private au.edu.unsw.sltf.services.bts.BuildTimeSeriesFault faultMessage;

    
        public BuildTimeSeriesFaultException() {
            super("BuildTimeSeriesFaultException");
        }

        public BuildTimeSeriesFaultException(java.lang.String s) {
           super(s);
        }

        public BuildTimeSeriesFaultException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public BuildTimeSeriesFaultException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(au.edu.unsw.sltf.services.bts.BuildTimeSeriesFault msg){
       faultMessage = msg;
    }
    
    public au.edu.unsw.sltf.services.bts.BuildTimeSeriesFault getFaultMessage(){
       return faultMessage;
    }
}
    