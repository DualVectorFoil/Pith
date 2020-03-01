package com.dualvector.pith.mvp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.dualvector.pith.R;
import com.dualvector.pith.app.detail.ImageDetail;
import com.dualvector.pith.mvp.ui.viewholder.ImageFlowViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ImageFlowAdapter extends RecyclerView.Adapter<ImageFlowViewHolder> {

    private List<Integer> mTestList = new ArrayList<Integer>();

    private Context mContext;

    private List<ImageDetail> mList;

    private RequestOptions mGlideHeadViewOptions = RequestOptions.circleCropTransform()
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(false)
            .fallback(R.mipmap.ic_default_user_icon)
            .placeholder(R.mipmap.ic_default_user_icon)
            .error(R.mipmap.ic_default_user_icon);

    public ImageFlowAdapter(Context context, List<ImageDetail> list) {
        if (list == null) {
            throw new IllegalArgumentException("can not use null to init ImageFlowAdapter");
        }
        mContext = context;
        mList = list;
    }

    public void feedImages(List<ImageDetail> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ImageFlowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ImageView view = (ImageView) LayoutInflater.from(parent.getContext()).inflate(R.layout.account_image_item, parent, false);
        return new ImageFlowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageFlowViewHolder holder, int position) {
        Glide.with(mContext)
                .load(mList.get(position).getUrl())
                .apply(mGlideHeadViewOptions)
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
