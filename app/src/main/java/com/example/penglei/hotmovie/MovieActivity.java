package com.example.penglei.hotmovie;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.penglei.hotmovie.utilities.JsonUtil;
import com.example.penglei.hotmovie.utilities.NetUtils;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;

public class MovieActivity extends AppCompatActivity
        implements MovieAdapter.MovieAdapterOnClickHandler,
        SharedPreferences.OnSharedPreferenceChangeListener,
        LoaderManager.LoaderCallbacks<String[]> {
    public static final String EXTRA_MOVIE = "EXTRA_MOVIE";
    private static final int LOADER_ID = 1;

    private RecyclerView mMovieDataView;
    private TextView mErrorView;
    private ProgressBar mLoadingView;
    private MovieAdapter mMovieAdapter;

    /**
     * 应当为static；精髓
     */
    private static boolean PREFERENCE_HAVE_BEEN_UPDATED = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        mErrorView = findViewById(R.id.tv_errorView_main);
        mLoadingView = findViewById(R.id.pb_loadingIndicator_main);

        mMovieAdapter = new MovieAdapter(this);

        mMovieDataView = findViewById(R.id.recycler_main);
        mMovieDataView.setLayoutManager(new LinearLayoutManager(this));
        mMovieDataView.setHasFixedSize(true);
        mMovieDataView.setAdapter(mMovieAdapter);
        getSupportLoaderManager().initLoader(LOADER_ID, null, this);

        /* onStart时注册 */
        PreferenceManager.getDefaultSharedPreferences(this)
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movie, menu);
        menu.findItem(R.id.action_settings).setIntent(new Intent(this, SettingActivity.class));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_refresh) {
            invalidateData();
            getSupportLoaderManager().restartLoader(LOADER_ID, null, this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (PREFERENCE_HAVE_BEEN_UPDATED) {
            invalidateData();
            getSupportLoaderManager().restartLoader(LOADER_ID, null, this);
            PREFERENCE_HAVE_BEEN_UPDATED = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this)
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    private void invalidateData() {
        mMovieAdapter.setData(null);
    }

    @Override
    public void onClick(String movie) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        startActivity(intent);
    }

    private void showErrorMessage() {
        mErrorView.setVisibility(View.VISIBLE);
        mMovieDataView.setVisibility(View.INVISIBLE);
    }

    private void showMovieDataView() {
        mErrorView.setVisibility(View.INVISIBLE);
        mMovieDataView.setVisibility(View.VISIBLE);
    }

    @Override
    public Loader<String[]> onCreateLoader(int id, Bundle args) {
        return new AsyncTaskLoader<String[]>(this) {
            private String[] mMovieData = null;

            @Override
            protected void onStartLoading() {
                if (mMovieData != null) deliverResult(mMovieData);
                else {
                    mLoadingView.setVisibility(View.VISIBLE);
                    forceLoad();
                }
            }

            @Override
            public String[] loadInBackground() {
                URL url = NetUtils.buildUrl(getContext());
                try {
                    String response = NetUtils.getResponseFromUrl(url);
                    return JsonUtil.getSimpleMovieStringsFromJson(response);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            public void deliverResult(String[] data) {
                mMovieData = data;
                super.deliverResult(data);
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<String[]> loader, String[] data) {
        mLoadingView.setVisibility(View.INVISIBLE);
        mMovieAdapter.setData(data);
        if (data == null) showErrorMessage();
        else showMovieDataView();
    }

    @Override
    public void onLoaderReset(Loader<String[]> loader) {
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (getString(R.string.pref_order_key).equals(key))
            PREFERENCE_HAVE_BEEN_UPDATED = true;
    }
}
