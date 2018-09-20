package com.fltry.module.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;

import com.fltry.module.dialog.databinding.ActivityDialogBinding;
import com.fltry.module.lib_common.BaseActivity;

import java.util.Random;

public class DialogActivity extends BaseActivity {

    private Dialog dialog;
    private FragmentDlg2 fragmentDlg2;

    ActivityDialogBinding mBinding;

    private void initDialog() {
        mBinding = (ActivityDialogBinding) dataBinding;
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

    }

    public void onlyWidth(View v) {
        initDialog();
        DialogUtli.dialogSize(dialog, 0.5, "width");
    }

    public void onlyHeight(View v) {
        initDialog();
        DialogUtli.dialogSize(dialog, 0.5, "height");
    }

    public void widthAndHeight(View v) {
        initDialog();
        DialogUtli.dialogSize(dialog, 0.2, 0.1);
    }

    public void positionTop(View v) {
        initDialog();
        DialogUtli.dialogSize(dialog, 0.5, 0.3);
        DialogUtli.dialogLocation(dialog, Gravity.TOP, 0, 0);
    }

    public void positionBottom(View v) {
        initDialog();
        DialogUtli.dialogSize(dialog, 0.5, 0.3);
        DialogUtli.dialogLocation(dialog, Gravity.BOTTOM, 0, 0);
    }

    public void positionLeft(View v) {
        initDialog();
        DialogUtli.dialogSize(dialog, 0.5, 0.3);
        DialogUtli.dialogLocation(dialog, Gravity.START, 0, 0);
    }

    public void positionRight(View v) {
        initDialog();
        DialogUtli.dialogSize(dialog, 0.5, 0.3);
        DialogUtli.dialogLocation(dialog, Gravity.END, 0, 0);
    }

    public void loading(View v) {
        showLoadingDialog();
    }

    public void fragmentDialog(View v) {
        FragmentDlg fragmentDlg = new FragmentDlg();
        //第二个参数 "missiles" 是系统用于保存片段状态并在必要时进行恢复的唯一标记名称。
        //该标记还允许您通过调用 findFragmentByTag() 获取片段的句柄。
        fragmentDlg.show(getSupportFragmentManager(), "fragmentDlgTest");
    }

    public void fragmentDialog2(View v) {
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
