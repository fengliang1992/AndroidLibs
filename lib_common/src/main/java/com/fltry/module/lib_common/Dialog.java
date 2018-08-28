package com.fltry.module.lib_common;

import android.app.AlertDialog;
import android.content.Context;

public class Dialog {

    public static AlertDialog.Builder getMyAlert(Context context, String title, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setPositiveButton("知道了", null);
        return builder;
    }

}
