package ren.solid.library;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.URLConnectionNetworkExecutor;
import com.yolanda.nohttp.cache.DiskCacheStore;

import java.io.File;
import ren.solid.library.manager.BaseAppManager;
import ren.solid.library.utils.ToastUtils;

/**
 * Created by zhoul
 * Date:2016/3/30
 * Time:20:59
 */
public class SolidApplication extends Application {
    private static SolidApplication mInstance;
    // 全局上下文
    private static Context mContext;
    // 主线程
    private static Thread mMainThread;
    // 主线程id
    private static long mMainThreadId;
    // 主线程Handler
    private static Handler mMainHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        // 上下文
        mContext = getApplicationContext();
        // 主线程
        mMainThread = Thread.currentThread();
        //主线程ID
        mMainThreadId = android.os.Process.myTid();
        // 创建主线程的handler
        mMainHandler = new Handler();
        ToastUtils.init(this);
        File saveFolder = new File(Environment.getExternalStorageDirectory(), "StateGridVideo");
        if (!saveFolder.exists()) {
            saveFolder.mkdirs();
        }

        // 如果你需要自定义配置：
        NoHttp.initialize(this, new NoHttp.Config()
                // 设置全局连接超时时间，单位毫秒，默认10s。
                .setConnectTimeout(30 * 1000)
                // 设置全局服务器响应超时时间，单位毫秒，默认10s。
                .setReadTimeout(30 * 1000)
                // 配置网络层，默认使用URLConnection，如果想用OkHttp：OkHttpNetworkExecutor。
                .setNetworkExecutor(new URLConnectionNetworkExecutor())
                // 缓存数据保存到SD卡
                .setCacheStore(new DiskCacheStore(this))
        );

        //初始化zxing扫描
        ZXingLibrary.initDisplayOpinion(this);
    }

    public static SolidApplication getInstance() {
        return mInstance;
    }

    @Override
    public File getCacheDir() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File cacheDir = getExternalCacheDir();
            if (cacheDir != null && (cacheDir.exists() || cacheDir.mkdirs())) {
                return cacheDir;
            }
        }
        return super.getCacheDir();
    }

    public static Context getContext() {
        return mContext;
    }

    public static Thread getMainThread() {
        return mMainThread;
    }

    public static long getMainThreadId() {
        return mMainThreadId;
    }

    public static Handler getMainHandler() {
        return mMainHandler;
    }

    public static void exitApp() {
        BaseAppManager.getInstance().clear();
    }

}
