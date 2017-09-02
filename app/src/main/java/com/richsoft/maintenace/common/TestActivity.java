package com.richsoft.maintenace.common;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.richsoft.maintenace.R;
import com.richsoft.maintenace.bean.workorder.SwitchRoomBean;
import com.richsoft.maintenace.bean.workorder.SwitchRoomDeviceBean;
import com.richsoft.maintenace.bean.workorder.SwitchRoomDeviceRowBean;

import java.util.ArrayList;

import ren.solid.library.utils.LogUtils;

public class TestActivity extends AppCompatActivity {
    SwitchRoom switch_room_view;
    SwitchRoomBean mSwitchRoomBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        switch_room_view = (SwitchRoom) findViewById(R.id.switch_room_view);

        switch_room_view.setSwitchCabinetChecker(new SwitchRoom.SwitchCabinetChecker() {
            @Override
            public void onSwitchCabinetClick(int row, int column) {
                LogUtils.i("zhoul","第"+row+"排，第"+column+"列被点击了");
                SwitchRoomDeviceBean s=mSwitchRoomBean.getSwitchRoomDeviceRowBeans().get(row).getSwitchRoomDeviceBeans().get(column);
                switch (s.getSwitchRoomDeviceState()){
                    case 0:
                        LogUtils.i("zhoul","这个开关柜停运呢");
                        s.setSwitchRoomDeviceState(1);
                        switch_room_view.setData(mSwitchRoomBean);
                        break;
                    case 1:
                        LogUtils.i("zhoul","这个开关柜开启呢");
                        break;
                    case 2:
                        LogUtils.i("zhoul","这个开关柜临时关闭呢");
                        break;
                    case 3:
                        LogUtils.i("zhoul","这个开关柜采集完了");
                        break;
                    case 4:
                        LogUtils.i("zhoul","这是个过道");
                        break;
                }
            }
        });
        createBean();
        switch_room_view.setData(mSwitchRoomBean);
    }

    private void createBean() {
        mSwitchRoomBean=new SwitchRoomBean();
        mSwitchRoomBean.setSwitchRoomID("1");
        mSwitchRoomBean.setSwitchRoomName("10kV开关室");

        ArrayList<SwitchRoomDeviceRowBean> switchRoomDeviceRowBeans=new ArrayList<>();
        mSwitchRoomBean.setSwitchRoomDeviceRowBeans(switchRoomDeviceRowBeans);

        SwitchRoomDeviceRowBean switchRoomDeviceRowBean1=new SwitchRoomDeviceRowBean();
        SwitchRoomDeviceRowBean switchRoomDeviceRowBean2=new SwitchRoomDeviceRowBean();
        switchRoomDeviceRowBeans.add(switchRoomDeviceRowBean1);
        switchRoomDeviceRowBeans.add(switchRoomDeviceRowBean2);

        ArrayList<SwitchRoomDeviceBean> switchRoomDeviceBeans1=new ArrayList<>();
        switchRoomDeviceRowBean1.setSwitchRoomDeviceBeans(switchRoomDeviceBeans1);
        ArrayList<SwitchRoomDeviceBean> switchRoomDeviceBeans2=new ArrayList<>();
        switchRoomDeviceRowBean2.setSwitchRoomDeviceBeans(switchRoomDeviceBeans2);

        SwitchRoomDeviceBean srd1=new SwitchRoomDeviceBean("1","1","40SG2061","电容器",0,1);
        SwitchRoomDeviceBean srd2=new SwitchRoomDeviceBean("2","2","39SG","江18",1,0);
        SwitchRoomDeviceBean srd3=new SwitchRoomDeviceBean("3","3","38SG2441-41","隔离开关",0,0);
        SwitchRoomDeviceBean srd4=new SwitchRoomDeviceBean("4","4","37SG2441","母联",0,0);
        SwitchRoomDeviceBean srd5=new SwitchRoomDeviceBean("3","3","38SG2441-41","隔离开关",0,0);
        SwitchRoomDeviceBean srd6=new SwitchRoomDeviceBean("4","4","37SG2441","母联",1,0);
        SwitchRoomDeviceBean srd7=new SwitchRoomDeviceBean("3","3","38SG2441-41","隔离开关",0,0);
        SwitchRoomDeviceBean srd8=new SwitchRoomDeviceBean("4","4","37SG2441","母联",0,0);
        SwitchRoomDeviceBean srd9=new SwitchRoomDeviceBean("3","3","38SG2441-41","隔离开关",0,0);
        SwitchRoomDeviceBean srd10=new SwitchRoomDeviceBean("4","4","37SG2441","母联",0,0);
        SwitchRoomDeviceBean srd11=new SwitchRoomDeviceBean("3","3","38SG2441-41","隔离开关",0,0);
        SwitchRoomDeviceBean srd12=new SwitchRoomDeviceBean("4","4","37SG2441","母联",1,0);
        SwitchRoomDeviceBean srd13=new SwitchRoomDeviceBean("3","3","38SG2441-41","隔离开关",0,0);
        SwitchRoomDeviceBean srd14=new SwitchRoomDeviceBean("4","4","37SG2441","母联",0,0);
        SwitchRoomDeviceBean srd15=new SwitchRoomDeviceBean("3","3","38SG2441-41","隔离开关",0,0);
        SwitchRoomDeviceBean srd16=new SwitchRoomDeviceBean("4","4","37SG2441","母联",1,0);
        SwitchRoomDeviceBean srd17=new SwitchRoomDeviceBean("3","3","38SG2441-41","隔离开关",0,0);
        SwitchRoomDeviceBean srd18=new SwitchRoomDeviceBean("4","4","37SG2441","母联",0,0);
        SwitchRoomDeviceBean srd19=new SwitchRoomDeviceBean("4","4","37SG2441","母联",1,0);
        SwitchRoomDeviceBean srd20=new SwitchRoomDeviceBean("4","4","37SG2441","母联",0,0);
        SwitchRoomDeviceBean srd21=new SwitchRoomDeviceBean("4","4","37SG2441","母联",0,1);
        switchRoomDeviceBeans1.add(srd1);
        switchRoomDeviceBeans1.add(srd2);
        switchRoomDeviceBeans1.add(srd3);
        switchRoomDeviceBeans1.add(srd4);
        switchRoomDeviceBeans1.add(srd5);
        switchRoomDeviceBeans1.add(srd6);
        switchRoomDeviceBeans1.add(srd7);
        switchRoomDeviceBeans1.add(srd8);
        switchRoomDeviceBeans1.add(srd9);
        switchRoomDeviceBeans1.add(srd10);
        switchRoomDeviceBeans1.add(srd11);
        switchRoomDeviceBeans1.add(srd12);
        switchRoomDeviceBeans1.add(srd13);
        switchRoomDeviceBeans1.add(srd14);
        switchRoomDeviceBeans1.add(srd15);
        switchRoomDeviceBeans1.add(srd16);
        switchRoomDeviceBeans1.add(srd17);
        switchRoomDeviceBeans1.add(srd18);
        switchRoomDeviceBeans1.add(srd19);
        switchRoomDeviceBeans1.add(srd20);
        switchRoomDeviceBeans1.add(srd21);

        SwitchRoomDeviceBean qsrd1=new SwitchRoomDeviceBean("1","1","40SG2061","电容器",0,1);
        SwitchRoomDeviceBean qsrd2=new SwitchRoomDeviceBean("2","2","39SG","江18",0,0);
        SwitchRoomDeviceBean qsrd3=new SwitchRoomDeviceBean("3","3","38SG2441-41","隔离开关",0,0);
        SwitchRoomDeviceBean qsrd4=new SwitchRoomDeviceBean("4","4","37SG2441","母联",0,0);
        SwitchRoomDeviceBean qsrd5=new SwitchRoomDeviceBean("3","3","38SG2441-41","隔离开关",0,0);
        SwitchRoomDeviceBean qsrd6=new SwitchRoomDeviceBean("4","4","37SG2441","母联",0,0);
        SwitchRoomDeviceBean qsrd7=new SwitchRoomDeviceBean("3","3","38SG2441-41","隔离开关",0,0);
        SwitchRoomDeviceBean qsrd8=new SwitchRoomDeviceBean("4","4","37SG2441","母联",1,0);
        SwitchRoomDeviceBean qsrd9=new SwitchRoomDeviceBean("3","3","38SG2441-41","隔离开关",0,1);
        SwitchRoomDeviceBean qsrd10=new SwitchRoomDeviceBean("4","4","37SG2441","母联",4,0);
        SwitchRoomDeviceBean qsrd11=new SwitchRoomDeviceBean("3","3","38SG2441-41","隔离开关",4,0);
        SwitchRoomDeviceBean qsrd12=new SwitchRoomDeviceBean("4","4","37SG2441","母联",0,1);
        SwitchRoomDeviceBean qsrd13=new SwitchRoomDeviceBean("3","3","38SG2441-41","隔离开关",0,0);
        SwitchRoomDeviceBean qsrd14=new SwitchRoomDeviceBean("4","4","37SG2441","母联",1,0);
        SwitchRoomDeviceBean qsrd15=new SwitchRoomDeviceBean("3","3","38SG2441-41","隔离开关",0,0);
        SwitchRoomDeviceBean qsrd16=new SwitchRoomDeviceBean("4","4","37SG2441","母联",0,0);
        SwitchRoomDeviceBean qsrd17=new SwitchRoomDeviceBean("3","3","38SG2441-41","隔离开关",0,0);
        SwitchRoomDeviceBean qsrd18=new SwitchRoomDeviceBean("4","4","37SG2441","母联",0,0);
        SwitchRoomDeviceBean qsrd19=new SwitchRoomDeviceBean("4","4","37SG2441","母联",0,0);
        SwitchRoomDeviceBean qsrd20=new SwitchRoomDeviceBean("4","4","37SG2441","母联",1,0);
        SwitchRoomDeviceBean qsrd21=new SwitchRoomDeviceBean("4","4","37SG2441","母联",0,1);
        switchRoomDeviceBeans2.add(qsrd1);
        switchRoomDeviceBeans2.add(qsrd2);
        switchRoomDeviceBeans2.add(qsrd3);
        switchRoomDeviceBeans2.add(qsrd4);
        switchRoomDeviceBeans2.add(qsrd5);
        switchRoomDeviceBeans2.add(qsrd6);
        switchRoomDeviceBeans2.add(qsrd7);
        switchRoomDeviceBeans2.add(qsrd8);
        switchRoomDeviceBeans2.add(qsrd9);
        switchRoomDeviceBeans2.add(qsrd10);
        switchRoomDeviceBeans2.add(qsrd11);
        switchRoomDeviceBeans2.add(qsrd12);
        switchRoomDeviceBeans2.add(qsrd13);
        switchRoomDeviceBeans2.add(qsrd14);
        switchRoomDeviceBeans2.add(qsrd15);
        switchRoomDeviceBeans2.add(qsrd16);
        switchRoomDeviceBeans2.add(qsrd17);
        switchRoomDeviceBeans2.add(qsrd18);
        switchRoomDeviceBeans2.add(qsrd19);
        switchRoomDeviceBeans2.add(qsrd20);
        switchRoomDeviceBeans2.add(qsrd21);
    }
}
