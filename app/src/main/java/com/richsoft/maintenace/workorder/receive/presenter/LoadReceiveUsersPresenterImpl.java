package com.richsoft.maintenace.workorder.receive.presenter;

import com.richsoft.maintenace.bean.workorder.ReceiveWorkBean;
import com.richsoft.maintenace.workorder.receive.model.LoadReceiveUsersModel;
import com.richsoft.maintenace.workorder.receive.model.LoadReceiveUsersModelImpl;
import com.richsoft.maintenace.workorder.receive.view.LoadReceiveUsersView;
import java.util.List;
import ren.solid.library.activity.base.BaseActivity;
import ren.solid.library.net.BaseSingleListener;

/**
 * Created by Administrator on 2016/11/26.
 */

public class LoadReceiveUsersPresenterImpl implements LoadReceiveUsersPresenter,BaseSingleListener<List<ReceiveWorkBean>> {
    private LoadReceiveUsersView mView;
    private BaseActivity mActivity;
    private LoadReceiveUsersModel mModel;

    public LoadReceiveUsersPresenterImpl(BaseActivity activity, LoadReceiveUsersView mView) {
        this.mView = mView;
        this.mActivity=activity;
        this.mModel=new LoadReceiveUsersModelImpl(mActivity);
    }

    @Override
    public void loadReceiveUsers(String wid) {
        mModel.loadReceiveUsers(wid,this);
    }

    @Override
    public void onSuccess(List<ReceiveWorkBean> data) {
        mView.onLoadReceiveUsersSuccess(data);
    }

    @Override
    public void onError(Exception e) {
        mView.onLoadReceiveUsersFail(e);
    }

    @Override
    public void onException(Exception e) {
        mView.onLoadReceiveUsersFail(e);
    }
}
