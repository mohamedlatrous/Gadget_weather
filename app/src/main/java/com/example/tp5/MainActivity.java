package com.example.tp5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    TextView textFiled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void meteo(View v) {
        Intent i = new Intent(this, MainActivity2.class);

        switch (v.getId()) {
            case (R.id.tunis):
                i.putExtra("ville", "tunis");
                startActivity(i);
                break;
            case (R.id.nabeul):
                i.putExtra("ville", "nabeul");
                startActivity(i);
                break;
            case (R.id.sfax):
                i.putExtra("ville", "sfax");
                startActivity(i);
                break;
            case (R.id.bizerte):
                i.putExtra("ville", "bizerte");
                startActivity(i);
                break;
        }

    }

}