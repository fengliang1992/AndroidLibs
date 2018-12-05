package com.fltry.module.screen;

import android.app.Application;

/**
 * Created by tol on 2018-12-05.
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Density.setDensity(this, 375f);
    }
}
