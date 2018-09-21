package com.fltry.module.eventbus;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import com.fltry.module.eventbus.databinding.ActivityHttpBinding;
import com.fltry.module.lib_common.BaseActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class OkHttpActivity extends BaseActivity<ActivityHttpBinding> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_http;
    }

    @Override
    protected String title() {
        return "okHttp+EventBus";
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void httpSccessEvent(HttpResult httpResult) {
        new AlertDialog.Builder(mContext).setMessage(httpResult.getResult()).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void httpFaileEvent(HttpFaile httpFaile) {
        new AlertDialog.Builder(mContext).setMessage("请求失败：" + httpFaile.getFalieMsg()).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    String url = "https://www.pgyer.com/apiv2/app/install?appKey=d72ffc3b7aef8fac939fa125241c4910&_api_key=9de4f82d0d07a4d2b57e02479861febc";

    public void sendGet(View v) {
        if (TextUtils.isEmpty(dataBinding.okHttpEt.getText().toString())) {
            Toast.makeText(mContext, "输入接口", Toast.LENGTH_LONG).show();
            return;
        }
        Http_Test_Get http_test_get = new Http_Test_Get("", dataBinding.okHttpEt.getText().toString());
        http_test_get.sendRequest();
    }

    public void update(View v) {
        new AlertDialog.Builder(mContext).setTitle("温馨提示")
                .setMessage("是否更新apk？")
                .setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        downloadAPK(url, "test.apk");
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        dataBinding.okHttpEt.setText("http://10.10.10.107:8004/areas");
    }


    DownloadManager downloadManager;
    long mTaskId;

    //使用系统下载器下载
    private void downloadAPK(String versionUrl, String versionName) {
        //创建下载任务
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(versionUrl));
        request.setAllowedOverRoaming(false);//漫游网络是否可以下载

        //设置文件类型，可以在下载结束后自动打开该文件
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        String mimeString = mimeTypeMap.getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(versionUrl));
        request.setMimeType(mimeString);

        //在通知栏中显示，默认就是显示的
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        request.setVisibleInDownloadsUi(true);

        //sdcard的目录下的download文件夹，必须设置
        request.setDestinationInExternalPublicDir("/download/", versionName);
        //request.setDestinationInExternalFilesDir(),也可以自己制定下载路径

        //将下载请求加入下载队列
        downloadManager = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);

        //加入下载队列后会给该任务返回一个long型的id，
        //通过该id可以取消任务，重启任务等等，看上面源码中框起来的方法
        mTaskId = downloadManager.enqueue(request);

        //注册广播接收者，监听下载状态
        mContext.registerReceiver(receiver,
                new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    //广播接受者，接收下载状态
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override

        public void onReceive(Context context, Intent intent) {
            checkDownloadStatus();//检查下载状态
        }
    };

    //检查下载状态
    private void checkDownloadStatus() {
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(mTaskId);//筛选下载任务，传入任务ID，可变参数
        Cursor c = downloadManager.query(query);
        if (c.moveToFirst()) {
            int status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
            switch (status) {
                case DownloadManager.STATUS_PAUSED:
                case DownloadManager.STATUS_PENDING:
                case DownloadManager.STATUS_RUNNING:
                    break;
                case DownloadManager.STATUS_SUCCESSFUL:
                    //下载完成安装APK
                    Toast.makeText(mContext, "下载成功", Toast.LENGTH_SHORT).show();
                    break;
                case DownloadManager.STATUS_FAILED:
                    break;
            }
        }
    }

}
