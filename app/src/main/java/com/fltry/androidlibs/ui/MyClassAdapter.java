package com.fltry.androidlibs.ui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fltry.androidlibs.R;

import java.util.ArrayList;

public class MyClassAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ClassBean> classes;

    public MyClassAdapter(Context context, ArrayList<ClassBean> classes) {
        this.context = context;
        this.classes = classes;
    }

    @Override
    public int getCount() {
        return classes.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(context, R.layout.item_classes, null);
        TextView textView = convertView.findViewById(R.id.item_classes_tv);
        textView.setText(classes.get(position).getName());
        return convertView;
    }
}
