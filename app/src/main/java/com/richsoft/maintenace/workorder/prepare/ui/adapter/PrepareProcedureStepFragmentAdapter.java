package com.richsoft.maintenace.workorder.prepare.ui.adapter;

import android.content.Context;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import com.richsoft.maintenace.bean.workorder.PrepareProcedureBean;
import com.richsoft.maintenace.bean.workorder.WorkOrderBean;
import com.richsoft.maintenace.workorder.prepare.ui.PrepareProcedureStepFragment;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;
import java.util.List;

public class PrepareProcedureStepFragmentAdapter extends AbstractFragmentStepAdapter {
    private List<PrepareProcedureBean> mPrepareProcedureBeans;
    private List<StepViewModel> mStepViewModels;
    private List<String> mRiskControls;
    private WorkOrderBean mWorkOrderBean;

    public PrepareProcedureStepFragmentAdapter(@NonNull FragmentManager fm, @NonNull Context context, List<StepViewModel> stepViewModels, List<PrepareProcedureBean> prepareProcedureBeans, List<String> riskControls, WorkOrderBean workOrder) {
        super(fm, context);
        this.mStepViewModels = stepViewModels;
        this.mPrepareProcedureBeans=prepareProcedureBeans;
        this.mRiskControls=riskControls;
        this.mWorkOrderBean=workOrder;
    }

    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {
        return mStepViewModels.get(position);
    }

    @Override
    public Step createStep(int position) {
        return PrepareProcedureStepFragment.newInstance(mPrepareProcedureBeans.get(position),mRiskControls.get(position),mWorkOrderBean);
    }

    @Override
    public int getCount() {
        return mStepViewModels.size();
    }
}