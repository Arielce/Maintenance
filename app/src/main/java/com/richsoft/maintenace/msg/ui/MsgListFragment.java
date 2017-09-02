package com.richsoft.maintenace.msg.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import com.richsoft.maintenace.bean.msg.GanHuoDataBean;
import com.richsoft.maintenace.bean.msg.GanHuoDataImg;
import com.richsoft.maintenace.bean.msg.GanHuoDataText;
import com.richsoft.maintenace.msg.presenter.LoadMsgsPresenter;
import com.richsoft.maintenace.msg.presenter.LoadMsgsPresenterImpl;
import com.richsoft.maintenace.msg.view.MsgsView;
import java.util.List;
import me.drakeet.multitype.MultiTypeAdapter;
import ren.solid.library.activity.base.BaseActivity;
import ren.solid.library.fragment.base.AbsLazyLoadListFragment;
import ren.solid.library.manager.EventCenter;
import ren.solid.library.widget.LinearDecoration;

/**
 * Created by Administrator on 2016/12/11.
 */

public class MsgListFragment extends AbsLazyLoadListFragment implements MsgsView {

    private String mType ;
    private LoadMsgsPresenter mPresenter;

    public static MsgListFragment newInstance(String type) {
        Bundle args = new Bundle();
        MsgListFragment fragment = new MsgListFragment();
        args.putString("type", type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mType = getArguments().getString("type");
        mPresenter=new LoadMsgsPresenterImpl((BaseActivity) getActivity(),this);
    }

    @Override
    protected void customConfig() {
        addItemDecoration(new LinearDecoration(getContext(), RecyclerView.VERTICAL, 2));
    }

    @Override
    protected int getInitPageIndex() {
        return 1;
    }

    @Override
    public void loadData(final int pageIndex) {
        mPresenter.loadMsgs(pageIndex,mType);
    }

    @Override
    public void onLoadMsgsSuccess(int pageIndex,List<GanHuoDataBean> data) {
        onDataSuccessReceived(pageIndex, data);
    }

    @Override
    public void onLoadMsgsFail(Exception e) {
        showError(e);
    }

    @Override
    protected MultiTypeAdapter getAdapter() {
        return new MultiTypeAdapter(getItems()) {
            @NonNull
            @Override
            public Class onFlattenClass(@NonNull Object item) {
                if (item instanceof GanHuoDataBean) {
                    GanHuoDataBean bean = (GanHuoDataBean) item;
                    if (bean.getImages() != null && bean.getImages().size() > 0) {
                        return GanHuoDataImg.class;
                    } else {
                        return GanHuoDataText.class;
                    }
                }
                return super.onFlattenClass(item);
            }
        };
    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {

    }

}
