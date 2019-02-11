package com.lin.myapplication.register;

import com.lin.myapplication.base.BaseContract;

public interface RegisterActivityContract {
    interface View extends BaseContract.BaseView{
        void registerSuccess();
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void register(String account, String password, String rePassword);
    }

}
