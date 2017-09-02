package com.richsoft.maintenace.personalhome.presenter;


import com.richsoft.maintenace.personalhome.model.UpdateRemarkModel;
import com.richsoft.maintenace.personalhome.model.UpdateRemarkModelImpl;
import com.richsoft.maintenace.personalhome.view.PersonalView;

import ren.solid.library.activity.base.BaseActivity;
import ren.solid.library.net.BaseSingleListener;

public class UpdateRemarkPresenterImpl implements UpdateRemarkPresenter, BaseSingleListener<String> {
    private PersonalView personalView;
    private UpdateRemarkModel personalModel;

    public UpdateRemarkPresenterImpl(PersonalView personalView, BaseActivity mActivity) {
        this.personalView = personalView;
        this.personalModel = new UpdateRemarkModelImpl(mActivity);
    }




    @Override
    public void updateRemark(String remark) {
        personalModel.updateRemark(remark, this);
    }



    @Override
    public void onSuccess(String data) {
        personalView.refreshRemark(data);
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
