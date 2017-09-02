package com.richsoft.maintenace.workorder.filed.presenter;

import com.richsoft.maintenace.bean.workorder.FieldWorkBean;
import com.richsoft.maintenace.workorder.filed.model.LoadFieldWorkProcedureModel;
import com.richsoft.maintenace.workorder.filed.model.LoadFieldWorkProcedureModelImpl;
import com.richsoft.maintenace.workorder.filed.view.LoadFieldWorkDataView;
import ren.solid.library.activity.base.BaseActivity;
import ren.solid.library.net.BaseSingleListener;

/**
 * Created by Administrator on 2016/11/26.
 */

public class LoadFieldWorkDataPresenterImpl implements LoadFieldWorkDataPresenter,BaseSingleListener<FieldWorkBean> {
    private LoadFieldWorkDataView mView;
    private BaseActivity mActivity;
    private LoadFieldWorkProcedureModel mModel;

    public LoadFieldWorkDataPresenterImpl(BaseActivity activity, LoadFieldWorkDataView mView) {
        this.mView = mView;
        this.mActivity=activity;
        this.mModel=new LoadFieldWorkProcedureModelImpl(mActivity);
    }

    @Override
    public void loadFieldWorkProcedure(String orderNo) {
        mModel.loadFieldWorkProcedure(orderNo,this);
    }

    @Override
    public void onSuccess(FieldWorkBean data) {
        mView.onLoadFieldWorkDataSuccess(data);
    }

    @Override
    public void onError(Exception e) {
        mView.onLoadFieldWorkDataFail(e);
    }

    @Override
    public void onException(Exception e) {
        mView.onLoadFieldWorkDataFail(e);
    }
}
