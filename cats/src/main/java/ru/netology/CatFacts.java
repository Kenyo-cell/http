package ru.netology;

import com.google.gson.annotations.SerializedName;

public class CatFacts {
    @SerializedName("_id") private String id;
    @SerializedName("text") private String text;
    @SerializedName("type") private String type;
    @SerializedName("user") private String user;
    @SerializedName("used") private boolean used;

    public CatFacts() {

    }


    public CatFacts(String id, String text, String type, String user, boolean used) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.user = user;
        this.used = used;
    }

    public String toString() {
        return String.format("Fact (id %s): %s, used: %s", id, text, used);
    }

    public boolean isUsed() {
        return used;
    }

    public String getText() {
        return text;
    }
}
