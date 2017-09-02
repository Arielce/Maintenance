package com.richsoft.maintenace.workorder.workorderdetail.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import com.richsoft.maintenace.R;
import com.richsoft.maintenace.bean.workorder.DocumentBean;
import com.richsoft.maintenace.common.ConstantValue;
import com.richsoft.maintenace.db.dao.DocDao;
import com.richsoft.maintenace.workorder.workorderdetail.presenter.LoadDocsPresenter;
import com.richsoft.maintenace.workorder.workorderdetail.presenter.LoadDocsPresenterImpl;
import com.richsoft.maintenace.workorder.workorderdetail.view.LoadDocsView;
import com.richsoft.maintenace.workorder.workorderdetail.ui.adapter.DocsAdapter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import ren.solid.library.activity.base.BaseActivity;
import ren.solid.library.fragment.base.BaseFragment;
import ren.solid.library.manager.EventCenter;
import ren.solid.library.manager.ThreadManager;
import ren.solid.library.utils.FileTools;
import ren.solid.library.utils.LogUtils;
import ren.solid.library.utils.UIUtils;
import ren.solid.library.widget.LinearDecoration;
import ren.solid.library.widget.StatusViewLayout;

/**
 * Created by Administrator on 2016/12/11.
 */

public class DocListFragment extends BaseFragment implements LoadDocsView {
    private RecyclerView mRecyclerView;
    private StatusViewLayout status_view;
    private LinearLayoutManager mLayoutManager;

    private int mType ;
    private LoadDocsPresenter mPresenter;
    private DocsAdapter mAdapter;


    public static DocListFragment newInstance(int type) {
        Bundle args = new Bundle();
        DocListFragment fragment = new DocListFragment();
        args.putInt("type", type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mType = getArguments().getInt("type");
        mPresenter=new LoadDocsPresenterImpl((BaseActivity) getActivity(),this);
    }


    @Override
    public void onLoadDocsSuccess(final List<DocumentBean> data) {
        //开启一个线程对所有获得的文档对象进行重新包装，赋值相关属性
        ThreadManager.getLongPool().submit(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<data.size();i++){
                    wrapDoc(data.get(i));
                }
                UIUtils.post(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.addAll(data,true);
                        if (mAdapter.getDatas().size() == 0) {
                            status_view.showEmpty();
                        } else {
                            status_view.showContent();
                        }
                    }
                });
            }
        });
    }


    @Override
    public void onLoadDocsFail(Exception e) {
        status_view.showError(e.getMessage());
    }


    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_recyclerview;
    }

    @Override
    protected void setUpView() {
        mRecyclerView = $(R.id.recycle_view);
        status_view = $(R.id.status_view);
        status_view.showLoading();
        //初始化列表控件
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new LinearDecoration(getContext(), RecyclerView.VERTICAL, 2));
        ((SimpleItemAnimator) mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        mAdapter = new DocsAdapter(getActivity(), new ArrayList<DocumentBean>());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void setUpData() {
        if(mPresenter==null){
            mPresenter=new LoadDocsPresenterImpl((BaseActivity) getActivity(),this);
        }
        mPresenter.loadDocs(mType,"");
    }

    /**
     * 1、赋值该文档下载保存的本地路径
     * 2、判断该文件是否下载完成了，并赋予下载完成状态
     * 3、判断该文件是否是部分下载，如果是，连接数据库获得已经下载的百分比，并赋予下载暂停状态
     * 4、从未下载过，赋予未下载暂停状态
     * @param bean
     */
    public void wrapDoc(DocumentBean bean) {
        // 赋值文件的保存路径
        File docFile = getDocFile(bean.getFileName());
        bean.setSavePath(docFile.getAbsolutePath());
        if (docFile.exists()) {
            if (docFile.length() == bean.getFileLength()) {
                //我们缓存的目录里面已经有了这个文件，并且大小一致,说明已下载完毕
                LogUtils.i("zhoul",bean.getFileName()+"已经下载完成了");
                bean.setState(ConstantValue.STATE_DOWNLOADED);
                bean.setProgress(100);
                return;
            }else{
                LogUtils.i("zhoul","存在但不完整");
            }
        }
        if(((GuidanceDocActivity)getActivity()).mDocDao==null){
            ((GuidanceDocActivity)getActivity()).mDocDao=new DocDao(getMContext());
        }
        DocumentBean db=((GuidanceDocActivity)getActivity()).mDocDao.queryById(bean.getFileName());
        if(db!=null){//以前下载过，记录断点位置
            bean.setState(ConstantValue.STATE_PAUSEDOWNLOAD);
            bean.setProgress(db.getProgress());
        }else{
            // 既没有下载
            bean.setState(ConstantValue.STATE_UNDOWNLOAD);
            bean.setProgress(0);
        }
    }

    public File getDocFile(String packageName) {
        String dir = FileTools.getDir("download");
        return new File(dir, packageName);
    }
}
