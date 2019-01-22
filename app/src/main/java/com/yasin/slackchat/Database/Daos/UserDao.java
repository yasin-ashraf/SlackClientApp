package com.yasin.slackchat.Database.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.yasin.slackchat.Model.Member;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by im_yasinashraf started on 21/1/19.
 */
@Dao
public interface UserDao {

    @Insert(onConflict = REPLACE)
    void saveUser(Member member);

    @Query("SELECT * FROM Member WHERE id = :id")
    Member getUser(String id);

    @Query("SELECT * FROM Member")
    List<Member> getUsers();
}
