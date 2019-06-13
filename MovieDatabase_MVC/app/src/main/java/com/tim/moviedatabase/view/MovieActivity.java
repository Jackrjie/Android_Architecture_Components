package com.tim.moviedatabase.view;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.tim.moviedatabase.R;
import com.tim.moviedatabase.model.Movie;

public class MovieActivity extends AppCompatActivity {

    private Movie mMovie;
    private ImageView imgMovieLarge;
    private RatingBar ratingBar;
    private TextView tvTitle,tvRate,tvReleaseDate,tvDescription,tvPopularity;
    private static final String TAG = "testing123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findView();

        Intent intent = getIntent();
        if (intent.hasExtra("movie")){
            mMovie = getIntent().getParcelableExtra("movie");
            getSupportActionBar().setTitle(mMovie.getTitle());

            double d = (mMovie.getVoteAverage())/2;
            float rate = (float)d;
            ratingBar.setRating(rate);

            String image = mMovie.getPosterPath();
            String imagePath = "https://image.tmdb.org/t/p/w500" + image;

            Glide.with(this)
                    .load(imagePath)
                    .placeholder(R.drawable.loading)
                    .into(imgMovieLarge);

            String msg;
            if (mMovie.getAdult()){
                msg = "18";
            } else {
                msg = "U";
            }
            String title = mMovie.getOriginalTitle() + " ( " + msg + " )";
            tvTitle.setText(title);

            String vote = "Rate: " + mMovie.getVoteAverage().toString() + " out of 10\n( " + mMovie.getVoteCount() + " voted )";
            tvRate.setText(vote);

            String date = "Release date:\n" + mMovie.getReleaseDate();
            tvReleaseDate.setText(date);

            tvDescription.setText(mMovie.getOverview());
            tvPopularity.setText(String.valueOf(mMovie.getPopularity()));

        }
    }

    private void findView() {
        imgMovieLarge = findViewById(R.id.img_movie_large);
        tvTitle = findViewById(R.id.tv_movie_title);
        tvRate = findViewById(R.id.tv_movie_rating);
        tvReleaseDate = findViewById(R.id.tv_release_date);
        tvDescription = findViewById(R.id.tv_movie_description);
        tvPopularity = findViewById(R.id.tv_popularity);
        ratingBar = findViewById(R.id.rb_movie_rate);
    }

}
