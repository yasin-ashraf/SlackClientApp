package com.yasin.slackchat.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by im_yasinashraf started on 20/1/19.
 */
public class History {

    @SerializedName("ok")
    @Expose
    private Boolean ok;
    @SerializedName("messages")
    @Expose
    private List<Message> messages = null;
    @SerializedName("has_more")
    @Expose
    private Boolean hasMore;

    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }
}
