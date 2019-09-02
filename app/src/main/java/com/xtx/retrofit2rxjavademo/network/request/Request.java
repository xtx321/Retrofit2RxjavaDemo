package com.xtx.retrofit2rxjavademo.network.request;

import com.xtx.retrofit2rxjavademo.bean.JavaBean;
import com.xtx.retrofit2rxjavademo.network.response.Response;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface Request {
    public static final String HOST = "http://192.168.3.2:8080/";
    //http://192.168.3.2:8080/common.json

    @GET("common.json")
    Observable<Response<List<JavaBean>>> getList();

}
