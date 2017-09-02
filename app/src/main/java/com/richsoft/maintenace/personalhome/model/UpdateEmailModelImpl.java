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

public class UpdateEmailModelImpl extends BaseRequestModel implements UpdateEmailModel {
    private BaseActivity mActivity;
    private Request<String> updateEmailRequest = null;

    public UpdateEmailModelImpl(BaseActivity mActivity) {
        this.mActivity = mActivity;
    }

    /**
     * 修改邮箱
     *
     * @param email
     * @param listener
     */
    @Override
    public void updateEmail(String email, BaseSingleListener listener) {
        updateEmailRequest = NoHttp.createStringRequest(Urls.UPDATE_EMAIL_URL, RequestMethod.POST);
        updateEmailRequest.add("userId", SPUtil.getInstance().getUid());
        updateEmailRequest.add("email", email);
        updateEmailRequest.setCacheMode(CacheMode.ONLY_REQUEST_NETWORK);
        requestServer(mActivity,0,updateEmailRequest,listener,true,true,true);
    }

    @Override
    protected void parseContent(String content, BaseSingleListener listener) {
        listener.onSuccess(content);
    }

}
