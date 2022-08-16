package com.vitaliy.moviesapp.pojo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "favorite_movies")
public class Movie implements Serializable {

    @PrimaryKey
    @SerializedName("id")
    private int id;
    @SerializedName("original_title")
    private String name;
    @SerializedName("overview")
    private String overview;
    @SerializedName("release_date")
    private String release_date;
    @SerializedName("poster_path")
    private String poster;
    @SerializedName("vote_average")
    private Double rating;

    public Movie(int id, String name, String overview, String release_date, String poster, Double rating) {
        this.id = id;
        this.name = name;
        this.overview = overview;
        this.release_date = release_date;
        this.poster = poster;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getPoster() {
        return "https://image.tmdb.org/t/p/w500/" + poster;
    }

    public Double getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", overview='" + overview + '\'' +
                ", release_date='" + release_date + '\'' +
                ", poster='" + poster + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }
}
