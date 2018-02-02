package com.example.penglei.hotmovie.data;

import android.content.Context;
import android.support.v7.preference.PreferenceManager;

import com.example.penglei.hotmovie.R;

/**
 * Created by penglei on 18-2-3.
 */

public class MoviePreferences {
    public static String getMovieOrder(Context context) {
        String orderKey = context.getString(R.string.pref_order_key);
        String orderDefault = context.getString(R.string.pref_order_value_default);
        return PreferenceManager.getDefaultSharedPreferences(context).getString(orderKey, orderDefault);
    }
}
