package com.gaoneng.androidleak;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class StaticActivity extends AppCompatActivity {
    private static Activity activity;
    public static View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static);
    }

    public void setStaticActivity(View view) {
        activity = this;
    }

    public void setStaticView(View view) {
        StaticActivity.view = findViewById(R.id.btn1);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, StaticActivity.class);
        context.startActivity(starter);
    }
}
