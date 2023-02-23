package com.example.tp5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity2 extends AppCompatActivity {
    TextView textFiled , tempFiled;
    String ville;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Bundle extras = getIntent().getExtras();
        ville= extras.getString("ville");
        textFiled = (TextView) findViewById(R.id.textfiled);
        textFiled.setText(ville);
    OkHttpClient client = new OkHttpClient();
    Request request = new Request.Builder()
            .url("http://api.weatherapi.com/v1/forecast.json?key=8384ce731c8c416892e111040231802&q=tunis&days=1&aqi=yes&alerts=yes").build();

        client.newCall(request).enqueue(new Callback() {
        @Override
        public void onFailure(okhttp3.Call call, IOException e) {
        }

        @Override
        public void onResponse(okhttp3.Call call, Response response) throws IOException {
            try {
                String responseData = response.body().string();
                JSONObject json = new JSONObject(responseData);
                tempFiled = (TextView) findViewById(R.id.tempFiled);

                String temperature = json.getJSONObject("current").getString("temp_c")+"°c";
                tempFiled.setText(temperature);
            } catch (JSONException e) {}
        }


    });

    }

}