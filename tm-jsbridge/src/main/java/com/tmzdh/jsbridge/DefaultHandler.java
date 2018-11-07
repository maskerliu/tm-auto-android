package com.tmzdh.jsbridge;

public class DefaultHandler implements JsBridgeHandler {

    @Override
    public String name() {
        return "DefaultHandler";
    }

    @Override
    public void handler(String data, CallBackFunc function) {
        if (function != null) {
            function.onCallBack("DefaultHandler response data");
        }
    }

}
