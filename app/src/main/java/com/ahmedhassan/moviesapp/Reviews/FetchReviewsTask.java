package com.ahmedhassan.moviesapp.Reviews;

import android.content.Context;
import android.os.AsyncTask;

import com.ahmedhassan.moviesapp.BuildConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Ahmed Hassan on 26/11/2016.
 */

public class FetchReviewsTask extends AsyncTask<String, Void, String> {
    DataReviews data;

    public void setDataReviews(DataReviews data) {
        this.data = data;
    }

    public Context context;

    private ArrayList<Reviews> getReviewsDataFromJson(String reviewsJsonStr)
            throws JSONException {
        JSONObject forecastJson = new JSONObject(reviewsJsonStr);
        JSONArray mreviewsArray = forecastJson.getJSONArray("results");
        ArrayList<Reviews> arrayList = new ArrayList<>();
        for (int i = 0; i < mreviewsArray.length(); i++) {
            String author;
            String content;
            JSONObject review = mreviewsArray.getJSONObject(i);
            author = review.getString("author");
            content = review.getString("content");
            Reviews reviews = new Reviews(author, content);
            arrayList.add(reviews);
        }
        return arrayList;
    }

    @Override
    protected String doInBackground(String... params) {
        if (params.length == 0) {
            return null;
        }
        HttpURLConnection urlConnection = null;
        BufferedReader bufferedReader = null;
        String reviewsjsonString = null;

        try {
            final String MOVIES_BASE_URL = "http://api.themoviedb.org/3/movie/" + params[0] + "/reviews?api_key=" +
                    BuildConfig.OPEN_Movies_API_KEY;

            URL besturl = new URL(MOVIES_BASE_URL);
            urlConnection = (HttpURLConnection) besturl.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                return null;
            }
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                buffer.append(line + "\n");
            }
            if (buffer.length() == 0) {
                return null;
            }
            reviewsjsonString = buffer.toString();
        } catch (IOException e) {
            return "";
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (final IOException e) {
                }
            }
        }
        return reviewsjsonString;
    }

    @Override
    protected void onPostExecute(String result) {
        try {
            if (data != null) {
                data.getReviews(getReviewsDataFromJson(result));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
