package com.fltry.androidlibs.ui;

import android.net.Uri;

public class ClassBean {

    private String name;
    private String className;

    public ClassBean(String name, String className) {
        this.name = name;
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public String getClassName() {
        return "fltry://module:80/" + className;
    }

    public Uri getUri() {
        return Uri.parse(getClassName());
    }
}
