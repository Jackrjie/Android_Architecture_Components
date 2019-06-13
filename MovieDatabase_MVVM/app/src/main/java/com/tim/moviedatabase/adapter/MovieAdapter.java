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
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.RvViewHolder>{

    private Context mContext;
    private ArrayList<Movie> mMovies;

    public MovieAdapter(Context context, ArrayList<Movie> movies) {
        mContext = context;
        mMovies = movies;
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
        Movie movie = mMovies.get(position);
        holder.mMovieBinding.setMovie(movie);
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
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
                        Movie selectedMovie = mMovies.get(position);
                        Intent intent = new Intent(mContext, MovieActivity.class);
                        intent.putExtra("movie",selectedMovie);
                        mContext.startActivity(intent);
                    }
                }
            });
        }
    }
}
