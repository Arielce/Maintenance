package com.richsoft.maintenace.common;

import java.io.File;

/**
 * Created by Administrator on 2016/12/11.
 */

public class ConstantValue {

    public static final int EVENT_BEGIN = 0X100;
    public static final int EVENT_REFRESH_AVATER = EVENT_BEGIN + 10;
    public static final int EVENT_SUBSTAION_CHOOSE = EVENT_BEGIN + 20;
    public static final int EVENT_TASK_CHOOSE = EVENT_BEGIN + 30;
    public static final int EVENT_RULER_CHOOSE = EVENT_BEGIN + 40;
    public static final int EVENT_MEMBER_CHOOSE = EVENT_BEGIN + 50;
    public static final int EVENT_SEND_WORK_SUCCESS = EVENT_BEGIN + 60;
    public static final int EVENT_REFRESH_MEMBERS = EVENT_BEGIN + 70;
    public static final int EVENT_RECEIVE_WORK_SUCCESS = EVENT_BEGIN + 80;
    public static final int EVENT_PREPARE_WORK_SUCCESS = EVENT_BEGIN + 90;
    public static final int EVENT_FIELD_WORK_SUCCESS = EVENT_BEGIN + 100;
    public static final int EVENT_SUMMARY_WORK_SUCCESS = EVENT_BEGIN + 110;
    public static final int EVENT_CONFIRE_WORK_SUCCESS = EVENT_BEGIN + 120;

    /**
     * 应用数据目录
     */
    public final static String APP_NAME = "SubstaionOperation";
    public final static String FILES_PATH = CustomApplication.getContext().getExternalFilesDir("").getAbsolutePath();
    public static final String APP_DATA_PATH = FILES_PATH + File.separator + APP_NAME;
    public static final String CS_APK_PATH = APP_DATA_PATH + File.separator + "cs.apk";
    public static final String APP_DATA_WORK_EXPORT_PATH = APP_DATA_PATH
            + File.separator + "作业导出目录";


    public static final String APP_DATA_AVI_PATH = APP_DATA_PATH
            + File.separator + "工作视频";

    public static final String APP_DATA_PDF_PATH = APP_DATA_PATH
            + File.separator + "指导文献";

    public static final int STATE_UNDOWNLOAD = 0;// 未下载
    public static final int STATE_DOWNLOADING = 1;// 下载中
    public static final int STATE_PAUSEDOWNLOAD = 2; // 暂停下载
    public static final int STATE_WAITINGDOWNLOAD = 3; // 等待下载
    public static final int STATE_DOWNLOADFAILED = 4;// 下载失败
    public static final int STATE_DOWNLOADED = 5;// 下载完成


}
