package com.ahmedhassan.moviesapp.Favorite;

import android.provider.BaseColumns;

/**
 * Created by Ahmed Hassan on 24/11/2016.
 */

public class favoriteDB {

    public static abstract class FavoriteEntry implements BaseColumns {

        //that implement base coln id
        public static final String TABLE_NAME = "favorite";
        // table details
        public static final String MOVIE_ID = "id";
        public static final String ORIGINAL_TITLE = "originalTitle";
        public static final String POSTER_PATH = "posterPath";
        public static final String BACK_DROPS ="backdrops";
        public static final String TITLE = "title";
        public static final String RAT = "rat";
        public static final String OVERVIEW = "overview";
        public static final String RELEASE_DATE = "release_date";

    }
}
