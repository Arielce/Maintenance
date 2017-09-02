package com.richsoft.maintenace.workorder.summary.ui.adapter;

import android.content.Context;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import com.richsoft.maintenace.bean.workorder.SummaryBean;
import com.richsoft.maintenace.workorder.summary.ui.ImplementationEvaluationFragment;
import com.richsoft.maintenace.workorder.summary.ui.SignFragment;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

import java.util.List;

public class SummaryWorkProcedureStepFragmentAdapter extends AbstractFragmentStepAdapter {
    private List<StepViewModel> mStepViewModels;
    private SummaryBean mBean;
    private SignFragment membersSignFragment;
    private SignFragment managerSignFragment;
    private ImplementationEvaluationFragment implementationEvaluationFragment;

    public SignFragment getMembersSignFragment() {
        return membersSignFragment;
    }

    public SignFragment getManagerSignFragment() {
        return managerSignFragment;
    }

    public ImplementationEvaluationFragment getImplementationEvaluationFragment() {
        return implementationEvaluationFragment;
    }

    public SummaryWorkProcedureStepFragmentAdapter(@NonNull FragmentManager fm, @NonNull Context context, List<StepViewModel> stepViewModels, SummaryBean bean) {
        super(fm, context);
        this.mStepViewModels = stepViewModels;
        this.mBean=bean;
    }

    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {
        return mStepViewModels.get(position);
    }

    @Override
    public Step createStep(int position) {
        switch (position){
            case 0:
                membersSignFragment=SignFragment.newInstance(mBean);
                return membersSignFragment;
            case 1:
                implementationEvaluationFragment=ImplementationEvaluationFragment.newInstance(mBean);
                return implementationEvaluationFragment;
            case 2:
                managerSignFragment=SignFragment.newInstance(mBean);
                return managerSignFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return mStepViewModels.size();
    }
}