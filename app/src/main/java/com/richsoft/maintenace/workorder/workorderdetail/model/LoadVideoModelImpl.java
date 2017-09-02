package com.richsoft.maintenace.workorder.workorderdetail.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.richsoft.maintenace.bean.workorder.VideoBean;
import com.richsoft.maintenace.common.BaseRequestModel;
import com.richsoft.maintenace.common.Urls;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.CacheMode;
import com.yolanda.nohttp.rest.Request;
import ren.solid.library.activity.base.BaseActivity;
import ren.solid.library.net.BaseSingleListener;

/**
 * Created by Administrator on 2016/11/26.
 */

public class LoadVideoModelImpl extends BaseRequestModel implements LoadVideoModel {
    private BaseActivity mActivity;
    private Request<String> mRequest = null;

    public LoadVideoModelImpl(BaseActivity activity) {
        super();
        this.mActivity = activity;
    }

    @Override
    public void loadVideo(String wid ,final BaseSingleListener listener) {
        mRequest= NoHttp.createStringRequest(Urls.URL_GETVIDEO, RequestMethod.GET);
        mRequest.add("wid",wid);
        mRequest.setCacheMode(CacheMode.REQUEST_NETWORK_FAILED_READ_CACHE);
        requestServer(mActivity,0,mRequest,listener,true,false,true);
    }

    @Override
    protected void parseContent(String content, BaseSingleListener listener) {
        Gson gson = new Gson();
        VideoBean data = gson.fromJson(content, new TypeToken<VideoBean>() {
        }.getType());
        listener.onSuccess(data);
    }
}
