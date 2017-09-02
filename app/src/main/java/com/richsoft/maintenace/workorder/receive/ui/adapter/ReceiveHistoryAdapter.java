package com.richsoft.maintenace.workorder.receive.ui.adapter;

import android.content.Context;
import com.richsoft.maintenace.R;
import com.richsoft.maintenace.bean.workorder.ReceiveWorkBean;
import java.util.List;
import ren.solid.library.adapter.SolidRVBaseAdapter;
import ren.solid.library.utils.UIUtils;

/**
 * 作者：e430 on 2017/1/10 12:49
 * <p>
 * 邮箱：chengzehao@163.com
 */

public class ReceiveHistoryAdapter extends SolidRVBaseAdapter<ReceiveWorkBean> {

    public ReceiveHistoryAdapter(Context context, List<ReceiveWorkBean> beans) {
        super(context, beans);
    }

    @Override
    protected void onBindDataToView(final SolidCommonViewHolder holder, final ReceiveWorkBean bean, final int position) {
        holder.setText(R.id.tv_time,bean.getReceiveTime());
        holder.setText(R.id.tv_user_name,bean.getUser().getUserName());
        holder.setRoundViewCheck(R.id.roundedView,false);
        holder.setRoundViewBackgroundColor(R.id.roundedView, UIUtils.getColor(R.color.colorPrimary));
        holder.setRoundViewText(R.id.roundedView,String.valueOf(position+1));
    }


    @Override
    public int getItemLayoutID(int viewType) {
        return R.layout.item_receive_history;
    }

}
