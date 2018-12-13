package com.fltry.androidlibs.view.pulllist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fltry.androidlibs.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FlowAdapter extends RecyclerView.Adapter<FlowAdapter.MyHolder> {

    private List<String> list;
    private Context context;
    String[] texts = new String[]{"数据","java","android","ios","web","python","go"};

    public FlowAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add(texts[new Random().nextInt(6)]);
        }
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(View.inflate(context, R.layout.flow_item, null));
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        holder.text.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        TextView text;

        public MyHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.flow_text);
        }
    }
}
