package com.fltry.androidlibs.view.elastic;

import android.view.View;
import android.widget.ArrayAdapter;

import com.fltry.androidlibs.R;
import com.fltry.androidlibs.ui.ButterknifeActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ElasticActivity extends ButterknifeActivity {

    @BindView(R.id.elastic_list)
    ElasticListView elasticList;
    private Unbinder unbinder;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_elastic;
    }

    @Override
    protected String title() {
        return "弹性界面";
    }

    @Override
    protected void initView() {
        unbinder = ButterKnife.bind(this);
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            arrayList.add("第：" + (i + 1) + "个");
        }
        elasticList.setAdapter(new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, arrayList));
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        View view = getLayoutInflater().inflate(R.layout.activity_elastic, null);
        view.setOnTouchListener(new ElasticTouchListener());
        setContentView(view);
    }
}
