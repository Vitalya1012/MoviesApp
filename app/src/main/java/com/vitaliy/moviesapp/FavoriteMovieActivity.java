package com.vitaliy.moviesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.vitaliy.moviesapp.pojo.Movie;

import java.util.List;

public class FavoriteMovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_movie);

        RecyclerView recyclerviewFavoriteMovies = findViewById(R.id.recyclerviewFavoriteMovies);
        MoviesAdapter moviesAdapter = new MoviesAdapter();
        recyclerviewFavoriteMovies.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerviewFavoriteMovies.setAdapter(moviesAdapter);
        moviesAdapter.setOnMovieClickListener(new MoviesAdapter.OnMovieClickListener() {
            @Override
            public void onMovieClick(Movie movie) {
                Intent intent = MovieDetailActivity.newIntent(FavoriteMovieActivity.this, movie);
                startActivity(intent);
            }
        });

        FavoriteMoviesViewModel viewModel = new ViewModelProvider(this).get(FavoriteMoviesViewModel.class);
        viewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                moviesAdapter.setMovies(movies);
            }
        });
    }

    public static Intent newIntent(Context context){
        return new Intent(context, FavoriteMovieActivity.class);
    }
}