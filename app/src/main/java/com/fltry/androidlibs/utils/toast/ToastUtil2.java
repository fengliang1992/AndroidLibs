package com.fltry.androidlibs.utils.toast;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

/**
 * Created by tol on 2018-06-13.
 */

public class ToastUtil2 {

    private static ToastUtil2 instance;
    private static Toast toast;
    private View view;

    private ToastUtil2(Context context) {
        toast = new Toast(context);
        view = Toast.makeText(context, "", Toast.LENGTH_SHORT).getView();
        toast.setView(view);
    }

    public static ToastUtil2 getInstance(Context context) {
        if (instance == null) {
            instance = new ToastUtil2(context);
        }
        return instance;
    }

    public static void show(String msg) {
        toast.setText(msg);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

}
