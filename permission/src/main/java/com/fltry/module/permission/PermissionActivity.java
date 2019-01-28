package com.fltry.module.permission;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Toast;

import com.fltry.module.lib_common.BaseActivity;

public class PermissionActivity extends BaseActivity {
    final static int MY_PERMISSIONS_REQUEST_READ = 1;
    final static int MY_PERMISSIONS_REQUEST_PHOTO = 2;
    final static int MY_PERMISSIONS_REQUEST_LOACLTION = 3;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected String title() {
        return "6.0以上权限动态申请";
    }

    @Override
    protected void initView() {

    }

    public void onReadOrWrite(View v) {
        //读写权限只需写一个
        if (Build.VERSION.SDK_INT >= 23
                && getApplicationInfo().targetSdkVersion >= 23) {
            if ((ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this
                    , Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) ||
                    (ActivityCompat.checkSelfPermission(this,
                            Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                            && ActivityCompat.checkSelfPermission(this
                            , Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ);
                return;
            }
            new AlertDialog.Builder(mContext).setMessage("以获取读写权限").setPositiveButton("知道了", null).show();
        }
    }

    public void onTakePhoto(View v) {
        if (Build.VERSION.SDK_INT >= 23
                && getApplicationInfo().targetSdkVersion >= 23) {
            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this
                    , Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        MY_PERMISSIONS_REQUEST_PHOTO);
                return;
            }
            new AlertDialog.Builder(mContext).setMessage("以获调用摄像头权限").setPositiveButton("知道了", null).show();
        }
    }

    public void onLocation(View v) {
        if (Build.VERSION.SDK_INT >= 23
                && getApplicationInfo().targetSdkVersion >= 23) {
            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this
                    , Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOACLTION);
                return;
            }
            new AlertDialog.Builder(mContext).setMessage("以获取位置信息权限").setPositiveButton("知道了", null).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(mContext, "获取成功", Toast.LENGTH_SHORT).show();
                } else {
                    showMissingPermissionDialog("读写");
                }
                break;
            case MY_PERMISSIONS_REQUEST_PHOTO:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(mContext, "获取成功", Toast.LENGTH_SHORT).show();
                } else {
                    showMissingPermissionDialog("调用摄像头");
                }
                break;
            case MY_PERMISSIONS_REQUEST_LOACLTION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(mContext, "获取成功", Toast.LENGTH_SHORT).show();
                } else {
                    showMissingPermissionDialog("位置信息");
                }
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * 显示提示信息
     *
     * @since 2.5.0
     */
    private void showMissingPermissionDialog(String titile) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("温馨提示");
        builder.setMessage("当前应用缺少" + titile + "权限。请点击\"设置\"-\"权限\"-打开所需权限。");
        // 拒绝, 退出应用
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("设置",
                (dialog, which) -> startAppSettings());
        builder.setCancelable(false);
        builder.show();
    }

    /**
     * 启动管理权限应用的设置
     *
     * @since 2.5.0
     */
    private void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }
}
