package com.android.maziotest.ui.ordersummary

import com.android.maziotest.ui.base.BasePresenter
import com.android.maziotest.ui.base.BaseView

interface OrderSummaryContracts {

    interface View : BaseView {
        fun showOrderSummary(name: String, priceLabel: String)

        fun openPizzaMenuScreen()
    }

    interface Presenter<V : View> : BasePresenter<V> {
        fun onOpenMenuClick()
    }
}
