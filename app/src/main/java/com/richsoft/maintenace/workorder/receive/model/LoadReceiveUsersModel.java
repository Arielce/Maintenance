package com.richsoft.maintenace.workorder.receive.model;

import ren.solid.library.net.BaseSingleListener;

/**
 * Created by Administrator on 2016/11/26.
 */

public interface LoadReceiveUsersModel {
    void loadReceiveUsers(String wid, BaseSingleListener listener);
}
