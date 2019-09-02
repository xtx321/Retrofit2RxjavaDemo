package com.xtx.retrofit2rxjavademo.mvp;

import android.util.Log;

import com.xtx.retrofit2rxjavademo.bean.JavaBean;
import com.xtx.retrofit2rxjavademo.network.NetworkManager;
import com.xtx.retrofit2rxjavademo.network.response.Response;

import java.util.List;

import io.reactivex.Observable;

public class MyModel implements Contract.Model {
    public static final String TAG  = "MyModel";
    @Override
    public Observable<Response<List<JavaBean>>> getData() {
        Log.d(TAG,"getData");
        return  NetworkManager.getRequest().getList();
    }
}
