package com.xtx.retrofit2rxjavademo.network.schedulers;

import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import io.reactivex.schedulers.Schedulers;

/**
 *
 * Create by tabsam on 2019/8/29
 * Describe:
 * 线程切换类
 */
public class SchedulerProvider implements BaseSchedulerProvider {

    @Nullable
    private static SchedulerProvider instance;

    // Prevent direct instantiation.
    private SchedulerProvider() {
    }

    public static synchronized SchedulerProvider getInstance() {
        if (instance == null) {
            instance = new SchedulerProvider();
        }
        return instance;
    }


    /**
     * 计算线程
     * @return
     */
    @Override
    public Scheduler computation() {

        return Schedulers.computation();
    }

    /**
     * IO线程
     * @return
     */
    @Override
    public Scheduler io() {
        return Schedulers.io();
    }

    /**
     * UI线程
     * @return
     */
    @Override
    public Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }

    /**
     * 执行线程切换
     * @param <T>
     * @return
     */
    @NonNull
    @Override
    public <T> ObservableTransformer<T, T> applySchedulers() {
        return observable -> observable.subscribeOn(io())
                .observeOn(ui());
    }
}
