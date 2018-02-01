package com.example.penglei.hotmovie;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by penglei on 18-2-1.
 */

public class DetailActivity extends AppCompatActivity {
    private static final String MOVIE_SHARE_HASHTAG = "#HotMovie";
    private String mMovie;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        TextView textView = findViewById(R.id.tv_display_movie);
        if (getIntent().hasExtra(MainActivity.EXTRA_MOVIE)) {
            mMovie = getIntent().getStringExtra(MainActivity.EXTRA_MOVIE);
            textView.setText(mMovie);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail, menu);
        MenuItem item = menu.findItem(R.id.action_share);
        /* 第一次发现还有这操作 */
        item.setIntent(getSharedIntent());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            startActivity(new Intent(this, SettingActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private Intent getSharedIntent() {
        /* 留意用法 */
        return ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setText(mMovie + MOVIE_SHARE_HASHTAG)
                .getIntent();
    }
}
