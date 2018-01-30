package com.example.penglei.hotmovie;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by penglei on 18-1-30.
 */

public class SimpleMovieAdapter extends RecyclerView.Adapter<SimpleMovieAdapter.VH> {
    private final String[] mData;

    public SimpleMovieAdapter(String[] data) {
        mData = data;
    }


    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VH(new TextView(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        TextView textView= (TextView) holder.itemView;
        textView.setText(mData[position]);
    }

    @Override
    public int getItemCount() {
        return mData.length;
    }

    static class VH extends RecyclerView.ViewHolder {
        public VH(View itemView) {
            super(itemView);
        }
    }
}
