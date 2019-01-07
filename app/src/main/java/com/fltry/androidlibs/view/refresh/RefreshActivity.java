package com.fltry.androidlibs.view.refresh;

import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.fltry.androidlibs.R;
import com.fltry.module.lib_common.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RefreshActivity extends BaseActivity {

    @BindView(R.id.refresh_lv)
    ListView refreshLv;
    @BindView(R.id.refresh_rl)
    RefreshLayout refreshRl;
    @BindView(R.id.refresh_rv)
    RecyclerView refreshRv;
    @BindView(R.id.refresh_r2)
    SwipeRefreshLayout refreshR2;
    @BindView(R.id.loadmore_footer_view)
    RelativeLayout loadmore_footer_view;
    private ArrayList<String> arrayList1;
    private MyAdapter myAdapter;
    private Unbinder unbinder;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    protected void initView() {
        unbinder = ButterKnife.bind(this);
        arrayList1 = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            arrayList1.add("第" + (i + 1) + "个");
        }
        myAdapter = new MyAdapter(mContext, arrayList1);
        refreshLv.setAdapter(new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, arrayList1));

        refreshRv.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        refreshRv.setLayoutManager(layoutManager);
        refreshRv.setAdapter(myAdapter);

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
        refreshR2.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
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
                        handler.sendEmptyMessage(2);
                    }
                }).start();
            }
        });
        refreshRv.addOnScrollListener(new EndLessOnScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                Log.i("aaa", "记载更改");
                loadmore_footer_view.setVisibility(View.VISIBLE);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        handler.sendEmptyMessage(2);
                    }
                }).start();
            }
        });
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    refreshRl.setRefreshing(false);
                    refreshRl.setLoading(false);
                    break;
                case 2:
                    refreshR2.setRefreshing(false);
                    loadmore_footer_view.setVisibility(View.GONE);
                    break;
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_refresh;
    }

    @Override
    protected String title() {
        return "刷新";
    }

}
