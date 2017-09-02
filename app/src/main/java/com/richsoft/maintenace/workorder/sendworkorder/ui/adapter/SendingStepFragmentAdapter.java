package com.richsoft.maintenace.workorder.sendworkorder.ui.adapter;

import android.content.Context;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import com.richsoft.maintenace.bean.workorder.WorkOrderBean;
import com.richsoft.maintenace.common.TestFragment;
import com.richsoft.maintenace.workorder.sendworkorder.ui.DateChooseFragment;
import com.richsoft.maintenace.workorder.sendworkorder.ui.MultUsersChooseFragment;
import com.richsoft.maintenace.workorder.sendworkorder.ui.SingleUserChooseFragment;
import com.richsoft.maintenace.workorder.sendworkorder.ui.SubstaionChooseFragment;
import com.richsoft.maintenace.workorder.sendworkorder.ui.TaskTabFragment;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

import java.util.List;

public class SendingStepFragmentAdapter extends AbstractFragmentStepAdapter {

    private List<StepViewModel> mStepViewModels;
    private WorkOrderBean mWorkOrderBean;

    public SendingStepFragmentAdapter(@NonNull FragmentManager fm, @NonNull Context context, List<StepViewModel> stepViewModels, WorkOrderBean workOrderBean) {
        super(fm, context);
        this.mStepViewModels = stepViewModels;
        this.mWorkOrderBean = workOrderBean;
    }

    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {
        return mStepViewModels.get(position);
    }

    @Override
    public Step createStep(int position) {
        switch (position) {
            case 0:
                return SubstaionChooseFragment.newInstance(mWorkOrderBean);
            case 1:
                return TaskTabFragment.newInstance(mWorkOrderBean);
            case 2:
                return SingleUserChooseFragment.newInstance(mWorkOrderBean);
            case 3:
                return MultUsersChooseFragment.newInstance(mWorkOrderBean);
            case 4:
                return DateChooseFragment.newInstance(mWorkOrderBean);
        }
        return new TestFragment();
    }

    @Override
    public int getCount() {
        return mStepViewModels.size();
    }
}