package com.fltry.module.web;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.fltry.module.lib_common.AlertDialogUtils;
import com.fltry.module.lib_common.BaseActivity;
import com.fltry.module.web.databinding.ActivityWebBinding;

public class WebActivity extends BaseActivity<ActivityWebBinding> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    protected String title() {
        return "html交互";
    }

    @SuppressLint("JavascriptInterface")
    @Override
    protected void initView() {
        WebSettings settings = dataBinding.webWb.getSettings();
        settings.setJavaScriptEnabled(true);// 设置与Js交互的权限
        settings.setJavaScriptCanOpenWindowsAutomatically(true);// 设置允许JS弹窗
        dataBinding.webWb.addJavascriptInterface(this, "android");//添加js监听 这样html就能调用客户端
        //支持屏幕缩放
        settings.setSupportZoom(false);
        settings.setBuiltInZoomControls(false);
        //不显示webview缩放按钮
//        webSettings.setDisplayZoomControls(false);

        dataBinding.webWb.setWebChromeClient(webChromeClient);
        dataBinding.webWb.setWebViewClient(webViewClient);
        /*
         * LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
         * LOAD_DEFAULT: （默认）根据cache-control决定是否从网络上取数据。
         * LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
         * LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。
         */
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);


        //加载html四种方式
//        webView.loadUrl("http://139.196.35.30:8080/OkHttpTest/apppackage/test.html");//加载url
//        webView.loadUrl("file:///android_asset/test.html");//加载asset文件夹下html
//        //方式3：加载手机sdcard上的html页面
//        webView.loadUrl("content://com.ansen.webview/sdcard/test.html");
//        //方式4 使用webview显示html代码
//        webView.loadDataWithBaseURL(null,"<html><head><title> 欢迎您 </title></head>" +
//                "<body><h2>使用webview显示 html代码</h2></body></html>", "text/html" , "utf-8", null);
        dataBinding.webWb.loadUrl("file:///android_asset/test.html");

        dataBinding.webBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBinding.webWb.loadUrl("javascript:loadJsFunction('调用html方法成功')");
            }
        });
    }

    /**
     * JS调用android的方法
     *
     * @param str
     * @return
     */
    @JavascriptInterface //仍然必不可少
    public void getClient(String str) {
        AlertDialogUtils.getMyAlert(mContext, "js调用android方法", str).show();
    }

    @JavascriptInterface
    public void startOtherApp(String url) {
        try {
            StartApp.startAPP(WebActivity.this, Uri.parse(url), "通过url跳转成功！");
        } catch (Exception e) {
            e.printStackTrace();
            AlertDialogUtils.getMyAlert(mContext, "跳转失败", "未找到相应的界面").show();
        }
    }

    @JavascriptInterface
    public void startOtherApp2() {
        try {
            StartApp.startAPP(WebActivity.this, "com.fltry.module.gson", "通过包名跳转成功！");
        } catch (Exception e) {
            e.printStackTrace();
            AlertDialogUtils.getMyAlert(mContext, "跳转失败", "未找到相关应用").show();
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    @JavascriptInterface
    public void startOtherApp3() {
        try {
            ComponentName com = ComponentName.createRelative("com.fltry.module.gson", "com.fltry.module.gson.GsonActivity");
            StartApp.startApp(WebActivity.this, com, "通过ComponentName跳转成功");
        } catch (Exception e) {
            e.printStackTrace();
            AlertDialogUtils.getMyAlert(mContext, "跳转失败", "未找到相关应用").show();
        }
    }

    //WebViewClient主要帮助WebView处理各种通知、请求事件
    private WebViewClient webViewClient = new WebViewClient() {
        @Override
        public void onPageFinished(WebView view, String url) {//页面加载完成

        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {//页面开始加载

        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.i("ansen", "拦截url:" + url);
            if (url.equals("http://www.google.com/")) {
                Toast.makeText(mContext, "国内不能访问google,拦截该url", Toast.LENGTH_LONG).show();
                return true;//表示我已经处理过了
            }
            return super.shouldOverrideUrlLoading(view, url);
        }

    };

    //WebChromeClient主要辅助WebView处理Javascript的对话框、网站图标、网站title、加载进度等
    private WebChromeClient webChromeClient = new WebChromeClient() {
        //不支持js的alert弹窗，需要自己监听然后通过dialog弹窗
        @Override
        public boolean onJsAlert(WebView webView, String url, String message, JsResult result) {
            AlertDialog.Builder localBuilder = new AlertDialog.Builder(webView.getContext());
            localBuilder.setMessage(message).setPositiveButton("确定", null);
            localBuilder.setCancelable(false);
            localBuilder.create().show();

            //注意:
            //必须要这一句代码:result.confirm()表示:
            //处理结果为确定状态同时唤醒WebCore线程
            //否则不能继续点击按钮
            result.confirm();
            return true;
        }

        //获取网页标题
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            Log.i("ansen", "网页标题:" + title);
        }

        //加载进度回调
        @Override
        public void onProgressChanged(WebView view, int newProgress) {

        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.i("ansen", "是否有上一个页面:" + dataBinding.webWb.canGoBack());
        if (dataBinding.webWb.canGoBack() && keyCode == KeyEvent.KEYCODE_BACK) {//点击返回按钮的时候判断有没有上一页
            dataBinding.webWb.goBack(); // goBack()表示返回webView的上一页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放资源
        dataBinding.webWb.destroy();
    }
}
