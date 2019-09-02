package com.xtx.retrofit2rxjavademo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.xtx.retrofit2rxjavademo.bean.JavaBean;
import com.xtx.retrofit2rxjavademo.mvp.Contract;
import com.xtx.retrofit2rxjavademo.mvp.MyPresenter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Contract.View {
    public static final String TAG = "MainActivity";

    private MyPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new MyPresenter(this);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRequest();
            }
        });
    }

    private void startRequest() {
        mPresenter.getData();
    }

    @Override
    public void onSuccess(List<JavaBean> list) {
        for (JavaBean javaBean  : list) {
            Log.d(TAG,"onSuccess: "+javaBean);
        }
    }

    @Override
    public void onFailed(Throwable e) {
        Log.d(TAG,"onFailed"+e.getMessage());
    }
}
