package com.yasin.slackchat.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by im_yasinashraf started on 20/1/19.
 */
public class Users {

    @SerializedName("ok")
    @Expose
    private Boolean ok;
    @SerializedName("members")
    @Expose
    private List<Member> members = null;
    @SerializedName("cache_ts")
    @Expose
    private Integer cacheTs;

    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public Integer getCacheTs() {
        return cacheTs;
    }

    public void setCacheTs(Integer cacheTs) {
        this.cacheTs = cacheTs;
    }
}
