package com.panshul.evo.Object.Search;

public class SearchObject {
    String type;
    String eventName;
    String eventId;
    String eventClubName;
    long timestamp;
    int price;
    String clubId;
    String clubName;
    String clubType;
    String eventPoster;
    String clubLogo;

    public SearchObject(String type, String eventName, String eventId, String eventClubName, long timestamp, int price, String clubId, String clubName, String clubType, String eventPoster, String clubLogo) {
        this.type = type;
        this.eventName = eventName;
        this.eventId = eventId;
        this.eventClubName = eventClubName;
        this.timestamp = timestamp;
        this.price = price;
        this.clubId = clubId;
        this.clubName = clubName;
        this.clubType = clubType;
        this.eventPoster = eventPoster;
        this.clubLogo = clubLogo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventClubName() {
        return eventClubName;
    }

    public void setEventClubName(String eventClubName) {
        this.eventClubName = eventClubName;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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

    public String getClubType() {
        return clubType;
    }

    public void setClubType(String clubType) {
        this.clubType = clubType;
    }

    public String getEventPoster() {
        return eventPoster;
    }

    public void setEventPoster(String eventPoster) {
        this.eventPoster = eventPoster;
    }

    public String getClubLogo() {
        return clubLogo;
    }

    public void setClubLogo(String clubLogo) {
        this.clubLogo = clubLogo;
    }
}
