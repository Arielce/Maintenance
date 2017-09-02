package com.richsoft.maintenace.bean.workorder;

import com.richsoft.maintenace.workorder.annotation.SubmitPrepare;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/2/27.
 */
public class PrepareBean implements Serializable{
    @SubmitPrepare
    private String prepareId;
    private List<RiskMeasureBean> riskMeasures;// 该任务对应的风险集合
    private List<ToolBean> tools;// 该任务需使用的工具集合
    @SubmitPrepare
    private List<PrepareProcedureBean> prepareProcedureBeans;//准备工序集合


    public String getPrepareId() {
        return prepareId;
    }

    public void setPrepareId(String prepareId) {
        this.prepareId = prepareId;
    }

    public List<PrepareProcedureBean> getPrepareProcedureBeans() {
        return prepareProcedureBeans;
    }

    public void setPrepareProcedureBeans(List<PrepareProcedureBean> prepareProcedureBeans) {
        this.prepareProcedureBeans = prepareProcedureBeans;
    }

    public List<RiskMeasureBean> getRiskMeasures() {
        return riskMeasures;
    }

    public void setRiskMeasures(List<RiskMeasureBean> riskMeasures) {
        this.riskMeasures = riskMeasures;
    }

    public List<ToolBean> getTools() {
        return tools;
    }

    public void setTools(List<ToolBean> tools) {
        this.tools = tools;
    }

    public List<PrepareProcedureBean> getPrepareBeans() {
        return prepareProcedureBeans;
    }

    public void setPrepareBeans(List<PrepareProcedureBean> prepareProcedureBeans) {
        this.prepareProcedureBeans = prepareProcedureBeans;
    }
}
