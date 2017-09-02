package com.richsoft.maintenace.workorder.sendworkorder.ui.adapter;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.richsoft.maintenace.bean.workorder.WorkOrderBean;
import com.richsoft.maintenace.bean.workorder.WorkTaskListBean;
import com.richsoft.maintenace.workorder.sendworkorder.ui.TaskChooseFragment;
import java.util.ArrayList;
import java.util.List;


public class TasksTabPagerAdapter extends FragmentStatePagerAdapter {

    private static List<String> mTitles;
    private List<WorkTaskListBean> mWorkTaskListBeans;
    private WorkOrderBean mWorkOrderBean;
    private int mChildCount = 0;

    public TasksTabPagerAdapter(FragmentManager fm, WorkOrderBean workOrderBean,List<String> titles, List<WorkTaskListBean> workTaskListBeans) {
        super(fm);
        mTitles = titles;
        mWorkTaskListBeans=workTaskListBeans;
        mWorkOrderBean=workOrderBean;
    }

    public void addAll(List<String> titles) {
        this.mTitles = titles;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return TaskChooseFragment.newInstance(mWorkOrderBean,position, (ArrayList<WorkTaskListBean>) mWorkTaskListBeans);
    }

    @Override
    public void notifyDataSetChanged() {
        mChildCount = getCount();
        super.notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(Object object) {
        if (mChildCount > 0) {
            mChildCount--;
            return POSITION_NONE;
        }
        return super.getItemPosition(object);
    }

    @Override
    public int getCount() {
        return mTitles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }
}
