package com.lin.request.core;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.lin.request.cookie.PersistentCookieJar;
import com.lin.request.cookie.SetCookieCache;
import com.lin.request.cookie.SharedPrefsCookiePersistor;
import com.lin.request.interceptor.HttpCacheInterceptor;
import com.lin.request.interceptor.HttpHeaderInterceptor;
import com.lin.request.interceptor.HttpLoggerInterceptor;
import com.lin.request.interceptor.RetryIntercepter;
import com.lin.request.utils.AppContextUtils;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {
    private Retrofit.Builder builder;
    private Retrofit retrofit;

    private RetrofitFactory() {
        File cacheFile = new File(AppContextUtils.getContext().getCacheDir(), "HttpCache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100);

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient().newBuilder()
                .readTimeout(ApiConfig.getDefaultTimeout(), TimeUnit.MILLISECONDS)
                .connectTimeout(ApiConfig.getDefaultTimeout(),TimeUnit.MILLISECONDS)
                .addInterceptor(HttpLoggerInterceptor.getLoggerInterceptor())
                .addInterceptor(new HttpHeaderInterceptor())
                .addNetworkInterceptor(new HttpCacheInterceptor())
                .addInterceptor(new RetryIntercepter(ApiConfig.getMaxRetry()))
                .cookieJar(new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(AppContextUtils.getContext())))
                .cache(cache);

        OkHttpClient httpClient = httpClientBuilder.build();
        Gson gson = new Gson();

        builder = new Retrofit.Builder()
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        if (!TextUtils.isEmpty(ApiConfig.getBaseUrl())){
            retrofit = builder.baseUrl(ApiConfig.getBaseUrl()).build();
        }
    }

    private static class RetrofitFactoryHolder {
        private static final RetrofitFactory INSTANCE = new RetrofitFactory();
    }

    public static final RetrofitFactory getInstance() {
        return RetrofitFactoryHolder.INSTANCE;
    }

    /**
     * 根据Api接口类生成Api实体
     *
     * @param clazz 传入的Api接口类
     * @return Api实体类
     */
    public <T> T create(Class<T> clazz) {
        checkNotNull(retrofit, "BaseUrl not init,you should init first!");
        return retrofit.create(clazz);
    }

    /**
     * 根据Api接口类生成Api实体
     *
     * @param baseUrl baseUrl
     * @param clazz   传入的Api接口类
     * @return Api实体类
     */
    public <T> T create(String baseUrl, Class<T> clazz) {
        return builder.baseUrl(baseUrl).build().create(clazz);
    }

    private <T> T checkNotNull(T object, String message) {
        if (object == null) {
            throw new NullPointerException(message);
        }
        return object;
    }


}
