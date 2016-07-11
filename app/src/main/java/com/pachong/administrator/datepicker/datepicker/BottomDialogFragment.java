package com.pachong.administrator.datepicker.datepicker;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;

import com.pachong.administrator.datepicker.R;

public abstract class BottomDialogFragment extends BaseDialogFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.MyDialog_Transparent);
        super.onCreate(savedInstanceState);
        setGravity(Gravity.BOTTOM);
    }


}
