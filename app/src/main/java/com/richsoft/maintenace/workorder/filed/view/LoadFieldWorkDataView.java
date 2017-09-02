package com.richsoft.maintenace.workorder.filed.view;

import com.richsoft.maintenace.bean.workorder.FieldWorkBean;

/**
 * 作者：周麟
 * 邮箱：chengzehao@163.com
 */
public interface LoadFieldWorkDataView {

    void onLoadFieldWorkDataSuccess(FieldWorkBean data);

    void onLoadFieldWorkDataFail(Exception e);
}
