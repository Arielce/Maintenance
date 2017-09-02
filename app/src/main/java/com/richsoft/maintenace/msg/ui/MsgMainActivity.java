package com.richsoft.maintenace.msg.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.Menu;

import com.richsoft.maintenace.R;

import ren.solid.library.activity.ToolbarActivity;
import ren.solid.library.manager.EventCenter;
import ren.solid.library.net.NetUtils;

/**
 * Created by Administrator on 2017/1/4.
 */

public class MsgMainActivity extends ToolbarActivity {

    @Override
    protected Fragment setFragment() {
        return new MsgMainFragment();
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.msg);
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
        getMenuInflater().inflate(R.menu.menu_msg, menu);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setQueryHint("请输入搜索关键词");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SearchMsgActivity.openActivity(MsgMainActivity.this, query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }
}
