package com.richsoft.maintenace.msg.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import com.richsoft.maintenace.bean.msg.SearchResult;
import com.richsoft.maintenace.msg.presenter.SearchMsgsPresenter;
import com.richsoft.maintenace.msg.presenter.SearchMsgsPresenterImpl;
import com.richsoft.maintenace.msg.view.SearchMsgsView;
import java.util.List;
import me.drakeet.multitype.MultiTypeAdapter;
import ren.solid.library.activity.base.BaseActivity;
import ren.solid.library.fragment.base.AbsNoLazyLoadListFragment;
import ren.solid.library.manager.EventCenter;
import ren.solid.library.widget.LinearDecoration;

/**
 * Created by _SOLID
 * Date:2016/11/22
 * Time:9:56
 * Desc:
 */

public class SearchResultListFragment extends AbsNoLazyLoadListFragment implements SearchMsgsView{
    private String keyWord = "Android";
    private SearchMsgsPresenter mPresenter;

    public static SearchResultListFragment newInstance(String keyWord) {
        Bundle args = new Bundle();
        SearchResultListFragment fragment = new SearchResultListFragment();
        args.putString("keyWord", keyWord);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        keyWord=getArguments().getString("keyWord");
        mPresenter=new SearchMsgsPresenterImpl((BaseActivity) getActivity(),this);
    }

    @Override
    protected void customConfig() {
        addItemDecoration(new LinearDecoration(getContext(), RecyclerView.VERTICAL, 1));
    }

    @Override
    public void loadData(int pageIndex) {
        mPresenter.searchMsgs(pageIndex,keyWord);
    }

    @Override
    protected int getInitPageIndex() {
        return 1;
    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {

    }

    @Override
    public void onSearchMsgsSuccess(int pageIndex, List<SearchResult> data) {
        onDataSuccessReceived(pageIndex,data);
    }

    @Override
    public void onSearchMsgsFail(Exception e) {
        showError(e);
    }

    @Override
    protected MultiTypeAdapter getAdapter() {
        return new MultiTypeAdapter(getItems()) {
            @NonNull
            @Override
            public Class onFlattenClass(@NonNull Object item) {
                if (item instanceof SearchResult) {
                    return SearchResult.class;
                }
                return super.onFlattenClass(item);
            }
        };
    }
}
