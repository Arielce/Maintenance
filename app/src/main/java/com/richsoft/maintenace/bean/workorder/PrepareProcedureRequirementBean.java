package com.richsoft.maintenace.bean.workorder;

import com.richsoft.maintenace.workorder.annotation.SubmitPrepare;

import java.io.Serializable;

/**
 * 作者：e430 on 2017/2/23 20:46
 * <p>
 * 邮箱：chengzehao@163.com
 */

public class PrepareProcedureRequirementBean implements Serializable {
    @SubmitPrepare
    public String prepareProcedureRequirementId;
    @SubmitPrepare
    public int type;//0-需指定是或者否 1-需要指定人员 2-温度记录 3-湿度记录 4-指定安全监护
    public String title;//质量标准及要求标题
    @SubmitPrepare
    public String verify;//如果type为0，该字段记录是与否
    @SubmitPrepare
    public String uid;//如果type为1或者4，该字段记录人员ID
    @SubmitPrepare
    public String userName;//如果type为1或者4，该字段记录人员姓名
    @SubmitPrepare
    public String temperature;//如果type为2，该字段记录温度
    @SubmitPrepare
    public String humidity;//如果type为3，该字段记录湿度

    public PrepareProcedureRequirementBean() {
    }

    public PrepareProcedureRequirementBean(Integer type,String prepareProcedureRequirementId, String title) {
        this.type = type;
        this.prepareProcedureRequirementId=prepareProcedureRequirementId;
        this.title = title;
    }

}
