package com.richsoft.maintenace.workorder.workorderlist.ui.adapter;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.richsoft.maintenace.workorder.workorderlist.ui.WorkOrderListFragment;

import java.util.List;


public class WorkOrdersTabPagerAdapter extends FragmentStatePagerAdapter {

    private List<String> mTitles;
    private int mChildCount = 0;

    public WorkOrdersTabPagerAdapter(FragmentManager fm, List<String> titles) {
        super(fm);
        mTitles = titles;
    }

    public void addAll(List<String> titles) {
        this.mTitles = titles;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return WorkOrderListFragment.newInstance(position);
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
