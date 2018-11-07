package com.tmzdh.jsbridge;

public interface JsBridgeHandler {

    String name();

    void handler(String data, CallBackFunc func);

}
