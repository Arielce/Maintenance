package com.richsoft.maintenace.workorder.filed.model;

import ren.solid.library.net.BaseSingleListener;

/**
 * Created by Administrator on 2016/11/26.
 */

public interface LoadFieldWorkProcedureModel {
    void loadFieldWorkProcedure(String orderNo, BaseSingleListener listener);
}
