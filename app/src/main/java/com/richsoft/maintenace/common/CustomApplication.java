package com.richsoft.maintenace.common;


import ren.solid.library.SolidApplication;

/**
 * 作者：e430 on 2016/12/10 22:40
 * <p>
 * 邮箱：chengzehao@163.com
 */

public class CustomApplication extends SolidApplication {

    public LocationModel locationModel;

    @Override
    public void onCreate() {
        super.onCreate();
        MultiTypeInstaller.install();
        locationModel = new LocationModel(getApplicationContext());
    }
}
