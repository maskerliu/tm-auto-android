package com.tmzdh.monitor.model;

import com.google.gson.annotations.Expose;

/**
 * Created by chris on 9/28/18.
 */
public class AccountToken {

    @Expose
    public String token;

    public AccountToken(String token) {
        this.token = token;
    }
}
