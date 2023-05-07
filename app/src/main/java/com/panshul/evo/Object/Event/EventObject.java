package com.panshul.evo.Object.Event;

import java.util.List;

public class EventObject {

    List<String> eventType;
    int likes;
    String _id;
    String name;
    String poster;
    EventClubObject clubId;
    String clubName;

    public EventObject(List<String> eventType, int likes, String _id, String name, String poster, EventClubObject clubId, String clubName) {
        this.eventType = eventType;
        this.likes = likes;
        this._id = _id;
        this.name = name;
        this.poster = poster;
        this.clubId = clubId;
        this.clubName = clubName;
    }

    public List<String> getEventType() {
        return eventType;
    }

    public void setEventType(List<String> eventType) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public EventClubObject getClubId() {
        return clubId;
    }

    public void setClubId(EventClubObject clubId) {
        this.clubId = clubId;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }
}
