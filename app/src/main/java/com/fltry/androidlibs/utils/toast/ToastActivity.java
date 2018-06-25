package com.fltry.androidlibs.utils.toast;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fltry.androidlibs.R;
import com.fltry.androidlibs.ui.BaseActivity;

import butterknife.OnClick;

public class ToastActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getToolbarTitle().setText("Toast");
    }

    int i = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_toast;
    }

    @OnClick({R.id.toast_btn1, R.id.toast_btn2, R.id.toast_btn3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toast_btn1:
                ToastMessage("我是一个自定义的吐司");
                break;
            case R.id.toast_btn2:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 101; i++) {
                            Message msg = new Message();
                            msg.what = 0;
                            msg.arg1 = i;
                            handler.sendMessage(msg);
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
                break;
            case R.id.toast_btn3:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 101; i++) {
                            Message msg = new Message();
                            msg.what = 1;
                            msg.arg1 = i;
                            handler.sendMessage(msg);
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
                break;
        }
    }

    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.arg1 <= 100) {
                if (msg.what == 0) {
                    ToastUtil.show(getMContext(), "进度：" + msg.arg1 + "%");
                } else {
                    ToastUtil3.showLong(getMContext(),"进度：" + msg.arg1 + "%");
                }
            } else {
                ToastUtil.show(getMContext(), "谢谢观赏");
            }
        }
    };


    /**
     * 将Toast封装在一个方法中，在其它地方使用时直接输入要弹出的内容即可
     */
    private void ToastMessage(String messages) {
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.TOP, 0, 20);//setGravity用来设置Toast显示的位置，相当于xml中的android:gravity或android:layout_gravity
        toast.setDuration(Toast.LENGTH_LONG);//setDuration方法：设置持续时间，以毫秒为单位。该方法是设置补间动画时间长度的主要方法
        TextView textView = new TextView(getMContext());
        textView.setText(messages);
        textView.setBackgroundColor(Color.YELLOW);
        textView.setTextColor(Color.RED);
        textView.setTextSize(30);
        toast.setView(textView);
        toast.show();
    }
}
