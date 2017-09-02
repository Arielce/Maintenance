package com.richsoft.maintenace.workorder.workorderdetail.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.richsoft.maintenace.R;
import com.richsoft.maintenace.workorder.workorderdetail.ui.adapter.GuidanceDocTabPagerAdapter;
import java.util.Arrays;
import java.util.List;
import ren.solid.library.fragment.base.BaseFragment;
import ren.solid.library.manager.EventCenter;

/**
 * Created by Administrator on 2016/12/31.
 */

public class GuidanceDocTabsFragment extends BaseFragment {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private GuidanceDocTabPagerAdapter mAdapter;
    private int tabCount;
    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {
        int eventCode = eventCenter.getEventCode();
        switch (eventCode) {

        }
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_tabs;
    }

    @Override
    protected void setUpView() {
        mTabLayout = $(R.id.tab_layout);
        mViewPager = $(R.id.viewpager);
    }

    @Override
    protected void setUpData() {
        setPagerAdapterData(Arrays.asList(getResources().getStringArray(R.array.doc_types)));
        tabCount=getResources().getStringArray(R.array.doc_types).length;
        mViewPager.setOffscreenPageLimit(tabCount-1);
    }

    public void setPagerAdapterData(List<String> list) {
        mAdapter = new GuidanceDocTabPagerAdapter(getChildFragmentManager(), list);
        for (int i = 0; i < list.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(list.get(i)));
        }
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
