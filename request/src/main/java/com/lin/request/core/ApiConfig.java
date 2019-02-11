package com.lin.request.core;

import android.content.Context;
import android.util.ArrayMap;

import com.lin.request.config.SslSocketConfigure;
import com.lin.request.utils.AppContextUtils;

import java.io.Serializable;

public class ApiConfig implements Serializable {
    private static int mInvalidateToken;
    private static String mBaseUrl;
    private static int mDefaultTimeout = 2000;
    private static int mSucceedCode;
    private static String mQuitBroadcastReceiverFilter;
    private static ArrayMap<String, String> mHeads;
    private static String mToken = "";
    private static boolean mOpenHttps;
    private static SslSocketConfigure mSslSocketConfigure;
    private static int maxRetry;

    private ApiConfig(Builder builder) {
        mInvalidateToken = builder.invalidateToken;
        mBaseUrl = builder.baseUrl;
        mDefaultTimeout = builder.defaultTimeout;
        mSucceedCode = builder.succeedCode;
        mQuitBroadcastReceiverFilter = builder.broadcastFilter;
        mHeads = builder.heads;
        mOpenHttps = builder.openHttps;
        mSslSocketConfigure = builder.sslSocketConfigure;
        maxRetry = builder.maxRetry;
    }

    public void init(Context appContext) {
        AppContextUtils.init(appContext);
    }

    public static int getInvalidateToken() {
        return mInvalidateToken;
    }

    public static String getBaseUrl() {
        return mBaseUrl;
    }

    public static int getDefaultTimeout() {
        return mDefaultTimeout;
    }

    public static int getSucceedCode() {
        return mSucceedCode;
    }

    public static String getQuitBroadcastReceiverFilter() {
        return mQuitBroadcastReceiverFilter;
    }

    public static ArrayMap<String, String> getHeads() {
        return mHeads;
    }

    public static void setHeads(ArrayMap<String, String> mHeads) {
        ApiConfig.mHeads = mHeads;
    }


    public static String getToken() {
        return mToken;
    }

    public static void setToken(String mToken) {
        ApiConfig.mToken = mToken;
    }

    public static boolean getOpenHttps() {
        return mOpenHttps;
    }


    public static SslSocketConfigure getSslSocketConfigure() {
        return mSslSocketConfigure;
    }

    public static int getMaxRetry(){
        return maxRetry;
    }

    public static final class Builder  {

        private int invalidateToken;

        private String baseUrl;

        private int defaultTimeout;

        private int succeedCode;

        private String broadcastFilter;

        private ArrayMap<String, String> heads;

        private boolean openHttps = false;

        private SslSocketConfigure sslSocketConfigure;

        private int maxRetry;

        public Builder setHeads(ArrayMap<String, String> heads) {
            this.heads = heads;
            return this;
        }

        public Builder setFilter(String filter) {
            this.broadcastFilter = filter;
            return this;
        }


        public Builder setSucceedCode(int succeedCode) {
            this.succeedCode = succeedCode;
            return this;
        }

        public Builder setBaseUrl(String mBaseUrl) {
            this.baseUrl = mBaseUrl;
            return this;
        }

        public Builder setInvalidateToken(int invalidateToken) {
            this.invalidateToken = invalidateToken;
            return this;
        }

        public Builder setDefaultTimeout(int defaultTimeout) {
            this.defaultTimeout = defaultTimeout;
            return this;
        }

        public Builder setOpenHttps(boolean open) {
            this.openHttps = open;
            return this;
        }

        public Builder setSslSocketConfigure(SslSocketConfigure sslSocketConfigure) {
            this.sslSocketConfigure = sslSocketConfigure;
            return this;
        }

        public Builder setMaxRetry(int maxRetry) {
            this.maxRetry = maxRetry;
            return this;
        }


        public ApiConfig build() {
            return new ApiConfig(this);
        }
    }

}
