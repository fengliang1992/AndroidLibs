package com.fltry.androidlibs.utils.Dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;

import com.fltry.androidlibs.R;
import com.fltry.androidlibs.ui.BaseActivity;

import java.util.Random;

import butterknife.OnClick;

public class DialogActivity extends BaseActivity {

    private Dialog dialog;
    private FragmentDlg2 fragmentDlg2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getToolbarTitle().setText("Dialog");
    }

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


    @OnClick({R.id.dlg_btn1, R.id.dlg_btn2, R.id.dlg_btn3, R.id.dlg_btn4, R.id.dlg_btn5,
            R.id.dlg_btn6, R.id.dlg_btn7, R.id.dlg_btn8, R.id.dlg_btn9, R.id.dlg_btn10})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.dlg_btn1:
                initDialog();
                DialogUtli.dialogSize(dialog, 0.5, "width");
                break;
            case R.id.dlg_btn2:
                initDialog();
                DialogUtli.dialogSize(dialog, 0.5, "height");
                break;
            case R.id.dlg_btn3:
                initDialog();
                DialogUtli.dialogSize(dialog, 0.2, 0.1);
                break;
            case R.id.dlg_btn4:
                initDialog();
                DialogUtli.dialogSize(dialog, 0.5, 0.3);
                DialogUtli.dialogLocation(dialog, Gravity.TOP, 0, 0);
                break;
            case R.id.dlg_btn5:
                initDialog();
                DialogUtli.dialogSize(dialog, 0.5, 0.3);
                DialogUtli.dialogLocation(dialog, Gravity.BOTTOM, 0, 0);
                break;
            case R.id.dlg_btn6:
                initDialog();
                DialogUtli.dialogSize(dialog, 0.5, 0.3);
                DialogUtli.dialogLocation(dialog, Gravity.LEFT, 0, 0);
                break;
            case R.id.dlg_btn7:
                initDialog();
                DialogUtli.dialogSize(dialog, 0.5, 0.3);
                DialogUtli.dialogLocation(dialog, Gravity.RIGHT, 0, 0);
                break;
            case R.id.dlg_btn8:
                showLoadingDialog();
                break;
            case R.id.dlg_btn9:
                FragmentDlg fragmentDlg = new FragmentDlg();
                //第二个参数 "missiles" 是系统用于保存片段状态并在必要时进行恢复的唯一标记名称。
                //该标记还允许您通过调用 findFragmentByTag() 获取片段的句柄。
                fragmentDlg.show(getSupportFragmentManager(), "fragmentDlgTest");
                break;
            case R.id.dlg_btn10:
                fragmentDlg2 = new FragmentDlg2();
                fragmentDlg2.show(getSupportFragmentManager(), "fragmentDlgTest2");
                step = 0;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(0);
                    }
                }, 2000);
                break;
        }
    }

    int step = 0;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (fragmentDlg2 != null) {
                fragmentDlg2.setStep(step, new Random().nextInt(2)%2 + 1);
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
}
