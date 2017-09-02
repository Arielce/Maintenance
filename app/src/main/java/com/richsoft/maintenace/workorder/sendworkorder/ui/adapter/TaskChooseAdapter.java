package com.richsoft.maintenace.workorder.sendworkorder.ui.adapter;

import android.content.Context;
import android.view.View;
import com.richsoft.maintenace.R;
import com.richsoft.maintenace.bean.workorder.WorkOrderBean;
import com.richsoft.maintenace.bean.workorder.WorkTaskBean;
import com.richsoft.maintenace.common.ConstantValue;
import org.greenrobot.eventbus.EventBus;
import java.util.List;
import ren.solid.library.adapter.SolidRVBaseAdapter;
import ren.solid.library.manager.EventCenter;

/**
 * 作者：e430 on 2017/1/10 12:49
 * <p>
 * 邮箱：chengzehao@163.com
 */

public class TaskChooseAdapter extends SolidRVBaseAdapter<WorkTaskBean> {

    private WorkOrderBean mWorkOrderBean;

    public TaskChooseAdapter(Context context, List<WorkTaskBean> beans, WorkOrderBean workOrderBean) {
        super(context, beans);
        this.mWorkOrderBean=workOrderBean;
    }

    @Override
    protected void onBindDataToView(final SolidCommonViewHolder holder, final WorkTaskBean bean, final int position) {
        holder.setText(R.id.tv_level,"【"+bean.getWorkType()+"】");
        holder.setText(R.id.tv_name,bean.getWorkName());
        holder.setImage(R.id.img_option,bean.isChooseTag()?R.mipmap.icon_choice_2:R.mipmap.icon_choice);
        holder.setOnClickListener(R.id.ll_root, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mWorkOrderBean.getTask().setWorkID(bean.getWorkID());
                mWorkOrderBean.getTask().setWorkType(bean.getWorkType());
                EventBus.getDefault().post(new EventCenter(ConstantValue.EVENT_TASK_CHOOSE,bean.getWorkID()));
            }
        });
    }


    @Override
    public int getItemLayoutID(int viewType) {
        return R.layout.item_choose;
    }

}
