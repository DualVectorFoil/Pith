package com.dualvector.pith.mvp.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
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
import com.dualvector.pith.mvp.base.BaseFragment;
import com.dualvector.pith.mvp.contract.FrAccountContract;
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
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

//import com.dualvector.pith.di.component.DaggerFrAccountComponent;

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
    private SmartRefreshLayout mStarRefreshLayout;
    private RecyclerView mStarRV;
    private SmartRefreshLayout mAtRefreshLayout;
    private RecyclerView mAtRV;

    private Activity mActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        DaggerFrAccountComponent.builder().frAccountModule(new FrAccountModule(this)).build().inject(this);
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

    private void initPagerList() {
        mSelfRefreshLayout = (SmartRefreshLayout) mActivity.getLayoutInflater().inflate(R.layout.recycler_view_page_item, null);
        mStarRefreshLayout = (SmartRefreshLayout) mActivity.getLayoutInflater().inflate(R.layout.recycler_view_page_item, null);
        mAtRefreshLayout = (SmartRefreshLayout) mActivity.getLayoutInflater().inflate(R.layout.recycler_view_page_item, null);

        mSelfRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mSelfRefreshLayout.finishRefresh(2000);
            }
        });
        mSelfRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mSelfRefreshLayout.finishLoadMore(2000);
            }
        });
        mStarRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mStarRefreshLayout.finishRefresh(2000);
            }
        });
        mStarRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mStarRefreshLayout.finishLoadMore(2000);
            }
        });
        mAtRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mAtRefreshLayout.finishRefresh(2000);
            }
        });
        mAtRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mAtRefreshLayout.finishLoadMore(2000);
            }
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

        ImageFlowAdapter adapter1 = new ImageFlowAdapter(mActivity, mPresenter.getSelfImages());
        mSelfRV.setAdapter(adapter1);
        ImageFlowAdapter adapter2 = new ImageFlowAdapter(mActivity, mPresenter.getStarImages());
        mStarRV.setAdapter(adapter2);
        ImageFlowAdapter adapter3 = new ImageFlowAdapter(mActivity, mPresenter.getAtImages());
        mAtRV.setAdapter(adapter3);

        mPagerList = new ArrayList<View>();
        mPagerList.add(mSelfRefreshLayout);
        mPagerList.add(mStarRefreshLayout);
        mPagerList.add(mAtRefreshLayout);
    }
}
