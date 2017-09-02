package com.richsoft.maintenace.workorder.workorderdetail.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.richsoft.maintenace.R;
import com.richsoft.maintenace.bean.workorder.WorkOrderBean;
import com.richsoft.maintenace.bean.workorder.WorkOrderStateBean;
import com.richsoft.maintenace.common.ConstantValue;
import com.richsoft.maintenace.workorder.filed.ui.FieldWorkActivity;
import com.richsoft.maintenace.workorder.prepare.ui.PrepareHistoryActivity;
import com.richsoft.maintenace.workorder.prepare.ui.PrepareProcedureActivity;
import com.richsoft.maintenace.workorder.receive.ui.ReceiveHistoryActivity;
import com.richsoft.maintenace.workorder.summary.ui.SummaryWorkActivity;
import com.richsoft.maintenace.workorder.workorderdetail.presenter.SubmitReceivePresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import ren.solid.library.adapter.SolidRVBaseAdapter;
import ren.solid.library.manager.EventCenter;
import ren.solid.library.utils.LogUtils;
import ren.solid.library.utils.SPUtil;
import ren.solid.library.utils.StringUtils;
import ren.solid.library.utils.UIUtils;

/**
 * 作者：e430 on 2017/1/10 12:49
 * <p>
 * 邮箱：chengzehao@163.com
 */

public class WorkOrderStatesAdapter extends SolidRVBaseAdapter<WorkOrderStateBean> {
    private WorkOrderBean mWorkOrder;
    private SubmitReceivePresenter mSubmitReceivePresenter;
    public WorkOrderStatesAdapter(Context context, List<WorkOrderStateBean> beans, WorkOrderBean workOrder,SubmitReceivePresenter submitReceivePresenter) {
        super(context, beans);
        this.mWorkOrder=workOrder;
        mSubmitReceivePresenter=submitReceivePresenter;
    }

    @Override
    protected void onBindDataToView(final SolidCommonViewHolder holder, final WorkOrderStateBean bean, final int position) {
        if(position==0){//展示工单基础信息
            holder.setRoundViewCheck(R.id.roundedView,false);
            holder.setRoundViewBackgroundColor(R.id.roundedView, UIUtils.getColor(R.color.yalan_blue_dark));
            holder.setRoundViewText(R.id.roundedView,"S");
            holder.setDisplay(R.id.ll_workorder_info);
            holder.setHide(R.id.ll_operation);
            holder.setText(R.id.tv_task,mWorkOrder.getTask().getWorkName());
            holder.setText(R.id.tv_order_date,mWorkOrder.getOrderDate());
            holder.setText(R.id.tv_no,"NO."+mWorkOrder.getOrderNo());
            holder.setText(R.id.tv_substation,"【变电站】"+mWorkOrder.getSubstation().getSubstationName());
            holder.setText(R.id.tv_unit_name,mWorkOrder.getUnitName());
            holder.setText(R.id.tv_group,mWorkOrder.getDepName());
            holder.setText(R.id.tv_start_work_time, StringUtils.isNullOrEmpty(mWorkOrder.getStartWorkTime())?"未开始【开始工作】":mWorkOrder.getStartWorkTime()+"【开始工作】");
            holder.setText(R.id.tv_end_work_time, StringUtils.isNullOrEmpty(mWorkOrder.getEndWorkTime())?"未结束【结束工作】":mWorkOrder.getEndWorkTime()+"【结束工作】");
            holder.setText(R.id.tv_rule_name,mWorkOrder.getRuler().getUserName()+"【负责人】");
            for (int i=0;i<mWorkOrder.getMembers().size();i++){
                holder.addTag(R.id.tcl,mWorkOrder.getMembers().get(i).getUserName());
            }
        }else{//展示工单各步骤
            holder.setHide(R.id.ll_workorder_info);
            holder.setDisplay(R.id.ll_operation);
            holder.setText(R.id.textViewLabel,bean.getStepTitle());
            holder.setText(R.id.textViewSubLabel,bean.getStepDesc());
            switch (bean.getStateType()){//工单需进行五大步操作：1-接受 2-准备 3-现场 4-总结 5-确认，每一步状态又分3个状态：1-未进行 2-正在进行 3执行完毕
                case 1://接受
                    switch (bean.getState()){
                        //case 1情况，接受未进行状态不会出现
                        case 2://当前应该执行
                            holder.setDisplay(R.id.operation);
                            holder.setHide(R.id.history);
                            holder.setText(R.id.operation,"接受工单");
                            holder.setRoundViewCheck(R.id.roundedView,false);
                            holder.setRoundViewBackgroundColor(R.id.roundedView, UIUtils.getColor(R.color.yalan_red));
                            holder.setRoundViewText(R.id.roundedView,String.valueOf(position));
                            //除班长外的所有成员，都需要进行接受工单的操作
                            if(mWorkOrder.getMonitor().getUserId().equals(SPUtil.getInstance().getUid())){
                                holder.setUnDisplay(R.id.operation);
                                holder.setOnClickListener(R.id.history,null);
                                holder.setOnClickListener(R.id.operation,null);
                            }else{
                                holder.setDisplay(R.id.operation);
                                holder.setOnClickListener(R.id.history,null);
                                holder.setOnClickListener(R.id.operation, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        //调取接口进行工单的接收，提交工单id，以及人员id
                                        mSubmitReceivePresenter.submitReceive(mWorkOrder.getOrderNo());
                                    }
                                });
                            }
                            break;
                        case 3://2-执行完毕的
                            holder.setText(R.id.tv_finishTime,mWorkOrder.getReceiveTime()+"【完成】");
                            holder.setText(R.id.operation,"接受工单");
                            holder.setRoundViewCheck(R.id.roundedView,true);
                            holder.setRoundViewBackgroundColor(R.id.roundedView, UIUtils.getColor(R.color.colorPrimary));
                            holder.setHide(R.id.operation);
                            holder.setDisplay(R.id.history);
                            holder.setOnClickListener(R.id.operation,null);
                            holder.setOnClickListener(R.id.history, new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    //该工单已被接收：表明当值负责人已经接收了该工单，点击可以查看各成员接收的历史时间
                                    //注意：只有当值负责人接收了该工单，工单的接收状态才可以算通过，其他成员接收与否不做硬性要求。
                                    //查看历史时间显示所有已接受的人员实际的接收时间
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("work_order", mWorkOrder);
                                    readyGo(holder.itemView.getContext(),ReceiveHistoryActivity.class,bundle);
                                }
                            });
                            break;
                    }
                    break;
                case 2://1-准备
                    switch (bean.getState()){
                        case 1://0-未进行
                            holder.setText(R.id.operation,"准备工作");
                            holder.setRoundViewCheck(R.id.roundedView,false);
                            holder.setRoundViewBackgroundColor(R.id.roundedView, UIUtils.getColor(R.color.circle_color_default_gray));
                            holder.setRoundViewText(R.id.roundedView,String.valueOf(position));
                            holder.setOnClickListener(R.id.operation,null);
                            holder.setOnClickListener(R.id.history,null);
                            holder.setUnDisplay(R.id.operation);
                            holder.setUnDisplay(R.id.history);
                            break;
                        case 2://1-当前应该执行
                            holder.setText(R.id.operation,"准备工作");
                            holder.setHide(R.id.history);
                            holder.setRoundViewCheck(R.id.roundedView,false);
                            holder.setRoundViewBackgroundColor(R.id.roundedView, UIUtils.getColor(R.color.yalan_red));
                            holder.setRoundViewText(R.id.roundedView,String.valueOf(position));
                            //只有当值负责人可进行准备工作的具体操作
                            if(mWorkOrder.getMonitor().getUserId().equals(SPUtil.getInstance().getUid())||!(mWorkOrder.getRuler().getUserId().equals(SPUtil.getInstance().getUid()))){
                                holder.setUnDisplay(R.id.operation);
                                holder.setOnClickListener(R.id.history,null);
                                holder.setOnClickListener(R.id.operation,null);
                            }else{
                                holder.setDisplay(R.id.operation);
                                holder.setOnClickListener(R.id.history,null);
                                holder.setOnClickListener(R.id.operation, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Bundle bundle = new Bundle();
                                        bundle.putSerializable("work_order", mWorkOrder);
                                        readyGo(holder.itemView.getContext(),PrepareProcedureActivity.class,bundle);
                                    }
                                });
                            }
                            break;
                        case 3://2-执行完毕的
                            holder.setText(R.id.tv_finishTime,mWorkOrder.getPrepareFinishTime()+"【完成】");
                            holder.setText(R.id.operation,"准备工作");
                            holder.setRoundViewCheck(R.id.roundedView,true);
                            holder.setRoundViewBackgroundColor(R.id.roundedView, UIUtils.getColor(R.color.colorPrimary));
                            holder.setUnDisplay(R.id.operation);
                            holder.setDisplay(R.id.history);
                            holder.setOnClickListener(R.id.operation,null);
                            holder.setOnClickListener(R.id.history, new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("work_order", mWorkOrder);
                                    readyGo(holder.itemView.getContext(),PrepareHistoryActivity.class,bundle);
                                }
                            });
                    }
                    break;
                case 3://2-现场
                    switch (bean.getState()){
                        case 1://0-未进行
                            holder.setText(R.id.operation,"现场工作");
                            holder.setRoundViewCheck(R.id.roundedView,false);
                            holder.setRoundViewBackgroundColor(R.id.roundedView, UIUtils.getColor(R.color.circle_color_default_gray));
                            holder.setRoundViewText(R.id.roundedView,String.valueOf(position));
                            holder.setOnClickListener(R.id.operation,null);
                            holder.setOnClickListener(R.id.history,null);
                            holder.setUnDisplay(R.id.operation);
                            holder.setHide(R.id.history);
                            break;
                        case 2://1-当前应该执行
                            holder.setText(R.id.operation,"现场工作");
                            holder.setHide(R.id.history);
                            holder.setRoundViewCheck(R.id.roundedView,false);
                            holder.setRoundViewBackgroundColor(R.id.roundedView, UIUtils.getColor(R.color.yalan_red));
                            holder.setRoundViewText(R.id.roundedView,String.valueOf(position));
                            //只有当值负责人可进行现场工作的具体操作
                            if(mWorkOrder.getMonitor().getUserId().equals(SPUtil.getInstance().getUid())||!(mWorkOrder.getRuler().getUserId().equals(SPUtil.getInstance().getUid()))){
                                holder.setUnDisplay(R.id.operation);
                                holder.setOnClickListener(R.id.history,null);
                                holder.setOnClickListener(R.id.operation,null);
                            }else{
                                holder.setDisplay(R.id.operation);
                                holder.setOnClickListener(R.id.history,null);
                                holder.setOnClickListener(R.id.operation, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Bundle bundle = new Bundle();
                                        bundle.putSerializable("work_order", mWorkOrder);
                                        readyGo(holder.itemView.getContext(),FieldWorkActivity.class,bundle);
                                    }
                                });
                            }
                            break;
                        case 3://2-执行完毕的
                            holder.setText(R.id.tv_finishTime,mWorkOrder.getFieldFinishTime()+"【完成】");
                            holder.setText(R.id.operation,"现场工作");
                            holder.setRoundViewCheck(R.id.roundedView,true);
                            holder.setRoundViewBackgroundColor(R.id.roundedView, UIUtils.getColor(R.color.colorPrimary));
                            holder.setUnDisplay(R.id.operation);
                            holder.setDisplay(R.id.history);
                            holder.setOnClickListener(R.id.operation,null);
                            holder.setOnClickListener(R.id.history, new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            });
                            break;
                    }
                    break;
                case 4://3-总结
                    switch (bean.getState()){
                        case 1://0-未进行
                            holder.setText(R.id.operation,"总结工作");
                            holder.setRoundViewCheck(R.id.roundedView,false);
                            holder.setRoundViewBackgroundColor(R.id.roundedView, UIUtils.getColor(R.color.circle_color_default_gray));
                            holder.setRoundViewText(R.id.roundedView,String.valueOf(position));
                            holder.setOnClickListener(R.id.operation,null);
                            holder.setOnClickListener(R.id.history,null);
                            holder.setUnDisplay(R.id.operation);
                            holder.setHide(R.id.history);
                            break;
                        case 2://1-当前应该执行
                            holder.setText(R.id.operation,"总结工作");
                            holder.setHide(R.id.history);
                            holder.setRoundViewCheck(R.id.roundedView,false);
                            holder.setRoundViewBackgroundColor(R.id.roundedView, UIUtils.getColor(R.color.yalan_red));
                            holder.setRoundViewText(R.id.roundedView,String.valueOf(position));
                            //只有当值负责人可进行总结工作的具体操作
                            if(mWorkOrder.getMonitor().getUserId().equals(SPUtil.getInstance().getUid())||!(mWorkOrder.getRuler().getUserId().equals(SPUtil.getInstance().getUid()))){
                                holder.setUnDisplay(R.id.operation);
                                holder.setOnClickListener(R.id.history,null);
                                holder.setOnClickListener(R.id.operation,null);
                            }else{
                                holder.setDisplay(R.id.operation);
                                holder.setOnClickListener(R.id.history,null);
                                holder.setOnClickListener(R.id.operation, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Bundle bundle = new Bundle();
                                        bundle.putSerializable("work_order", mWorkOrder);
                                        readyGo(holder.itemView.getContext(),SummaryWorkActivity.class,bundle);
                                    }
                                });
                            }
                            break;
                        case 3://2-执行完毕的
                            holder.setText(R.id.tv_finishTime,mWorkOrder.getSummaryFinishTime()+"【完成】");
                            holder.setText(R.id.operation,"总结工作");
                            holder.setRoundViewCheck(R.id.roundedView,true);
                            holder.setRoundViewBackgroundColor(R.id.roundedView, UIUtils.getColor(R.color.colorPrimary));
                            holder.setUnDisplay(R.id.operation);
                            holder.setDisplay(R.id.history);
                            holder.setOnClickListener(R.id.operation,null);
                            holder.setOnClickListener(R.id.history, new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            });
                            break;
                    }
                    break;
                case 5://4-确认
                    switch (bean.getState()){
                        case 1://0-未进行
                            holder.setText(R.id.operation,"确认工作");
                            holder.setRoundViewCheck(R.id.roundedView,false);
                            holder.setRoundViewBackgroundColor(R.id.roundedView, UIUtils.getColor(R.color.circle_color_default_gray));
                            holder.setRoundViewText(R.id.roundedView,String.valueOf(position));
                            holder.setOnClickListener(R.id.operation,null);
                            holder.setOnClickListener(R.id.history,null);
                            holder.setUnDisplay(R.id.operation);
                            holder.setHide(R.id.history);
                            break;
                        case 2://1-当前应该执行
                            holder.setText(R.id.operation,"确认工作");
                            holder.setHide(R.id.history);
                            holder.setRoundViewCheck(R.id.roundedView,false);
                            holder.setRoundViewBackgroundColor(R.id.roundedView, UIUtils.getColor(R.color.yalan_red));
                            holder.setRoundViewText(R.id.roundedView,String.valueOf(position));
                            holder.setOnClickListener(R.id.history,null);
                            //只有班长才能执行确认工作的操作
                            if(mWorkOrder.getMonitor().getUserId().equals(SPUtil.getInstance().getUid())){
                                holder.setDisplay(R.id.operation);
                                holder.setOnClickListener(R.id.operation, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        EventBus.getDefault().post(new EventCenter(ConstantValue.EVENT_CONFIRE_WORK_SUCCESS,"2017-09-02 15:00"));
                                    }
                                });
                            }else{
                                holder.setUnDisplay(R.id.operation);
                                holder.setOnClickListener(R.id.operation,null);
                            }
                            break;
                        case 3://2-执行完毕的
                            holder.setText(R.id.tv_finishTime,mWorkOrder.getConfirmFinishTime()+"【完成】");
                            holder.setHide(R.id.history);
                            holder.setText(R.id.operation,"确认工作");
                            holder.setRoundViewCheck(R.id.roundedView,true);
                            holder.setRoundViewBackgroundColor(R.id.roundedView, UIUtils.getColor(R.color.colorPrimary));
                            holder.setUnDisplay(R.id.operation);
                            holder.setOnClickListener(R.id.operation,null);
                            holder.setOnClickListener(R.id.history,null);
                            break;
                    }
                    break;
            }
        }
    }


    @Override
    public int getItemLayoutID(int viewType) {
        return R.layout.item_work_state;
    }

    private void readyGo(Context context,Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(context, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

}
