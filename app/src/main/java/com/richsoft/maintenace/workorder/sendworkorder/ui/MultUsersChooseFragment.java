package com.richsoft.maintenace.workorder.sendworkorder.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.animation.AnimationUtils;
import com.richsoft.maintenace.R;
import com.richsoft.maintenace.bean.user.UserBean;
import com.richsoft.maintenace.bean.workorder.WorkOrderBean;
import com.richsoft.maintenace.common.ConstantValue;
import com.richsoft.maintenace.workorder.sendworkorder.presenter.LoadUsersPresenter;
import com.richsoft.maintenace.workorder.sendworkorder.presenter.LoadUsersPresenterImpl;
import com.richsoft.maintenace.workorder.sendworkorder.view.LoadUsersView;
import com.richsoft.maintenace.workorder.sendworkorder.ui.adapter.UserMultChooseAdapter;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import ren.solid.library.activity.base.BaseActivity;
import ren.solid.library.fragment.base.BaseFragment;
import ren.solid.library.listener.OnNavigationBarListener;
import ren.solid.library.manager.EventCenter;
import ren.solid.library.utils.LogUtils;
import ren.solid.library.widget.StatusViewLayout;

/**
 * 作者：e430 on 2017/2/17 13:30
 * <p>派发工单采集成员id集合
 * 邮箱：chengzehao@163.com
 */

public class MultUsersChooseFragment extends BaseFragment implements Step,LoadUsersView {
    private RecyclerView mRecyclerView;
    private StatusViewLayout status_view;
    private LinearLayoutManager mLayoutManager;
    private WorkOrderBean mWorkOrderBean;
    private UserMultChooseAdapter mAdapter;
    private boolean isCanNext = false;
    private LoadUsersPresenter mPresenter;
    @Nullable
    private OnNavigationBarListener onNavigationBarListener;

    public static MultUsersChooseFragment newInstance(WorkOrderBean data) {
        Bundle args = new Bundle();
        MultUsersChooseFragment fragment = new MultUsersChooseFragment();
        args.putSerializable("data",data);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWorkOrderBean= (WorkOrderBean) getArguments().getSerializable("data");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnNavigationBarListener) {
            onNavigationBarListener = (OnNavigationBarListener) context;
        }
    }

    @Override
    protected boolean isBindEventBusHere() {
        return true;
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {
        int eventCode = eventCenter.getEventCode();
        switch (eventCode) {
            case ConstantValue.EVENT_MEMBER_CHOOSE:
                updateNavigationBar();
                break;
            case ConstantValue.EVENT_REFRESH_MEMBERS:
                if(mPresenter!=null){
                    LogUtils.i("zhoul","刷新成员列表");
                    status_view.showLoading();
                    if(mWorkOrderBean.getMembers()!=null){
                        mWorkOrderBean.getMembers().clear();
                    }
                    mPresenter.loadUsers();
                }
                break;
        }
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_recyclerview;
    }

    @Override
    protected void setUpView() {
        mRecyclerView=$(R.id.recycle_view);
        status_view=$(R.id.status_view);
        status_view.showLoading();
        //初始化列表控件
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        ((SimpleItemAnimator) mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        mAdapter = new UserMultChooseAdapter(getActivity(),new ArrayList<UserBean>(),mWorkOrderBean);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void setUpData() {
        mPresenter=new LoadUsersPresenterImpl((BaseActivity) getActivity(),this);
        mPresenter.loadUsers();
    }

    @Override
    public VerificationError verifyStep() {
        return isCanNext ? null : new VerificationError("请您选择成员");
    }

    @Override
    public void onSelected() {
        updateNavigationBar();
    }

    @Override
    public void onError(@NonNull VerificationError error) {
        mRecyclerView.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.shake_error));
    }

    public void updateNavigationBar() {
        if (onNavigationBarListener != null) {
            if(mWorkOrderBean.getMembers()==null||mWorkOrderBean.getMembers().size()==0){
                isCanNext=false;
            }else{
                isCanNext=true;
            }
            onNavigationBarListener.onChangeEndButtonsEnabled(isCanNext);
        }
    }
    @Override
    public void onLoadUsersSuccess(List<UserBean> data) {
        //去除班长
        Iterator<UserBean> it = data.iterator();
        while (it.hasNext()) {
            UserBean user=it.next();
            if (user.getUserId().equals("1")) {
                it.remove();
            }else if(user.getUserId().equals(mWorkOrderBean.getRuler().getUserId())){
                it.remove();
            }
        }
        mAdapter.addAll(data,true);
        if(mAdapter.getDatas().size()==0){
            status_view.showEmpty();
        }else{
            status_view.showContent();
        }
    }

    @Override
    public void onLoadUsersFail(Exception e) {
        status_view.showError(e.getMessage());
    }
}
