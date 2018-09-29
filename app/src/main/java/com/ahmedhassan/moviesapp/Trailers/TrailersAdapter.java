package com.ahmedhassan.moviesapp.Trailers;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmedhassan.moviesapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Ahmed Hassan on 25/11/2016.
 */

public class TrailersAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Trailers> trailersArrayList;

    public TrailersAdapter(Context context, ArrayList<Trailers> arrayList) {
        this.context = context;
        trailersArrayList = arrayList;
    }

    public class ViewHolder {
        ImageView imageView;
        TextView textView;

        public ViewHolder(View convertView) {
            textView = (TextView) convertView.findViewById(R.id.trailers_text);
            imageView = (ImageView) convertView.findViewById(R.id.trailers_img);
        }
    }

    @Override
    public int getCount() {
        return trailersArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return trailersArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Trailers trailers = trailersArrayList.get(position);
        final ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String name = trailers.getName();
        viewHolder.textView.setText(name);
        //viewHolder.imageView.setBackground();
        Picasso.with(context).load("http://img.youtube.com/vi/"+trailers.getKey()+"/mqdefault.jpg")
                .into(viewHolder.imageView);
        return convertView;
    }


}
