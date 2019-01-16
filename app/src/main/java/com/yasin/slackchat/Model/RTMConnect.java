package com.yasin.slackchat.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by im_yasinashraf started on 16/1/19.
 */
public class RTMConnect {

    @SerializedName("ok")
    @Expose
    private Boolean ok;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("team")
    @Expose
    private Team team;

    @SerializedName("self")
    @Expose
    private Self self;

    public Boolean getOk() {
        return ok;
    }

    public String getUrl() {
        return url;
    }

    public Team getTeam() {
        return team;
    }

    public Self getSelf() {
        return self;
    }
}
