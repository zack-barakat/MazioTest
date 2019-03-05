package com.android.maziotest.ui.custompizza

import com.android.maziotest.data.model.PizzaSelection
import com.android.maziotest.ui.base.BasePresenter
import com.android.maziotest.ui.base.BaseView

interface CustomPizzaContracts {

    interface View : BaseView {
        fun showPizzaFlavors(pizza: ArrayList<PizzaSelection>)

        fun openConfirmationScreen()

        fun showAlreadySelectedTwoPizzasMessage()

        fun enableConfirmSelectionButton(enable: Boolean)

        fun showPizzaSelection(selectionName: String, priceLabel: String)
    }

    interface Presenter<V : View> : BasePresenter<V> {
        fun onSelectPizza(position: Int)

        fun onCancelPizza(position: Int)

        fun onConfirmSelection()
    }
}
