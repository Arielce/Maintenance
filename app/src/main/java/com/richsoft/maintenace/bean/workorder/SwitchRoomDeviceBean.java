package com.richsoft.maintenace.bean.workorder;

import java.io.Serializable;

public class SwitchRoomDeviceBean implements Serializable {
    private String switchRoomID; // 开关室ID
    private String switchRoomDeviceID;// 开关室设备ID
    private String switchRoomDeviceNo;// 开关室设备编号
    private String switchRoomDeviceName;// 开关室设备名称
    private int switchRoomDeviceState;// 开关室设备状态，0-关 1-开，2-临时关闭 3-数据采集完毕 4-过道
    private int sideCabinet;//是否是边柜，0-不是边柜 1-边柜
    //地电波数据记录
    private String sideUp;
    private String sideMiddle;
    private String sideDown;
    private String frontUp;
    private String frontMiddle;
    private String frontDown;
    private String backUp;
    private String backMiddle;
    private String backDown;

    public SwitchRoomDeviceBean() {
    }

    public SwitchRoomDeviceBean(String switchRoomID, String switchRoomDeviceID, String switchRoomDeviceNo, String switchRoomDeviceName, int switchRoomDeviceState, int sideCabinet) {
        this.switchRoomID = switchRoomID;
        this.switchRoomDeviceID = switchRoomDeviceID;
        this.switchRoomDeviceNo = switchRoomDeviceNo;
        this.switchRoomDeviceName = switchRoomDeviceName;
        this.switchRoomDeviceState = switchRoomDeviceState;
        this.sideCabinet = sideCabinet;
    }

    public String getSwitchRoomID() {
        return switchRoomID;
    }

    public void setSwitchRoomID(String switchRoomID) {
        this.switchRoomID = switchRoomID;
    }

    public String getSwitchRoomDeviceID() {
        return switchRoomDeviceID;
    }

    public void setSwitchRoomDeviceID(String switchRoomDeviceID) {
        this.switchRoomDeviceID = switchRoomDeviceID;
    }

    public String getSwitchRoomDeviceNo() {
        return switchRoomDeviceNo;
    }

    public void setSwitchRoomDeviceNo(String switchRoomDeviceNo) {
        this.switchRoomDeviceNo = switchRoomDeviceNo;
    }

    public String getSwitchRoomDeviceName() {
        return switchRoomDeviceName;
    }

    public void setSwitchRoomDeviceName(String switchRoomDeviceName) {
        this.switchRoomDeviceName = switchRoomDeviceName;
    }

    public int getSwitchRoomDeviceState() {
        return switchRoomDeviceState;
    }

    public void setSwitchRoomDeviceState(int switchRoomDeviceState) {
        this.switchRoomDeviceState = switchRoomDeviceState;
    }

    public int getSideCabinet() {
        return sideCabinet;
    }

    public void setSideCabinet(int sideCabinet) {
        this.sideCabinet = sideCabinet;
    }

    public String getSideUp() {
        return sideUp;
    }

    public void setSideUp(String sideUp) {
        this.sideUp = sideUp;
    }

    public String getSideMiddle() {
        return sideMiddle;
    }

    public void setSideMiddle(String sideMiddle) {
        this.sideMiddle = sideMiddle;
    }

    public String getSideDown() {
        return sideDown;
    }

    public void setSideDown(String sideDown) {
        this.sideDown = sideDown;
    }

    public String getFrontUp() {
        return frontUp;
    }

    public void setFrontUp(String frontUp) {
        this.frontUp = frontUp;
    }

    public String getFrontMiddle() {
        return frontMiddle;
    }

    public void setFrontMiddle(String frontMiddle) {
        this.frontMiddle = frontMiddle;
    }

    public String getFrontDown() {
        return frontDown;
    }

    public void setFrontDown(String frontDown) {
        this.frontDown = frontDown;
    }

    public String getBackUp() {
        return backUp;
    }

    public void setBackUp(String backUp) {
        this.backUp = backUp;
    }

    public String getBackMiddle() {
        return backMiddle;
    }

    public void setBackMiddle(String backMiddle) {
        this.backMiddle = backMiddle;
    }

    public String getBackDown() {
        return backDown;
    }

    public void setBackDown(String backDown) {
        this.backDown = backDown;
    }
}
