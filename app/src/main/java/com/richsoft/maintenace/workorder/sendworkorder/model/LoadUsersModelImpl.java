package com.richsoft.maintenace.workorder.sendworkorder.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.richsoft.maintenace.bean.user.UserBean;
import com.richsoft.maintenace.common.BaseRequestModel;
import com.yolanda.nohttp.rest.Request;

import java.util.ArrayList;
import java.util.List;
import ren.solid.library.activity.base.BaseActivity;
import ren.solid.library.manager.ThreadManager;
import ren.solid.library.net.BaseSingleListener;
import ren.solid.library.utils.UIUtils;

/**
 * Created by Administrator on 2016/11/26.
 */

public class LoadUsersModelImpl extends BaseRequestModel implements LoadUsersModel {
    private BaseActivity mActivity;
    private Request<String> mRequest = null;

    public LoadUsersModelImpl(BaseActivity activity) {
        super();
        this.mActivity = activity;
    }

    @Override
    public void loadUsers(final BaseSingleListener listener) {
        final List<UserBean> data=new ArrayList<>();
        ThreadManager.getLongPool().submit(new Runnable() {
            @Override
            public void run() {
                UserBean u1=new UserBean("2","张玉馨","正值");
                UserBean u2=new UserBean("3","皮涛","正值");
                UserBean u3=new UserBean("4","王鹏","副职");

                data.add(u1);
                data.add(u2);
                data.add(u3);

                UIUtils.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onSuccess(data);
                    }
                });
            }
        });
        /*mRequest= NoHttp.createStringRequest(Urls.GET_USERS, RequestMethod.GET);
        mRequest.add("orgId", 1);
        mRequest.add("flag", 1);
        requestServer(mActivity,0,mRequest,listener,true,false,false);*/
    }

    @Override
    protected void parseContent(String content, BaseSingleListener listener) {
        Gson gson = new Gson();
        List<UserBean> data = gson.fromJson(content, new TypeToken<List<UserBean>>() {
        }.getType());
        listener.onSuccess(data);
    }
}
