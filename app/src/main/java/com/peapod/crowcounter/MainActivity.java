package com.peapod.crowcounter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView crowText;
    int crows = 0;
    public static final String KEY = "crows";
    SharedPreferences setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        crowText = findViewById(R.id.countText);
        setting = getSharedPreferences(KEY, Context.MODE_PRIVATE);
        if (savedInstanceState != null) {
            crows = savedInstanceState.getInt(KEY);
            crowText.setText("Насчитано " + crows + " ворон");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (setting.contains(KEY)) {
            crows = setting.getInt(KEY,0);
            crowText.setText("Насчитано " + crows + " ворон");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = setting.edit();
        editor.putInt(KEY, crows);
        editor.apply();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY, crows);
    }

    public void count(View view) {
        crows++;
        crowText.setText("Насчитано " + crows + " ворон");
    }
}