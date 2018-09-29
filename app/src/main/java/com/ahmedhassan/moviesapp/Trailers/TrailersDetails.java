package com.ahmedhassan.moviesapp.Trailers;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ahmedhassan.moviesapp.R;

import java.util.ArrayList;

public class TrailersDetails extends AppCompatActivity implements DataTailers {
    ArrayList<Trailers> trailersArrayList;
    ListView trailerslistView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trailers_details);
        Intent intent = getIntent();
        String id= intent.getStringExtra("id");
        trailersArrayList = new ArrayList<>();
        trailerslistView = (ListView) findViewById(R.id.list_trailer_Details);
        FetchTrailersTask task = new FetchTrailersTask();
        task.setDataTrailers(this);
        task.execute(id);
    }

    @Override
    public void getTrailers(ArrayList<Trailers> trailers) {
        this.trailersArrayList = trailers;
        TrailersAdapter trailersAdapter = new TrailersAdapter(this, trailersArrayList);
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
