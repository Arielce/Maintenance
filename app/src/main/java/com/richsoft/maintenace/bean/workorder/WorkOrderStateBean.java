package com.richsoft.maintenace.bean.workorder;

/**
 * 作者：e430 on 2017/2/24 09:22
 * <p>
 * 邮箱：chengzehao@163.com
 */

public class WorkOrderStateBean {
    private int state;//1-未进行 2-当前应该执行 3-执行完毕的
    private int stateType;//1-接受 2-准备 3-现场 4-总结 5-确认
    private String stepTitle;
    private String stepDesc;

    public WorkOrderStateBean() {
    }

    public WorkOrderStateBean(int state, int stateType, String stepTitle, String stepDesc) {
        this.state = state;
        this.stateType = stateType;
        this.stepTitle = stepTitle;
        this.stepDesc = stepDesc;
    }

    public String getStepTitle() {
        return stepTitle;
    }

    public void setStepTitle(String stepTitle) {
        this.stepTitle = stepTitle;
    }

    public String getStepDesc() {
        return stepDesc;
    }

    public void setStepDesc(String stepDesc) {
        this.stepDesc = stepDesc;
    }

    public int getStateType() {
        return stateType;
    }

    public void setStateType(int stateType) {
        this.stateType = stateType;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
