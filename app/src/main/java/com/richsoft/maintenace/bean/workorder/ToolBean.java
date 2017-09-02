package com.richsoft.maintenace.bean.workorder;

import java.io.Serializable;

public class ToolBean implements Serializable {
    private String toolID; // 工具或仪器ID
    private String toolName; // 工具或仪器名称

    public String getToolID() {
        return toolID;
    }

    public void setToolID(String toolID) {
        this.toolID = toolID;
    }

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }


    @Override
    public String toString() {
        return toolName;
    }
}
