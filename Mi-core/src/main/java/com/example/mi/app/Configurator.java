package com.example.mi.app;

import android.os.Handler;

import com.blankj.utilcode.util.Utils;
import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
 * Created by jian
 */

public final class Configurator {
    private static final Handler HANDLER = new Handler();
    private static final HashMap<Object, Object> MI_CONFIGS = new HashMap<>();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();
    public Configurator() {
        MI_CONFIGS.put(ConfigType.HANDLER, HANDLER);
        MI_CONFIGS.put(ConfigType.CONFIG_READY, false);


    }
    private void initIcons() {
        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }
    static Configurator getInstance() {

        return Holder.INSTANCE;
    }
//获取所有config
    public HashMap<Object, Object> getMiConfigs() {
        return MI_CONFIGS;
    }

//单例获取configrator实例
    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }
 //配置，
    public final void configure() {

        initIcons();
        MI_CONFIGS.put(ConfigType.CONFIG_READY, true);
        Utils.init(Mi.getApplicationContext());
    }
    public final Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }
//配置APIhost
    public final Configurator withApiHost(String host) {
        MI_CONFIGS.put(ConfigType.API_HOST, host);
        return this;
    }

    public final Configurator withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        MI_CONFIGS.put(ConfigType.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);
        MI_CONFIGS.put(ConfigType.INTERCEPTOR, INTERCEPTORS);
        return this;
    }
    public final Configurator withLoaderDelayed(long delayed) {
        MI_CONFIGS.put(ConfigType.LOADER_DELAYED, delayed);
        return this;
    }
 //检查是否配置，否则抛出异常
    public final void checkConfiguration() {
        final boolean isReady = (boolean) MI_CONFIGS.get(ConfigType.CONFIG_READY);
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready,please configure");
        }
    }
 //根据配置名获取单个配置信息
    final <T> T getSingleConfiguration(Object key){
        checkConfiguration();
        final Object value = MI_CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T) MI_CONFIGS.get(key);
    }
}
