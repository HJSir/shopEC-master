package com.example.a12965.mishopec;

import android.app.Application;

import com.example.ec.database.DatabaseManager;
import com.example.ec.icon.FontEcModule;
import com.example.mi.app.Api;
import com.example.mi.app.Mi;
import com.example.mi.net.interceptors.DebugInterceptor;
import com.example.mi.net.interceptors.MoreBaseUrlInterceptor;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * Created by 12965 on 2018/3/8.
 */

public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Mi.init(this).
                withApiHost(Api.base_url)
                .withLoaderDelayed(1000)
                .withIcon(new FontEcModule())
                .withIcon(new FontAwesomeModule())
                .withInterceptor(new MoreBaseUrlInterceptor())
                .withInterceptor(new DebugInterceptor("test",R.raw.test))
                .withInterceptor(new DebugInterceptor("first",R.raw.indexstruct))
                .withInterceptor(new DebugInterceptor("LoadMore",R.raw.loadmore))
                .withInterceptor(new DebugInterceptor("sortlist",R.raw.sortlist))
                .withInterceptor(new DebugInterceptor("shoplist",R.raw.sortshoplist))
                .withInterceptor(new DebugInterceptor("cart",R.raw.shopcart))
                .withInterceptor(new DebugInterceptor("order",R.raw.order))
                .withInterceptor(new DebugInterceptor("adress",R.raw.adress))
                .withInterceptor(new DebugInterceptor("detail",R.raw.detail))
                .withInterceptor(new DebugInterceptor("blance",R.raw.blancedetail))
                .withInterceptor(new DebugInterceptor("bank",R.raw.bankcard))
                .withInterceptor(new DebugInterceptor("hdored",R.raw.xorder))
                .withInterceptor(new DebugInterceptor("search",R.raw.search))
                .withInterceptor(new DebugInterceptor("message",R.raw.message))
                .withInterceptor(new DebugInterceptor("news",R.raw.news))
                .withInterceptor(new DebugInterceptor("goodsnew",R.raw.goodsnew))
                .withInterceptor(new DebugInterceptor("activity",R.raw.activity))
                .withInterceptor(new DebugInterceptor("hot",R.raw.hot))
                .configure();
//初始化数据库管理
        DatabaseManager.getInstance().init(this);

    }
}
