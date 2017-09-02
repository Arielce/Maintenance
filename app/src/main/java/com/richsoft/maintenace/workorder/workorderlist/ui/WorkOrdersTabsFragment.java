package com.richsoft.maintenace.workorder.workorderlist.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import com.richsoft.maintenace.R;
import com.richsoft.maintenace.workorder.workorderlist.ui.adapter.WorkOrdersTabPagerAdapter;
import java.util.Arrays;
import java.util.List;
import ren.solid.library.fragment.base.BaseFragment;
import ren.solid.library.manager.EventCenter;
import ren.solid.library.utils.SPUtil;

/**
 * Created by Administrator on 2016/12/31.
 */

public class WorkOrdersTabsFragment extends BaseFragment {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private WorkOrdersTabPagerAdapter mAdapter;
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
        return R.layout.fragment_fix_tabs;
    }

    @Override
    protected void setUpView() {
        mTabLayout = $(R.id.tab_layout);
        mViewPager = $(R.id.viewpager);
    }

    @Override
    protected void setUpData() {
        setPagerAdapterData(Arrays.asList(getResources().getStringArray(R.array.work_types)));
        tabCount=getResources().getStringArray(R.array.work_types).length;
        mViewPager.setOffscreenPageLimit(tabCount-1);
        if(SPUtil.getInstance().getUserRole().equals("1")){
            mViewPager.setCurrentItem(1);//班长角色默认切换到已完成工单的tab
        }else{
            mViewPager.setCurrentItem(0);//班长角色默认切换到未完成工单的tab
        }
    }

    public void setPagerAdapterData(List<String> list) {
        mAdapter = new WorkOrdersTabPagerAdapter(getChildFragmentManager(), list);
        for (int i = 0; i < list.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(list.get(i)));
        }
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
