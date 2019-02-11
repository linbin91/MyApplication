package com.lin.request.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;



public class RetryIntercepter implements Interceptor {
    private int maxRetry;
    private int retryNum;

    public RetryIntercepter(int maxRetry) {
        this.maxRetry = maxRetry;
    }
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        while (!response.isSuccessful() && retryNum < maxRetry) {
            retryNum++;
            response = chain.proceed(request);
        }
        return response;
    }
}
