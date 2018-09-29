package com.ahmedhassan.moviesapp.Favorite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ahmedhassan.moviesapp.Movies;

import java.util.ArrayList;

public class FavoritesDB extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "favorite.db";

    public FavoritesDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FAVORITE_TABLE = "create table " + favoriteDB.FavoriteEntry.TABLE_NAME
                + "("
                //+ favoriteDB.FavoriteEntry._ID + "Integer primary key autoincrement,"
                + favoriteDB.FavoriteEntry.MOVIE_ID + " text unique, "
                + favoriteDB.FavoriteEntry.ORIGINAL_TITLE + " text,"
                + favoriteDB.FavoriteEntry.OVERVIEW + " text,"
                + favoriteDB.FavoriteEntry.POSTER_PATH + " text,"
                + favoriteDB.FavoriteEntry.BACK_DROPS + " text,"
                + favoriteDB.FavoriteEntry.RAT + " text,"
                + favoriteDB.FavoriteEntry.RELEASE_DATE + " text,"
                + favoriteDB.FavoriteEntry.TITLE + " text"
                + ")";
        db.execSQL(CREATE_FAVORITE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists " + favoriteDB.FavoriteEntry.TABLE_NAME);
        onCreate(db);
    }

    public void insertFavorite(Movies movie) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(favoriteDB.FavoriteEntry.MOVIE_ID, movie.getId());
        values.put(favoriteDB.FavoriteEntry.ORIGINAL_TITLE, movie.getOriginalTitle());
        values.put(favoriteDB.FavoriteEntry.OVERVIEW, movie.getOverview());
        values.put(favoriteDB.FavoriteEntry.POSTER_PATH, movie.getPosterPath());
        values.put(favoriteDB.FavoriteEntry.BACK_DROPS, movie.getBackdrops());
        values.put(favoriteDB.FavoriteEntry.RAT, movie.getRat());
        values.put(favoriteDB.FavoriteEntry.RELEASE_DATE, movie.getRelease_date());
        values.put(favoriteDB.FavoriteEntry.TITLE, movie.getTitle());
        // create
        db.insert(favoriteDB.FavoriteEntry.TABLE_NAME, null, values);

        db.close();
    }

    public void deleteMovie(Movies movie) {
        SQLiteDatabase db = this.getWritableDatabase();

        String DELETE_MOVIE = "delete from " + favoriteDB.FavoriteEntry.TABLE_NAME
                + " where id =" + movie.getId();
        db.execSQL(DELETE_MOVIE);

    }

    public ArrayList<Movies> getAllFavoriteMovies() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Movies> moviesList = new ArrayList<>();

        String SELECT_ALL_MOVIES = "select * from " + favoriteDB.FavoriteEntry.TABLE_NAME;
        Cursor cursor = db.rawQuery(SELECT_ALL_MOVIES, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            moviesList.add(new Movies(
                    cursor.getString(cursor.getColumnIndex(favoriteDB.FavoriteEntry.POSTER_PATH))
                    , cursor.getString(cursor.getColumnIndex(favoriteDB.FavoriteEntry.ORIGINAL_TITLE))
                    , cursor.getString(cursor.getColumnIndex(favoriteDB.FavoriteEntry.TITLE))
                    , cursor.getString(cursor.getColumnIndex(favoriteDB.FavoriteEntry.BACK_DROPS))
                    , cursor.getDouble(cursor.getColumnIndex(favoriteDB.FavoriteEntry.RAT))
                    , cursor.getString(cursor.getColumnIndex(favoriteDB.FavoriteEntry.OVERVIEW))
                    , cursor.getString(cursor.getColumnIndex(favoriteDB.FavoriteEntry.RELEASE_DATE))
                    , cursor.getString(cursor.getColumnIndex(favoriteDB.FavoriteEntry.MOVIE_ID))));
//            Movies movies = new Movies();
//            movies.setId(cursor.getString(cursor.getColumnIndex(favoriteDB.FavoriteEntry.MOVIE_ID)));
//            movies.setTitle(cursor.getString(cursor.getColumnIndex(favoriteDB.FavoriteEntry.TITLE)));
//            movies.setRat(cursor.getDouble(cursor.getColumnIndex(favoriteDB.FavoriteEntry.RAT)));
//            movies.setRelease_date(cursor.getString(cursor.getColumnIndex(favoriteDB.FavoriteEntry.RELEASE_DATE)));
//            movies.setPosterPath(cursor.getString(cursor.getColumnIndex(favoriteDB.FavoriteEntry.POSTER_PATH)));
//            movies.setOverview(cursor.getString(cursor.getColumnIndex(favoriteDB.FavoriteEntry.OVERVIEW)));
//            movies.setOriginalTitle(cursor.getString(cursor.getColumnIndex(favoriteDB.FavoriteEntry.ORIGINAL_TITLE)));
//            moviesList.add(movies);
            cursor.moveToNext();
        }
        return moviesList;
    }

    public boolean isFavorite(Movies movie) {
        SQLiteDatabase db = this.getReadableDatabase();
        String SELECT = "select * from " + favoriteDB.FavoriteEntry.TABLE_NAME + " where id=" + movie.getId();
        Cursor cursor = db.rawQuery(SELECT, null);
        if (cursor.getCount() != 0)
            return true;
        else
            return false;
    }
}
