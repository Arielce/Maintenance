package com.richsoft.maintenace.personalhome.model;


import ren.solid.library.net.BaseSingleListener;

public interface UpdatePwdModel
{
	void updatePwd(String originalPwd, String newPwd, BaseSingleListener listener);
}
