package com.yasin.slackchat.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by im_yasinashraf started on 20/1/19.
 */
public class Member {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("team_id")
    @Expose
    private String teamId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("deleted")
    @Expose
    private Boolean deleted;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("real_name")
    @Expose
    private String realName;
    @SerializedName("tz")
    @Expose
    private Object tz;
    @SerializedName("tz_label")
    @Expose
    private String tzLabel;
    @SerializedName("tz_offset")
    @Expose
    private Integer tzOffset;
    @SerializedName("profile")
    @Expose
    private Profile profile;
    @SerializedName("is_admin")
    @Expose
    private Boolean isAdmin;
    @SerializedName("is_owner")
    @Expose
    private Boolean isOwner;
    @SerializedName("is_primary_owner")
    @Expose
    private Boolean isPrimaryOwner;
    @SerializedName("is_restricted")
    @Expose
    private Boolean isRestricted;
    @SerializedName("is_ultra_restricted")
    @Expose
    private Boolean isUltraRestricted;
    @SerializedName("is_bot")
    @Expose
    private Boolean isBot;
    @SerializedName("is_app_user")
    @Expose
    private Boolean isAppUser;
    @SerializedName("updated")
    @Expose
    private Integer updated;
    @SerializedName("has_2fa")
    @Expose
    private Boolean has2fa;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Object getTz() {
        return tz;
    }

    public void setTz(Object tz) {
        this.tz = tz;
    }

    public String getTzLabel() {
        return tzLabel;
    }

    public void setTzLabel(String tzLabel) {
        this.tzLabel = tzLabel;
    }

    public Integer getTzOffset() {
        return tzOffset;
    }

    public void setTzOffset(Integer tzOffset) {
        this.tzOffset = tzOffset;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public Boolean getOwner() {
        return isOwner;
    }

    public void setOwner(Boolean owner) {
        isOwner = owner;
    }

    public Boolean getPrimaryOwner() {
        return isPrimaryOwner;
    }

    public void setPrimaryOwner(Boolean primaryOwner) {
        isPrimaryOwner = primaryOwner;
    }

    public Boolean getRestricted() {
        return isRestricted;
    }

    public void setRestricted(Boolean restricted) {
        isRestricted = restricted;
    }

    public Boolean getUltraRestricted() {
        return isUltraRestricted;
    }

    public void setUltraRestricted(Boolean ultraRestricted) {
        isUltraRestricted = ultraRestricted;
    }

    public Boolean getBot() {
        return isBot;
    }

    public void setBot(Boolean bot) {
        isBot = bot;
    }

    public Boolean getAppUser() {
        return isAppUser;
    }

    public void setAppUser(Boolean appUser) {
        isAppUser = appUser;
    }

    public Integer getUpdated() {
        return updated;
    }

    public void setUpdated(Integer updated) {
        this.updated = updated;
    }

    public Boolean getHas2fa() {
        return has2fa;
    }

    public void setHas2fa(Boolean has2fa) {
        this.has2fa = has2fa;
    }
}
