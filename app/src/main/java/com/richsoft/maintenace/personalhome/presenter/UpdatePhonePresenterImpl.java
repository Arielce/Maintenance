package com.richsoft.maintenace.personalhome.presenter;


import com.richsoft.maintenace.personalhome.model.UpdatePhoneModel;
import com.richsoft.maintenace.personalhome.model.UpdatePhoneModelImpl;
import com.richsoft.maintenace.personalhome.view.PersonalView;

import ren.solid.library.activity.base.BaseActivity;
import ren.solid.library.net.BaseSingleListener;

public class UpdatePhonePresenterImpl implements UpdatePhonePresenter, BaseSingleListener<String> {
    private PersonalView personalView;
    private UpdatePhoneModel personalModel;

    public UpdatePhonePresenterImpl(PersonalView personalView, BaseActivity mActivity) {
        this.personalView = personalView;
        this.personalModel = new UpdatePhoneModelImpl(mActivity);
    }

    @Override
    public void updatePhone(String phone) {
        personalModel.updatePhone(phone, this);
    }

    @Override
    public void onSuccess(String data) {
        personalView.refreshPhone(data);
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
