package com.richsoft.maintenace.workorder.prepare.ui.adapter;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.richsoft.maintenace.bean.workorder.PrepareProcedureBean;
import com.richsoft.maintenace.workorder.prepare.ui.PrepareRequiresHistoryFragment;
import java.util.List;


public class PrepareHistoryTabPagerAdapter extends FragmentStatePagerAdapter {

    private List<String> mTitles;
    private List<PrepareProcedureBean> mData;
    private int mChildCount = 0;

    public PrepareHistoryTabPagerAdapter(FragmentManager fm, List<String> titles,List<PrepareProcedureBean> data) {
        super(fm);
        mTitles = titles;
        mData=data;
    }

    public void addAll(List<String> titles) {
        this.mTitles = titles;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return PrepareRequiresHistoryFragment.newInstance(mData.get(position));
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
