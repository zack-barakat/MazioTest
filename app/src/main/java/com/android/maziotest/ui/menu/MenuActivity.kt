package com.android.maziotest.ui.menu

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.android.maziotest.R
import com.android.maziotest.data.model.Pizza
import com.android.maziotest.ui.base.BaseMvpActivity
import com.android.maziotest.ui.base.BasePresenter
import com.android.maziotest.ui.custompizza.CustomPizzaActivity
import com.android.maziotest.ui.ordersummary.OrderSummaryActivity
import kotlinx.android.synthetic.main.activity_menu.*
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class MenuActivity : BaseMvpActivity(), MenuContracts.View {

    @Inject
    lateinit var mPresenter: MenuContracts.Presenter<MenuContracts.View>

    private lateinit var mAdapter: PizzasAdapter

    public override fun onCreate(savedInstanceState: Bundle?) {
        activityComponent?.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        setupLayout()
        mPresenter.onAttachView(this)
    }

    override fun sendExtrasToPresenter(extras: Bundle) {

    }

    private fun setupLayout() {
        supportActionBar?.title = getString(R.string.title_menu)
        btnConfirmSelection.setOnClickListener { mPresenter.onSelectCustomizedPizzaClick() }
        setupPricesRecyclerView()
    }

    private fun setupPricesRecyclerView() {
        mAdapter = PizzasAdapter { position ->
            mPresenter.onSelectPizzaClick(position)
        }
        rvPizzas.adapter = mAdapter
        val gridLayoutManager = GridLayoutManager(this, 2)
        rvPizzas.layoutManager = gridLayoutManager
    }

    public override fun getPresenter(): BasePresenter<*> {
        return mPresenter
    }

    override fun showPizzas(pizzas: ArrayList<Pizza>) {
        mAdapter.refreshPizzas(pizzas)
    }

    override fun openCustomPizzaSelectionScreen() {
        startActivity<CustomPizzaActivity>()
    }

    override fun openConfirmationScreen() {
        startActivity<OrderSummaryActivity>()
    }
}
