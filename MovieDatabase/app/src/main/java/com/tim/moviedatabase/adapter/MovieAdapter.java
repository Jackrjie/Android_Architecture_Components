package com.tim.moviedatabase.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tim.moviedatabase.R;
import com.tim.moviedatabase.databinding.ListItemMovieBinding;
import com.tim.moviedatabase.model.Movie;
import com.tim.moviedatabase.view.MovieActivity;

import java.util.ArrayList;

/**
 * create by Tim on 6/11/2019
 * email: jackrjie@gmail.com
 */
public class MovieAdapter extends PagedListAdapter<Movie,MovieAdapter.RvViewHolder> {

    private Context mContext;

    public MovieAdapter(Context context) {
        super(Movie.CALLBACK);
        mContext = context;
    }

    @NonNull
    @Override
    public RvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemMovieBinding movieBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.list_item_movie,parent,false);
        return new RvViewHolder(movieBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RvViewHolder holder, int position) {
        Movie movie = getItem(position);
        holder.mMovieBinding.setMovie(movie);
    }

    public class RvViewHolder extends RecyclerView.ViewHolder {
        private ListItemMovieBinding mMovieBinding;

        public RvViewHolder(@NonNull ListItemMovieBinding movieBinding) {
            super(movieBinding.getRoot());
            this.mMovieBinding = movieBinding;

            mMovieBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    if (position != RecyclerView.NO_POSITION){
                        Movie selectedMovie = getItem(position);
                        Intent intent = new Intent(mContext, MovieActivity.class);
                        intent.putExtra("movie",selectedMovie);
                        mContext.startActivity(intent);
                    }
                }
            });
        }
    }
}
