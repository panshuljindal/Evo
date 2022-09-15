package edu.vit.gravitas.Object.Like;

public class LikeBody {
    String eventId;
    String id;

    public LikeBody(String eventId, String id) {
        this.eventId = eventId;
        this.id = id;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
