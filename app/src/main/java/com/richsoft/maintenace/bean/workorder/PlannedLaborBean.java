package com.richsoft.maintenace.bean.workorder;

import com.richsoft.maintenace.bean.user.UserBean;

import java.io.Serializable;
import java.util.List;

public class PlannedLaborBean implements Serializable {
    /*"groupID": "1",
      "groupName": "变电运维一班",
      "key1": "11",
      "key2": "1",
      "members": [{"name": "曹乃韬","uid": "3"}],
      "monitorName": "王海泉",
      "monitorUid": "1",
      "riskMeasures": [],
      "ruleName": "李学明",
      "ruleUid": "4",
      "substationID": "3",
      "substationName": "珠江道",
      "switchRoomTasks": [],
      "unitID": "1",
      "unitName": "城南供电分公司",
      "workID": "140",
      "workName": "例行巡视",
      "workTime": "2017-02-18",
      "workType": "7"*/
    private String groupID; // 所属班组
    private String groupName; // 所属班组名称
    private List<UserBean> members;// 巡检人员姓名
    private String monitorName;// 班长姓名
    private String monitorUid;// 班长uid
    private String ruleUid; // 当值负责人uid
    private String ruleName;// 当值负责人姓名
    private String substationID;// 变电站ID
    private String substationName;// 变电站名称
    private String unitName; // 单位名称
    private String unitID; // 单位名称ID
    private String workName; // 任务名称
    private String workID;// 任务ID
    private String workTime;// 工作时间
    private String workType; // 任务类型

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setMembers(List<UserBean> members) {
        this.members = members;
    }

    public void setMonitorName(String monitorName) {
        this.monitorName = monitorName;
    }

    public void setMonitorUid(String monitorUid) {
        this.monitorUid = monitorUid;
    }

    public void setRuleUid(String ruleUid) {
        this.ruleUid = ruleUid;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public void setSubstationID(String substationID) {
        this.substationID = substationID;
    }

    public void setSubstationName(String substationName) {
        this.substationName = substationName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public void setUnitID(String unitID) {
        this.unitID = unitID;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public void setWorkID(String workID) {
        this.workID = workID;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getGroupID() {

        return groupID;
    }

    public String getGroupName() {
        return groupName;
    }

    public List<UserBean> getMembers() {
        return members;
    }

    public String getMonitorName() {
        return monitorName;
    }

    public String getMonitorUid() {
        return monitorUid;
    }

    public String getRuleUid() {
        return ruleUid;
    }

    public String getRuleName() {
        return ruleName;
    }

    public String getSubstationID() {
        return substationID;
    }

    public String getSubstationName() {
        return substationName;
    }

    public String getUnitName() {
        return unitName;
    }

    public String getUnitID() {
        return unitID;
    }

    public String getWorkName() {
        return workName;
    }

    public String getWorkID() {
        return workID;
    }

    public String getWorkTime() {
        return workTime;
    }

    public String getWorkType() {
        return workType;
    }
}
