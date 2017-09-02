package com.richsoft.maintenace.splash.ui;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.richsoft.maintenace.R;
import com.richsoft.maintenace.home.ui.HomeActivity;
import com.richsoft.maintenace.login.ui.LoginActivity;
import com.richsoft.maintenace.splash.presenter.SplashPresenter;
import com.richsoft.maintenace.splash.presenter.SplashPresenterImpl;
import com.richsoft.maintenace.splash.view.SplashView;
import butterknife.Bind;
import ren.solid.library.activity.base.BaseActivity;
import ren.solid.library.manager.EventCenter;
import ren.solid.library.net.NetUtils;
import ren.solid.library.utils.SPUtil;
import ren.solid.library.utils.StringUtils;


public class SplashActivity extends BaseActivity implements SplashView {
    @Bind(R.id.ll_logo_group)
    LinearLayout ll_logo_group;

    @Bind(R.id.splash_version_name)
    TextView mVersionName;

    @Bind(R.id.splash_copyright)
    TextView mCopyright;

    private SplashPresenter mSplashPresenter = null;

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {

    }

    @Override
    protected void initViewsAndEvents() {
        mSplashPresenter = new SplashPresenterImpl(this, this);
        mSplashPresenter.initialized();
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }


    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    protected boolean toggleOverridePendingTransition() {
        return false;
    }

    @Override
    protected TransitionMode getOverridePendingTransitionMode() {
        return null;
    }

    @Override
    public void animateBackgroundImage(Animation animation) {
        ll_logo_group.startAnimation(animation);
    }

    @Override
    public void initializeViews(String versionName, String copyright) {
        mCopyright.setText(copyright);
        mVersionName.setText(versionName);
    }


    @Override
    public void navigateToHomePage() {
        //首先判断用户是否登录过。或者没有被注销过
        if (!StringUtils.isNullOrEmpty(SPUtil.getInstance().getUserName()))
        {
            //进入主界面
            readyGoThenKill(HomeActivity.class);
        }
        else
        {
            //用户未登陆过，进入登录界面。
            readyGoThenKill(LoginActivity.class);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
