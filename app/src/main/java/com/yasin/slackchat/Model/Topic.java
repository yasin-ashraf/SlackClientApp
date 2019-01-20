package com.yasin.slackchat.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by im_yasinashraf started on 20/1/19.
 */
public class Topic {

    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("creator")
    @Expose
    private String creator;
    @SerializedName("last_set")
    @Expose
    private Integer lastSet;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Integer getLastSet() {
        return lastSet;
    }

    public void setLastSet(Integer lastSet) {
        this.lastSet = lastSet;
    }
}
