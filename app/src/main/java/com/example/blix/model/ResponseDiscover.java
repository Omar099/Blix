package com.example.blix.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseDiscover {
    @SerializedName("page")
    @Expose
    private int page;
    @SerializedName("total_results")
    @Expose
    private int totalResults;
    @SerializedName("total_pages")
    @Expose
    private int totalPages;
    @SerializedName("results")
    @Expose
    private List<ItemMedia> results;

    public ResponseDiscover(int page, int totalResults, int totalPages, List<ItemMedia> results) {
        this.page = page;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
        this.results = results;
    }

    public ResponseDiscover() {
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<ItemMedia> getResults() {
        return results;
    }

    public void setResults(List<ItemMedia> results) {
        this.results = results;
    }
}
