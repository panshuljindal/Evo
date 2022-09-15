package edu.vit.gravitas.Object.Club;

import java.util.List;

public class ClubSpecificObject {
    String tagline;
    String clubType;
    String description;
    String linkedIn;
    String instagram;
    String facebook;
    String medium;
    String youtube;
    String twitter;
    String logo;
    String backdrop;
    boolean isVerified;
    boolean isPartner;
    List<ClubEventObject> events;
    String _id;
    String email;
    String password;
    String name;
    String createdAt;
    String updatedAt;
    int __v;

    public ClubSpecificObject(String tagline,String clubType, String description, String linkedIn, String instagram, String facebook, String medium, String youtube, String twitter, String logo, String backdrop, boolean isVerified, boolean isPartner, List<ClubEventObject> events, String _id, String email, String password, String name, String createdAt, String updatedAt, int __v) {
        this.tagline=tagline;
        this.clubType = clubType;
        this.description = description;
        this.linkedIn = linkedIn;
        this.instagram = instagram;
        this.facebook = facebook;
        this.medium = medium;
        this.youtube = youtube;
        this.twitter = twitter;
        this.logo = logo;
        this.backdrop = backdrop;
        this.isVerified = isVerified;
        this.isPartner = isPartner;
        this.events = events;
        this._id = _id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.__v = __v;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getClubType() {
        return clubType;
    }

    public void setClubType(String clubType) {
        this.clubType = clubType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLinkedIn() {
        return linkedIn;
    }

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public boolean isPartner() {
        return isPartner;
    }

    public void setPartner(boolean partner) {
        isPartner = partner;
    }

    public List<ClubEventObject> getEvents() {
        return events;
    }

    public void setEvents(List<ClubEventObject> events) {
        this.events = events;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }
}
