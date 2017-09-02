package com.richsoft.maintenace.workorder.sendworkorder.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.richsoft.maintenace.bean.user.UserBean;
import com.richsoft.maintenace.bean.workorder.WorkOrderBean;
import com.richsoft.maintenace.common.BaseRequestModel;
import com.richsoft.maintenace.common.Urls;
import com.richsoft.maintenace.workorder.annotation.SendWorkOrderStrategy;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import ren.solid.library.activity.base.BaseActivity;
import ren.solid.library.net.BaseSingleListener;
import ren.solid.library.utils.LogUtils;
import ren.solid.library.utils.SPUtil;

public class SendWorkModelImpl extends BaseRequestModel implements SendWorkModel
{
	private BaseActivity mActivity;
	private Request<String> mRequest = null;

	public SendWorkModelImpl(BaseActivity mActivity) {
		this.mActivity = mActivity;
	}

	@Override
	public void send(WorkOrderBean workOrderBean, final BaseSingleListener listener)
	{
		mRequest= NoHttp.createStringRequest(Urls.URL_SENDWORK, RequestMethod.GET);
		UserBean monitor=new UserBean();
		monitor.setUserId( SPUtil.getInstance().getUid());
		workOrderBean.setMonitor(monitor);
		workOrderBean.setUnitId(SPUtil.getInstance().getCompanyId());
		workOrderBean.setDepId(SPUtil.getInstance().getUserDepartmentId());
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.addSerializationExclusionStrategy(new SendWorkOrderStrategy());
		LogUtils.i("zhoul",gsonBuilder.create().toJson(workOrderBean));
		mRequest.add("send_workorder",gsonBuilder.create().toJson(workOrderBean));

		requestServer(mActivity,0,mRequest,listener,true,true,true);

	}

	@Override
	protected void parseContent(String content, BaseSingleListener listener) {
		listener.onSuccess(content);
	}
}
