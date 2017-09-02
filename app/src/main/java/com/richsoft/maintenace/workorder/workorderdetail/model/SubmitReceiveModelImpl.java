package com.richsoft.maintenace.workorder.workorderdetail.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.richsoft.maintenace.bean.workorder.DocumentBean;
import com.richsoft.maintenace.common.BaseRequestModel;
import com.richsoft.maintenace.common.Urls;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;

import java.util.List;

import ren.solid.library.activity.base.BaseActivity;
import ren.solid.library.manager.ThreadManager;
import ren.solid.library.net.BaseSingleListener;
import ren.solid.library.utils.LogUtils;
import ren.solid.library.utils.SPUtil;
import ren.solid.library.utils.UIUtils;

/**
 * Created by Administrator on 2016/11/26.
 */

public class SubmitReceiveModelImpl extends BaseRequestModel implements SubmitReceiveModel {
    private BaseActivity mActivity;
    private Request<String> mRequest = null;

    public SubmitReceiveModelImpl(BaseActivity activity) {
        super();
        this.mActivity = activity;
    }

    @Override
    public void submitReceive(String wid,final BaseSingleListener listener) {
        LogUtils.i("zhoul","接受模型类执行接受");
        mActivity.showProgressDialog("正在提交接受信息！","请您稍候...",false);
        ThreadManager.getLongPool().submit(new Runnable() {
            @Override
            public void run() {
                UIUtils.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mActivity.dismissProgressDialog();
                        listener.onSuccess("2017-03-19 11:30");
                    }
                },1000);
            }
        });
        /*mRequest= NoHttp.createStringRequest(Urls.URL_DOCUMENT, RequestMethod.POST);
        mRequest.add("uid", SPUtil.getInstance().getUid());
        mRequest.add("wid", wid);
        mRequest.setCacheMode(CacheMode.ONLY_REQUEST_NETWORK);
        requestServer(mActivity,0,mRequest,listener,true,true,true);*/
    }

    @Override
    protected void parseContent(String content, BaseSingleListener listener) {
        listener.onSuccess(content);
    }
}
