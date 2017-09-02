package com.richsoft.maintenace.workorder.prepare.ui.adapter;

import android.content.Context;

import com.richsoft.maintenace.R;
import com.richsoft.maintenace.bean.workorder.PrepareProcedureRequirementBean;
import java.util.List;
import ren.solid.library.adapter.SolidRVBaseAdapter;
import ren.solid.library.utils.UIUtils;

/**
 * 作者：e430 on 2017/1/10 12:49
 * <p>
 * 邮箱：chengzehao@163.com
 */

public class PrepareRequiresHistoryAdapter extends SolidRVBaseAdapter<PrepareProcedureRequirementBean> {

    public PrepareRequiresHistoryAdapter(Context context, List<PrepareProcedureRequirementBean> beans) {
        super(context, beans);
    }

    @Override
    protected void onBindDataToView(final SolidCommonViewHolder holder, final PrepareProcedureRequirementBean bean, final int position) {
        holder.setRoundViewCheck(R.id.roundedView,false);
        holder.setRoundViewBackgroundColor(R.id.roundedView, UIUtils.getColor(R.color.colorPrimary));
        holder.setRoundViewText(R.id.roundedView,String.valueOf(position+1));
        holder.setText(R.id.tv_title,bean.title);
        String s="";
        switch (bean.type){
            case 0:
                s="已核实通过";
                break;
            case 1:
                s="已指定："+bean.userName;
                break;
            case 2:
                s="温度记录："+bean.temperature+"℃";
                break;
            case 3:
                s="湿度记录："+bean.humidity+"%";
                break;
            case 4:
                s="已指定："+bean.userName;
                break;
        }
        holder.setText(R.id.tv_content,s);
    }


    @Override
    public int getItemLayoutID(int viewType) {
        return R.layout.item_prepare_history;
    }

}
