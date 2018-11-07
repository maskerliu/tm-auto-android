package com.tmzdh.jsbridge;


public interface TMJsBridge {

    void send(String data);

    void send(String data, CallBackFunc responseCallback);

}
