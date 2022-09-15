package edu.vit.gravitas.Object.Popular;

import java.util.List;

public class PopularMainObject {
    String _id;
    List<PopularEventObject> events;

    public PopularMainObject(String _id, List<PopularEventObject> events) {
        this._id = _id;
        this.events = events;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public List<PopularEventObject> getEvents() {
        return events;
    }

    public void setEvents(List<PopularEventObject> events) {
        this.events = events;
    }
}
