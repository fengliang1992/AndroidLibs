package com.github.ybq.android.spinkit;

/**
 * Created by ybq.
 */
public enum Style {

    //旋转平面
    ROTATING_PLANE(0),
    //双反弹
    DOUBLE_BOUNCE(1),
    //波
    WAVE(2),
    //流浪的多维数据集
    WANDERING_CUBES(3),
    //脉冲
    PULSE(4),
    //追逐点
    CHASING_DOTS(5),
    //三个反弹
    THREE_BOUNCE(6),
    //圆
    CIRCLE(7),
    //立方体网格
    CUBE_GRID(8),
    //褪色的圆
    FADING_CIRCLE(9),
    //折叠立方体
    FOLDING_CUBE(10),
    //旋转的圆
    ROTATING_CIRCLE(11),
    //多脉冲
    MULTIPLE_PULSE(12),
    //脉冲环
    PULSE_RING(13),
    //多脉冲环
    MULTIPLE_PULSE_RING(14);

    private int value;

    Style(int value) {
        this.value = value;
    }
}
