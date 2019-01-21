package com.yasin.slackchat.Database;

import android.arch.persistence.room.Room;
import android.content.Context;

/**
 * Created by im_yasinashraf started on 5/11/18.
 */

public class DatabaseClient {

    private static DatabaseClient mInstance;

    private SlackChatDatabase slackChatDatabase;

    private DatabaseClient(Context context) {
        //creating the app database with Room database builder
        slackChatDatabase = Room.databaseBuilder(context,
                SlackChatDatabase.class, "SlackChatDatabase.db")
                .build();
    }

    public static synchronized DatabaseClient getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DatabaseClient(context);
        }
        return mInstance;
    }

    public SlackChatDatabase getAppDatabase() {
        return slackChatDatabase;
    }
}
