package com.richsoft.maintenace.bean.workorder;

import java.io.Serializable;


/**
 * 风险措施bean
 */
public class RiskMeasureBean implements Serializable {
    public String riskMeasureID;// 风险措施ID
    public String riskMeasureType;// 风险措施类别
    public String riskMeasureName;// 风险措施内容

    public String getRiskMeasureID() {
        return riskMeasureID;
    }

    public void setRiskMeasureID(String riskMeasureID) {
        this.riskMeasureID = riskMeasureID;
    }

    public String getRiskMeasureType() {
        return riskMeasureType;
    }

    public void setRiskMeasureType(String riskMeasureType) {
        this.riskMeasureType = riskMeasureType;
    }

    public String getRiskMeasureContent() {
        return riskMeasureName;
    }

    public void setRiskMeasureContent(String riskMeasureContent) {
        this.riskMeasureName = riskMeasureContent;
    }

}
