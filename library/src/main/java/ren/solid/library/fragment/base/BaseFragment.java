package ren.solid.library.fragment.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import ren.solid.library.R;
import ren.solid.library.manager.EventCenter;
import ren.solid.library.utils.LogUtils;

/**
 * Created by zhoul
 * Date:2016/3/30
 * Time:11:30
 */
public abstract class BaseFragment extends Fragment {

    private View mContentView;
    private Context mContext;

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isBindEventBusHere()) {
            EventBus.getDefault().register(this);
        }
    }

    /**
     * is bind eventBus
     *
     * @return
     */
    protected abstract boolean isBindEventBusHere();

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContentView = inflater.inflate(setLayoutResourceID(), container, false);//setContentView(inflater, container);
        mContext = getContext();
        init();
        setUpView();
        setUpData();
        return mContentView;
    }

    protected abstract int setLayoutResourceID();

    /**
     * initialize before  setUpView and  setUpData
     */
    protected void init() {

    }

    protected abstract void setUpView();

    protected abstract void setUpData();

    protected <T extends View> T $(int id) {
        return (T) mContentView.findViewById(id);
    }

    protected View getContentView() {
        return mContentView;
    }

    public Context getMContext() {
        return mContext;
    }

    /**
     * startActivity
     *
     * @param clazz
     */
    protected void readyGo(Class<?> clazz) {
        Intent intent = new Intent(getActivity(), clazz);
        startActivity(intent);
    }

    /**
     * startActivity with bundle
     *
     * @param clazz
     * @param bundle
     */
    protected void readyGo(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(getActivity(), clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * startActivityForResult
     *
     * @param clazz
     * @param requestCode
     */
    protected void readyGoForResult(Class<?> clazz, int requestCode) {
        Intent intent = new Intent(getActivity(), clazz);
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
        Intent intent = new Intent(getActivity(), clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
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
     * 打印Log信息
     *
     * @param string 打印的文本
     */
    public void showLog(String string) {
        LogUtils.i("zhoul", string);
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
        mProgressDialog = new MaterialDialog.Builder(mContext).title(title).content(content).cancelable(isCancelable).canceledOnTouchOutside(isCancelable).progress(true, 0).show();
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
            commonMaterialDialogBuilder = new MaterialDialog.Builder(getContext());
        }
        commonMaterialDialog = commonMaterialDialogBuilder.title(title).content(message).onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(MaterialDialog dialog, DialogAction which) {
                onPositive(dialogId);
            }
        }).onNeutral(new MaterialDialog.SingleButtonCallback() {
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
            commonMaterialDialogBuilder = new MaterialDialog.Builder(getContext());
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


    public void showInputDialog(final int dialogId, String title, String content, int minLength, int maxLength, String hint, String preFill, int inputType) {
        // 当前有显示的Dialog，则不做操作
        if (inputMaterialDialog != null && inputMaterialDialog.isShowing()) {
            return;
        }
        if (inputMaterialDialogBuilder == null) {
            inputMaterialDialogBuilder = new MaterialDialog.Builder(getContext());
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
    public void onInputCallBack(int dialogId, CharSequence input) {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isBindEventBusHere()) {
            EventBus.getDefault().unregister(this);
        }
    }
}
