package com.richsoft.maintenace.workorder.receive.view;

import com.richsoft.maintenace.bean.workorder.ReceiveWorkBean;

import java.util.List;

/**
 * 作者：周麟
 * 邮箱：chengzehao@163.com
 */
public interface LoadReceiveUsersView {

    void onLoadReceiveUsersSuccess(List<ReceiveWorkBean> data);

    void onLoadReceiveUsersFail(Exception e);
}
