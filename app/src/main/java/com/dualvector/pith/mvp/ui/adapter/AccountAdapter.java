package com.dualvector.pith.mvp.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AccountAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<ViewGroup> mLayoutList;

    public enum ITEM_TYPE {
        BACKGROUND_ZONE,
        ACCOUNT_INFO,
        BLANK_ZONE,
        SOCIAL_INFO,
        PICTURE_INFO,
        BOTTOM_ZONE,
    }

    public AccountAdapter(Context context, List<ViewGroup> list) {
        mContext = context;
        mLayoutList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutList.get(viewType);
        parent.addView(view);

        if (viewType == ITEM_TYPE.BACKGROUND_ZONE.ordinal()) {
            return new BackgroundViewHolder(view);
        } else if (viewType == ITEM_TYPE.ACCOUNT_INFO.ordinal()) {
            return new AccountInfoViewHolder(view);
        } else if (viewType == ITEM_TYPE.BLANK_ZONE.ordinal()) {
            return new BlankSpaceViewHolder(view);
        } else if (viewType == ITEM_TYPE.SOCIAL_INFO.ordinal()) {
            return new SocialInfoViewHolder(view);
        } else if (viewType == ITEM_TYPE.PICTURE_INFO.ordinal()) {
            return new PictureInfoViewHolder(view);
        } else if (viewType == ITEM_TYPE.BOTTOM_ZONE.ordinal()) {
            return new BottomZoneViewHolder(view);
        }
        throw new IllegalArgumentException("should not be other viewType");
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return ITEM_TYPE.BACKGROUND_ZONE.ordinal();
            case 1:
                return ITEM_TYPE.ACCOUNT_INFO.ordinal();
            case 2:
                return ITEM_TYPE.BLANK_ZONE.ordinal();
            case 3:
                return ITEM_TYPE.SOCIAL_INFO.ordinal();
            case 4:
                return ITEM_TYPE.PICTURE_INFO.ordinal();
            case 5:
                return ITEM_TYPE.BOTTOM_ZONE.ordinal();
            default:
        }
        throw new IllegalArgumentException("This recyclerview should hava only 6 items");
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public static class BlankSpaceViewHolder extends RecyclerView.ViewHolder {

        public BlankSpaceViewHolder(View view) {
            super(view);
        }
    }

    public static class BackgroundViewHolder extends RecyclerView.ViewHolder {

        public BackgroundViewHolder(View view) {
            super(view);
        }
    }

    public static class AccountInfoViewHolder extends RecyclerView.ViewHolder {

        public AccountInfoViewHolder(View view) {
            super(view);
        }
    }

    public static class SocialInfoViewHolder extends RecyclerView.ViewHolder {

        public SocialInfoViewHolder(View view) {
            super(view);
        }
    }

    public static class PictureInfoViewHolder extends RecyclerView.ViewHolder {

        public PictureInfoViewHolder(View view) {
            super(view);
        }
    }

    public static class BottomZoneViewHolder extends RecyclerView.ViewHolder {

        public BottomZoneViewHolder(View view) {
            super(view);
        }
    }
}
