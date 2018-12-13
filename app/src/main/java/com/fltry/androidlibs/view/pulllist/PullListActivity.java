package com.fltry.androidlibs.view.pulllist;

import android.util.TypedValue;

import com.fltry.androidlibs.R;
import com.fltry.androidlibs.databinding.ActivityPullListBinding;
import com.fltry.module.lib_common.BaseActivity;

public class PullListActivity extends BaseActivity<ActivityPullListBinding> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pull_list;
    }

    @Override
    protected String title() {
        return "标签布局";
    }

    @Override
    protected void initView() {
        //设置每一个item间距
        dataBinding.pullListRv.addItemDecoration(new SpaceItemDecoration(dp2px(10)));
        dataBinding.pullListRv.setLayoutManager(new FlowLayoutManager());
        dataBinding.pullListRv.setAdapter(new FlowAdapter(mContext));
    }

    private int dp2px(float value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, mContext.getResources().getDisplayMetrics());
    }
}
