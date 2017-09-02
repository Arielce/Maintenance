package com.richsoft.maintenace.bean.workorder;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：e430 on 2017/3/7 11:04
 * <p>
 * 邮箱：chengzehao@163.com
 */

public class WorkTaskListBean implements Serializable {
    private String workType;//工作任务类型0-表示【维护类】带电检测 1-【维护类】检查维护 2-【巡视操作类】
    private String workTypeName;
    private List<WorkTaskBean> workTaskBeans;

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getWorkTypeName() {
        return workTypeName;
    }

    public void setWorkTypeName(String workTypeName) {
        this.workTypeName = workTypeName;
    }

    public List<WorkTaskBean> getWorkTaskBeans() {
        return workTaskBeans;
    }

    public void setWorkTaskBeans(List<WorkTaskBean> workTaskBeans) {
        this.workTaskBeans = workTaskBeans;
    }
}
