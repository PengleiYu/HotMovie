package com.example.penglei.hotmovie.data;

import android.provider.BaseColumns;

/**
 * Created by penglei on 18-2-3.
 */

public class MovieContract {
    public static final class MovieEntry implements BaseColumns {
        public static final String TABLE_NAME = "movie";
        public static final String COLUMN_MOVIE_ID = "movie_id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_POPULARITY = "popularity";
        public static final String COLUMN_VOTE_COUNT = "vote_count";
        public static final String COLUMN_VOTE_AVERAGE = "vote_average";
        public static final String COLUMN_OVERVIEW = "overview";
        public static final String COLUMN_POSTER_PATH = "poster_path";
        public static final String COLUMN_BACKDROP_PATH = "backdrop_path";
        public static final String COLUMN_RELEASE_DATE = "release_date";
        /**
         * 录入数据库的时间
         */
        public static final String COLUMN_RECORD_DATE = "record_date";
    }
}
