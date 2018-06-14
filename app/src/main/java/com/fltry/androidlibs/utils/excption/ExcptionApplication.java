package com.fltry.androidlibs.utils.excption;

import android.app.Application;

/**
 * Created by tol on 2018-06-13.
 */

public class ExcptionApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
//        CrashHandler catchHandler = CrashHandler.getInstance();
//        catchHandler.init(getApplicationContext());
//        BlockCanary.install(this, new AppBlockCanaryContext()).start();
    }

}
