package com.richsoft.maintenace.workorder.filed.ui.adapter;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.richsoft.maintenace.bean.workorder.SwitchRoomBean;
import com.richsoft.maintenace.workorder.filed.ui.FieldRequireItemSwitchCabinetFragment;
import java.util.List;


public class SwitchRoomTabPagerAdapter extends FragmentStatePagerAdapter {

    private static List<String> mTitles;
    private List<SwitchRoomBean> mSwitchRooms;
    private int mChildCount = 0;

    public SwitchRoomTabPagerAdapter(FragmentManager fm, List<String> titles,List<SwitchRoomBean> switchRooms) {
        super(fm);
        mTitles = titles;
        mSwitchRooms=switchRooms;
    }

    public void addAll(List<String> titles) {
        this.mTitles = titles;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return FieldRequireItemSwitchCabinetFragment.newInstance(mSwitchRooms.get(position));
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
