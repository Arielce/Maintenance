package com.richsoft.maintenace.workorder.sendworkorder.presenter;

import com.richsoft.maintenace.bean.workorder.WorkTaskListBean;
import com.richsoft.maintenace.workorder.sendworkorder.model.LoadTasksModel;
import com.richsoft.maintenace.workorder.sendworkorder.model.LoadTasksModelImpl;
import com.richsoft.maintenace.workorder.sendworkorder.view.LoadTasksView;
import java.util.List;
import ren.solid.library.activity.base.BaseActivity;
import ren.solid.library.net.BaseSingleListener;

/**
 * Created by Administrator on 2016/11/26.
 */

public class LoadTasksPresenterImpl implements LoadTasksPresenter,BaseSingleListener<List<WorkTaskListBean>> {
    private LoadTasksView mView;
    private BaseActivity mActivity;
    private LoadTasksModel mModel;

    public LoadTasksPresenterImpl(BaseActivity activity, LoadTasksView mView) {
        this.mView = mView;
        this.mActivity=activity;
        this.mModel=new LoadTasksModelImpl(mActivity);
    }

    @Override
    public void loadTasks() {
        mModel.loadTasks(this);
    }

    @Override
    public void onSuccess(List<WorkTaskListBean> data) {
        mView.onLoadTasksSuccess(data);
    }

    @Override
    public void onError(Exception e) {
        mView.onLoadTasksFail(e);
    }

    @Override
    public void onException(Exception e) {
        mView.onLoadTasksFail(e);
    }
}
