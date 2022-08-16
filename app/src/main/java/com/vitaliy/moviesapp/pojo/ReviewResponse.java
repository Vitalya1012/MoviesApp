package com.vitaliy.moviesapp.pojo;

import com.google.gson.annotations.SerializedName;
import com.vitaliy.moviesapp.pojo.Review;

import java.util.List;

public class ReviewResponse {

    @SerializedName("results")
    List<Review> reviews;

    public ReviewResponse(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    @Override
    public String toString() {
        return "ReviewResponse{" +
                "reviews=" + reviews +
                '}';
    }
}
