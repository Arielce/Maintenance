package ren.solid.library.activity;

import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.animation.DecelerateInterpolator;
import ren.solid.library.R;
import ren.solid.library.activity.base.BaseActivity;

/**
 * Created by zhoul
 * Date:2016/4/22
 * Time:13:30
 * <p/>
 * ToolbarActivity
 */
public abstract class ToolbarActivity extends BaseActivity {
    public AppBarLayout mAppBarLayout;
    public Toolbar mToolbar;

    protected FragmentManager mFragmentManager;
    protected boolean mIsHidden = false;

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
        mFragmentManager = getSupportFragmentManager();
        mAppBarLayout= (AppBarLayout) findViewById(R.id.appbar_layout);
        mToolbar= (Toolbar) findViewById(R.id.toolbar);
        setUpToolBar();
        mFragmentManager.beginTransaction().replace(R.id.fl_content, setFragment()).commit();
    }

    protected abstract Fragment setFragment();

    private void setUpToolBar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getToolbarTitle());
    }

    protected abstract String getToolbarTitle();


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_content;
    }

    protected void setAppBarAlpha(float alpha) {
        mAppBarLayout.setAlpha(alpha);
    }

    public void hideOrShowToolbar() {
        mAppBarLayout.animate()
                .translationY(mIsHidden ? 0 : -mAppBarLayout.getHeight())
                .setInterpolator(new DecelerateInterpolator(2))
                .start();

        mIsHidden = !mIsHidden;
    }

    protected void setAppbarVisibility(int visibility) {
        mAppBarLayout.setVisibility(visibility);
    }
}
