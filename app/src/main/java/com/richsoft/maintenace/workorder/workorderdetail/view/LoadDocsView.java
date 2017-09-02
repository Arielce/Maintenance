package com.richsoft.maintenace.workorder.workorderdetail.view;

import com.richsoft.maintenace.bean.workorder.DocumentBean;
import java.util.List;

/**
 * 作者：周麟
 * 邮箱：chengzehao@163.com
 */
public interface LoadDocsView {

    void onLoadDocsSuccess(List<DocumentBean> data);

    void onLoadDocsFail(Exception e);
}
