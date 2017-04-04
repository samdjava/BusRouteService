package com.goeuro.job;

import com.goeuro.model.SearchResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Class to that manages the creation of Source Map.
 * Created by Sam on 4/4/17.
 */
public class BusRouteInformationManager {

    static final Logger LOG = Logger.getLogger(BusRouteInformationProcessor.class);

    final String fileName = System.getProperty("feedfile");
    @Autowired
    BusRouteInformationProcessor processor;

    Map<Integer,Set<Integer>> sourceByConnectionMap = new TreeMap<Integer, Set<Integer>>();

    public void load(){
        try {
            sourceByConnectionMap = processor.process(fileName);
            LOG.info("[BusRouteInformationManager] Loading of the file is complete");
        }
        catch (Exception ex) {
            LOG.info("[BusRouteInformationManager] The feed file could not be loaded.");
        }
    }

    public SearchResponse getDirectConnectionAvailable(int arrival,int destination){
        SearchResponse response = new SearchResponse();
        boolean isDirectRouteAvailable = sourceByConnectionMap.get(arrival)!=null ?
                sourceByConnectionMap.get(arrival).contains(destination) : false;
        response.setDirect_bus_route(isDirectRouteAvailable);
        response.setDep_sid(destination);
        response.setArr_sid(arrival);
        return response;
    }

}
