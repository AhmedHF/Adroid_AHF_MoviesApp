package com.ahmedhassan.moviesapp.NotUsed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.ahmedhassan.moviesapp.Data;
import com.ahmedhassan.moviesapp.DetailsActivity;
import com.ahmedhassan.moviesapp.FetchMoviesTask;
import com.ahmedhassan.moviesapp.Movies;
import com.ahmedhassan.moviesapp.MoviesAdapter;
import com.ahmedhassan.moviesapp.R;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class UpcomingFragment extends Fragment implements Data {
    GridView gridView;
    ArrayList<Movies> moviesArrayList;
    private NameListener nameListener;

    public UpcomingFragment() {
    }

    public void setNameListener(NameListener nameListener) {
        this.nameListener = nameListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_most_popular, container, false);
        gridView = (GridView) rootView.findViewById(R.id.gridview_most_popular);
        moviesArrayList = new ArrayList<>();

        FetchMoviesTask task = new FetchMoviesTask();
        task.setData(this);
        task.execute("upcoming");

        return rootView;
    }

    @Override
    public void getdata(final ArrayList<Movies> movies) {
        moviesArrayList = movies;

        MoviesAdapter adapter = new MoviesAdapter(getContext(), moviesArrayList);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                Movies movie = moviesArrayList.get(position);
                Intent intent = new Intent(getActivity(), DetailsActivity.class).putExtra("Movie", movie);
                startActivity(intent);
            }
        });
    }


}
