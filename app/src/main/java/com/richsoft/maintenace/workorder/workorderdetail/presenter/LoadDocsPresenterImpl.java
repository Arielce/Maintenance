package com.richsoft.maintenace.workorder.workorderdetail.presenter;

import com.richsoft.maintenace.bean.workorder.DocumentBean;
import com.richsoft.maintenace.workorder.workorderdetail.model.LoadDocsModel;
import com.richsoft.maintenace.workorder.workorderdetail.model.LoadDocsModelImpl;
import com.richsoft.maintenace.workorder.workorderdetail.view.LoadDocsView;
import java.util.List;
import ren.solid.library.activity.base.BaseActivity;
import ren.solid.library.net.BaseSingleListener;

/**
 * Created by Administrator on 2016/11/26.
 */

public class LoadDocsPresenterImpl implements LoadDocsPresenter,BaseSingleListener<List<DocumentBean>> {
    private LoadDocsView mView;
    private BaseActivity mActivity;
    private LoadDocsModel mModel;

    public LoadDocsPresenterImpl(BaseActivity activity, LoadDocsView mView) {
        this.mView = mView;
        this.mActivity=activity;
        this.mModel=new LoadDocsModelImpl(mActivity);
    }

    @Override
    public void loadDocs(int type,String fileName) {
        mModel.loadDocs(type,fileName,this);
    }

    @Override
    public void onSuccess(List<DocumentBean> data) {
        mView.onLoadDocsSuccess(data);
    }

    @Override
    public void onError(Exception e) {
        mView.onLoadDocsFail(e);
    }

    @Override
    public void onException(Exception e) {
        mView.onLoadDocsFail(e);
    }
}
