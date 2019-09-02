package com.xtx.retrofit2rxjavademo.network.response;

import android.util.Log;

import com.xtx.retrofit2rxjavademo.network.exception.ApiException;
import com.xtx.retrofit2rxjavademo.network.exception.CustomException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

/**
 * Create by tabsam on 2019/8/29
 * Describe:对返回的数据进行处理,包括异常情况
 */
public class ResponseTransformer {
    public static final String TAG = "ResponseTransformer";
    public static <T> ObservableTransformer<Response<T>, T> handleResult() {
        Log.d(TAG,"handleResult");

        return upstream -> upstream
                .onErrorResumeNext(new ErrorResumeFunction<>())
                .flatMap(new ResponseFunction<>());
    }


    /**
     * 非服务器产生的异常，比如本地无网络请求，Json数据解析错误等等。
     */
    private static class ErrorResumeFunction<T> implements Function<Throwable, ObservableSource<? extends Response<T>>> {

        @Override
        public ObservableSource<? extends Response<T>> apply(Throwable throwable) throws Exception {
            Log.d(TAG,"ErrorResumeFunction apply,e="+throwable.getMessage());
            return Observable.error(CustomException.handleException(throwable));
        }
    }

    /**
     * 服务其返回的数据解析
     * 正常服务器返回数据和服务器可能返回的exception
     *
     * @param <T>
     */
    private static class ResponseFunction<T> implements Function<Response<T>, ObservableSource<T>> {

        @Override
        public ObservableSource<T> apply(Response<T> tResponse) throws Exception {
            int code = tResponse.getRetcode();
            String msg = tResponse.getMsg();
            Log.d(TAG,"apply code="+code+", msg="+msg+", data="+tResponse.getData());
            if (code == 200) {
                return Observable.just(tResponse.getData());
            } else {
                return Observable.error(new ApiException(code, msg));
            }
        }
    }
}
