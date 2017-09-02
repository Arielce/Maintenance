package com.richsoft.maintenace.login.presenter;


import android.content.Context;

public interface LoginPresenter
{
	void login(String username, String pwd);

	void getCopy(Context context);

}
