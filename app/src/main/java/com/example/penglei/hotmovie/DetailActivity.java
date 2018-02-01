package com.example.penglei.hotmovie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by penglei on 18-2-1.
 */

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        TextView textView = findViewById(R.id.tv_display_movie);
        if (getIntent().hasExtra(MainActivity.EXTRA_MOVIE)) {
            textView.setText(getIntent().getStringExtra(MainActivity.EXTRA_MOVIE));
        }
    }
}
