package com.richsoft.maintenace.common;

import com.richsoft.maintenace.bean.msg.GanHuoDataImg;
import com.richsoft.maintenace.bean.msg.GanHuoDataText;
import com.richsoft.maintenace.bean.msg.SearchResult;
import com.richsoft.maintenace.bean.workorder.WorkOrderBean;
import com.richsoft.maintenace.msg.ui.adapter.GanHuoImgViewProvider;
import com.richsoft.maintenace.msg.ui.adapter.GanHuoTextViewProvider;
import com.richsoft.maintenace.msg.ui.adapter.SearchResultViewProvider;
import com.richsoft.maintenace.workorder.workorderlist.ui.adapter.WorkOrderItemProvider;

import me.drakeet.multitype.GlobalMultiTypePool;

public class MultiTypeInstaller {

    static void install() {
        GlobalMultiTypePool.register(GanHuoDataText.class, new GanHuoTextViewProvider());
        GlobalMultiTypePool.register(GanHuoDataImg.class, new GanHuoImgViewProvider());
        GlobalMultiTypePool.register(SearchResult.class, new SearchResultViewProvider());
        GlobalMultiTypePool.register(WorkOrderBean.class, new WorkOrderItemProvider());
    }
}
