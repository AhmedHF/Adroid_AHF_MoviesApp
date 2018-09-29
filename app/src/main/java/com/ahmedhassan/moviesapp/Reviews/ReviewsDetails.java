package com.ahmedhassan.moviesapp.Reviews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.ahmedhassan.moviesapp.R;

import java.util.ArrayList;

public class ReviewsDetails extends AppCompatActivity implements DataReviews {
    ArrayList<Reviews> reviewsArrayList;
    ListView reviewslistView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews_details);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        reviewsArrayList = new ArrayList<>();
        reviewslistView = (ListView) findViewById(R.id.list_reviews_Details);
        FetchReviewsTask fetchReviewsTask = new FetchReviewsTask();
        fetchReviewsTask.setDataReviews(this);
        fetchReviewsTask.execute(id);
    }

    @Override
    public void getReviews(ArrayList<Reviews> reviewsArrayList) {
        this.reviewsArrayList = reviewsArrayList;
        ReviewsAdapter reviewsAdapter = new ReviewsAdapter(this, reviewsArrayList);
        reviewslistView.setAdapter(reviewsAdapter);
    }
}
