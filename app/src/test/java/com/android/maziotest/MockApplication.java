package com.android.maziotest;

import com.android.maziotest.di.component.DaggerTestApplicationComponent;
import com.android.maziotest.di.component.TestApplicationComponent;

public class MockApplication extends App {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void initDagger() {
        TestApplicationComponent applicationComponent = DaggerTestApplicationComponent.builder()
                .application(this)
                .build();
        setComponent(applicationComponent);
    }
}
