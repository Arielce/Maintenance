package com.richsoft.maintenace.bean.workorder;

import java.io.Serializable;
import java.util.List;

public class SwitchRoomBean implements Serializable {
    private String switchRoomID;// 开关室ID
    private String switchRoomName;// 开关室名称
    private List<SwitchRoomDeviceRowBean> SwitchRoomDeviceRowBeans;//该开关室开关柜排集合

    public String getSwitchRoomID() {
        return switchRoomID;
    }

    public void setSwitchRoomID(String switchRoomID) {
        this.switchRoomID = switchRoomID;
    }

    public String getSwitchRoomName() {
        return switchRoomName;
    }

    public void setSwitchRoomName(String switchRoomName) {
        this.switchRoomName = switchRoomName;
    }

    public List<SwitchRoomDeviceRowBean> getSwitchRoomDeviceRowBeans() {
        return SwitchRoomDeviceRowBeans;
    }

    public void setSwitchRoomDeviceRowBeans(List<SwitchRoomDeviceRowBean> switchRoomDeviceRowBeans) {
        SwitchRoomDeviceRowBeans = switchRoomDeviceRowBeans;
    }

    @Override
    public String toString() {
        return switchRoomName;
    }
}
