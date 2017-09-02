package com.richsoft.maintenace.workorder.receive.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.richsoft.maintenace.bean.user.UserBean;
import com.richsoft.maintenace.bean.workorder.ReceiveWorkBean;
import com.richsoft.maintenace.common.BaseRequestModel;
import com.richsoft.maintenace.common.Urls;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;

import java.util.ArrayList;
import java.util.List;
import ren.solid.library.activity.base.BaseActivity;
import ren.solid.library.net.BaseSingleListener;

/**
 * Created by Administrator on 2016/11/26.
 */

public class LoadReceiveUsersModelImpl extends BaseRequestModel implements LoadReceiveUsersModel {
    private BaseActivity mActivity;
    private Request<String> mRequest = null;

    public LoadReceiveUsersModelImpl(BaseActivity activity) {
        super();
        this.mActivity = activity;
    }

    @Override
    public void loadReceiveUsers(String wid,final BaseSingleListener listener) {
        ReceiveWorkBean receiveWorkBean1=new ReceiveWorkBean();
        receiveWorkBean1.setReceiveTime("2017-03-19 15:30");
        UserBean u1=new UserBean();
        u1.setPicPath("/sss/jjj.jpg");
        u1.setUserName("张玉馨");
        u1.setUserId("2");
        receiveWorkBean1.setUser(u1);

        ReceiveWorkBean receiveWorkBean2=new ReceiveWorkBean();
        receiveWorkBean2.setReceiveTime("2017-03-19 16:30");
        UserBean u2=new UserBean();
        u2.setPicPath("/sss/jjj.jpg");
        u2.setUserName("皮涛");
        u2.setUserId("3");
        receiveWorkBean2.setUser(u2);

        ArrayList<ReceiveWorkBean> receiveWorkBeans=new ArrayList<>();
        receiveWorkBeans.add(receiveWorkBean1);
        receiveWorkBeans.add(receiveWorkBean2);

        listener.onSuccess(receiveWorkBeans);

        /*mRequest= NoHttp.createStringRequest(Urls.URL_DOCUMENT, RequestMethod.GET);
        mRequest.add("wid", wid);
        mRequest.setCacheMode(CacheMode.ONLY_REQUEST_NETWORK);
        requestServer(mActivity,0,mRequest,listener,true,false,true);*/
    }

    @Override
    protected void parseContent(String content, BaseSingleListener listener) {
        Gson gson = new Gson();
        List<ReceiveWorkBean> data = gson.fromJson(content, new TypeToken<List<ReceiveWorkBean>>() {
        }.getType());
        listener.onSuccess(data);
    }
}
