package com.vitaliy.moviesapp.pojo;

import com.google.gson.annotations.SerializedName;
import com.vitaliy.moviesapp.pojo.Movie;

import java.util.List;

public class MovieResponse {

    @SerializedName("results")
    private List<Movie> movies;

    public MovieResponse(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    @Override
    public String toString() {
        return "MovieResponse{" +
                "movies=" + movies +
                '}';
    }
}
