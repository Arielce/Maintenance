package com.richsoft.maintenace.bean.workorder;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/2/27.
 */
public class FieldWorkBean implements Serializable{

    private List<FieldWorkProcedureBean> fieldWorkProcedureBeans;//现场工序集合


    public List<FieldWorkProcedureBean> getFieldWorkProcedureBeans() {
        return fieldWorkProcedureBeans;
    }

    public void setFieldWorkProcedureBeans(List<FieldWorkProcedureBean> fieldWorkProcedureBeans) {
        this.fieldWorkProcedureBeans = fieldWorkProcedureBeans;
    }
}
