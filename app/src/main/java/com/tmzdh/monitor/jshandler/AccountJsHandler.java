package com.tmzdh.monitor.jshandler;

import android.text.TextUtils;

import com.tmzdh.jsbridge.CallBackFunc;
import com.tmzdh.jsbridge.JsBridgeHandler;
import com.tmzdh.monitor.AccountService;
import com.tmzdh.monitor.MainActivity;
import com.tmzdh.monitor.model.AccountToken;

/**
 * Created by chris on 9/28/18.
 */
public class AccountJsHandler implements JsBridgeHandler {

    private MainActivity context;
    private AccountService accountService;

    public AccountJsHandler(MainActivity context) {
        this.context = context;

        accountService = AccountService.instance();
    }

    @Override
    public String name() {
        return "getAccount";
    }

    @Override
    public void handler(String data, CallBackFunc func) {
        String token = accountService.getToken();
        if (TextUtils.isEmpty(token)) {
            context.gotoLogin();
            func.onCallBack(null);
        } else {
            AccountToken at = new AccountToken(token);
            func.onCallBack(context.gson().toJson(at));
        }
    }
}
