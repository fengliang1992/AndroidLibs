package com.fltry.androidlibs.ui;

import com.fltry.androidlibs.view.autotext.AutoTextActivity;
import com.fltry.androidlibs.view.elastic.ElasticActivity;
import com.fltry.androidlibs.view.refresh.RefreshActivity;
import com.fltry.androidlibs.view.timeselect.TimeSelectActivity;

import java.util.ArrayList;

/**
 * 添加新的model
 */
public class ClassUtils {
    public static final int SDK_FRAGMENT = 0;
    public static final int UTIL_FRAGMENT = 1;
    public static final int WEIGHT_FRAGMENT = 2;


    public static ArrayList<ArrayList<ClassBean>> getAllClasses() {
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
                classes.add(new ClassBean("Zxing_二维码", "qrcode"));
                classes.add(new ClassBean("rxandroid2 + retrofit", "retrofit"));
                classes.add(new ClassBean("AMap_高德地图", "amap"));
                break;
            case UTIL_FRAGMENT:
                classes.add(new ClassBean("DialogUtils_弹窗", "dialog"));
                classes.add(new ClassBean("ScreenUtils_屏幕", "screen"));
                classes.add(new ClassBean("Excption_异常奔溃ANR", "excption"));
                classes.add(new ClassBean("数据存储", "database"));
                classes.add(new ClassBean("Toast", "toast"));
                classes.add(new ClassBean("Picture_图片处理", "picture"));
                break;
            case WEIGHT_FRAGMENT:
                classes.add(new ClassBean("Elastic_弹性界面", "elastic"));
                classes.add(new ClassBean("Refresh_刷新", "refresh"));
                classes.add(new ClassBean("TimePic_系统时间日期选择器", "timeSelect"));
                classes.add(new ClassBean("TextView_富文本", "autoText"));
                break;
        }
        return classes;
    }
}
