package com.example.penglei.hotmovie.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.penglei.hotmovie.data.MovieContract.MovieEntry;

/**
 * Created by penglei on 18-2-3.
 */

public class MovieDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "movie.db";
    private static final int DATABASE_VERSION = 1;

    public MovieDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table " + MovieEntry.TABLE_NAME + " (" +
                        MovieEntry._ID + " integer primary key autoincrement, " +
                        MovieEntry.COLUMN_MOVIE_ID + " integer not null, " +
                        MovieEntry.COLUMN_TITLE + " text not null, " +
                        MovieEntry.COLUMN_POPULARITY + " integer not null, " +
                        MovieEntry.COLUMN_VOTE_AVERAGE + " integer not null, " +
                        MovieEntry.COLUMN_VOTE_COUNT + " integer not null, " +
                        MovieEntry.COLUMN_OVERVIEW + " text not null, " +
                        MovieEntry.COLUMN_BACKDROP_PATH + " text not null, " +
                        MovieEntry.COLUMN_POSTER_PATH + " text not null, " +
                        MovieEntry.COLUMN_RELEASE_DATE + " integer not null" + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + MovieEntry.TABLE_NAME);
        onCreate(db);
    }
}
