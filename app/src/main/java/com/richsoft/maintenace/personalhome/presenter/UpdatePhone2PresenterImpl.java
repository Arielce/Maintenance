package com.richsoft.maintenace.personalhome.presenter;


import com.richsoft.maintenace.personalhome.model.UpdatePhone2Model;
import com.richsoft.maintenace.personalhome.model.UpdatePhone2ModelImpl;
import com.richsoft.maintenace.personalhome.view.PersonalView;

import ren.solid.library.activity.base.BaseActivity;
import ren.solid.library.net.BaseSingleListener;

public class UpdatePhone2PresenterImpl implements UpdatePhone2Presenter, BaseSingleListener<String> {
    private PersonalView personalView;
    private UpdatePhone2Model personalModel;

    public UpdatePhone2PresenterImpl(PersonalView personalView, BaseActivity mActivity) {
        this.personalView = personalView;
        this.personalModel = new UpdatePhone2ModelImpl(mActivity);
    }


    @Override
    public void updatePhone2(String phone2) {
        personalModel.updatePhone2(phone2, this);
    }


    @Override
    public void onSuccess(String data) {
        personalView.refreshPhone2(data);
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
