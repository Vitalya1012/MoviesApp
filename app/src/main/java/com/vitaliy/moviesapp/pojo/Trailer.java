package com.vitaliy.moviesapp.pojo;

import com.google.gson.annotations.SerializedName;

public class Trailer {

    private static final String BASE_URL = "https://www.youtube.com/watch?v=";

    @SerializedName("key")
    private String url;

    @SerializedName("name")
    private String name;

    public Trailer(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public String getUrl() {
        return BASE_URL + url;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Trailer{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
