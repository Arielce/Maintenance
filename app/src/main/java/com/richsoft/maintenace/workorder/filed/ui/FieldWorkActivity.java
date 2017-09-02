package com.richsoft.maintenace.workorder.filed.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.richsoft.maintenace.R;
import com.richsoft.maintenace.bean.workorder.FieldWorkBean;
import com.richsoft.maintenace.bean.workorder.WorkOrderBean;
import com.richsoft.maintenace.common.ConstantValue;
import com.richsoft.maintenace.workorder.filed.presenter.LoadFieldWorkDataPresenter;
import com.richsoft.maintenace.workorder.filed.presenter.LoadFieldWorkDataPresenterImpl;
import com.richsoft.maintenace.workorder.filed.view.LoadFieldWorkDataView;
import com.richsoft.maintenace.workorder.filed.ui.adapter.FieldWorkProcedureStepFragmentAdapter;
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
import ren.solid.library.manager.ThreadManager;
import ren.solid.library.net.NetUtils;
import ren.solid.library.utils.UIUtils;
import ren.solid.library.widget.StatusViewLayout;

public class FieldWorkActivity extends BaseActivity implements StepperLayout.StepperListener,OnNavigationBarListener ,LoadFieldWorkDataView {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.status_view)
    StatusViewLayout status_view;
    @Bind(R.id.stepperLayout)
    StepperLayout mStepperLayout;
    private List<StepViewModel> mStepViewModels;
    private List<String> mRiskControls;
    private FieldWorkBean mData;
    private WorkOrderBean mWorkOrderBean;
    private LoadFieldWorkDataPresenter mLoadFieldWorkDataPresenter;
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
        getSupportActionBar().setTitle(R.string.field_work_procedure);
        status_view.showLoading();
        mLoadFieldWorkDataPresenter=new LoadFieldWorkDataPresenterImpl(this,this);
        ThreadManager.getLongPool().submit(new Runnable() {
            @Override
            public void run() {
                mLoadFieldWorkDataPresenter.loadFieldWorkProcedure(mWorkOrderBean.getOrderNo());
            }
        });
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
        //进行现场工作提交,然后刷新工单详情页面，并关闭本界面
        EventBus.getDefault().post(new EventCenter(ConstantValue.EVENT_FIELD_WORK_SUCCESS,"2017-07-02 15:00"));
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

    @Override
    public void onLoadFieldWorkDataSuccess(final FieldWorkBean data) {
        if(data==null||data.getFieldWorkProcedureBeans()==null||data.getFieldWorkProcedureBeans().size()==0){
            UIUtils.postDelayed(new Runnable() {
                @Override
                public void run() {
                    status_view.showError("系统错误，请您稍候重试！");
                }
            },500);
        }else {
            mData=data;
            mStepViewModels=new ArrayList<>();
            mRiskControls=new ArrayList<>();
            for (int i=0;i<data.getFieldWorkProcedureBeans().size();i++){
                StepViewModel stepViewModel=new StepViewModel.Builder(mContext).setTitle(data.getFieldWorkProcedureBeans().get(i).procedureName).create();
                mRiskControls.add(data.getFieldWorkProcedureBeans().get(i).procedureRiskControl);
                mStepViewModels.add(stepViewModel);
            }

            UIUtils.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mStepperLayout.setAdapter(new FieldWorkProcedureStepFragmentAdapter(FieldWorkActivity.this.getSupportFragmentManager(), mContext, mStepViewModels,data.getFieldWorkProcedureBeans(),mRiskControls,mWorkOrderBean), 0);
                    mStepperLayout.setOffscreenPageLimit(mStepViewModels.size());
                    mStepperLayout.setListener(FieldWorkActivity.this);
                    status_view.showContent();
                }
            },500);
        }

    }

    @Override
    public void onLoadFieldWorkDataFail(Exception e) {
        UIUtils.postDelayed(new Runnable() {
            @Override
            public void run() {
                status_view.showError("系统错误，请您稍候重试！");
            }
        },500);
    }
}
