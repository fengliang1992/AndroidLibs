package com.fltry.module.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;


/**
 * Created by tol on 2018-06-19.
 * 按步骤加载
 */

public class FragmentDlg2 extends DialogFragment {
    LinearLayout dlgTerminalRead1;
    Button dlgTerminalRead3;

    ArrayList<View> views;
    public static String[] titles = {"读取设备", "读取设备号", "读取ICCID"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.dlg_fragment, null);
        dlgTerminalRead1 = view.findViewById(R.id.dlg_terminal_read_1);
        dlgTerminalRead3 = view.findViewById(R.id.dlg_terminal_read_3);
        initView();
        return view;
    }

    private void initView() {
        views = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            LoadLinearLayout layout = new LoadLinearLayout(getContext());
            layout.setTitile(titles[i]);
            views.add(layout);
            dlgTerminalRead1.addView(layout);
        }

        dlgTerminalRead3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public void setStep(int step, int state) {
        LoadLinearLayout loadLayout = (LoadLinearLayout) views.get(step);
        loadLayout.setReadState(state);
    }

    @Override
    public void onStart() {
        super.onStart();
        DialogUtli.dialogSize(this, 0.8, "width");
        for (int i = 0; i < titles.length; i++) {
            setStep(i, LoadLinearLayout.STATE_INIT);
        }
    }
}
