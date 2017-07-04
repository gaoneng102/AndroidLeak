package com.gaoneng.androidleak;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.lang.ref.WeakReference;

public class HandlerActivity extends AppCompatActivity {

    public static final long DELAY_MILLIS = 10000;//注意这里的时间大小对捕获内存泄露是有影响的

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
    }

    public void setHandler(View view) {
        new Handler().sendEmptyMessageDelayed(0, Integer.MAX_VALUE);
//       new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//            }
//        }.sendEmptyMessageDelayed(0, Integer.MAX_VALUE);

//        new MyHandler(this).post(new Runnable() {
//            @Override
//            public void run() {
//                Log.d(MainActivity.TAG, "Runable in Handler Finish!!!");
//            }
//        });
    }

    public void setInnerHandler(View view) {
        findViewById(R.id.btn1).postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(MainActivity.TAG, "Runable in view's Handler Finish!!!");
            }
        }, DELAY_MILLIS);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void handleMessage(Message msg) {
        Log.d(MainActivity.TAG, "handleMessage in MyHandler Finish!!!");
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, HandlerActivity.class);
        context.startActivity(starter);
    }

    private static class MyHandler extends Handler {
        private WeakReference<HandlerActivity> reference;

        public MyHandler(HandlerActivity activity) {
            super();
            this.reference = new WeakReference<>(activity);
        }

        @Override
        public void dispatchMessage(Message msg) {
            HandlerActivity handlerActivity = reference.get();
            if (handlerActivity != null) {
                super.dispatchMessage(msg);
            }
        }

        @Override
        public void handleMessage(Message msg) {
            HandlerActivity handlerActivity = reference.get();
            if (handlerActivity != null) {
                handlerActivity.handleMessage(msg);
            }
        }
    }

    private static class MyRunable implements Runnable {

        @Override
        public void run() {
            Log.d(MainActivity.TAG, "Runable in view's Handler Finish!!!");
        }
    }
}
