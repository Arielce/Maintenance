package com.richsoft.maintenace.workorder.sendworkorder.ui.adapter;

import android.content.Context;
import android.view.View;

import com.richsoft.maintenace.R;
import com.richsoft.maintenace.bean.workorder.PlannedLaborBean;
import java.util.List;

import ren.solid.library.activity.base.BaseActivity;
import ren.solid.library.adapter.SolidRVBaseAdapter;

/**
 * 作者：e430 on 2017/1/10 12:49
 * <p>
 * 邮箱：chengzehao@163.com
 */

public class PlannedLaborListAdapter extends SolidRVBaseAdapter<PlannedLaborBean> {

    public PlannedLaborListAdapter(Context context, List<PlannedLaborBean> beans) {
        super(context, beans);
    }

    @Override
    protected void onBindDataToView(final SolidCommonViewHolder holder, final PlannedLaborBean bean, final int position) {
        holder.setText(R.id.tv_task,bean.getWorkName());
        holder.setText(R.id.tv_task_date,bean.getWorkTime());
        holder.setText(R.id.tv_group,"【班组】"+bean.getGroupName());
        holder.setText(R.id.tv_rule_name,"【负责人】"+bean.getRuleName());
        holder.setOnClickListener(R.id.bt_remove, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getDatas().size()==1){
                    ((BaseActivity)mContext).showMsgShortTime("已经是最后一条了");
                    return;
                }
                getDatas().remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemLayoutID(int viewType) {
        return R.layout.item_planned_labor;
    }

}
