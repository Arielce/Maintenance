package com.richsoft.maintenace.workorder.workorderdetail.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import com.richsoft.maintenace.R;
import com.richsoft.maintenace.bean.workorder.DocumentBean;
import com.richsoft.maintenace.common.ConstantValue;
import com.richsoft.maintenace.common.Urls;
import com.richsoft.maintenace.workorder.workorderdetail.ui.GuidanceDocActivity;
import com.yolanda.nohttp.Headers;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.download.DownloadListener;
import com.yolanda.nohttp.download.DownloadRequest;
import java.io.File;
import java.text.DecimalFormat;
import java.util.List;

import ren.solid.library.activity.ViewPicActivity;
import ren.solid.library.adapter.SolidRVBaseAdapter;
import ren.solid.library.net.CallServer;
import ren.solid.library.utils.FileTools;
import ren.solid.library.utils.LogUtils;

/**
 * 作者：e430 on 2017/1/10 12:49
 * <p>
 * 邮箱：chengzehao@163.com
 */

public class DocsAdapter extends SolidRVBaseAdapter<DocumentBean> {

    public DocsAdapter(Context context, List<DocumentBean> beans) {
        super(context, beans);
    }

    @Override
    protected void onBindDataToView(final SolidCommonViewHolder holder, final DocumentBean bean, final int position) {
        holder.setText(R.id.tv_doc_name, bean.getFileName());
        holder.setText(R.id.tv_size, "【文件大小】" + getDataSize(bean.getFileLength()));

        if (bean.getFileName().endsWith("PDF") || bean.getFileName().endsWith("pdf")) {
            holder.setImage(R.id.icon, R.mipmap.pdf);
        } else if (bean.getFileName().endsWith("doc")) {
            holder.setImage(R.id.icon, R.mipmap.doc);
        } else if (bean.getFileName().endsWith("docx")) {
            holder.setImage(R.id.icon, R.mipmap.doc);
        } else if (bean.getFileName().endsWith("xls")) {
            holder.setImage(R.id.icon, R.mipmap.xls);
        } else if (bean.getFileName().endsWith("xlsx")) {
            holder.setImage(R.id.icon, R.mipmap.xls);
        } else if (bean.getFileName().endsWith("jpg")) {
            holder.setImage(R.id.icon, R.mipmap.jpg);
        } else {
            holder.setImage(R.id.icon, R.mipmap.txt);
        }
        //刷新状态按钮
        refreshState(bean,holder);
        //状态按钮点击响应
        holder.setOnClickListener(R.id.pcv_progress, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (bean.getState()) {
                    case ConstantValue.STATE_UNDOWNLOAD:
                        // 未下载 点击去下载
                        LogUtils.i("zhoul","由未下载去下载");
                        doDownLoad(bean,position);
                        break;
                    case ConstantValue.STATE_DOWNLOADING:
                        // 下载中 点击去暂停下载
                        LogUtils.i("zhoul","由下载中去暂停");
                        ((GuidanceDocActivity) mContext).mRequests.get(bean.getFileName()).cancel();
                        ((GuidanceDocActivity) mContext).mRequests.remove(bean.getFileName());
                        break;
                    case ConstantValue.STATE_PAUSEDOWNLOAD:
                        // 暂停 点击去下载
                        LogUtils.i("zhoul","由暂停去下载");
                        doDownLoad(bean,position);
                        break;
                    case ConstantValue.STATE_WAITINGDOWNLOAD:
                        LogUtils.i("zhoul","等待中点击无效");
                        break;
                    case ConstantValue.STATE_DOWNLOADFAILED:
                        // 下载失败 点击去下载
                        LogUtils.i("zhoul","由失败去下载");
                        doDownLoad(bean,position);
                        break;
                    case ConstantValue.STATE_DOWNLOADED:
                        openDoc(bean,holder);
                        break;
                }
            }
        });
    }

    private void openDoc(DocumentBean data,SolidCommonViewHolder holder){
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        //打开文件之前判断文件是否存在
        File file = new File(data.getSavePath());
        if (file.exists()) {
            Uri uri = Uri.fromFile(new File(data.getSavePath()));
            if (data.getFileName().endsWith("PDF") || data.getFileName().endsWith("pdf")) {
                intent.setDataAndType(uri, "application/pdf");
                //PdfActivity.openActivity(mContext,data.getFileName());
                //return;
            } else if (data.getFileName().endsWith("doc")) {
                intent.setDataAndType(uri, "application/msword");
            } else if (data.getFileName().endsWith("xls") || data.getFileName().endsWith("xlsx")) {
                intent.setDataAndType(uri, "application/vnd.ms-excel");
            } else if (data.getFileName().endsWith("jpg")||data.getFileName().endsWith("png")) {
                //intent.setDataAndType(uri, "image/jpeg");
                ViewPicActivity.openActivity(mContext, holder.getView(R.id.pcv_progress), data.getSavePath());
                return;
            } else {
                ((GuidanceDocActivity) mContext).showMsgShortTime("不支持该类型文件预览！");
            }
            mContext.startActivity(intent);
        }
    }

    /**
     * 下载监听
     */
    private DownloadListener downloadListener = new DownloadListener() {

        @Override
        public void onStart(int what, boolean isResume, long beforeLength, Headers headers, long allCount) {
            mBeans.get(what).setState(ConstantValue.STATE_DOWNLOADING);
            notifyItemChanged(what);
            int progress = mBeans.get(what).getProgress();
            if (allCount != 0) {
                progress = (int) (beforeLength * 100 / allCount);
            }
            mBeans.get(what).setProgress(progress);
            notifyItemChanged(what);
        }

        @Override
        public void onDownloadError(int what, Exception exception) {
            mBeans.get(what).setState(ConstantValue.STATE_DOWNLOADFAILED);
            notifyItemChanged(what);
        }

        @Override
        public void onProgress(int what, int progress, long fileCount) {
            mBeans.get(what).setProgress(progress);
            ((GuidanceDocActivity)mContext).mDocDao.createOrUpdate(mBeans.get(what));
            notifyItemChanged(what);
        }

        @Override
        public void onFinish(int what, String filePath) {
            mBeans.get(what).setState(ConstantValue.STATE_DOWNLOADED);
            ((GuidanceDocActivity)mContext).mDocDao.deleteById(mBeans.get(what).getFileName());
            notifyItemChanged(what);
        }

        @Override
        public void onCancel(int what) {
            mBeans.get(what).setState(ConstantValue.STATE_PAUSEDOWNLOAD);
            notifyItemChanged(what);
        }

    };

    @Override
    public int getItemLayoutID(int viewType) {
        return R.layout.item_document;
    }

    /**
     * 返回byte的数据大小对应的文本
     *
     * @param size
     * @return
     */
    public String getDataSize(long size) {
        DecimalFormat formater = new DecimalFormat("####.00");
        if (size < 1024) {
            return size + "bytes";
        } else if (size < 1024 * 1024) {
            float kbsize = size / 1024f;
            return formater.format(kbsize) + "KB";
        } else if (size < 1024 * 1024 * 1024) {
            float mbsize = size / 1024f / 1024f;
            return formater.format(mbsize) + "MB";
        } else if (size < 1024 * 1024 * 1024 * 1024) {
            float gbsize = size / 1024f / 1024f / 1024f;
            return formater.format(gbsize) + "GB";
        } else {
            return "size: error";
        }
    }

    private void refreshState(DocumentBean bean, SolidCommonViewHolder holder) {
        int state = bean.getState();
        switch (state) {
            case ConstantValue.STATE_UNDOWNLOAD:
                // 未下载
                holder.setProgressText(R.id.pcv_progress, "下载");
                holder.setProgressIcon(R.id.pcv_progress, R.drawable.ic_download);
                break;
            case ConstantValue.STATE_DOWNLOADING:
                // 下载中
                holder.setProgressIcon(R.id.pcv_progress, R.drawable.ic_pause);
                holder.setProgressEnable(R.id.pcv_progress, true);
                holder.setCirProgress(R.id.pcv_progress, bean.getProgress());
                holder.setMax(R.id.pcv_progress, 100);
                holder.setProgressText(R.id.pcv_progress, bean.getProgress() + "%");
                break;
            case ConstantValue.STATE_WAITINGDOWNLOAD:
                // 等待
                holder.setProgressText(R.id.pcv_progress, "等待");
                holder.setProgressIcon(R.id.pcv_progress, R.drawable.ic_pause);
                break;
            case ConstantValue.STATE_PAUSEDOWNLOAD:
                // 暂停 继续下载 去下载
                holder.setProgressEnable(R.id.pcv_progress, true);
                holder.setCirProgress(R.id.pcv_progress, bean.getProgress());
                holder.setMax(R.id.pcv_progress, 100);
                holder.setProgressText(R.id.pcv_progress, bean.getProgress() + "%");
                holder.setProgressIcon(R.id.pcv_progress, R.drawable.ic_resume);
                break;
            case ConstantValue.STATE_DOWNLOADED:
                // 下载成功 安装 去安装
                holder.setProgressText(R.id.pcv_progress, "打开");
                holder.setProgressIcon(R.id.pcv_progress, R.drawable.ic_smart_update);
                break;
            case ConstantValue.STATE_DOWNLOADFAILED:
                // 下载失败 重试 去下载
                holder.setProgressText(R.id.pcv_progress, "重试");
                holder.setProgressIcon(R.id.pcv_progress, R.drawable.ic_redownload);
                break;
            default:
                break;
        }
    }

    private void doDownLoad(DocumentBean bean,int position){
        //首先切换为等待
        bean.setState(ConstantValue.STATE_WAITINGDOWNLOAD);
        notifyItemChanged(position);
        DownloadRequest downloadRequest = NoHttp.createDownloadRequest(Urls.SERVER_URL + bean.getFilePath(), FileTools.getDir("download"), bean.getFileName(), true, true);
        ((GuidanceDocActivity) mContext).mRequests.put(bean.getFileName(), downloadRequest);
        CallServer.getDownloadInstance().add(position, downloadRequest, downloadListener);
    }
}
