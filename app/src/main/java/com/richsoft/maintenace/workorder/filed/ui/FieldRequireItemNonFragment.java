package com.richsoft.maintenace.workorder.filed.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.richsoft.maintenace.R;
import com.richsoft.maintenace.bean.workorder.FieldWorkProcedureRequirementBean;
import ren.solid.library.fragment.base.BaseFragment;
import ren.solid.library.manager.EventCenter;

/**
 * 作者：e430 on 2017/2/17 13:30
 * <p>
 * 邮箱：chengzehao@163.com
 */

public class FieldRequireItemNonFragment extends BaseFragment{
    private ImageView iv_pass;
    private FieldWorkProcedureRequirementBean mData;
    private View.OnClickListener onClickListener;


    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }


    public static FieldRequireItemNonFragment newInstance(FieldWorkProcedureRequirementBean data) {
        Bundle args = new Bundle();
        FieldRequireItemNonFragment fragment = new FieldRequireItemNonFragment();
        args.putSerializable("data",data);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mData= (FieldWorkProcedureRequirementBean) getArguments().getSerializable("data");
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
        return R.layout.item_non_choice;
    }

    @Override
    protected void setUpView() {
        iv_pass=$(R.id.iv_pass);
        iv_pass.setImageResource(R.mipmap.nav_gray);
        iv_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mData.verify==0){
                    mData.verify=1;
                    iv_pass.setImageResource(R.mipmap.nav_green);
                    if(onClickListener!=null){
                        onClickListener.onClick(iv_pass);
                    }
                }
            }
        });
    }

    @Override
    protected void setUpData() {

    }

}
