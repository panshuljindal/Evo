package com.panshul.evo.Object.Event;

public class EventSpecificObject{
    public String shortName;
    public String tagline;
    public String website;
    public String info;
    public String clubName;
    public String videoLink;
    public boolean isPaid;
    public int eventCost;
    public int likes;
    public int timestamp;
    public int duration;
    public String registrationLink;
    public String meetingLink;
    public String poster;
    public String venue;
    public String _id;
    public String name;
    public EventClubObject clubId;
    public String createdAt;
    public String updatedAt;
    public int __v;

    public EventSpecificObject(String shortName, String tagline, String website, String info, String clubName, String videoLink, boolean isPaid, int eventCost, int likes, int timestamp, int duration, String registrationLink, String meetingLink, String poster, String venue, String _id, String name, EventClubObject clubId, String createdAt, String updatedAt, int __v) {
        this.shortName = shortName;
        this.tagline = tagline;
        this.website = website;
        this.info = info;
        this.clubName = clubName;
        this.videoLink = videoLink;
        this.isPaid = isPaid;
        this.eventCost = eventCost;
        this.likes = likes;
        this.timestamp = timestamp;
        this.duration = duration;
        this.registrationLink = registrationLink;
        this.meetingLink = meetingLink;
        this.poster = poster;
        this.venue = venue;
        this._id = _id;
        this.name = name;
        this.clubId = clubId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.__v = __v;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }


    public int getEventCost() {
        return eventCost;
    }

    public void setEventCost(int eventCost) {
        this.eventCost = eventCost;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getRegistrationLink() {
        return registrationLink;
    }

    public void setRegistrationLink(String registrationLink) {
        this.registrationLink = registrationLink;
    }

    public String getMeetingLink() {
        return meetingLink;
    }

    public void setMeetingLink(String meetingLink) {
        this.meetingLink = meetingLink;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EventClubObject getClubId() {
        return clubId;
    }

    public void setClubId(EventClubObject clubId) {
        this.clubId = clubId;
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
