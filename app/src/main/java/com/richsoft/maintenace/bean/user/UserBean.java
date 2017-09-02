package com.richsoft.maintenace.bean.user;

import com.richsoft.maintenace.workorder.annotation.SendWorkOrder;

import java.io.Serializable;

/**
 * 作者：e430 on 2016/7/11 22:00
 * <p/>
 * 邮箱：chengzehao@163.com
 */
public class UserBean implements Serializable {
    private String deptNo;//用户部门id
    private String deptName;//用户部门名称
    private String orgId;//用户公司id
    private String orgName;//用户公司名称
    private String postNo;//用户职位id
    private String postName;//用户职位名称
    private String pwd;//用户密码
    private String roleId;//用户角色id
    private String roleName;//用户角色名称
    @SendWorkOrder
    private String userId;//用户编号
    private String userNo;//用户登录编号
    private String userName;//用户姓名
    private String email;//用户电子邮箱
    private String phone;//用户手机号码
    private String phone2;//用户备用手机号码
    private String picPath;//用户头像网络地址
    private String remark;//用户个性签名

    private boolean chooseTag;//false-未选中 true-选中

    public UserBean() {
    }

    public UserBean(String userId, String userName, String postName) {
        this.userId = userId;
        this.userName = userName;
        this.postName = postName;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public void setPostNo(String postNo) {
        this.postNo = postNo;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public String getDeptName() {
        return deptName;
    }

    public String getOrgId() {
        return orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public String getPostNo() {
        return postNo;
    }

    public String getPostName() {
        return postName;
    }

    public String getPwd() {
        return pwd;
    }

    public String getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserNo() {
        return userNo;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPhone2() {
        return phone2;
    }

    public String getPicPath() {
        return picPath;
    }

    public String getRemark() {
        return remark;
    }

    public boolean isChooseTag() {
        return chooseTag;
    }

    public void setChooseTag(boolean chooseTag) {
        this.chooseTag = chooseTag;
    }

    @Override
    public String toString() {
        return userName;
    }
}
