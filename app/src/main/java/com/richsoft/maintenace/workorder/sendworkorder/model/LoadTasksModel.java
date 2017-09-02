package com.richsoft.maintenace.workorder.sendworkorder.model;

import ren.solid.library.net.BaseSingleListener;

/**
 * Created by Administrator on 2016/11/26.
 */

public interface LoadTasksModel {
    void loadTasks(BaseSingleListener listener);
}
