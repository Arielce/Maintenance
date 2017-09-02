package com.richsoft.maintenace.workorder.sendworkorder.presenter;

import com.richsoft.maintenace.bean.user.UserBean;
import com.richsoft.maintenace.workorder.sendworkorder.model.LoadUsersModel;
import com.richsoft.maintenace.workorder.sendworkorder.model.LoadUsersModelImpl;
import com.richsoft.maintenace.workorder.sendworkorder.view.LoadUsersView;
import java.util.List;
import ren.solid.library.activity.base.BaseActivity;
import ren.solid.library.net.BaseSingleListener;

/**
 * Created by Administrator on 2016/11/26.
 */

public class LoadUsersPresenterImpl implements LoadUsersPresenter,BaseSingleListener<List<UserBean>> {
    private LoadUsersView mView;
    private BaseActivity mActivity;
    private LoadUsersModel mModel;

    public LoadUsersPresenterImpl(BaseActivity activity, LoadUsersView mView) {
        this.mView = mView;
        this.mActivity=activity;
        this.mModel=new LoadUsersModelImpl(mActivity);
    }

    @Override
    public void loadUsers() {
        mModel.loadUsers(this);
    }

    @Override
    public void onSuccess(List<UserBean> data) {
        mView.onLoadUsersSuccess(data);
    }

    @Override
    public void onError(Exception e) {
        mView.onLoadUsersFail(e);
    }

    @Override
    public void onException(Exception e) {
        mView.onLoadUsersFail(e);
    }
}
