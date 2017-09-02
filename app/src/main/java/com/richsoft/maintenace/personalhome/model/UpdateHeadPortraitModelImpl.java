package com.richsoft.maintenace.personalhome.model;

import android.graphics.Bitmap;

import com.richsoft.maintenace.common.Urls;
import com.yolanda.nohttp.BitmapBinary;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.CacheMode;
import com.yolanda.nohttp.rest.Request;
import ren.solid.library.activity.base.BaseActivity;
import com.richsoft.maintenace.common.BaseRequestModel;
import ren.solid.library.net.BaseSingleListener;
import ren.solid.library.utils.SPUtil;

public class UpdateHeadPortraitModelImpl extends BaseRequestModel implements UpdateHeadPortraitModel {
    private BaseActivity mActivity;
    private Request<String> updateHeadPortraitRequest = null;

    public UpdateHeadPortraitModelImpl(BaseActivity mActivity) {
        this.mActivity = mActivity;
    }

    /**
     * 修改头像
     *
     * @param head
     * @param listener
     */
    @Override
    public void updateHeadPortrait(Bitmap head, BaseSingleListener listener) {
        updateHeadPortraitRequest = NoHttp.createStringRequest(Urls.UPDATE_HEADPORTRAIT_URL, RequestMethod.POST);
        updateHeadPortraitRequest.add("userId", SPUtil.getInstance().getUid());
        updateHeadPortraitRequest.add("picPath", new BitmapBinary(head, null));
        updateHeadPortraitRequest.setCacheMode(CacheMode.ONLY_REQUEST_NETWORK);
        requestServer(mActivity,0,updateHeadPortraitRequest,listener,true,true,true);
    }

    @Override
    protected void parseContent(String content, BaseSingleListener listener) {
        listener.onSuccess(content);
    }

}
