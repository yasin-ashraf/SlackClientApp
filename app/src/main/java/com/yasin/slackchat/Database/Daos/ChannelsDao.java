package com.yasin.slackchat.Database.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import com.yasin.slackchat.Model.Channel;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by im_yasinashraf started on 21/1/19.
 */
@Dao
public interface ChannelsDao {

    @Insert(onConflict = REPLACE)
    void saveChannels(Channel channel);
}
