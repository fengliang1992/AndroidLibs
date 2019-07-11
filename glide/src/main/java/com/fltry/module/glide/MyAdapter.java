package com.fltry.module.glide;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

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
        holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(v, holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }
}

