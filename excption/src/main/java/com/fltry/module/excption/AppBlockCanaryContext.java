package com.fltry.module.excption;

import com.github.moduth.blockcanary.BlockCanaryContext;

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
