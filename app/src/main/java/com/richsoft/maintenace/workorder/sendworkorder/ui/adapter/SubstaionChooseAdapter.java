package com.richsoft.maintenace.workorder.sendworkorder.ui.adapter;

import android.content.Context;
import android.view.View;
import com.richsoft.maintenace.R;
import com.richsoft.maintenace.bean.workorder.SubstationBean;
import com.richsoft.maintenace.bean.workorder.WorkOrderBean;
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

public class SubstaionChooseAdapter extends SolidRVBaseAdapter<SubstationBean> {

    private WorkOrderBean mWorkOrderBean;

    public SubstaionChooseAdapter(Context context, List<SubstationBean> beans,WorkOrderBean workOrderBean) {
        super(context, beans);
        this.mWorkOrderBean=workOrderBean;
    }

    @Override
    protected void onBindDataToView(final SolidCommonViewHolder holder, final SubstationBean bean, final int position) {
        holder.setText(R.id.tv_level,"【"+bean.getSubstationLevel()+"】");
        holder.setText(R.id.tv_name,bean.getSubstationName());
        holder.setImage(R.id.img_option,bean.isChooseTag()?R.mipmap.icon_choice_2:R.mipmap.icon_choice);
        holder.setOnClickListener(R.id.ll_root, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mWorkOrderBean.getSubstation().setSubstationID(bean.getSubstationID());
                for (int i=0;i<getDatas().size();i++){
                    getDatas().get(i).setChooseTag(position==i);
                }
                notifyDataSetChanged();
                EventBus.getDefault().post(new EventCenter(ConstantValue.EVENT_SUBSTAION_CHOOSE));
            }
        });
    }


    @Override
    public int getItemLayoutID(int viewType) {
        return R.layout.item_choose;
    }

}
