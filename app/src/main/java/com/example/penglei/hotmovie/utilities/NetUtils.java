package com.example.penglei.hotmovie.utilities;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.example.penglei.hotmovie.data.ApiKey;
import com.example.penglei.hotmovie.data.MoviePreferences;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by penglei on 18-1-30.
 */

public class NetUtils {
    private static final String API_KEY = ApiKey.KEY;
    private static final String STATIC_MOVIE_URL = "http://api.themoviedb.org/3/movie";
    private static final String POPULAR_URL = STATIC_MOVIE_URL + "/popular";
    private static final String PARAM_API_KEY = "api_key";

    @Nullable
    public static URL buildUrl(Context context) {
        String orderSegment = MoviePreferences.getMovieOrder(context);
        Uri uri = Uri.parse(STATIC_MOVIE_URL).buildUpon()
                .appendPath(orderSegment)
                .appendQueryParameter(PARAM_API_KEY, API_KEY)
                .build();
        try {
            return new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getResponseFromUrl(URL url) throws IOException {
        System.out.println("start request: " + url);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            InputStream inputStream = connection.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            scanner.useDelimiter("\\A");
            if (scanner.hasNext()) {
                return scanner.next();
            } else return null;
        } finally {
            connection.disconnect();
        }
    }
}
