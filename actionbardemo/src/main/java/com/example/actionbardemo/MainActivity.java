package com.example.actionbardemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.example.actionbardemo.Fragment.AlarmFragment;
import com.example.actionbardemo.Fragment.LocationFragment;
import com.example.actionbardemo.Fragment.ToyFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActionBar mActionBar;
    private ActionBarTabListener mTabListener;
    private ViewPager mViewPager;
    private List<Fragment> mFragments;
    private PagerAdapter mPagerAdapter;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActionBar = getSupportActionBar();
        mViewPager = (ViewPager) findViewById(R.id.vp);
        if (mActionBar != null) {
            mTabListener = new ActionBarTabListener();
            initActionBar();
        }
        initFragments();
        initPagerViewer();
    }

    private void initPagerViewer() {
        mFragmentManager = getSupportFragmentManager();
        mPagerAdapter = new PagerAdapter(mFragments, mFragmentManager);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPagerListener());
    }

    private void initFragments() {
        mFragments = new ArrayList<>();
        mFragments.add(0, new AlarmFragment());
        mFragments.add(1, new LocationFragment());
        mFragments.add(2, new ToyFragment());
    }

    private void initActionBar() {
        mActionBar.setDisplayOptions(0);
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        Tab mAlarm = mActionBar.newTab();
        mAlarm.setTabListener(mTabListener);
        mAlarm.setIcon(R.drawable.alarm_animation);
        mActionBar.addTab(mAlarm);

        Tab mTimer = mActionBar.newTab();
        mTimer.setTabListener(mTabListener);
        mTimer.setIcon(R.drawable.location_animation);
        mActionBar.addTab(mTimer);

        Tab mStopSwitch = mActionBar.newTab();
        mStopSwitch.setTabListener(mTabListener);
        mStopSwitch.setIcon(R.drawable.toy_animation);
        mActionBar.addTab(mStopSwitch);

        mActionBar.setSelectedNavigationItem(0);
    }

    private class ActionBarTabListener implements ActionBar.TabListener {
        @Override
        public void onTabSelected(Tab tab, FragmentTransaction ft) {
            int position = tab.getPosition();
            mViewPager.setCurrentItem(position, true);
        }

        @Override
        public void onTabUnselected(Tab tab, FragmentTransaction ft) {

        }

        @Override
        public void onTabReselected(Tab tab, FragmentTransaction ft) {

        }
    }

    private class ViewPagerListener implements OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            mActionBar.setSelectedNavigationItem(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    private class PagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> mList;

        public PagerAdapter(List<Fragment> mList, FragmentManager fm) {
            this(fm);
            this.mList = mList;
        }

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mList.get(position);
        }

        @Override
        public int getCount() {
            return mList.size();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        cancelAllListener();
        super.onDestroy();
    }

    private void cancelAllListener() {
        if (mViewPager != null){
            mViewPager.addOnPageChangeListener(null);
        }
        if (mActionBar != null){
            int count = mActionBar.getNavigationItemCount();
            for(int i = 0;i<count;i++){
                Tab tab = mActionBar.getTabAt(i);
                if(tab != null){
                    tab.setTabListener(null);
                }
            }
        }
    }
}
