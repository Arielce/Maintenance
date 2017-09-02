package ren.solid.library.fragment.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import ren.solid.library.utils.LogUtils;

/**
 * Created by zhoul
 * Date:2016/12/18
 * Time:1:07
 * Desc:懒加载Fragment
 */

public abstract class LazyLoadFragment extends BaseFragment {

    protected boolean isViewCreated = false;
    protected boolean isFirstLoad = true;
    protected boolean isNeedInitView = false;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewCreated = true;
        if (isNeedInitView) {
            lazyLoad();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        LogUtils.i("zhoul","setUserVisibleHint()执行");
        if (isVisibleToUser && isFirstLoad) {
            LogUtils.i("zhoul","isVisibleToUser为真&&isFirstLoad为真");
            if (isViewCreated) {
                isFirstLoad = false;
                lazyLoad();
            } else {
                isNeedInitView = true;
            }
        }
    }

    protected abstract void lazyLoad();
}
