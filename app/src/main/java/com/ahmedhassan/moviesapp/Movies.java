package com.ahmedhassan.moviesapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Ahmed Hassan on 21/10/2016.
 */

public class Movies implements Parcelable {
    private String originalTitle;
    private String posterPath;
    private String backdrops;
    private String title;
    private double rat;
    private String overview;
    private String release_date;
    private String id;
    private ArrayList<String> trailers;
    private ArrayList<String> reviews;

    public Movies() {

    }

    public Movies(String posterPath, String originalTitle, String title, String backdrops,
                  double rat, String overview, String release_date, String id) {
        this.posterPath = posterPath;
        this.backdrops = backdrops;
        this.originalTitle = originalTitle;
        this.title = title;
        this.rat = rat;
        this.overview = overview;
        this.release_date = release_date;
        this.id = id;
    }

    public String getBackdrops() {
        return backdrops;
    }

    public void setBackdrops(String backdrops) {
        this.backdrops = backdrops;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = this.originalTitle;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = this.posterPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = this.title;
    }

    public double getRat() {
        return rat;
    }

    public void setRat(double rat) {
        this.rat = this.rat;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overView) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String releaseData) {
        this.release_date = release_date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<String> getTrailers() {
        return trailers;
    }

    public void setTrailers(ArrayList<String> trailers) {
        this.trailers = trailers;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(originalTitle);
        dest.writeString(overview);
        dest.writeString(posterPath);
        dest.writeString(backdrops);
        dest.writeString(title);
        dest.writeString(release_date);
        dest.writeDouble(rat);
        dest.writeString(id);
        dest.writeStringList(trailers);
        dest.writeStringList(reviews);
    }

    public static final Parcelable.Creator<Movies> CREATOR
            = new Parcelable.Creator<Movies>() {
        public Movies createFromParcel(Parcel in) {
            return new Movies(in);
        }

        public Movies[] newArray(int size) {
            return new Movies[size];
        }
    };

    private Movies(Parcel in) {
        originalTitle = in.readString();
        overview = in.readString();
        posterPath = in.readString();
        backdrops = in.readString();
        title = in.readString();
        release_date = in.readString();
        rat = in.readDouble();
        id = in.readString();
        trailers = in.readArrayList(null);
        reviews = in.readArrayList(null);
    }
}
