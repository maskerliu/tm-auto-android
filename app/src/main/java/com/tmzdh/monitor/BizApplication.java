package com.tmzdh.monitor;

import android.support.multidex.MultiDexApplication;

import com.facebook.stetho.Stetho;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by chris on 9/29/18.
 */
public class BizApplication extends MultiDexApplication {

    private static final String MAPI_DOMAIN_BETA = "http://192.168.16.34:8080";
    private static final String MAPI_DOMAIN_PROD = "http://47.92.2.180:8080";
    public static final String MAPI_DOMAIN =MAPI_DOMAIN_BETA;
//            BuildConfig.DEBUG ? MAPI_DOMAIN_BETA : MAPI_DOMAIN_PROD;

    private static final String TM_DOMAIN_BETA = "http://192.168.16.34:8081";
    private static final String TM_DOMAIN_PROD = "http://47.92.2.180:9091";
    public static final String TM_DOMAIN =TM_DOMAIN_BETA;
//            BuildConfig.DEBUG ? TM_DOMAIN_BETA : TM_DOMAIN_PROD;
    public static final String JsBridget_Path = String
            .format("%s/static/js/common/TMJsBridge.js", TM_DOMAIN);

    private static BizApplication instance;
    protected Gson gson;

    public static BizApplication instance() {
        if (instance == null) {
            throw new IllegalStateException("Application has not been created");
        }
        return instance;
    }

    public BizApplication() {
        instance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.excludeFieldsWithoutExposeAnnotation();
        gson = gsonBuilder.create();

    }


    public Gson gson() {
        return this.gson;
    }

}
