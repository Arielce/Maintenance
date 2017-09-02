package com.richsoft.maintenace.login.model;

import android.content.Context;
import android.content.pm.PackageManager;
import com.google.gson.Gson;
import com.richsoft.maintenace.R;
import com.richsoft.maintenace.bean.user.UserBean;
import com.richsoft.maintenace.common.Urls;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.CacheMode;
import com.yolanda.nohttp.rest.Request;
import ren.solid.library.activity.base.BaseActivity;
import com.richsoft.maintenace.common.BaseRequestModel;
import ren.solid.library.net.BaseSingleListener;
import ren.solid.library.utils.LogUtils;
import ren.solid.library.utils.StringUtils;

public class LoginModelImpl extends BaseRequestModel implements LoginModel
{
	private BaseActivity mActivity;
	private Request<String> loginRequest = null;

	public LoginModelImpl(BaseActivity mActivity) {
		this.mActivity = mActivity;
	}

	@Override
	public void login(String username, String pwd, final BaseSingleListener listener)
	{
		/**脱机临时假数据*/
		UserBean user = null;
		if(username.equals("cx01")){
			user=new UserBean();
			user.setDeptNo("TJ_CX_DP_01");
			user.setDeptName("运检部变电运维一班");
			user.setOrgId("TJ_CX");
			user.setOrgName("天津城西电力公司");
			user.setPostNo("TJ_CX_DP_01_01");
			user.setPostName("班长");
			user.setRoleId("1");//1表示班长
			user.setRoleName("MANAGER");
			user.setUserId("01");
			user.setUserName("周星驰");
			user.setUserNo("cx01");
			user.setEmail("chengzehao@163.com");
			user.setPhone("15222696797");
			user.setPhone2("15222696797");
			user.setRemark("追求卓越，超越自己");
		}else if(username.equals("cx02")){
			user=new UserBean();
			user.setDeptNo("TJ_CX_DP_01");
			user.setDeptName("运检部变电运维一班");
			user.setOrgId("TJ_CX");
			user.setOrgName("天津城西电力公司");
			user.setPostNo("TJ_CX_DP_01_02");
			user.setPostName("正值");
			user.setRoleId("2");//2表示正值
			user.setRoleName("WORKER");
			user.setUserId("02");
			user.setUserName("朱茵");
			user.setUserNo("cx02");
			user.setEmail("chengzehao@163.com");
			user.setPhone("15222696797");
			user.setPhone2("15222696797");
			user.setRemark("追求卓越，超越自己");
		}
		listener.onSuccess(user);
		/**脱机临时假数据*/

		/*loginRequest= NoHttp.createStringRequest(Urls.LOGIN_URL, RequestMethod.POST);
		loginRequest.add("uid", "1");
		loginRequest.add("passwd", "123");
		loginRequest.setCacheMode(CacheMode.ONLY_REQUEST_NETWORK);
		requestServer(mActivity,0,loginRequest,listener,true,true,false);*/

	}

	@Override
	public String getCopyright(Context context) {
		return context.getResources().getString(R.string.splash_copyright);
	}

	@Override
	public String getVersionName(Context context) {
		String versionName = null;
		try {
			versionName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		return String.format(context.getResources().getString(R.string.splash_version), versionName);
	}

	@Override
	protected void parseContent(String content, BaseSingleListener listener) {
		if(!StringUtils.isNullOrEmpty(content)){
			Gson gson = new Gson();
			UserBean user = gson.fromJson(content,UserBean.class);
			listener.onSuccess(user);
		}else{
			listener.onSuccess(null);
		}
	}
}
