package com.tim.moviedatabase.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.bumptech.glide.Glide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.tim.moviedatabase.R;
import com.tim.moviedatabase.databinding.ActivityMovieBinding;
import com.tim.moviedatabase.model.Movie;

import static com.tim.moviedatabase.R.color.colorAccent;

public class MovieActivity extends AppCompatActivity {

    private Movie mMovie;
    private ActivityMovieBinding mMovieBinding;
    private static final String TAG = "testing123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        mMovieBinding = DataBindingUtil.setContentView(this,R.layout.activity_movie);
        setSupportActionBar(mMovieBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        if (intent.hasExtra("movie")){
            mMovie = getIntent().getParcelableExtra("movie");
            getSupportActionBar().setTitle(mMovie.getOriginalTitle());
            mMovieBinding.setMovie(mMovie);
            double d = mMovie.getVoteAverage();
            float rate = (float)d/2;
            mMovieBinding.secondaryLayout.rbMovieRate.setNumStars(5);
            mMovieBinding.secondaryLayout.rbMovieRate.setRating(rate);

            String voteCount = "( " + mMovie.getVoteCount() + " voted )";
            mMovieBinding.secondaryLayout.tvVoteCount.setText(voteCount);

            if (mMovie.getAdult()){
                mMovieBinding.secondaryLayout.imgAdult.setImageResource(R.drawable.ic_rated_r);
            } else {
                mMovieBinding.secondaryLayout.imgAdult.setImageResource(R.drawable.ic_rated_pg);
            }

            if (mMovie.getPopularity()>= 250) {
                setPopularityText(48, "#fdd835");
            }

            double marks = mMovie.getVoteAverage();
            if (marks>=5.0){
                setRateText(36,"#fbc02d");
            }
            if (marks>=8.0){
                setRateText(48,"#ffff00");
            }
        }
    }

    private void setPopularityText(int textSize,String color){
        mMovieBinding.secondaryLayout.tvMoviePopularity.setTextSize(textSize);
        mMovieBinding.secondaryLayout.tvMoviePopularity.setTextColor(Color.parseColor(color));
    }

    private void setRateText(int textSize,String color){
        mMovieBinding.secondaryLayout.tvMovieRating.setTextSize(textSize);
        mMovieBinding.secondaryLayout.tvMovieRating.setTextColor(Color.parseColor(color));
    }
}
