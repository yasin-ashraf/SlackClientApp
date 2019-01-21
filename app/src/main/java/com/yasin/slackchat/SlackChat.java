package com.yasin.slackchat;

import android.app.Application;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by im_yasinashraf started on 15/1/19.
 */
public class SlackChat extends Application {

    private static SlackChat sInstance;
    private Executor executor;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        executor = Executors.newCachedThreadPool();
        new NotificationHelper(this);
    }

    public static SlackChat getApp() {
        return sInstance;
    }

    public Executor getExecutor() {
        return executor;
    }
}
