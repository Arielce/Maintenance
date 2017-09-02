package com.richsoft.maintenace.workorder.prepare.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.goodiebag.horizontalpicker.HorizontalPicker;
import com.richsoft.maintenace.R;
import com.richsoft.maintenace.bean.workorder.PrepareProcedureRequirementBean;
import com.richsoft.maintenace.bean.workorder.WorkOrderBean;

import java.util.ArrayList;
import java.util.List;

import ren.solid.library.fragment.base.BaseFragment;
import ren.solid.library.manager.EventCenter;

/**
 * 作者：e430 on 2017/2/17 13:30
 * <p>
 * 邮箱：chengzehao@163.com
 */

public class PrepareRequireItemUserFragment extends BaseFragment{
    private HorizontalPicker hpicker;
    private PrepareProcedureRequirementBean mData;
    private WorkOrderBean mWorkOrderBean;
    private boolean isSafetyMonitoring;
    private HorizontalPicker.OnSelectionChangeListener onChangeListener;


    public void setOnSelectionChangeListener(HorizontalPicker.OnSelectionChangeListener onChangeListener) {
        this.onChangeListener = onChangeListener;
    }

    public static PrepareRequireItemUserFragment newInstance(PrepareProcedureRequirementBean data,WorkOrderBean workOrderBean,boolean isSafetyMonitoring) {
        Bundle args = new Bundle();
        PrepareRequireItemUserFragment fragment = new PrepareRequireItemUserFragment();
        args.putSerializable("data",data);
        args.putSerializable("work_order",workOrderBean);
        args.putBoolean("isSafetyMonitoring",isSafetyMonitoring);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mData= (PrepareProcedureRequirementBean) getArguments().getSerializable("data");
        mWorkOrderBean= (WorkOrderBean) getArguments().getSerializable("work_order");
        isSafetyMonitoring= getArguments().getBoolean("isSafetyMonitoring");
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
        return R.layout.item_user_choice;
    }

    @Override
    protected void setUpView() {
        hpicker=$(R.id.hpicker);
        List<HorizontalPicker.PickerItem> textItems = new ArrayList<>();
        if(isSafetyMonitoring){
            textItems.add(new HorizontalPicker.TextItem(mWorkOrderBean.getRuler().getUserName()));
        }else{
            for (int i=0;i<mWorkOrderBean.getMembers().size();i++){
                textItems.add(new HorizontalPicker.TextItem(mWorkOrderBean.getMembers().get(i).getUserName()));
            }
        }
        hpicker.setItems(textItems);
        hpicker.setChangeListener(new HorizontalPicker.OnSelectionChangeListener() {
            @Override
            public void onItemSelect(HorizontalPicker horizontalPicker, int i) {
                if(isSafetyMonitoring){
                    mData.uid=mWorkOrderBean.getRuler().getUserId();
                }else{
                    mData.uid=mWorkOrderBean.getMembers().get(i).getUserId();
                }
                onChangeListener.onItemSelect(hpicker,i);
            }
        });
    }

    @Override
    protected void setUpData() {

    }

}
