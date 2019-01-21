package com.yasin.slackchat.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.yasin.slackchat.Database.Daos.ChannelsDao;
import com.yasin.slackchat.Database.Daos.UserDao;
import com.yasin.slackchat.Model.Channel;
import com.yasin.slackchat.Model.Member;

/**
 * Created by im_yasinashraf started on 21/1/19.
 */
@Database(entities = {Member.class, Channel.class}, version = 1)
@TypeConverters({Converter.class})
public abstract class SlackChatDatabase extends RoomDatabase {

    public abstract UserDao userDao();

    public abstract ChannelsDao channelsDao();
}
