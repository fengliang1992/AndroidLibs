package com.fltry.androidlibs.view.refresh;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.fltry.androidlibs.R;
import com.fltry.androidlibs.ui.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RefreshActivity extends BaseActivity {

    @BindView(R.id.refresh_lv)
    ListView refreshLv;
    @BindView(R.id.refresh_rl)
    RefreshLayout refreshRl;
    private ArrayList<String> arrayList1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getToolbarTitle().setText("刷新");

        initView();
    }

    private void initView() {
        arrayList1 = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            arrayList1.add("第" + (i + 1) + "个");
        }
        refreshLv.setAdapter(new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, arrayList1));

        refreshRl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        handler.sendEmptyMessage(0);
                    }
                }).start();
            }
        });

        refreshRl.setOnLoadListener(new RefreshLayout.OnLoadListener() {
            @Override
            public void onLoad() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        handler.sendEmptyMessage(1);
                    }
                }).start();
            }
        });
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            refreshRl.setRefreshing(false);
            refreshRl.setLoading(false);
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_refresh;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_refresh)
        TextView itemRefresh;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
