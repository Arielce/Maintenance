package com.richsoft.maintenace.workorder.workorderlist.ui.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.richsoft.maintenace.R;
import com.richsoft.maintenace.bean.workorder.WorkOrderBean;
import com.richsoft.maintenace.workorder.workorderdetail.ui.WorkOrderDetailActivity;
import me.drakeet.multitype.ItemViewProvider;
import ren.solid.library.utils.UIUtils;

public class WorkOrderItemProvider
        extends ItemViewProvider<WorkOrderBean, WorkOrderItemProvider.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(
            @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_workorder, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(
            @NonNull final ViewHolder holder, @NonNull final WorkOrderBean bean) {
        if (bean.getWorkState().equals("1")) {
            holder.tv_state.setText("工单待办√");
            holder.tv_state.setTextColor(UIUtils.getColor(R.color.yalan_red));
            holder.tv_state.setBackground(UIUtils.getDrawalbe(R.drawable.shape_frame_yalan_red));
        } else if (bean.getWorkState().equals("2")) {
            holder.tv_state.setText("工单接收√");
            holder.tv_state.setTextColor(UIUtils.getColor(R.color.yalan_blue));
            holder.tv_state.setBackground(UIUtils.getDrawalbe(R.drawable.shape_frame_yalan_blue));
        }else if (bean.getWorkState().equals("3")) {
            holder.tv_state.setText("准备工作√");
            holder.tv_state.setTextColor(UIUtils.getColor(R.color.yalan_pink));
            holder.tv_state.setBackground(UIUtils.getDrawalbe(R.drawable.shape_frame_yalan_pink));
        }else if (bean.getWorkState().equals("4")) {
            holder.tv_state.setText("现场工作√");
            holder.tv_state.setTextColor(UIUtils.getColor(R.color.yalan_blue_dark));
            holder.tv_state.setBackground(UIUtils.getDrawalbe(R.drawable.shape_frame_yalan_blue_dark));
        }else if (bean.getWorkState().equals("5")) {
            holder.tv_state.setText("总结工作√");
            holder.tv_state.setTextColor(UIUtils.getColor(R.color.item_title));
            holder.tv_state.setBackground(UIUtils.getDrawalbe(R.drawable.shape_frame_black));
        }else if (bean.getWorkState().equals("6")) {
            holder.tv_state.setText("确认结束√");
            holder.tv_state.setTextColor(UIUtils.getColor(R.color.item_title));
            holder.tv_state.setBackground(UIUtils.getDrawalbe(R.drawable.shape_frame_black));
        }

        holder.tv_task.setText(bean.getTask().getWorkName());
        holder.tv_task_date.setText(bean.getOrderDate());
        holder.tv_no.setText("NO."+bean.getOrderNo());
        holder.tv_rule_name.setText(bean.getRuler().getUserName());
        holder.tv_substation.setText(bean.getSubstation().getSubstationName());
        StringBuilder sb=new StringBuilder();
        for (int i=0;i<bean.getMembers().size();i++){
            if(i==bean.getMembers().size()-1){
                sb.append(bean.getMembers().get(i).getUserName());
            }else{
                sb.append(bean.getMembers().get(i).getUserName()+",");
            }
        }
        holder.tv_member.setText("【成员】"+sb.toString());
        holder.bt_operation.setVisibility(bean.getWorkState().equals("6")?View.VISIBLE:View.INVISIBLE);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("workorder", bean);
                Intent intent = new Intent(holder.itemView.getContext(), WorkOrderDetailActivity.class);
                if (null != bundle) {
                    intent.putExtras(bundle);
                }
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_state;
        TextView tv_task;
        TextView tv_task_date;
        TextView tv_no;
        TextView tv_substation;
        TextView tv_member;
        TextView tv_rule_name;
        TextView bt_operation;

        ViewHolder(View itemView) {
            super(itemView);
            tv_state = (TextView) itemView.findViewById(R.id.tv_state);
            tv_task = (TextView) itemView.findViewById(R.id.tv_task);
            tv_task_date = (TextView) itemView.findViewById(R.id.tv_task_date);
            tv_no = (TextView) itemView.findViewById(R.id.tv_no);
            tv_substation = (TextView) itemView.findViewById(R.id.tv_substation);
            tv_rule_name = (TextView) itemView.findViewById(R.id.tv_rule_name);
            tv_member= (TextView) itemView.findViewById(R.id.tv_member);
            bt_operation = (TextView) itemView.findViewById(R.id.bt_operation);
        }
    }
}