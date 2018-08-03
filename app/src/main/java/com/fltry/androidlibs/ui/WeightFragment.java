package com.fltry.androidlibs.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.fltry.androidlibs.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeightFragment extends Fragment {

    View view;
    @BindView(R.id.weight_lv)
    ListView weightLv;

    private ArrayList<ClassBean> classes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) { // 之前的view 已经记录了一个fragment了 fragment是之前的ViewPager
            view = inflater.inflate(R.layout.view_widget, null);
            ButterKnife.bind(this, view);
        } else {// 移除view之前的fragment
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                ViewGroup group = (ViewGroup) parent;
                group.removeView(view);
            }
        }
        initView();
        return view;// 拿到当前viewPager 添加这个view
    }

    private void initView() {
        classes = ((MainActivity) getContext()).allClasses.get(ClassUtils.WEIGHT_FRAGMENT);
        weightLv.setAdapter(new MyClassAdapter(getContext(), classes));

        weightLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position > classes.size() - 1) {
                    Toast.makeText(getContext(), "index越界", Toast.LENGTH_SHORT).show();
                    return;
                }
                startActivity(new Intent(Intent.ACTION_VIEW, classes.get(position).getUri()));
            }
        });
    }
}
