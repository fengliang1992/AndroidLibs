package com.fltry.androidlibs.view.timeselect;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.fltry.androidlibs.R;
import com.fltry.androidlibs.ui.BaseActivity;

import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

public class TimeSelectActivity extends BaseActivity {

    @BindView(R.id.ts_tv)
    TextView tsTv;
    @BindView(R.id.ts_tv2)
    TextView tsTv2;

    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getToolbarTitle().setText("调用系统日期时间");

        calendar = Calendar.getInstance(Locale.CHINA);//获取日期格式器对象
        tsTv.setText(calendar.get(Calendar.HOUR_OF_DAY) + ":" +
                calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND));
        tsTv2.setText(calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH) + "-" +
                calendar.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_time_select;
    }

    @OnClick({R.id.ts_btn, R.id.ts_btn2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ts_btn:
                TimePickerDialog timePickerDialog = new TimePickerDialog(getMContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);
                        tsTv.setText(formatTime(hourOfDay) + ":" + formatTime(minute));
                    }
                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
                timePickerDialog.show();
                break;
            case R.id.ts_btn2:
                DatePickerDialog datePickerDialog = new DatePickerDialog(getMContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year, month, dayOfMonth);
                        tsTv2.setText(year + "-" + formatTime((month + 1)) + "-" + formatTime((dayOfMonth)));
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
                break;
        }
    }

    private static String formatTime(int time) {
        return time < 10 ? "0" + time : time + "";
    }
}
