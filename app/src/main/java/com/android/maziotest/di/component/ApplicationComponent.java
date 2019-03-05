package com.android.maziotest.di.component;

import android.app.Application;
import android.content.Context;
import com.android.maziotest.App;
import com.android.maziotest.data.IAppErrorHelper;
import com.android.maziotest.data.IDataManager;
import com.android.maziotest.di.module.ApiModule;
import com.android.maziotest.di.module.ApplicationModule;
import com.android.maziotest.di.module.DataManagerModule;
import com.android.maziotest.di.qualifiers.ApplicationContext;
import com.android.maziotest.di.scopes.ApplicationScope;
import dagger.BindsInstance;
import dagger.Component;

@ApplicationScope
@Component(modules = {
        DataManagerModule.class,
        ApiModule.class,
        ApplicationModule.class})
public interface ApplicationComponent {

    void inject(App app);

    @ApplicationContext
    Context context();

    Application application();

    IDataManager getDataManager();

    IAppErrorHelper getErrorHelper();

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        ApplicationComponent build();
    }
}