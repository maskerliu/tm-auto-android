package com.tmzdh.monitor.jshandler;

import com.tmzdh.jsbridge.CallBackFunc;
import com.tmzdh.jsbridge.JsBridgeHandler;
import com.tmzdh.monitor.common.BaseActivity;
import com.tmzdh.monitor.model.Location;

/**
 * Created by chris on 9/28/18.
 */
public class LocationJsHandler implements JsBridgeHandler {

    private BaseActivity context;
    private Location location;

    public LocationJsHandler(BaseActivity context) {
        this.context = context;
        location = new Location();
        location.city = "上海";
        location.address = "上海市长宁区安化路492号";
        location.lat = 31.413417;
        location.lng = 121.483248;
    }

    @Override
    public String name() {
        return "getLocation";
    }

    @Override
    public void handler(String data, CallBackFunc func) {
        func.onCallBack(context.gson().toJson(location));
    }
}
