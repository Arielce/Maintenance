package ren.solid.library.fragment;

import android.widget.TextView;

import ren.solid.library.R;
import ren.solid.library.fragment.base.BaseFragment;
import ren.solid.library.manager.EventCenter;

/**
 * Created by _SOLID
 * Date:2016/3/30
 * Time:21:43
 */
public class StringFragment extends BaseFragment {
    private String mText;
    private TextView mTvText;

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_string;
    }

    @Override
    protected void setUpView() {
        mText = getArguments().getString("text");
        mTvText = $(R.id.tv_text);

    }

    @Override
    protected void setUpData() {
        if (!mText.equals(""))
            mTvText.setText(mText);
        else
            mTvText.setText("暂无信息");
    }
}
