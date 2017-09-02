package com.richsoft.maintenace.workorder.sendworkorder.view;

import com.richsoft.maintenace.bean.user.UserBean;

import java.util.List;

/**
 * 作者：周麟
 * 邮箱：chengzehao@163.com
 */
public interface LoadUsersView {

    void onLoadUsersSuccess(List<UserBean> data);

    void onLoadUsersFail(Exception e);
}
