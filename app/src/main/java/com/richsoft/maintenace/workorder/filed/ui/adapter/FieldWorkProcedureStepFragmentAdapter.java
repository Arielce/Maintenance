package com.richsoft.maintenace.workorder.filed.ui.adapter;

import android.content.Context;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;

import com.richsoft.maintenace.bean.workorder.FieldWorkProcedureBean;
import com.richsoft.maintenace.bean.workorder.WorkOrderBean;
import com.richsoft.maintenace.workorder.filed.ui.FieldWorkProcedureStepFragment;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

import java.util.List;

public class FieldWorkProcedureStepFragmentAdapter extends AbstractFragmentStepAdapter {
    private List<FieldWorkProcedureBean> mFieldWorkProcedureBeans;
    private List<StepViewModel> mStepViewModels;
    private List<String> mRiskControls;
    private WorkOrderBean mWorkOrderBean;

    public FieldWorkProcedureStepFragmentAdapter(@NonNull FragmentManager fm, @NonNull Context context, List<StepViewModel> stepViewModels, List<FieldWorkProcedureBean> fieldWorkProcedureBeans, List<String> riskControls, WorkOrderBean workOrder) {
        super(fm, context);
        this.mStepViewModels = stepViewModels;
        this.mFieldWorkProcedureBeans=fieldWorkProcedureBeans;
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
        return FieldWorkProcedureStepFragment.newInstance(mFieldWorkProcedureBeans.get(position),mRiskControls.get(position),mWorkOrderBean);
    }

    @Override
    public int getCount() {
        return mStepViewModels.size();
    }
}