package com.richsoft.maintenace.bean.workorder;

import com.richsoft.maintenace.workorder.annotation.SubmitPrepare;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：e430 on 2017/2/23 20:26
 * <p>
 * 邮箱：chengzehao@163.com
 */

public class PrepareProcedureBean implements Serializable {
    @SubmitPrepare
    public String prepareProcedureId;
    public String procedureName;//工序名称
    public String procedureRiskControl;//风险辨识与预控措施
    @SubmitPrepare
    public List<PrepareProcedureRequirementBean> prepareProcedureRequirementBeans;//准备工序质量标准及要求

    public PrepareProcedureBean() {
    }

    public PrepareProcedureBean(String id, String procedureName, String procedureRiskControl, List<PrepareProcedureRequirementBean> prepareProcedureRequirementBeans) {
        this.prepareProcedureId = id;
        this.procedureName = procedureName;
        this.procedureRiskControl = procedureRiskControl;
        this.prepareProcedureRequirementBeans = prepareProcedureRequirementBeans;
    }
}
