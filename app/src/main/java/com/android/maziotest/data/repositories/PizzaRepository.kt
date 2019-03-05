package com.android.maziotest.data.repositories

import com.android.maziotest.data.model.Pizza
import com.android.maziotest.data.network.IApiHelper
import com.android.maziotest.di.scopes.ApplicationScope
import io.reactivex.Observable
import javax.inject.Inject


interface IPizzaRepository {
    fun getPizzas(): Observable<ArrayList<Pizza>>

    fun getCustomizedFlavourPizza(firstHalf: Pizza, secondHalf: Pizza): Pizza

    fun orderPizza(pizza: Pizza)

    fun getOrderedPizza(): Pizza?
}

@ApplicationScope
open class PizzaRepository @Inject constructor(private val apiHelper: IApiHelper) : IPizzaRepository {

    private var mOrderedPizza: Pizza? = null
    private var mPizzas: ArrayList<Pizza>? = null

    /**
     * get list of available pizzas and persist them in memory
     * @return list of pizzas
     */
    override fun getPizzas(): Observable<ArrayList<Pizza>> {
        return if (mPizzas != null) {
            Observable.just(mPizzas)
        } else {
            apiHelper.pizzas
                .doOnNext {
                    mPizzas = it
                }
        }
    }

    /**
     * @param firstHalf: flavor for the first half of the pizza
     * @param secondHalf: flavor for the second half of the pizza
     * @return customized pizza combines the two selected flavors
     */
    override fun getCustomizedFlavourPizza(firstHalf: Pizza, secondHalf: Pizza): Pizza {
        val customizedPizzaName = "${firstHalf.name} & ${secondHalf.name}"
        val customizedPizzaPrice = (firstHalf.price + secondHalf.price) / 2
        return Pizza(customizedPizzaName, customizedPizzaPrice)
    }

    /**
     * @param pizza: selected pizza to order
     */
    override fun orderPizza(pizza: Pizza) {
        mOrderedPizza = pizza
    }

    /**
     * @return pizza: ordered pizza
     */
    override fun getOrderedPizza(): Pizza? {
        return mOrderedPizza
    }

}