package com.android.maziotest.data

import android.content.Context
import com.android.maziotest.data.repositories.IPizzaRepository
import com.android.maziotest.di.qualifiers.ApplicationContext
import com.android.maziotest.di.scopes.ApplicationScope
import javax.inject.Inject

interface IDataManager {
    @ApplicationContext
    fun getApplicationContext(): Context

    fun getAppErrorHelper(): IAppErrorHelper

    fun getPizzaRepository(): IPizzaRepository
}

@ApplicationScope
class DataManager @Inject
constructor(
    @ApplicationContext val mApplicationContext: Context,
    private val appErrorHelper: IAppErrorHelper,
    private val pizzaRepository: IPizzaRepository
) : IDataManager {

    override fun getApplicationContext(): Context {
        return mApplicationContext
    }

    override fun getAppErrorHelper(): IAppErrorHelper {
        return appErrorHelper
    }

    override fun getPizzaRepository(): IPizzaRepository {
        return pizzaRepository
    }
}
