package com.richsoft.maintenace.personalhome.presenter;

import com.richsoft.maintenace.personalhome.model.UpdatePwdModel;
import com.richsoft.maintenace.personalhome.model.UpdatePwdModelImpl;
import com.richsoft.maintenace.personalhome.view.PersonalView;

import ren.solid.library.activity.base.BaseActivity;
import ren.solid.library.net.BaseSingleListener;

public class UpdatePwdPresenterImpl implements UpdatePwdPresenter, BaseSingleListener<String> {
    private PersonalView personalView;
    private UpdatePwdModel personalModel;

    public UpdatePwdPresenterImpl(PersonalView personalView, BaseActivity mActivity) {
        this.personalView = personalView;
        this.personalModel = new UpdatePwdModelImpl(mActivity);
    }


    @Override
    public void updatePwd(String originalPwd, String newPwd) {
        personalModel.updatePwd(originalPwd, newPwd, this);
    }


    @Override
    public void onSuccess(String data) {
        personalView.refreshPwd(data);
    }

    @Override
    public void onError(Exception e) {
        personalView.showNetFailureMsg(e.getMessage());
    }

    @Override
    public void onException(Exception e) {
        personalView.showNetFailureMsg(e.getMessage());
    }
}
