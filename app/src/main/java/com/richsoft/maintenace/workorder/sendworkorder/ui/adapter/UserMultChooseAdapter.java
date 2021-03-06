package com.richsoft.maintenace.workorder.sendworkorder.ui.adapter;

import android.content.Context;
import android.view.View;
import com.richsoft.maintenace.R;
import com.richsoft.maintenace.bean.user.UserBean;
import com.richsoft.maintenace.bean.workorder.WorkOrderBean;
import com.richsoft.maintenace.common.ConstantValue;
import org.greenrobot.eventbus.EventBus;
import java.util.Iterator;
import java.util.List;
import ren.solid.library.adapter.SolidRVBaseAdapter;
import ren.solid.library.manager.EventCenter;

/**
 * 作者：e430 on 2017/1/10 12:49
 * <p>
 * 邮箱：chengzehao@163.com
 */

public class UserMultChooseAdapter extends SolidRVBaseAdapter<UserBean> {

    private WorkOrderBean mWorkOrderBean;

    public UserMultChooseAdapter(Context context, List<UserBean> beans, WorkOrderBean workOrderBean) {
        super(context, beans);
        this.mWorkOrderBean=workOrderBean;
    }

    @Override
    protected void onBindDataToView(final SolidCommonViewHolder holder, final UserBean bean, final int position) {
        holder.setText(R.id.tv_level,"【"+bean.getPostName()+"】");
        holder.setText(R.id.tv_name,bean.getUserName());
        holder.setImage(R.id.img_option,bean.isChooseTag()?R.mipmap.icon_choice_2:R.mipmap.icon_choice);
        holder.setOnClickListener(R.id.ll_root, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bean.isChooseTag()){
                    bean.setChooseTag(false);
                    Iterator<UserBean> it = mWorkOrderBean.getMembers().iterator();
                    while(it.hasNext()){
                        if(it.next().getUserId().equals(bean.getUserId())){
                            it.remove();
                        }
                    }
                }else{
                    bean.setChooseTag(true);
                    UserBean u=new UserBean();
                    u.setUserId(bean.getUserId());
                    mWorkOrderBean.getMembers().add(u);
                }
                notifyDataSetChanged();
                EventBus.getDefault().post(new EventCenter(ConstantValue.EVENT_MEMBER_CHOOSE));
            }
        });
    }


    @Override
    public int getItemLayoutID(int viewType) {
        return R.layout.item_choose;
    }

}
