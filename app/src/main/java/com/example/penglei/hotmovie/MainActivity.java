package com.example.penglei.hotmovie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
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

public class MainActivity extends AppCompatActivity
        implements MovieAdapter.MovieAdapterOnClickHandler, LoaderManager.LoaderCallbacks<String[]> {
    public static final String EXTRA_MOVIE = "EXTRA_MOVIE";
    private static final int LOADER_ID = 1;

    private RecyclerView mMovieDataView;
    private TextView mErrorView;
    private ProgressBar mLoadingView;
    private MovieAdapter mMovieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mErrorView = findViewById(R.id.tv_errorView_main);
        mLoadingView = findViewById(R.id.pb_loadingIndicator_main);

        mMovieAdapter = new MovieAdapter(this);

        mMovieDataView = findViewById(R.id.recycler_main);
        mMovieDataView.setLayoutManager(new LinearLayoutManager(this));
        mMovieDataView.setHasFixedSize(true);
        mMovieDataView.setAdapter(mMovieAdapter);
        getSupportLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movie, menu);
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
                URL url = NetUtils.buildUrl();
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
    public void onLoaderReset(Loader<String[]> loader) { }
}
