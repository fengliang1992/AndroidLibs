package com.fltry.module.chart;

import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.fltry.module.chart.databinding.ActivityChartBinding;
import com.fltry.module.lib_common.BaseActivity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.BarLineScatterCandleBubbleData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChartActivity extends BaseActivity<ActivityChartBinding> {
    private ArrayList<ChartData> datas = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chart;
    }

    @Override
    protected String title() {
        return "各类统计图形";
    }

    @Override
    protected void initView() {
        for (int i = 0; i < 20; i++) {
            datas.add(new ChartData("第" + i + "个数据", i + 1, i * new Random().nextFloat() * 100));
        }
        showChart(new LineChart(this), getLineData());
        showChart(new BarChart(this), getBarData());
    }

    /**
     * @return 折线统计图的数据
     */
    public LineData getLineData() {
        ArrayList<Entry> mEntry = new ArrayList<>();
        for (int i = 0; i < datas.size(); i++) {
            Entry entry = new Entry((i + 1) * 1.6f, datas.get(i).getY());
            mEntry.add(i, entry);
        }
        LineDataSet mLineDataSet = new LineDataSet(mEntry, "母鸡哦");
        mLineDataSet.setLineWidth(1);
        mLineDataSet.setDrawCircleHole(false);
        mLineDataSet.setDrawCircles(false);
        mLineDataSet.setDrawFilled(false);
        mLineDataSet.setDrawValues(true);
        mLineDataSet.setDrawHighlightIndicators(false);
        mLineDataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
        LineData mLineData = new LineData();
        mLineData.addDataSet(mLineDataSet);
        return mLineData;
    }

    /**
     * @return 折线统计图的数据
     */
    public BarData getBarData() {
        List<BarEntry> mEntry = new ArrayList<>();
        for (int i = 0; i < datas.size(); i++) {
            BarEntry entry = new BarEntry((i + 1) * 1.6f, datas.get(i).getY());
            mEntry.add(i, entry);
        }
        BarDataSet mLineDataSet = new BarDataSet(mEntry,"母鸡哦");
        mLineDataSet.setDrawValues(true);
        BarData mLineData = new BarData();
        mLineData.addDataSet(mLineDataSet);
        return mLineData;
    }

    /**
     * @param lineChartBase barchart、linechart、candle chart。。的父类
     * @param data          线性表的 数据
     */
    public void showChart(BarLineChartBase lineChartBase, BarLineScatterCandleBubbleData data) {
        dataBinding.mContainer.addView(lineChartBase);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300);
        lineChartBase.setLayoutParams(params);


        lineChartBase.setData(data);
        lineChartBase.setDoubleTapToZoomEnabled(false);
        lineChartBase.setBackgroundColor(Color.argb(12, 12, 22, 22));
        //横坐标
        XAxis xAxis = lineChartBase.getXAxis();
//        xAxis.setDrawAxisLine(true);
//        xAxis.setCenterAxisLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setDrawGridLines(true);
        xAxis.setDrawLabels(true);
        //左纵坐标
        YAxis yAxisL = lineChartBase.getAxisLeft();
        yAxisL.setDrawAxisLine(false);
        yAxisL.setDrawGridLines(false);
        //右纵坐标
        YAxis yAxisR = lineChartBase.getAxisRight();
        yAxisR.setDrawGridLines(false);

        lineChartBase.setGridBackgroundColor(Color.argb(12, 123, 12, 33));
        lineChartBase.setDrawGridBackground(true);
        lineChartBase.animateX(2500);
    }
}
