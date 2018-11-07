package com.tmzdh.monitor.jshandler;

import android.content.Intent;

import com.tmzdh.jsbridge.CallBackFunc;
import com.tmzdh.jsbridge.JsBridgeHandler;
import com.tmzdh.monitor.MainActivity;
import com.tmzdh.monitor.common.BaseActivity;

/**
 * Created by chris on 9/28/18.
 */
public class AlbumJsHandler implements JsBridgeHandler {

    private BaseActivity context;
    public CallBackFunc cbFunc;

    public AlbumJsHandler(BaseActivity context) {
        this.context = context;
    }

    @Override
    public String name() {
        return "openAlbum";
    }

    @Override
    public void handler(String data, CallBackFunc func) {
        Intent chooserIntent = new Intent(Intent.ACTION_GET_CONTENT);
        chooserIntent.setType("image/*");
        context.startActivityForResult(chooserIntent, MainActivity.RESULT_CODE);

        cbFunc = func;
    }
}
