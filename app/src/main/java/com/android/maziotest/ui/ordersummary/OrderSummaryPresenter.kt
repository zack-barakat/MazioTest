package com.android.maziotest.ui.ordersummary

import com.android.maziotest.R
import com.android.maziotest.data.IDataManager
import com.android.maziotest.ui.base.BaseMvpPresenter
import javax.inject.Inject

class OrderSummaryPresenter @Inject
constructor(dataManager: IDataManager) : BaseMvpPresenter<OrderSummaryContracts.View>(dataManager),
    OrderSummaryContracts.Presenter<OrderSummaryContracts.View> {

    override fun onAttachView(view: OrderSummaryContracts.View) {
        super.onAttachView(view)
        val orderedPizza = mPizzaRepository.getOrderedPizza()
        orderedPizza?.let {
            val priceLabel = String.format(mAppContext.getString(R.string.text_pizza_price), it.price)
            view.showOrderSummary(it.name, priceLabel)
        }
    }

    override fun onOpenMenuClick() {
        view.openPizzaMenuScreen()
    }
}
