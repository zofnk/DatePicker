package com.pachong.administrator.datepicker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.pachong.administrator.datepicker.datepicker.DatePickerFragment;

import org.w3c.dom.Text;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView tvDatePicker = (TextView) findViewById(R.id.tvDatePicker);
        tvDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatePickerFragment dateDialog = new DatePickerFragment();
                Calendar calendar = Calendar.getInstance();
                dateDialog.setCurrentDate(calendar);
                dateDialog.show(getFragmentManager());
                dateDialog.setOnDateSelectedListener(new DatePickerFragment.OnDateSelectedListener() {
                    @Override
                    public void onDateSelected(int year, int monthOfYear, int dayOfMonth) {
                        tvDatePicker.setText(year + " - " + monthOfYear + " - " + dayOfMonth);
                        dateDialog.dismiss();
                    }
                });
            }
        });
    }
}
