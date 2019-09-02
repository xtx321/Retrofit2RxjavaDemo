package com.xtx.retrofit2rxjavademo;

import android.app.Application;

import com.xtx.retrofit2rxjavademo.network.NetworkManager;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NetworkManager.getInstance().init();
    }
}
