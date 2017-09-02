package com.richsoft.maintenace.msg.model;

import ren.solid.library.net.BaseSingleListener;

/**
 * Created by Administrator on 2016/11/26.
 */

public interface LoadMsgsModel {
    void loadMsgs(int pageIndex, String type, BaseSingleListener listener);
}
