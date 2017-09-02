package com.richsoft.maintenace.login.presenter;

import android.content.Context;
import com.richsoft.maintenace.bean.user.UserBean;
import com.richsoft.maintenace.common.Urls;
import com.richsoft.maintenace.login.model.LoginModel;
import com.richsoft.maintenace.login.model.LoginModelImpl;
import com.richsoft.maintenace.login.view.LoginView;
import ren.solid.library.activity.base.BaseActivity;
import ren.solid.library.net.BaseSingleListener;
import ren.solid.library.utils.SPUtil;

public class LoginPresenterImpl implements LoginPresenter, BaseSingleListener<UserBean>
{
	private LoginView loginView;
	private LoginModel loginModel;

	public LoginPresenterImpl(LoginView loginView,BaseActivity mActivity) {
		this.loginView = loginView;
		this.loginModel = new LoginModelImpl(mActivity);
	}

	@Override
	public void login(String username, String pwd)
	{
		loginModel.login(username, pwd, this);
	}

	@Override
	public void getCopy(Context context) {
		loginView.fillCopy(loginModel.getVersionName(context),loginModel.getCopyright(context));
	}

	public void saveUserInfo2Sp(UserBean user) {
		SPUtil.getInstance().saveUserDepartmentId(user.getDeptNo());
		SPUtil.getInstance().saveUserDepartment(user.getDeptName());
		SPUtil.getInstance().saveCompanyId(user.getOrgId());
		SPUtil.getInstance().saveCompany(user.getOrgName());
		SPUtil.getInstance().saveUserPostId(user.getPostNo());
		SPUtil.getInstance().saveUserPost(user.getPostName());
		SPUtil.getInstance().saveUserRole(user.getRoleId());
		SPUtil.getInstance().saveUserRoleDes(user.getRoleName());
		SPUtil.getInstance().saveUid(user.getUserId());
		SPUtil.getInstance().saveUserName(user.getUserName());
		SPUtil.getInstance().saveUserNo(user.getUserNo());
		SPUtil.getInstance().saveUserAvater(Urls.SERVER_URL+user.getPicPath());
		SPUtil.getInstance().saveUserPhone(user.getPhone());
		SPUtil.getInstance().saveUserPhone2(user.getPhone2());
		SPUtil.getInstance().saveUserRemark(user.getRemark());
		SPUtil.getInstance().saveUserEmail(user.getEmail());
	}


	@Override
	public void onSuccess(final UserBean data) {
		if(data==null){
			loginView.showLoginFailureMsg(new Exception("用户未找到"));
			return;
		}
		saveUserInfo2Sp(data);
		loginView.toMain(data);
	}

	@Override
	public void onError(Exception e) {
		loginView.showLoginFailureMsg(e);
	}

	@Override
	public void onException(Exception e) {
		loginView.showLoginFailureMsg(e);
	}

}
