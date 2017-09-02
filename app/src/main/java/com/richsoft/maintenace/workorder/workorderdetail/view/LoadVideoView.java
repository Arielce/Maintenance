package com.richsoft.maintenace.workorder.workorderdetail.view;

import com.richsoft.maintenace.bean.workorder.VideoBean;

/**
 * 作者：周麟
 * 邮箱：chengzehao@163.com
 */
public interface LoadVideoView {

    void onLoadVideoSuccess(VideoBean data);

    void onLoadVideoFail(Exception e);
}
