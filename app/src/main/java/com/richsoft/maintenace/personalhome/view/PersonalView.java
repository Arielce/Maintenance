package com.richsoft.maintenace.personalhome.view;


public interface PersonalView
{
	
	void refreshHeadPortrait(String path);

	void refreshPhone(String phone);

	void refreshPhone2(String phone2);

	void refreshEmail(String email);

	void refreshRemark(String remark);

	void refreshPwd(String newPwd);

	void showNetFailureMsg(String msg);

}
