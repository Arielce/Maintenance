package com.richsoft.maintenace.workorder.workorderdetail.model;

import ren.solid.library.net.BaseSingleListener;

/**
 * Created by Administrator on 2016/11/26.
 */

public interface LoadDocsModel {
    void loadDocs(int type,String fileName ,BaseSingleListener listener);
}
