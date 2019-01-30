package com.tmzdh.jsbridge;

public class DefaultJsBridgeHandler implements JsBridgeHandler {

    @Override
    public String name() {
        return "DefaultJsBridgeHandler";
    }

    @Override
    public void handler(String data, CallBackFunc function) {
        if (function != null) {
            function.onCallBack("DefaultJsBridgeHandler response data");
        }
    }

}
