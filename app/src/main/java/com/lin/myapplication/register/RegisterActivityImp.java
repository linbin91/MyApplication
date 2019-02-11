package com.lin.myapplication.register;

import android.content.Context;
import android.widget.Toast;

import com.lin.myapplication.base.BasePresenter;
import com.lin.myapplication.net.ApiService;
import com.lin.request.core.BaseObserver;
import com.lin.request.core.BaseResponseEntity;
import com.lin.request.core.RetrofitFactory;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RegisterActivityImp extends BasePresenter<RegisterActivityContract.View> implements RegisterActivityContract.Presenter {

    private Context mContext;

    public RegisterActivityImp(Context context) {
        mContext = context;
    }

    @Override
    public void register(String account, String password, String rePassword) {
        RetrofitFactory.getInstance().create(ApiService.class).register(account,
                password,
                rePassword)
                .compose(mView.<BaseResponseEntity>bindToLife())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseResponseEntity>(mContext,true) {

                    @Override
                    public void onSuccess(BaseResponseEntity response) {
                        Toast.makeText(mContext,"Success",Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
