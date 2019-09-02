package com.xtx.retrofit2rxjavademo.network;

import com.xtx.retrofit2rxjavademo.network.request.Request;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkManager {
    private static NetworkManager mInstance;
    private static Retrofit mRetrofit;
    private static volatile Request mRequest = null;//volatile 修饰符,禁止指令重排序”

    public static NetworkManager getInstance() {
        if (mInstance == null) {
            synchronized (NetworkManager.class) {
                if (mInstance == null) {
                    mInstance = new NetworkManager();
                }
            }

        }
        return mInstance;
    }

    /**
     * 初始化对象和参数
     */
    public  void init() {
        OkHttpClient client =new OkHttpClient.Builder()
                .build();

        mRetrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(Request.HOST)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Request getRequest() {
        if(mRequest==null) {
            synchronized (Request.class) {
                mRequest = mRetrofit.create(Request.class);
            }
        }

        return mRequest;
    }
}
