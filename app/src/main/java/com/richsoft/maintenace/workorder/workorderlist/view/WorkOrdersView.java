package com.richsoft.maintenace.workorder.workorderlist.view;

import com.richsoft.maintenace.bean.workorder.WorkOrderBean;
import java.util.List;

/**
 * 作者：周麟
 * 邮箱：chengzehao@163.com
 */
public interface WorkOrdersView {

    void onWorkOrdersSuccess(int pageIndex, List<WorkOrderBean> data);

    void onWorkOrdersFail(Exception e);
}
