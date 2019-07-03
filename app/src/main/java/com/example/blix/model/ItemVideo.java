package com.example.blix.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemVideo {
    @SerializedName("id")
    @Expose
    String id;
    @SerializedName("key")
    @Expose
    String key;
    @SerializedName("site")
    @Expose
    String site;

    public ItemVideo(String id, String key, String site) {
        this.id = id;
        this.key = key;
        this.site = site;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    @Override
    public String toString() {
        return "ItemVideo{" +
                "id='" + id + '\'' +
                ", key='" + key + '\'' +
                ", site='" + site + '\'' +
                '}';
    }
}
