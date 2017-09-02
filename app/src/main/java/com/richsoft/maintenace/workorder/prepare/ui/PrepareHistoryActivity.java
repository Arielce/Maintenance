package com.richsoft.maintenace.workorder.prepare.ui;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import com.richsoft.maintenace.bean.workorder.WorkOrderBean;
import ren.solid.library.activity.ToolbarActivity;
import ren.solid.library.manager.EventCenter;
import ren.solid.library.net.NetUtils;

public class PrepareHistoryActivity extends ToolbarActivity {

    private WorkOrderBean mWorkOrderBean;
    @Override
    protected Fragment setFragment() {
        return PrepareHistoryTabsFragment.newInstance(mWorkOrderBean);
    }

    @Override
    protected String getToolbarTitle() {
        return "准备工作历史";
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        mWorkOrderBean= (WorkOrderBean) extras.getSerializable("work_order");
    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {

    }
}
