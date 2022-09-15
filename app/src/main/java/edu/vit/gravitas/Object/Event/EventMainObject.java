package edu.vit.gravitas.Object.Event;

import java.util.List;

public class EventMainObject {

    List<EventObject> data;
    List<EventMetadataObject> metadata;

    public EventMainObject(List<EventObject> data, List<EventMetadataObject> metadata) {
        this.data = data;
        this.metadata = metadata;
    }

    public List<EventObject> getData() {
        return data;
    }

    public void setData(List<EventObject> data) {
        this.data = data;
    }

    public List<EventMetadataObject> getMetadata() {
        return metadata;
    }

    public void setMetadata(List<EventMetadataObject> metadata) {
        this.metadata = metadata;
    }
}
