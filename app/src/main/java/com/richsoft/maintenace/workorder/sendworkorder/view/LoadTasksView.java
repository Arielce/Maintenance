package com.richsoft.maintenace.workorder.sendworkorder.view;

import com.richsoft.maintenace.bean.workorder.WorkTaskListBean;
import java.util.List;

/**
 * 作者：周麟
 * 邮箱：chengzehao@163.com
 */
public interface LoadTasksView {

    void onLoadTasksSuccess(List<WorkTaskListBean> data);

    void onLoadTasksFail(Exception e);
}
