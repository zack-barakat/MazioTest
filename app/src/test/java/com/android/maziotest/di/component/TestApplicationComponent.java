package com.android.maziotest.di.component;

import android.app.Application;
import android.content.Context;
import com.android.maziotest.App;
import com.android.maziotest.data.IAppErrorHelper;
import com.android.maziotest.data.IDataManager;
import com.android.maziotest.data.network.IApiHelper;
import com.android.maziotest.data.prefs.IPreferencesHelper;
import com.android.maziotest.data.repositories.IPizzaRepository;
import com.android.maziotest.di.module.MockApiModule;
import com.android.maziotest.di.module.TestApplicationModule;
import com.android.maziotest.di.module.TestDataManagerModule;
import com.android.maziotest.di.qualifiers.ApplicationContext;
import com.android.maziotest.di.scopes.ApplicationScope;
import com.android.maziotest.mvp.CustomPizzaPresenterTest;
import com.android.maziotest.mvp.MenuPresenterTest;
import com.android.maziotest.mvp.OrderSummaryPresenterTest;
import com.android.maziotest.testCase.AppRobolectricTestCase;
import dagger.BindsInstance;
import dagger.Component;

@ApplicationScope
@Component(modules = {
        TestDataManagerModule.class,
        MockApiModule.class,
        TestApplicationModule.class})
public interface TestApplicationComponent extends ApplicationComponent {

    void inject(App app);

    @ApplicationContext
    Context context();

    void inject(AppRobolectricTestCase appRobolectricTestCase);

    void inject(MenuPresenterTest menuPresenterTest);

    void inject(CustomPizzaPresenterTest customPizzaPresenterText);

    void inject(OrderSummaryPresenterTest orderSummaryPresenterTest);

    @Component.Builder
    interface Builder {
        @BindsInstance
        TestApplicationComponent.Builder application(Application application);

        TestApplicationComponent build();
    }

    Application application();

    IPreferencesHelper getPreferenceHelper();

    IDataManager getDataManager();

    IPizzaRepository getPizzaRepository();


    IAppErrorHelper getAppErrorHelper();

    IApiHelper getApiHelper();
}
