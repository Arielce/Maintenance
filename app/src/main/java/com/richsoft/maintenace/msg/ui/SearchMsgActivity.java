package com.richsoft.maintenace.msg.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import ren.solid.library.activity.ToolbarActivity;
import ren.solid.library.manager.EventCenter;
import ren.solid.library.net.NetUtils;

public class SearchMsgActivity extends ToolbarActivity {
    private String mKeyWord;

    public static void openActivity(Context context, String word) {
        Intent intent = new Intent(context, SearchMsgActivity.class);
        intent.putExtra("keyWord", word);
        context.startActivity(intent);
    }

    @Override
    protected void initViewsAndEvents() {
        mKeyWord = getIntent().getExtras().getString("keyWord");
        super.initViewsAndEvents();
    }

    @Override
    protected Fragment setFragment() {
        return SearchResultListFragment.newInstance(mKeyWord);
    }

    @Override
    protected String getToolbarTitle() {
        return mKeyWord + "的搜索结果";
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
}
