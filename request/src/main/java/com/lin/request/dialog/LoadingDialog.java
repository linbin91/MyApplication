package com.lin.request.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lin.request.R;

public class LoadingDialog extends Dialog {

    private View mDialogView;
    private boolean cancelTouchOutside;
    private AnimationDrawable animationDrawable;

    public LoadingDialog(Builder builder) {
        super(builder.context);
        mDialogView = builder.mDialogView;
        cancelTouchOutside = builder.cancelTouchOutside;
    }

    private LoadingDialog(Builder builder, int themeResId) {
        super(builder.context, themeResId);
        mDialogView = builder.mDialogView;
        cancelTouchOutside = builder.cancelTouchOutside;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mDialogView);
        setCanceledOnTouchOutside(cancelTouchOutside);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (mDialogView == null) {
            return;
        }

        ImageView imageView = mDialogView.findViewById(R.id.iv_loading_img);
        animationDrawable = (AnimationDrawable) imageView.getBackground();
        animationDrawable.start();
    }

    public static final class Builder{
        private Context context;
        private int resStyle = -1;
        private View mDialogView;
        private boolean cancelTouchOutside;

        public Builder(Context context) {
            this.context = context;
            mDialogView = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);
        }

        public Builder setTheme(int resStyle) {
            this.resStyle = resStyle;
            return this;
        }

        public Builder setMessage(String message) {
            TextView tvMessage = mDialogView.findViewById(R.id.tv_loading_msg);
            if (tvMessage != null) {
                tvMessage.setText(message);
            }
            return this;
        }

        public Builder cancelTouchOutside(boolean cancel) {
            cancelTouchOutside = cancel;
            return this;
        }

        public LoadingDialog build() {
            if (resStyle != -1) {
                return new LoadingDialog(this, resStyle);
            } else {
                return new LoadingDialog(this);
            }
        }
    }
}
