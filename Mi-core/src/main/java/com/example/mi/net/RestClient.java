package com.example.mi.net;

import android.content.Context;

import com.example.mi.net.callback.IError;
import com.example.mi.net.callback.IFailure;
import com.example.mi.net.callback.IRequest;
import com.example.mi.net.callback.ISuccess;
import com.example.mi.net.callback.RequestCallback;
import com.example.mi.net.download.DownloadHandler;
import com.example.mi.ui.loader.LoaderStyle;
import com.example.mi.ui.loader.MiLoader;


import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;


import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by jian
 */

public class RestClient {

    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final String URL;
    private final IRequest REQUEST;
    private final String DOWNLOAD_DIR; //下载后存储路径
    private final String EXTENSION; //后缀名
    private final String NAME;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;
  private final LoaderStyle LOADER_STYLE;
    private final File FILE;
    private final Context CONTEXT;

    RestClient(String url,
               Map<String, Object> params,
               String downloadDir,
               String extension,
               String name,
               IRequest request,
               ISuccess success,
               IFailure failure,
               IError error,
               RequestBody body,
               File file,
               Context context,LoaderStyle loaderStyle) {
        this.URL = url;
        PARAMS.putAll(params);
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
        this.FILE = file;
        this.CONTEXT = context;
       this.LOADER_STYLE = loaderStyle;
    }
    private void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;

        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        if (LOADER_STYLE != null) {
            MiLoader.showLoading(CONTEXT, LOADER_STYLE);
        }

        switch (method) {
            case GET_TRUCK:
                call = service.getTurck(URL, PARAMS);
                break;
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case POST_RAW:
                call = service.postRaw(URL, BODY);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case PUT_RAW:
                call = service.putRaw(URL, BODY);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                call = service.upload(URL, body);
                break;
            default:
                break;
        }

        if (call != null) {
            call.enqueue(getRequestCallback());
        }
    }
   public static RestClientBuilder builder(){
        return new RestClientBuilder();
   }

    public final void get() {
        request(HttpMethod.GET);
    }
    public final void getTruck() {
        request(HttpMethod.GET_TRUCK);
    }
    public final void post() {
        if (BODY == null) {
            request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.POST_RAW);
        }
    }

    public final void put() {
        if (BODY == null) {
            request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.PUT_RAW);
        }
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }

    private Callback<String> getRequestCallback(){

       return new RequestCallback( REQUEST,
               SUCCESS,
               FAILURE,
               ERROR,LOADER_STYLE

               );
   }

    public final void upload() {
        request(HttpMethod.UPLOAD);
    }

    public final void download() {
        new DownloadHandler(URL, REQUEST, DOWNLOAD_DIR, EXTENSION, NAME,
                SUCCESS, FAILURE, ERROR)
                .handleDownload();
    }

}
