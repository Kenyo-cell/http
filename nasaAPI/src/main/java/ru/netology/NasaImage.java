package ru.netology;

import com.google.gson.annotations.SerializedName;

public class NasaImage {
    @SerializedName("copyright") private String copyright;
    @SerializedName("title") private String title;
    @SerializedName("explanation") private String explanation;
    @SerializedName("hdurl") private String hdUrl;
    @SerializedName("url") private String url;

    public NasaImage() {}

    public NasaImage(String copyright, String title, String explanation, String hdUrl, String url) {
        this.copyright = copyright;
        this.title = title;
        this.explanation = explanation;
        this.hdUrl = hdUrl;
        this.url = url;
    }

    public String toString() {
        return String.format("%s by %s from %s or %s:\n%s", title, copyright, hdUrl, url, explanation);
    }

    public String getHdUrl() {
        return hdUrl;
    }

    public String getUrl() {
        return url;
    }
}
