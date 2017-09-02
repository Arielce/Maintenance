package com.richsoft.maintenace.home.ui;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.richsoft.maintenace.common.ConstantValue;
import com.richsoft.maintenace.R;
import com.richsoft.maintenace.common.Urls;
import com.richsoft.maintenace.home.presenter.MainPresenter;
import com.richsoft.maintenace.home.presenter.MainPresenterImpl;
import com.richsoft.maintenace.home.view.MainView;
import com.richsoft.maintenace.msg.ui.MsgMainActivity;
import com.richsoft.maintenace.personalhome.ui.PersonalActivity;
import com.richsoft.maintenace.workorder.sendworkorder.ui.SendingWorkActivity;
import com.richsoft.maintenace.workorder.workorderlist.ui.WorkOrdersTabsFragment;
import butterknife.Bind;
import de.hdodenhof.circleimageview.CircleImageView;
import ren.solid.library.activity.base.BaseMainActivity;
import ren.solid.library.imageloader.ImageLoader;
import ren.solid.library.manager.EventCenter;
import ren.solid.library.net.NetUtils;
import ren.solid.library.utils.SPUtil;
import ren.solid.library.utils.UIUtils;

public class HomeActivity extends BaseMainActivity implements MainView {
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.navigation_view)
    NavigationView mNavigationView;

    private CircleImageView profile_image;
    private TextView tv_username;
    private TextView tv_hello;

    private ActionBarDrawerToggle mDrawerToggle;
    private MainPresenter mMainPresenter;

    private FragmentManager mFragmentManager;

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
        //设置标题栏
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.app_name));

        //设置左侧侧拉菜单
        initNavigationView();

        //创建业务对象
        mMainPresenter = new MainPresenterImpl(this, this);
        //业务对象获取相关信息
        mMainPresenter.getUserName();
        mMainPresenter.getHello();
        mMainPresenter.getAvater();
        //创建工单管理片段
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction().replace(R.id.frame_content, new WorkOrdersTabsFragment()).commit();
    }

    /**
     * 初始化左侧侧拉菜单
     */
    private void initNavigationView() {
        View headerView = mNavigationView.getHeaderView(0);
        profile_image = (CircleImageView) headerView.findViewById(R.id.profile_image);
        tv_username = (TextView) headerView.findViewById(R.id.tv_username);
        tv_hello = (TextView) headerView.findViewById(R.id.tv_hello);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open,
                R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        setupDrawerContent(mNavigationView);
    }

    /**
     * 侧滑菜单栏点击事件处理
     * @param navigationView
     */
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(final MenuItem menuItem) {
                        mDrawerLayout.closeDrawers();
                        UIUtils.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                switch (menuItem.getItemId()){
                                    case R.id.navigation_msg_center:
                                        readyGo(MsgMainActivity.class);
                                        break;
                                    case R.id.navigation_personal_center:
                                        readyGo(PersonalActivity.class);
                                        break;
                                    case R.id.navigation_exit:
                                        showDialog(0,getString(R.string.exit_app),getString(R.string.exit_app_des),getString(R.string.continue_exit),true);
                                        break;
                                }
                            }
                        },300);
                        return true;
                    }
                });
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
        return true;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_home;
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {
        int eventCode = eventCenter.getEventCode();
        switch (eventCode) {
            case ConstantValue.EVENT_REFRESH_AVATER:
                mMainPresenter.getAvater();
                break;
        }
    }

    @Override
    public void onPositive(int dialogId) {
        switch (dialogId) {
            case 0:
                mMainPresenter.cancellation();
                break;
        }
    }

    @Override
    public void filledUserName(String username) {
        tv_username.setText(username);
    }

    @Override
    public void filledHello(String hello) {
        tv_hello.setText(hello);
    }

    @Override
    public void fillAvater(String avater) {
        if(avater.equals(Urls.SERVER_URL+"null")){
            Glide.with(mContext).load(R.mipmap.hughjackman).centerCrop().into(profile_image);
        }else{
            ImageLoader.displayImage(profile_image,avater);
        }
    }

    @Override
    protected boolean beforeOnBackPressed() {
        if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
            mDrawerLayout.closeDrawer(Gravity.LEFT);
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(SPUtil.getInstance().getUserRole().equals("1")){
            getMenuInflater().inflate(R.menu.menu_home_option, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.option:
                readyGo(SendingWorkActivity.class);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
