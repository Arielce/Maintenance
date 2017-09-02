package com.richsoft.maintenace.workorder.prepare.model;

import ren.solid.library.net.BaseSingleListener;

/**
 * Created by Administrator on 2016/11/26.
 */

public interface LoadPrepareProceduresHistoryModel {
    void loadPrepareProceduresHistory(String orderNo, BaseSingleListener listener);
}
