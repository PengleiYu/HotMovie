package com.example.penglei.hotmovie.utilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by penglei on 18-1-30.
 */

public class JsonUtil {
    public static String[] getSimpleMovieStringsFromJson(String hotMovieJsonStr) throws JSONException {
        final String MOVIE_LIST = "results";
        final String MOVIE_VOTE = "vote_average";
        final String MOVIE_TITLE = "title";
        final String MOVIE_DESC = "overview";
        final String MOVIE_DATE = "release_date";
        JSONObject movieObj = new JSONObject(hotMovieJsonStr);
        JSONArray movieArray = movieObj.getJSONArray(MOVIE_LIST);

        String[] parsedMovieData = new String[movieArray.length()];
        for (int i = 0; i < movieArray.length(); i++) {
            String title, desc, vote, date;
            JSONObject movie = movieArray.getJSONObject(i);
            title = movie.getString(MOVIE_TITLE);
            desc = movie.getString(MOVIE_DESC);
            vote = movie.getString(MOVIE_VOTE);
            date = movie.getString(MOVIE_DATE);
            parsedMovieData[i] = String.format("%s \n评分:%s 上映日期:%s\n内容简介:%s", title, vote, date, desc);
        }
        return parsedMovieData;
    }
}
