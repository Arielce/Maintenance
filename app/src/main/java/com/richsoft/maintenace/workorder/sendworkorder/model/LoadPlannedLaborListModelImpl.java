package com.richsoft.maintenace.workorder.sendworkorder.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.richsoft.maintenace.bean.workorder.PlannedLaborBean;
import com.richsoft.maintenace.common.BaseRequestModel;
import com.richsoft.maintenace.common.Urls;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import java.util.List;
import ren.solid.library.activity.base.BaseActivity;
import ren.solid.library.net.BaseSingleListener;

/**
 * Created by Administrator on 2016/11/26.
 */

public class LoadPlannedLaborListModelImpl extends BaseRequestModel implements LoadPlannedLaborListModel {
    private BaseActivity mActivity;
    private Request<String> mRequest = null;

    public LoadPlannedLaborListModelImpl(BaseActivity activity) {
        super();
        this.mActivity = activity;
    }

    @Override
    public void loadPlannedLaborList(final BaseSingleListener listener) {
        mRequest= NoHttp.createStringRequest(Urls.GET_PLANNED_LABOR_LIST, RequestMethod.GET);
        requestServer(mActivity,0,mRequest,listener,true,false,true);
    }

    @Override
    protected void parseContent(String content, BaseSingleListener listener) {
        Gson gson = new Gson();
        List<PlannedLaborBean> data = gson.fromJson(content, new TypeToken<List<PlannedLaborBean>>() {
        }.getType());
        listener.onSuccess(data);
    }
}
