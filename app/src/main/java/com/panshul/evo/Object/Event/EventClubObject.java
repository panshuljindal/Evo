package com.panshul.evo.Object.Event;

public class EventClubObject {
    String _id;
    String logo;

    public EventClubObject(String _id, String logo) {
        this._id = _id;
        this.logo = logo;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
