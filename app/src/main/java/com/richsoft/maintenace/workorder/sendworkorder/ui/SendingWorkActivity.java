package com.richsoft.maintenace.workorder.sendworkorder.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.richsoft.maintenace.R;
import com.richsoft.maintenace.bean.user.UserBean;
import com.richsoft.maintenace.bean.workorder.SubstationBean;
import com.richsoft.maintenace.bean.workorder.WorkOrderBean;
import com.richsoft.maintenace.bean.workorder.WorkTaskBean;
import com.richsoft.maintenace.common.ConstantValue;
import com.richsoft.maintenace.workorder.sendworkorder.presenter.SendWorkPresenter;
import com.richsoft.maintenace.workorder.sendworkorder.presenter.SendWorkPresenterImpl;
import com.richsoft.maintenace.workorder.sendworkorder.view.SendWorkView;
import com.richsoft.maintenace.workorder.sendworkorder.ui.adapter.SendingStepFragmentAdapter;
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

public class SendingWorkActivity extends BaseActivity implements StepperLayout.StepperListener,OnNavigationBarListener ,SendWorkView{
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.status_view)
    StatusViewLayout status_view;
    @Bind(R.id.stepperLayout)
    StepperLayout mStepperLayout;
    private List<StepViewModel> mStepViewModels;
    private WorkOrderBean mWorkOrderBean;
    private SendWorkPresenter mPresenter;

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
        //初始化标题栏
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.send_workorder);
        status_view.showContent();
        //创建一个工单实例
        mWorkOrderBean=new WorkOrderBean();
        SubstationBean sb=new SubstationBean();
        mWorkOrderBean.setSubstation(sb);
        WorkTaskBean wb=new WorkTaskBean();
        mWorkOrderBean.setTask(wb);
        UserBean ruler=new UserBean();
        mWorkOrderBean.setRuler(ruler);
        ArrayList<UserBean> members=new ArrayList<>();
        mWorkOrderBean.setMembers(members);
        //初始化派发工单步骤相关数据
        mStepViewModels=new ArrayList<>();
        StepViewModel stepViewModel1=new StepViewModel.Builder(mContext).setTitle(R.string.appoint_substation).create();
        StepViewModel stepViewModel2=new StepViewModel.Builder(mContext).setTitle(R.string.appoint_task).create();
        StepViewModel stepViewModel3=new StepViewModel.Builder(mContext).setTitle(R.string.appoint_ruler).create();
        StepViewModel stepViewModel4=new StepViewModel.Builder(mContext).setTitle(R.string.appoint_members).create();
        StepViewModel stepViewModel5=new StepViewModel.Builder(mContext).setTitle(R.string.appoint_date).create();
        mStepViewModels.add(stepViewModel1);
        mStepViewModels.add(stepViewModel2);
        mStepViewModels.add(stepViewModel3);
        mStepViewModels.add(stepViewModel4);
        mStepViewModels.add(stepViewModel5);
        mStepperLayout.setAdapter(new SendingStepFragmentAdapter(getSupportFragmentManager(), this, mStepViewModels,mWorkOrderBean), 0);
        mStepperLayout.setOffscreenPageLimit(mStepViewModels.size());
        mStepperLayout.setListener(this);
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_sending_work;
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {

    }

    @Override
    public void onCompleted(View completeButton) {
        //进行工单的派发
        if(mPresenter==null){
            mPresenter=new SendWorkPresenterImpl(this,this);
        }
        mPresenter.send(mWorkOrderBean);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_to_plan_tasks, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.to_plan:
                readyGo(PlannedLaborListActivity.class);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showSendSuccessMsg(String msg) {
        showMsgShortTime(msg);
        EventBus.getDefault().post(new EventCenter(ConstantValue.EVENT_SEND_WORK_SUCCESS));
        finish();
    }

    @Override
    public void showSendFailureMsg(Exception e) {
        showMsgShortTime(e.getMessage());
    }
}
