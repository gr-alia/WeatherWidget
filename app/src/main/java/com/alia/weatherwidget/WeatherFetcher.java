package com.alia.weatherwidget;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WeatherFetcher {
    private static final String TAG = "WeatherFetcher";
    private static final String API_KEY = "b121b64aff49a1f3718e20fbea0ef0f4";
    private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather/";

    public byte[] getUrlBytes(String urlSpec) throws IOException {
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();
            //Gets the status code from an HTTP response message. OK=200
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() +
                        ": with " +
                        urlSpec);
            }
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            //Reads 1024 of bytes from the input stream and stores them into the buffer array
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();
        } finally {
            connection.disconnect();
        }
    }

    public String getUrlString(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }

    public List<WeatherItem> fetchItems() {
        List<WeatherItem> items = new ArrayList<>();
        try {
            String url = Uri.parse(API_URL)
                    .buildUpon()        // to obtain a builder representing an existing URI
                    .appendQueryParameter("id", "703448")       //Kyiv id
                    .appendQueryParameter("APPID", API_KEY)
                    .appendQueryParameter("units", "metric")
                    .build().toString();
            String jsonString = getUrlString(url);
            Log.i(TAG, "Received JSON: " + jsonString);
            JSONObject jsonBody = new JSONObject(jsonString);
            parseItems(items, jsonBody);

        } catch (JSONException je) {
            Log.e(TAG, "Failed to parse JSON", je);
        } catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch items", ioe);
        }
        return items;
    }

    private void parseItems(List<WeatherItem> items, JSONObject jsonBody)
            throws IOException, JSONException {

        JSONObject mainJsonObject = jsonBody.getJSONObject("main");
        String temp = mainJsonObject.getString("temp_min");
        JSONArray weatherJsonArray = jsonBody.getJSONArray("weather");
        JSONObject innerJsonObject = weatherJsonArray.getJSONObject(0);
        String description = innerJsonObject.getString("description");

        WeatherItem item = new WeatherItem();
        item.setTemp(temp);
        item.setDescription(description);
        items.add(item);
    }
}