package com.vitaliy.moviesapp.database;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.vitaliy.moviesapp.pojo.Movie;

@Database(entities = {Movie.class}, version = 1, exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {

    private static final String DB_NAME = "movie.db";
    private static MovieDatabase instance = null;

    public static MovieDatabase getInstance(Application application){
        if(instance == null){
            instance = Room.databaseBuilder(application,
                    MovieDatabase.class,
                    DB_NAME
            ).build();
        }
        return instance;
    }

    public abstract MovieDao movieDao();

}
