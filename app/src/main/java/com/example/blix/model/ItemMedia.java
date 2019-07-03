package com.example.blix.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemMedia implements Parcelable {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("ItemVideo")
    @Expose
    private String video;
    @SerializedName("media_type")
    @Expose
    private String mediaType;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("original_language")
    @Expose
    private String language;
    @SerializedName("adult")
    @Expose
    private String adult;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("release_date")
    @Expose
    private String releaseDate;

    private boolean priority;

    public ItemMedia(String id, String video, String title, String posterPath, String language, String adult, String overview, String releaseDate, boolean priority) {
        this.id = id;
        this.video = video;
        this.title = title;
        this.posterPath = posterPath;
        this.language = language;
        this.adult = adult;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.priority = priority;
    }

    public ItemMedia() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getAdult() {
        return adult;
    }

    public void setAdult(String adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public boolean isPriority() {
        return priority;
    }

    public void setPriority(boolean priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "ItemMedia{" +
                "id='" + id + '\'' +
                ", ItemVideo='" + video + '\'' +
                ", mediaType='" + mediaType + '\'' +
                ", title='" + title + '\'' +
                ", posterPath='" + posterPath + '\'' +
                ", language='" + language + '\'' +
                ", adult='" + adult + '\'' +
                ", overview='" + overview + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", priority=" + priority +
                '}';
    }

    protected ItemMedia(Parcel in) {
        id = in.readString();
        video = in.readString();
        mediaType = in.readString();
        title = in.readString();
        posterPath = in.readString();
        language = in.readString();
        adult = in.readString();
        overview = in.readString();
        releaseDate = in.readString();
        priority = in.readByte() != 0x00;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(video);
        dest.writeString(mediaType);
        dest.writeString(title);
        dest.writeString(posterPath);
        dest.writeString(language);
        dest.writeString(adult);
        dest.writeString(overview);
        dest.writeString(releaseDate);
        dest.writeByte((byte) (priority ? 0x01 : 0x00));
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ItemMedia> CREATOR = new Parcelable.Creator<ItemMedia>() {
        @Override
        public ItemMedia createFromParcel(Parcel in) {
            return new ItemMedia(in);
        }

        @Override
        public ItemMedia[] newArray(int size) {
            return new ItemMedia[size];
        }
    };
}