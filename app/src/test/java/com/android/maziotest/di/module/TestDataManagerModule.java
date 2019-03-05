package com.android.maziotest.di.module;

import com.android.maziotest.data.AppErrorHelper;
import com.android.maziotest.data.DataManager;
import com.android.maziotest.data.IAppErrorHelper;
import com.android.maziotest.data.IDataManager;
import com.android.maziotest.data.repositories.IPizzaRepository;
import com.android.maziotest.data.repositories.PizzaRepository;
import com.android.maziotest.di.scopes.ApplicationScope;
import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.spy;


@Module
public class TestDataManagerModule {

    @Provides
    @ApplicationScope
    IDataManager provideIAppDataManager(DataManager testDataManager) {
        return spy(testDataManager);
    }

    @Provides
    @ApplicationScope
    IPizzaRepository provideIPizzaRepository(PizzaRepository pizzaRepository) {
        return spy(pizzaRepository);
    }

    @Provides
    @ApplicationScope
    IAppErrorHelper provideIAppErrorHelper(AppErrorHelper errorHelper) {
        return spy(errorHelper);
    }
}
