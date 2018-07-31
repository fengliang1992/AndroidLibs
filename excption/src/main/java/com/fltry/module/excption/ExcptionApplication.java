package com.fltry.module.excption;

import android.app.Application;

import com.github.moduth.blockcanary.BlockCanary;

public class ExcptionApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler catchHandler = CrashHandler.getInstance();
        catchHandler.init(getApplicationContext());
        BlockCanary.install(this, new AppBlockCanaryContext()).start();
    }

}
