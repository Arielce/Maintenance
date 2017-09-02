package com.richsoft.maintenace.workorder.workorderdetail.ui;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.richsoft.maintenace.R;
import com.richsoft.maintenace.bean.workorder.VideoBean;
import com.richsoft.maintenace.bean.workorder.WorkOrderBean;
import com.richsoft.maintenace.bean.workorder.WorkOrderStateBean;
import com.richsoft.maintenace.common.ConstantValue;
import com.richsoft.maintenace.workorder.workorderdetail.presenter.LoadVideoPresenter;
import com.richsoft.maintenace.workorder.workorderdetail.presenter.LoadVideoPresenterImpl;
import com.richsoft.maintenace.workorder.workorderdetail.presenter.SubmitReceivePresenter;
import com.richsoft.maintenace.workorder.workorderdetail.presenter.SubmitReceivePresenterImpl;
import com.richsoft.maintenace.workorder.workorderdetail.view.LoadVideoView;
import com.richsoft.maintenace.workorder.workorderdetail.ui.adapter.WorkOrderStatesAdapter;
import com.richsoft.maintenace.workorder.workorderdetail.view.SubmitReceiveView;
import org.greenrobot.eventbus.EventBus;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import ren.solid.library.activity.VideoViewActivity;
import ren.solid.library.activity.base.BaseActivity;
import ren.solid.library.manager.EventCenter;
import ren.solid.library.net.NetUtils;
import ren.solid.library.utils.StringUtils;

public class WorkOrderDetailActivity extends BaseActivity implements LoadVideoView,SubmitReceiveView {
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.recycle_view)
    RecyclerView mRecyclerView;

    private WorkOrderBean mWorkOrderBean;
    private LoadVideoPresenter mLoadVideoPresenter;
    private SubmitReceivePresenter mSubmitReceivePresenter;
    private LinearLayoutManager mLayoutManager;
    private WorkOrderStatesAdapter mAdapter;
    private List<WorkOrderStateBean> mWorkStateBeans;

    @Override
    protected boolean toggleOverridePendingTransition() {
        return true;
    }

    @Override
    protected TransitionMode getOverridePendingTransitionMode() {
        return TransitionMode.FADE;
    }

    @Override
    protected void initViewsAndEvents() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.work_order_detail);
        mSubmitReceivePresenter=new SubmitReceivePresenterImpl(this,this);
        initRecyclerview();
        refreshListByWorkOrderState(mWorkOrderBean.getWorkState());
    }

    private void initRecyclerview() {
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        ((SimpleItemAnimator) mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        mWorkStateBeans=new ArrayList<>();
        mWorkStateBeans.add(new WorkOrderStateBean());//该条目用于展示WorkOrderBean相关基础信息
        mWorkStateBeans.add(new WorkOrderStateBean(0,1,"接受工单","接受该工单，班长将会收到您的反馈。"));
        mWorkStateBeans.add(new WorkOrderStateBean(0,2,"准备工作","负责人进行相关人员指定、风险措施预估以及交叉作业预估"));
        mWorkStateBeans.add(new WorkOrderStateBean(0,3,"现场工作","针对相应任务进行现场数据采集。"));
        mWorkStateBeans.add(new WorkOrderStateBean(0,4,"总结工作","负责人针对所完成工单进行签名、以及工作评价。"));
        mWorkStateBeans.add(new WorkOrderStateBean(0,5,"确认工单","班长将对所完成工单进行审核确认。"));
        mAdapter = new WorkOrderStatesAdapter(this,mWorkStateBeans,mWorkOrderBean,mSubmitReceivePresenter);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void refreshListByWorkOrderState(String workState) {
        //1-待办、2-执行、3-准备工作已完成、4-开始工作已完成,5-总结工作已完成,6-工作已确认
        if(workState.equals("1")){
            mWorkStateBeans.get(1).setState(2);
            mWorkStateBeans.get(2).setState(1);
            mWorkStateBeans.get(3).setState(1);
            mWorkStateBeans.get(4).setState(1);
            mWorkStateBeans.get(5).setState(1);
        }else if(workState.equals("2")){
            mWorkStateBeans.get(1).setState(3);
            mWorkStateBeans.get(2).setState(2);
            mWorkStateBeans.get(3).setState(1);
            mWorkStateBeans.get(4).setState(1);
            mWorkStateBeans.get(5).setState(1);
        }else if(workState.equals("3")){
            mWorkStateBeans.get(1).setState(3);
            mWorkStateBeans.get(2).setState(3);
            mWorkStateBeans.get(3).setState(2);
            mWorkStateBeans.get(4).setState(1);
            mWorkStateBeans.get(5).setState(1);
        }else if(workState.equals("4")){
            mWorkStateBeans.get(1).setState(3);
            mWorkStateBeans.get(2).setState(3);
            mWorkStateBeans.get(3).setState(3);
            mWorkStateBeans.get(4).setState(2);
            mWorkStateBeans.get(5).setState(1);
        }else if(workState.equals("5")){
            mWorkStateBeans.get(1).setState(3);
            mWorkStateBeans.get(2).setState(3);
            mWorkStateBeans.get(3).setState(3);
            mWorkStateBeans.get(4).setState(3);
            mWorkStateBeans.get(5).setState(2);
        }else if(workState.equals("6")){
            mWorkStateBeans.get(1).setState(3);
            mWorkStateBeans.get(2).setState(3);
            mWorkStateBeans.get(3).setState(3);
            mWorkStateBeans.get(4).setState(3);
            mWorkStateBeans.get(5).setState(3);
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        mWorkOrderBean = (WorkOrderBean) extras.getSerializable("workorder");
    }

    @Override
    protected boolean isBindEventBusHere() {
        return true;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_work_order_detail;
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {
        int eventCode = eventCenter.getEventCode();
        String time= (String) eventCenter.getData();
        switch (eventCode) {
            case ConstantValue.EVENT_RECEIVE_WORK_SUCCESS:
                mWorkOrderBean.setReceiveTime(time);
                refreshListByWorkOrderState("2");
                moveToPosition(mLayoutManager,mRecyclerView,2);
                break;
            case ConstantValue.EVENT_PREPARE_WORK_SUCCESS:
                mWorkOrderBean.setPrepareFinishTime(time);
                refreshListByWorkOrderState("3");
                moveToPosition(mLayoutManager,mRecyclerView,3);
                break;
            case ConstantValue.EVENT_FIELD_WORK_SUCCESS:
                mWorkOrderBean.setFieldFinishTime(time);
                refreshListByWorkOrderState("4");
                moveToPosition(mLayoutManager,mRecyclerView,4);
                break;
            case ConstantValue.EVENT_SUMMARY_WORK_SUCCESS:
                mWorkOrderBean.setSummaryFinishTime(time);
                refreshListByWorkOrderState("5");
                moveToPosition(mLayoutManager,mRecyclerView,5);
                break;
            case ConstantValue.EVENT_CONFIRE_WORK_SUCCESS:
                mWorkOrderBean.setConfirmFinishTime(time);
                refreshListByWorkOrderState("6");
                moveToPosition(mLayoutManager,mRecyclerView,6);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_guidance, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.document:
                readyGo(GuidanceDocActivity.class);
                break;
            case R.id.video:
                showProgressDialog("正在加载视频","请您稍候..",true);
                if(mLoadVideoPresenter==null){
                    mLoadVideoPresenter=new LoadVideoPresenterImpl(this,this);
                }
                mLoadVideoPresenter.loadVideo(mWorkOrderBean.getTask().getWorkID());
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLoadVideoSuccess(VideoBean data) {
        dismissProgressDialog();
        if(data==null|| StringUtils.isNullOrEmpty(data.getFilePath())){
            showDialog(0,"加载视频失败","服务器异常！请您稍候再试！",getString(R.string.known),true);
        }else{
            VideoViewActivity.openActivity(mContext,mWorkOrderBean.getTask().getWorkName(),data.getFilePath(),"");
        }
    }

    @Override
    public void onLoadVideoFail(Exception e) {
        dismissProgressDialog();
        showDialog(0,"加载视频失败","服务器忙！请您稍候再试！",getString(R.string.known),true);
    }

    /**
     * RecyclerView 移动到当前位置，
     *
     * @param manager   设置RecyclerView对应的manager
     * @param mRecyclerView  当前的RecyclerView
     * @param n  要跳转的位置
     */
    public void moveToPosition(LinearLayoutManager manager, RecyclerView mRecyclerView, int n) {
        int firstItem = manager.findFirstVisibleItemPosition();
        int lastItem = manager.findLastVisibleItemPosition();
        if (n <= firstItem) {
            mRecyclerView.scrollToPosition(n);
        } else if (n <= lastItem) {
            int top = mRecyclerView.getChildAt(n - firstItem).getTop();
            mRecyclerView.scrollBy(0, top);
        } else {
            mRecyclerView.scrollToPosition(n);
        }
    }

    @Override
    public void onSubmitReceiveSuccess(String data) {
        EventBus.getDefault().post(new EventCenter(ConstantValue.EVENT_RECEIVE_WORK_SUCCESS,data));
    }

    @Override
    public void onSubmitReceiveFail(Exception e) {
        showMsgShortTime(e.getMessage());
    }
}
