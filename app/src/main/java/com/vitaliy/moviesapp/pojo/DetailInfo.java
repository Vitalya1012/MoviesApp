package com.vitaliy.moviesapp.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailInfo {
    @SerializedName("imdb_id")
    @Expose
    private String imdbId;

    public String getImdbId() {
        return imdbId;
    }
}
