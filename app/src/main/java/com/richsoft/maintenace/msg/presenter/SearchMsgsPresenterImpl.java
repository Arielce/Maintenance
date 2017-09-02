package com.richsoft.maintenace.msg.presenter;

import com.richsoft.maintenace.bean.msg.SearchResult;
import com.richsoft.maintenace.msg.model.SearchMsgsModel;
import com.richsoft.maintenace.msg.model.SearchMsgsModelImpl;
import com.richsoft.maintenace.msg.view.SearchMsgsView;
import java.util.List;
import ren.solid.library.activity.base.BaseActivity;
import ren.solid.library.net.BaseSingleListener;

/**
 * Created by Administrator on 2016/11/26.
 */

public class SearchMsgsPresenterImpl implements SearchMsgsPresenter,BaseSingleListener<List<SearchResult>> {
    private SearchMsgsView mView;
    private BaseActivity mActivity;
    private SearchMsgsModel mModel;
    private int pageIndex;

    public SearchMsgsPresenterImpl(BaseActivity activity, SearchMsgsView mView) {
        this.mView = mView;
        this.mActivity=activity;
        this.mModel=new SearchMsgsModelImpl(mActivity);
    }

    @Override
    public void searchMsgs(int pageIndex,String key) {
        this.pageIndex=pageIndex;
        mModel.searchMsgs(pageIndex,key,this);
    }

    @Override
    public void onSuccess(List<SearchResult> data) {
        mView.onSearchMsgsSuccess(pageIndex,data);
    }

    @Override
    public void onError(Exception e) {
        mView.onSearchMsgsFail(e);
    }

    @Override
    public void onException(Exception e) {
        mView.onSearchMsgsFail(e);
    }
}
