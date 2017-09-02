package com.richsoft.maintenace.workorder.summary.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.richsoft.maintenace.R;
import com.richsoft.maintenace.bean.workorder.SummaryBean;
import com.richsoft.maintenace.bean.workorder.WorkOrderBean;
import com.richsoft.maintenace.common.ConstantValue;
import com.richsoft.maintenace.workorder.summary.ui.adapter.SummaryWorkProcedureStepFragmentAdapter;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;
import com.stepstone.stepper.viewmodel.StepViewModel;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import ren.solid.library.activity.base.BaseActivity;
import ren.solid.library.listener.OnNavigationBarListener;
import ren.solid.library.manager.EventCenter;
import ren.solid.library.net.NetUtils;
import ren.solid.library.widget.StatusViewLayout;

public class SummaryWorkActivity extends BaseActivity implements StepperLayout.StepperListener,OnNavigationBarListener{
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.status_view)
    StatusViewLayout status_view;
    @Bind(R.id.stepperLayout)
    StepperLayout mStepperLayout;
    private List<StepViewModel> mStepViewModels;
    private SummaryBean mData;
    private WorkOrderBean mWorkOrderBean;
    private SummaryWorkProcedureStepFragmentAdapter mAdapter;
    @Override
    protected boolean toggleOverridePendingTransition() {
        return true;
    }

    @Override
    protected TransitionMode getOverridePendingTransitionMode() {
        return TransitionMode.FADE;
    }

    @Override
    protected void initViewsAndEvents() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.summary_work_procedure);
        mData=new SummaryBean();
        mStepViewModels=new ArrayList<>();
        StepViewModel stepViewModel1=new StepViewModel.Builder(mContext).setTitle("工作人员签名").create();
        StepViewModel stepViewModel2=new StepViewModel.Builder(mContext).setTitle("填写工作评价").create();
        StepViewModel stepViewModel3=new StepViewModel.Builder(mContext).setTitle("工作负责人签名").create();
        mStepViewModels.add(stepViewModel1);
        mStepViewModels.add(stepViewModel2);
        mStepViewModels.add(stepViewModel3);
        mAdapter=new SummaryWorkProcedureStepFragmentAdapter(this.getSupportFragmentManager(), mContext, mStepViewModels,mData);
        mStepperLayout.setAdapter(mAdapter);
        mStepperLayout.setOffscreenPageLimit(mStepViewModels.size());
        mStepperLayout.setListener(this);
        status_view.showContent();

    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        mWorkOrderBean= (WorkOrderBean) extras.getSerializable("work_order");
    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_procedure;
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {

    }

    @Override
    public void onCompleted(View completeButton) {
        //进行总结工作提交,然后刷新工单详情页面，并关闭本界面
        EventBus.getDefault().post(new EventCenter(ConstantValue.EVENT_SUMMARY_WORK_SUCCESS,"2017-08-02 15:00"));
        finish();
    }

    @Override
    public void onError(VerificationError verificationError) {
        showMsgShortTime(verificationError.getErrorMessage());
    }

    @Override
    public void onStepSelected(int newStepPosition) {
    }

    @Override
    public void onReturn() {
        finish();
    }

    @Override
    public void onChangeEndButtonsEnabled(boolean enabled) {
        mStepperLayout.setNextButtonVerificationFailed(!enabled);
        mStepperLayout.setCompleteButtonVerificationFailed(!enabled);
    }

    @Override
    public void onBackPressed() {
        final int currentStepPosition = mStepperLayout.getCurrentStepPosition();
        if (currentStepPosition > 0) {
            mStepperLayout.setCurrentStepPosition(currentStepPosition - 1);
        } else {
            super.onBackPressed();
        }
    }
}
