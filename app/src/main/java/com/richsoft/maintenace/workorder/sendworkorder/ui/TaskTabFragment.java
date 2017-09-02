package com.richsoft.maintenace.workorder.sendworkorder.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.richsoft.maintenace.R;
import com.richsoft.maintenace.bean.workorder.WorkOrderBean;
import com.richsoft.maintenace.bean.workorder.WorkTaskListBean;
import com.richsoft.maintenace.common.ConstantValue;
import com.richsoft.maintenace.workorder.sendworkorder.presenter.LoadTasksPresenter;
import com.richsoft.maintenace.workorder.sendworkorder.presenter.LoadTasksPresenterImpl;
import com.richsoft.maintenace.workorder.sendworkorder.view.LoadTasksView;
import com.richsoft.maintenace.workorder.sendworkorder.ui.adapter.TasksTabPagerAdapter;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;
import java.util.ArrayList;
import java.util.List;

import ren.solid.library.activity.base.BaseActivity;
import ren.solid.library.fragment.base.BaseFragment;
import ren.solid.library.listener.OnNavigationBarListener;
import ren.solid.library.manager.EventCenter;
import ren.solid.library.utils.StringUtils;
import ren.solid.library.widget.StatusViewLayout;

/**
 * 作者：e430 on 2017/3/7 11:17
 * <p>派发工单采集任务的workId以及workType
 * 邮箱：chengzehao@163.com
 */

public class TaskTabFragment extends BaseFragment implements Step,LoadTasksView {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private StatusViewLayout status_view;
    private int tabCount;
    private WorkOrderBean mWorkOrderBean;
    private LoadTasksPresenter mPresenter;
    private TasksTabPagerAdapter mAdapter;
    private List<String> mTitles;
    private List<WorkTaskListBean> mData;
    private boolean isCanNext = false;
    @Nullable
    private OnNavigationBarListener onNavigationBarListener;

    public static TaskTabFragment newInstance(WorkOrderBean data) {
        Bundle args = new Bundle();
        TaskTabFragment fragment = new TaskTabFragment();
        args.putSerializable("data",data);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWorkOrderBean= (WorkOrderBean) getArguments().getSerializable("data");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnNavigationBarListener) {
            onNavigationBarListener = (OnNavigationBarListener) context;
        }
    }

    @Override
    public void onLoadTasksSuccess(List<WorkTaskListBean> data) {
        mData=data;
        mTitles=new ArrayList<>();
        for (int i=0;i<data.size();i++){
            mTitles.add(data.get(i).getWorkTypeName());
        }
        setPagerAdapterData(mTitles);
        tabCount=mTitles.size();
        mViewPager.setOffscreenPageLimit(tabCount-1);
        status_view.showContent();
    }

    @Override
    public void onLoadTasksFail(Exception e) {
        status_view.showError(e.getMessage());
    }

    @Override
    public VerificationError verifyStep() {
        return isCanNext ? null : new VerificationError("请您选择任务");
    }

    @Override
    public void onSelected() {
        updateNavigationBar();
    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }

    @Override
    protected boolean isBindEventBusHere() {
        return true;
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {
        int eventCode = eventCenter.getEventCode();
        switch (eventCode) {
            case ConstantValue.EVENT_TASK_CHOOSE:
                updateNavigationBar();
                break;
        }
    }

    public void updateNavigationBar() {
        if (onNavigationBarListener != null) {
            if(StringUtils.isNullOrEmpty(mWorkOrderBean.getTask().getWorkID())){
                isCanNext=false;
            }else{
                isCanNext=true;
            }
            onNavigationBarListener.onChangeEndButtonsEnabled(isCanNext);
        }
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_loading_fix_tabs;
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
        mPresenter=new LoadTasksPresenterImpl((BaseActivity) getActivity(),this);
        mPresenter.loadTasks();
    }

    public void setPagerAdapterData(List<String> list) {
        mAdapter = new TasksTabPagerAdapter(getChildFragmentManager(),mWorkOrderBean,mTitles,mData);
        for (int i = 0; i < list.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(list.get(i)));
        }
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
