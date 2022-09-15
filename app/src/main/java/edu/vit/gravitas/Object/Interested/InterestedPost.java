package edu.vit.gravitas.Object.Interested;

import java.util.List;

public class InterestedPost {
    List<String> events;

    public InterestedPost(List<String> events) {
        this.events = events;
    }

    public List<String> getEvents() {
        return events;
    }

    public void setEvents(List<String> events) {
        this.events = events;
    }
}
