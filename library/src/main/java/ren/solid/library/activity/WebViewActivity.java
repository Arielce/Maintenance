package ren.solid.library.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import ren.solid.library.R;
import ren.solid.library.activity.base.BaseActivity;
import ren.solid.library.fragment.WebViewFragment;
import ren.solid.library.manager.EventCenter;
import ren.solid.library.net.NetUtils;
import ren.solid.library.utils.ClipboardUtils;
import ren.solid.library.utils.SystemShareUtils;


/**
 * Created by zhoul
 * Date:2016/4/19
 * Time:13:03
 */
public class WebViewActivity extends BaseActivity {

    private static String TAG = "WebViewActivity";
    public static String WEB_URL = "webViewUrl";
    public static String TITLE = "webViewTitle";

    private Toolbar mToolbar;
    private String mUrl;
    private String mTitle;

    private FragmentManager mFragmentManager;
    private WebViewFragment mWebViewFragment;

    public  static void  openActivity(Context context,String title,String url)
    {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(WebViewActivity.WEB_URL, url);
        intent.putExtra(WebViewActivity.TITLE, title);
        context.startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (mWebViewFragment.canGoBack()) {
            mWebViewFragment.goBack();
        } else {
            finish();
        }
    }

    @Override
    protected boolean toggleOverridePendingTransition() {
        return true;
    }

    @Override
    protected TransitionMode getOverridePendingTransitionMode() {
        return TransitionMode.FADE;
    }

    @Override
    protected void initViewsAndEvents() {
        mUrl = getIntent().getExtras().getString(WEB_URL);
        mTitle = getIntent().getExtras().getString(TITLE);
        mFragmentManager = getSupportFragmentManager();
        //设置Toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(mTitle);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);//决定左上角的图标是否可以点击
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//决定左上角图标的右侧是否有向左的小箭头
        //mToolbar.setNavigationIcon(R.drawable.ic_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mWebViewFragment = WebViewFragment.newInstance(mUrl);
        mFragmentManager.beginTransaction().replace(R.id.fl_content, mWebViewFragment).commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_webview_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_copy) {
            ClipboardUtils.setText(this, mUrl);
            Snackbar.make(mToolbar, "已复制到剪切板", Snackbar.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_share) {
            SystemShareUtils.shareText(this, "【" + mTitle + "】链接:" + mUrl);
        }

        return super.onOptionsItemSelected(item);
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
    protected int getContentViewLayoutID() {
        return R.layout.activity_webview;
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {

    }
}
