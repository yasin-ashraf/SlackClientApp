package com.yasin.slackchat.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by im_yasinashraf started on 20/1/19.
 */
public class Profile {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("skype")
    @Expose
    private String skype;
    @SerializedName("real_name")
    @Expose
    private String realName;
    @SerializedName("real_name_normalized")
    @Expose
    private String realNameNormalized;
    @SerializedName("display_name")
    @Expose
    private String displayName;
    @SerializedName("display_name_normalized")
    @Expose
    private String displayNameNormalized;
    @SerializedName("status_text")
    @Expose
    private String statusText;
    @SerializedName("status_emoji")
    @Expose
    private String statusEmoji;
    @SerializedName("status_expiration")
    @Expose
    private Integer statusExpiration;
    @SerializedName("avatar_hash")
    @Expose
    private String avatarHash;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("image_24")
    @Expose
    private String image24;
    @SerializedName("image_32")
    @Expose
    private String image32;
    @SerializedName("image_48")
    @Expose
    private String image48;
    @SerializedName("image_72")
    @Expose
    private String image72;
    @SerializedName("image_192")
    @Expose
    private String image192;
    @SerializedName("image_512")
    @Expose
    private String image512;
    @SerializedName("status_text_canonical")
    @Expose
    private String statusTextCanonical;
    @SerializedName("team")
    @Expose
    private String team;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("bot_id")
    @Expose
    private String botId;
    @SerializedName("api_app_id")
    @Expose
    private String apiAppId;
    @SerializedName("always_active")
    @Expose
    private Boolean alwaysActive;
    @SerializedName("image_original")
    @Expose
    private String imageOriginal;
    @SerializedName("image_1024")
    @Expose
    private String image1024;
    @SerializedName("is_custom_image")
    @Expose
    private Boolean isCustomImage;
    @SerializedName("fields")
    @Expose
    private Object fields;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRealNameNormalized() {
        return realNameNormalized;
    }

    public void setRealNameNormalized(String realNameNormalized) {
        this.realNameNormalized = realNameNormalized;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayNameNormalized() {
        return displayNameNormalized;
    }

    public void setDisplayNameNormalized(String displayNameNormalized) {
        this.displayNameNormalized = displayNameNormalized;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public String getStatusEmoji() {
        return statusEmoji;
    }

    public void setStatusEmoji(String statusEmoji) {
        this.statusEmoji = statusEmoji;
    }

    public Integer getStatusExpiration() {
        return statusExpiration;
    }

    public void setStatusExpiration(Integer statusExpiration) {
        this.statusExpiration = statusExpiration;
    }

    public String getAvatarHash() {
        return avatarHash;
    }

    public void setAvatarHash(String avatarHash) {
        this.avatarHash = avatarHash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage24() {
        return image24;
    }

    public void setImage24(String image24) {
        this.image24 = image24;
    }

    public String getImage32() {
        return image32;
    }

    public void setImage32(String image32) {
        this.image32 = image32;
    }

    public String getImage48() {
        return image48;
    }

    public void setImage48(String image48) {
        this.image48 = image48;
    }

    public String getImage72() {
        return image72;
    }

    public void setImage72(String image72) {
        this.image72 = image72;
    }

    public String getImage192() {
        return image192;
    }

    public void setImage192(String image192) {
        this.image192 = image192;
    }

    public String getImage512() {
        return image512;
    }

    public void setImage512(String image512) {
        this.image512 = image512;
    }

    public String getStatusTextCanonical() {
        return statusTextCanonical;
    }

    public void setStatusTextCanonical(String statusTextCanonical) {
        this.statusTextCanonical = statusTextCanonical;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBotId() {
        return botId;
    }

    public void setBotId(String botId) {
        this.botId = botId;
    }

    public String getApiAppId() {
        return apiAppId;
    }

    public void setApiAppId(String apiAppId) {
        this.apiAppId = apiAppId;
    }

    public Boolean getAlwaysActive() {
        return alwaysActive;
    }

    public void setAlwaysActive(Boolean alwaysActive) {
        this.alwaysActive = alwaysActive;
    }

    public String getImageOriginal() {
        return imageOriginal;
    }

    public void setImageOriginal(String imageOriginal) {
        this.imageOriginal = imageOriginal;
    }

    public String getImage1024() {
        return image1024;
    }

    public void setImage1024(String image1024) {
        this.image1024 = image1024;
    }

    public Boolean getIsCustomImage() {
        return isCustomImage;
    }

    public void setIsCustomImage(Boolean isCustomImage) {
        this.isCustomImage = isCustomImage;
    }

    public Object getFields() {
        return fields;
    }

    public void setFields(Object fields) {
        this.fields = fields;
    }
}
