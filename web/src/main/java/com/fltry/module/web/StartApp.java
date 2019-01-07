package com.fltry.module.web;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

public class StartApp {

    /*
    * 启动一个app
    */
    public static void startAPP(Activity activity, String appPackageName, String param) throws Exception {
        Intent intent = activity.getPackageManager().getLaunchIntentForPackage(appPackageName);
        intent.putExtra("flag", param);
        activity.startActivity(intent);
    }


    /**
     * 启动一个app
     * com -- ComponentName 对象，包含apk的包名和主Activity名
     * param -- 需要传给apk的参数
     */
    public static void startApp(Activity activity, ComponentName com, String param) {
        if (com != null) {
            PackageInfo packageInfo;
            try {
                packageInfo = activity.getPackageManager().getPackageInfo(com.getPackageName(), 0);
            } catch (PackageManager.NameNotFoundException e) {
                packageInfo = null;
                Toast.makeText(activity, "没有安装", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
            try {
                Intent intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setComponent(com);
                if (param != null) {
                    Bundle bundle = new Bundle(); // 创建Bundle对象
                    bundle.putString("flag", param); // 装入数据
                    intent.putExtras(bundle); // 把Bundle塞入Intent里面
                }
                activity.startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(activity, "启动异常", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /*
    * 启动一个app
    */
    public static void startAPP(Activity activity, Uri uri, String param) throws Exception {
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.putExtra("flag", param);
        activity.startActivity(intent);
    }
}
