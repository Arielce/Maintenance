package com.richsoft.maintenace.workorder.sendworkorder.presenter;

import com.richsoft.maintenace.bean.workorder.PlannedLaborBean;
import com.richsoft.maintenace.workorder.sendworkorder.model.LoadPlannedLaborListModel;
import com.richsoft.maintenace.workorder.sendworkorder.model.LoadPlannedLaborListModelImpl;
import com.richsoft.maintenace.workorder.sendworkorder.view.PlannedLaborView;
import java.util.List;
import ren.solid.library.activity.base.BaseActivity;
import ren.solid.library.net.BaseSingleListener;

/**
 * Created by Administrator on 2016/11/26.
 */

public class LoadPlannedLaborListPresenterImpl implements LoadPlannedLaborListPresenter,BaseSingleListener<List<PlannedLaborBean>> {
    private PlannedLaborView mView;
    private BaseActivity mActivity;
    private LoadPlannedLaborListModel mModel;

    public LoadPlannedLaborListPresenterImpl(BaseActivity activity, PlannedLaborView mView) {
        this.mView = mView;
        this.mActivity=activity;
        this.mModel=new LoadPlannedLaborListModelImpl(mActivity);
    }

    @Override
    public void loadPlannedLaborList() {
        mModel.loadPlannedLaborList(this);
    }

    @Override
    public void onSuccess(List<PlannedLaborBean> data) {
        mView.onLoadPlannedLaborsSuccess(data);
    }

    @Override
    public void onError(Exception e) {
        mView.onLoadPlannedLaborsFail(e);
    }

    @Override
    public void onException(Exception e) {
        mView.onLoadPlannedLaborsFail(e);
    }
}
