package com.richsoft.maintenace.personalhome.model;

import com.richsoft.maintenace.common.Urls;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.CacheMode;
import com.yolanda.nohttp.rest.Request;

import ren.solid.library.activity.base.BaseActivity;
import com.richsoft.maintenace.common.BaseRequestModel;
import ren.solid.library.net.BaseSingleListener;
import ren.solid.library.utils.SPUtil;

public class UpdateRemarkModelImpl extends BaseRequestModel implements UpdateRemarkModel {
    private BaseActivity mActivity;
    private Request<String> updateRemarkRequest = null;

    public UpdateRemarkModelImpl(BaseActivity mActivity) {
        this.mActivity = mActivity;
    }

    /**
     * 修改个性签名
     *
     * @param remark
     * @param listener
     */
    @Override
    public void updateRemark(String remark, final BaseSingleListener listener) {
        updateRemarkRequest = NoHttp.createStringRequest(Urls.UPDATE_REMARK_URL, RequestMethod.POST);
        updateRemarkRequest.add("userId", SPUtil.getInstance().getUid());
        updateRemarkRequest.add("remark", remark);
        updateRemarkRequest.setCacheMode(CacheMode.ONLY_REQUEST_NETWORK);
        requestServer(mActivity, 0, updateRemarkRequest, listener,true,true,true);
    }

    @Override
    protected void parseContent(String content, BaseSingleListener listener) {
        listener.onSuccess(content);
    }

}
