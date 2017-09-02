package com.richsoft.maintenace.workorder.filed.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.richsoft.maintenace.R;
import com.richsoft.maintenace.bean.workorder.FieldWorkProcedureBean;
import com.richsoft.maintenace.bean.workorder.FieldWorkProcedureRequirementBean;
import com.richsoft.maintenace.bean.workorder.WorkOrderBean;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.util.ArrayList;

import ren.solid.library.fragment.base.BaseFragment;
import ren.solid.library.listener.OnNavigationBarListener;
import ren.solid.library.listener.OnSeekBarChooseListener;
import ren.solid.library.manager.EventCenter;
import ren.solid.library.utils.LogUtils;
import ren.solid.library.widget.StatusViewLayout;
import ren.solid.library.widget.steppers.OnCancelAction;
import ren.solid.library.widget.steppers.OnFinishAction;
import ren.solid.library.widget.steppers.OnResultClickAction;
import ren.solid.library.widget.steppers.SteppersItem;
import ren.solid.library.widget.steppers.SteppersView;

/**
 * 作者：e430 on 2017/2/17 13:30
 * <p>
 * 邮箱：chengzehao@163.com
 */

public class FieldWorkProcedureStepFragment extends BaseFragment implements Step{
    private StatusViewLayout status_view;
    private TextView tv_risk;
    private SteppersView mSteppersView;

    private FieldWorkProcedureBean mData;
    private WorkOrderBean mWorkOrderBean;
    private String mRiskControl;
    private SteppersView.Config steppersViewConfig;
    private ArrayList<SteppersItem> mSteps;

    private boolean isCanNext = false;
    @Nullable
    private OnNavigationBarListener onNavigationBarListener;

    public static FieldWorkProcedureStepFragment newInstance(FieldWorkProcedureBean data, String riskControl, WorkOrderBean workOrder) {
        Bundle args = new Bundle();
        FieldWorkProcedureStepFragment fragment = new FieldWorkProcedureStepFragment();
        args.putSerializable("data",data);
        args.putSerializable("workOrder",workOrder);
        args.putString("risk",riskControl);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mData= (FieldWorkProcedureBean) getArguments().getSerializable("data");
        mWorkOrderBean= (WorkOrderBean) getArguments().getSerializable("workOrder");
        mRiskControl=getArguments().getString("risk");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnNavigationBarListener) {
            onNavigationBarListener = (OnNavigationBarListener) context;
        }
    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_procedure_recyclerview;
    }

    @Override
    protected void setUpView() {
        status_view = $(R.id.status_view);
        tv_risk=$(R.id.tv_risk);
        tv_risk.setText("【风险辨识与预控措施】"+mRiskControl);
        mSteppersView=$(R.id.steppersView);
        steppersViewConfig = new SteppersView.Config();
        steppersViewConfig.setOnFinishAction(new OnFinishAction() {
            @Override
            public void onFinish() {
                isCanNext=true;
                updateNavigationBar();
            }
        });

        steppersViewConfig.setOnCancelAction(new OnCancelAction() {
            @Override
            public void onCancel() {

            }
        });

        steppersViewConfig.setOnResultClickAction(new OnResultClickAction() {
            @Override
            public void onResultClick(int position) {
                Log.i("zhoul",position+"");
            }
        });

    }

    @Override
    protected void setUpData() {
        steppersViewConfig.setFragmentManager(getChildFragmentManager());
        mSteps = new ArrayList<>();
        for (int i=0;i<mData.fieldWorkProcedureRequirementBeans.size();i++){
            final SteppersItem item = new SteppersItem();
            final FieldWorkProcedureRequirementBean bean=mData.fieldWorkProcedureRequirementBeans.get(i);
            item.setLabel(bean.title);
            item.setPositiveButtonEnable(false);
            if(bean.type==0){
                FieldRequireItemNonFragment requireFragment= FieldRequireItemNonFragment.newInstance(bean);
                requireFragment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        item.setSubLabel("该步骤执行完毕！");
                        item.setPositiveButtonEnable(true);
                    }
                });
                item.setFragment(requireFragment);
                mSteps.add(item);
            }else if(bean.type==1){
                FieldRequireItemDataFragment requireFragment= FieldRequireItemDataFragment.newInstance(bean,0,100);
                requireFragment.setOnSeekBarChooseListener(new OnSeekBarChooseListener() {
                    @Override
                    public void onSeekBarChoose(int value) {
                        item.setSubLabel("数据记录完毕:"+value+bean.dataUnit);
                        item.setPositiveButtonEnable(true);
                    }
                });
                item.setFragment(requireFragment);
                mSteps.add(item);
            }else if(bean.type==2){

                mSteps.add(item);
            }else if(bean.type==3){
                SwitchRoomTabsFragment requireFragment=SwitchRoomTabsFragment.newInstance(bean);
                item.setFragment(requireFragment);
                item.setPositiveButtonEnable(true);
                mSteps.add(item);
            }
            else{
                LogUtils.i("zhoul","不支持类型");
            }
        }
        mSteppersView.setConfig(steppersViewConfig);
        mSteppersView.setItems(mSteps);
        mSteppersView.build();
        status_view.showContent();
    }

    @Override
    public VerificationError verifyStep() {
        return isCanNext ? null : new VerificationError("本工序未完成！");
    }

    @Override
    public void onSelected() {
        updateNavigationBar();
    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }

    public void updateNavigationBar() {
        if (onNavigationBarListener != null) {
            onNavigationBarListener.onChangeEndButtonsEnabled(isCanNext);
        }
    }

}
