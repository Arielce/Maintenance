package com.richsoft.maintenace.workorder.prepare.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import com.richsoft.maintenace.R;
import com.richsoft.maintenace.bean.workorder.PrepareProcedureBean;
import com.richsoft.maintenace.workorder.prepare.ui.adapter.PrepareRequiresHistoryAdapter;
import ren.solid.library.fragment.base.BaseFragment;
import ren.solid.library.manager.EventCenter;
import ren.solid.library.widget.StatusViewLayout;

/**
 * 作者：e430 on 2017/2/17 13:30
 * <p>
 * 邮箱：chengzehao@163.com
 */

public class PrepareRequiresHistoryFragment extends BaseFragment{
    private RecyclerView mRecyclerView;
    private StatusViewLayout status_view;
    private LinearLayoutManager mLayoutManager;
    private PrepareProcedureBean mPrepareProcedureBean;
    private PrepareRequiresHistoryAdapter mAdapter;


    public static PrepareRequiresHistoryFragment newInstance(PrepareProcedureBean data) {
        Bundle args = new Bundle();
        PrepareRequiresHistoryFragment fragment = new PrepareRequiresHistoryFragment();
        args.putSerializable("data", data);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPrepareProcedureBean = (PrepareProcedureBean) getArguments().getSerializable("data");
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
        mAdapter = new PrepareRequiresHistoryAdapter(getActivity(), mPrepareProcedureBean.prepareProcedureRequirementBeans);
        mRecyclerView.setAdapter(mAdapter);
        status_view.showContent();
    }

    @Override
    protected void setUpData() {
    }
}
