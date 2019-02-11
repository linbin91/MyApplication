package com.lin.myapplication.register;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lin.myapplication.R;
import com.lin.myapplication.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity<RegisterActivityImp> implements RegisterActivityContract.View {

    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.account)
    EditText mAccount;
    @BindView(R.id.password)
    EditText mPassword;
    @BindView(R.id.repassword)
    EditText mRepassword;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initData() {
        mTitle.setText(R.string.register);
        mBack.setVisibility(View.VISIBLE);
        mPresenter = new RegisterActivityImp(this);
    }

    @OnClick(R.id.regitster)
    void onClickRegister() {
        mAccount.setError(null);
        mPassword.setError(null);
        if (TextUtils.isEmpty(mAccount.getText())) {
            mAccount.setError(getString(R.string.input_account));
            mAccount.setFocusable(true);
            mAccount.setFocusableInTouchMode(true);
            mAccount.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(mPassword.getText())) {
            mPassword.setError(getString(R.string.input_password));
            mPassword.setFocusable(true);
            mPassword.setFocusableInTouchMode(true);
            mPassword.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(mRepassword.getText())) {
            mRepassword.setError(getString(R.string.input_repassword));
            mRepassword.setFocusable(true);
            mRepassword.setFocusableInTouchMode(true);
            mRepassword.requestFocus();
            return;
        }

        if (!TextUtils.equals(mPassword.getText(), mRepassword.getText())) {
            mRepassword.setError(getString(R.string.valid_repassword));
            return;
        }
        requestRegister();
    }

    private void requestRegister() {
        mPresenter.register(mAccount.getText().toString(),
                mPassword.getText().toString(),
                mRepassword.getText().toString());
    }

    @Override
    public void registerSuccess() {

    }

    @OnClick(R.id.back)
    void onClickBack() {
        finish();
    }

}
