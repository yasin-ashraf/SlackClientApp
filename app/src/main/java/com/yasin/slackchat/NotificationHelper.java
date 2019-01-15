package com.yasin.slackchat;

/**
 * Created by im_yasinashraf started on 4/10/17.
 */

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.os.Build;


/**
 * Helper class to manage notification channels
 */
public class NotificationHelper extends ContextWrapper {

    private NotificationManager manager;
    public static final String ALERT_SERVICE = "PrimaryChannel";

    /**
     * Registers notification channels, which can be used later by individual notifications.
     *
     * @param ctx The application context
     */
    public NotificationHelper(Context ctx) {
        super(ctx);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel chan1 = new NotificationChannel(ALERT_SERVICE, getString(R.string.noti_channel_notif_service), NotificationManager.IMPORTANCE_HIGH);
            chan1.setLightColor(Color.GREEN);
            chan1.setLockscreenVisibility(Notification.VISIBILITY_SECRET);
            chan1.setShowBadge(true);
            getManager().createNotificationChannel(chan1);
        }
    }

    /**
     * Get the notification manager.
     *
     * Utility method as this helper works with it a lot.
     *
     * @return The system service NotificationManager
     */
    private NotificationManager getManager() {
        if (manager == null) {
            manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return manager;
    }
}
