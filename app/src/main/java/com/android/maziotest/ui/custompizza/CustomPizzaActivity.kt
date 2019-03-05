package com.android.maziotest.ui.custompizza

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.android.maziotest.R
import com.android.maziotest.data.model.PizzaSelection
import com.android.maziotest.ui.base.BaseMvpActivity
import com.android.maziotest.ui.base.BasePresenter
import com.android.maziotest.ui.ordersummary.OrderSummaryActivity
import com.android.maziotest.utils.DialogHelper
import kotlinx.android.synthetic.main.activity_custom_pizza.*
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class CustomPizzaActivity : BaseMvpActivity(), CustomPizzaContracts.View {

    @Inject
    lateinit var mPresenter: CustomPizzaContracts.Presenter<CustomPizzaContracts.View>

    @Inject
    lateinit var dialogHelper: DialogHelper

    private lateinit var mAdapter: PizzasSelectionAdapter

    public override fun onCreate(savedInstanceState: Bundle?) {
        activityComponent?.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_pizza)
        setupLayout()
        mPresenter.onAttachView(this)
    }

    override fun sendExtrasToPresenter(extras: Bundle) {

    }

    private fun setupLayout() {
        supportActionBar?.title = getString(R.string.title_create_customize_pizza)
        btnConfirmSelection.setOnClickListener { mPresenter.onConfirmSelection() }
        setupPricesRecyclerView()
    }

    private fun setupPricesRecyclerView() {
        mAdapter = PizzasSelectionAdapter({ position ->
            mPresenter.onSelectPizza(position)
        }, { position ->
            mPresenter.onCancelPizza(position)
        })
        rvPizzas.adapter = mAdapter
        val gridLayoutManager = GridLayoutManager(this, 2)
        rvPizzas.layoutManager = gridLayoutManager
    }

    public override fun getPresenter(): BasePresenter<*> {
        return mPresenter
    }

    override fun showPizzaFlavors(pizza: ArrayList<PizzaSelection>) {
        mAdapter.refreshPizzas(pizza)
    }

    override fun openConfirmationScreen() {
        startActivity<OrderSummaryActivity>()
    }

    override fun showAlreadySelectedTwoPizzasMessage() {
        dialogHelper.showAlert(this,
            dialogTitle = getString(R.string.title_information),
            dialogMessage = getString(R.string.text_already_have_two_flavors),
            positiveButton = getString(android.R.string.ok),
            onPositiveClick = {
                dialogHelper.closeDialog()
            })
    }

    override fun enableConfirmSelectionButton(enable: Boolean) {
        btnConfirmSelection.isEnabled = enable
    }

    override fun showPizzaSelection(selectionName: String, priceLabel: String) {
        tvPizzaName.text = selectionName
        tvPizzaPrize.text = priceLabel
    }
}
