package com.example.penglei.hotmovie;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Arrays;

/**
 * Created by penglei on 18-1-30.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.VH> {
    private String[] mData;

    public MovieAdapter() {
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_item, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
//        System.out.println(">>> onBindViewHolder:" + mData[position]);
        holder.mMovieTextView.setText(mData[position]);
    }

    @Override
    public int getItemCount() {
        System.out.println(">>> " + Arrays.toString(mData));
        if (mData == null) return 0;
        return mData.length;
    }

    public void setData(String[] data) {
        mData = data;
        notifyDataSetChanged();
    }

    static class VH extends RecyclerView.ViewHolder {
        public final TextView mMovieTextView;

        public VH(View itemView) {
            super(itemView);
            mMovieTextView = itemView.findViewById(R.id.tv_movie_data);
        }
    }
}
