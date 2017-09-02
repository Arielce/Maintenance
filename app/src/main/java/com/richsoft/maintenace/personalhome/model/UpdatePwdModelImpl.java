package com.richsoft.maintenace.personalhome.model;

import com.richsoft.maintenace.common.Urls;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.CacheMode;
import com.yolanda.nohttp.rest.Request;

import ren.solid.library.activity.base.BaseActivity;
import com.richsoft.maintenace.common.BaseRequestModel;

import org.apache.commons.codec.digest.DigestUtils;

import ren.solid.library.net.BaseSingleListener;
import ren.solid.library.utils.SPUtil;

public class UpdatePwdModelImpl extends BaseRequestModel implements UpdatePwdModel {
    private BaseActivity mActivity;
    private Request<String> updatePwdRequest = null;

    public UpdatePwdModelImpl(BaseActivity mActivity) {
        this.mActivity = mActivity;
    }

    /**
     * 修改密码
     *
     * @param originalPwd
     * @param newPwd
     * @param listener
     */
    @Override
    public void updatePwd(String originalPwd, String newPwd, final BaseSingleListener listener) {
        updatePwdRequest = NoHttp.createStringRequest(Urls.UPDATE_PWD_URL, RequestMethod.POST);
        updatePwdRequest.add("userId", SPUtil.getInstance().getUid());
        updatePwdRequest.add("oldPwd", DigestUtils.md5Hex(originalPwd));
        updatePwdRequest.add("pwd", DigestUtils.md5Hex(newPwd));
        updatePwdRequest.setCacheMode(CacheMode.ONLY_REQUEST_NETWORK);
        requestServer(mActivity, 0, updatePwdRequest, listener,true,true,true);
    }

    @Override
    protected void parseContent(String content, BaseSingleListener listener) {
        listener.onSuccess(content);
    }

}
