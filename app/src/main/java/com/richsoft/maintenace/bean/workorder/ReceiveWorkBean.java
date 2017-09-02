package com.richsoft.maintenace.bean.workorder;

import com.richsoft.maintenace.bean.user.UserBean;

import java.io.Serializable;

/**
 * 作者：e430 on 2017/3/19 12:38
 * <p>
 * 邮箱：chengzehao@163.com
 */

public class ReceiveWorkBean implements Serializable {
    private UserBean user;
    private String receiveTime;

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public ReceiveWorkBean(UserBean user, String receiveTime) {

        this.user = user;
        this.receiveTime = receiveTime;
    }

    public ReceiveWorkBean() {

    }
}
