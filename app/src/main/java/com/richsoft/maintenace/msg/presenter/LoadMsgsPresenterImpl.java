package com.richsoft.maintenace.msg.presenter;

import com.richsoft.maintenace.bean.msg.GanHuoDataBean;
import com.richsoft.maintenace.msg.model.LoadMsgsModel;
import com.richsoft.maintenace.msg.model.LoadMsgsModelImpl;
import com.richsoft.maintenace.msg.view.MsgsView;
import java.util.List;
import ren.solid.library.activity.base.BaseActivity;
import ren.solid.library.net.BaseSingleListener;

/**
 * Created by Administrator on 2016/11/26.
 */

public class LoadMsgsPresenterImpl implements LoadMsgsPresenter,BaseSingleListener<List<GanHuoDataBean>> {
    private MsgsView mView;
    private BaseActivity mActivity;
    private LoadMsgsModel mModel;
    private int pageIndex;

    public LoadMsgsPresenterImpl(BaseActivity activity, MsgsView mView) {
        this.mView = mView;
        this.mActivity=activity;
        this.mModel=new LoadMsgsModelImpl(mActivity);
    }

    @Override
    public void loadMsgs(int pageIndex,String type) {
        this.pageIndex=pageIndex;
        mModel.loadMsgs(pageIndex,type,this);
    }

    @Override
    public void onSuccess(List<GanHuoDataBean> data) {
        mView.onLoadMsgsSuccess(pageIndex,data);
    }

    @Override
    public void onError(Exception e) {
        mView.onLoadMsgsFail(e);
    }

    @Override
    public void onException(Exception e) {
        mView.onLoadMsgsFail(e);
    }
}
