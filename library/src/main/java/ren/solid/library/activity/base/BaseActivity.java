package ren.solid.library.activity.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.widget.Toast;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import butterknife.ButterKnife;
import ren.solid.library.R;
import ren.solid.library.manager.BaseAppManager;
import ren.solid.library.manager.EventCenter;
import ren.solid.library.net.CallServer;
import ren.solid.library.net.NetChangeObserver;
import ren.solid.library.net.NetStateReceiver;
import ren.solid.library.net.NetUtils;
import ren.solid.library.utils.LogUtils;

/**
 * Created by _zhoul
 * Date:2016/3/30
 * Time:9:40
 */
public abstract class BaseActivity extends AppCompatActivity {

    /**
     * Log tag
     */
    protected static String TAG_LOG = null;
    /**
     * 屏幕信息
     */
    protected int mScreenWidth = 0;
    protected int mScreenHeight = 0;
    protected float mScreenDensity = 0.0f;

    /**
     * 上下文
     */
    protected Context mContext = null;
    /**
     * 网络状态监听
     */
    protected NetChangeObserver mNetChangeObserver = null;

    /**
     * 对话框 (如需要监听对话框点击事件 请在子类中覆写onPositive和onNegative方法)
     **/
    protected MaterialDialog.Builder commonMaterialDialogBuilder;
    protected MaterialDialog commonMaterialDialog;

    /**
     * 显示输入对话框
     **/
    protected MaterialDialog.Builder inputMaterialDialogBuilder;
    protected MaterialDialog inputMaterialDialog;

    /**
     * 进度条对话框
     **/
    protected MaterialDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (toggleOverridePendingTransition()) {
            switch (getOverridePendingTransitionMode()) {
                case LEFT:
                    overridePendingTransition(R.anim.left_in, R.anim.right_out);
                    break;
                case RIGHT:
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                    break;
                case TOP:
                    overridePendingTransition(R.anim.top_in, R.anim.top_out);
                    break;
                case BOTTOM:
                    overridePendingTransition(R.anim.bottom_in, R.anim.bottom_out);
                    break;
                case SCALE:
                    overridePendingTransition(R.anim.scale_in, R.anim.scale_out);
                    break;
                case FADE:
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    break;
            }
        }
        super.onCreate(savedInstanceState);
        // base setup
        Bundle extras = getIntent().getExtras();
        if (null != extras) {
            getBundleExtras(extras);
        }

        if (isBindEventBusHere()) {
            EventBus.getDefault().register(this);
        }
        mContext = this;
        TAG_LOG = this.getClass().getSimpleName();
        BaseAppManager.getInstance().addActivity(this);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        mScreenDensity = displayMetrics.density;
        mScreenHeight = displayMetrics.heightPixels;
        mScreenWidth = displayMetrics.widthPixels;
        LogUtils.i(TAG_LOG, "准备填充界面");
        if (getContentViewLayoutID() != 0) {
            LogUtils.d(TAG_LOG, "界面无问题");
            setContentView(getContentViewLayoutID());
        } else {
            LogUtils.d(TAG_LOG, "界面异常");
            throw new IllegalArgumentException("You must return a right contentView layout resource Id");
        }
        mNetChangeObserver = new NetChangeObserver() {
            @Override
            public void onNetConnected(NetUtils.NetType type) {
                super.onNetConnected(type);
                onNetworkConnected(type);
            }

            @Override
            public void onNetDisConnect() {
                super.onNetDisConnect();
                onNetworkDisConnected();
            }
        };
        NetStateReceiver.registerObserver(mNetChangeObserver);
        initViewsAndEvents();
        getSavedInstanceState(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    /**
     * overridePendingTransition mode
     */
    public enum TransitionMode {
        LEFT, RIGHT, TOP, BOTTOM, SCALE, FADE
    }

    /**
     * toggle overridePendingTransition
     *
     * @return
     */
    protected abstract boolean toggleOverridePendingTransition();

    /**
     * get the overridePendingTransition mode
     */
    protected abstract TransitionMode getOverridePendingTransitionMode();

    /**
     * init all views and add events
     */
    protected abstract void initViewsAndEvents();

    protected void getSavedInstanceState(Bundle savedInstanceState) {
        //空方法，子类覆盖
    }

    /**
     * 网络连接正常
     */
    protected abstract void onNetworkConnected(NetUtils.NetType type);

    /**
     * 网络断开
     */
    protected abstract void onNetworkDisConnected();

    /**
     * bundle data
     *
     * @param extras
     */
    protected abstract void getBundleExtras(Bundle extras);

    /**
     * 是否绑定eventBus
     *
     * @return
     */
    protected abstract boolean isBindEventBusHere();

    /**
     * bind layout resource file
     *
     * @return id of layout resource
     */
    protected abstract int getContentViewLayoutID();

    /**
     * startActivity
     *
     * @param clazz
     */
    protected void readyGo(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    /**
     * startActivity with bundle
     *
     * @param clazz
     * @param bundle
     */
    protected void readyGo(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * startActivity then finish
     *
     * @param clazz
     */
    protected void readyGoThenKill(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
        finish();
    }

    /**
     * startActivity with bundle then finish
     *
     * @param clazz
     * @param bundle
     */
    protected void readyGoThenKill(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        finish();
    }

    /**
     * startActivityForResult
     *
     * @param clazz
     * @param requestCode
     */
    protected void readyGoForResult(Class<?> clazz, int requestCode) {
        Intent intent = new Intent(this, clazz);
        startActivityForResult(intent, requestCode);
    }

    /**
     * startActivityForResult with bundle
     *
     * @param clazz
     * @param requestCode
     * @param bundle
     */
    protected void readyGoForResult(Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * show toast
     *
     * @param msg
     */
    protected void showToast(String msg) {
        showMsgShortTime(msg);
    }

    /**
     * 打印Log信息
     *
     * @param string 打印的文本
     */
    public void showLog(String string) {
        LogUtils.i("zhoul", string);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Subscribe
    public void onEventMainThread(EventCenter eventCenter) {
        if (null != eventCenter) {
            onEventComming(eventCenter);
        }
    }

    /**
     * when event comming
     *
     * @param eventCenter
     */
    protected abstract void onEventComming(EventCenter eventCenter);

    @Override
    public void finish() {
        super.finish();
        BaseAppManager.getInstance().removeActivity(this);
        if (toggleOverridePendingTransition()) {
            switch (getOverridePendingTransitionMode()) {
                case LEFT:
                    overridePendingTransition(R.anim.left_in, R.anim.right_out);
                    break;
                case RIGHT:
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                    break;
                case TOP:
                    overridePendingTransition(R.anim.top_in, R.anim.top_out);
                    break;
                case BOTTOM:
                    overridePendingTransition(R.anim.bottom_in, R.anim.bottom_out);
                    break;
                case SCALE:
                    overridePendingTransition(R.anim.scale_in, R.anim.scale_out);
                    break;
                case FADE:
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    break;
            }
        }
    }

    /**
     * 提示信息
     **/
    private Toast mToast;

    /**
     * 显示toast信息，长时间显示
     *
     * @param string 显示的文本
     */
    public void showMsgLongTime(String string) {
        if (mToast == null) {
            mToast = Toast.makeText(mContext, string, Toast.LENGTH_LONG);
        } else {
            mToast.setText(string);
        }
        mToast.show();
    }

    /**
     * 显示toast信息，短时间显示
     *
     * @param string 显示的文本
     */
    public void showMsgShortTime(String string) {
        if (mToast == null) {
            mToast = Toast.makeText(mContext, string, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(string);
        }
        mToast.show();
    }

    /**
     * 显示一个进度条对话框
     *
     * @param title        标题
     * @param content      内容
     * @param isCancelable 是否可以取消
     */
    public void showProgressDialog(String title,
                                   String content,
                                   boolean isCancelable) {
        // 当前有显示的ProgressDialog，则不做操作
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            return;
        }
        mProgressDialog = new MaterialDialog.Builder(this).title(title).content(content).cancelable(isCancelable).canceledOnTouchOutside(isCancelable).progress(true, 0).show();
    }

    /**
     * 关闭进度条对话框
     */
    public void dismissProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    /**
     * 创建并显示一个Dialog
     *
     * @param dialogId     Dialog的标识
     * @param title        标题
     * @param message      内容
     * @param positive     确认文本
     * @param negative     取消文本
     * @param isCancelable 是否可以取消
     */
    public void showDialog(final int dialogId,
                           String title,
                           String message,
                           String positive,
                           String negative,
                           boolean isCancelable) {

        // 当前有显示的Dialog，则不做操作
        if (commonMaterialDialog != null && commonMaterialDialog.isShowing()) {
            return;
        }
        if (commonMaterialDialogBuilder == null) {
            commonMaterialDialogBuilder = new MaterialDialog.Builder(this);
        }
        commonMaterialDialog = commonMaterialDialogBuilder.title(title).content(message).onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(MaterialDialog dialog, DialogAction which) {
                onPositive(dialogId);
            }
        }).onNegative(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(MaterialDialog dialog, DialogAction which) {
                onNegative(dialogId);
            }
        }).positiveText(positive).negativeText(negative).show();
        commonMaterialDialog.setCanceledOnTouchOutside(isCancelable);
    }

    /**
     * 创建并显示一个Dialog,只有一个按钮
     *
     * @param dialogId     Dialog的标识
     * @param title        标题
     * @param message      内容
     * @param positive     按钮文本
     * @param isCancelable 是否可以取消
     */
    public void showDialog(final int dialogId,
                           String title,
                           String message,
                           String positive,
                           boolean isCancelable) {

        // 当前有显示的Dialog，则不做操作
        if (commonMaterialDialog != null && commonMaterialDialog.isShowing()) {
            return;
        }
        if (commonMaterialDialogBuilder == null) {
            commonMaterialDialogBuilder = new MaterialDialog.Builder(this);
        }

        commonMaterialDialog = commonMaterialDialogBuilder.title(title).content(message).onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(MaterialDialog dialog, DialogAction which) {
                onPositive(dialogId);
            }
        }).positiveText(positive).show();
        commonMaterialDialog.setCanceledOnTouchOutside(isCancelable);
    }

    /**
     * 关闭当前显示的对话框
     */
    public void dismissDialog() {
        try {
            if (commonMaterialDialog != null) {
                commonMaterialDialog.dismiss();
                commonMaterialDialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 点击确认按钮的回调方法(子类可实现该方法)
     *
     * @param dialogId 一个Activity可能有多个AlertDialog，这个id是来区别我们点击的是哪个AlertDialog
     */
    public void onPositive(int dialogId) {
    }

    /**
     * 点击取消按钮的回调方法，我们一般点取消都是关闭AlertDialog,这里我预留一个回调方法在这里(子类可实现该方法)
     *
     * @param dialogId
     */
    public void onNegative(int dialogId) {

    }

    public void showInputDialog(final int dialogId, String title, String content, int minLength, int maxLength, String hint, String preFill,int inputType) {
        // 当前有显示的Dialog，则不做操作
        if (inputMaterialDialog != null && inputMaterialDialog.isShowing()) {
            return;
        }
        if (inputMaterialDialogBuilder == null) {
            inputMaterialDialogBuilder = new MaterialDialog.Builder(this);
        }

        inputMaterialDialog = inputMaterialDialogBuilder
                .title(title)
                .content(content)
                .inputRangeRes(minLength, maxLength, R.color.md_red_500)
                .inputType(inputType)
                .input(hint, preFill, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                        onInputCallBack(dialogId, input);
                    }
                })
                .show();
    }

    /**
     * 输入对话框点击确认按钮的回调方法(子类可实现该方法)
     */
    public void onInputCallBack(int dialogId,CharSequence input) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        NetStateReceiver.removeRegisterObserver(mNetChangeObserver);
        if (isBindEventBusHere()) {
            EventBus.getDefault().unregister(this);
        }
        CallServer.getRequestInstance().cancelBySign(this);
    }

}
