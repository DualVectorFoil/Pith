package com.dualvector.pith.mvp.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.dualvector.pith.R;
import com.dualvector.pith.di.component.DaggerFrAccountComponent;
import com.dualvector.pith.di.module.FrAccountModule;
import com.dualvector.pith.mvp.base.BaseFragment;
import com.dualvector.pith.mvp.contract.FrAccountContract;
import com.dualvector.pith.mvp.model.bean.ImageDetailBean;
import com.dualvector.pith.mvp.presenter.FrAccountPresenter;
import com.dualvector.pith.mvp.ui.adapter.ImageFlowAdapter;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.jpush.android.api.JPushInterface;

public class AccountFragment extends BaseFragment<FrAccountPresenter> implements FrAccountContract.IFrAccountView {

    private static final String TAG = "FrAccount_Tag_Fragment";

    private int mToolbarHeight;
    private int mAppBarLayoutHeight;
    private int mTabLayoutHeight;

    @BindView(R.id.account_tool_bar)
    protected Toolbar mToolbar;
    @BindView(R.id.account_tab_layout)
    protected TabLayout mTabLayout;
    @BindView(R.id.account_activity_view_pager)
    protected ViewPager mViewPager;
    @BindView(R.id.account_app_bar_layout)
    protected AppBarLayout mAppBarLayout;

    private List<View> mPagerList;
    private SmartRefreshLayout mSelfRefreshLayout;
    private RecyclerView mSelfRV;
    private ImageFlowAdapter mSelfAdapter;
    private SmartRefreshLayout mStarRefreshLayout;
    private RecyclerView mStarRV;
    private ImageFlowAdapter mStarAdapter;
    private SmartRefreshLayout mAtRefreshLayout;
    private RecyclerView mAtRV;
    private ImageFlowAdapter mAtAdapter;

    private Activity mActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DaggerFrAccountComponent.builder().frAccountModule(new FrAccountModule(this)).build().inject(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_account;
    }

    @Override
    public void initView() {
        mActivity = getActivity();
        mToolbar.post(() -> mToolbarHeight = mToolbar.getHeight());
        mAppBarLayout.post(() -> mAppBarLayoutHeight = mAppBarLayout.getHeight());
        mTabLayout.post(() -> mTabLayoutHeight = mTabLayout.getHeight());

        // init viewpager
        initPagerList();
        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return mPagerList.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                container.addView(mPagerList.get(position));
                return mPagerList.get(position);
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView(mPagerList.get(position));
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return "";
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        if (mSelfAdapter.getItemCount() == 0) {
                            mSelfRefreshLayout.autoRefresh();
                        }
                        break;
                    case 1:
                        if (mStarAdapter.getItemCount() == 0) {
                            mStarRefreshLayout.autoRefresh();
                        }
                        break;
                    case 2:
                        if (mAtAdapter.getItemCount() == 0) {
                            mAtRefreshLayout.autoRefresh();
                        }
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        // init tablayout
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0).setIcon(R.mipmap.ic_account_self_icon);
        mTabLayout.getTabAt(1).setIcon(R.mipmap.ic_account_start_icon);
        mTabLayout.getTabAt(2).setIcon(R.mipmap.ic_account_at_icon);

        // init appbarlayout
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (mToolbarHeight == 0 && mTabLayoutHeight == 0 && mAppBarLayoutHeight == 0 && verticalOffset == 0) {
                    return;
                }

                mToolbar.setAlpha((float) (-verticalOffset) / (mAppBarLayoutHeight - mToolbarHeight));
                if (mToolbar.getAlpha() >= 1.0f) {
                    mToolbar.setFocusable(true);
                    mToolbar.setClickable(true);
                } else {
                    mToolbar.setFocusable(false);
                    mToolbar.setClickable(false);
                }
            }
        });
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void onResume() {
        super.onResume();
        mSelfRefreshLayout.autoRefresh();
    }

    @Override
    public void onGetSelfImagesSuccess(int imagesRefreshType, List<ImageDetailBean.DataBean> bean) {
        if (imagesRefreshType == FrAccountContract.FLAG_REFRESH) {
            mSelfAdapter.clearImages();
            mSelfRefreshLayout.finishRefresh();
        } else {
            mSelfRefreshLayout.finishLoadMore();
        }
        mSelfAdapter.feedImages(bean);
    }

    @Override
    public void onGetStartImagesSuccess(int imagesRefreshType, List<ImageDetailBean.DataBean> bean) {
        if (imagesRefreshType == FrAccountContract.FLAG_REFRESH) {
            mStarAdapter.clearImages();
            mStarRefreshLayout.finishRefresh();
        } else {
            mStarRefreshLayout.finishLoadMore();
        }
        mStarAdapter.feedImages(bean);
    }

    @Override
    public void onGetAtImagesSuccess(int imagesRefreshType, List<ImageDetailBean.DataBean> bean) {
        if (imagesRefreshType == FrAccountContract.FLAG_REFRESH) {
            mAtAdapter.clearImages();
            mAtRefreshLayout.finishRefresh();
        } else {
            mAtRefreshLayout.finishLoadMore();
        }
        mAtAdapter.feedImages(bean);
    }

    @Override
    public void onGetSelfImagesFailure(int imagesRefreshType, String errMsg) {
        if (imagesRefreshType == FrAccountContract.FLAG_REFRESH) {
            mSelfRefreshLayout.finishRefresh();
        } else {
            mSelfRefreshLayout.finishLoadMore();
        }
    }

    @Override
    public void onGetStarImagesFailure(int imagesRefreshType, String errMsg) {
        if (imagesRefreshType == FrAccountContract.FLAG_REFRESH) {
            mStarRefreshLayout.finishRefresh();
        } else {
            mStarRefreshLayout.finishLoadMore();
        }
        Log.w(TAG, "onGetStarImagesFailure failed, refresh type: " + imagesRefreshType + ", err: " + errMsg);
    }

    @Override
    public void onGetAtImagesFailure(int imagesRefreshType, String errMsg) {
        if (imagesRefreshType == FrAccountContract.FLAG_REFRESH) {
            mAtRefreshLayout.finishRefresh();
        } else {
            mAtRefreshLayout.finishLoadMore();
        }
        Log.w(TAG, "onGetAtImagesFailure failed, refresh type: " + imagesRefreshType + ", err: " + errMsg);
    }

    private void initPagerList() {
        mSelfRefreshLayout = (SmartRefreshLayout) mActivity.getLayoutInflater().inflate(R.layout.recycler_view_page_item, null);
        mStarRefreshLayout = (SmartRefreshLayout) mActivity.getLayoutInflater().inflate(R.layout.recycler_view_page_item, null);
        mAtRefreshLayout = (SmartRefreshLayout) mActivity.getLayoutInflater().inflate(R.layout.recycler_view_page_item, null);

        mSelfRefreshLayout.setOnRefreshListener((@NonNull RefreshLayout refreshLayout) -> {
            mPresenter.getSelfImages(FrAccountContract.FLAG_REFRESH, 0);
        });
        mSelfRefreshLayout.setOnLoadMoreListener((@NonNull RefreshLayout refreshLayout) -> {
            mPresenter.getSelfImages(FrAccountContract.FLAG_LOAD_MORE, mSelfAdapter.getItemCount());
        });
        mStarRefreshLayout.setOnRefreshListener((@NonNull RefreshLayout refreshLayout) -> {
            mPresenter.getStarImages(FrAccountContract.FLAG_REFRESH, 0);
        });
        mStarRefreshLayout.setOnLoadMoreListener((@NonNull RefreshLayout refreshLayout) -> {
            mPresenter.getStarImages(FrAccountContract.FLAG_LOAD_MORE, mStarAdapter.getItemCount());
        });
        mAtRefreshLayout.setOnRefreshListener((@NonNull RefreshLayout refreshLayout) -> {
            mPresenter.getAtImages(FrAccountContract.FLAG_REFRESH, 0);
        });
        mAtRefreshLayout.setOnLoadMoreListener((@NonNull RefreshLayout refreshLayout) -> {
            mPresenter.getAtImages(FrAccountContract.FLAG_LOAD_MORE, mAtAdapter.getItemCount());
        });

        mSelfRV = mSelfRefreshLayout.findViewById(R.id.account_recycler_view);
        mStarRV = mStarRefreshLayout.findViewById(R.id.account_recycler_view);
        mAtRV = mAtRefreshLayout.findViewById(R.id.account_recycler_view);

        FlexboxLayoutManager flexboxLayoutManager1 = new FlexboxLayoutManager(mActivity);
        flexboxLayoutManager1.setFlexDirection(FlexDirection.ROW);
        flexboxLayoutManager1.setFlexWrap(FlexWrap.WRAP);
        flexboxLayoutManager1.setJustifyContent(JustifyContent.SPACE_BETWEEN);
        flexboxLayoutManager1.setAlignItems(AlignItems.FLEX_START);
        mSelfRV.setLayoutManager(flexboxLayoutManager1);
        FlexboxLayoutManager flexboxLayoutManager2 = new FlexboxLayoutManager(mActivity);
        flexboxLayoutManager2.setFlexDirection(FlexDirection.ROW);
        flexboxLayoutManager2.setFlexWrap(FlexWrap.WRAP);
        flexboxLayoutManager1.setJustifyContent(JustifyContent.SPACE_BETWEEN);
        flexboxLayoutManager1.setAlignItems(AlignItems.FLEX_START);
        mStarRV.setLayoutManager(flexboxLayoutManager2);
        FlexboxLayoutManager flexboxLayoutManager3 = new FlexboxLayoutManager(mActivity);
        flexboxLayoutManager3.setFlexDirection(FlexDirection.ROW);
        flexboxLayoutManager3.setFlexWrap(FlexWrap.WRAP);
        flexboxLayoutManager1.setJustifyContent(JustifyContent.SPACE_BETWEEN);
        flexboxLayoutManager1.setAlignItems(AlignItems.FLEX_START);
        mAtRV.setLayoutManager(flexboxLayoutManager3);

        mSelfAdapter = new ImageFlowAdapter(mActivity, new ArrayList<ImageDetailBean.DataBean>());
        mSelfRV.setAdapter(mSelfAdapter);
        mStarAdapter = new ImageFlowAdapter(mActivity, new ArrayList<ImageDetailBean.DataBean>());
        mStarRV.setAdapter(mStarAdapter);
        mAtAdapter = new ImageFlowAdapter(mActivity, new ArrayList<ImageDetailBean.DataBean>());
        mAtRV.setAdapter(mAtAdapter);

        mPagerList = new ArrayList<View>();
        mPagerList.add(mSelfRefreshLayout);
        mPagerList.add(mStarRefreshLayout);
        mPagerList.add(mAtRefreshLayout);
    }
}
