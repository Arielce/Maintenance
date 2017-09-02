package com.richsoft.maintenace.workorder.sendworkorder.model;


import com.richsoft.maintenace.bean.workorder.WorkOrderBean;

import ren.solid.library.net.BaseSingleListener;

public interface SendWorkModel
{
	void send(WorkOrderBean workOrderBean, BaseSingleListener listener);
}
