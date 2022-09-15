package edu.vit.gravitas.Object.Popular;

public class PopularEventObject {
    String name;
    String poster;
    String eventId;

    public PopularEventObject(String name, String poster, String eventId) {
        this.name = name;
        this.poster = poster;
        this.eventId = eventId;
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

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
}
