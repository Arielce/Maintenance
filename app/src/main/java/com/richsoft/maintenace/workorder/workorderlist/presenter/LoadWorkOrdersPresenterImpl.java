package com.richsoft.maintenace.workorder.workorderlist.presenter;

import com.richsoft.maintenace.bean.workorder.WorkOrderBean;
import com.richsoft.maintenace.workorder.workorderlist.model.LoadWorkOrdersModel;
import com.richsoft.maintenace.workorder.workorderlist.model.LoadWorkOrdersModelImpl;
import com.richsoft.maintenace.workorder.workorderlist.view.WorkOrdersView;
import java.util.List;
import ren.solid.library.activity.base.BaseActivity;
import ren.solid.library.net.BaseSingleListener;

/**
 * Created by Administrator on 2016/11/26.
 */

public class LoadWorkOrdersPresenterImpl implements LoadWorkOrdersPresenter,BaseSingleListener<List<WorkOrderBean>> {
    private WorkOrdersView mView;
    private BaseActivity mActivity;
    private LoadWorkOrdersModel mModel;
    private int pageIndex;

    public LoadWorkOrdersPresenterImpl(BaseActivity activity, WorkOrdersView mView) {
        this.mView = mView;
        this.mActivity=activity;
        this.mModel=new LoadWorkOrdersModelImpl(mActivity);
    }

    @Override
    public void loadWorkOrders(int pageIndex,int type) {
        this.pageIndex=pageIndex;
        mModel.loadWorkOrders(pageIndex,type,this);
    }

    @Override
    public void onSuccess(List<WorkOrderBean> data) {
        mView.onWorkOrdersSuccess(pageIndex,data);
    }

    @Override
    public void onError(Exception e) {
        mView.onWorkOrdersFail(e);
    }

    @Override
    public void onException(Exception e) {
        mView.onWorkOrdersFail(e);
    }
}
