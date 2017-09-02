package com.richsoft.maintenace.workorder.prepare.presenter;

import com.richsoft.maintenace.bean.workorder.PrepareBean;
import com.richsoft.maintenace.workorder.prepare.model.LoadPrepareProceduresModel;
import com.richsoft.maintenace.workorder.prepare.model.LoadPrepareProceduresModelImpl;
import com.richsoft.maintenace.workorder.prepare.view.LoadPrepareProceduresView;
import ren.solid.library.activity.base.BaseActivity;
import ren.solid.library.net.BaseSingleListener;

/**
 * Created by Administrator on 2016/11/26.
 */

public class LoadPrepareProceduresPresenterImpl implements LoadPrepareProceduresPresenter,BaseSingleListener<PrepareBean> {
    private LoadPrepareProceduresView mView;
    private BaseActivity mActivity;
    private LoadPrepareProceduresModel mModel;

    public LoadPrepareProceduresPresenterImpl(BaseActivity activity, LoadPrepareProceduresView mView) {
        this.mView = mView;
        this.mActivity=activity;
        this.mModel=new LoadPrepareProceduresModelImpl(mActivity);
    }

    @Override
    public void loadPrepareProcedures(String orderNo) {
        mModel.loadPrepareProcedures(orderNo,this);
    }

    @Override
    public void onSuccess(PrepareBean data) {
        mView.onLoadPrepareProceduresSuccess(data);
    }

    @Override
    public void onError(Exception e) {
        mView.onLoadPrepareProceduresFail(e);
    }

    @Override
    public void onException(Exception e) {
        mView.onLoadPrepareProceduresFail(e);
    }
}
