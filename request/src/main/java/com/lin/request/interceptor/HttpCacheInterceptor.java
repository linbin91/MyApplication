package com.lin.request.interceptor;

import android.util.Log;

import com.lin.request.utils.AppContextUtils;
import com.lin.request.utils.NetworkUtils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HttpCacheInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!NetworkUtils.isConnected(AppContextUtils.getContext())){
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
            Log.e("http", "no network");
        }

        Response originalResponse = chain.proceed(request);
        //参考https://www.jianshu.com/p/9c3b4ea108a7
        if (NetworkUtils.isConnected(AppContextUtils.getContext())) {
            //有网的时候读接口上的@Headers里的配置,统一配置
            String cacheControl = request.cacheControl().toString();
            return originalResponse.newBuilder()
                    .header("Cache-Control",cacheControl)
                    .removeHeader("Pragma")
                    .build();
        }else {
            return originalResponse.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=2419200")
                    .removeHeader("Pragma")
                    .build();
        }
    }
}

/**
 * @Headers("Cache-Control: public, max-age=3600)
 * @GET("merchants/{shopId}/icon")
 * Observable<ShopIconEntity> getShopIcon(@Path("shopId") long shopId);
 */
