package com.fltry.androidlibs.view.elastic;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.fltry.androidlibs.R;
import com.fltry.androidlibs.ui.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;

public class ElasticActivity extends BaseActivity {

    @BindView(R.id.elastic_list)
    ElasticListView elasticList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getToolbarTitle().setText("弹性界面");

        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            arrayList.add("第：" + (i + 1) + "个");
        }
        elasticList.setAdapter(new ArrayAdapter<>(getMContext(), android.R.layout.simple_list_item_1, arrayList));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_elastic;
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        View view = getLayoutInflater().inflate(R.layout.activity_elastic, null);
        view.setOnTouchListener(new ElasticTouchListener());
        setContentView(view);
    }
}
