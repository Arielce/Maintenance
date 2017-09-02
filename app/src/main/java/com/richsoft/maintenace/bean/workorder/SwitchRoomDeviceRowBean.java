package com.richsoft.maintenace.bean.workorder;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：e430 on 2017/2/27 11:19
 * <p>
 * 邮箱：chengzehao@163.com
 */
public class SwitchRoomDeviceRowBean implements Serializable{
    private List<SwitchRoomDeviceBean> switchRoomDeviceBeans;//该排开关柜所包含的开关柜集合

    public List<SwitchRoomDeviceBean> getSwitchRoomDeviceBeans() {
        return switchRoomDeviceBeans;
    }

    public void setSwitchRoomDeviceBeans(List<SwitchRoomDeviceBean> switchRoomDeviceBeans) {
        this.switchRoomDeviceBeans = switchRoomDeviceBeans;
    }
}
