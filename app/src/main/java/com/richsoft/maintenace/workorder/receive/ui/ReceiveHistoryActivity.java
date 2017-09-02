package com.richsoft.maintenace.workorder.receive.ui;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import com.richsoft.maintenace.bean.workorder.WorkOrderBean;
import ren.solid.library.activity.ToolbarActivity;
import ren.solid.library.manager.EventCenter;
import ren.solid.library.net.NetUtils;

public class ReceiveHistoryActivity extends ToolbarActivity {

    private WorkOrderBean mWorkOrderBean;

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

    @Override
    protected Fragment setFragment() {
        return ReceiveHistoryFragment.newInstance(mWorkOrderBean);
    }

    @Override
    protected String getToolbarTitle() {
        return "工单接受历史";
    }
}
