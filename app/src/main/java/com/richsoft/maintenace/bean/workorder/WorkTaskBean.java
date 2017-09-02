package com.richsoft.maintenace.bean.workorder;

import com.richsoft.maintenace.workorder.annotation.SendWorkOrder;

import java.io.Serializable;

public class WorkTaskBean implements Serializable {
	/*
	"riskMeasures": [{"riskMeasureID": "14","riskMeasureName": "雷雨天气时不允许作业；系统有接地故障时不允许作业。","riskMeasureType": "作业条件"}],
	"tools": [{"toolID": "5","toolName": "导通测量仪KF-6501A"}],
	"workID": 1,
	"workName": "暂态地电压局部放电检测",
	"workType": "1"
	*/
	@SendWorkOrder
	private String workID;//工作任务ID
	@SendWorkOrder
	private String workType;//工作任务类型0-表示【维护类】带电检测 1-【维护类】检查 2-【巡视操作类】
	private String workName;//工作任务名称
	private boolean chooseTag;//false-未选中 true-选中（本地维护）

	public WorkTaskBean(String workID, String workType, String workName) {
		this.workID = workID;
		this.workType = workType;
		this.workName = workName;
	}

	public WorkTaskBean() {
	}

	public void setWorkID(String workID) {
		this.workID = workID;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	public void setChooseTag(boolean chooseTag) {
		this.chooseTag = chooseTag;
	}

	public String getWorkID() {

		return workID;
	}

	public String getWorkType() {
		return workType;
	}

	public String getWorkName() {
		return workName;
	}

	public boolean isChooseTag() {
		return chooseTag;
	}
}
