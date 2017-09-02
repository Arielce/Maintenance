package com.richsoft.maintenace.common;

import android.support.annotation.NonNull;

import com.richsoft.maintenace.R;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import ren.solid.library.fragment.base.BaseFragment;
import ren.solid.library.manager.EventCenter;


/**
 * Created by Administrator on 2016/12/20.
 */

public class TestFragment extends BaseFragment implements Step {
    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_test;
    }

    @Override
    protected void setUpView() {

    }

    @Override
    protected void setUpData() {

    }

    @Override
    public VerificationError verifyStep() {
        return null;
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }
}
