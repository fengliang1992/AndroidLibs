package com.fltry.androidlibs.ui;

/**
 * Created by tol on 2018-06-15.
 */

public class ClassBean {

    private String name;
    private Class cClass;

    public ClassBean(String name, Class cClass) {
        this.name = name;
        this.cClass = cClass;
    }

    public String getName() {
        return name;
    }

    public Class getcClass() {
        return cClass;
    }
}
