package com.richsoft.maintenace.bean.workorder;

/**
 * 作者：e430 on 2017/2/27 11:25
 * <p>
 * 邮箱：chengzehao@163.com
 */
public class SwitchRoomDeviceDataRecordBean {
    private String position;//设备位置 如前上、前下等
    private String data;//该位置记录的数据

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
