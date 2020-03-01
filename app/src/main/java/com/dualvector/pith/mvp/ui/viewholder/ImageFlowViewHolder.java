package com.dualvector.pith.mvp.ui.viewholder;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ImageFlowViewHolder extends RecyclerView.ViewHolder {

    public ImageView mImageView;

    public ImageFlowViewHolder(@NonNull ImageView itemView) {
        super(itemView);
        mImageView = itemView;
    }
}
