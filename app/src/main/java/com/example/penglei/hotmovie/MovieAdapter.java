package com.example.penglei.hotmovie;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by penglei on 18-1-30.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.VH> {
    private String[] mData;
    private final MovieAdapterOnClickHandler mOnClickHandler;

    public MovieAdapter(MovieAdapterOnClickHandler onClickHandler) {
        mOnClickHandler = onClickHandler;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_item, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.mMovieTextView.setText(mData[position]);
    }

    @Override
    public int getItemCount() {
        if (mData == null) return 0;
        return mData.length;
    }

    public void setData(String[] data) {
        mData = data;
        notifyDataSetChanged();
    }

    interface MovieAdapterOnClickHandler {
        void onClick(String movie);
    }

    class VH extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView mMovieTextView;

        public VH(View itemView) {
            super(itemView);
            mMovieTextView = itemView.findViewById(R.id.tv_movie_data);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnClickHandler.onClick(mData[getAdapterPosition()]);
        }
    }
}
