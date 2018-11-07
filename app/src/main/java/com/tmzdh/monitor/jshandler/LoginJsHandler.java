package com.tmzdh.monitor.jshandler;

import com.tmzdh.jsbridge.CallBackFunc;
import com.tmzdh.jsbridge.JsBridgeHandler;
import com.tmzdh.monitor.AccountService;
import com.tmzdh.monitor.MainActivity;

/**
 * Created by chris on 10/10/18.
 */
public class LoginJsHandler implements JsBridgeHandler {

    private MainActivity context;
    private AccountService accountService;

    public LoginJsHandler(MainActivity context) {
        this.context = context;

        accountService = AccountService.instance();
    }

    @Override
    public String name() {
        return "login";
    }

    @Override
    public void handler(String data, CallBackFunc func) {
        accountService.logout();
        context.gotoLogin();
    }
}
