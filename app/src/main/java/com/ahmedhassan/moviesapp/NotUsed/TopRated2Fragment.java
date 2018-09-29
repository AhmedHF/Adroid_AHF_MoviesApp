package com.ahmedhassan.moviesapp.NotUsed;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
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
public class TopRated2Fragment extends Fragment implements Data {
    GridView gridView;
    ArrayList<Movies> moviesArrayList;

    public TopRated2Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_top_rated, container, false);
        gridView = (GridView) rootView.findViewById(R.id.gridview_TopRelated);
        moviesArrayList = new ArrayList<>();

        FetchMoviesTask task = new FetchMoviesTask();
        task.setData(this);
        task.execute("top_rated");
        return rootView;
    }

    @Override
    public void getdata(ArrayList<Movies> movies) {
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
