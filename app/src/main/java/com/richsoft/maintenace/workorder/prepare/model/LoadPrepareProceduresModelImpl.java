package com.richsoft.maintenace.workorder.prepare.model;

import com.richsoft.maintenace.bean.workorder.PrepareBean;
import com.richsoft.maintenace.bean.workorder.PrepareProcedureBean;
import com.richsoft.maintenace.bean.workorder.PrepareProcedureRequirementBean;
import java.util.ArrayList;
import java.util.List;
import ren.solid.library.activity.base.BaseActivity;
import ren.solid.library.net.BaseSingleListener;
/**
 * Created by Administrator on 2016/11/26.
 */

public class LoadPrepareProceduresModelImpl implements LoadPrepareProceduresModel {
    private BaseActivity mActivity;
    private List<PrepareProcedureBean> PrepareProcedureBean;

    public LoadPrepareProceduresModelImpl(BaseActivity activity) {
        super();
        this.mActivity = activity;
    }

    @Override
    public void loadPrepareProcedures(String orderNo ,final BaseSingleListener listener) {
        PrepareBean prepareBean=new PrepareBean();
        prepareBean.setPrepareId("p01");
        List<PrepareProcedureBean> prepareProcedureBeans=new ArrayList<>();
        prepareBean.setPrepareBeans(prepareProcedureBeans);
        PrepareProcedureBean ppb1=new PrepareProcedureBean("pp01","局放测试仪，备品备件、工器具运至工作现场","对备件进行检查，保证完好。",new ArrayList<PrepareProcedureRequirementBean>());
        ppb1.prepareProcedureRequirementBeans.add(new PrepareProcedureRequirementBean(0,"a","检查局放测试仪，超声测试仪是否在有效期"));
        ppb1.prepareProcedureRequirementBeans.add(new PrepareProcedureRequirementBean(0,"b","检查仪器是否完好"));
        ppb1.prepareProcedureRequirementBeans.add(new PrepareProcedureRequirementBean(0,"c","电池是否电量充足"));
        prepareProcedureBeans.add(ppb1);
        PrepareProcedureBean ppb2=new PrepareProcedureBean("pp02","工作人员就位","工作人员应分工明确：确认仪器测试人员，安全监护及数据记录人员。",new ArrayList<PrepareProcedureRequirementBean>());
        ppb2.prepareProcedureRequirementBeans.add(new PrepareProcedureRequirementBean(1,"d","指定局防检测1人"));
        ppb2.prepareProcedureRequirementBeans.add(new PrepareProcedureRequirementBean(4,"e","确认安全监护人"));
        ppb2.prepareProcedureRequirementBeans.add(new PrepareProcedureRequirementBean(0,"f","穿好工作服，戴好安全帽"));
        prepareProcedureBeans.add(ppb2);
        PrepareProcedureBean ppb3=new PrepareProcedureBean("pp03","检查环境情况","1.检测中应尽量避免干扰源（如气体放电灯、排风系统电机）等带来的影响；2.信号线应完全展开，避免与电源线（若有）缠绕一起，必要时可关闭开关室内照明灯及通风设备。",new ArrayList<PrepareProcedureRequirementBean>());
        ppb3.prepareProcedureRequirementBeans.add(new PrepareProcedureRequirementBean(2,"g","记录环境温度"));
        ppb3.prepareProcedureRequirementBeans.add(new PrepareProcedureRequirementBean(3,"h","记录环境湿度"));
        ppb3.prepareProcedureRequirementBeans.add(new PrepareProcedureRequirementBean(0,"i","大气条件是否满足试验要求"));
        prepareProcedureBeans.add(ppb3);

        PrepareProcedureBean ppb4=new PrepareProcedureBean("pp04","检查安全措施","1.注意保持与带电设备的安全距离。\n" +
                "2.工作中注意周围环境，注意防止摔、磕、碰。\n" +
                "3.雷电时禁止进行检测。\n" +
                "4.测试现场出现明显异常情况时（如异音、电压波动、系统接地等），应立即停止测试工作并撤离现场。",new ArrayList<PrepareProcedureRequirementBean>());
        ppb4.prepareProcedureRequirementBeans.add(new PrepareProcedureRequirementBean(0,"j","核对工作设备名称正确，检查现场符合工作条件。"));
        prepareProcedureBeans.add(ppb4);

        PrepareProcedureBean ppb5=new PrepareProcedureBean("pp05","局放测试要求","无风险描述",new ArrayList<PrepareProcedureRequirementBean>());
        ppb5.prepareProcedureRequirementBeans.add(new PrepareProcedureRequirementBean(0,"k","明确暂态地电压局部放电检测至少一年一次，每年迎峰度夏（冬）前应开展一次"));
        ppb5.prepareProcedureRequirementBeans.add(new PrepareProcedureRequirementBean(0,"l","明确新投运和解体检修后的设备，应在投运后1个月内进行一次运行电压下的检测，记录开关柜每一面的测试数据作为初始数据，以后测试中作为参考"));
        ppb5.prepareProcedureRequirementBeans.add(new PrepareProcedureRequirementBean(0,"m","明确对存在异常的开关柜设备，在该异常不能完全判定时，可根据开关柜设备的运行工况缩短检测周期"));
        ppb5.prepareProcedureRequirementBeans.add(new PrepareProcedureRequirementBean(0,"n","明确应在设备投入运行30分钟后，方可进行带电测试"));
        prepareProcedureBeans.add(ppb5);

        listener.onSuccess(prepareBean);
    }
}
