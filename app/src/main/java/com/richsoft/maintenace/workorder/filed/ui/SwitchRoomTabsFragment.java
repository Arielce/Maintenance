package com.richsoft.maintenace.workorder.filed.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import com.richsoft.maintenace.R;
import com.richsoft.maintenace.bean.workorder.FieldWorkProcedureRequirementBean;
import com.richsoft.maintenace.workorder.filed.ui.adapter.SwitchRoomTabPagerAdapter;
import java.util.ArrayList;
import java.util.List;
import ren.solid.library.fragment.base.BaseFragment;
import ren.solid.library.manager.EventCenter;

/**
 * Created by Administrator on 2016/12/31.
 */

public class SwitchRoomTabsFragment extends BaseFragment {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private FieldWorkProcedureRequirementBean mData;
    private SwitchRoomTabPagerAdapter mAdapter;
    private int tabCount;

    public static SwitchRoomTabsFragment newInstance(FieldWorkProcedureRequirementBean data) {
        Bundle args = new Bundle();
        SwitchRoomTabsFragment fragment = new SwitchRoomTabsFragment();
        args.putSerializable("data",data);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mData= (FieldWorkProcedureRequirementBean) getArguments().getSerializable("data");
    }

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
        return R.layout.fragment_mini_tabs;
    }

    @Override
    protected void setUpView() {
        mTabLayout = $(R.id.tab_layout);
        mViewPager = $(R.id.viewpager);
    }

    @Override
    protected void setUpData() {
        List<String> tabs=new ArrayList<>();
        for (int i=0;i<mData.switchRooms.size();i++){
            tabs.add(mData.switchRooms.get(i).getSwitchRoomName());
        }
        setPagerAdapterData(tabs);
        tabCount=tabs.size();
        mViewPager.setOffscreenPageLimit(tabCount-1);
    }

    public void setPagerAdapterData(List<String> list) {
        mAdapter = new SwitchRoomTabPagerAdapter(getChildFragmentManager(), list,mData.switchRooms);
        for (int i = 0; i < list.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(list.get(i)));
        }
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
