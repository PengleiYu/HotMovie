package com.example.penglei.hotmovie.pojo;

/**
 * Created by penglei on 18-2-4.
 */

public class Movie {
    private final long mId;
    private final String mTitle;
    private final float mPopularity;
    private final float mVoteAverage;
    private final int mVoteCount;
    private final String mOverView;
    private final String mPosterPath;
    private final String mBackdropPath;
    private final long mReleaseDate;

    public Movie(long id, String title, float popularity, float voteAverage, int voteCount, String overView, String posterPath, String backdropPath, long releaseDate) {
        mId = id;
        mTitle = title;
        mPopularity = popularity;
        mVoteAverage = voteAverage;
        mVoteCount = voteCount;
        mOverView = overView;
        mPosterPath = posterPath;
        mBackdropPath = backdropPath;
        mReleaseDate = releaseDate;
    }

    public long getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public float getPopularity() {
        return mPopularity;
    }

    public float getVoteAverage() {
        return mVoteAverage;
    }

    public int getVoteCount() {
        return mVoteCount;
    }

    public String getOverView() {
        return mOverView;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public long getReleaseDate() {
        return mReleaseDate;
    }
}
