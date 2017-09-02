package com.richsoft.maintenace.bean.workorder;

import com.richsoft.maintenace.bean.user.UserBean;
import com.richsoft.maintenace.workorder.annotation.SendWorkOrder;
import com.richsoft.maintenace.workorder.annotation.SubmitPrepare;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：e430 on 2017/2/27 09:29
 * <p>
 * 邮箱：chengzehao@163.com
 */

public class WorkOrderBean implements Serializable{
    @SubmitPrepare
    private String orderNo; //工单编号，唯一标识
    @SendWorkOrder
    private String unitId; //所属单位Id
    private String unitName; //单位名称
    @SendWorkOrder
    private String depId; //所属部门id
    private String depName; //所属部门名称
    @SendWorkOrder
    private UserBean monitor;//班长
    @SendWorkOrder
    private UserBean ruler;//当值负责
    @SendWorkOrder
    private List<UserBean> members;//工作成员
    @SendWorkOrder
    private String orderDate; // 工单日期
    private String startWorkTime; //开始工作时间
    private String endWorkTime;//结束工作时间
    private String workState; // 工作执行状态。1-待办、2-执行、3-准备工作已完成、4-开始工作已完成,5-总结工作已完成,6-工作已确认
    private String receiveTime;//如果workState大于等于2，说明当值负责已经接收工单了，将接收的时间返回
    private String prepareFinishTime;//如果workState大于等于3，将准备工作完成的时间返回
    private String fieldFinishTime;//如果workState大于等于4，将现场工作完成的时间返回
    private String summaryFinishTime;//如果workState大于等于5，将总结工作完成的时间返回
    private String confirmFinishTime;//如果workState等于6，将确认工作完成的时间返回
    private String excelUrl;//如果工作已确认，对应导出的作业指导卡网络路径
    @SendWorkOrder
    private WorkTaskBean task;//任务
    @SendWorkOrder
    private SubstationBean substation;//变电站

    /*工单详细执行模块，当获取工单列表时，以下数据无需给。*/
    @SubmitPrepare
    private PrepareBean prepareBean;
    private FieldWorkBean fieldWorkBean;
    private SummaryBean summaryBean;//总结工序

    public WorkOrderBean() {
    }

    public WorkOrderBean(String orderNo, String unitId, String unitName, String depId, String depName, UserBean monitor, UserBean ruler, List<UserBean> members, String orderDate, String startWorkTime, String endWorkTime,String workState, String receiveTime, String prepareFinishTime, String fieldFinishTime, String summaryFinishTime, String confirmFinishTime, String excelUrl, WorkTaskBean task, SubstationBean substation) {
        this.orderNo = orderNo;
        this.unitId = unitId;
        this.unitName = unitName;
        this.depId = depId;
        this.depName = depName;
        this.monitor = monitor;
        this.ruler = ruler;
        this.members = members;
        this.orderDate = orderDate;
        this.startWorkTime = startWorkTime;
        this.endWorkTime = endWorkTime;
        this.workState = workState;
        this.receiveTime = receiveTime;
        this.prepareFinishTime = prepareFinishTime;
        this.fieldFinishTime = fieldFinishTime;
        this.summaryFinishTime = summaryFinishTime;
        this.confirmFinishTime = confirmFinishTime;
        this.excelUrl = excelUrl;
        this.task = task;
        this.substation = substation;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public UserBean getMonitor() {
        return monitor;
    }

    public void setMonitor(UserBean monitor) {
        this.monitor = monitor;
    }

    public UserBean getRuler() {
        return ruler;
    }

    public void setRuler(UserBean ruler) {
        this.ruler = ruler;
    }

    public List<UserBean> getMembers() {
        return members;
    }

    public void setMembers(List<UserBean> members) {
        this.members = members;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getStartWorkTime() {
        return startWorkTime;
    }

    public void setStartWorkTime(String startWorkTime) {
        this.startWorkTime = startWorkTime;
    }

    public String getEndWorkTime() {
        return endWorkTime;
    }

    public void setEndWorkTime(String endWorkTime) {
        this.endWorkTime = endWorkTime;
    }

    public String getWorkState() {
        return workState;
    }

    public void setWorkState(String workState) {
        this.workState = workState;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getPrepareFinishTime() {
        return prepareFinishTime;
    }

    public void setPrepareFinishTime(String prepareFinishTime) {
        this.prepareFinishTime = prepareFinishTime;
    }

    public String getFieldFinishTime() {
        return fieldFinishTime;
    }

    public void setFieldFinishTime(String fieldFinishTime) {
        this.fieldFinishTime = fieldFinishTime;
    }

    public String getSummaryFinishTime() {
        return summaryFinishTime;
    }

    public void setSummaryFinishTime(String summaryFinishTime) {
        this.summaryFinishTime = summaryFinishTime;
    }

    public String getConfirmFinishTime() {
        return confirmFinishTime;
    }

    public void setConfirmFinishTime(String confirmFinishTime) {
        this.confirmFinishTime = confirmFinishTime;
    }

    public String getExcelUrl() {
        return excelUrl;
    }

    public void setExcelUrl(String excelUrl) {
        this.excelUrl = excelUrl;
    }

    public WorkTaskBean getTask() {
        return task;
    }

    public void setTask(WorkTaskBean task) {
        this.task = task;
    }

    public SubstationBean getSubstation() {
        return substation;
    }

    public void setSubstation(SubstationBean substation) {
        this.substation = substation;
    }

    public PrepareBean getPrepareBean() {
        return prepareBean;
    }

    public void setPrepareBean(PrepareBean prepareBean) {
        this.prepareBean = prepareBean;
    }

    public FieldWorkBean getFieldWorkBean() {
        return fieldWorkBean;
    }

    public void setFieldWorkBean(FieldWorkBean fieldWorkBean) {
        this.fieldWorkBean = fieldWorkBean;
    }

    public SummaryBean getSummaryBean() {
        return summaryBean;
    }

    public void setSummaryBean(SummaryBean summaryBean) {
        this.summaryBean = summaryBean;
    }
}
