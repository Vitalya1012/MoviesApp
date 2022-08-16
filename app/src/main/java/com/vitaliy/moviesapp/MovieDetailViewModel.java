package com.vitaliy.moviesapp;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.vitaliy.moviesapp.database.MovieDao;
import com.vitaliy.moviesapp.database.MovieDatabase;
import com.vitaliy.moviesapp.pojo.DetailInfo;
import com.vitaliy.moviesapp.pojo.Movie;
import com.vitaliy.moviesapp.pojo.Review;
import com.vitaliy.moviesapp.pojo.ReviewResponse;
import com.vitaliy.moviesapp.pojo.Trailer;
import com.vitaliy.moviesapp.pojo.TrailersResponse;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MovieDetailViewModel extends AndroidViewModel {

    private static final String TAG = "MovieDetailViewModel";

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final MutableLiveData<List<Trailer>> trailers = new MutableLiveData<>();
    private final MutableLiveData<List<Review>> reviews = new MutableLiveData<>();
    private final MutableLiveData<DetailInfo> movieDetailInfo = new MutableLiveData<>();

    private final MovieDao movieDao;

    public MovieDetailViewModel(@NonNull Application application) {
        super(application);
        movieDao = MovieDatabase.getInstance(application).movieDao();
    }

    public LiveData<Movie> getFavoriteMovie(int movieID){
        return movieDao.getFavoriteMovie(movieID);
    }

    public LiveData<List<Trailer>> getTrailers() {
        return trailers;
    }

    public LiveData<List<Review>> getReviews() {
        return reviews;
    }

    public LiveData<DetailInfo> getDetailInfo() {
        return movieDetailInfo;
    }

    public void insertMovie(Movie movie){
        Disposable disposable = movieDao.insertMovie(movie)
                .subscribeOn(Schedulers.io())
                .subscribe();
        compositeDisposable.add(disposable);
    }

    public void removeMovie(int movieId){
        Disposable disposable = movieDao.removeMovie(movieId)
                .subscribeOn(Schedulers.io())
                .subscribe();
        compositeDisposable.add(disposable);
    }

    public void loadReviews(int id) {
        Disposable disposable = ApiFactoryTMDB.apiServiceTMDB.loadRiviews(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<ReviewResponse, List<Review>>() {
                    @Override
                    public List<Review> apply(ReviewResponse reviewResponse) throws Throwable {
                        return reviewResponse.getReviews();
                    }
                })
                .subscribe(new Consumer<List<Review>>() {
                    @Override
                    public void accept(List<Review> reviewList) throws Throwable {
                        reviews.setValue(reviewList);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d(TAG, throwable.toString());
                    }
                });
        compositeDisposable.add(disposable);
    }

    public void loadTrailer(int id) {
        Disposable disposable = ApiFactoryTMDB.apiServiceTMDB.loadTrailers(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<TrailersResponse, List<Trailer>>() {
                    @Override
                    public List<Trailer> apply(TrailersResponse trailersResponse) throws Throwable {
                        return trailersResponse.getTrailers();
                    }
                })
                .subscribe(new Consumer<List<Trailer>>() {
                    @Override
                    public void accept(List<Trailer> trailerList) throws Throwable {
                        trailers.setValue(trailerList);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d(TAG, throwable.toString());
                    }
                });
        compositeDisposable.add(disposable);
    }

    public void loadDetailInfo(int id) {
        Disposable disposable = ApiFactoryTMDB.apiServiceTMDB.loadDetailInfo(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DetailInfo>() {
                    @Override
                    public void accept(DetailInfo detailInfo) throws Throwable {
                        movieDetailInfo.setValue(detailInfo);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d(TAG, throwable.toString());
                    }
                });
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
