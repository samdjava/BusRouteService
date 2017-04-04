package com.goeuro.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Search Response
 * Created by sam on 1/4/17.
 */
@XmlRootElement(name = "SearchResponse")
public class SearchResponse {

    private static final long serialVersionUID = -2941599593814403638L;

    Integer arr_sid;
    Integer dep_sid;
    Boolean direct_bus_route;

    public Integer getArr_sid() {
        return arr_sid;
    }

    public void setArr_sid(Integer arr_sid) {
        this.arr_sid = arr_sid;
    }

    public Integer getDep_sid() {
        return dep_sid;
    }

    public void setDep_sid(Integer dep_sid) {
        this.dep_sid = dep_sid;
    }

    public Boolean getDirect_bus_route() {
        return direct_bus_route;
    }

    public void setDirect_bus_route(Boolean direct_bus_route) {
        this.direct_bus_route = direct_bus_route;
    }

}
