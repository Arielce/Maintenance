package com.richsoft.maintenace.workorder.sendworkorder.presenter;

import com.richsoft.maintenace.bean.workorder.SubstationBean;
import com.richsoft.maintenace.workorder.sendworkorder.model.LoadSubstaionsModel;
import com.richsoft.maintenace.workorder.sendworkorder.model.LoadSubstaionsModelImpl;
import com.richsoft.maintenace.workorder.sendworkorder.view.LoadSubstaionsView;
import java.util.List;
import ren.solid.library.activity.base.BaseActivity;
import ren.solid.library.net.BaseSingleListener;

/**
 * Created by Administrator on 2016/11/26.
 */

public class LoadSubstaionsPresenterImpl implements LoadSubstaionsPresenter,BaseSingleListener<List<SubstationBean>> {
    private LoadSubstaionsView mView;
    private BaseActivity mActivity;
    private LoadSubstaionsModel mModel;

    public LoadSubstaionsPresenterImpl(BaseActivity activity, LoadSubstaionsView mView) {
        this.mView = mView;
        this.mActivity=activity;
        this.mModel=new LoadSubstaionsModelImpl(mActivity);
    }

    @Override
    public void loadSubstaions() {
        mModel.loadSubstaions(this);
    }

    @Override
    public void onSuccess(List<SubstationBean> data) {
        mView.onLoadSubstaionsSuccess(data);
    }

    @Override
    public void onError(Exception e) {
        mView.onLoadSubstaionsFail(e);
    }

    @Override
    public void onException(Exception e) {
        mView.onLoadSubstaionsFail(e);
    }
}
