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

public class UpdatePhoneModelImpl extends BaseRequestModel implements UpdatePhoneModel {
    private BaseActivity mActivity;
    private Request<String> updatePhoneRequest = null;

    public UpdatePhoneModelImpl(BaseActivity mActivity) {
        this.mActivity = mActivity;
    }


    /**
     * 修改电话
     *
     * @param phone
     * @param listener
     */
    @Override
    public void updatePhone(String phone, final BaseSingleListener listener) {
        updatePhoneRequest = NoHttp.createStringRequest(Urls.UPDATE_PHONE_URL, RequestMethod.POST);
        updatePhoneRequest.add("userId", SPUtil.getInstance().getUid());
        updatePhoneRequest.add("phone", phone);
        updatePhoneRequest.setCacheMode(CacheMode.ONLY_REQUEST_NETWORK);
        requestServer(mActivity, 0, updatePhoneRequest, listener,true,true,true);
    }

    @Override
    protected void parseContent(String content, BaseSingleListener listener) {
        listener.onSuccess(content);
    }

}
