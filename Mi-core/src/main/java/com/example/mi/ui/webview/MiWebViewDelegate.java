package com.example.mi.ui.webview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.mi.R;
import com.example.mi.R2;
import com.example.mi.delegates.MiDelegate;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by jian
 */

public class MiWebViewDelegate extends MiDelegate {
String title;
String url;
    @BindView(R2.id.tv_core_top_mid)
    TextView topMidText = null;
    @OnClick(R2.id.icon_core_top_left)
    void topFinsh() {
        getSupportDelegate().pop();

    }
@BindView(R2.id.mi_webView)
WebView webView=null;
    private void init(String url){

        //WebView加载web资源
        webView.loadUrl(url);
        webView.getSettings().setJavaScriptEnabled(true);//启用js
        webView.getSettings().setBlockNetworkImage(false);//解决图片不显示
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    public Object setLayout() {
        return R.layout.mi_webview;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        title=getArguments().getString("title");
        url=getArguments().getString("url");
        topMidText.setText(title);
            init(url);
    }

    @Override
    public void post(Runnable runnable) {

    }
}
