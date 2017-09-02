package com.richsoft.maintenace.workorder.workorderdetail.presenter;

import com.richsoft.maintenace.workorder.workorderdetail.model.SubmitReceiveModel;
import com.richsoft.maintenace.workorder.workorderdetail.model.SubmitReceiveModelImpl;
import com.richsoft.maintenace.workorder.workorderdetail.view.SubmitReceiveView;
import ren.solid.library.activity.base.BaseActivity;
import ren.solid.library.net.BaseSingleListener;
import ren.solid.library.utils.LogUtils;

/**
 * Created by Administrator on 2016/11/26.
 */

public class SubmitReceivePresenterImpl implements SubmitReceivePresenter,BaseSingleListener<String> {
    private SubmitReceiveView mView;
    private BaseActivity mActivity;
    private SubmitReceiveModel mModel;

    public SubmitReceivePresenterImpl(BaseActivity activity, SubmitReceiveView mView) {
        this.mView = mView;
        this.mActivity=activity;
        this.mModel=new SubmitReceiveModelImpl(mActivity);
    }

    @Override
    public void submitReceive(String wid) {
        LogUtils.i("zhoul","接受业务类执行接受");
        mModel.submitReceive(wid,this);
    }

    @Override
    public void onSuccess(String data) {
        mView.onSubmitReceiveSuccess(data);
    }

    @Override
    public void onError(Exception e) {
        mView.onSubmitReceiveFail(e);
    }

    @Override
    public void onException(Exception e) {
        mView.onSubmitReceiveFail(e);
    }
}
