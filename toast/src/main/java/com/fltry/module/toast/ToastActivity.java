package com.fltry.module.toast;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fltry.module.lib_common.BaseActivity;
import com.fltry.module.toast.databinding.ActivityToastBinding;

public class ToastActivity extends BaseActivity {

    ActivityToastBinding mBinding;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_toast;
    }

    @Override
    protected String title() {
        return "Toast";
    }


    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.arg1 <= 100) {
                if (msg.what == 0) {
                    ToastUtil.show(mContext, "进度：" + msg.arg1 + "%");
                } else {
                    ToastUtil3.showLong(mContext, "进度：" + msg.arg1 + "%");
                }
            } else {
                ToastUtil.show(mContext, "谢谢观赏");
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
        TextView textView = new TextView(mContext);
        textView.setText(messages);
        textView.setBackgroundColor(Color.YELLOW);
        textView.setTextColor(Color.RED);
        textView.setTextSize(30);
        toast.setView(textView);
        toast.show();
    }

    @Override
    protected void initView() {
        mBinding = (ActivityToastBinding) dataBinding;
    }

    public void customToast(View v) {
        ToastMessage("我是一个自定义的吐司");
    }

    public void loadingToast(View v) {
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
    }

    public void loadingToast2(View v) {
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
    }
}
