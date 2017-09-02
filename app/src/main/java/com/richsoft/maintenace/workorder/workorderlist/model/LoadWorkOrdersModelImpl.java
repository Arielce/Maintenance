package com.richsoft.maintenace.workorder.workorderlist.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.richsoft.maintenace.bean.user.UserBean;
import com.richsoft.maintenace.bean.workorder.SubstationBean;
import com.richsoft.maintenace.bean.workorder.WorkOrderBean;
import com.richsoft.maintenace.bean.workorder.WorkTaskBean;
import com.richsoft.maintenace.common.BaseRequestModel;
import com.yolanda.nohttp.rest.Request;
import java.util.ArrayList;
import java.util.List;
import ren.solid.library.activity.base.BaseActivity;
import ren.solid.library.net.BaseSingleListener;
import ren.solid.library.utils.LogUtils;

/**
 * Created by Administrator on 2016/11/26.
 */

public class LoadWorkOrdersModelImpl extends BaseRequestModel implements LoadWorkOrdersModel {
    private BaseActivity mActivity;
    private Request<String> mRequest = null;

    public LoadWorkOrdersModelImpl(BaseActivity activity) {
        super();
        this.mActivity = activity;
    }

    @Override
    public void loadWorkOrders(int pageIndex,int type ,final BaseSingleListener listener) {
        List<WorkOrderBean> wbs=new ArrayList<>();
        UserBean monitor=new UserBean();
        monitor.setUserId("01");
        monitor.setUserName("王海泉");
        monitor.setPicPath("http://www.qqtouxiang.com/d/file/qinglv/20170212/b1d7176b20c98ea9f1fec59f90513813.jpg");

        UserBean ruler=new UserBean();
        ruler.setUserId("02");
        ruler.setUserName("张玉馨");
        ruler.setPicPath("http://www.qqtouxiang.com/d/file/qinglv/20170212/b1d7176b20c98ea9f1fec59f90513813.jpg");

        List<UserBean> members=new ArrayList<>();
        UserBean u1=new UserBean();
        u1.setUserId("03");
        u1.setUserName("王鹏");
        u1.setPicPath("http://www.qqtouxiang.com/d/file/qinglv/20170212/b1d7176b20c98ea9f1fec59f90513813.jpg");
        UserBean u2=new UserBean();
        u2.setUserId("04");
        u2.setUserName("皮涛");
        u2.setPicPath("http://www.qqtouxiang.com/d/file/qinglv/20170212/b1d7176b20c98ea9f1fec59f90513813.jpg");
        members.add(u1);
        members.add(u2);

        WorkTaskBean task=new WorkTaskBean();
        task.setWorkType("1");
        task.setWorkID("1");
        task.setWorkName("开关柜暂态地电波检测");

        WorkTaskBean task1=new WorkTaskBean();
        task1.setWorkType("1");
        task1.setWorkID("2");
        task1.setWorkName("开关柜红外检测");

        WorkTaskBean task2=new WorkTaskBean();
        task2.setWorkType("1");
        task2.setWorkID("3");
        task2.setWorkName("开关柜超声波检测");

        SubstationBean substation=new SubstationBean();
        substation.setSubstationName("珠江道");
        substation.setSubstationID("001");
        substation.setSubstationLevel("35kV");

        switch (type){
            case 0:
                WorkOrderBean wb1=new WorkOrderBean("200001","1","城南电力公司","1","变电运维一班",monitor,ruler,members,"2017-02-28","","","2","2017-03-14 18:00","","","","","",task,substation);
                WorkOrderBean wb2=new WorkOrderBean("200002","1","城南电力公司","1","变电运维一班",monitor,ruler,members,"2017-02-28","","","3","2017-03-14 18:00","2017-03-14 19:00","","","","",task1,substation);
                WorkOrderBean wb3=new WorkOrderBean("200003","1","城南电力公司","1","变电运维一班",monitor,ruler,members,"2017-02-28","","","4","2017-03-14 18:00","2017-03-14 19:00","2017-03-14 19:00","","","",task2,substation);
                WorkOrderBean wb5=new WorkOrderBean("200005","1","城南电力公司","1","变电运维一班",monitor,ruler,members,"2017-02-28","","","1","","","","","","",task2,substation);
                wbs.add(wb1);
                wbs.add(wb2);
                wbs.add(wb3);
                wbs.add(wb5);
                break;
            case 1:
                WorkOrderBean wb6=new WorkOrderBean("200006","1","城南电力公司","1","变电运维一班",monitor,ruler,members,"2017-02-28","","","5","2017-03-14 18:00","2017-03-14 18:00","2017-03-14 18:00","2017-03-14 18:00","2017-03-14 18:00","",task,substation);
                wbs.add(wb6);
                break;
            case 2:
                WorkOrderBean wb7=new WorkOrderBean("200007","1","城南电力公司","1","变电运维一班",monitor,ruler,members,"2017-02-28","","","6","2017-03-14 18:00","2017-03-14 18:00","2017-03-14 18:00","2017-03-14 18:00","2017-03-14 18:00","2017-03-14 18:00",task,substation);
                wbs.add(wb7);
                break;
        }
        LogUtils.i("zhoul",new Gson().toJson(wbs));
        listener.onSuccess(wbs);

        /*mRequest= NoHttp.createStringRequest(Urls.GET_WORKORDER_LIST, RequestMethod.GET);
        mRequest.add("uid", "1");
        mRequest.add("page", pageIndex);
        mRequest.add("pagenum", Urls.PAGE_COUNT);
        mRequest.add("stateType", type);
        mRequest.setCacheMode(CacheMode.REQUEST_NETWORK_FAILED_READ_CACHE);
        requestServer(mActivity,0,mRequest,listener,true,false,true);*/
    }

    @Override
    protected void parseContent(String content, BaseSingleListener listener) {
        Gson gson = new Gson();
        List<WorkOrderBean> data = gson.fromJson(content, new TypeToken<List<WorkOrderBean>>() {
        }.getType());
        listener.onSuccess(data);
    }
}
