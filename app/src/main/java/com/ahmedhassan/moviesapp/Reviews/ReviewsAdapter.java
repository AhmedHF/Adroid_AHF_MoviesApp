package com.ahmedhassan.moviesapp.Reviews;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ahmedhassan.moviesapp.R;

import java.util.ArrayList;

/**
 * Created by Ahmed Hassan on 26/11/2016.
 */

public class ReviewsAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Reviews> reviewsArrayList;

    public ReviewsAdapter(Context context, ArrayList<Reviews> arrayList) {
        this.context = context;
        reviewsArrayList = arrayList;
    }

    public class ViewHolder {
        TextView textViewAuther;
        TextView textViewContent;

        public ViewHolder(View convertView) {
            textViewAuther = (TextView) convertView.findViewById(R.id.auther);
            textViewContent = (TextView) convertView.findViewById(R.id.content);
        }
    }

    @Override
    public int getCount() {
        return reviewsArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return reviewsArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Reviews reviews = reviewsArrayList.get(position);
        final ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item_reviews, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String name = reviews.getAuthor();
        String content = reviews.getContent();
        viewHolder.textViewAuther.setText(name);
        viewHolder.textViewContent.setText(content);
        return convertView;
    }
}
