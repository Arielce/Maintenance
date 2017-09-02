package com.richsoft.maintenace.workorder.workorderdetail.ui;

import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import com.richsoft.maintenace.R;
import com.richsoft.maintenace.db.dao.DocDao;
import com.yolanda.nohttp.download.DownloadRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import ren.solid.library.activity.ToolbarActivity;
import ren.solid.library.manager.EventCenter;
import ren.solid.library.net.NetUtils;

public class GuidanceDocActivity extends ToolbarActivity {
    //用于管理正在下载文档的请求
    public Map<String, DownloadRequest> mRequests = new HashMap<String, DownloadRequest>();
    //维护一个数据库操作对象，用于与文档断点记录数据库通信
    public DocDao mDocDao;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected Fragment setFragment() {
        return new GuidanceDocTabsFragment();
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.guidance_doc);
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
    protected void onDestroy() {
        //当界面被销毁，那么将所有正在下载的请求关闭
        Iterator iter = mRequests.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            DownloadRequest val = (DownloadRequest) entry.getValue();
            val.cancel();
        }
        super.onDestroy();
    }
}
