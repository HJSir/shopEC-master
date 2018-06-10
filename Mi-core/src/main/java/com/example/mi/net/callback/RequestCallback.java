package com.example.mi.net.callback;

import android.os.Handler;

import com.example.mi.app.ConfigType;
import com.example.mi.app.Mi;
import com.example.mi.net.RestCreator;
import com.example.mi.ui.loader.LoaderStyle;
import com.example.mi.ui.loader.MiLoader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jian
 */

public class RequestCallback implements Callback<String> {
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final LoaderStyle LOADER_STYLE;
    private static final Handler HANDLER = Mi.getHandler();
    public  RequestCallback(IRequest request, ISuccess success, IFailure failure, IError error,LoaderStyle style) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.LOADER_STYLE = style;
    }


    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if(response.isSuccessful()){
            if (call.isExecuted()) {
                if (SUCCESS != null) {
                    SUCCESS.onSuccess(response.body());
                }
            }
        }else {
            if (ERROR != null) {
                ERROR.onError(response.code(), response.message());
            }

        }

        onRequestFinish();
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE != null) {
            FAILURE.onFailure();
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }

        onRequestFinish();
    }

     /*
     结束加载动画
      */
    private void onRequestFinish() {
        final long delayed = Mi.getConfiguration(ConfigType.LOADER_DELAYED);
        if (LOADER_STYLE != null) {
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    RestCreator.getParams().clear();
                    MiLoader.stopLoading();
                }
            }, delayed);
        }

    }


}
