package com.fltry.module.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import com.fltry.module.lib_common.BaseActivity;

import java.util.Random;

public class DialogActivity extends BaseActivity implements View.OnClickListener {

    private Dialog dialog;
    private FragmentDlg2 fragmentDlg2;
    /**
     * 只设置Dialog的宽
     */
    private Button mDlgBtn1;
    /**
     * 只设置Dialog的高
     */
    private Button mDlgBtn2;
    /**
     * 设置Dialog的宽和高
     */
    private Button mDlgBtn3;
    /**
     * 设置Dialog的位置（上）
     */
    private Button mDlgBtn4;
    /**
     * 设置Dialog的位置（下）
     */
    private Button mDlgBtn5;
    /**
     * 设置Dialog的位置（左）
     */
    private Button mDlgBtn6;
    /**
     * 设置Dialog的位置（右）
     */
    private Button mDlgBtn7;
    /**
     * 多种加载中动效
     */
    private Button mDlgBtn8;
    /**
     * FragmentDialog
     */
    private Button mDlgBtn9;
    /**
     * FragmentDialog_自定义布局
     */
    private Button mDlgBtn10;

    private void initDialog() {
        dialog = new Dialog(mContext);
        View view = new View(mContext);
        view.setBackgroundColor(Color.RED);
        dialog.setContentView(view);
        dialog.show();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_dialog;
    }

    @Override
    protected String title() {
        return "Dialog";
    }

    int step = 0;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (fragmentDlg2 != null) {
                fragmentDlg2.setStep(step, new Random().nextInt(2) % 2 + 1);
                if (step < FragmentDlg2.titles.length - 1) {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            handler.sendEmptyMessage(0);
                        }
                    }, 2000);
                    step++;
                }
            }
        }
    };

    private void showLoadingDialog() {
        Dialog dialog = new Dialog(mContext, R.style.MyDialog2);
        View view = View.inflate(mContext, R.layout.dlg_loading, null);
        dialog.setContentView(view);
        dialog.show();
        DialogUtli.dialogSize(dialog, 1, "width");
    }

    @Override
    protected void initView() {
        mDlgBtn1 = (Button) findViewById(R.id.dlg_btn1);
        mDlgBtn1.setOnClickListener(this);
        mDlgBtn2 = (Button) findViewById(R.id.dlg_btn2);
        mDlgBtn2.setOnClickListener(this);
        mDlgBtn3 = (Button) findViewById(R.id.dlg_btn3);
        mDlgBtn3.setOnClickListener(this);
        mDlgBtn4 = (Button) findViewById(R.id.dlg_btn4);
        mDlgBtn4.setOnClickListener(this);
        mDlgBtn5 = (Button) findViewById(R.id.dlg_btn5);
        mDlgBtn5.setOnClickListener(this);
        mDlgBtn6 = (Button) findViewById(R.id.dlg_btn6);
        mDlgBtn6.setOnClickListener(this);
        mDlgBtn7 = (Button) findViewById(R.id.dlg_btn7);
        mDlgBtn7.setOnClickListener(this);
        mDlgBtn8 = (Button) findViewById(R.id.dlg_btn8);
        mDlgBtn8.setOnClickListener(this);
        mDlgBtn9 = (Button) findViewById(R.id.dlg_btn9);
        mDlgBtn9.setOnClickListener(this);
        mDlgBtn10 = (Button) findViewById(R.id.dlg_btn10);
        mDlgBtn10.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.dlg_btn1) {
            initDialog();
            DialogUtli.dialogSize(dialog, 0.5, "width");
        } else if (v.getId() == R.id.dlg_btn2) {
            initDialog();
            DialogUtli.dialogSize(dialog, 0.5, "height");
        } else if (v.getId() == R.id.dlg_btn3) {
            initDialog();
            DialogUtli.dialogSize(dialog, 0.2, 0.1);
        } else if (v.getId() == R.id.dlg_btn4) {
            initDialog();
            DialogUtli.dialogSize(dialog, 0.5, 0.3);
            DialogUtli.dialogLocation(dialog, Gravity.TOP, 0, 0);
        } else if (v.getId() == R.id.dlg_btn5) {
            initDialog();
            DialogUtli.dialogSize(dialog, 0.5, 0.3);
            DialogUtli.dialogLocation(dialog, Gravity.BOTTOM, 0, 0);
        } else if (v.getId() == R.id.dlg_btn6) {
            initDialog();
            DialogUtli.dialogSize(dialog, 0.5, 0.3);
            DialogUtli.dialogLocation(dialog, Gravity.LEFT, 0, 0);
        } else if (v.getId() == R.id.dlg_btn7) {
            initDialog();
            DialogUtli.dialogSize(dialog, 0.5, 0.3);
            DialogUtli.dialogLocation(dialog, Gravity.RIGHT, 0, 0);
        } else if (v.getId() == R.id.dlg_btn8) {
            showLoadingDialog();
        } else if (v.getId() == R.id.dlg_btn9) {
            FragmentDlg fragmentDlg = new FragmentDlg();
            //第二个参数 "missiles" 是系统用于保存片段状态并在必要时进行恢复的唯一标记名称。
            //该标记还允许您通过调用 findFragmentByTag() 获取片段的句柄。
            fragmentDlg.show(getSupportFragmentManager(), "fragmentDlgTest");
        } else if (v.getId() == R.id.dlg_btn10) {
            fragmentDlg2 = new FragmentDlg2();
            fragmentDlg2.show(getSupportFragmentManager(), "fragmentDlgTest2");
            step = 0;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    handler.sendEmptyMessage(0);
                }
            }, 2000);
        }
    }
}
