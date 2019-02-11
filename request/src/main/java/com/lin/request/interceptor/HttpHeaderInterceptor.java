package com.lin.request.interceptor;

import android.util.ArrayMap;

import com.lin.request.core.ApiConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HttpHeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        ArrayMap<String, String> heads = ApiConfig.getHeads();
        String token = ApiConfig.getToken();
        final Request.Builder authorization = originalRequest.newBuilder()
                .header("Content-type", "application/json")
                .header("Authorization", token)
                .addHeader("Connection", "close")
                .addHeader("Accept-Encoding", "identity");
        for (ArrayMap.Entry<String, String> entry : heads.entrySet()) {
            authorization.addHeader(entry.getKey(), entry.getValue());
        }

        Request build = authorization.build();
        return chain.proceed(build);
    }
}
