package com.xtx.retrofit2rxjavademo.mvp;

import com.xtx.retrofit2rxjavademo.network.response.ResponseTransformer;
import com.xtx.retrofit2rxjavademo.network.schedulers.SchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class MyPresenter implements Contract.Presenter {
    private final CompositeDisposable mDisposable;
    private       Contract.View       mView;
    private       MyModel             mMyModel;
    private       SchedulerProvider   mScheduler;

    public MyPresenter(Contract.View view) {
        mView = view;
        mMyModel = new MyModel();
        mScheduler = SchedulerProvider.getInstance();
        mDisposable = new CompositeDisposable();//管理rxjava订阅
    }

    /**
     * 处理
     */
    public void despose() {
        mDisposable.dispose();
    }

    @Override
    public void getData() {
        //compose rxjava 针对整个序列的变换, lambada表达式
        Disposable disposable = mMyModel.getData()
                .compose(ResponseTransformer.handleResult())//序列变换处理数据
                .compose(mScheduler.applySchedulers()) //线程切换处理
                .subscribe(javaBeans -> {
                    mView.onSuccess(javaBeans);//成功
                }, throwable -> {
                    mView.onFailed(throwable);//失败
                });

        mDisposable.add(disposable);
    }
}
