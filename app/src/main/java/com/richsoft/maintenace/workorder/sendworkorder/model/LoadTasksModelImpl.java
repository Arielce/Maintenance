package com.richsoft.maintenace.workorder.sendworkorder.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.richsoft.maintenace.bean.workorder.WorkTaskBean;
import com.richsoft.maintenace.bean.workorder.WorkTaskListBean;
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

public class LoadTasksModelImpl extends BaseRequestModel implements LoadTasksModel {
    private BaseActivity mActivity;
    private Request<String> mRequest = null;

    public LoadTasksModelImpl(BaseActivity activity) {
        super();
        this.mActivity = activity;
    }

    @Override
    public void loadTasks(final BaseSingleListener listener) {
        final List<WorkTaskListBean> workTaskListBeans=new ArrayList<>();
        ThreadManager.getLongPool().submit(new Runnable() {
            @Override
            public void run() {
                /**********************第一个开始*********************/
                WorkTaskListBean workTaskListBean1=new WorkTaskListBean();
                workTaskListBean1.setWorkType("0");
                workTaskListBean1.setWorkTypeName("【维护类】带电检测");

                WorkTaskBean wb1=new WorkTaskBean("1","0","暂态低电压局放测试");
                WorkTaskBean wb2=new WorkTaskBean("2","0","红外测试");
                WorkTaskBean wb3=new WorkTaskBean("3","0","一次设备接地导通测试");
                List<WorkTaskBean> data1=new ArrayList<>();
                data1.add(wb1);
                data1.add(wb2);
                data1.add(wb3);
                workTaskListBean1.setWorkTaskBeans(data1);
                workTaskListBeans.add(workTaskListBean1);
                /**********************第一个结束*********************/

                /**********************第二个开始*********************/
                WorkTaskListBean workTaskListBean2=new WorkTaskListBean();
                workTaskListBean2.setWorkType("1");
                workTaskListBean2.setWorkTypeName("【维护类】检查维护");

                WorkTaskBean wb21=new WorkTaskBean("4","1","高压带电显示装置维护");
                WorkTaskBean wb22=new WorkTaskBean("5","1","接地引下线维护");
                WorkTaskBean wb23=new WorkTaskBean("6","1","变电站消防系统维护");
                WorkTaskBean wb24=new WorkTaskBean("7","1","防汛设施检查维护");
                WorkTaskBean wb25=new WorkTaskBean("8","1","设备铭牌等标识维护、更换，围栏、警示牌等安全设施检查");
                WorkTaskBean wb26=new WorkTaskBean("9","1","高压熔断器（一次熔断器）更换工作");
                WorkTaskBean wb27=new WorkTaskBean("10","1","低压熔断器（二次熔断器）更换工作");
                List<WorkTaskBean> data2=new ArrayList<>();
                data2.add(wb21);
                data2.add(wb22);
                data2.add(wb23);
                data2.add(wb24);
                data2.add(wb25);
                data2.add(wb26);
                data2.add(wb27);
                workTaskListBean2.setWorkTaskBeans(data2);
                workTaskListBeans.add(workTaskListBean2);
                /**********************第二个结束*********************/

                /**********************第三个开始*********************/
                WorkTaskListBean workTaskListBean3=new WorkTaskListBean();
                workTaskListBean3.setWorkType("2");
                workTaskListBean3.setWorkTypeName("【巡视操作类】");

                WorkTaskBean wb31=new WorkTaskBean("11","2","正常巡视");
                WorkTaskBean wb32=new WorkTaskBean("12","2","全面巡视");
                WorkTaskBean wb33=new WorkTaskBean("13","2","熄灯巡视");
                WorkTaskBean wb34=new WorkTaskBean("14","2","防汛设施检查维护");
                WorkTaskBean wb35=new WorkTaskBean("15","2","特殊巡视【冰雹】");
                WorkTaskBean wb36=new WorkTaskBean("16","2","特殊巡视【大雪】");
                WorkTaskBean wb37=new WorkTaskBean("17","2","特殊巡视【雾霾】");
                List<WorkTaskBean> data3=new ArrayList<>();
                data3.add(wb31);
                data3.add(wb32);
                data3.add(wb33);
                data3.add(wb34);
                data3.add(wb35);
                data3.add(wb36);
                data3.add(wb37);
                workTaskListBean3.setWorkTaskBeans(data3);
                workTaskListBeans.add(workTaskListBean3);
                /**********************第三个结束*********************/
                UIUtils.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onSuccess(workTaskListBeans);
                    }
                });
            }
        });

        /*mRequest= NoHttp.createStringRequest(Urls.GET_TASKS, RequestMethod.GET);
        mRequest.add("workType", 1);
        mRequest.add("flag", 1);
        requestServer(mActivity,0,mRequest,listener,true,false,true);*/
    }

    @Override
    protected void parseContent(String content, BaseSingleListener listener) {
        Gson gson = new Gson();
        List<WorkTaskBean> data = gson.fromJson(content, new TypeToken<List<WorkTaskBean>>() {
        }.getType());
        listener.onSuccess(data);
    }
}
