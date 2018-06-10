package com.example.a12965.mishopec;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.mi.app.ConfigType;
import com.example.mi.app.Mi;

import com.example.mi.delegates.MiDelegate;
import com.example.mi.net.RestClient;
import com.example.mi.net.callback.IError;
import com.example.mi.net.callback.IFailure;
import com.example.mi.net.callback.ISuccess;
import com.example.mi.net.rx.RxRestClient;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 12965 on 2018/3/8.
 */

public class ExampleDelegate extends MiDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(Bundle saveInstanceState, View rootView) {
        testNet();
//        testRxNet();
    }
    String name="yt";
    String number="888588710242830601";
    String key="d057453754f844e9b6772450a039e26e";
    String url = "exp/index?key=" + key
            + "&com=" + name + "&no=" + number;
    public void testRxNet(){
        RxRestClient.builder().url(url).build().get().
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.i("tag",s.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("eero",e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void testNet(){

        RestClient.builder().url(url)
                .loader(getContext()).
                success(new ISuccess() {
            @Override
            public void onSuccess(String response) {
                Log.i("tag",response.toString());
            }
        }).error(new IError() {
            @Override
            public void onError(int code, String msg) {
                Log.i("tag","failure"+code+msg.toString());
            }
        }).failure(new IFailure() {
            @Override
            public void onFailure() {
                Log.i("tag","failure");
            }
        }).build().getTruck();

    }

    @Override
    public void post(Runnable runnable) {

    }
}
