package com.richsoft.maintenace.login.ui;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.richsoft.maintenace.R;
import com.richsoft.maintenace.bean.user.UserBean;
import com.richsoft.maintenace.home.ui.HomeActivity;
import com.richsoft.maintenace.login.presenter.LoginPresenter;
import com.richsoft.maintenace.login.presenter.LoginPresenterImpl;
import com.richsoft.maintenace.login.view.LoginView;

import java.util.ArrayList;

import butterknife.Bind;
import ren.solid.library.activity.base.BaseMainActivity;
import ren.solid.library.manager.EventCenter;
import ren.solid.library.net.NetUtils;

public class LoginActivity extends BaseMainActivity implements LoginView {
    private final int SDK_PERMISSION_REQUEST = 127;
    private String permissionInfo;
    @Bind(R.id.splash_version_name)
    protected TextView mVersionName;
    @Bind(R.id.splash_copyright)
    protected TextView mCopyright;
    @Bind(R.id.password)
    protected EditText passwordInput;
    @Bind(R.id.username)
    protected EditText usernameInput;
    @Bind(R.id.btnSignin)
    protected Button btnSignin;
    @Bind(R.id.showPassword)
    protected Switch showPassword;

    protected LoginPresenter loginPresenter;

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
        if (loginPresenter == null) {
            loginPresenter = new LoginPresenterImpl(this,this);
        }

        loginPresenter.getCopy(this);

        usernameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btnSignin.setEnabled(s.toString().trim().length() > 0&&passwordInput.getText().toString().length()>0);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        passwordInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btnSignin.setEnabled(s.toString().trim().length() > 0&&usernameInput.getText().toString().length()>0);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        showPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                passwordInput.setInputType(!isChecked ? InputType.TYPE_TEXT_VARIATION_PASSWORD : InputType.TYPE_CLASS_TEXT);
                passwordInput.setTransformationMethod(!isChecked ? PasswordTransformationMethod.getInstance() : null);
            }
        });
        btnSignin.setEnabled(false);
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameInput.getText().toString();
                String pwd = passwordInput.getText().toString();
                loginPresenter.login(username, pwd);
            }
        });
        getPersimmions();
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
        return R.layout.activity_login;
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {

    }

    @Override
    public void toMain(final UserBean user) {
        if(user!=null){
            readyGoThenKill(HomeActivity.class);
        }else{
            showDialog(0,getString(R.string.login_ex),getString(R.string.user_null),getString(R.string.known),true);
        }
    }


    @Override
    public void showLoginFailureMsg(Exception e) {
        showDialog(0,getString(R.string.login_ex),e.getMessage(),getString(R.string.known),true);
    }

    @Override
    public void fillCopy(String versionName, String copyright) {
        mVersionName.setText(versionName);
        mCopyright.setText(copyright);
    }

    @TargetApi(23)
    private void getPersimmions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ArrayList<String> permissions = new ArrayList<String>();
            /***
             * 定位权限为必须权限，用户如果禁止，则每次进入都会申请
             */
            // 定位精确位置
            if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
            }
            if(checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            }
            //读取电话状态权限，必要，需要获得手机相关信息
            if(checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED){
                permissions.add(Manifest.permission.READ_PHONE_STATE);
            }
            //拍摄权限，必要。
            if(checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                permissions.add(Manifest.permission.CAMERA);
            }
			/*
			 * 读写权限权限非必要权限(建议授予)只会申请一次，用户同意或者禁止，只会弹一次
			 */
            if (addPermission(permissions, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                permissionInfo += "Manifest.permission.WRITE_EXTERNAL_STORAGE Deny \n";
            }

            if (permissions.size() > 0) {
                requestPermissions(permissions.toArray(new String[permissions.size()]), SDK_PERMISSION_REQUEST);
            }
        }
    }

    @TargetApi(23)
    private boolean addPermission(ArrayList<String> permissionsList, String permission) {
        if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) { // 如果应用没有获得对应权限,则添加到列表中,准备批量申请
            if (shouldShowRequestPermissionRationale(permission)){
                return true;
            }else{
                permissionsList.add(permission);
                return false;
            }

        }else{
            return true;
        }
    }

    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        for(int i=0;i<grantResults.length;i++){
            if(grantResults[i]!=PackageManager.PERMISSION_GRANTED){
                finish();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
