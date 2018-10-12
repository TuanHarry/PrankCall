package com.example.dev97.tuantran.prankcall;

import android.content.Context;

public class Application extends android.app.Application{
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }
}
