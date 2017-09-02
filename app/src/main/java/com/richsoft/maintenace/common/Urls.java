package com.richsoft.maintenace.common;

import java.io.File;

/**
 * 作者：e430 on 2016/6/26 15:28
 * <p/>
 * 邮箱：chengzehao@163.com
 */
public class Urls {

    public static int PAGE_COUNT = 20;
    /**
     * 服务器IP
     */
    public static String SERVER_IP = "127.0.0.1";
//    public static String SERVER_IP = "192.168.191.1";

    /**
     * 服务器端口
     */
    public final static String SERVER_PORT = "27001";
//    public final static String SERVER_PORT = "8090";

    /**
     * 消息推送IP
     **/
    public final static String PUSH_SERVER_IP = "192.168.3.205";

    /**
     * 消息推送端口
     **/
    public final static String PUSH_SERVER_PORT = "5222";

    /**
     * 接口路径
     */
    public final static String SERVER_URL = "http://" + SERVER_IP + ":" + SERVER_PORT + File.separator + "BDYW" + File.separator;

    /**
     * 登录接口
     */
    public final static String LOGIN_URL = Urls.SERVER_URL + "login.do";


    /**
     * 修改头像接口
     */
    public final static String UPDATE_HEADPORTRAIT_URL = Urls.SERVER_URL + "user" + File.separator + "updatePic.do";

    /**
     * 修改电话接口
     */
    public final static String UPDATE_PHONE_URL = Urls.SERVER_URL + "user" + File.separator + "updatePhone.do";


    /**
     * 修改备用电话接口
     */
    public final static String UPDATE_PHONE2_URL = Urls.SERVER_URL + "user" + File.separator + "updatePhone2.do";

    /**
     * 修改邮箱接口
     */
    public final static String UPDATE_EMAIL_URL = Urls.SERVER_URL + "user" + File.separator + "updateEmail.do";

    /**
     * 修改个性签名接口
     */
    public final static String UPDATE_REMARK_URL = Urls.SERVER_URL + "user" + File.separator + "updateRemark.do";


    /**
     * 修改密码接口
     */
    public final static String UPDATE_PWD_URL = Urls.SERVER_URL + "user" + File.separator + "updatePwd.do";

    /**
     * 获取工单列表
     */
    public final static String GET_WORKORDER_LIST = Urls.SERVER_URL + "getworklist.do";

    /**
     * 获取工单列表
     */
    public final static String GET_PLANNED_LABOR_LIST = Urls.SERVER_URL + "get_tourplan.do";

    /**
     * 获取变电站列表
     */
    public final static String GET_SUBSTAIONS = Urls.SERVER_URL + "substation.do";


    /**
     * 获取任务列表
     */
    public final static String GET_TASKS = Urls.SERVER_URL + "getwork_tasklist.do";

    /**
     * 获取用户列表
     */
    public final static String GET_USERS = Urls.SERVER_URL + "get_user_list.do";

    /**
     * 派发工单
     */
    public static final String URL_SENDWORK = Urls.SERVER_URL + "sendwork.do";

    /**
     * 获取绩效列表数据
     */
    public static final String URL_GETPERFORMANCELIST = Urls.SERVER_URL  + "getperformance.do";

    /**
     * 文档地址
     */
    public static final String URL_DOCUMENT = Urls.SERVER_URL   + "getWwytContent.do";

    /**
     * 获取视频url
     */
    public static final String URL_GETVIDEO = Urls.SERVER_URL + "getTaskVideo.do";

}
