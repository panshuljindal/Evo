package com.panshul.evo.Object.Search;

public class SearchObject {
    int type;
    String eventId;
    String poster;
    String eventName;
    String clubName;
    String clubId;
    long timestamp;
    String clubLogo;
    String clubBackdrop;
    String _id;
    int __v;

    public SearchObject(int type, String eventId, String poster, String eventName, String clubName, String clubId, long timestamp, String clubLogo, String clubBackdrop, String _id, int __v) {
        this.type = type;
        this.eventId = eventId;
        this.poster = poster;
        this.eventName = eventName;
        this.clubName = clubName;
        this.clubId = clubId;
        this.timestamp = timestamp;
        this.clubLogo = clubLogo;
        this.clubBackdrop = clubBackdrop;
        this._id = _id;
        this.__v = __v;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClubId() {
        return clubId;
    }

    public void setClubId(String clubId) {
        this.clubId = clubId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getClubLogo() {
        return clubLogo;
    }

    public void setClubLogo(String clubLogo) {
        this.clubLogo = clubLogo;
    }

    public String getClubBackdrop() {
        return clubBackdrop;
    }

    public void setClubBackdrop(String clubBackdrop) {
        this.clubBackdrop = clubBackdrop;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }
}
