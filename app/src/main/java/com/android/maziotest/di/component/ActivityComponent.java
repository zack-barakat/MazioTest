package com.android.maziotest.di.component;


import com.android.maziotest.di.module.ActivityModule;
import com.android.maziotest.di.scopes.ActivityScope;
import com.android.maziotest.ui.base.BaseMvpActivity;
import com.android.maziotest.ui.custompizza.CustomPizzaActivity;
import com.android.maziotest.ui.menu.MenuActivity;
import com.android.maziotest.ui.splash.SplashActivity;
import dagger.Component;


@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(BaseMvpActivity activity);

    void inject(SplashActivity activity);

    void inject(MenuActivity activity);

    void inject(CustomPizzaActivity activity);

}