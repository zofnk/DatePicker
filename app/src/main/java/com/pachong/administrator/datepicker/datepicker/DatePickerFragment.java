package com.pachong.administrator.datepicker.datepicker;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.pachong.administrator.datepicker.R;

import java.lang.reflect.Field;
import java.util.Calendar;

public class DatePickerFragment extends BottomDialogFragment implements OnClickListener {


    private TextView mTvCancel;
    private TextView mTvAccept;

    //日期选择器
    private NumberPicker mYearPicker;
    private NumberPicker mMonthPicker;
    private NumberPicker mDayOfMonthPicker;

    private static String str1 = "1999";
    private static String str2 = "1";
    private static String str3 = "1";

    private Calendar mCalendar = Calendar.getInstance();

    @SuppressLint("ValidFragment")
    public DatePickerFragment(Calendar currDate) {
        super();
        setCurrentDate(currDate);
    }
    public DatePickerFragment() {
        super();
    }

    private OnDateSelectedListener mOnDateSelectedListener;

    public void setOnDateSelectedListener(OnDateSelectedListener l) {
        mOnDateSelectedListener = l;
    }

    public interface OnDateSelectedListener {
//    public void onCancelClick();

        public void onDateSelected(int year, int monthOfYear, int dayOfMonth);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.dialog_datepicker, container, false);
        mTvCancel = (TextView) layout.findViewById(R.id.dialog_dashboard_date_cancel);
        mTvAccept = (TextView) layout.findViewById(R.id.dialog_dashboard_date_accept);

        mYearPicker = (NumberPicker) layout.findViewById(R.id.np_year);
        mMonthPicker = (NumberPicker) layout.findViewById(R.id.np_month);
        mDayOfMonthPicker = (NumberPicker) layout.findViewById(R.id.np_day);

        mYearPicker.getChildAt(0).setFocusable(false);
        mMonthPicker.getChildAt(0).setFocusable(false);
        mDayOfMonthPicker.getChildAt(0).setFocusable(false);
        mYearPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        updateDateViews();
        try {
            setFormater();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        //为每个numberpicker设置监听器
        mYearPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                str1 = mYearPicker.getValue() + "";
                if (Integer.parseInt(str1) % 4 == 0
                        && Integer.parseInt(str1) % 100 != 0
                        || Integer.parseInt(str1) % 400 == 0) {
                    if (str2.equals("1") || str2.equals("3") || str2.equals("5") || str2.equals("7") || str2.equals("8") || str2.equals("10") || str2.equals("12")) {
                        mDayOfMonthPicker.setMaxValue(31);
                        mDayOfMonthPicker.setMinValue(1);
                    } else if (str2.equals("4") || str2.equals("6") || str2.equals("9") || str2.equals("11")) {
                        mDayOfMonthPicker.setMaxValue(30);
                        mDayOfMonthPicker.setMinValue(1);
                    } else {
                        mDayOfMonthPicker.setMaxValue(29);
                        mDayOfMonthPicker.setMinValue(1);
                    }

                } else {
                    if (str2.equals("1") || str2.equals("3") || str2.equals("5") || str2.equals("7") || str2.equals("8") || str2.equals("10") || str2.equals("12")) {
                        mDayOfMonthPicker.setMaxValue(31);
                        mDayOfMonthPicker.setMinValue(1);
                    } else if (str2.equals("4") || str2.equals("6") || str2.equals("9") || str2.equals("11")) {
                        mDayOfMonthPicker.setMaxValue(30);
                        mDayOfMonthPicker.setMinValue(1);
                    } else {
                        mDayOfMonthPicker.setMaxValue(28);
                        mDayOfMonthPicker.setMinValue(1);
                    }
                }

            }

        });

        mMonthPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                str2 = mMonthPicker.getValue() + "";
                if (str2.equals("1") || str2.equals("3") || str2.equals("5") || str2.equals("7") || str2.equals("8") || str2.equals("10") || str2.equals("12")) {
                    mDayOfMonthPicker.setMaxValue(31);
                    mDayOfMonthPicker.setMinValue(1);
                } else if (str2.equals("4") || str2.equals("6") || str2.equals("9") || str2.equals("11")) {
                    mDayOfMonthPicker.setMaxValue(30);
                    mDayOfMonthPicker.setMinValue(1);
                } else {
                    if (Integer.parseInt(str1) % 4 == 0
                            && Integer.parseInt(str1) % 100 != 0
                            || Integer.parseInt(str1) % 400 == 0) {
                        mDayOfMonthPicker.setMaxValue(29);
                        mDayOfMonthPicker.setMinValue(1);
                    } else {
                        mDayOfMonthPicker.setMaxValue(28);
                        mDayOfMonthPicker.setMinValue(1);
                    }
                }
            }
        });

        mDayOfMonthPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                str3 = mDayOfMonthPicker.getValue() + "";
            }
        });

        setNumberPickerDividerColor(mYearPicker);
        setNumberPickerDividerColor(mMonthPicker);
        setNumberPickerDividerColor(mDayOfMonthPicker);

        mTvCancel.setOnClickListener(this);
        mTvAccept.setOnClickListener(this);

        return layout;
    }


    public void setCurrentDate(Calendar calendar) {
        if (calendar != null) {
            mCalendar = calendar;
        }
    }


    public void updateDateViews() {
        mYearPicker.setMaxValue(2050);
        mYearPicker.setMinValue(1900);

        mMonthPicker.setMaxValue(12);
        mMonthPicker.setMinValue(1);

        mDayOfMonthPicker.setMinValue(1);
        mDayOfMonthPicker.setMaxValue(31);

        mYearPicker.setValue(mCalendar.get(Calendar.YEAR));
        mMonthPicker.setValue(mCalendar.get(Calendar.MONTH) + 1);
        mDayOfMonthPicker.setValue(mCalendar.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (mOnDateSelectedListener != null) {
            if (id == R.id.dialog_dashboard_date_cancel) {
                this.dismiss();

            } else if (id == R.id.dialog_dashboard_date_accept) {
                int year = mYearPicker.getValue();
                int month = mMonthPicker.getValue();
                int dayOfMonth = mDayOfMonthPicker.getValue();
                Calendar cal = Calendar.getInstance();
                cal.set(year, month, dayOfMonth);
                mOnDateSelectedListener.onDateSelected(year, month, dayOfMonth);
            }
        }
    }

    /**
     * DatePicker设置分割线
     *
     * @param
     */
    private void setNumberPickerDividerColor(NumberPicker numberPicker) {
        NumberPicker picker = numberPicker;
        Field[] pickerFields = NumberPicker.class.getDeclaredFields();
        for (Field pf : pickerFields) {
            if (pf.getName().equals("mSelectionDivider")) {
                pf.setAccessible(true);
                //设置分割线的颜色值
                try {
                    pf.set(picker, null);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void setFormater() throws NoSuchFieldException, IllegalAccessException {
        mYearPicker.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                String str = String.valueOf(value);
                str = str + "年";
                return str;
            }
        });

        Field f = NumberPicker.class.getDeclaredField("mInputText");
        f.setAccessible(true);
        EditText inputText = (EditText) f.get(mYearPicker);
        inputText.setFilters(new InputFilter[0]);


        mMonthPicker.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                String str = String.valueOf(value);
                str = str + "月";
                return str;
            }
        });
        EditText inputText2 = (EditText) f.get(mMonthPicker);
        inputText2.setFilters(new InputFilter[0]);


        mDayOfMonthPicker.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                String str = String.valueOf(value);
                str = str + "日";
                return str;
            }
        });
        EditText inputText3 = (EditText) f.get(mDayOfMonthPicker);
        inputText3.setFilters(new InputFilter[0]);
    }
}
