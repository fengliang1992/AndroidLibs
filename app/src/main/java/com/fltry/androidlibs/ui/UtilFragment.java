package com.fltry.androidlibs.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.fltry.androidlibs.R;
import com.fltry.androidlibs.utils.Dialog.DialogActivity;
import com.fltry.androidlibs.utils.excption.ExcptionActivity;
import com.fltry.androidlibs.utils.screen.ScreenUtilActivity;
import com.fltry.androidlibs.utils.sp.SPActivity;
import com.fltry.androidlibs.utils.toast.ToastActivity;
import com.fltry.androidlibs.utils.toast.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tol on 2018-06-13.
 */

public class UtilFragment extends Fragment {

    View view;
    @BindView(R.id.util_lv)
    ListView utilLv;

    private List<Class> classes = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) { // 之前的view 已经记录了一个fragment了 fragment是之前的ViewPager
            view = inflater.inflate(R.layout.view_util, null);
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
        classes.add(DialogActivity.class);
        classes.add(ScreenUtilActivity.class);
        classes.add(ExcptionActivity.class);
        classes.add(SPActivity.class);
        classes.add(ToastActivity.class);

        String[] utilList = getResources().getStringArray(R.array.utilList);
        utilLv.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, utilList));

        utilLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position > classes.size() - 1) {
                    ToastUtil.show(getContext(), "index越界");
                    return;
                }
                startActivity(new Intent(getContext(), classes.get(position)));
            }
        });
    }

}
