package com.richsoft.maintenace.msg.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.richsoft.maintenace.bean.msg.GanHuoDataBean;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.List;
import ren.solid.library.activity.base.BaseActivity;
import ren.solid.library.net.BaseSingleListener;
import ren.solid.library.net.CallServer;
import ren.solid.library.net.HttpListener;
import ren.solid.library.utils.LogUtils;

/**
 * Created by Administrator on 2016/11/26.
 */

public class LoadMsgsModelImpl implements LoadMsgsModel {
    private BaseActivity mActivity;
    private Request<String> mRequest = null;

    public LoadMsgsModelImpl(BaseActivity activity) {
        super();
        this.mActivity = activity;
    }

    @Override
    public void loadMsgs(int pageIndex,String type ,final BaseSingleListener listener) {
        mRequest= NoHttp.createStringRequest("http://www.gank.io/api/data/"+ URLEncoder.encode(type)+"/20/"+pageIndex, RequestMethod.GET);
        CallServer.getRequestInstance().add(mActivity, 0, mRequest, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                LogUtils.i("zhoul", response.get());
                try {
                    JSONObject obj = new JSONObject(response.get());
                    if (!obj.getBoolean("error")) {
                        parseContent(obj.getString("results"),listener);
                    }else{
                        listener.onException(new Exception("解析失败"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    listener.onException(e);
                }
            }

            @Override
            public void onFailed(int what, Response<String> response) {
                listener.onException(response.getException());
            }
        }, true, false);
    }

    protected void parseContent(String content, BaseSingleListener listener) {
        Gson gson = new Gson();
        List<GanHuoDataBean> data = gson.fromJson(content, new TypeToken<List<GanHuoDataBean>>() {
        }.getType());
        listener.onSuccess(data);
    }
}
