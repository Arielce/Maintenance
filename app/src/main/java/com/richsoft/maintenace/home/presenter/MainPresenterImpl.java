package com.richsoft.maintenace.home.presenter;

import android.content.Intent;
import com.richsoft.maintenace.R;
import com.richsoft.maintenace.home.view.MainView;
import com.richsoft.maintenace.login.ui.LoginActivity;
import java.util.Calendar;
import ren.solid.library.activity.base.BaseActivity;
import ren.solid.library.utils.SPUtil;
import ren.solid.library.utils.StringUtils;
import ren.solid.library.utils.UIUtils;

/**
 * Created by Administrator on 2016/12/11.
 */

public class MainPresenterImpl implements MainPresenter{
    MainView mainView;
    BaseActivity mActivity;

    public MainPresenterImpl(MainView mainView, BaseActivity mActivity) {
        this.mainView=mainView;
        this.mActivity=mActivity;
    }

    @Override
    public void getUserName() {
        if(!StringUtils.isNullOrEmpty(SPUtil.getInstance().getUserName())){
            mainView.filledUserName(SPUtil.getInstance().getUserName());
        }
    }

    @Override
    public void getHello() {
        String hello;
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(calendar.HOUR_OF_DAY);
        if (hour >= 6 && hour <= 12) {
            hello= "【"+SPUtil.getInstance().getUserPost()+"】"+UIUtils.getString(R.string.morning_hello);
        } else if (hour > 12 && hour <= 18) {
            hello= "【"+SPUtil.getInstance().getUserPost()+"】"+UIUtils.getString(R.string.afternoon_hello);
        } else {
            hello= "【"+SPUtil.getInstance().getUserPost()+"】"+UIUtils.getString(R.string.night_hello);
        }
        mainView.filledHello(hello);
    }

    @Override
    public void getAvater() {
        if(!StringUtils.isNullOrEmpty(SPUtil.getInstance().getUserAvater())){
            mainView.fillAvater(SPUtil.getInstance().getUserAvater());
        }
    }

    @Override
    public void cancellation() {
        SPUtil.getInstance().saveUid("");
        SPUtil.getInstance().saveUserName("");
        SPUtil.getInstance().saveAppToken("");
        SPUtil.getInstance().saveUserRole("");
        SPUtil.getInstance().saveUserRoleDes("");
        SPUtil.getInstance().saveUserPostId("");
        SPUtil.getInstance().saveUserPost("");
        SPUtil.getInstance().saveCompanyId("");
        SPUtil.getInstance().saveCompany("");
        SPUtil.getInstance().saveUserEmail("");
        SPUtil.getInstance().saveUserDepartmentId("");
        SPUtil.getInstance().saveUserDepartment("");
        SPUtil.getInstance().saveUserAvater("");
        SPUtil.getInstance().saveUserPhone("");
        SPUtil.getInstance().saveUserPhone2("");
        SPUtil.getInstance().saveUserRemark("");
        Intent intent = new Intent(mActivity, LoginActivity.class);
        mActivity.startActivity(intent);
        mActivity.finish();
    }
}
