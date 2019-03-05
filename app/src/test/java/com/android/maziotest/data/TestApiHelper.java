package com.android.maziotest.data;

import com.android.maziotest.data.model.Pizza;
import com.android.maziotest.data.network.IApiHelper;
import com.android.maziotest.di.scopes.ApplicationScope;
import com.android.maziotest.testUtils.TestDataGenerator;
import io.reactivex.Observable;

import java.util.ArrayList;


@ApplicationScope
public class TestApiHelper implements IApiHelper {

    @Override
    public Observable<ArrayList<Pizza>> getPizzas() {
        return Observable.just(TestDataGenerator.getPizzas());
    }
}
