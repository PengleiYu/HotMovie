package com.example.penglei.hotmovie;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.penglei.hotmovie.utilities.JsonUtil;
import com.example.penglei.hotmovie.utilities.NetUtils;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycler_main);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movie, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_refresh) {
            mRecyclerView.setAdapter(null);
            loadData();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadData() {
        new MovieTask().execute();
    }

    private class MovieTask extends AsyncTask<Void, Void, String[]> {
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
            SimpleMovieAdapter adapter = new SimpleMovieAdapter(s);
            mRecyclerView.setAdapter(adapter);
        }
    }
}
