package com.yasin.slackchat.Database;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yasin.slackchat.Model.Profile;
import com.yasin.slackchat.Model.Purpose;
import com.yasin.slackchat.Model.Topic;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by im_yasinashraf started on 21/1/19.
 */
public class Converter {

    @TypeConverter
    public String fromProfile(Profile profile) {
        if (profile == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Profile>() {
        }.getType();
        return gson.toJson(profile, type);
    }

    @TypeConverter
    public Profile toProfile(String profileString) {
        if (profileString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Profile>() {
        }.getType();
        return gson.fromJson(profileString, type);
    }

    @TypeConverter
    public String fromTopic(Topic topic) {
        if (topic == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Topic>() {
        }.getType();
        return gson.toJson(topic, type);
    }

    @TypeConverter
    public Topic toTopic(String topicString) {
        if (topicString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Topic>() {
        }.getType();
        return gson.fromJson(topicString, type);
    }

    @TypeConverter
    public String fromPurpose(Purpose purpose) {
        if (purpose == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Purpose>() {
        }.getType();
        return gson.toJson(purpose, type);
    }

    @TypeConverter
    public Purpose toPurpose(String purposeString) {
        if (purposeString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Purpose>() {
        }.getType();
        return gson.fromJson(purposeString, type);
    }

    @TypeConverter
    public String fromStrings(List<String> strings) {
        if (strings == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {
        }.getType();
        return gson.toJson(strings, type);
    }

    @TypeConverter
    public List<String> toStrings(String strings) {
        if (strings == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {
        }.getType();
        return gson.fromJson(strings, type);
    }
}
