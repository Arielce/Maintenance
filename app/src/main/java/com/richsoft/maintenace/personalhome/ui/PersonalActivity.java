package com.richsoft.maintenace.personalhome.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.cocosw.bottomsheet.BottomSheet;
import com.richsoft.maintenace.common.ConstantValue;
import com.richsoft.maintenace.R;
import com.richsoft.maintenace.common.Urls;
import com.richsoft.maintenace.login.ui.LoginActivity;
import com.richsoft.maintenace.personalhome.presenter.UpdateEmailPresenter;
import com.richsoft.maintenace.personalhome.presenter.UpdateEmailPresenterImpl;
import com.richsoft.maintenace.personalhome.presenter.UpdateHeadPortraitPresenter;
import com.richsoft.maintenace.personalhome.presenter.UpdateHeadPortraitPresenterImpl;
import com.richsoft.maintenace.personalhome.presenter.UpdatePhone2Presenter;
import com.richsoft.maintenace.personalhome.presenter.UpdatePhone2PresenterImpl;
import com.richsoft.maintenace.personalhome.presenter.UpdatePhonePresenter;
import com.richsoft.maintenace.personalhome.presenter.UpdatePhonePresenterImpl;
import com.richsoft.maintenace.personalhome.presenter.UpdatePwdPresenter;
import com.richsoft.maintenace.personalhome.presenter.UpdatePwdPresenterImpl;
import com.richsoft.maintenace.personalhome.presenter.UpdateRemarkPresenter;
import com.richsoft.maintenace.personalhome.presenter.UpdateRemarkPresenterImpl;
import com.richsoft.maintenace.personalhome.view.PersonalView;
import org.greenrobot.eventbus.EventBus;
import java.io.File;
import butterknife.Bind;
import de.hdodenhof.circleimageview.CircleImageView;
import ren.solid.library.activity.base.BaseActivity;
import ren.solid.library.imageloader.ImageLoader;
import ren.solid.library.manager.EventCenter;
import ren.solid.library.net.NetUtils;
import ren.solid.library.utils.SPUtil;
import ren.solid.library.utils.UIUtils;

public class PersonalActivity extends BaseActivity implements PersonalView, View.OnClickListener{
    @Bind(R.id.toolbar_layout)
    protected CollapsingToolbarLayout toolbar_layout;
    @Bind(R.id.iv_head_portrait)
    protected CircleImageView iv_head_portrait;
    @Bind(R.id.tv_company)
    protected TextView tv_company;
    @Bind(R.id.tv_department)
    protected TextView tv_department;
    @Bind(R.id.tv_position)
    protected TextView tv_position;
    @Bind(R.id.rl_remark)
    protected RelativeLayout rl_remark;
    @Bind(R.id.tv_remark)
    protected TextView tv_remark;
    @Bind(R.id.rl_change_pwd)
    protected RelativeLayout rl_change_pwd;
    @Bind(R.id.rl_phone)
    protected RelativeLayout rl_phone;
    @Bind(R.id.tv_phone)
    protected TextView tv_phone;
    @Bind(R.id.rl_phone2)
    protected RelativeLayout rl_phone2;
    @Bind(R.id.tv_phone2)
    protected TextView tv_phone2;
    @Bind(R.id.rl_email)
    protected RelativeLayout rl_email;
    @Bind(R.id.tv_email)
    protected TextView tv_email;
    private BottomSheet sheet;
    private Bitmap head;
    private Bitmap head_bitmap;
    private File temp;
    private UpdateEmailPresenter updateEmailPresenter;
    private UpdateHeadPortraitPresenter updateHeadPortraitPresenter;
    private UpdatePhone2Presenter updatePhone2Presenter;
    private UpdatePhonePresenter updatePhonePresenter;
    private UpdatePwdPresenter updatePwdPresenter;
    private UpdateRemarkPresenter updateRemarkPresenter;

    public final int phoneInputDialogId = 0;
    public final int phone2InputDialogId = 1;
    public final int emailInputDialogId = 2;
    public final int remarkInputDialogId = 3;


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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar_layout.setTitle(SPUtil.getInstance().getUserName());
        updateEmailPresenter = new UpdateEmailPresenterImpl(this, this);
        updateHeadPortraitPresenter = new UpdateHeadPortraitPresenterImpl(this, this);
        updatePhone2Presenter = new UpdatePhone2PresenterImpl(this, this);
        updatePhonePresenter = new UpdatePhonePresenterImpl(this, this);
        updatePwdPresenter = new UpdatePwdPresenterImpl(this, this);
        updateRemarkPresenter = new UpdateRemarkPresenterImpl(this, this);
        //填充用户相关信息
        refreshUserInfo();
        iv_head_portrait.setOnClickListener(this);
        rl_remark.setOnClickListener(this);
        rl_change_pwd.setOnClickListener(this);
        rl_phone.setOnClickListener(this);
        rl_phone2.setOnClickListener(this);
        rl_email.setOnClickListener(this);
    }

    public void refreshUserInfo() {
        tv_remark.setText(SPUtil.getInstance().getUserRemark());
        tv_company.setText(SPUtil.getInstance().getCompany());
        tv_department.setText(SPUtil.getInstance().getUserDepartment());
        tv_position.setText(SPUtil.getInstance().getUserPost());
        tv_phone.setText(SPUtil.getInstance().getUserPhone());
        tv_phone2.setText(SPUtil.getInstance().getUserPhone2());
        tv_email.setText(SPUtil.getInstance().getUserEmail());
        if(SPUtil.getInstance().getUserAvater().equals(Urls.SERVER_URL+"null")){
            Glide.with(mContext).load(R.mipmap.hughjackman).centerCrop().into(iv_head_portrait);
        }else{
            ImageLoader.displayImage(iv_head_portrait,SPUtil.getInstance().getUserAvater());
        }
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_personal;
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_head_portrait:
                sheet = new BottomSheet.Builder(PersonalActivity.this).icon(UIUtils.getDrawalbe(R.mipmap.ic_brightness_low_grey600_36dp)).title(UIUtils.getString(R.string.get_photo)).sheet(R.menu.menu_get_photo).listener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case R.id.by_take:
                                Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "head.jpg")));
                                startActivityForResult(intent2, 2);//采用ForResult打开
                                break;
                            case R.id.by_photos:
                                Intent intent1 = new Intent(Intent.ACTION_PICK, null);
                                intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                                startActivityForResult(intent1, 1);
                                break;
                        }
                        sheet.dismiss();
                    }
                }).build();
                sheet.show();
                break;
            case R.id.rl_remark:
                showInputDialog(remarkInputDialogId, "修改个性签名", "请您将字数控制在3~15个", 3, 15, "请您输入新签名..", tv_remark.getText().toString(), InputType.TYPE_CLASS_TEXT);
                break;
            case R.id.rl_change_pwd:
                showChangePwdView();
                break;
            case R.id.rl_phone:
                showInputDialog(phoneInputDialogId, "修改手机号码", "请保证11位手机号码", 11, 11, "请您输入手机号码..", tv_phone.getText().toString(), InputType.TYPE_CLASS_NUMBER);
                break;
            case R.id.rl_phone2:
                showInputDialog(phone2InputDialogId, "修改备用手机号码", "请保证11位手机号码", 11, 11, "请您输入备用手机号码..", tv_phone2.getText().toString(), InputType.TYPE_CLASS_NUMBER);
                break;
            case R.id.rl_email:
                showInputDialog(emailInputDialogId, "修改电子邮箱", "", 0, 0, "请输入电子邮箱..", tv_email.getText().toString(), InputType.TYPE_CLASS_TEXT);
                break;
        }
    }

    @Override
    public void refreshHeadPortrait(String path) {
        SPUtil.getInstance().saveUserAvater(Urls.SERVER_URL+path);
        if(iv_head_portrait!=null){
            iv_head_portrait.setImageBitmap(head_bitmap);
        }
        //刷新主页顶部头像
        EventBus.getDefault().post(new EventCenter(ConstantValue.EVENT_REFRESH_AVATER));
        showMsgShortTime(UIUtils.getString(R.string.chanage_success));
    }

    @Override
    public void refreshPhone(String phone) {
        SPUtil.getInstance().saveUserPhone(phone);
        if(tv_phone!=null){
            tv_phone.setText(SPUtil.getInstance().getUserPhone());
        }
        showMsgShortTime(UIUtils.getString(R.string.chanage_success));
    }

    @Override
    public void refreshPhone2(String phone2) {
        SPUtil.getInstance().saveUserPhone2(phone2);
        if(tv_phone2!=null){
            tv_phone2.setText(SPUtil.getInstance().getUserPhone2());
        }
        showMsgShortTime(UIUtils.getString(R.string.chanage_success));
    }

    @Override
    public void refreshEmail(String email) {
        SPUtil.getInstance().saveUserEmail(email);
        if(tv_email!=null){
            tv_email.setText(SPUtil.getInstance().getUserEmail());
        }
        showMsgShortTime(UIUtils.getString(R.string.chanage_success));
    }

    @Override
    public void refreshRemark(String remark) {
        SPUtil.getInstance().saveUserRemark(remark);
        if(tv_remark!=null){
            tv_remark.setText(SPUtil.getInstance().getUserRemark());
        }
        showMsgShortTime(UIUtils.getString(R.string.chanage_success));
    }

    @Override
    public void refreshPwd(String newPwd) {
        showMsgShortTime(UIUtils.getString(R.string.chanage_success));
        //注销退出
        cancellation();
    }

    public void cancellation() {
        SPUtil.getInstance().saveUid("");
        SPUtil.getInstance().saveUserName("");
        SPUtil.getInstance().saveAppToken("");
        SPUtil.getInstance().saveUserRole("");
        SPUtil.getInstance().saveUserRoleDes("");
        SPUtil.getInstance().saveUserPostId("");
        SPUtil.getInstance().saveUserPost("");
        SPUtil.getInstance().saveCompanyId("");
        SPUtil.getInstance().saveCompany("");
        SPUtil.getInstance().saveUserEmail("");
        SPUtil.getInstance().saveUserDepartmentId("");
        SPUtil.getInstance().saveUserDepartment("");
        SPUtil.getInstance().saveUserAvater("");
        SPUtil.getInstance().saveUserPhone("");
        SPUtil.getInstance().saveUserPhone2("");
        SPUtil.getInstance().saveUserRemark("");
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showNetFailureMsg(String msg) {
        showMsgShortTime(msg);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    cropPhoto(data.getData());//裁剪图片
                }

                break;
            case 2:
                if (resultCode ==RESULT_OK) {
                    temp = new File(Environment.getExternalStorageDirectory() + "/head.jpg");
                    cropPhoto(Uri.fromFile(temp));//裁剪图片
                }

                break;
            case 3:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    head = extras.getParcelable("data");
                    if (head != null) {
                        //复制一份，因为head在上传之后会被回收。复制的作为成功后显示用
                        head_bitmap = Bitmap.createBitmap(head);
                        updateHeadPortraitPresenter.updateHeadPortrait(head);
                    }
                }
                break;
            default:
                break;

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    /**
     * 调用系统的裁剪
     *
     * @param uri
     */
    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 100);
        intent.putExtra("outputY", 100);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }

    private MaterialDialog pwdDialog;
    private MaterialDialog.Builder pwdDialogBuilder;
    private EditText original_pwd;
    private EditText new_password;
    private EditText confirm_password;
    private View positiveAction;

    public void showChangePwdView() {
        if (pwdDialog != null && pwdDialog.isShowing()) {
            return;
        }
        if (pwdDialogBuilder == null) {
            pwdDialogBuilder = new MaterialDialog.Builder(mContext);
        }
        pwdDialog = pwdDialogBuilder.title("修改密码")
                .customView(R.layout.dialog_change_pwd_view, true)
                .positiveText(R.string.change)
                .negativeText(R.string.cancel)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        updatePwdPresenter.updatePwd(original_pwd.getText().toString(), confirm_password.getText().toString());
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                    }
                }).build();
        positiveAction = pwdDialog.getActionButton(DialogAction.POSITIVE);
        original_pwd = (EditText) pwdDialog.getCustomView().findViewById(R.id.original_pwd);
        new_password = (EditText) pwdDialog.getCustomView().findViewById(R.id.new_password);
        confirm_password = (EditText) pwdDialog.getCustomView().findViewById(R.id.confirm_password);
        original_pwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (original_pwd.getText().toString().length() > 0 && new_password.getText().toString().trim().length() > 0 && confirm_password.getText().toString().trim().length() > 0 && new_password.getText().toString().trim().equals(confirm_password.getText().toString().trim())) {
                    positiveAction.setEnabled(true);
                } else {
                    positiveAction.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        new_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (original_pwd.getText().toString().length() > 0 && new_password.getText().toString().trim().length() > 0 && confirm_password.getText().toString().trim().length() > 0 && new_password.getText().toString().trim().equals(confirm_password.getText().toString().trim())) {
                    positiveAction.setEnabled(true);
                } else {
                    positiveAction.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        confirm_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (original_pwd.getText().toString().length() > 0 && new_password.getText().toString().trim().length() > 0 && confirm_password.getText().toString().trim().length() > 0 && new_password.getText().toString().trim().equals(confirm_password.getText().toString().trim())) {
                    positiveAction.setEnabled(true);
                } else {
                    positiveAction.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        pwdDialog.show();
        positiveAction.setEnabled(false);
    }

    @Override
    public void onInputCallBack(int dialogId, CharSequence input) {
        switch (dialogId){
            case remarkInputDialogId:
                updateRemarkPresenter.updateRemark(input.toString());
                break;
            case phoneInputDialogId:
                updatePhonePresenter.updatePhone(input.toString());
                break;
            case phone2InputDialogId:
                updatePhone2Presenter.updatePhone2(input.toString());
                break;
            case emailInputDialogId:
                updateEmailPresenter.updateEmail(input.toString());
                break;
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (head_bitmap != null) {
            head_bitmap.recycle();
        }
    }
}
