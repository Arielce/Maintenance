package com.richsoft.maintenace.workorder.sendworkorder.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;

import com.richsoft.maintenace.R;
import com.richsoft.maintenace.bean.workorder.WorkOrderBean;
import com.richsoft.maintenace.bean.workorder.WorkTaskListBean;
import com.richsoft.maintenace.common.ConstantValue;
import com.richsoft.maintenace.workorder.sendworkorder.ui.adapter.TaskChooseAdapter;

import java.util.ArrayList;

import ren.solid.library.fragment.base.BaseFragment;
import ren.solid.library.manager.EventCenter;
import ren.solid.library.widget.StatusViewLayout;

/**
 * 作者：e430 on 2017/2/17 13:30
 * <p>
 * 邮箱：chengzehao@163.com
 */

public class TaskChooseFragment extends BaseFragment {
    private RecyclerView mRecyclerView;
    private StatusViewLayout status_view;
    private LinearLayoutManager mLayoutManager;
    private WorkOrderBean mWorkOrderBean;
    private TaskChooseAdapter mAdapter;
    private ArrayList<WorkTaskListBean> mWorkTaskListBeans;
    private int tabPos = 0;

    public static TaskChooseFragment newInstance(WorkOrderBean data, int tabPos, ArrayList<WorkTaskListBean> workTaskListBeans) {
        Bundle args = new Bundle();
        TaskChooseFragment fragment = new TaskChooseFragment();
        args.putSerializable("data", data);
        args.putInt("tabPos", tabPos);
        args.putSerializable("tasks", workTaskListBeans);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWorkOrderBean = (WorkOrderBean) getArguments().getSerializable("data");
        mWorkTaskListBeans = (ArrayList<WorkTaskListBean>) getArguments().getSerializable("tasks");
        tabPos = getArguments().getInt("tabPos");
    }

    @Override
    protected boolean isBindEventBusHere() {
        return true;
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {
        int eventCode = eventCenter.getEventCode();
        String wid= (String) eventCenter.getData();
        switch (eventCode) {
            case ConstantValue.EVENT_TASK_CHOOSE:
                for (int i = 0; i < mWorkTaskListBeans.size(); i++) {
                    if(i==tabPos){
                        for (int j = 0; j < mWorkTaskListBeans.get(i).getWorkTaskBeans().size(); j++) {
                            if(wid.equals(mWorkTaskListBeans.get(i).getWorkTaskBeans().get(j).getWorkID())){
                                mWorkTaskListBeans.get(i).getWorkTaskBeans().get(j).setChooseTag(true);
                            }else{
                                mWorkTaskListBeans.get(i).getWorkTaskBeans().get(j).setChooseTag(false);
                            }
                        }
                    }
                }
                mAdapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_recyclerview;
    }

    @Override
    protected void setUpView() {
        mRecyclerView = $(R.id.recycle_view);
        status_view = $(R.id.status_view);
        //初始化列表控件
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        ((SimpleItemAnimator) mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        mAdapter = new TaskChooseAdapter(getActivity(), mWorkTaskListBeans.get(tabPos).getWorkTaskBeans(), mWorkOrderBean);
        mRecyclerView.setAdapter(mAdapter);
        status_view.showContent();
    }

    @Override
    protected void setUpData() {

    }
}
