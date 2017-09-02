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


public class UpdatePhone2ModelImpl extends BaseRequestModel implements UpdatePhone2Model {
    private BaseActivity mActivity;
    private Request<String> updatePhone2Request = null;

    public UpdatePhone2ModelImpl(BaseActivity mActivity) {
        this.mActivity = mActivity;
    }


    /**
     * 修改备用电话
     *
     * @param phone2
     * @param listener
     */
    @Override
    public void updatePhone2(String phone2, BaseSingleListener listener) {
        updatePhone2Request = NoHttp.createStringRequest(Urls.UPDATE_PHONE2_URL, RequestMethod.POST);
        updatePhone2Request.add("userId", SPUtil.getInstance().getUid());
        updatePhone2Request.add("phone2", phone2);
        updatePhone2Request.setCacheMode(CacheMode.ONLY_REQUEST_NETWORK);
        requestServer(mActivity,0,updatePhone2Request,listener,true,true,true);
    }

    @Override
    protected void parseContent(String content, BaseSingleListener listener) {
        listener.onSuccess(content);
    }


}
