package com.richsoft.maintenace.workorder.sendworkorder.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.richsoft.maintenace.bean.workorder.SubstationBean;
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

public class LoadSubstaionsModelImpl extends BaseRequestModel implements LoadSubstaionsModel {
    private BaseActivity mActivity;
    private Request<String> mRequest = null;

    public LoadSubstaionsModelImpl(BaseActivity activity) {
        super();
        this.mActivity = activity;
    }

    @Override
    public void loadSubstaions(final BaseSingleListener listener) {
        final List<SubstationBean> data=new ArrayList<>();
        ThreadManager.getLongPool().submit(new Runnable() {
            @Override
            public void run() {
                SubstationBean sb1=new SubstationBean("1","小海地","110kV");
                SubstationBean sb2=new SubstationBean("2","珠江道","35kV");
                SubstationBean sb3=new SubstationBean("3","双林","35kV");
                data.add(sb1);
                data.add(sb2);
                data.add(sb3);
                UIUtils.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onSuccess(data);
                    }
                });
            }
        });
        /*mRequest= NoHttp.createStringRequest(Urls.GET_SUBSTAIONS, RequestMethod.GET);
        requestServer(mActivity,0,mRequest,listener,true,false,true);*/
    }

    @Override
    protected void parseContent(String content, BaseSingleListener listener) {
        Gson gson = new Gson();
        List<SubstationBean> data = gson.fromJson(content, new TypeToken<List<SubstationBean>>() {
        }.getType());
        listener.onSuccess(data);
    }
}
