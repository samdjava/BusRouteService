package com.goeuro.services;

import com.goeuro.model.SearchResponse;

/**
 * List of api's
 * Created by Sam on 4/4/17.
 */

public interface SearchServices {

    String ping();


    SearchResponse direct(Integer departureStop,Integer arrivalStop);
}
