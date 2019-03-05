package com.android.maziotest.ui.splash

import com.android.maziotest.ui.base.BasePresenter
import com.android.maziotest.ui.base.BaseView

interface SplashContracts {

    interface View : BaseView {
        fun showMenuScreen()
    }

    interface Presenter<V : View> : BasePresenter<V>
}
