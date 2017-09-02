/*
 * Copyright (c) 2015 [1076559197@qq.com | tchen0707@gmail.com]
 *
 * Licensed under the Apache License, Version 2.0 (the "License”);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.richsoft.maintenace.splash.model;

import android.content.Context;
import android.content.pm.PackageManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.richsoft.maintenace.R;

/**
 * Author:  zhoul
 * Email:   1076559197@qq.com | tauchen1990@gmail.com
 * Date:    2015/3/12.
 * Description:
 */
public class SplashModelImpl implements SplashModel {

    @Override
    public Animation getBackgroundImageAnimation(Context context) {
        return AnimationUtils.loadAnimation(context, R.anim.splash);
    }

    @Override
    public String getVersionName(Context context) {
        String versionName = null;
        try {
            versionName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return String.format(context.getResources().getString(R.string.splash_version), versionName);
    }

    @Override
    public String getCopyright(Context context) {
        return context.getResources().getString(R.string.splash_copyright);
    }
}
