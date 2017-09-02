package com.richsoft.maintenace.workorder.prepare.view;

import com.richsoft.maintenace.bean.workorder.PrepareBean;

/**
 * 作者：周麟
 * 邮箱：chengzehao@163.com
 */
public interface LoadPrepareProceduresView {

    void onLoadPrepareProceduresSuccess(PrepareBean data);

    void onLoadPrepareProceduresFail(Exception e);
}
