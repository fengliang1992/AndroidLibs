package com.fltry.androidlibs.ui;

import com.fltry.androidlibs.sdkmodule.Okhttp.OkHttpActivity;
import com.fltry.androidlibs.sdkmodule.butterkinfe.ButterKinfeActivity;
import com.fltry.androidlibs.sdkmodule.gson.GsonActivity;
import com.fltry.androidlibs.utils.Dialog.DialogActivity;
import com.fltry.androidlibs.utils.excption.ExcptionActivity;
import com.fltry.androidlibs.utils.screen.ScreenUtilActivity;
import com.fltry.androidlibs.utils.sp.SPActivity;
import com.fltry.androidlibs.utils.toast.ToastActivity;
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
                classes.add(new ClassBean("ButterKnife", ButterKinfeActivity.class));
                classes.add(new ClassBean("OkHttp", OkHttpActivity.class));
                classes.add(new ClassBean("Gson", GsonActivity.class));
                break;
            case UTIL_FRAGMENT:
                classes.add(new ClassBean("DialogUtils_弹窗", DialogActivity.class));
                classes.add(new ClassBean("ScreenUtils_屏幕", ScreenUtilActivity.class));
                classes.add(new ClassBean("Excption_异常奔溃ANR", ExcptionActivity.class));
                classes.add(new ClassBean("SharedPreferences", SPActivity.class));
                classes.add(new ClassBean("Toast", ToastActivity.class));
                break;
            case WEIGHT_FRAGMENT:
                classes.add(new ClassBean("Elastic_弹性界面", ElasticActivity.class));
                classes.add(new ClassBean("Refresh_刷新", RefreshActivity.class));
                classes.add(new ClassBean("TimePic_系统时间日期选择器", TimeSelectActivity.class));
                classes.add(new ClassBean("TextView_富文本", AutoTextActivity.class));
                break;
        }
        return classes;
    }
}
