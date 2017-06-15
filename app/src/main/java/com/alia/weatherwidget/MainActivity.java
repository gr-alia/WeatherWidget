package com.alia.weatherwidget;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView mTempMin;
    private TextView mTempMax;
    private TextView mDescription;

    private List<WeatherItem> mItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTempMin = (TextView) findViewById(R.id.textview_tempMin);
        mTempMax = (TextView) findViewById(R.id.textview_tempMax);
        mDescription = (TextView) findViewById(R.id.textview_desc);

        new FetchItemsTask().execute();
    }

    private class FetchItemsTask extends AsyncTask<Void, Void, List<WeatherItem>> {
        @Override
        protected List<WeatherItem> doInBackground(Void... params) {

            return new WeatherFetcher().fetchItems();
        }

        @Override
        protected void onPostExecute(List<WeatherItem> items) {
            mItems = items;
            for (int i = 0; i < mItems.size(); i++) {

                mTempMin.setText(mItems.get(i).getTempMin());
                mTempMax.setText(mItems.get(i).getTempMax());
                mDescription.setText(mItems.get(i).getDescription());

            }

        }
    }
}
