package com.fltry.module.glide;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fltry.module.lib_common.BaseActivity;
import com.fltry.module.lib_common.Dialog;
import com.leochuan.CarouselLayoutManager;
import com.leochuan.CenterSnapHelper;
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

public class GlideActivity extends BaseActivity {

    private RecyclerView mGlideGv;
    private Button glideBtn;
    List<Movice.SubjectsBean> subjects;
    private Retrofit mRetrofit;
    String[] types;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarTitle("glide+豆瓣API使用");
        /*https://developers.douban.com/  豆瓣api首页*/
        initView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_glide;
    }

    private void initView() {
        mGlideGv = (RecyclerView) findViewById(R.id.glide_gv);
        glideBtn = (Button) findViewById(R.id.glide_btn);
        types = new String[]{"环形滚动", "横向滑动", "环形缩放", "向前推进", "环绕效果", "旋转平移", "无"};

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
        glideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (subjects == null) {
                    getPic();
                    return;
                }
                new AlertDialog.Builder(mContext).setTitle("选择效果")
                        .setAdapter(new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, types),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        glideBtn.setText(types[which]);
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
        });

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
        mGlideGv.setLayoutManager(layout);
        mGlideGv.setItemAnimator(new DefaultItemAnimator());
        MyAdapter myAdapter = new MyAdapter(subjects, mContext);
        mGlideGv.setAdapter(myAdapter);
        myAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                ScrollHelper.smoothScrollToTargetView(mGlideGv, v);
                String msg = "名字：" + subjects.get(position).getTitle()
                        + "\n类型：" + subjects.get(position).getGenres().toString()
                        + "\n上映时间：" + subjects.get(position).getYear();
                Dialog.getMyAlert(mContext, "电影详情", msg).show();
            }
        });
    }

    public class MyAdapter extends RecyclerView.Adapter<ViewHolder> {
        private List<Movice.SubjectsBean> subjects;
        private Context mContext;
        private OnItemClickListener onItemClickListener;

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }

        public MyAdapter(List<Movice.SubjectsBean> subjects, Context mContext) {
            this.subjects = subjects;
            this.mContext = mContext;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(View.inflate(mContext, R.layout.glide_item_movie, null));
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            Glide.with(mContext)
                    .load(subjects.get(position).getImages().getMedium())
                    .placeholder(R.mipmap.ic_launcher)
                    .into(holder.imageView);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(v, holder.getAdapterPosition());
                }
            });
        }

        @Override
        public int getItemCount() {
            return subjects.size();
        }

    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.glide_item_iv);
        }
    }

}
