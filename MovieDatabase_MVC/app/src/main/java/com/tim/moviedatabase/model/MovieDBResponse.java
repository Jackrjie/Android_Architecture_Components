package com.tim.moviedatabase.model;

/**
 * create by Tim on 6/11/2019
 * email: jackrjie@gmail.com
 */
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieDBResponse implements Parcelable
{

    @SerializedName("page")
    @Expose
    private Integer page;

    @SerializedName("total_results")
    @Expose
    private Integer totalMovies;

    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;

    @SerializedName("results")
    @Expose
    private List<Movie> movies = null;
    public final static Parcelable.Creator<MovieDBResponse> CREATOR = new Creator<MovieDBResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public MovieDBResponse createFromParcel(Parcel in) {
            return new MovieDBResponse(in);
        }

        public MovieDBResponse[] newArray(int size) {
            return (new MovieDBResponse[size]);
        }

    }
            ;

    protected MovieDBResponse(Parcel in) {
        this.page = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.totalMovies = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.totalPages = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.movies, (com.tim.moviedatabase.model.Movie.class.getClassLoader()));
    }

    public MovieDBResponse() {
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalMovies() {
        return totalMovies;
    }

    public void setTotalMovies(Integer totalMovies) {
        this.totalMovies = totalMovies;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> results) {
        this.movies = results;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(page);
        dest.writeValue(totalMovies);
        dest.writeValue(totalPages);
        dest.writeList(movies);
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return "MovieDBResponse{" +
                "page=" + page +
                ", totalMovies=" + totalMovies +
                ", totalPages=" + totalPages +
                ", movies=" + movies +
                '}';
    }
}