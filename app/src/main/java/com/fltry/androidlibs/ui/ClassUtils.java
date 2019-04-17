package com.fltry.androidlibs.ui;

import com.fltry.androidlibs.R;

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
                classes.add(new ClassBean(R.string.module_name_eventbus, R.string.path_eventbus));
                classes.add(new ClassBean(R.string.module_name_gson, R.string.path_gson));
                classes.add(new ClassBean(R.string.module_name_qrcode, R.string.path_qrcode));
                classes.add(new ClassBean(R.string.module_name_retrofit, R.string.path_retrofit));
                classes.add(new ClassBean(R.string.module_name_amap, R.string.path_amap));
                classes.add(new ClassBean(R.string.module_name_glide, R.string.path_glide));
                break;
            case UTIL_FRAGMENT:
                classes.add(new ClassBean(R.string.module_name_dialog, R.string.path_dialog));
                classes.add(new ClassBean(R.string.module_name_screen, R.string.path_screen));
                classes.add(new ClassBean(R.string.module_name_exception, R.string.path_exception));
                classes.add(new ClassBean(R.string.module_name_database, R.string.path_database));
                classes.add(new ClassBean(R.string.module_name_toast, R.string.path_toast));
                classes.add(new ClassBean(R.string.module_name_picture, R.string.path_picture));
                classes.add(new ClassBean(R.string.module_name_sensor, R.string.path_sensor));
                classes.add(new ClassBean(R.string.module_name_apk, R.string.path_apk));
                classes.add(new ClassBean(R.string.module_name_animation, R.string.path_animation));
                classes.add(new ClassBean(R.string.module_name_web, R.string.path_web));
                classes.add(new ClassBean(R.string.module_name_net_state, R.string.path_net_state));
                break;
            case WEIGHT_FRAGMENT:
                classes.add(new ClassBean(R.string.module_name_elastic, R.string.path_elastic));
                classes.add(new ClassBean(R.string.module_name_refresh, R.string.path_refresh));
                classes.add(new ClassBean(R.string.module_name_timeSelect, R.string.path_timeSelect));
                classes.add(new ClassBean(R.string.module_name_autoText, R.string.path_autoText));
                classes.add(new ClassBean(R.string.module_name_pullList, R.string.path_pullList));
                classes.add(new ClassBean(R.string.module_name_chart, R.string.path_chart));
                break;
        }
        return classes;
    }
}
