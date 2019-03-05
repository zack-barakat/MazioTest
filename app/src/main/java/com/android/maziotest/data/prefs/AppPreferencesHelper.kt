package com.android.maziotest.data.prefs

import android.content.Context
import android.content.SharedPreferences
import com.android.maziotest.di.qualifiers.ApplicationContext
import com.android.maziotest.di.scopes.ApplicationScope
import javax.inject.Inject

interface IPreferencesHelper {
}

@ApplicationScope
class AppPreferencesHelper @Inject
constructor(@ApplicationContext context: Context) : IPreferencesHelper {

    companion object {
        private const val PREF_FILE_NAME = "Mazio"
    }

    private val mSharedPreferences: SharedPreferences
    private val mPreferenceEditors
        get() = mSharedPreferences.edit()

    init {
        mSharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
    }
}
