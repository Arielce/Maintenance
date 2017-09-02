package com.richsoft.maintenace.personalhome.presenter;

import android.graphics.Bitmap;

import com.richsoft.maintenace.personalhome.model.UpdateHeadPortraitModel;
import com.richsoft.maintenace.personalhome.model.UpdateHeadPortraitModelImpl;
import com.richsoft.maintenace.personalhome.view.PersonalView;

import ren.solid.library.activity.base.BaseActivity;
import ren.solid.library.net.BaseSingleListener;


public class UpdateHeadPortraitPresenterImpl implements UpdateHeadPortraitPresenter, BaseSingleListener<String> {
    private PersonalView personalView;
    private UpdateHeadPortraitModel personalModel;

    public UpdateHeadPortraitPresenterImpl(PersonalView personalView, BaseActivity mActivity) {
        this.personalView = personalView;
        this.personalModel = new UpdateHeadPortraitModelImpl(mActivity);
    }


    @Override
    public void updateHeadPortrait(Bitmap head) {
        personalModel.updateHeadPortrait(head, this);
    }


    @Override
    public void onSuccess( String data) {
        personalView.refreshHeadPortrait(data);
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
