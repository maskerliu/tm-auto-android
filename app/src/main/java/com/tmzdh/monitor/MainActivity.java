package com.tmzdh.monitor;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.webkit.WebChromeClient;

import com.tmzdh.jsbridge.DefaultHandler;
import com.tmzdh.jsbridge.HybridWebView;
import com.tmzdh.monitor.common.BaseActivity;
import com.tmzdh.monitor.jshandler.AccountJsHandler;
import com.tmzdh.monitor.jshandler.AlbumJsHandler;
import com.tmzdh.monitor.jshandler.LocationJsHandler;
import com.tmzdh.monitor.jshandler.LoginJsHandler;
import com.tmzdh.monitor.model.AccountToken;

public class MainActivity extends BaseActivity implements OnClickListener {

    private static final String[] LOCATION_AND_STORAGE =
            {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private static final int RC_SMS_PERM = 122;
    private static final int RC_CAMERA_PERM = 123;
    private static final int RC_LOCATION_STORAGE_PERM = 124;

    public static int RESULT_CODE = 0;

    protected FloatingActionButton btnSwitch;
    protected HybridWebView wvContainer;
    protected AlbumJsHandler albumJsHandler;
    protected boolean isAdmin = false;
    protected String url = BizApplication.TM_DOMAIN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSwitch = findViewById(R.id.btn_switch);
        btnSwitch.setOnClickListener(this);

        wvContainer = findViewById(R.id.wv_container);

        HybridWebView.setWebContentsDebuggingEnabled(true);

        wvContainer.setJsBridgePath(BizApplication.JsBridget_Path);
        wvContainer.setBackgroundColor(0); // 设置背景色
        wvContainer.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        wvContainer.loadUrl(url);

        wvContainer.setOnClickListener(this);
        wvContainer.setDefaultHandler(new DefaultHandler());

        wvContainer.setWebChromeClient(new WebChromeClient() {

        });


        wvContainer.registerHandler(new AccountJsHandler(this));
        wvContainer.registerHandler(new LoginJsHandler(this));
        wvContainer.registerHandler(new LocationJsHandler(this));
        wvContainer.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return true;
            }
        });
        albumJsHandler = new AlbumJsHandler(this);
        wvContainer.registerHandler(albumJsHandler);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == RESULT_CODE) {
            if (albumJsHandler.cbFunc == null) {
                return;
            }
            Uri result = intent == null || resultCode != RESULT_OK ? null : intent.getData();
            albumJsHandler.cbFunc.onCallBack(result.toString());
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_switch:
                isAdmin = !isAdmin;
                url = isAdmin ? BizApplication.TM_DOMAIN + "/mgr/#clients" :
                        BizApplication.TM_DOMAIN;
                wvContainer.loadUrl(url);
                break;
        }
//        if (btnSwitch.equals(v)) {
//            wvContainer.callHandler("functionInJs", "data from Java", new CallBackFunc() {
//                @Override
//                public void onCallBack(String data) {
//                    Log.i("chris", "reponse data from js: " + data);
//                }
//            });
//
//        }
    }


    public void gotoLogin() {
        narrowBackgroundAnimation();
        LoginFragment.newInstance().show(getSupportFragmentManager(), "dialog-login");
    }

    public void injectToken(String token) {
        AccountToken at = new AccountToken(token);
        wvContainer.callHandler("functionInJs",
                gson().toJson(at),
                (data) -> {
                    Log.i("chris", "reponse data from js: " + data);
                }
        );
    }

}
