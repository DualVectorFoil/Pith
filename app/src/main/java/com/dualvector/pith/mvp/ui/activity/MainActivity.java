package com.dualvector.pith.mvp.ui.activity;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.dualvector.pith.R;
import com.dualvector.pith.app.manager.AccountManager;
import com.dualvector.pith.mvp.base.BaseActivity;
import com.dualvector.pith.mvp.contract.MainContract;
import com.dualvector.pith.mvp.model.bean.ProfileBean;
import com.dualvector.pith.mvp.presenter.MainPresenter;
import com.dualvector.pith.mvp.ui.adapter.SectionsPagerAdapter;
import com.dualvector.pith.mvp.ui.fragment.AccountFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

//import com.dualvector.pith.di.component.DaggerMainComponent;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.IMainView {

    private static final String TAG = "Main_Tag_Activity";

//    @BindView(R.id.main_tool_bar)
//    protected Toolbar mToolbar;
    @BindView(R.id.sections_view_pager)
    protected ViewPager mViewPager;
    @BindView(R.id.main_navi_bar)
    protected BottomNavigationBar mNaviBar;

    private List<Fragment> mFragments;
    private ProfileBean.DataBean mCookie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        DaggerMainComponent.builder().mainModule(new MainModule(this)).build().inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // TODO get cookie, and get user's home page data from network
        mCookie = AccountManager.getsInstance().getCookie();
        if (mCookie == null) {
            startAsGuest();
        } else {
            startAsUser();
        }
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        // init viewpager and fragments
        mFragments = new ArrayList<Fragment>();
        // TODO add fragment into mFragments
        mFragments.add(new AccountFragment());
        mFragments.add(new AccountFragment());
        mFragments.add(new AccountFragment());
        mFragments.add(new AccountFragment());
        mFragments.add(new AccountFragment());
        mViewPager.setAdapter(new SectionsPagerAdapter(getSupportFragmentManager(), mFragments));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mNaviBar.selectTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        mViewPager.setCurrentItem(0);

        // init bottom navigation bar
        mNaviBar.setMode(BottomNavigationBar.MODE_FIXED);
        mNaviBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mNaviBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(int position) {
            }

            @Override
            public void onTabReselected(int position) {
            }
        });
        mNaviBar.addItem(new BottomNavigationItem(R.mipmap.ic_home_page_icon_active, "首页").setActiveColorResource(R.color.colorDark).setInactiveIconResource(R.mipmap.ic_home_page_icon_inactive).setInActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.mipmap.ic_creation_icon_active, "画室").setActiveColorResource(R.color.colorDark).setInactiveIconResource(R.mipmap.ic_creation_icon_inactive).setInActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.mipmap.ic_search_icon_active, "搜索").setActiveColorResource(R.color.colorDark).setInactiveIconResource(R.mipmap.ic_search_icon_inactive).setInActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.mipmap.ic_message_icon_active, "消息").setActiveColorResource(R.color.colorDark).setInactiveIconResource(R.mipmap.ic_message_icon_inactive).setInActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.mipmap.ic_user_icon_active, R.string.myself_str).setActiveColorResource(R.color.colorDark).setInactiveIconResource(R.mipmap.ic_user_icon_inactive).setInActiveColorResource(R.color.colorPrimary))
                .initialise();
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @OnClick(R.id.main_navi_bar)
    public void onClicked(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.main_navi_bar:
                break;
            default:
        }
    }

    private void startAsUser() {

    }

    private void startAsGuest() {

    }
}
