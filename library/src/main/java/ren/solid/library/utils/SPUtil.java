package ren.solid.library.utils;

import android.content.Context;
import android.content.SharedPreferences.Editor;

public class SPUtil {
    private static final int MODE = Context.MODE_PRIVATE;
    private static Context mContext = UIUtils.getContext();
    private static final String USERINFO = "USERINFO";
    private static final String USER_STATE = "USER_STATE";
    private static final String USER_NAME = "USER_NAME";
    private static final String USER_NO = "USER_NO";
    private static final String USER_ID = "USER_ID";
    private static final String USER_ROLE = "USER_ROLE";
    private static final String USER_ROLE_DES = "USER_ROLE_DES";
    private static final String COMPANY_ID = "COMPANY_ID";
    private static final String COMPANY_NAME = "COMPANY_NAME";
    private static final String DEP_ID = "DEP_ID";
    private static final String DEP_NAME = "DEP_NAME";
    private static final String USER_POST_ID = "USER_POST_ID";
    private static final String USER_POST = "USER_POST";
    private static final String AVATER = "AVATER";
    private static final String PHONE = "PHONE";
    private static final String PHONE2 = "PHONE2";
    private static final String EMAIL = "EMAIL";
    private static final String REMARK = "REMARK";
    private static final String APPTOKEN = "APPTOKEN";


    private SPUtil() {
    }

    private static class SingletonHolder {
        private static SPUtil instance = new SPUtil();
    }

    public static SPUtil getInstance() {
        return SingletonHolder.instance;
    }

    /**
     * 存储首次登录
     */
    public void saveUserState(boolean userState) {
        Editor editor = mContext.getSharedPreferences(USERINFO, MODE).edit();
        editor.putBoolean(USER_STATE, userState);
        editor.commit();
    }

    /**
     * 获取首次登录
     */
    public boolean getUserState() {
        return mContext.getSharedPreferences(USERINFO, MODE).getBoolean(USER_STATE, true);
    }

    /**
     * 存储用户名
     */
    public void saveUserName(String name) {
        Editor editor = mContext.getSharedPreferences(USERINFO, MODE).edit();
        editor.putString(USER_NAME, name);
        editor.commit();
    }

    /**
     * 获取用户名
     */
    public String getUserName() {
        return mContext.getSharedPreferences(USERINFO, MODE).getString(USER_NAME, "");
    }

    /**
     * 存储令牌
     */
    public void saveAppToken(String appToken) {
        Editor editor = mContext.getSharedPreferences(USERINFO, MODE).edit();
        editor.putString(APPTOKEN, appToken);
        editor.commit();
    }

    /**
     * 获取令牌
     */
    public String getAppToken() {
        return mContext.getSharedPreferences(USERINFO, MODE).getString(APPTOKEN, "");
    }

    /**
     * 存储用户登录账号
     */
    public void saveUserNo(String no) {
        Editor editor = mContext.getSharedPreferences(USERINFO, MODE).edit();
        editor.putString(USER_NO, no);
        editor.commit();
    }

    /**
     * 获取用户登录账号
     */
    public String getUserNo() {
        return mContext.getSharedPreferences(USERINFO, MODE).getString(USER_NO, "");
    }

    /**
     * 存储用户ID
     */
    public void saveUid(String uid) {
        Editor editor = mContext.getSharedPreferences(USERINFO, MODE).edit();
        editor.putString(USER_ID, uid);
        editor.commit();
    }

    /**
     * 获取用户id
     */
    public String getUid() {
        return mContext.getSharedPreferences(USERINFO, MODE).getString(USER_ID, "");
    }

    /**
     * 存储用户角色
     */
    public void saveUserRole(String role) {
        Editor editor = mContext.getSharedPreferences(USERINFO, MODE).edit();
        editor.putString(USER_ROLE, role);
        editor.commit();
    }

    /**
     * 获取用户角色
     */
    public String getUserRole() {
        return mContext.getSharedPreferences(USERINFO, MODE).getString(USER_ROLE, "");
    }

    /**
     * 存储用户角色描述
     */
    public void saveUserRoleDes(String role_des) {
        Editor editor = mContext.getSharedPreferences(USERINFO, MODE).edit();
        editor.putString(USER_ROLE_DES, role_des);
        editor.commit();
    }

    /**
     * 获取用户角色描述
     */
    public String getUserRoleDes() {
        return mContext.getSharedPreferences(USERINFO, MODE).getString(USER_ROLE_DES, "");
    }

    /**
     * 存储用户岗位ID
     */
    public void saveUserPostId(String pid) {
        Editor editor = mContext.getSharedPreferences(USERINFO, MODE).edit();
        editor.putString(USER_POST_ID, pid);
        editor.commit();
    }

    /**
     * 获取用户岗位ID
     */
    public String getUserPostId() {
        return mContext.getSharedPreferences(USERINFO, MODE).getString(USER_POST_ID, "");
    }

    /**
     * 存储用户岗位
     */
    public void saveUserPost(String pname) {
        Editor editor = mContext.getSharedPreferences(USERINFO, MODE).edit();
        editor.putString(USER_POST, pname);
        editor.commit();
    }

    /**
     * 获取用户岗位
     */
    public String getUserPost() {
        return mContext.getSharedPreferences(USERINFO, MODE).getString(USER_POST, "");
    }


    /**
     * 存储公司ID
     */
    public void saveCompanyId(String cid) {
        Editor editor = mContext.getSharedPreferences(USERINFO, MODE).edit();
        editor.putString(COMPANY_ID, cid);
        editor.commit();
    }

    /**
     * 获取公司ID
     */
    public String getCompanyId() {
        return mContext.getSharedPreferences(USERINFO, MODE).getString(COMPANY_ID, "");
    }

    /**
     * 存储公司
     */
    public void saveCompany(String cname) {
        Editor editor = mContext.getSharedPreferences(USERINFO, MODE).edit();
        editor.putString(COMPANY_NAME, cname);
        editor.commit();
    }

    /**
     * 获取公司
     */
    public String getCompany() {
        return mContext.getSharedPreferences(USERINFO, MODE).getString(COMPANY_NAME, "");
    }


    /**
     * 存储用户部门id
     */
    public void saveUserDepartmentId(String did) {
        Editor editor = mContext.getSharedPreferences(USERINFO, MODE).edit();
        editor.putString(DEP_ID, did);
        editor.commit();
    }

    /**
     * 获取用户部门id
     */
    public String getUserDepartmentId() {
        return mContext.getSharedPreferences(USERINFO, MODE).getString(DEP_ID, "");
    }

    /**
     * 存储用户部门id
     */
    public void saveUserDepartment(String dname) {
        Editor editor = mContext.getSharedPreferences(USERINFO, MODE).edit();
        editor.putString(DEP_NAME, dname);
        editor.commit();
    }

    /**
     * 获取用户部门id
     */
    public String getUserDepartment() {
        return mContext.getSharedPreferences(USERINFO, MODE).getString(DEP_NAME, "");
    }

    /**
     * 存储用户头像
     */
    public void saveUserAvater(String avater) {
        Editor editor = mContext.getSharedPreferences(USERINFO, MODE).edit();
        editor.putString(AVATER, avater);
        editor.commit();
    }

    /**
     * 获取用户头像
     */
    public String getUserAvater() {
        return mContext.getSharedPreferences(USERINFO, MODE).getString(AVATER, "");
    }

    /**
     * 存储用户电话1
     */
    public void saveUserPhone(String phone) {
        Editor editor = mContext.getSharedPreferences(USERINFO, MODE).edit();
        editor.putString(PHONE, phone);
        editor.commit();
    }

    /**
     * 获取用户电话1
     */
    public String getUserPhone() {
        return mContext.getSharedPreferences(USERINFO, MODE).getString(PHONE, "");
    }

    /**
     * 存储用户电话2
     */
    public void saveUserPhone2(String phone2) {
        Editor editor = mContext.getSharedPreferences(USERINFO, MODE).edit();
        editor.putString(PHONE2, phone2);
        editor.commit();
    }

    /**
     * 获取用户电话2
     */
    public String getUserPhone2() {
        return mContext.getSharedPreferences(USERINFO, MODE).getString(PHONE2, "");
    }

    /**
     * 存储用户备注
     */
    public void saveUserRemark(String remark) {
        Editor editor = mContext.getSharedPreferences(USERINFO, MODE).edit();
        editor.putString(REMARK, remark);
        editor.commit();
    }

    /**
     * 获取用户备注
     */
    public String getUserRemark() {
        return mContext.getSharedPreferences(USERINFO, MODE).getString(REMARK, "");
    }

    /**
     * 存储用户邮箱
     */
    public void saveUserEmail(String remark) {
        Editor editor = mContext.getSharedPreferences(USERINFO, MODE).edit();
        editor.putString(EMAIL, remark);
        editor.commit();
    }

    /**
     * 获取用户邮箱
     */
    public String getUserEmail (){
        return mContext.getSharedPreferences(USERINFO, MODE).getString(EMAIL, "");
    }

}
