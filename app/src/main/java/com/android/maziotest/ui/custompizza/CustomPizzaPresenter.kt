package com.android.maziotest.ui.custompizza

import com.android.maziotest.R
import com.android.maziotest.data.IDataManager
import com.android.maziotest.data.model.Pizza
import com.android.maziotest.data.model.PizzaSelection
import com.android.maziotest.ui.base.BaseMvpPresenter
import javax.inject.Inject

class CustomPizzaPresenter @Inject
constructor(dataManager: IDataManager) : BaseMvpPresenter<CustomPizzaContracts.View>(dataManager),
    CustomPizzaContracts.Presenter<CustomPizzaContracts.View> {

    private lateinit var mPizzaSelection: ArrayList<PizzaSelection>

    override fun onAttachView(view: CustomPizzaContracts.View) {
        super.onAttachView(view)
        val disposable = mPizzaRepository.getPizzas()
            .map { pizzas ->
                return@map ArrayList(pizzas.map { it.toPizzaSelection() })
            }
            .subscribe({
                mPizzaSelection = it
                view.showPizzaFlavors(mPizzaSelection)
            }, {
                it.printStackTrace()
            })
        addToSubscription(disposable)
    }

    override fun onSelectPizza(position: Int) {
        if (!canAddToSelection()) {
            view.showAlreadySelectedTwoPizzasMessage()
            return
        }

        val pizza = mPizzaSelection[position]
        pizza.selected = true
        mPizzaSelection[position] = pizza
        view.showPizzaFlavors(mPizzaSelection)
        view.enableConfirmSelectionButton(getSelectedPizzas().size == 2)

        if (pizzaSelectionComplete()) {
            val customizedPizza = getCustomPizzaSelection()
            val priceLabel = String.format(mAppContext.getString(R.string.text_pizza_price), customizedPizza.price)
            view.showPizzaSelection(customizedPizza.name, priceLabel)
        } else {
            view.showPizzaSelection("-", "-")
        }
    }

    override fun onCancelPizza(position: Int) {
        val pizza = mPizzaSelection[position]
        pizza.selected = false
        mPizzaSelection[position] = pizza
        view.showPizzaFlavors(mPizzaSelection)
        view.enableConfirmSelectionButton(getSelectedPizzas().size == 2)
        view.showPizzaSelection("-", "-")
    }

    override fun onConfirmSelection() {
        val customizedPizza = getCustomPizzaSelection()
        mPizzaRepository.selectPizza(customizedPizza)
        view.openConfirmationScreen()
    }

    private fun getCustomPizzaSelection(): Pizza {
        val firstHalfPizza = getSelectedPizzas()[0]
        val secondHalfPizza = getSelectedPizzas()[1]
        val customizedPizza =
            mPizzaRepository.getCustomizedFlavourPizza(firstHalfPizza.toPizza(), secondHalfPizza.toPizza())
        return customizedPizza
    }

    private fun getSelectedPizzas(): ArrayList<PizzaSelection> {
        val selectedPizzaList = mPizzaSelection.filter { it.selected }
        return ArrayList(selectedPizzaList)
    }

    private fun canAddToSelection(): Boolean = getSelectedPizzas().size < 2

    private fun pizzaSelectionComplete(): Boolean = getSelectedPizzas().size == 2

}
