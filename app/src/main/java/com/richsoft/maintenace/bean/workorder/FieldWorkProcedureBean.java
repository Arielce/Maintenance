package com.richsoft.maintenace.bean.workorder;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：e430 on 2017/2/23 21:10
 * <p>
 * 邮箱：chengzehao@163.com
 */

public class FieldWorkProcedureBean implements Serializable {
    public String id;
    public String procedureName;//工序名称
    public String procedureRiskControl;//风险辨识与预控措施
    public List<FieldWorkProcedureRequirementBean> fieldWorkProcedureRequirementBeans;//准备工序质量标准及要求集合

    public FieldWorkProcedureBean() {
    }

    public FieldWorkProcedureBean(String id, String procedureName, String procedureRiskControl, List<FieldWorkProcedureRequirementBean> fieldWorkProcedureRequirementBeans) {
        this.id = id;
        this.procedureName = procedureName;
        this.procedureRiskControl = procedureRiskControl;
        this.fieldWorkProcedureRequirementBeans = fieldWorkProcedureRequirementBeans;
    }
}
