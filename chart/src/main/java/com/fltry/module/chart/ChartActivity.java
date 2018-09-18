package com.fltry.module.chart;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.fltry.module.lib_common.BaseActivity;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarLineScatterCandleBubbleData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Random;

public class ChartActivity extends BaseActivity {
    private ArrayList<ChartData> datas = new ArrayList<>();
    private LinearLayout mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarTitle("各类统计图形");
        mContainer = findViewById(R.id.mContainer);

        for (int i = 0; i < 20; i++) {
            datas.add(new ChartData("第" + i + "个数据", i + 1, i * new Random().nextFloat() * 100));
        }
        showChart(new LineChart(this), getLineData());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chart;
    }

    /**
     * @return 折线统计图的数据
     */
    public LineData getLineData() {
        ArrayList<Entry> mEntry = new ArrayList<>();
        for (int i = 0; i < datas.size(); i++) {
            Entry entry = new Entry(i, datas.get(i).getY());
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
     * @param lineChartBase barchart、linechart、candle chart。。的父类
     * @param data          线性表的 数据
     */
    public void showChart(BarLineChartBase lineChartBase, BarLineScatterCandleBubbleData data) {
        mContainer.addView(lineChartBase);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300);
        lineChartBase.setLayoutParams(params);


        lineChartBase.setData(data);
        lineChartBase.setDoubleTapToZoomEnabled(false);
        lineChartBase.setBackgroundColor(Color.argb(12, 12, 22, 22));
        //横坐标
        XAxis xAxis = lineChartBase.getXAxis();
        xAxis.setDrawAxisLine(false);
        xAxis.setCenterAxisLabels(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawLabels(false);
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
