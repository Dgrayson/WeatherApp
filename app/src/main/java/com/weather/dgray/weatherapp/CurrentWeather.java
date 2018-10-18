package com.weather.dgray.weatherapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CurrentWeather extends AppCompatActivity {

    String jsonString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_weather);

        Intent intent = getIntent();

        jsonString = intent.getStringExtra(MainActivity.MESSAGE);

        TextView cityNameTextView = findViewById(R.id.cityNameTextView);

        JsonParser parser = new JsonParser();

        JsonObject jObj = parser.parse(jsonString).getAsJsonObject();

        cityNameTextView.setText(jObj.get("name").toString());

    }
}
