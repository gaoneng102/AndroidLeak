package com.gaoneng.androidleak;

import android.content.Context;

/**
 * Created by gaoneng on 17-7-4.
 */

public class Singleton {
    private static Singleton mInstance;
    private Context context;

    private Singleton(Context context) {
        this.context = context;
    }

    public static Singleton getInstance(Context context) {
        if (mInstance == null) {
            synchronized (Singleton.class) {
                if (mInstance == null) {
                    mInstance = new Singleton(context);
                }
            }
        }
        return mInstance;
    }
}
