package com.fltry.module.glide;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

public class ViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;

    public ViewHolder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.glide_item_iv);
    }
}
