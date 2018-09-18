package com.fltry.module.glide;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.fltry.module.glide.databinding.ActivityGlideBinding;
import com.fltry.module.lib_common.BaseActivity;
import com.fltry.module.lib_common.Dialog;
import com.leochuan.CarouselLayoutManager;
import com.leochuan.CircleLayoutManager;
import com.leochuan.CircleScaleLayoutManager;
import com.leochuan.GalleryLayoutManager;
import com.leochuan.RotateLayoutManager;
import com.leochuan.ScaleLayoutManager;
import com.leochuan.ScrollHelper;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class GlideActivity extends DataBindingActivity {

    List<Movice.SubjectsBean> subjects;
    private Retrofit mRetrofit;

    protected ActivityGlideBinding mBinding;
    private String[] types;

    @Override
    protected String title() {
        return "glide+豆瓣API使用";
    }

    @Override
    protected void initView() {
        /*https://developers.douban.com/  豆瓣api首页*/
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_glide);
        types = new String[]{"环形滚动", "横向滑动", "环形缩放", "向前推进", "环绕效果", "旋转平移", "无特殊效果"};
        mBinding.setTypes(types);
        mBinding.setIndex(6);

        OkHttpClient okHttpClient;
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(12, TimeUnit.SECONDS);
        builder.readTimeout(60, TimeUnit.SECONDS);
        okHttpClient = builder.build();


        mRetrofit = new Retrofit.Builder().client(okHttpClient)
                .baseUrl("https://api.douban.com")
                .addConverterFactory(ScalarsConverterFactory.create())//增加返回值为String的支持
                .addConverterFactory(GsonConverterFactory.create())//增加返回值为Gson的支持(以实体类返回)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//增加返回值为Oservable<T>的支持
                .build();

        getPic();
    }


    private void getPic() {
        mRetrofit.create(ApiService.class)
                .getMovice(250)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Movice>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Movice movice) {
                        setGvList(movice.getSubjects());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Dialog.getMyAlert(mContext, "错误提示", e.getMessage()).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void setGvList(final List<Movice.SubjectsBean> subjects) {
        this.subjects = subjects;
        setMyRecyleLayoutManage(new GridLayoutManager(mContext, 3));
    }

    private void setMyRecyleLayoutManage(RecyclerView.LayoutManager layout) {
        mBinding.glideGv.setLayoutManager(layout);
        mBinding.glideGv.setItemAnimator(new DefaultItemAnimator());
        MyAdapter myAdapter = new MyAdapter(subjects, mContext);
        mBinding.glideGv.setAdapter(myAdapter);
        myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                ScrollHelper.smoothScrollToTargetView(mBinding.glideGv, v);
                String msg = "名字：" + subjects.get(position).getTitle()
                        + "\n类型：" + subjects.get(position).getGenres().toString()
                        + "\n上映时间：" + subjects.get(position).getYear();
                Dialog.getMyAlert(mContext, "电影详情", msg).show();
            }
        });
    }

    public void changeType(View v) {
        if (subjects == null) {
            getPic();
        } else {
            new AlertDialog.Builder(mContext).setTitle("选择效果")
                    .setAdapter(new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, types),
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    mBinding.setIndex(which);
                                    if (which == 0) {
                                        setMyRecyleLayoutManage(new CircleLayoutManager(mContext));
                                    } else if (which == 1) {
                                        setMyRecyleLayoutManage(new ScaleLayoutManager(mContext, 20));
                                    } else if (which == 2) {
                                        setMyRecyleLayoutManage(new CircleScaleLayoutManager(mContext));
                                    } else if (which == 3) {
                                        setMyRecyleLayoutManage(new CarouselLayoutManager(mContext, 100));
                                    } else if (which == 4) {
                                        setMyRecyleLayoutManage(new GalleryLayoutManager(mContext, 20));
                                    } else if (which == 5) {
                                        setMyRecyleLayoutManage(new RotateLayoutManager(mContext, 20));
                                    } else {
                                        setMyRecyleLayoutManage(new GridLayoutManager(mContext, 3));
                                    }
                                }
                            }).show();
        }
    }

}
