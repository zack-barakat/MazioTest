package com.android.maziotest.ui.base

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.view.ViewGroup
import com.android.maziotest.App
import com.android.maziotest.R
import com.android.maziotest.di.component.DaggerFragmentComponent
import com.android.maziotest.di.component.FragmentComponent
import com.android.maziotest.di.module.FragmentModule
import com.android.maziotest.utils.DialogHelper
import com.android.maziotest.utils.ResourceUtil
import javax.inject.Inject

abstract class BaseMvpFragment : Fragment(), BaseView {

    private var mFragmentComponent: FragmentComponent? = null
    internal var activity: BaseMvpActivity? = null
    private var loadingDialog: AlertDialog? = null

    protected abstract val presenter: BasePresenter<*>?

    @Inject
    lateinit var dialogHelper: DialogHelper

    val isActive: Boolean
        get() = isAdded && !isDetached

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onAttach(context: Context?) {
        assert(presenter != null)
        super.onAttach(context)
        if (context is BaseMvpActivity) {
            activity = context
        }
        getFragmentComponent()!!.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp(view)
        if (arguments != null) {
            passArgumentsToPresenter(arguments)
        }
    }

    protected abstract fun setUp(view: View)

    override fun onResume() {
        super.onResume()
        presenter!!.isViewPaused = false
    }

    override fun onPause() {
        super.onPause()
        presenter!!.isViewPaused = true
    }

    override fun onDestroyView() {
        presenter!!.onDestroyView()
        super.onDestroyView()
    }

    override fun onDetach() {
        activity = null
        super.onDetach()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun createComponent() {
        mFragmentComponent = DaggerFragmentComponent.builder()
            .fragmentModule(FragmentModule(this))
            .applicationComponent((getActivity()!!.application as App).component)
            .build()
    }

    fun getFragmentComponent(): FragmentComponent? {
        if (mFragmentComponent == null) {
            createComponent()
        }
        return mFragmentComponent
    }

    protected abstract fun passArgumentsToPresenter(arguments: Bundle?)

    override fun showProgress() {
        if (!isActive) {
            return
        }
        if (activity != null) {
            activity!!.showProgress()
        } else if (getActivity() != null) {
            hideProgress()
            if (loadingDialog == null) {
                val builder: AlertDialog.Builder =
                    AlertDialog.Builder(context, ResourceUtil.getStyleId(context, "transparentBackgroundDialog"))
                val loadingView = View.inflate(context, R.layout.loading_layout, null)
                builder.setView(loadingView)
                builder.setCancelable(false)
                loadingDialog = builder.create()
            }
            try {
                loadingDialog!!.window!!.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                loadingDialog!!.show()
            } catch (e: RuntimeException) {
                e.printStackTrace()
            }

        }
    }

    override fun hideProgress() {
        if (!isActive) {
            return
        }
        if (activity != null) {
            activity!!.hideProgress()
        } else if (getActivity() != null) {
            if (loadingDialog != null) {
                try {
                    loadingDialog!!.dismiss()
                } catch (e: RuntimeException) {
                    e.printStackTrace()
                }
            }
        }
    }

    override fun destroyView() {
        if (activity != null) {
            activity!!.destroyView()
        }
    }

    override fun showError(message: String, messageStyle: Int) {
        if (activity != null) {
            activity!!.showError(message, messageStyle)
        }
    }

    override fun showError(message: Int, messageStyle: Int) {
        if (activity != null) {
            activity!!.showError(message, messageStyle)
        }
    }
}
