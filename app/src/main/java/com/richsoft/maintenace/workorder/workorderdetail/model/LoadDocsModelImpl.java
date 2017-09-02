package com.richsoft.maintenace.workorder.workorderdetail.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.richsoft.maintenace.bean.workorder.DocumentBean;
import com.richsoft.maintenace.common.BaseRequestModel;
import com.richsoft.maintenace.common.Urls;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.CacheMode;
import com.yolanda.nohttp.rest.Request;
import java.util.List;
import ren.solid.library.activity.base.BaseActivity;
import ren.solid.library.net.BaseSingleListener;
import ren.solid.library.utils.LogUtils;

/**
 * Created by Administrator on 2016/11/26.
 */

public class LoadDocsModelImpl extends BaseRequestModel implements LoadDocsModel {
    private BaseActivity mActivity;
    private Request<String> mRequest = null;

    public LoadDocsModelImpl(BaseActivity activity) {
        super();
        this.mActivity = activity;
    }

    @Override
    public void loadDocs(int type ,String fileName,final BaseSingleListener listener) {
        mRequest= NoHttp.createStringRequest(Urls.URL_DOCUMENT, RequestMethod.GET);
        LogUtils.i("zhoul","0"+(type+1));
        mRequest.add("fileType", "0"+(type+1));
        mRequest.add("fileName", fileName);
        mRequest.setCacheMode(CacheMode.REQUEST_NETWORK_FAILED_READ_CACHE);
        requestServer(mActivity,0,mRequest,listener,true,false,true);
    }

    @Override
    protected void parseContent(String content, BaseSingleListener listener) {
        Gson gson = new Gson();
        List<DocumentBean> data = gson.fromJson(content, new TypeToken<List<DocumentBean>>() {
        }.getType());
        listener.onSuccess(data);
    }
}
