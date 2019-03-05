package com.android.maziotest.di.component;

import com.android.maziotest.di.module.FragmentModule;
import com.android.maziotest.di.scopes.FragmentScope;
import com.android.maziotest.ui.base.BaseMvpFragment;
import dagger.Component;


@FragmentScope
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    void inject(BaseMvpFragment baseMvpFragment);

}