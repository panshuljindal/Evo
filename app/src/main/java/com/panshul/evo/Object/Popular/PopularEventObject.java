package com.panshul.evo.Object.Popular;

import java.util.List;

public class PopularEventObject {
    String name;
    String poster;
    int likes;
    long timestamp;
    String clubId;
    String clubName;
    String eventType;
    List<String> clublogo;

    public PopularEventObject(String name, String poster, int likes, long timestamp, String clubId, String clubName, String eventType, List<String> clublogo) {
        this.name = name;
        this.poster = poster;
        this.likes = likes;
        this.timestamp = timestamp;
        this.clubId = clubId;
        this.clubName = clubName;
        this.eventType = eventType;
        this.clublogo = clublogo;
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

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getClubId() {
        return clubId;
    }

    public void setClubId(String clubId) {
        this.clubId = clubId;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public List<String> getClublogo() {
        return clublogo;
    }

    public void setClublogo(List<String> clublogo) {
        this.clublogo = clublogo;
    }
}
