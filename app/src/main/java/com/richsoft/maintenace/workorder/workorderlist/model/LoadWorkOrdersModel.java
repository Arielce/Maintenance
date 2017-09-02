package com.richsoft.maintenace.workorder.workorderlist.model;

import ren.solid.library.net.BaseSingleListener;

/**
 * Created by Administrator on 2016/11/26.
 */

public interface LoadWorkOrdersModel {
    void loadWorkOrders(int pageIndex, int type, BaseSingleListener listener);
}
