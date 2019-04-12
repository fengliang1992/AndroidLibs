package com.fltry.androidlibs.ui;

import java.util.ArrayList;

/**
 * 添加新的model
 */
class ClassUtils {
    static final int SDK_FRAGMENT = 0;
    static final int UTIL_FRAGMENT = 1;
    static final int WEIGHT_FRAGMENT = 2;


    static ArrayList<ArrayList<ClassBean>> getAllClasses() {
        ArrayList<ArrayList<ClassBean>> allClasses = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            allClasses.add(getClasses(i));
        }
        return allClasses;
    }

    private static ArrayList<ClassBean> getClasses(int fragmentIndex) {
        ArrayList<ClassBean> classes = new ArrayList<>();
        switch (fragmentIndex) {
            case SDK_FRAGMENT:
                classes.add(new ClassBean("OkHttp + EventBus", "eventbus"));
                classes.add(new ClassBean("Gson", "gson"));
                classes.add(new ClassBean("二维码", "qrcode"));
                classes.add(new ClassBean("rxandroid2 + retrofit", "retrofit"));
                classes.add(new ClassBean("AMap_高德地图", "amap"));
                classes.add(new ClassBean("glide", "glide"));
                break;
            case UTIL_FRAGMENT:
                classes.add(new ClassBean("弹窗", "dialog"));
                classes.add(new ClassBean("屏幕", "screen"));
                classes.add(new ClassBean("异常奔溃ANR", "exception"));
                classes.add(new ClassBean("数据存储", "database"));
                classes.add(new ClassBean("Toast", "toast"));
                classes.add(new ClassBean("图片处理", "picture"));
                classes.add(new ClassBean("传感器", "sensor"));
                classes.add(new ClassBean("获取apk信息", "apk"));
                classes.add(new ClassBean("动画", "animation"));
                classes.add(new ClassBean("webView", "web"));
                break;
            case WEIGHT_FRAGMENT:
                classes.add(new ClassBean("弹性界面", "elastic"));
                classes.add(new ClassBean("列表刷新", "refresh"));
                classes.add(new ClassBean("系统时间日期选择器", "timeSelect"));
                classes.add(new ClassBean("富文本", "autoText"));
                classes.add(new ClassBean("标签布局", "pullList"));
                classes.add(new ClassBean("图表", "chart"));
                break;
        }
        return classes;
    }
}
