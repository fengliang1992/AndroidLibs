package com.fltry.androidlibs.view.timeselect;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.fltry.androidlibs.R;
import com.fltry.module.lib_common.BaseActivity;

import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class TimeSelectActivity extends BaseActivity {

    @BindView(R.id.ts_tv)
    TextView tsTv;
    @BindView(R.id.ts_tv2)
    TextView tsTv2;
    @BindView(R.id.ts_tv3)
    TextView tsTv3;
    @BindView(R.id.ts_tv4)
    TextView tsTv4;

    private Calendar calendar;
    private CustomDatePicker timePicker;
    private Unbinder unbinder;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_time_select;
    }

    @Override
    protected String title() {
        return "调用系统日期时间";
    }

    @Override
    protected void initView() {
        unbinder = ButterKnife.bind(this);
        calendar = Calendar.getInstance(Locale.CHINA);//获取日期格式器对象
        tsTv.setText(calendar.get(Calendar.HOUR_OF_DAY) + ":" +
                calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND));
        tsTv2.setText(calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH) + "-" +
                calendar.get(Calendar.DAY_OF_MONTH));

        timePicker = new CustomDatePicker(this, "请选择时间", time -> tsTv3.setText(time), "2000-01-01 00:00", "2050-12-31 23:59");// "2027-12-31 23:59"
        timePicker.showSpecificTime(true);
        timePicker.setIsLoop(true);
        tsTv3.setText(GetTimeUtli.getTime("yyyy-MM-dd HH"));
    }

    @OnClick({R.id.ts_btn, R.id.ts_btn2, R.id.ts_btn3, R.id.ts_btn4})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.ts_btn:
                TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, (view, hourOfDay, minute) -> {
                    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    calendar.set(Calendar.MINUTE, minute);
                    tsTv.setText(formatTime(hourOfDay) + ":" + formatTime(minute));
                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
                timePickerDialog.show();
                break;
            case R.id.ts_btn2:
                DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, (view, year, month, dayOfMonth) -> {
                    calendar.set(year, month, dayOfMonth);
                    tsTv2.setText(year + "-" + formatTime((month + 1)) + "-" + formatTime((dayOfMonth)));
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
                break;
            case R.id.ts_btn3:
                timePicker.show(tsTv3.getText().toString());
                break;
            case R.id.ts_btn4:
                final Dialog dialog = new Dialog(mContext, R.style.MyDialog2);
                View view = View.inflate(mContext, R.layout.dialog_custom, null);
                MyCalendar myCalendar = view.findViewById(R.id.myCalendar);
                Log.i("MyCalendar", Color.RED + "++++++");
                myCalendar.setDayColor("2019-1-23", Color.WHITE, Color.RED);
                myCalendar.setOnSelectClickListener(new MyCalendar.OnSelectClickListener() {
                    @Override
                    public void onSelect(String year, String month, String day) {
                        tsTv4.setText(year + "-" + month + "-" + day);
                        dialog.dismiss();
                    }

                    @Override
                    public void onMonthChange() {

                    }
                });
                dialog.setContentView(view);
                dialog.show();
                break;
        }
    }

    private static String formatTime(int time) {
        return time < 10 ? "0" + time : time + "";
    }
}
