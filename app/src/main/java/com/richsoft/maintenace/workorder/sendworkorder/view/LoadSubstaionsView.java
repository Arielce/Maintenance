package com.richsoft.maintenace.workorder.sendworkorder.view;

import com.richsoft.maintenace.bean.workorder.SubstationBean;
import java.util.List;

/**
 * 作者：周麟
 * 邮箱：chengzehao@163.com
 */
public interface LoadSubstaionsView {

    void onLoadSubstaionsSuccess(List<SubstationBean> data);

    void onLoadSubstaionsFail(Exception e);
}
