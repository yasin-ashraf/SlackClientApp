package com.yasin.slackchat;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by im_yasinashraf started on 11/10/17.
 */

public class SessionManager {

    private static final String PREF_NAME = "SlackChatPref";
    private static final String IS_FIRST_USE = "IsFirstUse";
    private static final String IS_FIRST_DATA_LOADED = "IsFirstDataLoaded";
    private static final String WEBSOCKET_URL = "websocketUrl"; // change this
    private static final String SLACK_TOKEN = "apiToken"; // change this

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    public SessionManager(Context context) {
        int PRIVATE_MODE = 0;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setIsFirstUseFalse(){
        editor.putBoolean(IS_FIRST_USE,false);
        editor.commit();
    }

    public boolean getIsFirstUse(){
        return pref.getBoolean(IS_FIRST_USE,true);
    }


    public void setFirstDataLoaded(boolean s){
        editor.putBoolean(IS_FIRST_DATA_LOADED,s);
        editor.commit();
    }

    public boolean getIsFirstDataLoaded(){
        return pref.getBoolean(IS_FIRST_DATA_LOADED,false);
    }

    public void setWebsocketUrl(String s){
        editor.putString(WEBSOCKET_URL,s);
        editor.commit();
    }

    public String getWebsocketUrl(){
        return pref.getString(WEBSOCKET_URL,"");
    }

    public void setApiToken(String s){
        editor.putString(SLACK_TOKEN,s);
        editor.commit();
    }

    public String getApiToken(){
        return pref.getString(SLACK_TOKEN,"");
    }

}

