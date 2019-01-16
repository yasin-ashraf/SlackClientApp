package com.yasin.slackchat;

import com.yasin.slackchat.Model.RTMConnect;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by im_yasinashraf started on 15/1/19.
 */
public interface ApiInterface {

    @GET("rtm.connect/")
    Call<RTMConnect> getOTP (@Query("token") String token);
}
