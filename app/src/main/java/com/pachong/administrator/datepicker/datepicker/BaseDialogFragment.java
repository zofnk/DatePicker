package com.pachong.administrator.datepicker.datepicker;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

import com.pachong.administrator.datepicker.R;


public abstract class BaseDialogFragment extends DialogFragment {

    protected float mDim = 1.0f;
    protected int mGravity;
    protected int mWidth = WindowManager.LayoutParams.MATCH_PARENT;
    protected int mHeight = WindowManager.LayoutParams.WRAP_CONTENT;
    private boolean mCanCanceledOutside = true;
    private boolean mHasMargin;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.MyDialog_Transparent);
        super.onCreate(savedInstanceState);
    }


    /**
     * 设置背景灰度
     *
     * @param dim
     */
    public void setDimAmount(float dim) {
        this.mDim = dim;
        Dialog dialog = getDialog();
        if (dialog != null && dialog.getWindow() != null) {
            Window window = getDialog().getWindow();
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.dimAmount = dim;
            window.setAttributes(lp);
        }
    }

    /**
     * 设置Dialog的位置
     *
     * @param gravity
     */
    public void setGravity(int gravity) {
        this.mGravity = gravity;
        if (getDialog() != null) {
            getDialog().getWindow().setGravity(gravity);
        }
    }

    public void setWidth(int width) {
        this.mWidth = width;
    }

    public void setHeight(int height) {
        this.mHeight = height;
    }

    public int show(FragmentTransaction transaction) {
        return super.show(transaction, getClass().getSimpleName());
    }

    public void show(FragmentManager manager) {
        super.show(manager, getClass().getSimpleName());
    }

    /**
     * 点击对话框外面，对话框自动消失
     *
     * @param canCanceled
     */
    public void setCanceledOnTouchOutside(boolean canCanceled) {
        this.mCanCanceledOutside = canCanceled;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().setCanceledOnTouchOutside(this.mCanCanceledOutside);

        Dialog dialog = getDialog();
        if (dialog != null && dialog.getWindow() != null){
            Window window = getDialog().getWindow();
            if (mHasMargin){
                Display display = window.getWindowManager().getDefaultDisplay();
                mWidth = (int) (display.getWidth() * 4.2 / 5);
            }
            window.setLayout(mWidth, mHeight);

        }
        setGravity(mGravity);
    }


    /**
     * 是否自动留边
     * @param hasMargin
     */
    public void setHasMargin(boolean hasMargin){
        this.mHasMargin = hasMargin;
    }

}
