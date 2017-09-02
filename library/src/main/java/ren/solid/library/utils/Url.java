package ren.solid.library.utils;

import java.io.File;

/**
 * 作者：e430 on 2016/6/26 15:28
 * <p/>
 * 邮箱：chengzehao@163.com
 */
public class Url {

    /**
     * 服务器IP
     */
    public static String SERVER_IP = "127.0.0.1";
//    public static String SERVER_IP = "192.168.191.1";

    /**
     * 服务器端口
     */
    public final static String SERVER_PORT = "18888";

//    public final static String SERVER_PORT = "8080";


    /**
     * 接口路径
     */
    public final static String SERVER_URL = "http://" + SERVER_IP + ":" + SERVER_PORT + File.separator + "sgm" + File.separator;


}
