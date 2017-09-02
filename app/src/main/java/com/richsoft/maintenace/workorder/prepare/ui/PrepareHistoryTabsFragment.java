package com.richsoft.maintenace.workorder.prepare.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import com.richsoft.maintenace.R;
import com.richsoft.maintenace.bean.workorder.PrepareBean;
import com.richsoft.maintenace.bean.workorder.PrepareProcedureBean;
import com.richsoft.maintenace.bean.workorder.WorkOrderBean;
import com.richsoft.maintenace.workorder.prepare.presenter.LoadPrepareProceduresHistoryPresenter;
import com.richsoft.maintenace.workorder.prepare.presenter.LoadPrepareProceduresHistoryPresenterImpl;
import com.richsoft.maintenace.workorder.prepare.ui.adapter.PrepareHistoryTabPagerAdapter;
import com.richsoft.maintenace.workorder.prepare.view.LoadPrepareProceduresHistoryView;
import java.util.ArrayList;
import java.util.List;
import ren.solid.library.activity.base.BaseActivity;
import ren.solid.library.fragment.base.BaseFragment;
import ren.solid.library.manager.EventCenter;
import ren.solid.library.widget.StatusViewLayout;

/**
 * Created by Administrator on 2016/12/31.
 */

public class PrepareHistoryTabsFragment extends BaseFragment implements LoadPrepareProceduresHistoryView {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private StatusViewLayout status_view;
    private PrepareHistoryTabPagerAdapter mAdapter;
    private WorkOrderBean mWorkOrderBean;
    private LoadPrepareProceduresHistoryPresenter mLoadPrepareProceduresHistoryPresenter;

    public static PrepareHistoryTabsFragment newInstance(WorkOrderBean workOrder) {
        Bundle args = new Bundle();
        PrepareHistoryTabsFragment fragment = new PrepareHistoryTabsFragment();
        args.putSerializable("work_order",workOrder);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWorkOrderBean=(WorkOrderBean) getArguments().getSerializable("work_order");
        mLoadPrepareProceduresHistoryPresenter=new LoadPrepareProceduresHistoryPresenterImpl((BaseActivity) getActivity(),this);
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
        return R.layout.fragment_loading_tabs;
    }

    @Override
    protected void setUpView() {
        mTabLayout = $(R.id.tab_layout);
        mViewPager = $(R.id.viewpager);
        status_view= $(R.id.status_view);
        status_view.showLoading();
    }

    @Override
    protected void setUpData() {
        mLoadPrepareProceduresHistoryPresenter.loadPrepareProceduresHistory(mWorkOrderBean.getOrderNo());
    }

    int tabCount=0;
    @Override
    public void onLoadPrepareProceduresHistorySuccess(PrepareBean data) {
        if(data!=null){
            List<String> titles=new ArrayList<>();
            for (int i=0;i<data.getPrepareProcedureBeans().size();i++){
                titles.add("【"+(i+1)+"】"+data.getPrepareProcedureBeans().get(i).procedureName);
            }
            setPagerAdapterData(titles,data.getPrepareProcedureBeans());
            tabCount=data.getPrepareProcedureBeans().size();
            mViewPager.setOffscreenPageLimit(tabCount-1);
            status_view.showContent();
        }
    }

    public void setPagerAdapterData(List<String> titles,List<PrepareProcedureBean> data) {
        mAdapter = new PrepareHistoryTabPagerAdapter(getChildFragmentManager(),titles, data);
        for (int i = 0; i < titles.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(i)));
        }
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onLoadPrepareProceduresHistoryFail(Exception e) {
        status_view.showError(e.getMessage());
    }
}
