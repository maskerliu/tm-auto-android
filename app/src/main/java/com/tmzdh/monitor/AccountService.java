package com.tmzdh.monitor;

import android.content.Context;
import android.text.TextUtils;

import com.tmauto.argus.mapi.model.TMAccount;
import com.tmzdh.monitor.repository.AccountLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class AccountService {

    private static final String ACCOUNT_FILE = "account.tm";

    private static AccountService instance;
    private AccountLoader accountLoader;
    private BizApplication context;
    private String token;

    public static AccountService instance() {
        synchronized (AccountService.class) {
            if (instance == null) {
                instance = new AccountService();
            }
        }

        return instance;
    }

    private AccountService() {
        context = BizApplication.instance();

        accountLoader = new AccountLoader();
    }


    public String getToken() {

        if (!TextUtils.isEmpty(token)) {
            return token;
        }
        try {
            File file = new File(context.getFilesDir(), ACCOUNT_FILE);
            if (!file.exists()) {
                file.createNewFile();
            }

            FileInputStream fis = context.openFileInput(ACCOUNT_FILE);
            InputStreamReader isr = new InputStreamReader(fis, "utf-8");
            char input[] = new char[fis.available()];
            isr.read(input);
            isr.close();
            fis.close();
            token = new String(input);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return token;
    }

    private void saveAccount(TMAccount account) {
        try {
            this.token = account.token;
            File file = new File(context.getFilesDir(), ACCOUNT_FILE);
            if (!file.exists()) {
                file.createNewFile();
            }

            FileOutputStream fos = context.openFileOutput(ACCOUNT_FILE, Context.MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");
            osw.write(token);
            osw.flush();
            fos.flush();
            osw.close();
            fos.close();
        } catch (Exception e) {
            this.token = null;
            e.printStackTrace();
        }
    }

    public Observable<TMAccount> login(String phone, String pwd) {

        Observable<TMAccount> observable = accountLoader.login(phone, pwd);
        observable.subscribe(new Observer<TMAccount>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(TMAccount account) {
                if (account.status.code == 200) {
                    saveAccount(account);
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        return observable;
    }

    public void logout() {
        this.token = null;
        try {
            File file = new File(context.getFilesDir(), ACCOUNT_FILE);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fos = context.openFileOutput(ACCOUNT_FILE, Context.MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");
            osw.write("");
            osw.flush();
            fos.flush();
            osw.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
