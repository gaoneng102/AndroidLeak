package com.gaoneng.androidleak;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SingletonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singleton);
    }

    public void setSingleton(View view) {
        Singleton.getInstance(this);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, SingletonActivity.class);
        context.startActivity(starter);
    }
}
