package com.richsoft.maintenace.workorder.filed.model;

import com.richsoft.maintenace.bean.workorder.FieldWorkBean;
import com.richsoft.maintenace.bean.workorder.FieldWorkProcedureBean;
import com.richsoft.maintenace.bean.workorder.FieldWorkProcedureRequirementBean;
import com.richsoft.maintenace.bean.workorder.SwitchRoomBean;
import com.richsoft.maintenace.bean.workorder.SwitchRoomDeviceBean;
import com.richsoft.maintenace.bean.workorder.SwitchRoomDeviceRowBean;
import java.util.ArrayList;
import java.util.List;
import ren.solid.library.activity.base.BaseActivity;
import ren.solid.library.net.BaseSingleListener;

/**
 * Created by Administrator on 2016/11/26.
 */

public class LoadFieldWorkProcedureModelImpl implements LoadFieldWorkProcedureModel {
    private BaseActivity mActivity;
    private FieldWorkBean fieldWorkBean;

    public LoadFieldWorkProcedureModelImpl(BaseActivity activity) {
        super();
        this.mActivity = activity;
    }

    @Override
    public void loadFieldWorkProcedure(String orderNo ,final BaseSingleListener listener) {
        fieldWorkBean=new FieldWorkBean();
        createFieldWorkProcedureBeans();

        listener.onSuccess(fieldWorkBean);
    }

    private void createFieldWorkProcedureBeans() {
        List<FieldWorkProcedureBean> fieldWorkProcedureBeans= new ArrayList<>();
        fieldWorkBean.setFieldWorkProcedureBeans(fieldWorkProcedureBeans);
        List<FieldWorkProcedureRequirementBean> fieldWorkProcedureRequirementBeans1=new ArrayList<>();
        FieldWorkProcedureRequirementBean f1=new FieldWorkProcedureRequirementBean();
        f1.type=0;
        f1.title="使用前用数据线对局放测试仪进行自检，数据线一端插入检测口，另一端逐渐靠近测试仪前部，数值越来越大则为正常";
        f1.verify=0;
        fieldWorkProcedureRequirementBeans1.add(f1);
        FieldWorkProcedureRequirementBean f2=new FieldWorkProcedureRequirementBean();
        f2.type=1;
        f2.title="将测试仪调至TEV档，指向空气中，测量开关室内大气背景噪声三次，取最大值，记录数据";
        f2.dataUnit="db";
        fieldWorkProcedureRequirementBeans1.add(f2);
        FieldWorkProcedureRequirementBean f3=new FieldWorkProcedureRequirementBean();
        f3.type=1;
        f3.title="将测试仪的测试端与开关室内金属物件紧密接触，测试开关室金属物体的背景噪声，记录数据（该值如果大于空气噪音，请您核实异常）";
        f3.dataUnit="db";
        fieldWorkProcedureRequirementBeans1.add(f3);
        FieldWorkProcedureRequirementBean f4=new FieldWorkProcedureRequirementBean();
        f4.type=3;
        f4.title="将测试仪测试端与开关柜柜体紧密接触，贴紧至少3s，待数据稳定后测试开关柜局放幅值，分别测试开关柜正面中间及下部，背面及侧面的上，中，下部位，记录测试结果。";

        List<SwitchRoomBean> switchRooms=new ArrayList<>();
        f4.switchRooms=switchRooms;

        SwitchRoomBean mSwitchRoomBean1=new SwitchRoomBean();
        switchRooms.add(mSwitchRoomBean1);
        mSwitchRoomBean1.setSwitchRoomID("1");
        mSwitchRoomBean1.setSwitchRoomName("10kV开关室");

        ArrayList<SwitchRoomDeviceRowBean> switchRoomDeviceRowBeans1=new ArrayList<>();
        mSwitchRoomBean1.setSwitchRoomDeviceRowBeans(switchRoomDeviceRowBeans1);

        SwitchRoomDeviceRowBean switchRoomDeviceRowBean1=new SwitchRoomDeviceRowBean();
        SwitchRoomDeviceRowBean switchRoomDeviceRowBean2=new SwitchRoomDeviceRowBean();
        switchRoomDeviceRowBeans1.add(switchRoomDeviceRowBean1);
        switchRoomDeviceRowBeans1.add(switchRoomDeviceRowBean2);

        ArrayList<SwitchRoomDeviceBean> switchRoomDeviceBeans1=new ArrayList<>();
        switchRoomDeviceRowBean1.setSwitchRoomDeviceBeans(switchRoomDeviceBeans1);
        ArrayList<SwitchRoomDeviceBean> switchRoomDeviceBeans2=new ArrayList<>();
        switchRoomDeviceRowBean2.setSwitchRoomDeviceBeans(switchRoomDeviceBeans2);

        SwitchRoomDeviceBean srd1=new SwitchRoomDeviceBean("1","1","40SG2061","电容器",1,1);
        SwitchRoomDeviceBean srd2=new SwitchRoomDeviceBean("2","2","39SG","江18",1,0);
        SwitchRoomDeviceBean srd3=new SwitchRoomDeviceBean("2","2","","",4,0);

        switchRoomDeviceBeans1.add(srd1);
        switchRoomDeviceBeans1.add(srd2);
        switchRoomDeviceBeans1.add(srd1);
        switchRoomDeviceBeans1.add(srd2);
        switchRoomDeviceBeans1.add(srd1);
        switchRoomDeviceBeans1.add(srd2);
        switchRoomDeviceBeans1.add(srd1);
        switchRoomDeviceBeans1.add(srd2);
        switchRoomDeviceBeans1.add(srd1);
        switchRoomDeviceBeans1.add(srd2);
        switchRoomDeviceBeans1.add(srd1);
        switchRoomDeviceBeans1.add(srd2);
        switchRoomDeviceBeans1.add(srd1);
        switchRoomDeviceBeans1.add(srd2);
        switchRoomDeviceBeans1.add(srd1);
        switchRoomDeviceBeans1.add(srd2);
        switchRoomDeviceBeans1.add(srd1);
        switchRoomDeviceBeans1.add(srd2);
        switchRoomDeviceBeans1.add(srd1);
        switchRoomDeviceBeans1.add(srd2);
        switchRoomDeviceBeans1.add(srd1);
        /***************************************************************/
        switchRoomDeviceBeans2.add(srd1);
        switchRoomDeviceBeans2.add(srd2);
        switchRoomDeviceBeans2.add(srd1);
        switchRoomDeviceBeans2.add(srd2);
        switchRoomDeviceBeans2.add(srd1);
        switchRoomDeviceBeans2.add(srd2);
        switchRoomDeviceBeans2.add(srd1);
        switchRoomDeviceBeans2.add(srd2);
        switchRoomDeviceBeans2.add(srd1);
        switchRoomDeviceBeans2.add(srd3);
        switchRoomDeviceBeans2.add(srd3);
        switchRoomDeviceBeans2.add(srd2);
        switchRoomDeviceBeans2.add(srd1);
        switchRoomDeviceBeans2.add(srd2);
        switchRoomDeviceBeans2.add(srd1);
        switchRoomDeviceBeans2.add(srd2);
        switchRoomDeviceBeans2.add(srd1);
        switchRoomDeviceBeans2.add(srd2);
        switchRoomDeviceBeans2.add(srd1);
        switchRoomDeviceBeans2.add(srd2);
        switchRoomDeviceBeans2.add(srd1);









        SwitchRoomBean mSwitchRoomBean2=new SwitchRoomBean();
        switchRooms.add(mSwitchRoomBean2);
        mSwitchRoomBean2.setSwitchRoomID("2");
        mSwitchRoomBean2.setSwitchRoomName("35kV开关室");
        ArrayList<SwitchRoomDeviceRowBean> switchRoomDeviceRowBeans2=new ArrayList<>();
        mSwitchRoomBean2.setSwitchRoomDeviceRowBeans(switchRoomDeviceRowBeans2);

        SwitchRoomDeviceRowBean switchRoomDeviceRowBean3=new SwitchRoomDeviceRowBean();
        switchRoomDeviceRowBeans2.add(switchRoomDeviceRowBean3);

        ArrayList<SwitchRoomDeviceBean> switchRoomDeviceBeans3=new ArrayList<>();
        switchRoomDeviceRowBean3.setSwitchRoomDeviceBeans(switchRoomDeviceBeans3);

        switchRoomDeviceBeans3.add(srd1);
        switchRoomDeviceBeans3.add(srd2);
        switchRoomDeviceBeans3.add(srd1);
        switchRoomDeviceBeans3.add(srd2);
        switchRoomDeviceBeans3.add(srd1);
        switchRoomDeviceBeans3.add(srd2);
        switchRoomDeviceBeans3.add(srd1);
        switchRoomDeviceBeans3.add(srd2);
        switchRoomDeviceBeans3.add(srd1);
        switchRoomDeviceBeans3.add(srd2);
        switchRoomDeviceBeans3.add(srd1);
        switchRoomDeviceBeans3.add(srd2);
        switchRoomDeviceBeans3.add(srd1);
        switchRoomDeviceBeans3.add(srd2);
        switchRoomDeviceBeans3.add(srd1);
        switchRoomDeviceBeans3.add(srd2);
        switchRoomDeviceBeans3.add(srd3);
        switchRoomDeviceBeans3.add(srd3);
        switchRoomDeviceBeans3.add(srd1);
        switchRoomDeviceBeans3.add(srd1);
        switchRoomDeviceBeans3.add(srd2);



        fieldWorkProcedureRequirementBeans1.add(f4);

        FieldWorkProcedureBean fp1=new FieldWorkProcedureBean("1","地电波测试",".检测前应检查开关柜设备上无其他作业，开关柜金属外壳应清洁并可靠接地。",fieldWorkProcedureRequirementBeans1);
        fieldWorkProcedureBeans.add(fp1);

        List<FieldWorkProcedureRequirementBean> fieldWorkProcedureRequirementBeans2=new ArrayList<>();
        FieldWorkProcedureRequirementBean f5=new FieldWorkProcedureRequirementBean();
        f5.type=0;
        f5.title="全部测试完成后及时填写PMS系统中的测温记录";
        f5.verify=0;
        fieldWorkProcedureRequirementBeans2.add(f5);
        FieldWorkProcedureRequirementBean f6=new FieldWorkProcedureRequirementBean();
        f6.type=0;
        f6.title="完成局放检测报告";
        f6.verify=0;
        fieldWorkProcedureRequirementBeans2.add(f6);

        FieldWorkProcedureBean fp2=new FieldWorkProcedureBean("2","数据记录处理",".无",fieldWorkProcedureRequirementBeans2);
        fieldWorkProcedureBeans.add(fp2);
    }

}
