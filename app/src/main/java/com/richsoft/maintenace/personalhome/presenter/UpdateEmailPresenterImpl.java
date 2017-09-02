package com.richsoft.maintenace.personalhome.presenter;

import com.richsoft.maintenace.personalhome.model.UpdateEmailModel;
import com.richsoft.maintenace.personalhome.model.UpdateEmailModelImpl;
import com.richsoft.maintenace.personalhome.view.PersonalView;

import ren.solid.library.activity.base.BaseActivity;
import ren.solid.library.net.BaseSingleListener;

public class UpdateEmailPresenterImpl implements UpdateEmailPresenter, BaseSingleListener<String> {
    private PersonalView personalView;
    private UpdateEmailModel personalModel;

    public UpdateEmailPresenterImpl(PersonalView personalView, BaseActivity mActivity) {
        this.personalView = personalView;
        this.personalModel = new UpdateEmailModelImpl(mActivity);
    }

    @Override
    public void updateEmail(String email) {
        personalModel.updateEmail(email, this);
    }


    @Override
    public void onSuccess(String data) {
        personalView.refreshEmail(data);
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
