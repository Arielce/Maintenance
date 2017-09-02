package com.richsoft.maintenace.workorder.filed.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;
import com.richsoft.maintenace.R;
import com.richsoft.maintenace.bean.workorder.FieldWorkProcedureRequirementBean;
import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;
import ren.solid.library.fragment.base.BaseFragment;
import ren.solid.library.listener.OnSeekBarChooseListener;
import ren.solid.library.manager.EventCenter;

/**
 * 作者：e430 on 2017/2/17 13:30
 * <p>
 * 邮箱：chengzehao@163.com
 */

public class FieldRequireItemDataFragment extends BaseFragment{
    private DiscreteSeekBar dsb_data;
    private TextView tv_unit;
    private FieldWorkProcedureRequirementBean mData;
    private int max;
    private int min;
    private OnSeekBarChooseListener onSeekBarChooseListener;

    public void setOnSeekBarChooseListener(OnSeekBarChooseListener onSeekBarChooseListener) {
        this.onSeekBarChooseListener = onSeekBarChooseListener;
    }

    public static FieldRequireItemDataFragment newInstance(FieldWorkProcedureRequirementBean data,int min,int max) {
        Bundle args = new Bundle();
        FieldRequireItemDataFragment fragment = new FieldRequireItemDataFragment();
        args.putSerializable("data",data);
        args.putInt("max",max);
        args.putInt("min",min);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mData= (FieldWorkProcedureRequirementBean) getArguments().getSerializable("data");
        max=getArguments().getInt("max");
        min=getArguments().getInt("min");
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
        return R.layout.item_data_record;
    }

    @Override
    protected void setUpView() {
        tv_unit=$(R.id.tv_unit);
        tv_unit.setText(mData.dataUnit);
        dsb_data=$(R.id.dsb_data);
        dsb_data.setMax(max);
        dsb_data.setMin(min);
        dsb_data.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                mData.data=String.valueOf(value);
                if(onSeekBarChooseListener!=null){
                    onSeekBarChooseListener.onSeekBarChoose(value);
                }
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {

            }
        });
    }

    @Override
    protected void setUpData() {

    }

}
