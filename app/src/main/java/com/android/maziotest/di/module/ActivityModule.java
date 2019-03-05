package com.android.maziotest.di.module;

import android.app.Activity;
import android.content.Context;
import com.android.maziotest.di.qualifiers.ActivityContext;
import com.android.maziotest.ui.splash.SplashContracts;
import com.android.maziotest.ui.splash.SplashPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return activity;
    }

    @Provides
    Activity provideActivity() {
        return activity;
    }

    @Provides
    SplashContracts.Presenter<SplashContracts.View> provideSplashPresenter(SplashPresenter splashPresenter) {
        return splashPresenter;
    }
}
