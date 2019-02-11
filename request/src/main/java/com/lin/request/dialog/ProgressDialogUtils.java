package com.lin.request.dialog;

import android.content.Context;

import com.lin.request.R;

public class ProgressDialogUtils {

    private LoadingDialog loadingDialog;

    public void showProgress(Context context, String msg) {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog.Builder(context)
                    .setTheme(R.style.LoadingDialogStyle)
                    .setMessage(msg)
                    .build();
        }
        if (!loadingDialog.isShowing()) {
            loadingDialog.show();
        }
    }

    public void showProgress(Context context) {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog.Builder(context)
                    .setTheme(R.style.LoadingDialogStyle)
                    .build();
        }
        if (!loadingDialog.isShowing()) {
            loadingDialog.show();
        }
    }

    /**
     * 取消ProgressDialog
     */
    public void dismissProgress() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
            loadingDialog.cancel();
            loadingDialog=null;
        }
    }
}
