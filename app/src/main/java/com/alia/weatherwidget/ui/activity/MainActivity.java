package com.alia.weatherwidget.ui.activity;


import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ImageView;
import android.widget.TextView;

import com.alia.weatherwidget.R;
import com.alia.weatherwidget.model.Main;
import com.alia.weatherwidget.model.Weather;
import com.alia.weatherwidget.network.RetrofitService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private TextView mViewTemp;
    private TextView mViewDescription;
    private ImageView mImageViewWeather;

    private List<Object> data = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewTemp = (TextView) findViewById(R.id.textview_temp);
        mViewDescription = (TextView) findViewById(R.id.textview_desc);
        mImageViewWeather = (ImageView) findViewById(R.id.weather_image_view);

        subscribe(RetrofitService.getInstance().getApi().getData("703448", "metric", "b121b64aff49a1f3718e20fbea0ef0f4"),
                weatherCollection -> {

                    data.addAll(weatherCollection.getWeather());

                    for (int i = 0; i < data.size(); i++) {
                        Weather weather = (Weather) data.get(i);
                        mViewDescription.setText(weather.getDescription());
                        mViewTemp.setText(weatherCollection.getMain().getTemp().toString());
                        Picasso.with(this)
                                .load(weather.getIconUrl())
                                .placeholder(R.drawable.some_img)
                                .into(mImageViewWeather);
                    }

                });

    }

}
