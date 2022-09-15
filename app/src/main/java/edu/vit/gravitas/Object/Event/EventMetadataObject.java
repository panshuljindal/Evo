package edu.vit.gravitas.Object.Event;

public class EventMetadataObject {
    String _id;
    int count;

    public EventMetadataObject(String _id, int count) {
        this._id = _id;
        this.count = count;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
