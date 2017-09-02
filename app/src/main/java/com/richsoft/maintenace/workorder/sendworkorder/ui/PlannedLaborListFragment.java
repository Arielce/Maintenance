package com.richsoft.maintenace.workorder.sendworkorder.ui;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import com.richsoft.maintenace.R;
import com.richsoft.maintenace.bean.workorder.PlannedLaborBean;
import com.richsoft.maintenace.workorder.sendworkorder.presenter.LoadPlannedLaborListPresenter;
import com.richsoft.maintenace.workorder.sendworkorder.presenter.LoadPlannedLaborListPresenterImpl;
import com.richsoft.maintenace.workorder.sendworkorder.view.PlannedLaborView;
import com.richsoft.maintenace.workorder.sendworkorder.ui.adapter.PlannedLaborListAdapter;
import java.util.ArrayList;
import java.util.List;
import ren.solid.library.activity.base.BaseActivity;
import ren.solid.library.fragment.base.BaseFragment;
import ren.solid.library.manager.EventCenter;
import ren.solid.library.widget.LinearDecoration;
import ren.solid.library.widget.StatusViewLayout;

/**
 * 作者：e430 on 2017/2/16 20:31
 * <p>
 * 邮箱：chengzehao@163.com
 */

public class PlannedLaborListFragment extends BaseFragment implements PlannedLaborView {
    private RecyclerView mRecyclerView;
    private StatusViewLayout status_view;
    private LinearLayoutManager mLayoutManager;
    private PlannedLaborListAdapter mAdapter;
    private List<PlannedLaborBean> mData;
    private LoadPlannedLaborListPresenter mPresenter;

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
        mRecyclerView=$(R.id.recycle_view);
        status_view=$(R.id.status_view);
        status_view.showLoading();
        //初始化列表控件
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new LinearDecoration(getContext(), RecyclerView.VERTICAL, 5));
        ((SimpleItemAnimator) mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        mData=new ArrayList<>();
        mAdapter = new PlannedLaborListAdapter(getActivity(),mData);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void setUpData() {
        mPresenter=new LoadPlannedLaborListPresenterImpl((BaseActivity) getActivity(),this);
        mPresenter.loadPlannedLaborList();
    }

    @Override
    public void onLoadPlannedLaborsSuccess(List<PlannedLaborBean> data) {
        mAdapter.addAll(data,true);
        if(mAdapter.getDatas().size()==0){
            status_view.showEmpty();
        }else{
            status_view.showContent();
        }
    }

    @Override
    public void onLoadPlannedLaborsFail(Exception e) {
        status_view.showError(e.getMessage());
    }
}
