package com.yasin.slackchat.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by im_yasinashraf started on 20/1/19.
 */
public class Channel {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("is_channel")
    @Expose
    private Boolean isChannel;
    @SerializedName("created")
    @Expose
    private Integer created;
    @SerializedName("is_archived")
    @Expose
    private Boolean isArchived;
    @SerializedName("is_general")
    @Expose
    private Boolean isGeneral;
    @SerializedName("unlinked")
    @Expose
    private Integer unlinked;
    @SerializedName("creator")
    @Expose
    private String creator;
    @SerializedName("name_normalized")
    @Expose
    private String nameNormalized;
    @SerializedName("is_shared")
    @Expose
    private Boolean isShared;
    @SerializedName("is_org_shared")
    @Expose
    private Boolean isOrgShared;
    @SerializedName("is_member")
    @Expose
    private Boolean isMember;
    @SerializedName("is_private")
    @Expose
    private Boolean isPrivate;
    @SerializedName("is_mpim")
    @Expose
    private Boolean isMpim;
    @SerializedName("members")
    @Expose
    private List<String> members = null;
    @SerializedName("topic")
    @Expose
    private Topic topic;
    @SerializedName("purpose")
    @Expose
    private Purpose purpose;
    @SerializedName("previous_names")
    @Expose
    private List<Object> previousNames = null;
    @SerializedName("num_members")
    @Expose
    private Integer numMembers;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsChannel() {
        return isChannel;
    }

    public void setIsChannel(Boolean isChannel) {
        this.isChannel = isChannel;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public Boolean getIsArchived() {
        return isArchived;
    }

    public void setIsArchived(Boolean isArchived) {
        this.isArchived = isArchived;
    }

    public Boolean getIsGeneral() {
        return isGeneral;
    }

    public void setIsGeneral(Boolean isGeneral) {
        this.isGeneral = isGeneral;
    }

    public Integer getUnlinked() {
        return unlinked;
    }

    public void setUnlinked(Integer unlinked) {
        this.unlinked = unlinked;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getNameNormalized() {
        return nameNormalized;
    }

    public void setNameNormalized(String nameNormalized) {
        this.nameNormalized = nameNormalized;
    }

    public Boolean getIsShared() {
        return isShared;
    }

    public void setIsShared(Boolean isShared) {
        this.isShared = isShared;
    }

    public Boolean getIsOrgShared() {
        return isOrgShared;
    }

    public void setIsOrgShared(Boolean isOrgShared) {
        this.isOrgShared = isOrgShared;
    }

    public Boolean getIsMember() {
        return isMember;
    }

    public void setIsMember(Boolean isMember) {
        this.isMember = isMember;
    }

    public Boolean getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(Boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public Boolean getIsMpim() {
        return isMpim;
    }

    public void setIsMpim(Boolean isMpim) {
        this.isMpim = isMpim;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Purpose getPurpose() {
        return purpose;
    }

    public void setPurpose(Purpose purpose) {
        this.purpose = purpose;
    }

    public List<Object> getPreviousNames() {
        return previousNames;
    }

    public void setPreviousNames(List<Object> previousNames) {
        this.previousNames = previousNames;
    }

    public Integer getNumMembers() {
        return numMembers;
    }

    public void setNumMembers(Integer numMembers) {
        this.numMembers = numMembers;
    }
}
