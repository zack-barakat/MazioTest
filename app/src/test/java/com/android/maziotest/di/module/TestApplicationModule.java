package com.android.maziotest.di.module;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.android.maziotest.data.prefs.AppPreferencesHelper;
import com.android.maziotest.data.prefs.IPreferencesHelper;
import com.android.maziotest.di.qualifiers.ApplicationContext;
import com.android.maziotest.di.scopes.ApplicationScope;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.spy;


@Module
public abstract class TestApplicationModule {

    @Binds
    @ApplicationContext
    public abstract Context provideContext(Application application);

    @Provides
    @ApplicationScope
    public static PackageInfo packageInfo(@ApplicationContext Context context) {
        try {
            return context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Provides
    @ApplicationScope
    static IPreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {

        return spy(appPreferencesHelper);
    }
}
