package com.vitaliy.moviesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vitaliy.moviesapp.pojo.DetailInfo;
import com.vitaliy.moviesapp.pojo.Movie;
import com.vitaliy.moviesapp.pojo.Review;
import com.vitaliy.moviesapp.pojo.Trailer;

import java.util.List;

public class MovieDetailActivity extends AppCompatActivity {

    private static final String EXTRA_MOVIE = "movie";
    private static final String TAG = "MovieDetailActivity";

    private MovieDetailViewModel viewModel;

    private ImageView imageViewPoster;
    private TextView textViewTitle;
    private TextView textViewYear;
    private TextView textViewDescription;
    private RecyclerView recyclerviewTrailers;
    private RecyclerView recyclerviewReviews;
    private TrailersAdapter trailersAdapter;
    private ReviewAdapter reviewAdapter;
    private ImageView imageViewStar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        viewModel = new ViewModelProvider(this).get(MovieDetailViewModel.class);
        initViews();
        trailersAdapter = new TrailersAdapter();
        reviewAdapter = new ReviewAdapter();
        recyclerviewTrailers.setAdapter(trailersAdapter);
        recyclerviewReviews.setAdapter(reviewAdapter);

        Movie movie = (Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);

        Glide.with(this)
                .load(movie.getPoster())
                .into(imageViewPoster);
        textViewTitle.setText(movie.getName());
        textViewYear.setText(movie.getRelease_date());
        textViewDescription.setText(movie.getOverview());

        viewModel.loadTrailer(movie.getId());
        viewModel.getTrailers().observe(this, new Observer<List<Trailer>>() {
            @Override
            public void onChanged(List<Trailer> trailers) {
                trailersAdapter.setTrailers(trailers);
            }
        });
        trailersAdapter.setOnTrailerClickListener(new TrailersAdapter.OnTrailerClickListener() {
            @Override
            public void donClick(Trailer trailer) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(trailer.getUrl()));
                startActivity(intent);
            }
        });
        viewModel.getReviews().observe(this, new Observer<List<Review>>() {
            @Override
            public void onChanged(List<Review> reviews) {
                reviewAdapter.setReviews(reviews);
            }
        });
        viewModel.loadReviews(movie.getId());
        Drawable starOff = ContextCompat.getDrawable(
                MovieDetailActivity.this,
                android.R.drawable.star_big_off
        );
        Drawable starOn = ContextCompat.getDrawable(
                MovieDetailActivity.this,
                android.R.drawable.star_big_on
        );
        viewModel.getFavoriteMovie(movie.getId()).observe(this, new Observer<Movie>() {
            @Override
            public void onChanged(Movie movieFromDB) {
                if(movieFromDB == null){
                    imageViewStar.setImageDrawable(starOff);
                    imageViewStar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            viewModel.insertMovie(movie);
                        }
                    });
                } else {
                    imageViewStar.setImageDrawable(starOn);
                    imageViewStar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            viewModel.removeMovie(movie.getId());
                        }
                    });
                }
            }
        });
//        viewModel.loadDetailInfo(movie.getId());
//        viewModel.getDetailInfo().observe(this, new Observer<DetailInfo>() {
//            @Override
//            public void onChanged(DetailInfo detailInfo) {
//                Log.d("getDetailInfo", detailInfo.getImdbId());
//            }
//        });

    }

    private void initViews() {
        imageViewPoster = findViewById(R.id.imageViewPoster);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewYear = findViewById(R.id.textViewYear);
        textViewDescription = findViewById(R.id.textViewDescription);
        recyclerviewTrailers = findViewById(R.id.recyclerviewTrailers);
        recyclerviewReviews = findViewById(R.id.recyclerviewReviews);
        imageViewStar = findViewById(R.id.imageViewStar);
    }

    public static Intent newIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        return intent;
    }
}