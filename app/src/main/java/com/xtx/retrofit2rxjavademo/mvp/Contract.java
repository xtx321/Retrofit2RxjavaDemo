package com.xtx.retrofit2rxjavademo.mvp;

import com.xtx.retrofit2rxjavademo.bean.JavaBean;
import com.xtx.retrofit2rxjavademo.network.response.Response;

import java.util.List;

import io.reactivex.Observable;

public interface Contract {
    interface View {
        /**
         * 成功回调
         *
         * @param list
         */
        void onSuccess(List<JavaBean> list);

        /**
         * 异常回调
         *
         * @param e
         */
        void onFailed(Throwable e);
    }

    interface Presenter {
        void getData();
    }

    interface Model {
        Observable<Response<List<JavaBean>>> getData();
    }
}
