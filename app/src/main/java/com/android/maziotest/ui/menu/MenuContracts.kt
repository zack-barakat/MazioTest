package com.android.maziotest.ui.menu

import com.android.maziotest.data.model.Pizza
import com.android.maziotest.ui.base.BasePresenter
import com.android.maziotest.ui.base.BaseView

interface MenuContracts {

    interface View : BaseView {
        fun showPizzas(pizzas: ArrayList<Pizza>)

        fun openCustomPizzaSelectionScreen()

        fun openConfirmationScreen()
    }

    interface Presenter<V : View> : BasePresenter<V> {
        fun onSelectPizzaClick(position: Int)

        fun onSelectCustomizedPizzaClick()
    }
}
