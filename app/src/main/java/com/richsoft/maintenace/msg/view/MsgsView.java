package com.richsoft.maintenace.msg.view;


import com.richsoft.maintenace.bean.msg.GanHuoDataBean;

import java.util.List;

/**
 * 作者：周麟
 * 邮箱：chengzehao@163.com
 */
public interface MsgsView {

    void onLoadMsgsSuccess(int pageIndex, List<GanHuoDataBean> data);

    void onLoadMsgsFail(Exception e);
}
