package com.ahmedhassan.moviesapp;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

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
 * Created by Ahmed Hassan on 21/10/2016.
 */

public class FetchMoviesTask extends AsyncTask<String, Void, String> {
    Data data;

    public void setData(Data data) {
        this.data = data;
    }

    public Context context;

    private ArrayList<Movies> getMoviesDataFromJson(String moviesJsonStr)
            throws JSONException {
        JSONObject forecastJson = new JSONObject(moviesJsonStr);
        JSONArray moviesArray = forecastJson.getJSONArray("results");
        ArrayList<Movies> arrayList = new ArrayList<>();
        for (int i = 0; i < moviesArray.length(); i++) {
            String poster_path;
            String originalTitle;
            String title;
            String backdrops;
            double rat;
            String overview;
            String release_date;
            String id;

            JSONObject moviePoster = moviesArray.getJSONObject(i);
            poster_path = moviePoster.getString("poster_path");
            backdrops = moviePoster.getString("backdrop_path");
            originalTitle = moviePoster.getString("original_title");
            title = moviePoster.getString("title");
            rat = moviePoster.getDouble("vote_average");
            overview = moviePoster.getString("overview");
            release_date = moviePoster.getString("release_date");
            id = moviePoster.getString("id");
            Movies movies = new Movies(poster_path, originalTitle,
                    title, backdrops, rat, overview, release_date, id);
            arrayList.add(movies);
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
        String moviesjsonString = null;
        try {
            final String MOVIES_BASE_URL = "http://api.themoviedb.org/3/movie/" + params[0] + "?api_key=" +
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
            moviesjsonString = buffer.toString();
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
        Log.e("ahmed",moviesjsonString);
        return moviesjsonString;
    }

    @Override
    protected void onPostExecute(String result) {
        try {
            if (data != null) {
                data.getdata(getMoviesDataFromJson(result));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
