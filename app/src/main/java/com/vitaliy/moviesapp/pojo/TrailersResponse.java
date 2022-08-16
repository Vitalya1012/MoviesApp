package com.vitaliy.moviesapp.pojo;

import com.google.gson.annotations.SerializedName;
import com.vitaliy.moviesapp.pojo.Trailer;

import java.util.List;

public class TrailersResponse {

    @SerializedName("results")
    private List<Trailer> trailers;

    public TrailersResponse(List<Trailer> trailers) {
        this.trailers = trailers;
    }

    public List<Trailer> getTrailers() {
        return trailers;
    }

    @Override
    public String toString() {
        return "TrailersList{" +
                "trailers=" + trailers +
                '}';
    }
}
