package com.example.penglei.hotmovie;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.penglei.hotmovie.utilities.JsonUtil;
import com.example.penglei.hotmovie.utilities.NetUtils;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieAdapterOnClickHandler {
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
        loadData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movie, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_refresh) {
            mMovieAdapter.setData(null);
            loadData();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showErrorMessage() {
        mErrorView.setVisibility(View.VISIBLE);
        mMovieDataView.setVisibility(View.INVISIBLE);
    }

    private void showMovieDataView() {
        mErrorView.setVisibility(View.INVISIBLE);
        mMovieDataView.setVisibility(View.VISIBLE);
    }

    private void loadData() {
        showMovieDataView();
        new MovieTask().execute();
    }

    @Override
    public void onClick(String movie) {
        Toast.makeText(this, movie, Toast.LENGTH_SHORT).show();
    }

    private class MovieTask extends AsyncTask<Void, Void, String[]> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingView.setVisibility(View.VISIBLE);
        }

        @Override
        protected String[] doInBackground(Void... voids) {
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
        protected void onPostExecute(String[] s) {
            super.onPostExecute(s);
            mLoadingView.setVisibility(View.INVISIBLE);
            if (s != null) {
                showMovieDataView();
                mMovieAdapter.setData(s);
            } else showErrorMessage();
        }
    }
}
