package com.gaoneng.androidleak;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class InnerActivity extends AppCompatActivity {
    private static InnerClass innerClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inner);
    }

    public void setInnerClass(View view) {
        innerClass = new InnerClass();
    }

    public void setAnonymousClass(View view) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                while (true) ;
            }
        }.execute();
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, InnerActivity.class);
        context.startActivity(starter);
    }

    private class InnerClass {

    }
}
