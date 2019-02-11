package com.lin.myapplication.base;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.trello.rxlifecycle3.LifecycleTransformer;
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import io.reactivex.annotations.Nullable;

public abstract class BaseActivity <T extends BaseContract.BasePresenter> extends RxAppCompatActivity implements BaseContract.BaseView{
    private static final String TAG = BaseActivity.class.getSimpleName();
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, String.format("%s:onCreate", this));
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
        }
        ButterKnife.bind(this);
        attachView();
        initView();
        initData();
        applyEvent();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        detachView();
    }

    /**
     * 返回当前界面布局文件
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化View
     */
    protected void initView(){}

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 设置事件监听
     */
    protected void applyEvent(){}


    /**
     * 贴上view
     */
    private void attachView() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    /**
     * 分离view
     */
    private void detachView() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.bindToLifecycle();
    }

    @Override
    public void showSuccess(String successMsg) {

    }

    @Override
    public void showFaild(String errorMsg) {

    }

    @Override
    public void showNoNet() {

    }

    @Override
    public void onRetry() {

    }


    public void startActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void startActivityForResult(Class<?> cls, int requestCode) {
        Intent intent = new Intent(this, cls);
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void jumpToLogin() {

    }
}
