package com.panshul.evo.Object.User;

import java.util.List;

public class UserMetaData {
    List<String> likedEvents;
    List<String> interestedEvents;
    String _id;
    String createdAt;
    String updatedAt;
    int __v;

    public UserMetaData(List<String> likedEvents, List<String> interestedEvents, String _id, String createdAt, String updatedAt, int __v) {
        this.likedEvents = likedEvents;
        this.interestedEvents = interestedEvents;
        this._id = _id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.__v = __v;
    }

    public List<String> getLikedEvents() {
        return likedEvents;
    }

    public void setLikedEvents(List<String> likedEvents) {
        this.likedEvents = likedEvents;
    }

    public List<String> getInterestedEvents() {
        return interestedEvents;
    }

    public void setInterestedEvents(List<String> interestedEvents) {
        this.interestedEvents = interestedEvents;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }
}
