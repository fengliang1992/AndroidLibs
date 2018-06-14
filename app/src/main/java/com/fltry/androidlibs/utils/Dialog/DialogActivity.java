package com.fltry.androidlibs.utils.Dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.fltry.androidlibs.R;
import com.fltry.androidlibs.ui.BaseActivity;
import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.SpriteFactory;
import com.github.ybq.android.spinkit.Style;
import com.github.ybq.android.spinkit.sprite.Sprite;

import butterknife.OnClick;

public class DialogActivity extends BaseActivity {

    private Dialog dialog;

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
            R.id.dlg_btn6, R.id.dlg_btn7, R.id.dlg_btn8})
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
        }
    }

    private void showLoadingDialog() {
        Dialog dialog = new Dialog(mContext, R.style.MyDialog2);
        View view = View.inflate(mContext, R.layout.dlg_loading, null);
        dialog.setContentView(view);
        dialog.show();
        DialogUtli.dialogSize(dialog, 1, "width");
    }
}
