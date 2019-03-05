package com.android.maziotest.di.module;

import com.android.maziotest.data.TestApiHelper;
import com.android.maziotest.data.network.IApiHelper;
import com.android.maziotest.di.scopes.ApplicationScope;
import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.spy;


@Module(includes = {TestApplicationModule.class})
public class MockApiModule {

    @Provides
    @ApplicationScope
    IApiHelper provideApiHelper() {
        return spy(new TestApiHelper());
    }
}

