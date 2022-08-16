package com.vitaliy.moviesapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.vitaliy.moviesapp.database.MovieDao;
import com.vitaliy.moviesapp.database.MovieDatabase;
import com.vitaliy.moviesapp.pojo.Movie;

import java.util.List;


public class FavoriteMoviesViewModel extends AndroidViewModel {

    private final MovieDao movieDao;

    public FavoriteMoviesViewModel(@NonNull Application application) {
        super(application);
        movieDao = MovieDatabase.getInstance(application).movieDao();
    }

    public LiveData<List<Movie>> getMovies(){
        return movieDao.getAllFavoriteMovies();
    }
}
