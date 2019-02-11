package com.lin.request.interceptor;

import android.util.Log;

import okhttp3.logging.HttpLoggingInterceptor;

public class HttpLoggerInterceptor {
    public static HttpLoggingInterceptor getLoggerInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new InterceptorLogger());
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    public static class InterceptorLogger implements HttpLoggingInterceptor.Logger {

        @Override
        public void log(String message) {
            Log.i("http", message);
        }
    }

}
