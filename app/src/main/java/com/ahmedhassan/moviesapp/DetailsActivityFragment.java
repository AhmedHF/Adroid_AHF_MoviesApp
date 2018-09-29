package com.ahmedhassan.moviesapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.ahmedhassan.moviesapp.Favorite.FavoritesDB;
import com.ahmedhassan.moviesapp.Reviews.DataReviews;
import com.ahmedhassan.moviesapp.Reviews.Reviews;
import com.ahmedhassan.moviesapp.Reviews.ReviewsAdapter;
import com.ahmedhassan.moviesapp.Reviews.ReviewsDetails;
import com.ahmedhassan.moviesapp.Trailers.DataTailers;
import com.ahmedhassan.moviesapp.Trailers.Trailers;
import com.ahmedhassan.moviesapp.Trailers.TrailersAdapter;
import com.ahmedhassan.moviesapp.Trailers.TrailersDetails;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailsActivityFragment extends Fragment implements DataReviews, DataTailers {
    ImageView imageView;
    TextView title;
    TextView overview;
    TextView releaseDate;
    TextView originalTitle;
    TextView rat;

    ToggleButton toggleButton;
    RecyclerView recyclerView;
    FavoritesDB favorites;

    ArrayList<Trailers> trailersArrayList;
    ListView trailerslistView;
    Button buttonTrailers;

    ArrayList<Reviews> reviewsArrayList;
    ListView reviewslistView;
    Button buttonReviews;

    DataReviews dataReviews;
    DataTailers dataTailers;

    public DetailsActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_details, container, false);
        final Movies movie = (Movies) getActivity().getIntent().getParcelableExtra("Movie");
        imageView = (ImageView) rootView.findViewById(R.id.d_image);

        originalTitle = (TextView) rootView.findViewById(R.id.d_originalTitle);
        overview = (TextView) rootView.findViewById(R.id.d_overview);
        releaseDate = (TextView) rootView.findViewById(R.id.d_releasedata);
        rat = (TextView) rootView.findViewById(R.id.d_rat);
        // recyclerView=(RecyclerView)rootView.findViewById(R.id.d_recyclerView);
        favorites = new FavoritesDB(this.getContext());


        originalTitle.setText(movie.getTitle().toString());
        overview.setText(movie.getOverview().toString());
        releaseDate.setText(movie.getRelease_date().toString());
        rat.setText(String.valueOf(movie.getRat()));

        Picasso.with(this.getContext()).load("http://image.tmdb.org/t/p/w780"
                + movie.getBackdrops()).into(imageView);


        toggleButton = (ToggleButton) rootView.findViewById(R.id.d_favorate);
        toggleButton.setChecked(false);
        if (favorites.isFavorite(movie)) {
            toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.favorite));
        } else {
            toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.unfavorite));
        }
        if (favorites.isFavorite(movie)) {
            toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.unfavorite));
                        favorites.deleteMovie(movie);
                    } else {
                        toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.favorite));
                    }
                }
            });

        } else {
            toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.favorite));
                        favorites.insertFavorite(movie);
                    } else {
                        toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.unfavorite));
                    }
                }
            });

        }

//        trailerslistView = (ListView) rootView.findViewById(R.id.list_trailer);
//        FetchTailersTask task = new FetchTailersTask(this.getContext(), trailerslistView);
//        task.execute(movie.getId());
        //===================
//        FetchMoviesTask task1=new FetchMoviesTask();
//        task1.setDataTailers(this.dataTailers);
//        task1.execute("284052/videos");
        //===================
//        FetchMoviesTask task2=new FetchMoviesTask();
//        task2.setDataReviews(this);
//        task2.execute("284052/reviews");

        //===================
//        trailersArrayList = new ArrayList<>();
//        trailerslistView = (ListView) rootView.findViewById(R.id.list_trailer);
//        FetchTrailersTask task = new FetchTrailersTask();
//        task.setDataTrailers(this);
//        task.execute(movie.getId());

        buttonTrailers = (Button) rootView.findViewById(R.id.buttonTrailers);
        buttonTrailers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TrailersDetails.class)
                        .putExtra("id", movie.getId());
                startActivity(intent);
            }
        });

//        reviewsArrayList = new ArrayList<>();
//        reviewslistView = (ListView) rootView.findViewById(R.id.list_reviews);
//        FetchReviewsTask fetchReviewsTask = new FetchReviewsTask();
//        fetchReviewsTask.setDataReviews(this);
//        fetchReviewsTask.execute(movie.getId());

        buttonReviews = (Button) rootView.findViewById(R.id.buttonReviews);
        buttonReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ReviewsDetails.class)
                        .putExtra("id", movie.getId());
                startActivity(intent);
            }
        });

        return rootView;
    }

    @Override
    public void getReviews(final ArrayList<Reviews> reviewsArrayList) {
        this.reviewsArrayList = reviewsArrayList;
        ReviewsAdapter reviewsAdapter = new ReviewsAdapter(this.getContext(), reviewsArrayList);
        reviewslistView.setAdapter(reviewsAdapter);
    }

    @Override
    public void getTrailers(final ArrayList<Trailers> trailers) {
        this.trailersArrayList = trailers;
        TrailersAdapter trailersAdapter = new TrailersAdapter(this.getContext(), trailersArrayList);
        trailerslistView.setAdapter(trailersAdapter);
        trailerslistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Trailers trailers1 = trailersArrayList.get(position);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v="
                        + trailers1.getKey()));
                startActivity(intent);
            }
        });
    }
}
