package com.panshul.evo.Object;

public class EventObject {
    boolean isPaid;
    boolean isGravitas;
    boolean isRiviera;
    boolean isHack;
    String eventType;
    int likes;
    String _id;
    String info;
    String videoLink;
    int eventCost;
    String startDate;
    String startTime;
    String registrationLink;
    String meetingLink;
    String venue;
    String poster;
    String club;
    String clubName;
    String createdAt;
    String updatedAt;
    int __v;

    public EventObject(boolean isPaid, boolean isGravitas, boolean isRiviera, boolean isHack, String eventType, int likes, String _id, String info, String videoLink, int eventCost, String startDate, String startTime, String registrationLink, String meetingLink, String venue, String poster, String club, String clubName, String createdAt, String updatedAt, int __v) {
        this.isPaid = isPaid;
        this.isGravitas = isGravitas;
        this.isRiviera = isRiviera;
        this.isHack = isHack;
        this.eventType = eventType;
        this.likes = likes;
        this._id = _id;
        this.info = info;
        this.videoLink = videoLink;
        this.eventCost = eventCost;
        this.startDate = startDate;
        this.startTime = startTime;
        this.registrationLink = registrationLink;
        this.meetingLink = meetingLink;
        this.venue = venue;
        this.poster = poster;
        this.club = club;
        this.clubName = clubName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.__v = __v;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public boolean isGravitas() {
        return isGravitas;
    }

    public void setGravitas(boolean gravitas) {
        isGravitas = gravitas;
    }

    public boolean isRiviera() {
        return isRiviera;
    }

    public void setRiviera(boolean riviera) {
        isRiviera = riviera;
    }

    public boolean isHack() {
        return isHack;
    }

    public void setHack(boolean hack) {
        isHack = hack;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public int getEventCost() {
        return eventCost;
    }

    public void setEventCost(int eventCost) {
        this.eventCost = eventCost;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
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

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
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
