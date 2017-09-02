package com.richsoft.maintenace.workorder.sendworkorder.view;

import com.richsoft.maintenace.bean.workorder.PlannedLaborBean;

import java.util.List;

/**
 * 作者：周麟
 * 邮箱：chengzehao@163.com
 */
public interface PlannedLaborView {

    void onLoadPlannedLaborsSuccess(List<PlannedLaborBean> data);

    void onLoadPlannedLaborsFail(Exception e);
}
