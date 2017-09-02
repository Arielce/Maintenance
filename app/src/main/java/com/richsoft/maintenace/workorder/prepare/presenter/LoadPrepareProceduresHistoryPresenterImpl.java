package com.richsoft.maintenace.workorder.prepare.presenter;

import com.richsoft.maintenace.bean.workorder.PrepareBean;
import com.richsoft.maintenace.workorder.prepare.model.LoadPrepareProceduresHistoryModel;
import com.richsoft.maintenace.workorder.prepare.model.LoadPrepareProceduresHistoryModelImpl;
import com.richsoft.maintenace.workorder.prepare.view.LoadPrepareProceduresHistoryView;
import ren.solid.library.activity.base.BaseActivity;
import ren.solid.library.net.BaseSingleListener;

/**
 * Created by Administrator on 2016/11/26.
 */

public class LoadPrepareProceduresHistoryPresenterImpl implements LoadPrepareProceduresHistoryPresenter,BaseSingleListener<PrepareBean> {
    private LoadPrepareProceduresHistoryView mView;
    private BaseActivity mActivity;
    private LoadPrepareProceduresHistoryModel mModel;

    public LoadPrepareProceduresHistoryPresenterImpl(BaseActivity activity, LoadPrepareProceduresHistoryView mView) {
        this.mView = mView;
        this.mActivity=activity;
        this.mModel=new LoadPrepareProceduresHistoryModelImpl(mActivity);
    }

    @Override
    public void loadPrepareProceduresHistory(String orderNo) {
        mModel.loadPrepareProceduresHistory(orderNo,this);
    }

    @Override
    public void onSuccess(PrepareBean data) {
        mView.onLoadPrepareProceduresHistorySuccess(data);
    }

    @Override
    public void onError(Exception e) {
        mView.onLoadPrepareProceduresHistoryFail(e);
    }

    @Override
    public void onException(Exception e) {
        mView.onLoadPrepareProceduresHistoryFail(e);
    }
}
