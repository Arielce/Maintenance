package com.richsoft.maintenace.workorder.prepare.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.richsoft.maintenace.R;
import com.richsoft.maintenace.bean.workorder.PrepareProcedureRequirementBean;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

import ren.solid.library.fragment.base.BaseFragment;
import ren.solid.library.listener.OnNavigationBarListener;
import ren.solid.library.listener.OnSeekBarChooseListener;
import ren.solid.library.manager.EventCenter;

/**
 * 作者：e430 on 2017/2/17 13:30
 * <p>
 * 邮箱：chengzehao@163.com
 */

public class PrepareRequireItemHumidityFragment extends BaseFragment{
    private DiscreteSeekBar dsb_humidity;
    private PrepareProcedureRequirementBean mData;
    private OnSeekBarChooseListener onSeekBarChooseListener;

    public void setOnSeekBarChooseListener(OnSeekBarChooseListener onSeekBarChooseListener) {
        this.onSeekBarChooseListener = onSeekBarChooseListener;
    }

    @Nullable
    private OnNavigationBarListener onNavigationBarListener;

    public static PrepareRequireItemHumidityFragment newInstance(PrepareProcedureRequirementBean data) {
        Bundle args = new Bundle();
        PrepareRequireItemHumidityFragment fragment = new PrepareRequireItemHumidityFragment();
        args.putSerializable("data",data);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mData= (PrepareProcedureRequirementBean) getArguments().getSerializable("data");
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
        return R.layout.item_humidity_record;
    }

    @Override
    protected void setUpView() {
        dsb_humidity=$(R.id.dsb_humidity);
        dsb_humidity.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                mData.humidity=String.valueOf(value);
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
