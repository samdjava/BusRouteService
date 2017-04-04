package com.goeuro.services;

import com.goeuro.job.BusRouteInformationManager;
import com.goeuro.model.SearchResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * API available for the micro service
 * Created by Sam on 4/4/17.
 */
@Component
@Path("/api")
public class SearchServicesImpl implements SearchServices{

    static final Logger LOG = Logger.getLogger(SearchServicesImpl.class);

    @Autowired
    BusRouteInformationManager busRouteInformationManager;

    @GET
    @Path("/ping")
    public String ping(){
        return "SUCCESS";
    }

    @GET
    @Path("/direct")
    @Produces(MediaType.APPLICATION_JSON)
    public SearchResponse direct(@QueryParam("dep_sid") Integer departureStop, @QueryParam("arr_sid") Integer arrivalStop){
        return busRouteInformationManager.getDirectConnectionAvailable(arrivalStop,departureStop);
    }

}
