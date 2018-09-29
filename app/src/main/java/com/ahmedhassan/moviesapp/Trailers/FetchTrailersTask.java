package com.ahmedhassan.moviesapp.Trailers;

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

public class FetchTrailersTask extends AsyncTask<String, Void, String> {
    DataTailers data;

    public void setDataTrailers(DataTailers data) {
        this.data = data;
    }

    public Context context;

    private ArrayList<Trailers> getTrailersDataFromJson(String trailersJsonStr)
            throws JSONException {
        JSONObject forecastJson = new JSONObject(trailersJsonStr);
        JSONArray mreviewsArray = forecastJson.getJSONArray("results");
        ArrayList<Trailers> arrayList = new ArrayList<>();
        for (int i = 0; i < mreviewsArray.length(); i++) {
            String name;
            String key;
            JSONObject review = mreviewsArray.getJSONObject(i);
            name = review.getString("name");
            key = review.getString("key");
            Trailers trailers = new Trailers(key, name);
            arrayList.add(trailers);
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

        String type = null;
        try {
            final String MOVIES_BASE_URL = "http://api.themoviedb.org/3/movie/" + params[0] + "/videos?api_key=" +
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
                data.getTrailers(getTrailersDataFromJson(result));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
