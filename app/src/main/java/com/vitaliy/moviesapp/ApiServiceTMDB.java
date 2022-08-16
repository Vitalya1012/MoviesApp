package com.vitaliy.moviesapp;

import com.vitaliy.moviesapp.pojo.DetailInfo;
import com.vitaliy.moviesapp.pojo.MovieResponse;
import com.vitaliy.moviesapp.pojo.ReviewResponse;
import com.vitaliy.moviesapp.pojo.TrailersResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiServiceTMDB {

    @GET("discover/movie?api_key=ca4cecd747e7e2899902abdef7d6587d&language=ru-RU&sort_by=popularity.desc")
    Single<MovieResponse> loadMovies(@Query("page") int page);

    @GET("search/movie?api_key=ca4cecd747e7e2899902abdef7d6587d&language=en-US")
    Single<MovieResponse> loadFilteredMovies(@Query("query") String name);

    @GET("movie/{movie_id}/videos?api_key=ca4cecd747e7e2899902abdef7d6587d&language=en-US")
    Single<TrailersResponse> loadTrailers(@Path("movie_id") int id);

    @GET("movie/{movie_id}/reviews?api_key=ca4cecd747e7e2899902abdef7d6587d&language=en-US")
    Single<ReviewResponse> loadRiviews(@Path("movie_id") int id);

    @GET("movie/{movie_id}?api_key=ca4cecd747e7e2899902abdef7d6587d&language=en-US")
    Single<DetailInfo> loadDetailInfo(@Path("movie_id") int id);

}
