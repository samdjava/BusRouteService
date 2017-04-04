package com.goeuro.job;

import com.goeuro.constants.Constants;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Responsible for creating the connectivity map by parsing a file
 * Created by Sam on 4/4/17.
 */
public class BusRouteInformationProcessor {

    static final Logger LOG = Logger.getLogger(BusRouteInformationProcessor.class);

    int routeCount;

    public BusRouteInformationProcessor() {
    }

    Map<Integer,Set<Integer>> sourceByConnectionMap = new TreeMap<Integer, Set<Integer>>();

    public Map<Integer,Set<Integer>>  process(String fileName) throws FileNotFoundException {
        Scanner input = new Scanner(new File(fileName));
        routeCount= Integer.parseInt(input.next());
        for(int index=0;index<routeCount;index++){
            processBusRouteInfo(input.nextLine());
        }
        return sourceByConnectionMap;
    }

    private void processBusRouteInfo(String route){
        String tokens[] = StringUtils.trim(route).split(Constants.DELIMETER);
        String[] busStopArray = ArrayUtils.subarray(tokens, 1, tokens.length);
        Set<Integer> busStopSet = new HashSet<Integer>();
        for(String bustStopIndex : Arrays.asList(busStopArray)){
            if(StringUtils.isBlank(bustStopIndex))
                continue;
            try{
                busStopSet.add(Integer.parseInt(bustStopIndex));
            }catch (NumberFormatException ex){
                LOG.info("[BusRouteInformationProcessor] Exception reading bus stop "
                        + bustStopIndex+" bus route " + route);
                throw ex;
            }
        }
        for(Integer busStop: busStopSet) {
            if(!sourceByConnectionMap.containsKey(busStop)){
                sourceByConnectionMap.put(busStop,new HashSet<Integer>());
            }
            sourceByConnectionMap.get(busStop).addAll(busStopSet);
        }
    }

}
