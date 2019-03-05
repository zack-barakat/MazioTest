package com.android.maziotest.ui.ordersummary

import android.os.Bundle
import com.android.maziotest.R
import com.android.maziotest.ui.base.BaseMvpActivity
import com.android.maziotest.ui.base.BasePresenter
import com.android.maziotest.ui.menu.MenuActivity
import kotlinx.android.synthetic.main.activity_order_summary.*
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import javax.inject.Inject

class OrderSummaryActivity : BaseMvpActivity(), OrderSummaryContracts.View {

    @Inject
    lateinit var mPresenter: OrderSummaryContracts.Presenter<OrderSummaryContracts.View>

    public override fun onCreate(savedInstanceState: Bundle?) {
        activityComponent?.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_summary)
        setupLayout()
        mPresenter.onAttachView(this)
    }

    override fun sendExtrasToPresenter(extras: Bundle) {

    }

    private fun setupLayout() {
        supportActionBar?.title = getString(R.string.title_order_summary)
        btnOpenPizzasMenu.setOnClickListener {
            mPresenter.onOpenMenuClick()
        }
    }

    public override fun getPresenter(): BasePresenter<*> {
        return mPresenter
    }

    override fun showOrderSummary(name: String, priceLabel: String) {
        tvPizzaName.text = name
        tvPizzaPrice.text = priceLabel
    }

    override fun openPizzaMenuScreen() {
        startActivity(intentFor<MenuActivity>().clearTask().newTask())
    }
}
