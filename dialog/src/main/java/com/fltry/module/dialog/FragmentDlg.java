package com.fltry.module.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

public class FragmentDlg extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("FragmentDialog");
        builder.setMessage("这是一个FragmentDialog");
        builder.setNegativeButton("隐藏", null);
        builder.setPositiveButton("知道了", null);
        return builder.create();
    }
}
