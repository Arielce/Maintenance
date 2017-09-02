package com.richsoft.maintenace.workorder.workorderdetail.view;


/**
 * 作者：周麟
 * 邮箱：chengzehao@163.com
 */
public interface SubmitReceiveView {

    void onSubmitReceiveSuccess(String data);

    void onSubmitReceiveFail(Exception e);
}
