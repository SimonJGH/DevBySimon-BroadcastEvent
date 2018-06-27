package com.simon.broadcast;

import android.app.Application;

/**
 * Created by Simon on 2017/10/8.
 */
@SuppressWarnings("all")
public class MyApplication extends Application {

    private static MyApplication mContext;

    public static MyApplication getInstance() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;
    }
}
