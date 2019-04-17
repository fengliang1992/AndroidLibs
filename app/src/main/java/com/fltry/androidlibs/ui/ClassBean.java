package com.fltry.androidlibs.ui;

import android.content.Context;
import android.net.Uri;

import com.fltry.androidlibs.R;

class ClassBean {

    private int nameRes;
    private int clzRes;

    ClassBean(int nameRes, int clzRes) {
        this.nameRes = nameRes;
        this.clzRes = clzRes;
    }

    String getName(Context context) {
        return context.getResources().getString(nameRes);
    }

    private String getClassName(Context context) {
        return context.getResources().getString(R.string.scheme) + "://" + context.getResources().getString(R.string.host) + ":"
                + context.getResources().getString(R.string.port) + context.getResources().getString(clzRes);
    }

    Uri getUri(Context context) {
        return Uri.parse(getClassName(context));
    }
}
