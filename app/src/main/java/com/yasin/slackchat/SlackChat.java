package com.yasin.slackchat;

import android.app.Application;

/**
 * Created by im_yasinashraf started on 15/1/19.
 */
public class SlackChat extends Application {

    private static SlackChat sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        new NotificationHelper(this);
    }

    public static SlackChat getApp() {
        return sInstance;
    }
}
