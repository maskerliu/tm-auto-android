package com.tmzdh.monitor.model;

import com.google.gson.annotations.Expose;

/**
 * Created by chris on 9/28/18.
 */
public class Location {

    @Expose
    public String city;
    @Expose
    public String address;
    @Expose
    public double lat;
    @Expose
    public double lng;

}
