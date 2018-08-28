package com.fltry.module.glide;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fltry.module.lib_common.BaseActivity;
import com.fltry.module.lib_common.Dialog;

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


        OkHttpClient okHttpClient;
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(12, TimeUnit.SECONDS);
        builder.readTimeout(60, TimeUnit.SECONDS);
        okHttpClient = builder.build();
        Retrofit mRetrofit = new Retrofit.Builder().client(okHttpClient)
                .baseUrl("https://api.douban.com")
                .addConverterFactory(ScalarsConverterFactory.create())//增加返回值为String的支持
                .addConverterFactory(GsonConverterFactory.create())//增加返回值为Gson的支持(以实体类返回)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//增加返回值为Oservable<T>的支持
                .build();

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
        //270*382
        mGlideGv.setLayoutManager(new GridLayoutManager(mContext, 3));
        mGlideGv.setItemAnimator(new DefaultItemAnimator());
        MyAdapter myAdapter = new MyAdapter(subjects, mContext);
        mGlideGv.setAdapter(myAdapter);
        myAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String msg = "名字：" + subjects.get(position).getTitle()
                        + "\n类型：" + subjects.get(position).getGenres().toString()
                        + "\n上映时间：" + subjects.get(position).getYear();
                Dialog.getMyAlert(mContext,"电影详情",msg).show();
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
        public void onBindViewHolder(ViewHolder holder, final int position) {
            Glide.with(mContext)
                    .load(subjects.get(position).getImages().getMedium())
                    .into(holder.imageView);
            holder.title.setText(subjects.get(position).getTitle());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(position);
                }
            });
        }

        @Override
        public int getItemCount() {
            return subjects.size();
        }

    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.glide_item_iv);
            title = itemView.findViewById(R.id.glide_item_tv);
        }
    }

}
