package com.fltry.androidlibs.ui;

import android.net.Uri;

class ClassBean {

    private String name;
    private String className;

    ClassBean(String name, String className) {
        this.name = name;
        this.className = className;
    }

    String getName() {
        return name;
    }

    private String getClassName() {
        return "fltry://module:80/" + className;
    }

    Uri getUri() {
        return Uri.parse(getClassName());
    }
}
