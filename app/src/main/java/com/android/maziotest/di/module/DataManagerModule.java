package com.android.maziotest.di.module;


import com.android.maziotest.data.AppErrorHelper;
import com.android.maziotest.data.DataManager;
import com.android.maziotest.data.IAppErrorHelper;
import com.android.maziotest.data.IDataManager;
import com.android.maziotest.di.scopes.ApplicationScope;
import dagger.Module;
import dagger.Provides;

/**
 * Created by zack_barakat
 */

@Module(includes = ApiModule.class)
public class DataManagerModule {

    @Provides
    @ApplicationScope
    public IDataManager provideDataManager(DataManager manager) {
        return manager;
    }

    @Provides
    @ApplicationScope
    public IAppErrorHelper provideErrorHelper(AppErrorHelper errorHelper) {
        return errorHelper;
    }
}
