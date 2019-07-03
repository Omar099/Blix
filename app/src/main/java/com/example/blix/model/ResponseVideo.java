package com.example.blix.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseVideo {
    @SerializedName("id")
    @Expose
    int id;
    @SerializedName("results")
    @Expose
    List<ItemVideo> results;

    public ResponseVideo(int id, List<ItemVideo> results) {
        this.id = id;
        this.results = results;
    }

    public ResponseVideo() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ItemVideo> getResults() {
        return results;
    }

    public void setResults(List<ItemVideo> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "ResponseVideo{" +
                "id=" + id +
                ", results=" + results +
                '}';
    }
}
