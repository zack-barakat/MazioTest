package com.android.maziotest.ui.menu

import com.android.maziotest.data.IDataManager
import com.android.maziotest.data.model.Pizza
import com.android.maziotest.ui.base.BaseMvpPresenter
import com.android.maziotest.ui.base.ErrorView
import javax.inject.Inject

class MenuPresenter @Inject
constructor(dataManager: IDataManager) : BaseMvpPresenter<MenuContracts.View>(dataManager),
    MenuContracts.Presenter<MenuContracts.View> {

    private var mPizzas = arrayListOf<Pizza>()

    override fun onAttachView(view: MenuContracts.View) {
        super.onAttachView(view)
        fetchAndShowPizzas()
    }

    override fun onSelectPizzaClick(position: Int) {
        val pizza = mPizzas[position]
        mPizzaRepository.orderPizza(mPizzas[position])
        view.openConfirmationScreen()
    }

    override fun onSelectCustomizedPizzaClick() {
        view.openCustomPizzaSelectionScreen()
    }

    private fun fetchAndShowPizzas() {
        view.showProgress()
        val disposable = mPizzaRepository.getPizzas()
            .subscribe({
                mPizzas = it
                view.showPizzas(mPizzas)
                view.hideProgress()
            }, {
                view.hideProgress()
                handleApiError(it, ErrorView.ERROR_TOAST)
            })
        addToSubscription(disposable)
    }
}
