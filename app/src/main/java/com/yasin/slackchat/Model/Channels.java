package com.yasin.slackchat.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by im_yasinashraf started on 20/1/19.
 */
public class Channels {

    @SerializedName("ok")
    @Expose
    private Boolean ok;
    @SerializedName("channels")
    @Expose
    private List<Channel> channels = null;

    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }
}
