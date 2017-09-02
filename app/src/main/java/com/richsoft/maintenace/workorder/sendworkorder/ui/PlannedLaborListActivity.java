package com.richsoft.maintenace.workorder.sendworkorder.ui;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.richsoft.maintenace.R;
import ren.solid.library.activity.ToolbarActivity;
import ren.solid.library.manager.EventCenter;
import ren.solid.library.net.NetUtils;

public class PlannedLaborListActivity extends ToolbarActivity {


    @Override
    protected Fragment setFragment() {
        return new PlannedLaborListFragment();
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.planned_labor_list);
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_send_plan, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.send:

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
