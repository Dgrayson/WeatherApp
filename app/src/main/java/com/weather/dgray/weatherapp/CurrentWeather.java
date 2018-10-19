package com.weather.dgray.weatherapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;

public class CurrentWeather extends AppCompatActivity {

    String jsonString = "";
    String imageUrl = "http://openweathermap.org/img/w/01n.png";
    Typeface weatherFont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_weather);

        weatherFont = Typeface.createFromAsset(getAssets(), "fonts/weathericons-regular-webfont.ttf");

        Intent intent = getIntent();

        jsonString = intent.getStringExtra(MainActivity.MESSAGE);

        TextView cityNameTextView = findViewById(R.id.cityNameTextView);
        TextView tempTextView = findViewById(R.id.tempTextView);
        TextView currentConditionsTextView = findViewById(R.id.currentConditionsTextView);
        TextView weatherIcon = findViewById(R.id.weatherIcon);

        weatherIcon.setTypeface(weatherFont);

        JsonParser parser = new JsonParser();

        JsonObject jObj = parser.parse(jsonString).getAsJsonObject();

        cityNameTextView.setText(jObj.get("name").toString());

        double currTemp = jObj.getAsJsonObject("main").get("temp").getAsDouble();
        currTemp = (currTemp - 273.15) * (9/5) + 32;

        tempTextView.setText(Double.toString(currTemp));

        weatherIcon.setText(Html.fromHtml("&#xf01e;"));



    }
}
