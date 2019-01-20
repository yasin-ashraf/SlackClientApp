package com.yasin.slackchat.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by im_yasinashraf started on 20/1/19.
 */
public class Message {

    @SerializedName("client_msg_id")
    @Expose
    private String clientMsgId;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("text")
    @Expose
    private String text;

    @SerializedName("user")
    @Expose
    private String user;

    @SerializedName("ts")
    @Expose
    private String ts;

    @SerializedName("subtype")
    @Expose
    private String subtype;

    @SerializedName("bot_id")
    @Expose
    private String botId;

    @SerializedName("inviter")
    @Expose
    private String inviter;

    @SerializedName("bot_link")
    @Expose
    private String botLink;

    public String getClientMsgId() {
        return clientMsgId;
    }

    public void setClientMsgId(String clientMsgId) {
        this.clientMsgId = clientMsgId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getBotId() {
        return botId;
    }

    public void setBotId(String botId) {
        this.botId = botId;
    }

    public String getInviter() {
        return inviter;
    }

    public void setInviter(String inviter) {
        this.inviter = inviter;
    }

    public String getBotLink() {
        return botLink;
    }

    public void setBotLink(String botLink) {
        this.botLink = botLink;
    }
}
