package edu.vit.gravitas.Object.Club;

import edu.vit.gravitas.Object.Event.EventMetadataObject;

import java.util.List;

public class ClubAllEvents {
    List<ClubEventObject> data;
    List<EventMetadataObject> metadata;

    public ClubAllEvents(List<ClubEventObject> data, List<EventMetadataObject> metadata) {
        this.data = data;
        this.metadata = metadata;
    }

    public List<ClubEventObject> getData() {
        return data;
    }

    public void setData(List<ClubEventObject> data) {
        this.data = data;
    }

    public List<EventMetadataObject> getMetadata() {
        return metadata;
    }

    public void setMetadata(List<EventMetadataObject> metadata) {
        this.metadata = metadata;
    }
}
