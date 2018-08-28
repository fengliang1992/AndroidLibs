package com.fltry.module.chart;


public class ChartData {

    private String title;
    private float x;
    private float y;

    public ChartData(String title, float x, float y) {
        this.title = title;
        this.x = x;
        this.y = y;
    }

    public String getTitle() {
        return title;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
