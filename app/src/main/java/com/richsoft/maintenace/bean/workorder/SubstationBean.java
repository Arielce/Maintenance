package com.richsoft.maintenace.bean.workorder;

import com.richsoft.maintenace.workorder.annotation.SendWorkOrder;

import java.io.Serializable;

public class SubstationBean implements Serializable {
    @SendWorkOrder
    private String substationID;//变电站ID
    private String substationName;//变电站名
    private String substationLevel;//变电站等级 如110kV
    private boolean chooseTag;//false-未选中 true-选中(本地维护)

    public SubstationBean(String substationID, String substationName, String substationLevel) {
        this.substationID = substationID;
        this.substationName = substationName;
        this.substationLevel = substationLevel;
    }

    public SubstationBean() {
    }

    public void setSubstationID(String substationID) {
        this.substationID = substationID;
    }

    public void setSubstationName(String substationName) {
        this.substationName = substationName;
    }


    public void setSubstationLevel(String substationLevel) {
        this.substationLevel = substationLevel;
    }

    public String getSubstationLevel() {

        return substationLevel;
    }

    public void setChooseTag(boolean chooseTag) {
        this.chooseTag = chooseTag;
    }

    public String getSubstationID() {
        return substationID;
    }

    public String getSubstationName() {
        return substationName;
    }

    public boolean isChooseTag() {
        return chooseTag;
    }

    @Override
    public String toString() {
        return substationName;
    }
}
