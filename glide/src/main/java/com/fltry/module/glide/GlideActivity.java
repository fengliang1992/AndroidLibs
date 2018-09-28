package com.fltry.module.glide;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;

import com.fltry.module.glide.databinding.ActivityGlideBinding;
import com.fltry.module.lib_common.Dialog;
import com.leochuan.CarouselLayoutManager;
import com.leochuan.CircleLayoutManager;
import com.leochuan.CircleScaleLayoutManager;
import com.leochuan.GalleryLayoutManager;
import com.leochuan.RotateLayoutManager;
import com.leochuan.ScaleLayoutManager;
import com.leochuan.ScrollHelper;

import java.util.List;

public class GlideActivity extends DataBindingActivity<ActivityGlideBinding> implements IGlideView {

    List<Movice.SubjectsBean> subjects;
    private String[] types;
    GlideVM glideVM;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_glide;
    }

    @Override
    protected String title() {
        return "glide+豆瓣API使用";
    }

    @Override
    protected void initView() {
        /*https://developers.douban.com/  豆瓣api首页*/
        types = new String[]{"环形滚动", "横向滑动", "环形缩放", "向前推进", "环绕效果", "旋转平移", "无特殊效果"};
        dataBinding.setType(types[0]);
        glideVM = new GlideVM(this);
        dataBinding.setGlideVM(glideVM);
        dataBinding.setCount(10 + "");
        glideVM.gitMovies();
    }

    private void setGvList(final List<Movice.SubjectsBean> subjects) {
        this.subjects = subjects;
        setMyRecyclerLayoutManage(new GridLayoutManager(mContext, 3));
    }

    private void setMyRecyclerLayoutManage(RecyclerView.LayoutManager layout) {
        dataBinding.glideGv.setLayoutManager(layout);
        dataBinding.glideGv.setItemAnimator(new DefaultItemAnimator());
        MyAdapter myAdapter = new MyAdapter(subjects, mContext);
        dataBinding.glideGv.setAdapter(myAdapter);
        myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                ScrollHelper.smoothScrollToTargetView(dataBinding.glideGv, v);
                String msg = "名字：" + subjects.get(position).getTitle()
                        + "\n类型：" + subjects.get(position).getGenres().toString()
                        + "\n上映时间：" + subjects.get(position).getYear();
                Dialog.getMyAlert(mContext, "电影详情", msg).show();
            }
        });
    }

    public void changeType(View v) {
        if (subjects == null) {
            glideVM.gitMovies();
        } else {
            new AlertDialog.Builder(mContext).setTitle("选择效果")
                    .setAdapter(new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, types),
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dataBinding.setType(types[which]);
                                    if (which == 0) {
                                        setMyRecyclerLayoutManage(new CircleLayoutManager(mContext));
                                    } else if (which == 1) {
                                        setMyRecyclerLayoutManage(new ScaleLayoutManager(mContext, 20));
                                    } else if (which == 2) {
                                        setMyRecyclerLayoutManage(new CircleScaleLayoutManager(mContext));
                                    } else if (which == 3) {
                                        setMyRecyclerLayoutManage(new CarouselLayoutManager(mContext, 100));
                                    } else if (which == 4) {
                                        setMyRecyclerLayoutManage(new GalleryLayoutManager(mContext, 20));
                                    } else if (which == 5) {
                                        setMyRecyclerLayoutManage(new RotateLayoutManager(mContext, 20));
                                    } else {
                                        setMyRecyclerLayoutManage(new GridLayoutManager(mContext, 3));
                                    }
                                }
                            }).show();
        }
    }

    @Override
    public void httpSucceed(Movice movice) {
        setGvList(movice.getSubjects());
    }

    @Override
    public void httpFailed(String error) {
        Dialog.getMyAlert(mContext, "错误提示", error).show();
    }

    @Override
    public String getCount() {
        return dataBinding.getCount();
    }
}
