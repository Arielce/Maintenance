package com.richsoft.maintenace.splash.view;

import android.view.animation.Animation;

public interface SplashView {

    void animateBackgroundImage(Animation animation);

    void initializeViews(String versionName, String copyright);

    void navigateToHomePage();
}
