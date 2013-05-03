
/**
 * TimeSeriesBuildingServiceSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */
    package au.edu.unsw.sltf.services.bts;


import au.edu.unsw.sltf.services.bts.ext.TimeSeriesBuildingServiceExt;
    /**
     *  TimeSeriesBuildingServiceSkeleton java skeleton for the axisService
     */
    public class TimeSeriesBuildingServiceSkeleton implements TimeSeriesBuildingServiceSkeletonInterface{
        
         
        /**
         * Auto generated method signature
         * 
                                     * @param buildTimeSeries0 
             * @return buildTimeSeriesResponse1 
             * @throws BuildTimeSeriesFaultException 
         */
        
        public au.edu.unsw.sltf.services.bts.BuildTimeSeriesResponse buildTimeSeries(
                  au.edu.unsw.sltf.services.bts.BuildTimeSeries buildTimeSeriesRequest) throws BuildTimeSeriesFaultException{
        	        	
        	return (new TimeSeriesBuildingServiceExt()).buildTimeSeries(buildTimeSeriesRequest);
        }
     
    }
    