package com.richsoft.maintenace.workorder.sendworkorder.presenter;

import com.richsoft.maintenace.bean.workorder.WorkOrderBean;
import com.richsoft.maintenace.workorder.sendworkorder.model.SendWorkModel;
import com.richsoft.maintenace.workorder.sendworkorder.model.SendWorkModelImpl;
import com.richsoft.maintenace.workorder.sendworkorder.view.SendWorkView;
import ren.solid.library.activity.base.BaseActivity;
import ren.solid.library.net.BaseSingleListener;

/**
 * Created by Administrator on 2016/11/26.
 */

public class SendWorkPresenterImpl implements SendWorkPresenter,BaseSingleListener<String> {
    private SendWorkView mView;
    private BaseActivity mActivity;
    private SendWorkModel mModel;

    public SendWorkPresenterImpl(BaseActivity activity, SendWorkView mView) {
        this.mView = mView;
        this.mActivity=activity;
        this.mModel=new SendWorkModelImpl(mActivity);
    }

    @Override
    public void send(WorkOrderBean workOrderBean) {
        mModel.send(workOrderBean,this);
    }

    @Override
    public void onSuccess(String msg) {
        mView.showSendSuccessMsg(msg);
    }

    @Override
    public void onError(Exception e) {
        mView.showSendFailureMsg(e);
    }

    @Override
    public void onException(Exception e) {
        mView.showSendFailureMsg(e);
    }
}
