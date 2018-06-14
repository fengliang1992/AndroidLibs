package com.fltry.androidlibs.utils.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

import com.fltry.androidlibs.utils.screen.ScreenUtils;

public class DialogUtli {
    private static Window dialogWindow;
    private static WindowManager.LayoutParams p;
    private static DisplayMetrics dm = null;

    static {
        dm = new DisplayMetrics();
    }

    /**
     * 设置Dialog的宽高的比例
     */
    public static void dialogSize(Dialog dialog, double widthRatio, double heightRatio) {
        dialogWindow = dialog.getWindow();
        dialogWindow.getWindowManager().getDefaultDisplay().getMetrics(dm);
        p = dialogWindow.getAttributes(); // 获取对话框当前的参数值;
        p.width = (int) (dm.widthPixels * widthRatio);
        p.height = (int) (dm.heightPixels * heightRatio);
        dialog.getWindow().setAttributes(p);
    }

    /**
     * 设置Dialog的宽高的比例
     */
    public static void dialogSize2(Context context, Dialog dialog, double widthRatio, double heightRatio) {
        dialogWindow = dialog.getWindow();
        dialogWindow.getWindowManager().getDefaultDisplay().getMetrics(dm);
        p = dialogWindow.getAttributes(); // 获取对话框当前的参数值;
        p.width = (int) (dm.widthPixels * widthRatio);
        p.height = (int) (dm.heightPixels * heightRatio) - ScreenUtils.getStatusHeight(context);
        dialog.getWindow().setAttributes(p);
    }

    /**
     * 设置Dialog的宽或高的比例
     */
    public static void dialogSize(Dialog dialog, double ratio, String widthOrHeight) {
        dialogWindow = dialog.getWindow();
        dialogWindow.getWindowManager().getDefaultDisplay().getMetrics(dm);
        p = dialogWindow.getAttributes(); // 获取对话框当前的参数值;
        if (widthOrHeight.equals("width")) {
            p.width = (int) (dm.widthPixels * ratio);
        } else if (widthOrHeight.equals("height")) {
            p.height = (int) (dm.heightPixels * ratio);
        }
        dialog.getWindow().setAttributes(p);
    }

    /**
     * LEFT,p.x表示相对左边的偏移,负值忽略. RIGHT时,p.x表示相对右边的偏移,负值忽略. TOP时,p.y表示相对上边的偏移,负值忽略.
     * BOTTOM,p.y表示相对下边的偏移,负值忽略
     */
    public static void dialogLocation(Dialog dialog, int gravity, int x, int y) {
        dialogWindow = dialog.getWindow();
        p = dialogWindow.getAttributes();
        dialogWindow.setGravity(gravity);
        p.x = x; // 新位置X坐标
        p.y = y; // 新位置Y坐标
        dialogWindow.setAttributes(p);
    }
}
