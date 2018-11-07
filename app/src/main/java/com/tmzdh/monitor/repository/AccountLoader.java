package com.tmzdh.monitor.repository;

import com.tmauto.argus.mapi.model.TMAccount;
import com.tmzdh.monitor.common.network.ObjectLoader;
import com.tmzdh.monitor.common.network.RetrofitServiceManager;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by chris on 10/9/18.
 */
public class AccountLoader extends ObjectLoader {

    private AccountApi accountApi;

    public AccountLoader() {
        accountApi = RetrofitServiceManager.getInstance().create(AccountApi.class);
    }


    public Observable<TMAccount> login(String phone, String pwd) {
        return observe(accountApi.login(phone, pwd));
    }

    public Observable<TMAccount> getProfile(String token) {
        return observe(accountApi.getProfile(token));
    }

    public interface AccountApi {

        @FormUrlEncoded
        @POST("/mapi/login")
        Observable<TMAccount> login(@Field("phone") String phone,
                @Field("encryptPwd") String pwd);

        @FormUrlEncoded
        @POST("/mapi/profile")
        Observable<TMAccount> getProfile(@Field("tm_token") String token);

    }

}
