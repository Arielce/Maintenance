package com.richsoft.maintenace.workorder.receive.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import com.richsoft.maintenace.R;
import com.richsoft.maintenace.bean.workorder.ReceiveWorkBean;
import com.richsoft.maintenace.bean.workorder.WorkOrderBean;
import com.richsoft.maintenace.workorder.receive.presenter.LoadReceiveUsersPresenter;
import com.richsoft.maintenace.workorder.receive.presenter.LoadReceiveUsersPresenterImpl;
import com.richsoft.maintenace.workorder.receive.ui.adapter.ReceiveHistoryAdapter;
import com.richsoft.maintenace.workorder.receive.view.LoadReceiveUsersView;

import java.util.ArrayList;
import java.util.List;

import ren.solid.library.activity.base.BaseActivity;
import ren.solid.library.fragment.base.BaseFragment;
import ren.solid.library.manager.EventCenter;
import ren.solid.library.widget.StatusViewLayout;

/**
 * 作者：e430 on 2017/2/17 13:30
 * <p>
 * 邮箱：chengzehao@163.com
 */

public class ReceiveHistoryFragment extends BaseFragment implements LoadReceiveUsersView {
    private RecyclerView mRecyclerView;
    private StatusViewLayout status_view;
    private LinearLayoutManager mLayoutManager;
    private WorkOrderBean mWorkOrderBean;
    private ReceiveHistoryAdapter mAdapter;
    private LoadReceiveUsersPresenter mLoadReceiveUsersPresenter;


    public static ReceiveHistoryFragment newInstance(WorkOrderBean data) {
        Bundle args = new Bundle();
        ReceiveHistoryFragment fragment = new ReceiveHistoryFragment();
        args.putSerializable("data", data);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWorkOrderBean = (WorkOrderBean) getArguments().getSerializable("data");
        mLoadReceiveUsersPresenter=new LoadReceiveUsersPresenterImpl((BaseActivity) getActivity(),this);
    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_recyclerview;
    }

    @Override
    protected void setUpView() {
        mRecyclerView = $(R.id.recycle_view);
        status_view = $(R.id.status_view);
        status_view.showLoading();
        //初始化列表控件
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        ((SimpleItemAnimator) mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        mAdapter = new ReceiveHistoryAdapter(getActivity(), new ArrayList<ReceiveWorkBean>());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void setUpData() {
        mLoadReceiveUsersPresenter.loadReceiveUsers(mWorkOrderBean.getOrderNo());
    }

    @Override
    public void onLoadReceiveUsersSuccess(List<ReceiveWorkBean> data) {
        if(data==null||data.size()==0){
            status_view.showEmpty();
            return;
        }
        mAdapter.addAll(data);
        status_view.showContent();
    }

    @Override
    public void onLoadReceiveUsersFail(Exception e) {
        status_view.showError(e.getMessage());
    }
}
