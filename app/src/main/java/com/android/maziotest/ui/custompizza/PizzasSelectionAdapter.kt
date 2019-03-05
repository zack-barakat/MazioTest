package com.android.maziotest.ui.custompizza

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.maziotest.R
import com.android.maziotest.data.model.PizzaSelection
import kotlinx.android.synthetic.main.item_pizza.view.*


class PizzasSelectionAdapter(val onSelectPizza: (position: Int) -> Unit, val onCancelPizza: (position: Int) -> Unit) :
    RecyclerView.Adapter<PizzasSelectionAdapter.PizzaHolder>() {

    private var mPizzas = arrayListOf<PizzaSelection>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PizzaHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_select_pizza, parent, false)
        return PizzaHolder(view)
    }

    override fun getItemCount() = mPizzas.size

    override fun onBindViewHolder(holder: PizzaHolder, position: Int) {
        holder.bind(mPizzas[position])
    }

    fun refreshPizzas(pizzas: ArrayList<PizzaSelection>) {
        mPizzas = pizzas
        notifyDataSetChanged()
    }

    inner class PizzaHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(pizza: PizzaSelection) {
            with(itemView) {
                tvPizzaName.text = pizza.name
                tvPizzaPrize.text = String.format(context.getString(R.string.text_pizza_price), pizza.price)
                if (pizza.selected) {
                    btnConfirmPizzaSelection.text = context.getString(android.R.string.cancel)
                } else {
                    btnConfirmPizzaSelection.text = context.getString(R.string.text_order_this)
                }

                btnConfirmPizzaSelection.setOnClickListener {
                    if (pizza.selected) {
                        onCancelPizza.invoke(adapterPosition)
                    } else {
                        onSelectPizza.invoke(adapterPosition)
                    }
                }
            }
        }
    }
}