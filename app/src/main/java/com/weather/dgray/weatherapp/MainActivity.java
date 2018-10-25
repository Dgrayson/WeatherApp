package com.weather.dgray.weatherapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    JsonObjectRequest jsonObjectRequest;
    String baseUrl = "https://api.openweathermap.org/data/2.5/weather?q=";
    String jsonString = "";

    Intent intent;

    EditText cityName;
    Button goButton;

    RequestQueue queue;

    public static final String MESSAGE = "JSON";

    String url;
    ApiKey key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        key = new ApiKey();

        queue = Volley.newRequestQueue(this);

        cityName = findViewById(R.id.cityName);
        goButton = findViewById(R.id.goButton);

        intent = new Intent(this, CurrentWeather.class);



        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                url = baseUrl + cityName.getText() + "&appid=" + key.GetKey();

                jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        jsonString = response.toString();

                        intent.putExtra(MESSAGE, jsonString);
                        intent.putExtra("cityName", cityName.getText());

                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                queue.add(jsonObjectRequest);
            }
        });
    }
}