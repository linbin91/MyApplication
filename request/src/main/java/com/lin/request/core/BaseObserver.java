package com.lin.request.core;

import android.content.Context;
import android.net.ParseException;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.gson.JsonParseException;
import com.lin.request.dialog.ProgressDialogUtils;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BaseObserver<T extends BaseResponseEntity> implements Observer<T> {
    /**
     * dialog 显示文字
     */
    private String mMsg;
    private ProgressDialogUtils progressDialogUtils;
    private Context mContext;
    private boolean mShowLoading = false;

    /**
     * token失效 发送广播标识
     */
    public static final String TOKEN_INVALID_TAG = "token_invalid";
    public static final String QUIT_APP = "quit_app";

    private static final String CONNECT_ERROR = "网络连接失败,请检查网络";
    private static final String CONNECT_TIMEOUT = "连接超时,请稍后再试";
    private static final String BAD_NETWORK = "服务器异常";
    private static final String PARSE_ERROR = "解析服务器响应数据失败";
    private static final String UNKNOWN_ERROR = "未知错误";
    private static final String RESPONSE_RETURN_ERROR = "服务器返回数据失败";

    public BaseObserver() {

    }


    public BaseObserver(Context context, boolean isShow) {
        this.mContext = context;
        this.mShowLoading = isShow;
    }

    public BaseObserver(Context context, boolean isShow, String msg) {
        this.mContext = context;
        this.mShowLoading = isShow;
        this.mMsg = msg;
    }

    @Override
    public void onSubscribe(Disposable d) {
        onRequestStart();
    }

    private void onRequestStart(){
        if (mShowLoading) {
            showProgressDialog();
        }
    }

    private void showProgressDialog() {
        progressDialogUtils = new ProgressDialogUtils();
        if (TextUtils.isEmpty(mMsg)) {
            progressDialogUtils.showProgress(mContext);
        }else{
            progressDialogUtils.showProgress(mContext,mMsg);
        }
    }

    @Override
    public void onNext(T response) {
        onRequestEnd();
        if (response.success()) {
            try {
                onSuccess(response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            try{
                onFailing(response);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onComplete() {
        onRequestEnd();
    }



    @Override
    public void onError(Throwable e) {
        onRequestEnd();
        if (e instanceof retrofit2.HttpException) {
            //HTTP错误
            onException(ExceptionReason.BAD_NETWORK);
        } else if (e instanceof ConnectException || e instanceof UnknownHostException) {
            //连接错误
            onException(ExceptionReason.CONNECT_ERROR);
        } else if (e instanceof InterruptedIOException) {
            //连接超时
            onException(ExceptionReason.CONNECT_TIMEOUT);
        } else if (e instanceof JsonParseException || e instanceof JSONException || e instanceof ParseException) {
            //解析错误
            onException(ExceptionReason.PARSE_ERROR);
        } else {
            //其他错误
            onException(ExceptionReason.UNKNOWN_ERROR);
        }
    }

    private void onException(ExceptionReason reason) {
        switch (reason) {
            case CONNECT_ERROR:
                Toast.makeText(mContext, CONNECT_ERROR, Toast.LENGTH_SHORT).show();
                break;

            case CONNECT_TIMEOUT:
                Toast.makeText(mContext, CONNECT_TIMEOUT, Toast.LENGTH_SHORT).show();
                break;

            case BAD_NETWORK:
                Toast.makeText(mContext, BAD_NETWORK, Toast.LENGTH_SHORT).show();
                break;

            case PARSE_ERROR:
                Toast.makeText(mContext, PARSE_ERROR, Toast.LENGTH_SHORT).show();
                break;

            case UNKNOWN_ERROR:
            default:
                Toast.makeText(mContext, UNKNOWN_ERROR, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void onFailing(T response) {
        String message = response.msg;
        if (TextUtils.isEmpty(message)) {
            Toast.makeText(mContext, RESPONSE_RETURN_ERROR, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
        }
    }

    public abstract void onSuccess(T response);

    private void onRequestEnd() {
        closeProgressDialog();
    }

    private void closeProgressDialog() {
        if (progressDialogUtils != null) {
            progressDialogUtils.dismissProgress();
        }
    }

    /**
     * 网络请求失败原因
     */
    public enum ExceptionReason {
        /**
         * 解析数据失败
         */
        PARSE_ERROR,
        /**
         * 网络问题
         */
        BAD_NETWORK,
        /**
         * 连接错误
         */
        CONNECT_ERROR,
        /**
         * 连接超时
         */
        CONNECT_TIMEOUT,
        /**
         * 未知错误
         */
        UNKNOWN_ERROR
    }


}

