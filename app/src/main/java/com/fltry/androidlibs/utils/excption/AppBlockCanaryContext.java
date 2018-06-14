package com.fltry.androidlibs.utils.excption;

import com.github.moduth.blockcanary.BlockCanaryContext;

/**
 * Created by tol on 2018-06-08.
 */

public class AppBlockCanaryContext extends BlockCanaryContext {


    public int getConfigBlockThreshold() {
        return 5000;
    }

    // if set true, notification will be shown, else only write log file

    public boolean isNeedDisplay() {
        return true;
    }

    // path to save log file

    public String getLogPath() {
        return "/mnt/sdcard/";
    }


}
