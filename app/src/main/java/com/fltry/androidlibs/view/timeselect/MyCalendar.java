package com.fltry.androidlibs.view.timeselect;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyCalendar extends View {

    private String TAG = "MyCalendar";

    /**
     * 字和背景paint
     */
    private Paint textPaint;
    private Paint bgPaint;

    /**
     * 每个日期所占的长和宽
     */
    private float mRectWidth;
    private float mRectHeight;
    /**
     * 控制变色框的大小
     */
    private float mRectSideLength = 5;

    private float titleHeight;
    private float btnWidth;

    /**
     * 字体和背景颜色
     */
    private int otherMonthDefaultTextColor = 0xff898989;
    private int otherMonthDefaultBgColor = 0xffffff;
    private int thisMonthDefaultTextColor = 0xff000000;
    private int thisMonthDefaultBgColor = 0xffffff;

    /**
     * 当前日期所有的天数
     */
    private Map<String, Day> allDaysMap;
    private ArrayList<ArrayList<String>> allDaysList;

    /**
     * 今天
     */
    private String toDay;
    private int selectYear;
    private int selectMonth;
    private Day beforeDay;


    private String[] weekStr = {"日", "一", "二", "三", "四", "五", "六"};
    private Calendar calendar;

    public MyCalendar(Context context) {
        this(context, null);
    }

    public MyCalendar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyCalendar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        textPaint = new Paint();
        bgPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setTextAlign(Paint.Align.CENTER);
        bgPaint.setAntiAlias(true);

        textPaint.setTextSize(20);
        String str = "哈哈哈";
        int len = str.length();
        float[] widths = new float[len];
        textPaint.getTextWidths(str, widths);
        for (int j = 0; j < len; j++) {
            btnWidth += (int) Math.ceil(widths[j]);
        }

        if (calendar == null) {
            calendar = Calendar.getInstance();
        }
        selectYear = calendar.get(Calendar.YEAR);
        selectMonth = calendar.get(Calendar.MONTH) + 1;
        setSelectDate(selectYear, selectMonth);
        toDay = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH);
//        setSelectDate(2019, 9);
    }

    /**
     * 切换月
     */
    public void setSelectDate(int selectYear, int selectMonth) {
        this.selectYear = selectYear;
        this.selectMonth = selectMonth;
        initAllDays(selectYear, selectMonth, calendar);
        invalidate();
    }

    /**
     * 设置某天颜色（可根据自己的需求调整日期格式）
     *
     * @param date      日期：2019-1-2
     * @param textColor 字体颜色
     * @param bgColor   背景颜色
     */
    public void setDayColor(String date, int textColor, int bgColor) {
        if (allDaysMap.containsKey(date) && date.contains(selectYear + "-" + selectMonth)) {
            allDaysMap.put(date, new Day(date, textColor, bgColor));
            invalidate();
        } else {
            Log.e(TAG, "当前月不存在该时间");
        }
    }

    /**
     * 设置多天颜色（可根据自己的需求调整日期格式）
     *
     * @param days 日期list：2019-1-2
     */
    public void setDayColor(List<Day> days) {
        for (int i = 0; i < days.size(); i++) {
            if (allDaysMap.containsKey(days.get(i).getDate()) && days.get(i).getYearAndMonth().equals(selectYear + "-" + selectMonth)) {
                allDaysMap.put(days.get(i).getDate(), new Day(days.get(i).getDate(), days.get(i).getTextColor(), days.get(i).getTextColor()));
                invalidate();
            } else {
                Log.e(TAG, "当前月不存在该时间");
            }
        }
    }

    /**
     * 设置多天颜色（可根据自己的需求调整日期格式）
     *
     * @param days 日期数组  格式：{"2019-1-2","#ff4848","#ff0000"}
     */
    public void setDayColor(String[][] days) {
        for (int i = 0; i < days.length; i++) {
            if (allDaysMap.containsKey(days[i][0]) && days[i][0].contains(selectYear + "-" + selectMonth)) {
                allDaysMap.put(days[i][0], new Day(days[i][0], Color.parseColor(days[i][1]), Color.parseColor(days[i][2])));
                invalidate();
            } else {
                Log.e(TAG, "当前月不存在该时间");
            }
        }
    }

    public void initAllDays(int selectYear, int selectMonth, Calendar calendar) {
        allDaysList = new ArrayList<>();
        allDaysMap = new HashMap<>();
        int lastMonthOfDay;
        if (selectMonth == 1) {
            lastMonthOfDay = getMonthOfDay(selectYear - 1, 12);
        } else {
            lastMonthOfDay = getMonthOfDay(selectYear, selectMonth - 1);
        }
        int nowMonthOfDay = getMonthOfDay(selectYear, selectMonth);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String pTime = selectYear + "-" + selectMonth + "-1";
        try {
            calendar.setTime(format.parse(pTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int todayWeekIndex = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        int day = 0;
        int newDay = 0;
        String dateStr;
        for (int i = 0; i < 6; i++) {
            ArrayList<String> days = new ArrayList<>();
            if (i == 0) {
                for (int j = 0; j < todayWeekIndex; j++) {
                    if (selectMonth == 1) {
                        dateStr = (selectYear - 1) + "-12-" + String.valueOf(lastMonthOfDay - todayWeekIndex + j + 1);
                    } else {
                        dateStr = selectYear + "-" + (selectMonth - 1) + "-" + String.valueOf(lastMonthOfDay - todayWeekIndex + j + 1);
                    }
                    days.add(dateStr);
                    allDaysMap.put(dateStr, new Day(dateStr, otherMonthDefaultTextColor, otherMonthDefaultBgColor));
                }
                for (int j = 0; j < 7 - todayWeekIndex; j++) {
                    day++;
                    dateStr = selectYear + "-" + selectMonth + "-" + String.valueOf(day);
                    days.add(dateStr);
                    allDaysMap.put(dateStr, new Day(dateStr, thisMonthDefaultTextColor, thisMonthDefaultBgColor));
                }
            } else {
                int residueDay = nowMonthOfDay - day;
                if (residueDay >= 7)
                    for (int j = 0; j < 7; j++) {
                        day++;
                        dateStr = selectYear + "-" + selectMonth + "-" + String.valueOf(day);
                        days.add(dateStr);
                        allDaysMap.put(dateStr, new Day(dateStr, thisMonthDefaultTextColor, thisMonthDefaultBgColor));
                    }
                else {
                    for (int j = 0; j < residueDay; j++) {
                        day++;
                        dateStr = selectYear + "-" + selectMonth + "-" + String.valueOf(day);
                        allDaysMap.put(dateStr, new Day(dateStr, thisMonthDefaultTextColor, thisMonthDefaultBgColor));
                        days.add(dateStr);
                    }
                    for (int j = 0; j < 7 - residueDay; j++) {
                        newDay++;
                        if (selectMonth == 12) {
                            dateStr = (selectYear + 1) + "-1-" + String.valueOf(newDay);
                        } else {
                            dateStr = selectYear + "-" + (selectMonth + 1) + "-" + String.valueOf(newDay);
                        }
                        days.add(dateStr);
                        allDaysMap.put(dateStr, new Day(dateStr, otherMonthDefaultTextColor, otherMonthDefaultBgColor));
                    }
                }
            }
            allDaysList.add(days);
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int mWidth = getMeasuredWidth();
        mRectWidth = (float) mWidth / 7;
        mRectHeight = mRectWidth / 2;
        titleHeight = mRectWidth * 2 / 3;
        int mHeight = (int) (mRectHeight * 7 + titleHeight);
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        drawTitle(canvas);
        drawWeek(canvas);

        for (int i = 0; i < allDaysList.size(); i++) {
            drawOneRow(canvas, i + 1, allDaysList.get(i));
        }
    }

    private void drawTitle(Canvas canvas) {
        textPaint.setTextSize(40);
        textPaint.setColor(Color.BLACK);
        canvas.drawText(selectYear + "-" + selectMonth, mRectWidth * 7 / 2, titleHeight / 2 - getBaseLine(textPaint), textPaint);

        textPaint.setTextSize(25);
        canvas.drawText("上一月", mRectWidth / 2, titleHeight / 2 - getBaseLine(textPaint), textPaint);
        canvas.drawText("下一月", mRectWidth * 13 / 2, titleHeight / 2 - getBaseLine(textPaint), textPaint);
    }

    private void drawWeek(Canvas canvas) {
        textPaint.setColor(0xff5a5a5a);
        textPaint.setTextSize(30);
        for (int i = 0; i < weekStr.length; i++) {
            canvas.drawText(weekStr[i], mRectWidth * i + mRectWidth / 2, titleHeight + mRectHeight / 2 - getBaseLine(textPaint), textPaint);
        }
    }

    /**
     * 画一行
     *
     * @param row 第几行
     */
    private void drawOneRow(Canvas canvas, int row, ArrayList<String> days) {
        textPaint.setTextSize(25);
        RectF rectF = new RectF();
        for (int i = 0; i < days.size(); i++) {
            Day day = allDaysMap.get(days.get(i));
            textPaint.setColor(day.getTextColor());
            bgPaint.setColor(day.getBgColor());
            rectF.left = (int) (i * mRectWidth) + mRectWidth / 4 + mRectSideLength;
            rectF.right = (int) ((i + 1) * mRectWidth) - mRectWidth / 4 - mRectSideLength;
            rectF.top = titleHeight + (int) ((row + 1) * mRectHeight) - mRectSideLength;
            rectF.bottom = titleHeight + (int) (row * mRectHeight) + mRectSideLength;
            canvas.drawRoundRect(rectF, 10, 10, bgPaint);
            canvas.drawText(day.getDay(), mRectWidth * i + mRectWidth / 2,
                    titleHeight + mRectHeight * row + mRectHeight / 2 - getBaseLine(textPaint), textPaint);
        }
    }

    /**
     * 获取基准线
     */
    public float getBaseLine(Paint paint) {
        Paint.FontMetrics fm = paint.getFontMetrics();
        return fm.descent - Math.abs(fm.descent - fm.ascent) / 2;
    }


    /**
     * 获取当月有多少天
     *
     * @param year  年
     * @param month 月
     */
    public static int getMonthOfDay(int year, int month) {
        int day;
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            day = 29;
        } else {
            day = 28;
        }
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                return day;

        }
        return 0;
    }

    /**
     * 天对象
     */
    public static class Day {
        private String date;
        private int textColor;
        private int bgColor;

        public Day(String date, int textColor, int bgColor) {
            this.date = date;
            this.textColor = textColor;
            this.bgColor = bgColor;
        }

        public String getYear() {
            return date.split("-")[0];
        }

        public String getMonth() {
            return date.split("-")[1];
        }

        public String getDay() {
            return date.split("-")[2];
        }

        public String getYearAndMonth() {
            return date.split("-")[0] + "-" + date.split("-")[1];
        }

        public String getDate() {
            return date;
        }

        public int getTextColor() {
            return textColor;
        }

        public int getBgColor() {
            return bgColor;
        }
    }

    PointF focusPoint = new PointF();

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                focusPoint.set(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_UP:
                countDate(focusPoint.x, focusPoint.y);
                break;
        }
        return true;
    }

    /**
     * 点击事件
     */
    private void countDate(float eventX, float eventY) {
        if (null != onSelectClickListener && eventX >= 0 && eventX < mRectWidth * 7 && eventY >= titleHeight
                && eventY < titleHeight + mRectHeight * 7) {
            //获取选择的日期
            String date = allDaysList.get((int) ((eventY - mRectHeight - titleHeight) / (mRectHeight))).get((int) (eventX / mRectWidth));
            Day day = allDaysMap.get(date);
            if (Integer.parseInt(day.getMonth()) == selectMonth) {
                if (null != beforeDay) {
                    allDaysMap.put(beforeDay.getDate(), beforeDay);
                } else {
                    beforeDay = day;
                }
                if (null != beforeDay && !beforeDay.getDate().equals(day.getDate())) {
                    beforeDay = day;
                }
                setDayColor(date, Color.WHITE, Color.RED);
            } else {
                setSelectDate(Integer.parseInt(day.getYear()), Integer.parseInt(day.getMonth()));
                onSelectClickListener.onMonthChange();
                beforeDay = null;
            }
            onSelectClickListener.onSelect(day.getYear(), day.getMonth(), day.getDay());
        } else if (eventX >= 0 && eventX < 2 * btnWidth && eventY >= 0 && eventY < titleHeight) {
            if (selectMonth == 1)
                setSelectDate(selectYear - 1, 12);
            else
                setSelectDate(selectYear, selectMonth - 1);

            onSelectClickListener.onMonthChange();
        } else if (eventX >= mRectWidth * 7 - 2 * btnWidth && eventX < mRectWidth * 7 && eventY >= 0 && eventY < titleHeight) {
            if (selectMonth == 12)
                setSelectDate(selectYear + 1, 1);
            else
                setSelectDate(selectYear, selectMonth + 1);

            onSelectClickListener.onMonthChange();
        }
    }

    public OnSelectClickListener onSelectClickListener;

    public void setOnSelectClickListener(OnSelectClickListener onSelectClickListener) {
        this.onSelectClickListener = onSelectClickListener;
    }

    public interface OnSelectClickListener {
        void onSelect(String year, String month, String day);

        void onMonthChange();
    }
}
