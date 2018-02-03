package com.example.penglei.hotmovie.utilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by penglei on 18-1-30.
 */

public class JsonUtil {
    private static final String MOVIE_RESULT = "results";
    private static final String MOVIE_ID = "id";
    private static final String MOVIE_TITLE = "title";
    private static final String MOVIE_POPULARITY = "popularity";
    private static final String MOVIE_VOTE_AVERAGE = "vote_average";
    private static final String MOVIE_VOTE_COUNT = "vote_count";
    private static final String MOVIE_DESC = "overview";
    private static final String MOVIE_POSTER_PATH = "poster_path";
    private static final String MOVIE_BACKDROP_PATH = "backdrop_path";
    private static final String MOVIE_DATE = "release_date";

    public static String[] getSimpleMovieStringsFromJson(String hotMovieJsonStr) throws JSONException {
        JSONObject movieObj = new JSONObject(hotMovieJsonStr);
        JSONArray movieArray = movieObj.getJSONArray(MOVIE_RESULT);

        String[] parsedMovieData = new String[movieArray.length()];
        for (int i = 0; i < movieArray.length(); i++) {
            String title, desc, vote, date;
            JSONObject movie = movieArray.getJSONObject(i);
            title = movie.getString(MOVIE_TITLE);
            desc = movie.getString(MOVIE_DESC);
            vote = movie.getString(MOVIE_VOTE_AVERAGE);
            date = movie.getString(MOVIE_DATE);
            parsedMovieData[i] = String.format("%s \n评分:%s 上映日期:%s\n内容简介:%s", title, vote, date, desc);
        }
        return parsedMovieData;
    }
}
