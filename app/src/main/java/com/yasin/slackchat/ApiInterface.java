package com.yasin.slackchat;

import com.yasin.slackchat.Model.Channels;
import com.yasin.slackchat.Model.History;
import com.yasin.slackchat.Model.RTMConnect;
import com.yasin.slackchat.Model.Users;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by im_yasinashraf started on 15/1/19.
 */
public interface ApiInterface {

    @GET("rtm.connect/")
    Call<RTMConnect> getWebSocketUrl(@Query("token") String token);

    @GET("users.list/")
    Call<Users> getUsers(@Query("token") String token);

    @GET("channels.list/")
    Call<Channels> getChannels(@Query("token") String token);

    @GET("channels.history/")
    Call<History> getChannelHistory(@Query("token") String token,
                                    @Query("channel") String channelId);


}
