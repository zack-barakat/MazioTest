package com.android.maziotest.ui.menu

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.maziotest.R
import com.android.maziotest.data.model.Pizza
import kotlinx.android.synthetic.main.item_pizza.view.*


class PizzasAdapter(val onSelectPizza: (position: Int) -> Unit) : RecyclerView.Adapter<PizzasAdapter.PizzaHolder>() {

    private var mPizzas = arrayListOf<Pizza>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PizzaHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pizza, parent, false)
        return PizzaHolder(view)
    }

    override fun getItemCount() = mPizzas.size

    override fun onBindViewHolder(holder: PizzaHolder, position: Int) {
        holder.bind(mPizzas[position])
    }

    fun refreshPizzas(pizzas: ArrayList<Pizza>) {
        mPizzas = pizzas
        notifyDataSetChanged()
    }

    inner class PizzaHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(pizza: Pizza) {
            with(itemView) {
                tvPizzaName.text = pizza.name
                tvPizzaPrize.text = String.format(context.getString(R.string.text_pizza_price), pizza.price)
                btnConfirmPizzaSelection.setOnClickListener { onSelectPizza.invoke(adapterPosition) }
            }
        }
    }
}