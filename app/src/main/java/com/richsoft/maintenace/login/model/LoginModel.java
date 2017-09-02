package com.richsoft.maintenace.login.model;


import android.content.Context;

import ren.solid.library.net.BaseSingleListener;

public interface LoginModel
{
	void login(String username, String pwd, BaseSingleListener listener);

	String getCopyright(Context context);

	String getVersionName(Context context);
}
