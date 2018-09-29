package com.ahmedhassan.moviesapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Ahmed Hassan on 20/10/2016.
 */

public class MoviesAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Movies> moviesArrayList;

    public class ViewHolder {
        ImageView imageView;

        public ViewHolder(View convertView) {
            imageView = (ImageView) convertView.findViewById(R.id.img_id);
        }
    }

    public MoviesAdapter(Context context, ArrayList<Movies> arrayList) {
        this.context = context;
        moviesArrayList = arrayList;
    }

    @Override
    public int getCount() {
        return moviesArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return moviesArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movies movies = moviesArrayList.get(position);
        final ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.gridview_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String imageId = movies.getPosterPath();
        Picasso.with(context).load("http://image.tmdb.org/t/p/w185" + imageId).into(viewHolder.imageView);
        return convertView;
    }
}
