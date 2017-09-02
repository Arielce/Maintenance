package com.richsoft.maintenace.login.view;


import com.richsoft.maintenace.bean.user.UserBean;

public interface LoginView
{
	
	void toMain(UserBean user);
	
	void showLoginFailureMsg(Exception e);

	void fillCopy(String versionName, String copyright);
}
