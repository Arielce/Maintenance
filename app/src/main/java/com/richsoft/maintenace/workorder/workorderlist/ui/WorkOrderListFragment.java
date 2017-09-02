package com.richsoft.maintenace.workorder.workorderlist.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import com.richsoft.maintenace.bean.workorder.WorkOrderBean;
import com.richsoft.maintenace.common.ConstantValue;
import com.richsoft.maintenace.workorder.workorderlist.presenter.LoadWorkOrdersPresenter;
import com.richsoft.maintenace.workorder.workorderlist.presenter.LoadWorkOrdersPresenterImpl;
import com.richsoft.maintenace.workorder.workorderlist.view.WorkOrdersView;
import java.util.List;
import ren.solid.library.activity.base.BaseActivity;
import ren.solid.library.fragment.base.AbsLazyLoadListFragment;
import ren.solid.library.manager.EventCenter;
import ren.solid.library.widget.LinearDecoration;

/**
 * Created by Administrator on 2016/12/11.
 */

public class WorkOrderListFragment extends AbsLazyLoadListFragment implements WorkOrdersView {

    private int mType ;
    private LoadWorkOrdersPresenter mPresenter;

    public static WorkOrderListFragment newInstance(int type) {
        Bundle args = new Bundle();
        WorkOrderListFragment fragment = new WorkOrderListFragment();
        args.putInt("type", type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mType = getArguments().getInt("type");
        mPresenter=new LoadWorkOrdersPresenterImpl((BaseActivity) getActivity(),this);
    }

    @Override
    protected void customConfig() {
        addItemDecoration(new LinearDecoration(getContext(), RecyclerView.VERTICAL, 1));
    }

    @Override
    protected int getInitPageIndex() {
        return 1;
    }

    @Override
    public void loadData(final int pageIndex) {
        mPresenter.loadWorkOrders(pageIndex,mType);
    }

    @Override
    public void onWorkOrdersSuccess(int pageIndex,List<WorkOrderBean> data) {
        onDataSuccessReceived(pageIndex, data);
    }

    @Override
    public void onWorkOrdersFail(Exception e) {
        showError(e);
    }


    @Override
    protected boolean isBindEventBusHere() {
        return true;
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {
        int eventCode = eventCenter.getEventCode();
        switch (eventCode) {
            case ConstantValue.EVENT_SEND_WORK_SUCCESS:
                if(mType==0){
                    mPresenter.loadWorkOrders(getInitPageIndex(),mType);
                }
                break;
        }
    }

}
