package com.richsoft.maintenace.workorder.workorderdetail.presenter;

import com.richsoft.maintenace.bean.workorder.VideoBean;
import com.richsoft.maintenace.workorder.workorderdetail.model.LoadVideoModel;
import com.richsoft.maintenace.workorder.workorderdetail.model.LoadVideoModelImpl;
import com.richsoft.maintenace.workorder.workorderdetail.view.LoadVideoView;
import ren.solid.library.activity.base.BaseActivity;
import ren.solid.library.net.BaseSingleListener;

/**
 * Created by Administrator on 2016/11/26.
 */

public class LoadVideoPresenterImpl implements LoadVideoPresenter,BaseSingleListener<VideoBean> {
    private LoadVideoView mView;
    private BaseActivity mActivity;
    private LoadVideoModel mModel;

    public LoadVideoPresenterImpl(BaseActivity activity, LoadVideoView mView) {
        this.mView = mView;
        this.mActivity=activity;
        this.mModel=new LoadVideoModelImpl(mActivity);
    }

    @Override
    public void loadVideo(String wid) {
        mModel.loadVideo(wid,this);
    }

    @Override
    public void onSuccess(VideoBean data) {
        mView.onLoadVideoSuccess(data);
    }

    @Override
    public void onError(Exception e) {
        mView.onLoadVideoFail(e);
    }

    @Override
    public void onException(Exception e) {
        mView.onLoadVideoFail(e);
    }
}
