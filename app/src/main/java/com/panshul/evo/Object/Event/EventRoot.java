package com.panshul.evo.Object.Event;

public class EventRoot {
    EventSpecificObject event;

    public EventRoot(EventSpecificObject event) {
        this.event = event;
    }

    public EventSpecificObject getEvent() {
        return event;
    }

    public void setEvent(EventSpecificObject event) {
        this.event = event;
    }
}
