package com.ahmedhassan.moviesapp.NotUsed;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ahmedhassan.moviesapp.R;

public class TopRated extends AppCompatActivity implements NameListener {
    boolean mIsTwoPane = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_rated);

/////inflate the MainFragment
//        MainFragment mostPopularFragment = new MainFragment();
//        //Set The Activity to be a listener to the Fragment
//        mostPopularFragment.setNameListener(this);
//        getSupportFragmentManager().beginTransaction().add(R.id.content_most_popular, mostPopularFragment, "").commit();
//        //Check if two pane
//        if (null != findViewById(R.id.content_details)) {
//            mIsTwoPane = true;
//        }
    }

    @Override
    public void setSelectedName(String id) {
//        // Case One Pane
//        //Start Details Activity
//        if (!mIsTwoPane) {
//            Intent i = new Intent(this, DetailsActivity.class);
////            i.putExtra("name", name);
//            startActivity(i);
//        } else {
//            //Case Two-PAne
//            DetailsActivityFragment detailsActivityFragment = new DetailsActivityFragment();
//            Bundle extras = new Bundle();
////            extras.putString("name", name);
//            detailsActivityFragment.setArguments(extras);
//            getSupportFragmentManager().beginTransaction().replace(R.id.content_details, detailsActivityFragment, "").commit();
//        }
    }
}
